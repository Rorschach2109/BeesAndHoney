/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Type;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"userName"}
))
public class BeesAndHoneyUser implements java.io.Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    
    private String userName;
    
    @Type(type = "encryptedPassword")
    private String userNamePassword;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beesAndHoneyUser")
    private List<BankAccountLogin> bankAccountLogins;
    
    public BeesAndHoneyUser() {
        this("", "");
    }
    
    public BeesAndHoneyUser(String userName, String userNamePassword) {
        this.userName = userName;
        this.userNamePassword = userNamePassword;
        this.accessDate = new Date();
        this.bankAccountLogins = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<BankAccountLogin> getBankAccountLogins() {
        return bankAccountLogins;
    }

    public void setBankAccountLogins(List<BankAccountLogin> bankAccountLogins) {
        this.bankAccountLogins = bankAccountLogins;
    }
}
