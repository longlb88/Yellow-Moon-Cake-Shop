/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblcategory;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblCategoryDTO implements Serializable{
    private String categoryID;
    private String name;

    public TblCategoryDTO() {
    }

    public TblCategoryDTO(String categoryID, String name) {
	this.categoryID = categoryID;
	this.name = name;
    }

    /**
     * @return the categoryID
     */
    public String getCategoryID() {
	return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(String categoryID) {
	this.categoryID = categoryID;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }
    
    
}
