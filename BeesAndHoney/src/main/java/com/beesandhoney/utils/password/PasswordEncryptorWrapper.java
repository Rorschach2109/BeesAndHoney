/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.password;

public interface PasswordEncryptorWrapper {
    public void initialize(String privateKey);
    public void kill();
    
    public String encryptPlainPassword(String plainPassword);
    public boolean comparePasswords(String plainPassword, String encryptedPassword);
}
