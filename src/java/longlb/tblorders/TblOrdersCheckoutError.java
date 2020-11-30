/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblorders;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblOrdersCheckoutError implements Serializable{
    private String nameLengthError;
    private String addressLengthError;
    private String phoneNumberLengthError;
    private String phoneNumberFormarError;
    private String cakeIsNotAvailableError;
    private String buyQuantityError;
    private String notPaidError;

    /**
     * @return the nameLengthError
     */
    public String getNameLengthError() {
	return nameLengthError;
    }

    /**
     * @param nameLengthError the nameLengthError to set
     */
    public void setNameLengthError(String nameLengthError) {
	this.nameLengthError = nameLengthError;
    }

    /**
     * @return the addressLengthError
     */
    public String getAddressLengthError() {
	return addressLengthError;
    }

    /**
     * @param addressLengthError the addressLengthError to set
     */
    public void setAddressLengthError(String addressLengthError) {
	this.addressLengthError = addressLengthError;
    }

    /**
     * @return the phoneNumberLengthError
     */
    public String getPhoneNumberLengthError() {
	return phoneNumberLengthError;
    }

    /**
     * @param phoneNumberLengthError the phoneNumberLengthError to set
     */
    public void setPhoneNumberLengthError(String phoneNumberLengthError) {
	this.phoneNumberLengthError = phoneNumberLengthError;
    }

    /**
     * @return the phoneNumberFormarError
     */
    public String getPhoneNumberFormarError() {
	return phoneNumberFormarError;
    }

    /**
     * @param phoneNumberFormarError the phoneNumberFormarError to set
     */
    public void setPhoneNumberFormarError(String phoneNumberFormarError) {
	this.phoneNumberFormarError = phoneNumberFormarError;
    }

    /**
     * @return the cakeIsNotAvailableError
     */
    public String getCakeIsNotAvailableError() {
	return cakeIsNotAvailableError;
    }

    /**
     * @param cakeIsNotAvailableError the cakeIsNotAvailableError to set
     */
    public void setCakeIsNotAvailableError(String cakeIsNotAvailableError) {
	this.cakeIsNotAvailableError = cakeIsNotAvailableError;
    }

    /**
     * @return the buyQuantityError
     */
    public String getBuyQuantityError() {
	return buyQuantityError;
    }

    /**
     * @param buyQuantityError the buyQuantityError to set
     */
    public void setBuyQuantityError(String buyQuantityError) {
	this.buyQuantityError = buyQuantityError;
    }

    /**
     * @return the notPaidError
     */
    public String getNotPaidError() {
	return notPaidError;
    }

    /**
     * @param notPaidError the notPaidError to set
     */
    public void setNotPaidError(String notPaidError) {
	this.notPaidError = notPaidError;
    }
    
    
}
