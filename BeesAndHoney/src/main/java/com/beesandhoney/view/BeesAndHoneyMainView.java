/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankingBookModel;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class BeesAndHoneyMainView implements IView {
    
    private final BeesAndHoneyMainController mainController;
    
    @FXML
    BorderPane mainView;
    
    @FXML
    Label currentUserLabel;
    
    @FXML
    Label lastAccessDateLabel;
    
    @FXML
    TableView<BankingBookModel> bankingBookTable;
    
    public BeesAndHoneyMainView() {
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.<init>()");
        this.mainController = new BeesAndHoneyMainController(this);
    }
    
    public void setDisableFlag(boolean disableFlag) {
        this.mainView.setDisable(disableFlag);
    }
    
    public void setCurrentUser(String currentUserLogin) {
        this.currentUserLabel.setText(currentUserLogin);
    }
    
    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDateLabel.setText(lastAccessDate.toString());
    }
    
    public ObservableList<BankingBookModel> getBankingBookTableItems() {
        return bankingBookTable.getItems();
    }
    
    @Override
    public void cleanUp() {
        
    }
    
    @Override
    public IController getController() {
        return this.mainController;
    }
    
    private int getBankingBookTableSelectedIndex() {
        return this.bankingBookTable.getSelectionModel().getFocusedIndex();
    }
    
    @FXML
    private void handleDetailsButtonClicked() {
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.handleDetailsButtonClicked()");
        this.mainController.handleShowBankingBookItemDetails(getBankingBookTableSelectedIndex());
    }
    
    @FXML
    private void handleRefreshButtonClicked() {
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.handleRefreshButtonClicked()");
        this.mainController.handleRefreshBankingBookTable();
    }
    
    @FXML
    private void handleAddButtonClicked() {
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.handleAddButtonClicked()");
        this.mainController.handleAddBankingBookItem();
//        ObservableList<BankingBookModel> data = this.bankingBookTable.getItems();
//        data.add(new BankingBookModel("a", "b", "c", 1));
//        data.add(new BankingBookModel("a", "b", "c", 2));
//        data.add(new BankingBookModel("a", "b", "c", 3));
    }
    
    @FXML
    private void handleDeleteButtonClicked() {
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.handleDeleteButtonClicked()");
        this.mainController.handleDeleteBankingBookItem(getBankingBookTableSelectedIndex());
    }
    
    @FXML
    private void initialize() {
    }
}
