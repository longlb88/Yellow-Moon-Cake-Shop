/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import longlb.cake.CakeItem;
import longlb.tblproducts.TblProductsDAO;
import longlb.tblusers.TblUsersDTO;
import longlb.utils.PageCalculator;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "SearchCakeServlet", urlPatterns = {"/SearchCakeServlet"})
public class SearchCakeServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(SearchCakeServlet.class.getName());
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
	String searchValue = request.getParameter("txtSearch");
	String priceRange = request.getParameter("cmbPrice");
	String category = request.getParameter("cmbCategory");
	int page = 1; //default page is 1
	String pageString = request.getParameter("page");	
	String role = "GUEST";
	
	try {
	    if (pageString != null){
		page = Integer.parseInt(pageString);
	    }
	    HttpSession session = request.getSession(false);   
	    if (session != null) {
		TblUsersDTO curUser = (TblUsersDTO) session.getAttribute("LOGIN_USER");
		if (curUser != null){
		    role = curUser.getRoleID();
		}
	    }
	    doSearch(request, searchValue, priceRange, category, page, role);
	    
	} catch (NumberFormatException ex) {
	    log.error("NumberFormatException: " + ex.getMessage());
	} catch (NamingException ex) {
	    log.error("NamingException: " + ex.getMessage());
	} catch (SQLException ex) {
	    log.error("SQLException: " + ex.getMessage());
	} finally {
	    String destination = request.getParameter("destination"); //to redirect because search bar used in 2 different pages
	    //block member or guest to access manage cake page
	    if (destination.equals("manage-cake") && !role.equals("ADMIN")){
		destination = "home";
	    }
	    
	    Map<String, String> functionMap = (Map<String, String>) getServletContext().getAttribute("FUNCTIONS_MAP");
	    destination = functionMap.get(destination);
	    RequestDispatcher rd = request.getRequestDispatcher(destination);
	    rd.forward(request, response);
	    out.close();
	}
    }
    
    private void doSearch(HttpServletRequest request, String searchValue, 
	    String priceRange, String category, int page, 
	    String role) throws NamingException, SQLException{
	TblProductsDAO dao = new TblProductsDAO();
	
	//get cakes
	dao.searchCakes(searchValue, priceRange, category, role, page);
	List<CakeItem> listCake = dao.getCakeList();
	request.setAttribute("CAKE_LIST", listCake);
	
	//calculate total pages for paging
	int totalCake = dao.getTotalResult(searchValue, priceRange, category, role);
	int totalPage = PageCalculator.calculateTotalPage(totalCake, 20);
	request.setAttribute("TOTAL_PAGES", totalPage);
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
