/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.IController;
import com.beesandhoney.utils.ObservableInterface;
import com.beesandhoney.utils.ObserverInterface;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class DetailsView implements IView, ObservableInterface {
    
    ArrayList<ObserverInterface> observers;
    
    @FXML
    private AnchorPane detailsPane;
    @FXML
    private Label accountNameLabel;
    @FXML
    private Label accountNumberLabel;
    @FXML
    private Label accountOwnerLabel;
    @FXML
    private Label availableFundsLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label bankNameLabel;
        
    public DetailsView() {
        this.observers = new ArrayList<>();
    }

    public void fillDetailsLabels(String accountName, String accountNumber,
            String accountOwner, String availableFunds, String balance, 
            String bankName) {
        this.accountNameLabel.setText(accountName);
        this.accountNumberLabel.setText(accountNumber);
        this.accountOwnerLabel.setText(accountOwner);
        this.availableFundsLabel.setText(availableFunds);
        this.balanceLabel.setText(balance);
        this.bankNameLabel.setText(bankName);
    }
    
    @Override
    public void cleanUp() {
    }

    @Override
    public IController getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observers.add(observer);
    }

    @Override
    public void deleteObserver(ObserverInterface observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : this.observers) {
            observer.update();
        }
    }
    
    @FXML
    private void handleCloseButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            notifyObservers();
        }
    }
    
    @FXML
    private void handleCloseButtonClicked() {
        notifyObservers();
    }
}
