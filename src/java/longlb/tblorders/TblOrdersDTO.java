/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblorders;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Long Le
 */
public class TblOrdersDTO implements Serializable{
    private String orderID;
    private String userID;
    private String name;
    private String address;
    private String phoneNumber;
    private double total;
    private Timestamp orderDate;
    private String paymentID;
    private String paymentStatus;

    public TblOrdersDTO() {
    }

    public TblOrdersDTO(String orderID, String userID, String name, String address, String phoneNumber, double total, Timestamp orderDate, String paymentID, String paymentStatus) {
	this.orderID = orderID;
	this.userID = userID;
	this.name = name;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.total = total;
	this.orderDate = orderDate;
	this.paymentID = paymentID;
	this.paymentStatus = paymentStatus;
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
     * @return the userID
     */
    public String getUserID() {
	return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
	this.userID = userID;
    }

    /**
     * @return the address
     */
    public String getAddress() {
	return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    /**
     * @return the total
     */
    public double getTotal() {
	return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
	this.total = total;
    }

    /**
     * @return the orderDate
     */
    public Timestamp getOrderDate() {
	return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Timestamp orderDate) {
	this.orderDate = orderDate;
    }

    /**
     * @return the paymentID
     */
    public String getPaymentID() {
	return paymentID;
    }

    /**
     * @param paymentID the paymentID to set
     */
    public void setPaymentID(String paymentID) {
	this.paymentID = paymentID;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
	return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
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
