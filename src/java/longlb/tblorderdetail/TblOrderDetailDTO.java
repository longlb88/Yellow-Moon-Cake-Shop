/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblorderdetail;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblOrderDetailDTO implements Serializable {
    private int detailID;
    private String orderID;
    private int productID;
    private int quantity;
    private double totalPrice;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(int detailID, String orderID, int productID, int quantity, double totalPrice) {
	this.detailID = detailID;
	this.orderID = orderID;
	this.productID = productID;
	this.quantity = quantity;
	this.totalPrice = totalPrice;
    }

    
    /**
     * @return the detailID
     */
    public int getDetailID() {
	return detailID;
    }

    /**
     * @param detailID the detailID to set
     */
    public void setDetailID(int detailID) {
	this.detailID = detailID;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
	return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
	this.orderID = orderID;
    }

    /**
     * @return the productID
     */
    public int getProductID() {
	return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
	this.productID = productID;
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
     * @return the totalPrice
     */
    public double getTotalPrice() {
	return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
    }
    
    
    
}
