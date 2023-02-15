package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utils.CommonOperations;
import utils.ConfigFileReader;
import utils.ExcelReader;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Devdun.k
 */

public class Base {
    public WebDriver driver;
    public ExcelReader excel;
    Date date = Calendar.getInstance().getTime();
    private static final String Test_Output_Folder = "./test-output/MyReports/";
    private static final String FOLDER_NAME = "Time_" + System.currentTimeMillis() + "/";
    private static final String SPECIAL_CAPTURE_FOLDER = "/SPECIAL_CAPTURE_/";
    public CommonOperations commonOperations = new CommonOperations(driver);
    public String currentSystemUrl = null;

    //Load all the data in the String[][] using the getDataFromSheet method of ExcelReader class
    public String[][] getData(String sheetname, String excelname) {
        String path = System.getProperty("user.dir") + "/src/main/resources/data/" + excelname;
        excel = new ExcelReader(path);
        return excel.getDataFromSheet(sheetname, excelname);
    }

    @BeforeTest
    @Parameters({"browser", "URL", "Env"})
    public void setupBrowserAndURL(String browser, String URL, String Env) throws Exception {
        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("window-size=1400,2100");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("HLChrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("disable-gpu");
            chromeOptions.addArguments("window-size=1400,2100"); // linux should be activate
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("HLFirefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(firefoxBinary);
            driver = new FirefoxDriver(options);
        } else {
            throw new Exception("Browser is not correct in xml :" + browser);
        }
        this.urlSetup(URL);
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        takeSnapShot(driver, Test_Output_Folder + SPECIAL_CAPTURE_FOLDER + methodName + System.currentTimeMillis() + ".png");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.MILLISECONDS);
    }

    public void urlSetup(String URL) throws Exception {
        ConfigFileReader configFileReader = new ConfigFileReader();
        if (URL.equalsIgnoreCase("QA")) {
            currentSystemUrl = configFileReader.applicationUrl_QA();
            driver.get(currentSystemUrl);
            commonOperations.waitUntilPageLoaded(driver);
        } else if (URL.equalsIgnoreCase("Live")) {
            driver.get(configFileReader.applicationUrl_Live());
            commonOperations.waitUntilPageLoaded(driver);
        } else if (URL.equalsIgnoreCase("DEMO")) {
            driver.get(configFileReader.applicationUrl_Demo());
            commonOperations.waitUntilPageLoaded(driver);
        } else if (URL!= null){
            driver.get(URL);
        } else{
            throw new Exception("URL is not correct in xml :" + URL);
        }
    }

    @AfterMethod
    public void screenShot(ITestResult result) {
        DateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        String today = formatter.format(date);
        String outputImageFolder = Test_Output_Folder + today + "/ScreenCaptures/";
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(outputImageFolder + result.getName() + "_" + System.currentTimeMillis() + ".png"));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void endReport() {
//        driver.quit();
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
