/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank implements java.io.Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String bankName;
    private String bankUrl;
    
    public Bank() {
        this("", "");
    }
    
    public Bank(String bankName, String bankUrl) {
        this.bankName = bankName;
        this.bankUrl = bankUrl;
    }
    
    public int getBankId() {
        return bankId;
    }
    
    public void setBankId(int bankId) {
        this.bankId = bankId;
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
