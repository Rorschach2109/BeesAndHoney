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
                    log.getBankAccountLoginKey().getBankAccountLoginAlias(),
                    log.getBank().getBankName(),
                    log.getBankAccountLoginKey().getClientId())
            );
        }
    }
    
    private int getBankingBookTableSelectedIndex() {
        return this.bankingBookTable.getSelectionModel().getFocusedIndex();
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
        System.out.println("com.beesandhoney.view.BeesAndHoneyMainView.handleDetailsButtonClicked()");
        this.mainController.handleEditBankingBookItem(getBankingBookTableSelectedIndex());
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
        DaoModelFactory.getBankAccountLoginDaoInstance().registerObserver(this);
    }
}
