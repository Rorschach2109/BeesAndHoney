/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleStringProperty;

public class AccountModel {
    
    private SimpleStringProperty alias;
    private SimpleStringProperty accountName;
    private SimpleStringProperty availableFunds;
    
    public AccountModel() {
        this("", "", "");
    }
    
    public AccountModel(String alias, String accountName, String availableFunds) {
        this.alias.set(alias);
        this.accountName.set(accountName);
        this.availableFunds.set(availableFunds);
    }

    public String getAlias() {
        return alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public String getAvailableFunds() {
        return availableFunds.get();
    }

    public void setAvailableFunds(String availableFunds) {
        this.availableFunds.set(availableFunds);
    }
    
    
}
