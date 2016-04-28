/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.beesandhoney.utils.constants.BankAccountConstants.BankAccountType;

@Entity
public class BankAccount implements java.io.Serializable {
        
    @Id
    private String accountNumber;
    @Column(columnDefinition = "varchar(60) COLLATE utf8_bin")
    private String accountName;
    private double accountLimit;
    private double availableSources;
    private double accountBalance;
    private String currency;
    private BankAccountType bankAccountType;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private BankAccountLogin bankAccountLogin;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BankAccountOwner bankAccountOwner;
    
    public BankAccount() {
        this("", "", 0.0, 0.0, "");
    }
    
    public BankAccount(String accountNumber, String accountName,
            double availableSources, double accountBalance, String currency) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountLimit = Double.MAX_VALUE;
        this.availableSources = availableSources;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.bankAccountType = BankAccountType.NO_TYPE;
        this.bankAccountLogin = null;
        this.bankAccountOwner = null;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountLimit() {
        return accountLimit;
    }
    
    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }
    
    public double getAvailableSources() {
        return availableSources;
    }

    public void setAvailableSources(double availableSources) {
        this.availableSources = availableSources;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public BankAccountLogin getBankAccountLogin() {
        return bankAccountLogin;
    }
    
    public void setBankAccountLogin(BankAccountLogin bankAccountLogin) {
        this.bankAccountLogin = bankAccountLogin;
    }

    public BankAccountOwner getBankAccountOwner() {
        return bankAccountOwner;
    }

    public void setBankAccountOwner(BankAccountOwner bankAccountOwner) {
        this.bankAccountOwner = bankAccountOwner;
    }
    
    public BankAccountType getBankAccountType() {
        return this.bankAccountType;
    }
    
    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankAccount)) {
            return false;
        }
        
        if (object == this) {
            return true;
        }
        
        BankAccount rhs = (BankAccount) object;
        return (this.accountNumber.equals(rhs.accountNumber));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.accountNumber);
    }
}
