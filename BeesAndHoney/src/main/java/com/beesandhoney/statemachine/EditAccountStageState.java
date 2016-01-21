/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.statemachine;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.view.AddAccountView;

public class EditAccountStageState implements SecondStageStateInterface {

    private BeesAndHoneyMainController controller;
    private BankingBookModel bankingBookModel;
    
    public EditAccountStageState(BeesAndHoneyMainController controller, 
            BankingBookModel bankingBookModel) {
        this.controller = controller;
        this.bankingBookModel = bankingBookModel;
    }
    
    @Override
    public void handleBeforeExit() {
        AddAccountView editAccountView = (AddAccountView) this.controller
                .getCurrentSecondStageView();
        
        if (true == editAccountView.getDecisionResult()) {
            this.controller.editAccount(bankingBookModel);
        }
    }
    
}
