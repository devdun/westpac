package buggy_just_testit_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonOperations;

public class Rating {
    WebDriver driver;
    public CommonOperations commonOperations = new CommonOperations(driver);

    public Rating(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = RatingXpathContent.OVERALL_IMG_LINK)
    WebElement overAllImgLink;

    @FindBy(id = RatingXpathContent.SINGLE_CAR_COMMENT)
    WebElement singleCarComment;

    @FindBy(xpath = RatingXpathContent.VOTE_BTN)
    WebElement voteBtn;

    @FindBy(xpath = RatingXpathContent.SINGLE_CAR_VOTE_COUNT)
    WebElement singleCarVoteCount;

    @FindBy(xpath = RatingXpathContent.LIST_VIEW_MORE_BTN3)
    WebElement listViewMoreBtn3;

    public void clickOverAllRating(){
        commonOperations.waitForAnElementDisplayed(driver,overAllImgLink,5,5);
        overAllImgLink.click();
        commonOperations.waitUntilPageLoaded(driver);
    }

    public void clickListThirdItemViewMore(){
        listViewMoreBtn3.click();
    }

}
