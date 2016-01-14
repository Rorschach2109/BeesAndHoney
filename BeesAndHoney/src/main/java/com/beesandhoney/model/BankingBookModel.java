/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleStringProperty;

public class BankingBookModel {
    private SimpleStringProperty alias;
    private SimpleStringProperty balance;
    private SimpleStringProperty availableFunds;

    public BankingBookModel() {
        this("", "", "");
    }
    
    public BankingBookModel(String alias, String balance, String availableFunds) {
        this.alias = new SimpleStringProperty(alias);
        this.balance = new SimpleStringProperty(balance);
        this.availableFunds = new SimpleStringProperty(availableFunds);
    }
    
    public String getAlias() {
        return alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getBalance() {
        return balance.get();
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public String getAvailableFunds() {
        return availableFunds.get();
    }

    public void setAvailableFunds(String availableFunds) {
        this.availableFunds.set(availableFunds);
    }
    
}
