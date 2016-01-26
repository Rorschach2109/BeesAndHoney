/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Date;
import java.util.Set;
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
    
    @Temporal(TemporalType.DATE)
    private Date accessDate;
    
    @OneToMany
    private Set<BankAccountLogin> bankAccountLogins;
    
    public BeesAndHoneyUser() {
        this("", "");
    }
    
    public BeesAndHoneyUser(String userName, String userNamePassword) {
        this.userName = userName;
        this.userNamePassword = userNamePassword;
        this.accessDate = null;
        this.bankAccountLogins = null;
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

    public Set<BankAccountLogin> getBankAccountLogins() {
        return bankAccountLogins;
    }

    public void setBankAccountLogins(Set<BankAccountLogin> bankAccountLogins) {
        this.bankAccountLogins = bankAccountLogins;
    }
}
