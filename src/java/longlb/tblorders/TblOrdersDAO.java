/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblorders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import longlb.utils.DBHelpers;

/**
 *
 * @author Long Le
 */
public class TblOrdersDAO implements Serializable{
    public boolean createOrder(String name, String address, String phoneNumber, String userID, double total, String paymentID) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "INSERT INTO tblOrders(name, address, phoneNumber, userID, total, paymentID) "
			+ "VALUES(?,?,?,?,?,?)";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, address);
		stm.setString(3, phoneNumber);
		stm.setString(4, userID);
		stm.setDouble(5, total);
		stm.setString(6, paymentID);
		
		int row = stm.executeUpdate();
		
		if (row > 0){
		    return true;
		}
	    }
	} finally {
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return false;
    }
    
    public TblOrdersDTO getLatestOrders(String name, String address, String phoneNumber, String userID, double total) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT TOP(1) orderID, orderDate, paymentID, paymentStatus "
			+ "FROM tblOrders "
			+ "WHERE name = ? "
			+ "AND address = ? "
			+ "AND phoneNumber = ? "
			+ "AND userID = ? "
			+ "AND total = ? "
			+ "ORDER BY orderDate DESC";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, address);
		stm.setString(3, phoneNumber);
		stm.setString(4, userID);
		stm.setDouble(5, total);
		
		rs = stm.executeQuery();
		
		if (rs.next()){
		    String orderID = rs.getString("orderID");
		    Timestamp orderDate = rs.getTimestamp("orderDate");
		    String paymentID = rs.getString("paymentID");
		    String paymentStatus = rs.getString("paymentStatus");
		    
		    TblOrdersDTO result = new TblOrdersDTO(orderID, userID, name, address, phoneNumber, total, orderDate, paymentID, paymentStatus);
		    return result;
		}
	    }
	} finally {
	    if (rs != null){
		rs.close();
	    }
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return null;
    }
    
    public TblOrdersDTO getOrderByID(String orderID, String userID) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT name, address, phoneNumber, total, orderDate, paymentID, paymentStatus "
			+ "FROM tblOrders "
			+ "WHERE orderID = ? AND userID = ?";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, orderID);
		stm.setString(2, userID);
		
		rs = stm.executeQuery();
		
		if (rs.next()){
		    String name = rs.getString("name");
		    String address = rs.getString("address");
		    String phoneNumber = rs.getString("phoneNumber");
		    double total = rs.getDouble("total");
		    Timestamp orderDate = rs.getTimestamp("orderDate");
		    String paymentID = rs.getString("paymentID");
		    String paymentStatus = rs.getString("paymentStatus");
		    
		    TblOrdersDTO result = new TblOrdersDTO(orderID, userID, name, address, phoneNumber, total, orderDate, paymentID, paymentStatus);
		    return result;
		}
	    }
	} finally {
	    if (rs != null){
		rs.close();
	    }
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return null;
    }
}
