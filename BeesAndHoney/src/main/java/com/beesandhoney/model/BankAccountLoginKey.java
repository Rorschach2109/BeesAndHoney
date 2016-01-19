/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javax.persistence.Embeddable;

@Embeddable
public class BankAccountLoginKey implements java.io.Serializable {
    
    private String clientId;
    private String bankAccountLoginAlias;

    public BankAccountLoginKey() {
        this("", "");
    }

    public BankAccountLoginKey(String clientId, String bankAccountLoginAlias) {
        this.clientId = clientId;
        this.bankAccountLoginAlias = bankAccountLoginAlias;
    }
    
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBankAccountLoginAlias() {
        return bankAccountLoginAlias;
    }

    public void setBankAccountLoginAlias(String bankAccountLoginAlias) {
        this.bankAccountLoginAlias = bankAccountLoginAlias;
    }
    
    
}
