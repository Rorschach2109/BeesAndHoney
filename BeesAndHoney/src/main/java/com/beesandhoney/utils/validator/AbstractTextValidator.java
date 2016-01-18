/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.validator;

public abstract class AbstractTextValidator {
    
    private final String PATTERN_NUMBER;
    private final String PATTERN_VALID_LETTERS;
    
    {
        PATTERN_NUMBER = ".*\\d+.*";
        PATTERN_VALID_LETTERS = "\\w+";
    }
    
    public abstract boolean validateText(String text);
    
    protected final boolean validLength(String text, int minimalLength) {
        return minimalLength < text.length();
    }
    
    protected final boolean validLetters(String text) {
        return text.matches(this.PATTERN_VALID_LETTERS);
    }
    
    protected final boolean containsCapitalLetter(String text) {
        return !text.equals(text.toLowerCase());
    }
    
    protected final boolean containsSmallLetter(String text) {
        return !text.equals(text.toUpperCase());
    }
    
    protected final boolean containsNumber(String text) {
        return text.matches(this.PATTERN_NUMBER);
    }
}
