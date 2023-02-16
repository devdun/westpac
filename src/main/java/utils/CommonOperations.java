package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Devdun.k
 */
public class CommonOperations {
    private WebDriver driver;
    int sleepTime;
    Properties properties = null;

    public CommonOperations(WebDriver driver) {
        this.driver = driver;
    }

    public CommonOperations() {
        this.sleepTime = 1000;
    }

    public CommonOperations(int sleep, int tries) {
        this.sleepTime = sleep;
    }

    public Properties getProperties (String propertiesPath) {
        InputStream input = CommonOperations.class.getClassLoader().getResourceAsStream(propertiesPath + ".properties");
        properties = new Properties();
        try {
            if (input.available() > 0) {
                properties.load(input);
            }
        } catch (Exception e) {
        }
        return this.properties;
    }

    public boolean isPageLoading(WebDriver d) {
        JavascriptExecutor js = (JavascriptExecutor)d;
        String strExec = "return document.readyState!=\'complete\';";
        return ((Boolean)js.executeScript(strExec, new Object[0])).booleanValue();
    }

    public void waitUntilPageLoaded(WebDriver driver) {
        while(this.isPageLoading(driver)) {
            try {
                Thread.sleep((long)this.sleepTime);
            } catch (InterruptedException var3) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //refresh page and wait until page load
    public void refreshPageAndWaitUntilLoad(){
        driver.navigate().refresh();
        this.waitUntilPageLoaded(driver);
    }

    public boolean waitForAnElementDisplayed(WebDriver driver, WebElement element , int tries, int sleepTime) {
        for(int count = 0; count < tries; ++count) {
            try {
                if(element.isDisplayed()) {
                    return true;
                }

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException var9) {
                    Thread.currentThread().interrupt();
                }
            } catch (Exception var10) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException var8) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return false;
    }

    public int randomInt(int min, int max) {
        if(min > max) {
            int rand = min;
            min = max;
            max = rand;
        }

        Random rand1 = new Random();
        int randomNumber = rand1.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    public void waitForSpecificTime(int sleepTime){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //select the dropdown using "select by visible text"
    public static void dropDownSelectByText(WebElement webElement, String VisibleText){
        Select selObj=new Select(webElement);
        selObj.selectByVisibleText(VisibleText);
    }

    //select the dropdown using "select by index"
    public static void dropDownSelectByIndex(WebElement webElement, int IndexValue){
        Select selObj=new Select(webElement);
        selObj.selectByIndex(IndexValue);
    }

    //select the dropdown using "select by value"
    public static void dropDownSelectByValue(WebElement webElement, String Value){
        Select selObj=new Select(webElement);
        selObj.selectByValue(Value);
    }

    //scroll down web page
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
