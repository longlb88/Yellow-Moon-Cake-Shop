/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblusers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longlb.utils.DBHelpers;

/**
 *
 * @author Long Le
 */
public class TblUsersDAO implements Serializable{
    private List<TblUsersDTO> userList;

    public List<TblUsersDTO> getUserList() {
	return userList;
    }
    
    
    public TblUsersDTO checkLogin(String ID, String password) throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    String statusID = "A";
	    //1. Make connetion
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		//2. create sql statement
		String sql = "SELECT name, address, phoneNumber, statusID, roleID "
			+ "FROM tblUsers "
			+ "WHERE userID = ? AND password = ? AND statusID = ? "
			+ "AND (roleID = 'ADMIN' OR roleID = 'MEM')";
		
		//3. Create query statement
		stm = con.prepareStatement(sql);
		stm.setString(1, ID);
		stm.setString(2, password);
		stm.setString(3, statusID);
		
		//4. query
		rs = stm.executeQuery();
		
		if (rs.next()){
		    String name = rs.getString("name");
		    String address = rs.getString("address");
		    String phoneNumber = rs.getString("phoneNumber");
		    String roleID = rs.getString("roleID");
		    
		    TblUsersDTO result = new TblUsersDTO(ID, name, password, address, phoneNumber, statusID, roleID);
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
    
    public TblUsersDTO checkLoginGoogle(String gmail) throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	
	try {
	    String roleID = "GMAIL";
	    String statusID = "A"; 
	    //1. Make connetion
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		//2. create sql statement
		String sql = "SELECT name, address, password, phoneNumber, statusID "
			+ "FROM tblUsers "
			+ "WHERE userID = ? AND statusID = ? "
			+ "AND roleID = ?";
		
		//3. Create query statement
		stm = con.prepareStatement(sql);
		stm.setString(1, gmail);
		stm.setString(2, statusID);
		stm.setString(3, roleID);
		
		//4. query
		rs = stm.executeQuery();
		
		if (rs.next()){
		    String name = rs.getString("name");
		    String address = rs.getString("address");
		    String password = rs.getString("password");
		    String phoneNumber = rs.getString("phoneNumber");
		    
		    TblUsersDTO result = new TblUsersDTO(gmail, name, password, address, phoneNumber, statusID, roleID);
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
    
    public void loadAllUser() throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    String statusID = "A";
	    //1. Make connetion
	    con = DBHelpers.makeConnection();
	    
	    if (con != null){
		//2. create sql statement
		String sql = "SELECT userID, name, address, phoneNumber, statusID, roleID "
			+ "FROM tblUsers";
		
		//3. Create query statement
		stm = con.prepareStatement(sql);
		
		//4. query
		rs = stm.executeQuery();
		
		while (rs.next()){
		    String ID = rs.getString("userID");
		    String password = "";
		    String name = rs.getString("name");
		    String address = rs.getString("address");
		    String phoneNumber = rs.getString("phoneNumber");
		    String roleID = rs.getString("roleID");
		    
		    TblUsersDTO result = new TblUsersDTO(ID, name, password, address, phoneNumber, statusID, roleID);
		    if (userList == null){
			userList = new ArrayList<>();
		    }
		    userList.add(result);
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
    }
}
