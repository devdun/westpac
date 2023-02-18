package buggy_just_testit_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonOperations;

public class RegistrationPage {
    WebDriver driver;
    public CommonOperations commonOperations = new CommonOperations(driver);

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = RegistrationPageXpathContent.LOGIN_INPUT_FIELD)
    WebElement loginTextField;

    @FindBy(id = RegistrationPageXpathContent.F_NAME_INPUT_FIELD)
    WebElement firstNameInputField;

    @FindBy(id = RegistrationPageXpathContent.L_NAME_INPUT_FIELD)
    WebElement lastNameInputField;

    @FindBy(id = RegistrationPageXpathContent.PASSWORD_INPUT_FIELD)
    WebElement passwordInputField;

    @FindBy(id = RegistrationPageXpathContent.CONFIRM_PWD_INPUT_FIELD)
    WebElement confirmPwdInputField;

    @FindBy(xpath = RegistrationPageXpathContent.REGISTER_BTN)
    WebElement registerBtn;

    @FindBy(xpath = RegistrationPageXpathContent.CANCEL_BTN)
    WebElement cancelBtn;

    public void setLoginField(String setLoginField){
        commonOperations.waitForElementToBeClickable(driver,loginTextField,10);
        loginTextField.clear();
        loginTextField.sendKeys(setLoginField);
    }

    public String getLoginField(){
        return loginTextField.getAttribute("value");
    }

    public void setFName(String setFName){
        firstNameInputField.clear();
        firstNameInputField.sendKeys(setFName);
    }

    public String getFNameField(){
        return firstNameInputField.getAttribute("value");
    }

    public void setLName(String setLName){
        lastNameInputField.clear();
        lastNameInputField.sendKeys(setLName);
    }

    public String getLNameField(){
        return lastNameInputField.getAttribute("value");
    }

    public void setPwd(String setPwd){
        passwordInputField.clear();
        passwordInputField.sendKeys(setPwd);
    }

    public String getPasswordField(){
        return passwordInputField.getAttribute("value");
    }

    public void setConfirmPwd(String setConfirmPwd){
        confirmPwdInputField.clear();
        confirmPwdInputField.sendKeys(setConfirmPwd);
    }

    public String getConfirmPWDField(){
        return confirmPwdInputField.getAttribute("value");
    }

    public void clickRegisterBtn(){
        registerBtn.click();
    }

    public void fillRegistrationForm(String setLoginField,String setFName,String setLName,String setPwd,String setConfirmPwd){
        this.setLoginField(setLoginField);
        this.setFName(setFName);
        this.setLName(setLName);
        this.setPwd(setPwd);
        this.setConfirmPwd(setConfirmPwd);
    }

    public void fillRegistrationAndSubmit(String setLoginField,String setFName,String setLName,String setPwd,String setConfirmPwd){
        this.fillRegistrationForm(setLoginField,setFName,setLName,setPwd,setConfirmPwd);
        this.clickRegisterBtn();
    }

}
