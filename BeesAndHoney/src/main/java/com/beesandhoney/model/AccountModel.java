/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class AccountModel {
    
    private SimpleStringProperty alias;
    private SimpleStringProperty accountName;
    private SimpleDoubleProperty availableSources;
    
    public AccountModel() {
        this("", "", 0);
    }
    
    public AccountModel(String alias, String accountName, double availableSources) {
        this.alias = new SimpleStringProperty(alias);
        this.accountName = new SimpleStringProperty(accountName);
        this.availableSources = new SimpleDoubleProperty(availableSources);
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

    public double getAvailableSources() {
        return availableSources.get();
    }

    public void setAvailableSources(double availableSources) {
        this.availableSources.set(availableSources);
    }
    
    
}
