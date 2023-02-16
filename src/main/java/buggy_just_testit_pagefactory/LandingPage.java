package buggy_just_testit_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonOperations;

public class LandingPage {
    WebDriver driver;
    public CommonOperations commonOperations = new CommonOperations(driver);

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = LandingPageXpathContent.LOGIN_INPUT_FIELD)
    WebElement loginTextField;

    @FindBy(name = LandingPageXpathContent.PASSWORD_INPUT_FIELD)
    WebElement passwordTextField;

    public void setLoginField(String setLoginField){
        commonOperations.waitForElementToBeClickable(driver,loginTextField,10);
        loginTextField.clear();
        loginTextField.sendKeys(setLoginField);
    }

    public void setPwdField(String setPwdField){
        commonOperations.waitForElementToBeClickable(driver,passwordTextField,10);
        passwordTextField.clear();
        passwordTextField.sendKeys(setPwdField);
    }

    public String typedLogin(){
        return loginTextField.getAttribute("value");
    }

    public String typedPwd(){
        return passwordTextField.getAttribute("value");
    }

    public void setLoginAndPwd(String setLoginField,String setPwdField){
        this.setLoginField(setLoginField);
        this.setPwdField(setPwdField);
    }

}
