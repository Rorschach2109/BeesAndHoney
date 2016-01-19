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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BankAccountLogin implements java.io.Serializable {
    
    @Id
    private BankAccountLoginKey bankAccountLoginKey;
    private String loginPassword;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Bank bank;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccountLogin")
    private Set<BankAccount> bankAccounts;
    
    public BankAccountLogin() {
        this("", "", "");
    }
    
    public BankAccountLogin(String clientId, String loginPassword,
            String bankAccountLoginAlias) {
        this.bankAccountLoginKey = new BankAccountLoginKey(clientId, 
                bankAccountLoginAlias);
        this.loginPassword = loginPassword;
        this.bank = null;
        this.bankAccounts = null;
    }
    
    public BankAccountLoginKey getBankAccountLoginKey() {
        return bankAccountLoginKey;
    }
    
    public void setBankAccountLoginKey(BankAccountLoginKey bankAccountLoginKey) {
        this.bankAccountLoginKey = bankAccountLoginKey;
    }
    
    public String getLoginPassword() {
        return loginPassword;
    }
    
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    public Bank getBank() {
        return bank;
    }
    
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    
    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
