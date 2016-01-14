/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BeesAndHoneyUser implements java.io.Serializable {
    
    @Id
    private String userName;
    private String userNamePassword;
    
    @Temporal(TemporalType.DATE)
    private Date accessDate;
    
    public BeesAndHoneyUser() {
        this.userName = "";
        this.userNamePassword = "";
        this.accessDate = null;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserNamePassword() {
        return userNamePassword;
    }
    
    public void setUserNamePassword(String userNamePassword) {
        this.userNamePassword = userNamePassword;
    }
    
    public Date getAccessDate() {
        return accessDate;
    }
    
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }
}
