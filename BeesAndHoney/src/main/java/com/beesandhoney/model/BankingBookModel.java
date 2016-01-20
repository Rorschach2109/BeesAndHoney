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
    private String accountLoginNumber;

    public BankingBookModel() {
        this("", "", "");
    }
    
    public BankingBookModel(String alias, String bankName, String accountNumber) {
        this.alias = new SimpleStringProperty(alias);
        this.bankName = new SimpleStringProperty(bankName);
        this.accountLoginNumber = accountNumber;
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

    public String getAccountLoginNumber() {
        return accountLoginNumber;
    }

    public void setAccountLoginNumber(String accountLoginNumber) {
        this.accountLoginNumber = accountLoginNumber;
    }
    
}
