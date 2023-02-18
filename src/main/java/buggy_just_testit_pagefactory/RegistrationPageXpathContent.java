package buggy_just_testit_pagefactory;

public class RegistrationPageXpathContent {
    public static final String LOGIN_INPUT_FIELD = "username";
    public static final String F_NAME_INPUT_FIELD = "firstName";
    public static final String L_NAME_INPUT_FIELD = "lastName";
    public static final String PASSWORD_INPUT_FIELD = "password";
    public static final String CONFIRM_PWD_INPUT_FIELD = "confirmPassword";
    public static final String REGISTER_BTN = "//button[@type='submit'][not(@disabled)][contains(text(),'Register')]";
    public static final String CANCEL_BTN = "//a[@role='button'][contains(text(),'Cancel')]";
    public static final String SUCCESS_MSG = "//div[@class='result alert alert-success']";
    public static final String ERROR_MSG = "//div[@class='result alert alert-danger']";
}
