/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.password;

import org.jasypt.util.password.PasswordEncryptor;

public class JasyptStrongPasswordEncryptor implements PasswordEncryptorWrapper {
    
    private final PasswordEncryptor passwordEncryptor;
    
    public JasyptStrongPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public void initialize(String privateKey) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void kill() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public String encryptPlainPassword(String plainPassword) {
        return this.passwordEncryptor.encryptPassword(plainPassword);
    }
    
    @Override
    public boolean comparePasswords(String plainPassword, String encryptedPassword) {
        return this.passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
