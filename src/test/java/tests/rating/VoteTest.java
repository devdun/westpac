package tests.rating;

import buggy_just_testit_pagefactory.LoginOptions;
import buggy_just_testit_pagefactory.Rating;
import buggy_just_testit_pagefactory.RegistrationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.Base;

public class VoteTest extends Base {
    private LoginOptions loginOptions;
    private Rating rating;
    private RegistrationPage registrationPage;
    private String buildLoginName = "LoginTestLogin" + "_" + System.currentTimeMillis();
    private String buildFirstName = "LoginTestFName" + "_" + System.currentTimeMillis();
    private String commonLName = "LName";
    private String commonPwd ="Pwd@123456";
    private String testComment = "This is Test Comment !@#$%^&**()_1234567890 - Time - " + System.currentTimeMillis();

    @DataProvider(name = "Login")
    public Object[][] getLoginData() {
        return getData("Valid_Login", "test_data.xlsx");
    }

    @Test(priority = 1)
    public void verifyVoteFirstItemWithoutComment(){
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        loginOptions.clickRegisterBtn();
        registrationPage.fillRegistrationAndSubmit(buildLoginName,buildFirstName,commonLName,commonPwd,commonPwd);
        Assert.assertTrue(registrationPage.successMsgVisible());
        Assert.assertEquals(registrationPage.getSuccessMsg(),"Registration is successful");
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonPwd);
        rating = PageFactory.initElements(driver, Rating.class);
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonPwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        loginOptions.clickBuggyRatingBtn();
        rating.clickOverAllRating();
        rating.clickListThirdItemViewMore();
        int beforeVoteCount = Integer.parseInt(rating.carVoteCount());
        rating.clickVoteBtn();
        Assert.assertEquals(rating.carVoteSuccessMsg(),"Thank you for your vote!");
        int afterVoteCount = Integer.parseInt(rating.carVoteCount());
        Assert.assertEquals(afterVoteCount,beforeVoteCount+1);
        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
    }

    @Test( priority = 2)
    public void verifyUserCannotVoteMoreThanOneForSpecificCar(){
        driver.get("https://buggy.justtestit.org/");
        commonOperations.waitUntilPageLoaded(driver);
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonPwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        rating.clickOverAllRating();
        rating.clickListThirdItemViewMore();
        Assert.assertFalse(rating.clickVoteBtnVisibleStatus(),"User already voted and vote button should not be visible");
        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
    }

    @Test( priority = 3)
    public void verifyUserCanVoteWithComment(){
        driver.get("https://buggy.justtestit.org/");
        loginOptions.setLoginAndPwdAndClickLogin(buildLoginName,commonPwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        rating.clickOverAllRating();
        rating.clickListFourthItemViewMore();
        int beforeVoteCount = Integer.parseInt(rating.carVoteCount());
        rating.addComment(testComment);
        rating.clickVoteBtn();
        Assert.assertEquals(rating.carVoteSuccessMsg(),"Thank you for your vote!");
        int afterVoteCount = Integer.parseInt(rating.carVoteCount());
        Assert.assertEquals(afterVoteCount,beforeVoteCount+1);
        Assert.assertEquals(rating.getFirstComment(),testComment);
    }
}
