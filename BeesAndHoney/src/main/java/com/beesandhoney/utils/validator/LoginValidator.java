/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.validator;

public final class LoginValidator extends AbstractTextValidator {
    
    @Override
    public boolean validateText(String login) {
        return validLetters(login);
    }
}
