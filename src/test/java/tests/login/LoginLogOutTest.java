package tests.login;

import buggy_just_testit_pagefactory.LoginOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.Base;

public class LoginLogOutTest extends Base {
    private LoginOptions loginOptions;
    String loginText = null;
    String pwdText = null;
    String firstName = null;

    @DataProvider(name = "Login")
    public Object[][] getLoginData() {
        return getData("Valid_Login", "test_data.xlsx");
    }

    @Test(dataProvider = "Login", priority = 1)
    public void verifyValidLoginLogOut(String login, String pwd, String fName, String type){
        loginText = login;
        pwdText = pwd;
        firstName=fName;
        SoftAssert softAssert = new SoftAssert();
        loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        loginOptions.setLoginAndPwdAndClickLogin(loginText,pwdText);
        softAssert.assertTrue(loginOptions.profileLinkAppear());
        softAssert.assertTrue(loginOptions.userName(firstName),"User name not display in the header");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyLogOut(){
        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
    }

}
