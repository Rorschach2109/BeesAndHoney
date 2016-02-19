/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BankAccountOwner implements java.io.Serializable {
    
    @Id
    @Column(columnDefinition = "varchar(60) COLLATE utf8_bin")
    private String ownerNameSurname;
    @Column(columnDefinition = "varchar(60) COLLATE utf8_bin")
    private String ownerAddress;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccountOwner")
    private Set<BankAccount> bankAccounts;

    public BankAccountOwner() {
        this("", "");
    }

    public BankAccountOwner(String ownerNameSurname, 
            String ownerAddress) {
        this.ownerNameSurname = ownerNameSurname;
        this.ownerAddress = ownerAddress;
        this.bankAccounts = new HashSet<>();
    }

    public String getOwnerNameSurname() {
        return ownerNameSurname;
    }

    public void setOwnerNameSurname(String ownerNameSurname) {
        this.ownerNameSurname = ownerNameSurname;
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
