/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.tblstatus;

import java.io.Serializable;

/**
 *
 * @author Long Le
 */
public class TblStatusDTO implements Serializable{
    private String statusID;
    private String status;

    public TblStatusDTO() {
    }

    public TblStatusDTO(String statusID, String status) {
	this.statusID = statusID;
	this.status = status;
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
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }
}
