/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cake;

import java.io.Serializable;
import longlb.tblproducts.TblProductsDTO;

/**
 *
 * @author Long Le
 */
public class CakeItem implements Serializable{
    private TblProductsDTO cake;
    private String category;

    public CakeItem() {
    }

    public CakeItem(TblProductsDTO cake, String category) {
	this.cake = cake;
	this.category = category;
    }

    /**
     * @return the cake
     */
    public TblProductsDTO getCake() {
	return cake;
    }

    /**
     * @param cake the cake to set
     */
    public void setCake(TblProductsDTO cake) {
	this.cake = cake;
    }

    /**
     * @return the category
     */
    public String getCategory() {
	return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
	this.category = category;
    }
    
    
}
