/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblcategory;

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
public class TblCategoryDAO implements Serializable{
    private List<TblCategoryDTO> categoryList;

    public List<TblCategoryDTO> getCategoryList() {
	return categoryList;
    }
     
    public void loadCategory() throws NamingException, SQLException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	try {
	    con = DBHelpers.makeConnection();
	    if (con != null){
		String sql = "SELECT categoryID, categoryName "
			+ "FROM tblCategory";
		
		stm = con.prepareStatement(sql);
		
		rs = stm.executeQuery();
		
		while (rs.next()){
		    String categoryID = rs.getString("categoryID");
		    String name = rs.getString("categoryName");
		    
		    TblCategoryDTO dto = new TblCategoryDTO(categoryID, name);
		    
		    if (categoryList == null){
			categoryList = new ArrayList<>();
		    }
		    categoryList.add(dto);
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
