/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.model.Bank;
import com.beesandhoney.model.dao.BankDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.constants.BankConstants;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.Session;

public class BeesAndHoney extends Application {
    
    private Stage currentStage;
    
    private IView currentView;

    private String userLogin;

    private final String BAH_VIEW_RESOURCE_PATH;
    private final String LOGIN_VIEW_RESOURCE_PATH;
    
    {
        userLogin = "";
        
        BAH_VIEW_RESOURCE_PATH = "views/BeesAndHoneyView.fxml";
        LOGIN_VIEW_RESOURCE_PATH = "views/LoginView.fxml";
    }
    
    public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage primaryStage) {
        this.currentStage = primaryStage;
        startBeesAndHoney();
    }
    
    public void startBeesAndHoney() {
        changeStage(LOGIN_VIEW_RESOURCE_PATH);
    }
    
    public Scene getCurrentScene() {
        return this.currentStage.getScene();
    }
    
    public void handleSuccessfulLogin(String userLogin) {
        this.userLogin = userLogin;
        changeStage(BAH_VIEW_RESOURCE_PATH);
        
        BankDao dao = DaoModelFactory.getBankDaoInstance();
        
        Session session = dao.openSessionWithTransaction();
        
        for (Bank bank : BankConstants.bankModelList) {
            dao.createOrUpdate(bank, session);
        }
        
        dao.closeSessionWithTransaction(session);
    }
    
    public String getUserLogin() {
        return this.userLogin;
    }
    
    private void changeStage(String viewResourcePath) {
        Scene stageScene = loadScene(viewResourcePath);
        if (null == stageScene) {
            System.out.println("no scene");
            return;
        }
        
        Stage newStage = new Stage();
        newStage.setScene(stageScene);
        configureStage(newStage);
        
        this.currentStage.close();
        this.currentStage = newStage;
        this.currentStage.show();
    }
    
    private void configureStage(Stage stage) {
        stage.setTitle("BeesAndHoney");
        stage.setResizable(false);
    }
    
    private Scene loadScene(String sceneResourcePath) {
        Pane pane = null;
        
        try {
            pane = loadLayout(sceneResourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        return new Scene(pane);
    }
    
    private Pane loadLayout(String viewResourcePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
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
