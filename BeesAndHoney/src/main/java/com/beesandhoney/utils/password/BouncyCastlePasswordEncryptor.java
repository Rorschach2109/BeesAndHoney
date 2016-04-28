/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.password;

import java.lang.reflect.Field;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class BouncyCastlePasswordEncryptor implements PasswordEncryptorWrapper {

    private StandardPBEStringEncryptor pbeEncryptor;

    private static BouncyCastlePasswordEncryptor bouncyCastleEncryptor = null;
    
    private static final String ALGORITHM_NAME;
    private static final int OBTATION_ITERATIONS;
    private static final String PROVIDER_NAME;
    private static final String STRING_ENCRYPTOR_NAME;

    static
    {
        ALGORITHM_NAME = "PBEWithSHAAnd2-KeyTripleDES-CBC";
        OBTATION_ITERATIONS = 2000;
        PROVIDER_NAME = BouncyCastleProvider.PROVIDER_NAME;
        STRING_ENCRYPTOR_NAME = "BCHibernatePasswordEncryptor";
    }
    
    private BouncyCastlePasswordEncryptor() {
        super();
    }
    
    public static BouncyCastlePasswordEncryptor getInstance() {
        if (null == bouncyCastleEncryptor) {
            bouncyCastleEncryptor = new BouncyCastlePasswordEncryptor(); 
        }
        return bouncyCastleEncryptor;
    }
    
    @Override
    public void initialize(String privateKey) {
        if (null == this.pbeEncryptor) {
            unlimitedStrengthHack();
            Security.addProvider(new BouncyCastleProvider());
            this.pbeEncryptor = new StandardPBEStringEncryptor();
            this.pbeEncryptor.setAlgorithm(ALGORITHM_NAME);
            this.pbeEncryptor.setKeyObtentionIterations(OBTATION_ITERATIONS);
            this.pbeEncryptor.setProviderName(PROVIDER_NAME);
            this.pbeEncryptor.setPassword(privateKey);

            this.pbeEncryptor.initialize();
            
            HibernatePBEEncryptorRegistry.getInstance()
                    .registerPBEStringEncryptor(STRING_ENCRYPTOR_NAME, pbeEncryptor);
            
        }
    }

    @Override
    public void kill() {
        this.pbeEncryptor = null;
    }
    
    @Override
    public String encryptPlainPassword(String plainPassword) {
        String encryptedPassword = "";
        
        if (null != this.pbeEncryptor) {
            encryptedPassword = this.pbeEncryptor.encrypt(plainPassword);
        }
        
        return encryptedPassword;
    }

    @Override
    public boolean comparePasswords(String plainPassword, String encryptedPassword) {
        throw new UnsupportedOperationException("Not supported.");
    }    
    
    private static void unlimitedStrengthHack() {
        try { 
            Field field = Class.forName("javax.crypto.JceSecurity").
            getDeclaredField("isRestricted");
            field.setAccessible(true);
            field.set(null, java.lang.Boolean.FALSE); 
        } catch (Exception ex) {
        }
    }
}
