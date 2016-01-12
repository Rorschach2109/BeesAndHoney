/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BeesAndHoney extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BeesAndHoney");
        
        showLoginLayout();
    }
    
    private void showLoginLayout() {
        try {
            System.out.println("[AK] " + getClass().getClassLoader().getResource("views/loginLayout.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("views/loginLayout.fxml"));
            AnchorPane loginLayout = (AnchorPane) loader.load();
            
            LoginController loginController = loader.getController();
            
            Scene scene = new Scene(loginLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();
        } catch (IOException e) {
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
