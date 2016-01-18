/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank implements java.io.Serializable {
    
    @Id
    private String bankName;
    private String bankUrl;
    
    public Bank() {
        this("", "");
    }
    
    public Bank(String bankName, String bankUrl) {
        this.bankName = bankName;
        this.bankUrl = bankUrl;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getBankUrl() {
        return bankUrl;
    }
    
    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }
}
