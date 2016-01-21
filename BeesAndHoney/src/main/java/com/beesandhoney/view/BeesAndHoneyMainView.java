/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BankAccountLoginDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.ObserverInterface;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.hibernate.Session;

public class BeesAndHoneyMainView implements IView, ObserverInterface {
    
    private final BeesAndHoneyMainController mainController;
    
    @FXML
    private BorderPane mainView;
    
    @FXML
    private Label currentUserLabel;
    
    @FXML
    private Label lastAccessDateLabel;
    
    @FXML
    private TableView<BankingBookModel> bankingBookTable;
    
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
    
    @Override
    public void update() {
        List<BankAccountLogin> bankAccountLoginList = getBankAccountLoginList();

        ObservableList<BankingBookModel> bankingBookTableContent = 
                this.bankingBookTable.getItems();
        
        bankingBookTableContent.clear();
        
        for (BankAccountLogin log : bankAccountLoginList) {
            bankingBookTableContent.add(ModelFactory.createBankingBookModel(
                    log.getBankAccountLoginAlias(),
                    log.getBank().getBankName(),
                    log.getClientId())
            );
        }
    }
    
    private BankingBookModel getSelectedBankingBookModel() {
        return this.bankingBookTable.getSelectionModel().getSelectedItem();
    }
    
    private List<BankAccountLogin> getBankAccountLoginList() {
        BankAccountLoginDao dao = DaoModelFactory.getBankAccountLoginDaoInstance();
        Session session = dao.openSession();
        
        List<BankAccountLogin> bankAccountList = dao.readAll(session);
        
        dao.closeSession(session);
        
        return bankAccountList;
    }
    
    @FXML
    private void handleEditButtonClicked() {
        BankingBookModel selectedBankingBookModel = getSelectedBankingBookModel();
        
        if (null != selectedBankingBookModel) {
            this.mainController.handleEditBankingBookItem(
                    selectedBankingBookModel
            );
        }
    }
    
    @FXML
    private void handleRefreshButtonClicked() {
        this.mainController.handleRefreshBankingBookTable();
    }
    
    @FXML
    private void handleAddButtonClicked() {
        this.mainController.handleAddBankingBookItem();
    }
    
    @FXML
    private void handleDeleteButtonClicked() {
        BankingBookModel selectedBankingBookModel = getSelectedBankingBookModel();
        
        if (null != selectedBankingBookModel) {
            this.mainController.handleDeleteBankingBookItem(
                    selectedBankingBookModel
            );
        }
    }
    
    @FXML
    private void initialize() {
        DaoModelFactory.getBankAccountLoginDaoInstance().registerObserver(this);
    }
}
