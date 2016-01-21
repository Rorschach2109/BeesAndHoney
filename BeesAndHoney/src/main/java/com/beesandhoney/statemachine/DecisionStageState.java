/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.statemachine;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.view.DecisionView;

public class DecisionStageState implements SecondStageStateInterface {

    private BeesAndHoneyMainController controller;
    private BankingBookModel selectedItem;
    
    public DecisionStageState(BeesAndHoneyMainController controller, 
            BankingBookModel selectedItem) {
        this.controller = controller;
        this.selectedItem = selectedItem;
    }
    
    @Override
    public void handleBeforeExit() {
        DecisionView decisionView = (DecisionView) this.controller
                .getCurrentSecondStageView();
        
        if (decisionView.getDecisionResult()) {
            this.controller.deleteBankingBookItem(this.selectedItem);
        }
    }
    
}
