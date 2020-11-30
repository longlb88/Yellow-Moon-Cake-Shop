/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.order;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class CustomerCheckoutInfo implements Serializable{
    private String name;
    private String phoneNumber;
    private String address;
    private String paymentMethod;

    public CustomerCheckoutInfo(String name, String phoneNumber, String address, String paymentMethod) {
	this.name = name;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.paymentMethod = paymentMethod;
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
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
	return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }
    
    
}
