/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longlb.cart.CartObject;
import longlb.tblproducts.TblProductsDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "AddCakeToCartServlet", urlPatterns = {"/AddCakeToCartServlet"})
public class AddCakeToCartServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(AddCakeToCartServlet.class.getName());
    
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
	String cakeIDString = request.getParameter("cakeID");
	int cakeID = 0;
	String url = null;
		
	try {
	     
	    if (cakeIDString != null){
		cakeID = Integer.parseInt(cakeIDString);
	    }
	    
	    //if cake is available
	    TblProductsDAO productsDAO = new TblProductsDAO();
	    boolean isAvailableCake = productsDAO.checkAvailableCake(cakeID);
	    
	    if (isAvailableCake){
		//1. cutomer go to cart place
		HttpSession session = request.getSession(true);

		//2. Customer get cart
		CartObject cart = (CartObject) session.getAttribute("CART");
		if (cart == null){
		    cart = new CartObject();
		}

		//3. Customer drop cake to cart
		cart.addCakeToCart(cakeID);

		//4. Customer continue shopping
		session.setAttribute("CART", cart);
	    }
	    
	    //get info to recall search
	    String searchValue = request.getParameter("txtSearch");
	    String priceRange = request.getParameter("cmbPrice");
	    String category = request.getParameter("cmbCategory");
	    String page = request.getParameter("page");
	    if (page.trim().isEmpty()){
		page = "1";
	    }
	   
	    String destination = request.getParameter("destination");
	    
	    url = "search-cake"
		    + "?txtSearch=" + searchValue
		    + "&cmbPrice=" + priceRange 
		    + "&cmbCategory=" + category 
		    + "&page=" + page 
		    + "&destination=" + destination;
	    
	} catch(NumberFormatException ex){
	    log.error("NumberFormatException: " + ex.getMessage());
	} catch (SQLException ex) {
	    log.error("SQLException: " + ex.getMessage());
	} catch (NamingException ex) {
	    log.error("NamingException: " + ex.getMessage());
	} finally {
	    response.sendRedirect(url);
	    out.close();
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
