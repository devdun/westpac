package tests.rating;

import buggy_just_testit_pagefactory.LoginOptions;
import buggy_just_testit_pagefactory.Rating;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.Base;

public class VoteTest extends Base {
    private LoginOptions loginOptions;
    private Rating rating;

    @DataProvider(name = "Login")
    public Object[][] getLoginData() {
        return getData("Valid_Login", "test_data.xlsx");
    }

    @Test(dataProvider = "Login", priority = 1)
    public void verifyVoteFirstItemWithoutComment(String login, String pwd, String fName, String type){
        loginOptions = PageFactory.initElements(driver, LoginOptions.class);
        rating = PageFactory.initElements(driver, Rating.class);
        loginOptions.setLoginAndPwdAndClickLogin(login,pwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        rating.clickOverAllRating();
        rating.clickListThirdItemViewMore();


        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
    }

    @Test(dataProvider = "Login", priority = 2)
    public void verifyVoteFirstItemWithComment(String login, String pwd, String fName, String type){
        driver.get("https://buggy.justtestit.org/");
        loginOptions.setLoginAndPwdAndClickLogin(login,pwd);
        Assert.assertTrue(loginOptions.profileLinkAppear());
        rating.clickOverAllRating();
        rating.clickListThirdItemViewMore();

        loginOptions.clickLogOut();
        Assert.assertFalse(loginOptions.profileLinkAppear());
    }
}
