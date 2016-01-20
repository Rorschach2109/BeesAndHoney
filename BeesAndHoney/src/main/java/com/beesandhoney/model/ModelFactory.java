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
    
    public static BankAccount createBankAccountModel(int accountNumber,
            String accountName, double availableSources, 
            double accoutnBalance) {
        
        return new BankAccount(
                accountNumber, accountName, availableSources, accoutnBalance
        );
    }
    
    public static BankAccountLogin createBankAccountLogin(String clientId,
            String loginPassword, String bankAccountLoginAlias) {
        
        return new BankAccountLogin(
                clientId, loginPassword, bankAccountLoginAlias
        );
    }
    
    public static BankAccountOwner createBankAccountOwnerModel(String ownerName,
            String ownerSurname, String ownerAddress) {
        
        return new BankAccountOwner(
                new BankAccountOwnerKey(ownerName, ownerSurname), ownerAddress
        );
    }
    
    public static BankingBookModel createBankingBookModel(String alias, 
            String bankName, String accountNumber) {
        
        return new BankingBookModel(alias, bankName, accountNumber);
    }
    
    public static BeesAndHoneyUser createBeesAndHoneyUser(String userName, 
            String userNamePassword) {
        
        return new BeesAndHoneyUser(
                userName, userNamePassword
        );
    }
}
