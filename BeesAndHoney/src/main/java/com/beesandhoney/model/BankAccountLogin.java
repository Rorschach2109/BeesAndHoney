/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Type;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"clientId", "bankAccountLoginAlias", 
            "bankAccountLogin_userId"}
))
public class BankAccountLogin implements java.io.Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bankAccountLoginId;
    
    private String clientId;
    private String bankAccountLoginAlias;
    @Type(type = "encryptedPassword")
    private String loginPassword;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Bank bank;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccountLogin")
    private Set<BankAccount> bankAccounts;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bankAccountLogin_userId")
    private BeesAndHoneyUser beesAndHoneyUser;
    
    public BankAccountLogin() {
        this("", "", "");
    }
    
    public BankAccountLogin(String clientId, String loginPassword,
            String bankAccountLoginAlias) {
        this.clientId = clientId;
        this.bankAccountLoginAlias = bankAccountLoginAlias;
        this.loginPassword = loginPassword;
        this.bankAccounts = null;
        this.bank = null;
        this.beesAndHoneyUser = null;
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

    public int getBankAccountLoginId() {
        return bankAccountLoginId;
    }

    public void setBankAccountLoginId(int bankAccountLoginId) {
        this.bankAccountLoginId = bankAccountLoginId;
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

    public BeesAndHoneyUser getBeesAndHoneyUser() {
        return beesAndHoneyUser;
    }

    public void setBeesAndHoneyUser(BeesAndHoneyUser beesAndHoneyUser) {
        this.beesAndHoneyUser = beesAndHoneyUser;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankAccountLogin)) {
            return false;
        }
        
        if (object == this) {
            return true;
        }
        
        BankAccountLogin rhs = (BankAccountLogin) object;
        
        return ((this.clientId.equals(rhs.clientId)) &&
                (this.bankAccountLoginAlias.equals(rhs.bankAccountLoginAlias)));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
                this.bankAccountLoginId,
                this.clientId,
                this.bankAccountLoginAlias
        );
    }
}
