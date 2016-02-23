/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

public final class ModelFactory {
    
    private ModelFactory() {
        
    }
    
    public static Bank createBankModel(String bankName, String bankUrl) {
        
        return new Bank(bankName, bankUrl);
    }
    
    public static BankAccount createBankAccountModel(String accountNumber,
            String accountName, double availableSources, 
            double accoutnBalance, String currency) {
        
        return new BankAccount(
                accountNumber, accountName, availableSources, accoutnBalance, currency
        );
    }
    
    public static BankAccountLogin createBankAccountLoginModel(String clientId,
            String loginPassword, String bankAccountLoginAlias) {
        
        return new BankAccountLogin(
                clientId, loginPassword, bankAccountLoginAlias
        );
    }
    
    public static BankAccountOwner createBankAccountOwnerModel(String ownerNameSurname, 
            String ownerAddress) {
        
        return new BankAccountOwner(ownerNameSurname, ownerAddress);
    }
    
    public static BankingBookModel createBankingBookModel(String alias, 
            String bankName, String accountNumber) {
        
        return new BankingBookModel(alias, bankName, accountNumber);
    }
    
    public static AccountModel createAccountModel(String alias, String accountName, 
            double availableSources, String accountNumber, String currency) {
        return new AccountModel(alias, accountName, availableSources, accountNumber, currency);
    }
    
    public static BeesAndHoneyUser createBeesAndHoneyUserModel(String userName, 
            String userNamePassword) {
        
        return new BeesAndHoneyUser(
                userName, userNamePassword
        );
    }
}
