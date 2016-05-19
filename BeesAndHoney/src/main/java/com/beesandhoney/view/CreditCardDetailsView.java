/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.CreditCardDetailsController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.CreditCardModel;
import com.beesandhoney.utils.ObserverInterface;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CreditCardDetailsView implements IObservableView {
    
    private final CreditCardDetailsController controller;
    private final ArrayList<ObserverInterface> observers;
    
    @FXML
    private Label creditCardNameLabel;
    @FXML
    private Label creditCardNumberLabel;
    @FXML
    private Label accountOwnerNameLabel;
    @FXML
    private Label accountOwnerAddressLabel;
    @FXML
    private Label availableFundsLabel;
    @FXML
    private Label creditCardLimitLabel;
    @FXML
    private Label bankNameLabel;

    public CreditCardDetailsView() {
        this.controller = new CreditCardDetailsController();
        this.observers = new ArrayList<>();
    }
    
    public void fillAccountDetails(CreditCardModel selectedItem) {
        BankAccount bankAccount = this.controller.getAccountInformation(selectedItem);
        
        if (null != bankAccount) {
            this.creditCardNameLabel.setText(bankAccount.getAccountName());
            this.creditCardNumberLabel.setText(bankAccount.getAccountNumber());
            this.accountOwnerNameLabel.setText(bankAccount.getBankAccountOwner().getOwnerNameSurname());
            this.accountOwnerAddressLabel.setText(bankAccount.getBankAccountOwner().getOwnerAddress());
            this.availableFundsLabel.setText(Double.toString(bankAccount.getAvailableSources()) + " " + bankAccount.getCurrency());
            this.creditCardLimitLabel.setText(Double.toString(bankAccount.getAccountLimit()) + " " + bankAccount.getCurrency());
            this.bankNameLabel.setText(bankAccount.getBankAccountLogin().getBank().getBankName());
        }
    }
    
    @Override
    public void cleanUp() {
    }

    @Override
    public IController getController() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setController(IController controller) {
        throw new UnsupportedOperationException("Not supported.");
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
