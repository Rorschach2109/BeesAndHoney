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
import com.beesandhoney.model.BeesAndHoneyUser;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BeesAndHoneyUserDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.ObserverInterface;
import java.util.Date;
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
        this.mainController = new BeesAndHoneyMainController(this);
    }
    
    public void setDisableFlag(boolean disableFlag) {
        this.mainView.setDisable(disableFlag);
    }
    
    public void setCurrentUser(String currentUserLogin) {
        this.currentUserLabel.setText(currentUserLogin);
    }
    
    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDateLabel.setText(lastAccessDate);
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
    public void setController(IController controller) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void update() {
        BeesAndHoneyUserDao dao = DaoModelFactory.getBeesAndHoneyUserDao();
        Session session = dao.openSession();
        
        BeesAndHoneyUser currentUser = dao.findByUserName(
                this.mainController.getCurrentUserLogin(), session
        );
        
        ObservableList<BankingBookModel> bankingBookTableContent = 
                this.bankingBookTable.getItems();
        
        bankingBookTableContent.clear();
        
        for (BankAccountLogin log : currentUser.getBankAccountLogins()) {
            bankingBookTableContent.add(ModelFactory.createBankingBookModel(
                    log.getBankAccountLoginAlias(),
                    log.getBank().getBankName(),
                    log.getClientId())
            );
        }
        
        dao.closeSession(session);
    }
    
    private BankingBookModel getSelectedBankingBookModel() {
        return this.bankingBookTable.getSelectionModel().getSelectedItem();
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
        DaoModelFactory.getBeesAndHoneyUserDao().registerObserver(this);
        DaoModelFactory.getBankAccountLoginDaoInstance().registerObserver(this);
    }
}
