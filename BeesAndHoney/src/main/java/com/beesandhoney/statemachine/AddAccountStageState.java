/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.statemachine;

import com.beesandhoney.controller.BeesAndHoneyMainController;

public class AddAccountStageState implements SecondStageStateInterface {

    private final BeesAndHoneyMainController controller;
    
    public AddAccountStageState(BeesAndHoneyMainController controller) {
        this.controller = controller;
    }
    
    @Override
    public void handleBeforeExit() {
    }
    
}
