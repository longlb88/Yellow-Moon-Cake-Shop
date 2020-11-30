/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblproducts;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Long Le
 */
public class TblProductsDTO implements Serializable{
    private int productID;
    private String name;
    private String image;
    private double price;
    private String description;
    private Date createdDate;
    private Date expirationDate;
    private int quantity;
    private String categoryID;
    private String statusID;

    public TblProductsDTO() {
    }

    public TblProductsDTO(int productID, String name, String image, double price, String description, Date createdDate, Date expirationDate, int quantity, String categoryID, String statusID) {
	this.productID = productID;
	this.name = name;
	this.image = image;
	this.price = price;
	this.description = description;
	this.createdDate = createdDate;
	this.expirationDate = expirationDate;
	this.quantity = quantity;
	this.categoryID = categoryID;
	this.statusID = statusID;
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

    /**
     * @return the image
     */
    public String getImage() {
	return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
	this.image = image;
    }

    /**
     * @return the price
     */
    public double getPrice() {
	return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
	this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
	return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
	return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
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
     * @return the statusID
     */
    public String getStatusID() {
	return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(String statusID) {
	this.statusID = statusID;
    }
    
    
    
}
