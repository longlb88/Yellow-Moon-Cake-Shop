/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblpaymentmethod;

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
public class TblPaymentMethodDAO implements Serializable{
    List<TblPaymentMethodDTO> listMethod;

    public List<TblPaymentMethodDTO> getListPaymentMethod() {
	return listMethod;
    }
    
    public void loadAllPaymentMethod() throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT paymentID, methodName "
			+ "FROM tblPaymentMethod";
		
		stm = con.prepareStatement(sql);
		
		rs = stm.executeQuery();
		
		while (rs.next()){
		    String paymentID = rs.getString("paymentID");
		    String methodName = rs.getString("methodName");
		    TblPaymentMethodDTO dto = new TblPaymentMethodDTO(paymentID, methodName);
		    
		    if (listMethod == null){
			listMethod = new ArrayList<>();
		    }
		    listMethod.add(dto);	    
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
