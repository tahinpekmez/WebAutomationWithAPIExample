package MethodsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);

    }


    public void clickElementByXpath(String xpath) {
        waitUntilJSReady();
        waitClickableWebElementByXpath(xpath);
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).click();

    }

    public void clickToElementByXpath(String xpath) {
        waitUntilJSReady();
        waitClickableWebElementByXpath(xpath);
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).click();

    }

    public void clickElementByClassName(String className) {
        waitUntilJSReady();
        waitClickableWebElementByXpath(className);
        scrollToElementByXpath(className);
        driver.findElement(By.className(className)).click();

    }


    public void clickElementById(String id) {
        waitUntilJSReady();
        waitClickableWebElementById(id);
        scrollToElementByID(id);
        driver.findElement(By.id(id)).click();
    }

    public void fillInTheBlankById(String id, String keys) {
        waitUntilJSReady();
        scrollToElementByID(id);
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    public void fillInTheBlankByXpath(String xpath, String keys) {
        waitUntilJSReady();
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(keys);
    }

    public void selectByXpathIndex(String xpath, int index) {
        waitPageLoad();
        waitUntilJSReady();
        WebElement element = driver.findElement(By.xpath(xpath));
        waitClickableWebElement(element);
        Select select = new Select(element);
        select.selectByIndex(index);

    }

    public void selectByXpathByValue(String xpath, String value) {
        waitPageLoad();
        waitUntilJSReady();
        WebElement element = driver.findElement(By.xpath(xpath));
        waitClickableWebElement(element);
        Select select = new Select(element);
        select.selectByValue(value);

    }

    public String selectByXpathBySelectedText(String xpath) {
        waitPageLoad();
        waitUntilJSReady();
        WebElement element = driver.findElement(By.xpath(xpath));
        waitClickableWebElement(element);
        Select select = new Select(driver.findElement(By.xpath(xpath)));
        select.getFirstSelectedOption().getText();
        return select.getFirstSelectedOption().getText();
    }



    public void scrollToElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementByCSS(String css) {
        WebElement element = driver.findElement(By.cssSelector(css));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementByID(String id) {
        WebElement element = driver.findElement(By.id(id));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }



    public void waitUntilJSReady() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }



    // MOVE TO A ELEMENT
    public void hoverOnElementByCSS(String css) {
        waitPageLoad();
        WebElement element = driver.findElement(By.cssSelector(css));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    // WAIT METHODS
    public void waitDOM() {
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }


    public void waitPageLoad() {
        waitDOM();
    }


    public void waitClickableWebElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitClickableWebElementById(String id) {
        WebElement element = driver.findElement(By.id(id));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitClickableWebElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void switchToTab(int index) {

            waitForNewWindow(driver, 10);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(0)).close();
            driver.switchTo().window(tabs.get(index));
    }


    public void waitForNewWindow(WebDriver driver, int timeout) {

            boolean flag = false;
            int counter = 0;

            while (!flag) {


                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }

                    sleep(1);

                    counter++;
                    if (counter > timeout) {
                        return;
                    }
            }

    }

    protected void refresh(){
        driver.navigate().refresh();
    }


    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

}
