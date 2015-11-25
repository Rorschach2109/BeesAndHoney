/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.datamanager;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author azazello
 */
public final class DataManagerFactory {
    public static ArrayList<AccountState> CreateAccountStateList(
            HashMap<String, ArrayList<String>> accountStateMap) {
        
        ArrayList<AccountState> accountStateList = new ArrayList<>();
        
        accountStateMap.keySet().forEach((accountNumber) -> {
            ArrayList<String> accountStateStringList = 
                    accountStateMap.get(accountNumber);
            
            AccountState accountState = new AccountState(
                    accountNumber, 
                    accountStateStringList.get(0), 
                    accountStateStringList.get(1),
                    accountStateStringList.get(2)
            );
            
            accountStateList.add(accountState);
        });
        
        return accountStateList;
    }
}
