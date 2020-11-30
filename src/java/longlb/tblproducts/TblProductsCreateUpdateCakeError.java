/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblproducts;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblProductsCreateUpdateCakeError implements Serializable{
    private String nameLengthError;
    private String priceError;
    private String descriptionLengthError;
    private String createdDateFormatError;
    private String expirationDateFormatError;
    private String expirationDateError;
    private String quantityError;

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
     * @return the priceError
     */
    public String getPriceError() {
	return priceError;
    }

    /**
     * @param priceError the priceError to set
     */
    public void setPriceError(String priceError) {
	this.priceError = priceError;
    }

    /**
     * @return the descriptionLengthError
     */
    public String getDescriptionLengthError() {
	return descriptionLengthError;
    }

    /**
     * @param descriptionLengthError the descriptionLengthError to set
     */
    public void setDescriptionLengthError(String descriptionLengthError) {
	this.descriptionLengthError = descriptionLengthError;
    }

    /**
     * @return the createdDateFormatError
     */
    public String getCreatedDateFormatError() {
	return createdDateFormatError;
    }

    /**
     * @param createdDateFormatError the createdDateFormatError to set
     */
    public void setCreatedDateFormatError(String createdDateFormatError) {
	this.createdDateFormatError = createdDateFormatError;
    }

    /**
     * @return the expirationDateFormatError
     */
    public String getExpirationDateFormatError() {
	return expirationDateFormatError;
    }

    /**
     * @param expirationDateFormatError the expirationDateFormatError to set
     */
    public void setExpirationDateFormatError(String expirationDateFormatError) {
	this.expirationDateFormatError = expirationDateFormatError;
    }

    /**
     * @return the expirationDateError
     */
    public String getExpirationDateError() {
	return expirationDateError;
    }

    /**
     * @param expirationDateError the expirationDateError to set
     */
    public void setExpirationDateError(String expirationDateError) {
	this.expirationDateError = expirationDateError;
    }

    /**
     * @return the quantityError
     */
    public String getQuantityError() {
	return quantityError;
    }

    /**
     * @param quantityError the quantityError to set
     */
    public void setQuantityError(String quantityError) {
	this.quantityError = quantityError;
    }
    
    
}
