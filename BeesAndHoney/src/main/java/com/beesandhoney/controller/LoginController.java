/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.BeesAndHoneyUser;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BeesAndHoneyUserDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.hibernate.HibernateUtil;
import com.beesandhoney.utils.password.BouncyCastlePasswordEncryptor;
import com.beesandhoney.utils.password.PasswordEncryptorWrapper;
import com.beesandhoney.utils.validator.AbstractTextValidator;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.LoginView;
import java.util.Date;
import org.hibernate.Session;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

public final class LoginController implements IController {
    
    private LoginView loginView;
    private BeesAndHoney application;
    private BeesAndHoneyUserDao dao;
        
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.dao = DaoModelFactory.getBeesAndHoneyUserDao();
    }
    
    public void logIn(String login, String password, String securityPassword) {
        this.loginView.cleanErrorLabels();
        
        if (false == validateInput(login, password, securityPassword)) {
            return;
        }
        
        initializeEncryptor(securityPassword);
        
        BeesAndHoneyUser user;        
        try {
            user = getBeesAndHoneyUser(login);
        } catch (EncryptionOperationNotPossibleException e) {
            return;
        }
        
        if (null != user) {
            if (false == validatePassword(password, user)) {
                return;
            } else {
                changeLastAccessDate(login);
            }
        } else {
            user = insertUserInfo(login, password);
        }
        this.application.handleSuccessfulLogin(login, user.getAccessDate().toString());
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        this.application = application;
    }
    
    private void initializeEncryptor(String securityPassword) {
        PasswordEncryptorWrapper encryptor = BouncyCastlePasswordEncryptor.getInstance();
        encryptor.initialize(securityPassword);
    }
    
    private void killEncryptor() {
        PasswordEncryptorWrapper encryptor = BouncyCastlePasswordEncryptor.getInstance();
        encryptor.kill();
    }
    
    private boolean validateInput(String login, String password, 
            String securityPassword) {
//        AbstractTextValidator textValidator = new LoginValidator();
//        if (false == validateInputText(textValidator, login)) {
//            this.loginView.handleIncorrectLogin();
//            return false;
//        }
//        
//        textValidator = new PasswordValidator();
//        if (false == validateInputText(textValidator, password)) {
//            this.loginView.handleIncorrectPassword();
//            return false;
//        }
//
//        if (false == validateInputText(textValidator, password)) {
//            this.loginView.handleIncorrectSecurityPassword();
//            return false;
//        }
//
//        if (0 == password.compareTo(securityPassword)) {
//            this.loginView.handleSamePasswords();
//            return false;
//        }
        return true;
    }
    
    private boolean validateInputText(AbstractTextValidator inputTextValidator, 
            String inputText) {
        return inputTextValidator.validateText(inputText);
    }
    
    private BeesAndHoneyUser getBeesAndHoneyUser(String login) 
            throws EncryptionOperationNotPossibleException {
        
        Session session = dao.openSession();
        BeesAndHoneyUser user;
        
        try {
            user = dao.findByUserName(login, session);
        } catch (EncryptionOperationNotPossibleException e) {
            killEncryptor();
            this.loginView.handleInvalidSecurityPassword();
            throw e;
        } finally {
            dao.closeSession(session);
            HibernateUtil.restart();
        }
        
        return user;
    }
    
    private boolean validatePassword(String password, BeesAndHoneyUser user) {
        if (0 != password.compareTo(user.getUserNamePassword())) {
            this.loginView.handleInvalidPassword();
            killEncryptor();
            return false;
        }
        
        return true;
    }
    
    private BeesAndHoneyUser insertUserInfo(String login, String password) {
        Session session = dao.openSessionWithTransaction();
        
        BeesAndHoneyUser currentUser = ModelFactory.createBeesAndHoneyUser(
                login, password);
        dao.create(currentUser, session);
        
        dao.closeSessionWithTransaction(session);
        
        return currentUser;
    }
    
    private void changeLastAccessDate(String userName) {
        Session session = dao.openSessionWithTransaction();
        
        BeesAndHoneyUser currentUser = dao.findByUserName(userName, session);
        currentUser.setAccessDate(new Date());
        
        dao.closeSessionWithTransaction(session);
    }
}
