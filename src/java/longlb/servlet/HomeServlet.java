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
import longlb.tblcategory.TblCategoryDAO;
import longlb.tblcategory.TblCategoryDTO;
import longlb.tblproducts.TblProductsDAO;
import longlb.tblusers.TblUsersDTO;
import longlb.utils.PageCalculator;
import org.apache.log4j.Logger;

/**
 *
 * @author Long Le
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(HomeServlet.class.getName());
    private final String HOME_PAGE = "home-page";
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
	String url = HOME_PAGE;
	
	try {
	    //list all cake if haven't searched anythings
	    String searchValue = request.getParameter("txtSearch");
	    if (searchValue == null){
		HttpSession session = request.getSession(false);
		String role = "GUEST";
		
		if (session != null) {
		    TblUsersDTO curUser = (TblUsersDTO) session.getAttribute("LOGIN_USER");
		    if (curUser != null){
			role = curUser.getRoleID();
		    }
		}
		loadAllCake(request, role);
		request.setAttribute("LOAD_ALL_DEFAULT_PAGE", 1);
	    }
	    
	    //load category list
	    TblCategoryDAO categoryDAO = new TblCategoryDAO();
	    categoryDAO.loadCategory();
	    List<TblCategoryDTO> categoryList = categoryDAO.getCategoryList();
	    request.setAttribute("CATEGORY_LIST", categoryList);

	} catch (NamingException ex) {
	    log.error("NamingException: " + ex.getMessage());
	} catch (SQLException ex) {
	    log.error("SQLException: " + ex.getMessage());
	} finally {
	    Map<String, String> functionMap = (Map<String, String>) getServletContext().getAttribute("FUNCTIONS_MAP");
	    url = functionMap.get(url);
	    RequestDispatcher rd = request.getRequestDispatcher(url);
	    rd.forward(request, response);
	    out.close();
	}
    }
    
    private void loadAllCake(HttpServletRequest request, 
	    String role) throws NamingException, SQLException{
	TblProductsDAO dao = new TblProductsDAO();
	dao.getAllCake(role);
	List<CakeItem> listCake = dao.getCakeList();
	request.setAttribute("CAKE_LIST", listCake);

	int totalCake = dao.getTotalResult(role);
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
