package buggy_just_testit_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonOperations;

public class Profile {
    WebDriver driver;
    public CommonOperations commonOperations = new CommonOperations(driver);

    public Profile(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = ProfileXpathContent.F_NAME)
    WebElement firstName;

    @FindBy(name = ProfileXpathContent.L_NAME)
    WebElement lastName;

    @FindBy(name = ProfileXpathContent.GENDER)
    WebElement genderField;

    @FindBy(name = ProfileXpathContent.AGE)
    WebElement ageField;

    @FindBy(name = ProfileXpathContent.ADDRESS)
    WebElement addressField;

    @FindBy(name = ProfileXpathContent.PHONE)
    WebElement phoneField;

    @FindBy(name = ProfileXpathContent.HOBBY)
    WebElement hobbyField;

    @FindBy(name = ProfileXpathContent.CURRENT_PWD)
    WebElement currentPwd;

    @FindBy(name = ProfileXpathContent.NEW_PWD)
    WebElement newPwd;

    @FindBy(name = ProfileXpathContent.NEW_PWD_CONFIRM)
    WebElement confirmNewPwd;

    @FindBy(xpath = ProfileXpathContent.SAVE_BTN)
    WebElement saveBtn;

    public void setFName(String fName){
        firstName.clear();
        firstName.sendKeys(fName);
    }

    public void setLName(String lName){
        lastName.clear();
        firstName.sendKeys(lName);
    }

    public void setGender(String gender){
        genderField.clear();
        genderField.sendKeys(gender);
    }

    public void setAge(String age){
        ageField.clear();
        ageField.sendKeys(age);
    }

    public void setAddress(String address){
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void setPhone(String phone){
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void setHobby(){

    }

    public void setCurrentPwd(String currentPwdtxt){
        currentPwd.clear();
        currentPwd.sendKeys(currentPwdtxt);
    }

    public void setNewPwd(String newPwdTxt){
        newPwd.clear();
        newPwd.sendKeys(newPwdTxt);
    }

    public void setConfirmNewPwd(String newPwdConfirmTxt){
        confirmNewPwd.clear();
        confirmNewPwd.sendKeys(newPwdConfirmTxt);
    }

    public void clickSaveBtn(){
        saveBtn.click();
    }

    public void setNewPwdDetails(String currentPwdtxt,String newPwdTxt,String newPwdConfirmTxt){
        this.setCurrentPwd(currentPwdtxt);
        this.setNewPwd(newPwdTxt);
        this.setConfirmNewPwd(newPwdConfirmTxt);
    }
}
