/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.statemachine;

import com.beesandhoney.controller.BeesAndHoneyMainController;

public class EditAccountStageState implements SecondStageStateInterface {

    private BeesAndHoneyMainController controller;
    
    public EditAccountStageState(BeesAndHoneyMainController controller) {
        this.controller = controller;
    }
    
    @Override
    public void handleBeforeExit() {

    }
    
}
