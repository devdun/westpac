package tests.login;

import buggy_just_testit_pagefactory.LoginOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.Base;

public class InvalidLoginTests extends Base {
    String loginText = null;
    String pwdText = null;

    @DataProvider(name = "InvalidLogin")
    public Object[][] getLoginData() {
        return getData("Invalid_Login", "test_data.xlsx");
    }

    @Test(dataProvider = "InvalidLogin", priority = 1)
    public void verifyInValidLogin(String login, String pwd, String type){
        loginText = login;
        pwdText = pwd;
        SoftAssert softAssert = new SoftAssert();
        LoginOptions loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        loginOptions.setLoginAndPwdAndClickLogin(loginText,pwdText);
        if (type.equalsIgnoreCase("invalidLoginName")){
            softAssert.assertFalse(loginOptions.profileLinkAppear());
            softAssert.assertEquals(loginOptions.validationMsg(),"Invalid username/password");
        }else if (type.equalsIgnoreCase("NullLoginName")){
            softAssert.assertTrue(loginOptions.emptyLoginValidationMsgAppear());
            softAssert.assertEquals(loginOptions.emptyLoginValidationMsg(),"Please fill out this field.");
        }else if (type.equalsIgnoreCase("NullPwd")){
            softAssert.assertTrue(loginOptions.emptyPwdValidationMsgAppear());
            softAssert.assertEquals(loginOptions.emptyPwdValidationMsg(),"Please fill out this field.");
        }
        softAssert.assertAll();
    }
}
