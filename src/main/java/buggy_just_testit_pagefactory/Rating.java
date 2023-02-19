package buggy_just_testit_pagefactory;

import org.openqa.selenium.By;
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

    @FindBy(xpath = RatingXpathContent.LIST_VIEW_MORE_BTN4)
    WebElement listViewMoreBtn4;

    @FindBy(xpath = RatingXpathContent.VOTE_BTN_SUCCESS_MSG)
    WebElement voteBtnSuccessMsg;

    @FindBy(xpath = RatingXpathContent.FIRST_COMMENT_IN_LIST)
    WebElement firstCommentInList;

    public void clickOverAllRating(){
        commonOperations.waitForAnElementDisplayed(driver,overAllImgLink,5,5);
        overAllImgLink.click();
        commonOperations.waitUntilPageLoaded(driver);
    }

    public void clickListThirdItemViewMore(){
        listViewMoreBtn3.click();
    }

    public void clickListFourthItemViewMore(){
        listViewMoreBtn4.click();
    }

    public void addComment(String addComment){
        commonOperations.waitUntilElementVisible(driver,singleCarComment,10);
        singleCarComment.sendKeys(addComment);
    }

    public String carVoteCount(){
        commonOperations.waitUntilElementVisible(driver,singleCarVoteCount,10);
        return singleCarVoteCount.getText();
    }

    public String getFirstComment(){
        commonOperations.waitUntilElementVisible(driver,firstCommentInList,10);
        return firstCommentInList.getText();
    }

    public String carVoteSuccessMsg(){
        commonOperations.waitUntilElementVisible(driver,voteBtnSuccessMsg,10);
        return voteBtnSuccessMsg.getText();
    }

    public void clickVoteBtn(){
        voteBtn.click();
    }

    public Boolean clickVoteBtnVisibleStatus(){
        return driver.findElements(By.xpath(RatingXpathContent.VOTE_BTN)).size()>0;
    }

}
