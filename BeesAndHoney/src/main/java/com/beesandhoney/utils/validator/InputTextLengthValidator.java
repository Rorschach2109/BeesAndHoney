/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.validator;

public class InputTextLengthValidator extends AbstractTextValidator {
    
    private final int LOGIN_MIN_LENGTH = 1;
    
    @Override
    public boolean validateText(String login) {
        return validLength(login, LOGIN_MIN_LENGTH);
    }
}
