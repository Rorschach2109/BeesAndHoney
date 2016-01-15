/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleStringProperty;

public class BankingBookModel {
    private SimpleStringProperty alias;
    private SimpleStringProperty bankName;
    private SimpleStringProperty availableFunds;
    private int accountNumber;

    public BankingBookModel() {
        this("", "", "", -1);
    }
    
    public BankingBookModel(String alias, String bankName, 
            String availableFunds, int accountNumber) {
        this.alias = new SimpleStringProperty(alias);
        this.bankName = new SimpleStringProperty(bankName);
        this.availableFunds = new SimpleStringProperty(availableFunds);
        this.accountNumber = accountNumber;
    }
    
    public String getAlias() {
        return alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getBalance() {
        return bankName.get();
    }

    public void setBalance(String balance) {
        this.bankName.set(balance);
    }

    public String getAvailableFunds() {
        return availableFunds.get();
    }

    public void setAvailableFunds(String availableFunds) {
        this.availableFunds.set(availableFunds);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
}
