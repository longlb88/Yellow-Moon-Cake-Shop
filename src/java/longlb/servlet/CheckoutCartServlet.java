/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longlb.cart.CartItem;
import longlb.cart.CartObject;
import longlb.order.CustomerCheckoutInfo;
import longlb.paypal.PaypalCheckoutUtil;
import longlb.tblorderdetail.TblOrderDetailDAO;
import longlb.tblorders.TblOrdersCheckoutError;
import longlb.tblorders.TblOrdersDAO;
import longlb.tblorders.TblOrdersDTO;
import longlb.tblproducts.TblProductsDAO;
import longlb.tblproducts.TblProductsDTO;
import longlb.tblusers.TblUsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "CheckoutCartServlet", urlPatterns = {"/CheckoutCartServlet"})
public class CheckoutCartServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CheckoutCartServlet.class.getName());
    private final String ERROR_PAGE = "view-cart";
    private final String CHECKOUT_SUCCESSFUL_PAGE = "checkout_success_page";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	String name = request.getParameter("txtCustomerName");
	String address = request.getParameter("txtAddress");
	String phoneNumber = request.getParameter("txtPhone");
	String paymentMethod = request.getParameter("cmbPaymentMethod");
	String userID = "";
	String url = ERROR_PAGE;
	
	String paypalReturnToken = request.getParameter("token");
	
	try{
	    HttpSession session = request.getSession(false);
	    if (session != null){
		//get current user
		TblUsersDTO curUser = (TblUsersDTO) session.getAttribute("LOGIN_USER");
		if (curUser != null){
		    userID = curUser.getUserID();
		}
		
		CartObject cart = (CartObject) session.getAttribute("CART");
		if (cart != null){
		    if (!cart.getItemList().isEmpty()){
			//check error
			TblOrdersCheckoutError errors = new TblOrdersCheckoutError();
			boolean foundError = false;
			
			//when redirect from paypal payment page, payment medthod will be null
			//because all checkout info not exist on parameter
			if (paymentMethod == null){
			    // Get checkout info from session
			    CustomerCheckoutInfo checkoutInfo = (CustomerCheckoutInfo) session.getAttribute("CHECKOUT_INFO");
			    name = checkoutInfo.getName();
			    phoneNumber = checkoutInfo.getPhoneNumber();
			    address = checkoutInfo.getAddress();
			    paymentMethod = checkoutInfo.getPaymentMethod();
			}
			
			// if error not occur via payment
			//check name
			if (name.length() > 50){
			    errors.setNameLengthError("Name is limit to 50 chars");
			    foundError = true;
			}

			//check address
			if (address.length() > 100){
			    errors.setAddressLengthError("Address is limit to 100 chars");
			    foundError = true;
			}

			//check phone number
			if (phoneNumber.length() > 10){
			    errors.setPhoneNumberLengthError("Phone number is limit to 10 digits");
			    foundError = true;
			} else if (!phoneNumber.matches("^[0-9]*$")){
			    errors.setPhoneNumberFormarError("Phone number only allows digits");
			    foundError = true;
			}

			//check cakes
			String availableError = checkCakesAvaialable(cart);
			if (!availableError.isEmpty()){
			    errors.setCakeIsNotAvailableError(availableError + ". Please remove it from your cart to checkout!");
			    foundError = true;
			}

			String buyQuantityError = checkCakeQuantity(cart);
			if (!buyQuantityError.isEmpty()){
			    errors.setBuyQuantityError(buyQuantityError + "Please change quantity to checkout!");
			    foundError = true;
			}
			
			//Check payment status
			if (!foundError){			
			    boolean isCompleted = PaypalCheckoutUtil.captureOrder(paypalReturnToken);
			    if (isCompleted){
				//clear checkout info on session
				session.setAttribute("CHECKOUT_INFO", null);
			    }
			    else {
				errors.setNotPaidError("You have not completed PayPal payment!");
				foundError = true;
			    }
			}
			
			if (foundError){
			    request.setAttribute("CHECKOUT_ERROR", errors);
			} else { 
			    //create order
			    TblOrdersDAO ordersDAO = new TblOrdersDAO();
			    TblOrdersDTO createdOrder = null;
			    
			    //write order to DB
			    double totalMoney = cart.getTotalPriceOfCart();
			    boolean createOrderSuccessful = ordersDAO.createOrder(name, address, phoneNumber, userID, totalMoney, paymentMethod);
			    if (createOrderSuccessful){
				createdOrder = ordersDAO.getLatestOrders(name, address, phoneNumber, userID, totalMoney);
			    }
			    
			    //get order ID and order date
			    String orderID = "";
			    if (createdOrder != null){
				orderID = createdOrder.getOrderID();
			    }
			    
			    TblOrderDetailDAO orderDetailDAO = new TblOrderDetailDAO();
			    boolean result = orderDetailDAO.createOrder(cart.getItemList(), orderID);
			    
			    if (result){
				request.setAttribute("ORDER_ID", orderID);
				
				//reduce quantity of cake in store
				updateQuantityInStore(cart);
				
				//remove cart
				session.setAttribute("CART", null); 
				
				//clear checkout info on session
				session.setAttribute("CHECKOUT_INFO", null);
				
				url = CHECKOUT_SUCCESSFUL_PAGE;
			    }
			}
		    }
		}
	    }
	} catch (SQLException ex) {
	    log.error("SQLException: " + ex.getMessage());
	} catch (NamingException ex) {
	    log.error("NamingException: " + ex.getMessage());
	} finally {
	    Map<String, String> functionMap = (Map<String, String>) getServletContext().getAttribute("FUNCTIONS_MAP");
	    url = functionMap.get(url);
	    RequestDispatcher rd = request.getRequestDispatcher(url);
	    rd.forward(request, response);
	    out.close();
	}
    }
    
    public String checkCakesAvaialable(CartObject cart) throws SQLException, NamingException{
	boolean foundError = false;
	String cakeErrorString = "";
	TblProductsDAO productsDAO = new TblProductsDAO();
	List<String> notAvailableCakes = null;
	
	for (CartItem cartItem : cart.getItemList()) {
	    TblProductsDTO cake = cartItem.getCake();
	    int cakeID = cake.getProductID();    
	    boolean isAvailable = productsDAO.checkAvailableCake(cakeID);
	    if (!isAvailable){
		if (notAvailableCakes == null){
		    notAvailableCakes = new ArrayList<>();
		}
		
		notAvailableCakes.add(cake.getName());
		foundError = true;
	    }
	}
	
	if (foundError){
	    cakeErrorString = "Cake(s) not available/out of stock: ";
	    for (int i = 0; i < notAvailableCakes.size(); i++) {
		cakeErrorString += notAvailableCakes.get(i);
		if (i != notAvailableCakes.size() - 1){
		    cakeErrorString += ", ";
		}
	    }
	}
	return cakeErrorString;
    }
    
    public String checkCakeQuantity(CartObject cart) throws SQLException, NamingException{
	String cakeErrorString = "";
	
	for (CartItem cartItem : cart.getItemList()) {
	    TblProductsDTO cake = cartItem.getCake();
	    int buyQuantity = cartItem.getQuantity();
	    int cakeID = cake.getProductID();  
	    
	    int newQuantity = calculateCakeNewQuantity(cakeID, buyQuantity);
	    if (newQuantity < 0){	
		//set message
		int quantityInStore = newQuantity + buyQuantity;
		cakeErrorString += cake.getName() + " only have " + quantityInStore + " left!\n";
	    }
	}
	return cakeErrorString;
    }
    
    public int calculateCakeNewQuantity(int cakeID, int buyQuantity) throws SQLException, NamingException{
	TblProductsDAO dao = new TblProductsDAO();
	TblProductsDTO cake = dao.getCake(cakeID);
	int newQuantity = cake.getQuantity();
	
	//get cake
	boolean isAvailable = dao.checkAvailableCake(cakeID);
	if (isAvailable){    
	    newQuantity -= buyQuantity;
	}
	
	return newQuantity;
    }
    
    public void updateQuantityInStore(CartObject cart) throws NamingException, SQLException{
	for (CartItem cartItem : cart.getItemList()) {
	    int cakeID = cartItem.getCake().getProductID();
	    int buyQuantity = cartItem.getQuantity();
	    
	    int newQuantity = calculateCakeNewQuantity(cakeID, buyQuantity);
	    TblProductsDAO dao = new TblProductsDAO();
	    dao.updateQuantity(cakeID, newQuantity);
	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
