/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount implements java.io.Serializable {
    
    @Id
    private int accountNumber;
    private String accountName;
    private double availableSources;
    private double accountBalance;
    
    @ManyToOne(cascade = CascadeType.ALL)
    BankAccountLogin bankAccountLogin;
    
    @ManyToOne(cascade = CascadeType.ALL)
    BankAccountOwner bankAccountOwner;
    
    public BankAccount() {
        this(-1, "", 0.0, 0.0);
    }
    
    public BankAccount(int accountNumber, String accountName, 
            double availableSources, double accoutnBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.availableSources = availableSources;
        this.accountBalance = accoutnBalance;
        this.bankAccountLogin = null;
        this.bankAccountOwner = null;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
}
