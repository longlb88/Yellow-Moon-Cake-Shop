/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import longlb.tblproducts.TblProductsCreateUpdateCakeError;
import longlb.tblproducts.TblProductsDAO;
import longlb.utils.DateHandler;
import longlb.utils.ImageHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "CreateCakeServlet", urlPatterns = {"/CreateCakeServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 2MB
       maxFileSize = 1024 * 1024 * 10) // 10MB
public class CreateCakeServlet extends HttpServlet {
    private final String MANAGE_CAKE_PAGE = "manage-cake";
    private final Logger log = Logger.getLogger(CreateCakeServlet.class.getName());
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
	String name = request.getParameter("txtName");
	String description = request.getParameter("txtDescription");
	String createdDateString = request.getParameter("txtCreatedDate");
	String expirationDateString = request.getParameter("txtExpiration");
	String category = request.getParameter("cmbCategory");
	String priceString = request.getParameter("txtPrice");	
	String quantityString = request.getParameter("txtQuantity");
	Part imagePart = request.getPart("image");
	int quantity = 0;
	double price = 0;

	TblProductsCreateUpdateCakeError errors = new TblProductsCreateUpdateCakeError();
	boolean isError = false;

	try {
	    //check name error
	    if (name.length() <= 0 || name.length() >= 50){
		errors.setNameLengthError("Name must not empty and less than 50 chars");
		isError = true;
	    }
	    
	    //check description error
	    if (description.trim().isEmpty() || description.length() > 50){
		errors.setDescriptionLengthError("Description must not empty and less than 200 chars");
		isError = true;
	    }
	    
	    //check input create date
	    if (createdDateString.trim().isEmpty() || !DateHandler.isValid(createdDateString)){
		errors.setCreatedDateFormatError("Must follow the format yyyy-MM-dd");
		isError = true;
	    }
	    
	    //check input expiration date
	    if (expirationDateString.trim().isEmpty() || !DateHandler.isValid(expirationDateString)){
		errors.setExpirationDateFormatError("Must follow the format yyyy-MM-dd");
		isError = true;
	    }
	    
	    //check price
	    try {
		price = Double.parseDouble(priceString);	
		if (price <= 0){
		    errors.setPriceError("Price must be positive");
		    isError = true;
		}
	    } catch (NumberFormatException ex){
		log.error("NumberFormatException (Price): " + ex.getMessage());
		errors.setPriceError("Price must be positive");
		isError = true;
	    }
	    
	    //check double
	    try {
		quantity = Integer.parseInt(quantityString);	
		if (quantity < 0){
		    errors.setQuantityError("Quantity must be positive");
		    isError = true;
		}
	    } catch (NumberFormatException ex){
		log.error("NumberFormatException (Quantity): " + ex.getMessage());
		errors.setQuantityError("Quantity must be positive");
		isError = true;
	    }
	    
	    //if error occur
	    if (isError){
		request.setAttribute("CREATE_CAKE_ERRORS", errors);
	    } else {
		//if no error occur
		Date createdDate = DateHandler.createDateObject(createdDateString);
		Date expirationDate = DateHandler.createDateObject(expirationDateString);
		
		if (expirationDate.before(createdDate)){
		    errors.setExpirationDateError("Expiration date must after create date");
		    request.setAttribute("CREATE_CAKE_ERRORS", errors);
		} else {
		    //get image storage
		    String imageStorage = getServletContext().getInitParameter("ImageStorage");
		    String imageLink = ImageHelper.writeToDisk(imagePart, imageStorage);
		    
		    TblProductsDAO dao = new TblProductsDAO();
		    dao.createCake(name, description, imageLink, price, createdDate, expirationDate, quantity, category);
		}
	    }
	}
	catch (NamingException ex) {
	    log.error("NamingException: " + ex.getMessage());
	} 
	catch (SQLException ex) {
	    log.error("SQLException: " + ex.getMessage());
	} 
	finally {
	    Map<String, String> functionMap = (Map<String, String>) getServletContext().getAttribute("FUNCTIONS_MAP");
	    String url = functionMap.get(MANAGE_CAKE_PAGE);
	    RequestDispatcher rd = request.getRequestDispatcher(url);
	    rd.forward(request, response);
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
