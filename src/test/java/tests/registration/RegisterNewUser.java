package tests.registration;

import buggy_just_testit_pagefactory.LoginOptions;
import buggy_just_testit_pagefactory.RegistrationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.Base;

public class RegisterNewUser extends Base {
    private RegistrationPage registrationPage;
    private LoginOptions loginOptions;
    private String buildLoginName = "LoginTestLogin" + "_" + System.currentTimeMillis();
    private String buildFirstName = "LoginTestFName" + "_" + System.currentTimeMillis();
    private String commonLName = "LName";

    @DataProvider(name = "Registration")
    public Object[][] getRegistrationData() {
        return getData("Register_User", "test_data.xlsx");
    }

    @Test(dataProvider = "Registration", priority = 1)
    public void verifyInputFields(String login,  String fName, String lName, String pwd, String confirmPwd){
        loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        loginOptions.clickRegisterBtn();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://buggy.justtestit.org/register");
        registrationPage.fillRegistrationForm(login,fName,lName,pwd,confirmPwd);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registrationPage.getLoginField(),login);
        softAssert.assertEquals(registrationPage.getFNameField(),fName);
        softAssert.assertEquals(registrationPage.getLNameField(),lName);
        softAssert.assertEquals(registrationPage.getPasswordField(),pwd);
        softAssert.assertEquals(registrationPage.getConfirmPWDField(),confirmPwd);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyInputFields" },priority = 2)
    public void registerValidUserAndLogin(){
        loginOptions.clickRegisterBtn();
        registrationPage.fillRegistrationAndSubmit(buildLoginName,"buildFirstName","LastName","Pwd@123456","Pwd@123456");
        Assert.assertTrue(registrationPage.successMsgVisible());
        Assert.assertEquals(registrationPage.getSuccessMsg(),"Registration is successful");
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,"Pwd@123456");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginOptions.profileLinkAppear());
        softAssert.assertTrue(loginOptions.userName("buildFirstName"),"User name not display in the header");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyInputFields" },priority = 3)
    public void verifyExistingUserNameValidation(){
        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
        loginOptions.clickRegisterBtn();
        registrationPage.fillRegistrationAndSubmit(buildLoginName,buildFirstName,commonLName,"Pwd@123456","Pwd@123456");
        Assert.assertTrue(registrationPage.errorMsgVisible());
        Assert.assertEquals(registrationPage.getErrorMsg(),"UsernameExistsException: User already exists");
    }

    @Test(dependsOnMethods = {"verifyInputFields" },priority = 4)
    public void verifyInvalidPwdValidation1(){
        registrationPage.fillRegistrationAndSubmit(buildLoginName,buildFirstName,commonLName,"Pwd@123","Pwd@123");
        Assert.assertTrue(registrationPage.errorMsgVisible());
        Assert.assertEquals(registrationPage.getErrorMsg(),"InvalidPasswordException: Password did not conform with policy: Password not long enough");
    }

    @Test(dependsOnMethods = {"verifyInputFields" },priority = 5)
    public void verifyInvalidPwdValidation2(){
        registrationPage.fillRegistrationAndSubmit(buildLoginName,buildFirstName,commonLName,"PwdABCDEFGH","PwdABCDEFGH");
        Assert.assertTrue(registrationPage.errorMsgVisible());
        Assert.assertEquals(registrationPage.getErrorMsg(),"InvalidPasswordException: Password did not conform with policy: Password must have numeric characters");
    }
}
