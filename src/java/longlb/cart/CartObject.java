/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longlb.tblproducts.TblProductsDAO;
import longlb.tblproducts.TblProductsDTO;

/**
 *
 * @author Long Le
 */
public class CartObject implements Serializable{
    private List<CartItem> itemList;
    private int totalItems;

    public void addCakeToCart(int cakeID) throws SQLException, NamingException{
	//1. Check item list
	if (itemList == null){
	    itemList = new ArrayList();
	}//end if itemList is not exist
	
	//2. Check existed item in itemList
	int quantity = 1;
	
	CartItem cartItem = searchCakeInCart(cakeID);
	
	if (cartItem == null) {
	    TblProductsDAO productsDAO = new TblProductsDAO();
	    TblProductsDTO cake = productsDAO.getCake(cakeID);
	    CartItem newItem = new CartItem(cake, quantity);
	    itemList.add(newItem);
	} else {
	    quantity = cartItem.getQuantity() + 1;
	    cartItem.setQuantity(quantity);
	    cartItem.updateTotal();
	}
    }
    
    public void removeCake(int cakeID){
	if (itemList == null){
	    return;
	}//end if item list not existed
	
	CartItem cartItem = searchCakeInCart(cakeID);
	if (cartItem != null){
	    itemList.remove(cartItem);
	    
	    if (itemList.isEmpty()){
		itemList = null; //remove cart if no item
	    }
	}
    }
    
    public CartItem searchCakeInCart(int cakeID){
	for (CartItem cartItem : itemList) {
	    if (cartItem.getCake().getProductID() == cakeID){
		return cartItem;
	    }
	}
	return null;
    }
    
    public double getTotalPriceOfCart(){
	double totalPrice = 0;
	for (CartItem cartItem : itemList) {
	    totalPrice += cartItem.getTotal();
	}
	return totalPrice;
    }
    
    public void updateQuantity(int quantity, int cakeID){
	CartItem updateItem = searchCakeInCart(cakeID);
	if (updateItem != null){
	    updateItem.setQuantity(quantity);
	    updateItem.updateTotal();
	}
    }
    
    public void reloadItemInfo() throws SQLException, NamingException{
	for (CartItem cartItem : itemList) {
	    int cakeID = cartItem.getCake().getProductID();
	    TblProductsDAO dao = new TblProductsDAO();
	    TblProductsDTO cake = dao.getCake(cakeID);
	    cartItem.setCake(cake);
	}
    }

    public List<CartItem> getItemList() {
	return itemList;
    }

    public int getTotalItems() {
	totalItems = itemList.size();
	return totalItems;
    } 
}
