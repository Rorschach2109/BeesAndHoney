/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class CreditCardModel {
    
    private SimpleStringProperty alias;
    private SimpleStringProperty creditCardName;
    private SimpleDoubleProperty creditCardLimit;
    private SimpleDoubleProperty availableFunds;
    private SimpleStringProperty currency;
    private final String accountNumber;
    
    public CreditCardModel() {
        this("", "", 0.0, 0.0, "", "");
    }
    
    public CreditCardModel(String alias, String creditCardName, double creditCardLimit,
            double availableFunds, String accountNumber, String currency) {
        this.alias = new SimpleStringProperty(alias);
        this.creditCardName = new SimpleStringProperty(creditCardName);
        this.creditCardLimit = new SimpleDoubleProperty(creditCardLimit);
        this.availableFunds = new SimpleDoubleProperty(availableFunds);
        this.currency = new SimpleStringProperty(currency);
        
        this.accountNumber = accountNumber;
    }

    public String getAlias() {
        return this.alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getCreditCardName() {
        return this.creditCardName.get();
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName.set(creditCardName);
    }

    public double getCreditCardLimit() {
        return this.creditCardLimit.get();
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit.set(creditCardLimit);
    }

    public double getAvailableFunds() {
        return this.availableFunds.get();
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds.set(availableFunds);
    }

    public String getCurrency() {
        return currency.get();
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
}
