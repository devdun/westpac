package tests.login;

import buggy_just_testit_pagefactory.LoginOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.Base;

public class LoginTest extends Base {
    String loginText = null;
    String pwdText = null;

    @DataProvider(name = "Login")
    public Object[][] getLoginData() {
        return getData("Valid_Login", "test_data.xlsx");
    }

    @Test( priority = 1)
    public void verifyInputFields(){
        loginText = "Login34!@#$%^&*(),=";
        pwdText = "Pwd!@#$%^&*()_+=-<>?,./";
        SoftAssert softAssert = new SoftAssert();
        LoginOptions loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        loginOptions.setLoginAndPwd(loginText,pwdText);
        String typedLogin = loginOptions.typedLogin();
        String typedPwd = loginOptions.typedPwd();
        softAssert.assertEquals(typedLogin,loginText);
        softAssert.assertEquals(typedPwd,pwdText);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyInputFields" },dataProvider = "Login", priority = 2)
    public void verifyValidLogin(String login, String pwd, String fName, String type){
        loginText = login;
        pwdText = pwd;
        SoftAssert softAssert = new SoftAssert();
        LoginOptions loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        loginOptions.setLoginAndPwdAndClickLogin(loginText,pwdText);
        softAssert.assertTrue(loginOptions.profileLinkAppear());
        softAssert.assertTrue(loginOptions.userName(fName),"User name not display in the header");
        softAssert.assertAll();
    }

}
