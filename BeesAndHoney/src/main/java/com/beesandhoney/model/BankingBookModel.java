/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javafx.beans.property.SimpleStringProperty;

public class BankingBookModel {
    private SimpleStringProperty clientId;
    private SimpleStringProperty alias;
    private SimpleStringProperty bankName;

    public BankingBookModel() {
        this("", "", "");
    }
    
    public BankingBookModel(String alias, String bankName, String accountNumber) {
        this.clientId = new SimpleStringProperty(accountNumber);
        this.alias = new SimpleStringProperty(alias);
        this.bankName = new SimpleStringProperty(bankName);
    }

    public String getClientId() {
        return clientId.get();
    }

    public void setClientId(String clientId) {
        this.clientId.set(clientId);
    }
    
    public String getAlias() {
        return alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getBankName() {
        return bankName.get();
    }

    public void setBankName(String balance) {
        this.bankName.set(balance);
    }    
}
