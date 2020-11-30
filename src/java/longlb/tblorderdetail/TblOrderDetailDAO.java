/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblorderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longlb.cart.CartItem;
import longlb.order.OrderItem;
import longlb.utils.DBHelpers;

/**
 *
 * @author Long Le
 */
public class TblOrderDetailDAO implements Serializable{
    List<OrderItem> detailList;

    public List<OrderItem> getDetailList() {
	return detailList;
    }
    
    
    public boolean createOrder(List<CartItem> itemList, String orderID) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "INSERT INTO tblOrderDetail(orderID, productID, quantity, totalPrice) "
			+ "VALUES(?,?,?,?)";
		
		stm = con.prepareStatement(sql);
		
		// Set auto-commit to false
		con.setAutoCommit(false);
		
		int totalRow = 0;
		for (CartItem cartItem : itemList) {
		    stm.setString(1, orderID);
		    stm.setInt(2, cartItem.getCake().getProductID());
		    stm.setInt(3, cartItem.getQuantity());
		    stm.setDouble(4, cartItem.getTotal());
		    
		    stm.addBatch();
		    totalRow++;
		}
		
		int[] result = stm.executeBatch();
		con.commit();
		
		if (result.length == totalRow){
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
    
    public void getDetailsByID(String orderID) throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT detailID, tblOrderDetail.productID, tblOrderDetail.quantity, totalPrice, tblProducts.name "
			+ "FROM tblOrderDetail, tblProducts "
			+ "WHERE tblOrderDetail.productID = tblProducts.productID "
			+ "AND orderID = ?";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, orderID);
		
		rs = stm.executeQuery();
		
		while (rs.next()){
		    int detailID = rs.getInt("detailID");
		    int productID = rs.getInt("productID");
		    int quantity = rs.getInt("quantity");
		    double totalPrice = rs.getDouble("totalPrice");
		    String cakeName = rs.getString("name"); 
		    TblOrderDetailDTO orderDetailDTO = new TblOrderDetailDTO(detailID, orderID, productID, quantity, totalPrice);
		    OrderItem orderItem = new OrderItem(orderDetailDTO, cakeName);
		    
		    if (detailList == null){
			detailList = new ArrayList<>();
		    }
		    detailList.add(orderItem);
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
