/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.datamanager;

/**
 *
 * @author azazello
 */
public class AccountInformation {
    private String accountOwnerName;
    private String accountOwnerSurname;
    private String accountAddressOwner;
    private String accountAddress;

    public AccountInformation() {
        this.accountOwnerName = "";
        this.accountOwnerSurname = "";
        this.accountAddressOwner = "";
        this.accountAddress = "";
    }

    public AccountInformation(String accountOwnerName, 
            String accountOwnerSurname, String accountAddressOwner, 
            String accountAddress) {
        this.accountOwnerName = accountOwnerName;
        this.accountOwnerSurname = accountOwnerSurname;
        this.accountAddressOwner = accountAddressOwner;
        this.accountAddress = accountAddress;
    }
    
    public String GetAccountOwnerName() {
        return this.accountOwnerName;
    }
    
    public void SetAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }
    
    public String GetAccountOwnerSurname() {
        return this.accountOwnerSurname;
    }
    
    public void SetAccountOwnerSurname(String accountOwnerSurname) {
        this.accountOwnerSurname = accountOwnerSurname;
    }
    
    public String GetAccountAddressOwner() {
        return this.accountAddressOwner;
    }
    
    public void SetAccountAddressOwner(String accountAddressOwner) {
        this.accountAddressOwner = accountAddressOwner;
    }
    
    public String GetAccountAddress() {
        return this.accountAddress;
    }
    
    public void SetAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }
    
}
