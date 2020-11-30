/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblproducts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longlb.cake.CakeItem;
import longlb.utils.DBHelpers;

/**
 *
 * @author Long Le
 */
public class TblProductsDAO implements Serializable {

    private List<CakeItem> cakeList;
    private final String ACTIVE_STATUS = "A";

    public List<CakeItem> getCakeList() {
	return cakeList;
    }
    
    public int getTotalResult(String searchValue, String priceRange, String categoryID, String role) throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	boolean isAdmin = false;
	
	try {
	    if (role.equals("ADMIN")){
		isAdmin = true;
	    }
	    
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT COUNT(productID) AS total "
			+ "FROM tblProducts "
			+ "WHERE quantity > ? ";

		//checking search value to create sql string
		if (!searchValue.trim().isEmpty()) {
		    sql += " AND name LIKE '%" + searchValue + "%'";
		}
		
		//checking price range to create sql string
		if (!priceRange.trim().isEmpty()) {
		    if (priceRange.contains("-")){
			String[] splitStrings = priceRange.trim().split("-");
			double min = Double.parseDouble(splitStrings[0]);
			double max = Double.parseDouble(splitStrings[1]);
			sql += " AND (price BETWEEN " + min + " AND " + max + ")";
		    } else {
			priceRange = priceRange.replace(">", "");
			sql += " AND price > " + priceRange;
		    }
		}
		
		//checking categoryID to create sql string
		if (!categoryID.trim().isEmpty()) {
		    sql += " AND categoryID='" + categoryID + "'";
		}

		//checking role to create status query zone
		if (!isAdmin){
		    sql += "AND statusID='" + ACTIVE_STATUS + "'";
		}
		
		//checking role to create date query zone
		if (!isAdmin){
		   sql += " AND expirationDate >= GETDATE()";
		}

		stm = con.prepareStatement(sql);
		if (isAdmin){
		    stm.setInt(1, -1);
		} else {
		    stm.setInt(1, 0);
		}

		//execute query
		rs = stm.executeQuery();
		if (rs.next()) {
		    return rs.getInt("total");
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
	return 0;
    }
    
    public int getTotalResult(String role) throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	boolean isAdmin = false;
	
	try {
	    if (role.equals("ADMIN")){
		isAdmin = true;
	    }
	    
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT COUNT(productID) AS total "
			+ "FROM tblProducts "
			+ "WHERE quantity > ? ";

		//checking role to create status query zone
		if (!isAdmin){
		    sql += " AND statusID='" + ACTIVE_STATUS + "'";
		}
		
		//checking role to create date query zone
		if (!isAdmin){
		    sql += " AND expirationDate >= GETDATE()";
		}


		stm = con.prepareStatement(sql);
		if (isAdmin){
		    stm.setInt(1, -1);
		} else {
		    stm.setInt(1, 0);
		}

		//execute query
		rs = stm.executeQuery();
		if (rs.next()) {
		    return rs.getInt("total");
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
	return 0;
    }
    
    public void searchCakes(String searchValue, String priceRange, String categoryID, String role, int page) throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	boolean isAdmin = false;
	
	try {
	    if (role.equals("ADMIN")){
		isAdmin = true;
	    }
	    
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT productID, name, image, price, description, createDate, expirationDate, quantity, tblProducts.categoryID, statusID, categoryName "
			+ "FROM tblProducts, tblCategory "
			+ "WHERE tblProducts.categoryID = tblCategory.categoryID "
			+ "AND quantity > ? ";

		//checking search value to create sql string
		if (!searchValue.trim().isEmpty()) {
		    sql += " AND name LIKE '%" + searchValue + "%'";
		}
		
		//checking price range to create sql string
		if (!priceRange.trim().isEmpty()) {
		    if (priceRange.contains("-")){
			String[] splitStrings = priceRange.trim().split("-");
			double min = Double.parseDouble(splitStrings[0]);
			double max = Double.parseDouble(splitStrings[1]);
			sql += " AND (price BETWEEN " + min + " AND " + max + ")";
		    } else {
			priceRange = priceRange.replace(">", "");
			sql += " AND price > " + priceRange;
		    }
		}
		
		//checking categoryID to create sql string
		if (!categoryID.trim().isEmpty()) {
		    sql += " AND tblProducts.categoryID='" + categoryID + "'";
		}

		//checking role to create status query zone
		if (!isAdmin){
		    sql += " AND statusID='" + ACTIVE_STATUS + "'";
		}
		
		//checking role to create date query zone
		if (!isAdmin){
		    sql += " AND expirationDate >= GETDATE()";
		}

		sql += " GROUP BY productID, name, image, price, description, "
			+ "createDate, expirationDate, quantity, "
			+ "tblProducts.categoryID, statusID, categoryName "
			+ "ORDER BY createDate DESC "
			+ "OFFSET ? ROWS "
			+ "FETCH NEXT 20 ROWS ONLY";

		stm = con.prepareStatement(sql);
		if (isAdmin){
		    stm.setInt(1, -1);
		} else {
		    stm.setInt(1, 0);
		}
		stm.setInt(2, (page - 1) * 20);

		//execute query
		rs = stm.executeQuery();
		while (rs.next()) {
		    int productID = rs.getInt("productID");
		    String name = rs.getString("name");
		    String image = rs.getString("image");
		    String description = rs.getString("description");
		    double price = rs.getDouble("price");
		    Date createDate = rs.getDate("createDate");
		    Date expirationDate = rs.getDate("expirationDate");
		    int quantity = rs.getInt("quantity");
		    String statusID = rs.getString("statusID");
		    String categoryName = rs.getString("categoryName");
		    categoryID = rs.getString("categoryID");

		    TblProductsDTO dto = new TblProductsDTO(productID, name, image, price, description, createDate, expirationDate, quantity, categoryID, statusID);
		    CakeItem cake = new CakeItem(dto, categoryName);
		    if (cakeList == null) {
			cakeList = new ArrayList<>();
		    }
		    cakeList.add(cake);
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
    }
    
    public void getAllCake(String role) throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	boolean isAdmin = false;
	
	try {
	    if (role.equals("ADMIN")){
		isAdmin = true;
	    }
	    
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT productID, name, image, price, description, createDate, expirationDate, quantity, tblProducts.categoryID, statusID, categoryName "
			+ "FROM tblProducts, tblCategory "
			+ "WHERE tblProducts.categoryID = tblCategory.categoryID "
			+ "AND quantity > ? ";

		//checking role to create status query zone
		if (!isAdmin){
		    sql += " AND statusID='" + ACTIVE_STATUS + "'";
		}
		
		//checking role to create date query zone
		if (!isAdmin){
		    sql += " AND expirationDate >= GETDATE()";
		}

		sql += " GROUP BY productID, name, image, price, description, "
			+ "createDate, expirationDate, quantity, "
			+ "tblProducts.categoryID, statusID, categoryName "
			+ "ORDER BY createDate DESC "
			+ "OFFSET 0 ROWS "
			+ "FETCH NEXT 20 ROWS ONLY";

		stm = con.prepareStatement(sql);
		if (isAdmin){
		    stm.setInt(1, -1);
		} else {
		    stm.setInt(1, 0);
		}

		//execute query
		rs = stm.executeQuery();
		while (rs.next()) {
		    int productID = rs.getInt("productID");
		    String name = rs.getString("name");
		    String image = rs.getString("image");
		    String description = rs.getString("description");
		    double price = rs.getDouble("price");
		    Date createDate = rs.getDate("createDate");
		    Date expirationDate = rs.getDate("expirationDate");
		    int quantity = rs.getInt("quantity");
		    String statusID = rs.getString("statusID");
		    String categoryName = rs.getString("categoryName");
		    String categoryID = rs.getString("categoryID");

		    TblProductsDTO dto = new TblProductsDTO(productID, name, image, price, description, createDate, expirationDate, quantity, categoryID, statusID);
		    CakeItem cake = new CakeItem(dto, categoryName);
		    if (cakeList == null) {
			cakeList = new ArrayList<>();
		    }
		    cakeList.add(cake);
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
    }
    
    public void createCake(String name, String description, String imageLink, 
			    double price, Date createdDate, Date expirationDate, 
			    int quantity, String categoryID) 
			    throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "INSERT INTO tblProducts(name, description, image, price, createDate, expirationDate, quantity, categoryID, statusID) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, description);
		stm.setString(3, imageLink);
		stm.setDouble(4, price);
		stm.setDate(5, createdDate);
		stm.setDate(6, expirationDate);
		stm.setInt(7, quantity);
		stm.setString(8, categoryID);
		stm.setString(9, ACTIVE_STATUS);
		
		stm.executeUpdate();
	    }
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
    }
    
    public int updateCake(int productID, String name, String description, String imageLink, 
			    double price, Date createdDate, Date expirationDate, 
			    int quantity, String categoryID, String statusID)
			    throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "UPDATE tblProducts "
			+ "SET name=?, description=?, image=?, price=?, createDate=?, "
			+ "expirationDate=?, quantity=?, categoryID=?, statusID=? "
			+ "WHERE productID=?";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, description);
		stm.setString(3, imageLink);
		stm.setDouble(4, price);
		stm.setDate(5, createdDate);
		stm.setDate(6, expirationDate);
		stm.setInt(7, quantity);
		stm.setString(8, categoryID);
		stm.setString(9, statusID);
		stm.setInt(10, productID);
		
		int row = stm.executeUpdate();
		if (row > 0){
		    return row;
		}
	    }
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
	
