/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.order;

import java.io.Serializable;
import longlb.tblorderdetail.TblOrderDetailDTO;

/**
 *
 * @author Long Le
 */
public class OrderItem implements Serializable{
    private TblOrderDetailDTO orderDetail;
    private String cakeName;

    public OrderItem() {
    }

    public OrderItem(TblOrderDetailDTO orderDetail, String cakeName) {
	this.orderDetail = orderDetail;
	this.cakeName = cakeName;
    }

    /**
     * @return the orderDetail
     */
    public TblOrderDetailDTO getOrderDetail() {
	return orderDetail;
    }

    /**
     * @param orderDetail the orderDetail to set
     */
    public void setOrderDetail(TblOrderDetailDTO orderDetail) {
	this.orderDetail = orderDetail;
    }

    /**
     * @return the cakeName
     */
    public String getCakeName() {
	return cakeName;
    }

    /**
     * @param cakeName the cakeName to set
     */
    public void setCakeName(String cakeName) {
	this.cakeName = cakeName;
    }
    
    
}
