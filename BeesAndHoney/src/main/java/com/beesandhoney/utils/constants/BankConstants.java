/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.constants;

import com.beesandhoney.model.Bank;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class BankConstants {

    public static class BankPKO {
        public static final String NAME = "PKO BP";
        public static final String URL = "https://www.ipko.pl/stare";
    }
    
    public static class BankBZWBK {
        public static final String NAME = "BZ WBK";
        public static final String URL = "https://www.bzwbk.pl";
    }
    
    public static ArrayList<Bank> bankModelList;
    public static ObservableList<String> bankNameList;

    static 
    {
        createBankModelList();
        createBankNameList();
    }
    
    private static void createBankModelList() {
        bankModelList = new ArrayList<>();
        addToBankModelList(BankPKO.class.getName());
        addToBankModelList(BankBZWBK.class.getName());
    }
    
    private static void addToBankModelList(String bankClassName) {    
        try {
            Field nameField = Class.forName(bankClassName)
                    .getDeclaredField("NAME");
                
            Field urlField = Class.forName(bankClassName)
                    .getDeclaredField("URL");
                
            bankModelList.add(new Bank(
                    (String) nameField.get(null), (String) urlField.get(null)
            ));
        } catch (ReflectiveOperationException e) {
        }
    }
    
    private static void createBankNameList() {
        bankNameList = FXCollections.observableArrayList();
        
        for (Bank bank : bankModelList) {
            bankNameList.add(bank.getBankName());
        }
    }
}
