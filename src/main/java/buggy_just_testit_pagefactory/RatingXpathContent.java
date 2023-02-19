package buggy_just_testit_pagefactory;

public class RatingXpathContent {
    public static final String OVERALL_IMG_LINK = "//a[@href='/overall']";
    public static final String SINGLE_CAR_COMMENT= "comment";
    public static final String VOTE_BTN= "//button[contains(text(),'Vote!')]";
    public static final String VOTE_BTN_SUCCESS_MSG= "//p[@class='card-text']";
    public static final String FIRST_COMMENT_IN_LIST= "(//tr//td)[3]";
    public static final String SINGLE_CAR_VOTE_COUNT = "//div[@class='card-block']//h4[contains(text(),'Votes: ')]//strong";
    public static final String LIST_VIEW_MORE_BTN3 = "(//td//a[contains(text(),'View more')])[3]";
    public static final String LIST_VIEW_MORE_BTN4 = "(//td//a[contains(text(),'View more')])[4]";
    public static final String LIST_VIEW_MORE_BTN5 = "(//td//a[contains(text(),'View more')])[5]";
}
