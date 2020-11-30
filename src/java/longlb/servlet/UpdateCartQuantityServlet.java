/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longlb.cart.CartObject;
import longlb.cart.CartUpdateQuantityError;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "UpdateCartQuantityServlet", urlPatterns = {"/UpdateCartQuantityServlet"})
public class UpdateCartQuantityServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(UpdateCartQuantityServlet.class.getName());
    private final String VIEW_CART = "view-cart";
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
	String quantityString = request.getParameter("txtQuantity");
	String cakeIDString = request.getParameter("cakeID");
	String url = VIEW_CART;
		
	CartUpdateQuantityError error = new CartUpdateQuantityError();
	boolean isError = false;
	
	try {
	    int quantity = 0;
	    if (quantityString != null){
		quantity = Integer.parseInt(quantityString);
	    }
	    if (quantity <= 0){
		error.setQuantityError("Quantity must be a positive number");
		isError = true;
	    }
	    
	    int cakeID = 0;
	    if (cakeIDString != null){
		cakeID = Integer.parseInt(cakeIDString);
	    }
	    
	    if (isError){
		request.setAttribute("UPDATE_QUANTITY_ERROR", error);
	    } else {
		HttpSession session = request.getSession(false);
		if (session != null){
		    CartObject cart = (CartObject) session.getAttribute("CART");
		    if (cart != null){
			cart.updateQuantity(quantity, cakeID);
			session.setAttribute("CART", cart);
		    }
		}
	    }
	} catch (NumberFormatException ex){
	    log.error("NumberFormatException: " + ex.getMessage());
	    error.setQuantityError("Quantity must be a positive number");
	    request.setAttribute("UPDATE_QUANTITY_ERROR", error);
	}	
	finally {
	    Map<String, String> functionMap = (Map<String, String>) getServletContext().getAttribute("FUNCTIONS_MAP");
	    url = functionMap.get(url);
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
