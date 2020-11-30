/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cart;

import java.io.Serializable;
import longlb.tblproducts.TblProductsDTO;

/**
 *
 * @author Long Le
 */
public class CartItem implements Serializable{
    private TblProductsDTO cake;
    private int quantity;
    private double total;

    public CartItem() {
    }
    
    public CartItem(TblProductsDTO cake, int quantity) {
	this.cake = cake;
	this.quantity = quantity;
	this.total = cake.getPrice() * quantity;
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
     * @return the quantity
     */
    public int getQuantity() {
	return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public double getTotal() {
	return total;
    }
    
    public void updateTotal(){
	this.total = cake.getPrice() * quantity;
    }
    
}
