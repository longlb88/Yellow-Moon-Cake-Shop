/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tbllog;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import longlb.utils.DBHelpers;

/**
 *
 * @author Long Le
 */
public class TblLogDAO implements Serializable{
    public void createLog(String userID, int productID) 
			    throws NamingException, SQLException {
	Connection con = null;
	PreparedStatement stm = null;

	try {
	    con = DBHelpers.makeConnection();
	    if (con != null) {
		String sql = "INSERT INTO tblLog(userID, productID) "
			+ "VALUES(?, ?)";
		
		stm = con.prepareStatement(sql);
		stm.setString(1, userID);
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
