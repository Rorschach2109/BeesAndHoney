/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BeesAndHoney extends Application {
    
    private Stage primaryStage;
    
    private IView currentView;

    private final String BAH_VIEW_RESOURCE_PATH;
    private final String LOGIN_VIEW_RESOURCE_PATH;
    
    {
        BAH_VIEW_RESOURCE_PATH = "views/BeesAndHoneyView.fxml";
        LOGIN_VIEW_RESOURCE_PATH = "views/LoginView.fxml";
    }
    
    public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        configureStage();
        startBeesAndHoney();
    }
    
    public void startBeesAndHoney() {
        try {
            Pane loginLayout = loadLayout(LOGIN_VIEW_RESOURCE_PATH);
            Scene scene = new Scene(loginLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();
            
        } catch (IOException e) {
        }
    }
    
    public void handleSuccessfulLogin() {
        Stage newStage = new Stage();
        System.out.println("com.beesandhoney.view.BeesAndHoney.handleSuccessfulLogin()");
        try {
            Pane bahLayout = loadLayout(BAH_VIEW_RESOURCE_PATH);
//            changeScene(new Scene(bahLayout));
            this.primaryStage.close();
            newStage.setScene(new Scene(bahLayout));
            newStage.show();
            
            
        } catch (IOException e) {
//            e.printStackTrace();
            
        }
    }
    
    private void changeStage(Scene newScene) {
//        this.primaryStage.close();
        this.primaryStage.setScene(newScene);
        this.primaryStage.show();
    }
    
    private void configureStage() {
        this.primaryStage.setTitle("BeesAndHoney");
    }
    
    private Pane loadLayout(String viewResourcePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.println("[AK] " + getClass().getClassLoader()
            .getResource(viewResourcePath));
        fxmlLoader.setLocation(getClass().getClassLoader()
            .getResource(viewResourcePath));
        
        Pane pane = fxmlLoader.load();
        configureView(fxmlLoader);
        
        return pane;
    }
    
    private void configureView(FXMLLoader fxmlLoader) {
        this.currentView = fxmlLoader.getController();
        if (null != this.currentView) {
            this.currentView.getController().setApplication(this);
        }
    }
}
