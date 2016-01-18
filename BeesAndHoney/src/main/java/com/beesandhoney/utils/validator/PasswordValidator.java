/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.validator;

public final class PasswordValidator extends AbstractTextValidator {
    
    private final int PASSWORD_MIN_LENGTH = 8;
    
    @Override
    public boolean validateText(String password) {
        boolean checkerResult = validLength(password, PASSWORD_MIN_LENGTH) &&
                validLetters(password) &&
                containsCapitalLetter(password) &&
                containsSmallLetter(password) && 
                containsNumber(password);
        
        return checkerResult;
    }
}
