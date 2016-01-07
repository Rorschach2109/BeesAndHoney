/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BankAccountOwner implements java.io.Serializable {
    
    @Id
    private BankAccountOwnerKey bankAccountOwnerKey;
    private String ownerAddress;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccountOwner")
    private Set<BankAccount> bankAccounts;

    public BankAccountOwner() {
        this.bankAccountOwnerKey = null;
        this.ownerAddress = "";
    }

    public BankAccountOwner(BankAccountOwnerKey bankAccountOwnerKey, 
            String ownerAddress) {
        this.bankAccountOwnerKey = bankAccountOwnerKey;
        this.ownerAddress = ownerAddress;
    }

    public BankAccountOwnerKey getBankAccountOwnerKey() {
        return bankAccountOwnerKey;
    }

    public void setBankAccountOwnerKey(BankAccountOwnerKey bankAccountOwnerKey) {
        this.bankAccountOwnerKey = bankAccountOwnerKey;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
