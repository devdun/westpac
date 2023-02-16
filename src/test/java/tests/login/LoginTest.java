package tests.login;

import buggy_just_testit_pagefactory.LandingPage;
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

    @Test(dataProvider = "Login", priority = 1)
    public void verifyInputFields(String login, String pwd, String type){
        loginText = login;
        pwdText = pwd;
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = PageFactory.initElements(driver,LandingPage.class);
        landingPage.setLoginAndPwd(loginText,pwdText);
        String typedLogin = landingPage.typedLogin();
        String typedPwd = landingPage.typedPwd();
        softAssert.assertEquals(typedLogin,loginText);
        softAssert.assertEquals(typedPwd,pwdText);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyValidLogin(){
        //click login button
        //verify successfully logged in
    }
}
