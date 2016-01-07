/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import javax.persistence.Embeddable;

@Embeddable
public class BankAccountOwnerKey implements java.io.Serializable {
    
    private String ownerName;
    private String ownerSurname;
    
    public BankAccountOwnerKey() {
        this.ownerName = "";
        this.ownerSurname = "";
    }
    
    public BankAccountOwnerKey(String ownerName, String ownerSurname) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }
}
