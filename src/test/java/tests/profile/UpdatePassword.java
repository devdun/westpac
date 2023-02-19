package tests.profile;

import buggy_just_testit_pagefactory.LoginOptions;
import buggy_just_testit_pagefactory.Profile;
import buggy_just_testit_pagefactory.RegistrationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.Base;

public class UpdatePassword extends Base {
    private RegistrationPage registrationPage;
    private LoginOptions loginOptions;
    private Profile profile;
    private String buildLoginName = "LoginTestLogin" + "_" + System.currentTimeMillis();
    private String buildFirstName = "LoginTestFName" + "_" + System.currentTimeMillis();
    private String commonLName = "LName";
    private String commonPwd = "Pwd@123456";
    private String commonNewPwd = "New@123458";

    @Test(priority = 1)
    public void createNewUserChangePwdAndLogin(){
        loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        profile = PageFactory.initElements(driver, Profile.class);
        loginOptions.clickRegisterBtn();
        registrationPage.fillRegistrationAndSubmit(buildLoginName,buildFirstName,commonLName,commonPwd,commonPwd);
        Assert.assertTrue(registrationPage.successMsgVisible());
        Assert.assertEquals(registrationPage.getSuccessMsg(),"Registration is successful");
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonPwd);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginOptions.profileLinkAppear());
        softAssert.assertTrue(loginOptions.userName(buildFirstName),"Correct user name not display in the header");
        softAssert.assertAll();
        loginOptions.clickProfileLink();
        profile.setNewPwdDetails(commonPwd,commonNewPwd,commonNewPwd);
        profile.clickSaveBtn();
        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonNewPwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        Assert.assertTrue(loginOptions.userName(buildFirstName),"Correct user  name not display in the header");
    }
}
