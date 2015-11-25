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
public class AccountState {

    private String accountNumber;
    private String availableFunds;
    private String balance;
    private String currency;
    
    public AccountState() {
        this.accountNumber = "";
        this.availableFunds = "";
        this.balance = "";
        this.currency = "";
    }
    
    public AccountState(String accountNumber, String availableFunds, 
            String balance, String currency) {
        this.accountNumber = accountNumber;
        this.availableFunds = availableFunds;
        this.balance = balance;
        this.currency = currency;
    }
    
    public String GetAccountNumber() {
        return this.accountNumber;
    }
    
    public void SetAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String GetAvailableFunds() {
        return this.availableFunds;
    }
    
    public void SetAvailableFunds(String availableFunds) {
        this.availableFunds = availableFunds;
    }
    
    public String GetBalance() {
        return this.balance;
    }
    
    public void SetBalance(String balance) {
        this.balance = balance;
    }
    
    public String GetCurrency() {
        return this.currency;
    }
    
    public void SetCurrency(String currency) {
        this.currency = currency;
    }
}
