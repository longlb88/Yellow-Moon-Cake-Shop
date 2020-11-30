/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cart;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class CartUpdateQuantityError implements Serializable{
    private String quantityError;

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
