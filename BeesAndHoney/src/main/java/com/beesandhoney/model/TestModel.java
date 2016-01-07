/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model;

import java.util.Date;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class TestModel extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Bank pko = new Bank();
                pko.setBankName("PKO BP");

                BankAccountLogin bankLogin = new BankAccountLogin(1234, "pass");
                bankLogin.setAccessDate(new Date());
                bankLogin.setBank(pko);
                bankLogin.setBankAccounts(new HashSet<>());

                Session session = HibernateUtil.getSessionFactory().openSession();

                Transaction transaction = session.beginTransaction();

                session.save(bankLogin);

                transaction.commit();
                session.close();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}