	return 0;
    }
    
    public TblProductsDTO getCake(int productID) throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT name, image, price, description, createDate, expirationDate, quantity, categoryID, statusID "
			+ "FROM tblProducts "
			+ "WHERE productID=? ";

		stm = con.prepareStatement(sql);
		stm.setInt(1, productID);

		//execute query
		rs = stm.executeQuery();
		if (rs.next()) {
		    String name = rs.getString("name");
		    String image = rs.getString("image");
		    String description = rs.getString("description");
		    double price = rs.getDouble("price");
		    Date createDate = rs.getDate("createDate");
		    Date expirationDate = rs.getDate("expirationDate");
		    int quantity = rs.getInt("quantity");
		    String statusID = rs.getString("statusID");
		    String categoryID = rs.getString("categoryID");

		    TblProductsDTO dto = new TblProductsDTO(productID, name, image, price, description, createDate, expirationDate, quantity, categoryID, statusID);
		    return dto;
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
	return null;
    }
    
    public boolean checkAvailableCake(int productID) throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "SELECT name "
			+ "FROM tblProducts "
			+ "WHERE productID=? "
			+ "AND expirationDate > GETDATE() "
			+ "AND quantity > ? "
			+ "AND statusID=?";

		stm = con.prepareStatement(sql);
		stm.setInt(1, productID);
		stm.setInt(2, 0);
		stm.setString(3, ACTIVE_STATUS);

		//execute query
		rs = stm.executeQuery();
		if (rs.next()) {
		    return true;
		}
	    }
	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
	return false;
    }
    
    public void updateQuantity(int productID, int quantity) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "UPDATE tblProducts SET quantity = ? "
			+ "WHERE productID = ?";
		
		stm = con.prepareStatement(sql);
		stm.setInt(1, quantity);
		stm.setInt(2, productID);
		
		stm.executeUpdate();
	    }
	} finally {
	    if (stm != null) {
		stm.close();
	    }
	    if (con != null) {
		con.close();
	    }
	}
    }  
}