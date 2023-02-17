package buggy_just_testit_pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonOperations;

public class LoginOptions {
    WebDriver driver;
    public CommonOperations commonOperations = new CommonOperations(driver);

    public LoginOptions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = LoginOptionsXpathContent.LOGIN_INPUT_FIELD)
    WebElement loginTextField;

    @FindBy(name = LoginOptionsXpathContent.PASSWORD_INPUT_FIELD)
    WebElement passwordTextField;

    @FindBy(xpath = LoginOptionsXpathContent.LOGIN_BTN)
    WebElement loginBtn;

    @FindBy(xpath = LoginOptionsXpathContent.LINK_TEXT_USERNAME)
    WebElement userName;

    @FindBy(linkText = LoginOptionsXpathContent.LINK_REGISTER_BTN)
    WebElement registerLink;

    @FindBy(linkText = LoginOptionsXpathContent.LINK_TEXT_PROFILE)
    WebElement profileLink;

    @FindBy(linkText = LoginOptionsXpathContent.LINK_TEXT_LOGOUT)
    WebElement logoutLink;

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

    public boolean userName(String loginText){
        String name=  userName.getText();
        return name.contains(loginText);
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

    public boolean profileLinkAppear(){
        return driver.findElements(By.linkText(LoginOptionsXpathContent.LINK_TEXT_PROFILE)).size()>0;
    }

    public void setLoginAndPwd(String setLoginField,String setPwdField){
        this.setLoginField(setLoginField);
        this.setPwdField(setPwdField);
    }

    public void setLoginAndPwdAndClickLogin(String setLoginField,String setPwdField){
        this.setLoginField(setLoginField);
        this.setPwdField(setPwdField);
        this.clickLoginBtn();
    }

}
