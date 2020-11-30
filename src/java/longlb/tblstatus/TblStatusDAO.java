/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblstatus;

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
public class TblStatusDAO implements Serializable{
    List<TblStatusDTO> listStatus;

    public List<TblStatusDTO> getListStatus() {
	return listStatus;
    }
    
    public void loadAllStatus() throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT statusID, status "
			+ "FROM tblStatus";
		
		stm = con.prepareStatement(sql);
		
		rs = stm.executeQuery();
		
		while (rs.next()){
		    String statusID = rs.getString("statusID");
		    String status = rs.getString("status");
		    TblStatusDTO dto = new TblStatusDTO(statusID, status);
		    
		    if (listStatus == null){
			listStatus = new ArrayList<>();
		    }
		    listStatus.add(dto);	    
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
