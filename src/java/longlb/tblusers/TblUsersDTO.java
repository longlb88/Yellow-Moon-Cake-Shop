/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templatess
 * and open the template in the editor.
 */
package longlb.tblusers;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblUsersDTO implements Serializable{
    private String userID;
    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String statusID;
    private String roleID;

    public TblUsersDTO() {
    }

    public TblUsersDTO(String userID, String name, String password, String address, String phoneNumber, String statusID, String roleID) {
	this.userID = userID;
	this.name = name;
	this.password = password;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.statusID = statusID;
	this.roleID = roleID;
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
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
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

    /**
     * @return the roleID
     */
    public String getRoleID() {
	return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(String roleID) {
	this.roleID = roleID;
    }
    
    
    
}
