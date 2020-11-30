/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblpaymentmethod;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblPaymentMethodDTO implements Serializable{
    private String paymentID;
    private String methodName;

    public TblPaymentMethodDTO() {
    }

    public TblPaymentMethodDTO(String paymentID, String methodName) {
	this.paymentID = paymentID;
	this.methodName = methodName;
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
     * @return the methodName
     */
    public String getMethodName() {
	return methodName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
	this.methodName = methodName;
    }

    
    
}
