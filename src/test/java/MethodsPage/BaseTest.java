package MethodsPage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    ExtentReports extent;
    ExtentHtmlReporter reporter;
    ExtentTest logger;


    @BeforeSuite
    public void beforeSuite() {
        extent = new ExtentReports();
        reporter = new ExtentHtmlReporter("./Reports/AutomationReport.html");
        extent.attachReporter(reporter);
    }



    @BeforeClass
    public void Setup() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions());
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
            wait = new WebDriverWait(driver,30);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @BeforeMethod
    public void beforeMethod(Method method) {
        logger = extent.createTest(method.getName());
        logger.info("driver has been started and the test has run");


    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE) {
            String encodeString = ExtentReportUtilities.encodeFileToBase64Binary(ExtentReportUtilities.getScreenshot(driver));
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(encodeString).build());
            driver.manage().deleteAllCookies();
            Constant constant = new Constant();
//            driver.get(constant.getMainPage());
        }


        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.info("Test is Successful.");
        }

    }

    @AfterClass()
    public void AfterClass() {
        driver.quit();
    }


    @AfterSuite()
    public void afterSuite() {
        extent.flush();
    }




    private ChromeOptions chromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-legacy-window");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--aggressive-cache-discard");
        chromeOptions.addArguments("--disable-cache");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-offline-load-stale-cache");
        chromeOptions.addArguments("--disk-cache-size=0");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--no-proxy-server");
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--silent");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-translate");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-browser-side-navigation");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("disable-features=NetworkService");
        chromeOptions.addArguments("enable-features=NetworkServiceInProcess");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.setProxy(null);

        return chromeOptions;
    }
}
