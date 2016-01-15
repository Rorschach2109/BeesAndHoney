///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.beesandhoney.model;
//
//import com.beesandhoney.model.dao.BankAccountDao;
//import com.beesandhoney.model.dao.BankAccountLoginDao;
//import com.beesandhoney.model.dao.BankDao;
//import com.beesandhoney.model.dao.GenericDaoHibernateImpl;
//import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
//import com.beesandhoney.utils.hibernate.HibernateUtil;
//import java.util.Date;
//import java.util.HashSet;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import org.hibernate.Hibernate;
//import org.jasypt.util.password.StrongPasswordEncryptor;
//
// 
//public class TestModel extends Application {
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                Bank pko = new Bank();
//                pko.setBankName("PKO BP");
//                
//                Bank bzwbk = new Bank();
//                bzwbk.setBankName("BZ WBK");
//                
//                Bank pkosa = new Bank();
//                pkosa.setBankName("PKO SA");
//                
//
//                BankAccountLogin bankLogin = new BankAccountLogin(1234, "pass");
//                bankLogin.setAccessDate(new Date());
//                bankLogin.setBank(pko);
//                
//                BankAccount bankAccount = new BankAccount(1234, "KONTO", 2500, 2800);
//                
//                
//                BankAccountOwner owner = new BankAccountOwner(
//                    new BankAccountOwnerKey("Andrzej", "Koryciski"), "Poznan");
//                HashSet<BankAccount> bankAccounts = new HashSet<>();
//                
//                
//                bankAccount.setBankAccountLogin(bankLogin);
//                bankAccount.setBankAccountOwner(owner);
//
//                bankAccounts.add(bankAccount);
//                owner.setBankAccounts(bankAccounts);
//
//                bankLogin.setBankAccounts(bankAccounts);
//                                
//                HibernateSessionUtil.openSessionWithTransaction();
//                
//                BankAccountDao bankAccountDao = new BankAccountDao();
////                bankAccountDao.create(bankAccount);
//                BankAccountLoginDao dao = new BankAccountLoginDao();
//                dao.create(bankLogin);
//                
//                BankDao bankDao = new BankDao();
//                bankDao.create(bzwbk);
//                bankDao.create(pkosa);
//                
//                HibernateSessionUtil.closeSessionWithTransaction();
//                
//                HibernateSessionUtil.openSession();
//                
//                for (Bank bank : bankDao.readAll()) {
//                    System.out.println(bank.getBankName());
//                }
//                BankAccount bacc = bankAccountDao.findByBankName("PKO BP").get(0);
//                System.out.println(bankDao.findByName("BZ WBK").getBankName());
//                
//                System.out.println(bacc.getAccountBalance() + " " +
//                        bacc.getAccountName() + " " +
//                        bacc.getAccountNumber() + " " +
//                        bacc.getAvailableSources() + " " +
//                        bacc.getBankAccountOwner().getBankAccountOwnerKey().getOwnerName() + " " +
//                        bacc.getBankAccountOwner().getBankAccountOwnerKey().getOwnerSurname());
//                BankAccountLogin acc = dao.findByBankName("PKO BP").get(0);
//                System.out.println(acc.getAccessDate() + " " + 
//                        acc.getBank().getBankName() + " " +
//                        acc.getClientId() + " " + 
//                        acc.getLoginPassword());
//
//                HibernateSessionUtil.closeSession();
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//        
//        StrongPasswordEncryptor passEnc = new StrongPasswordEncryptor();
//        String encryptedPass = passEnc.encryptPassword("andrzej");
//        
//        System.out.println("AK: " + encryptedPass);
//        if(passEnc.checkPassword("andrzej", encryptedPass)) {
//            System.out.println("AK Success");
//        } else {
//            System.out.println("Failure");
//        }
//    }
//}