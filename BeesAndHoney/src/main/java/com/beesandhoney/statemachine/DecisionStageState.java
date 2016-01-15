/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.statemachine;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.view.DecisionView;

public class DecisionStageState implements SecondStageStateInterface {

    private BeesAndHoneyMainController controller;
    private int selectedItemIndex;
    
    public DecisionStageState(BeesAndHoneyMainController controller, 
            int selectedItemIndex) {
        this.controller = controller;
        this.selectedItemIndex = selectedItemIndex;
    }
    
    @Override
    public void handleBeforeExit() {
        DecisionView decisionView = (DecisionView) this.controller
                .getCurrentSecondStageView();
        
        if (decisionView.getDecisionResult()) {
            this.controller.deleteBankingBookItem(this.selectedItemIndex);
        }
    }
    
}
