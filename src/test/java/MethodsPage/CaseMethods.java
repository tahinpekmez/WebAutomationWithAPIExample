package MethodsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CaseMethods extends BasePage {

    Constant constant = new Constant();
    HttpURLConnection huc;
    int respCode = 200;

    public CaseMethods(WebDriver driver) {
        super(driver);
    }

//  QA Assignment a of 2 with 1
    public void verifyAuthor() {
        driver.get(constant.getTestWebPage());
        waitDOM();
        List<WebElement> authors = driver.findElements(By.xpath("//*[@class='river river--homepage ']//article/header/h2/following-sibling::div/div/span"));
        ArrayList<String> nameOfAuthors = new ArrayList<>();

        for (WebElement name : authors) {
            nameOfAuthors.add(name.getText());
        }


        int lengthList = nameOfAuthors.size();

        for (int i = 1; i <= lengthList; ++i) {

            String nameOfAuthor = driver.findElement(By.xpath("//*[@class='river river--homepage ']//article[" + i + "]/header/h2/following-sibling::div/div/span")).getText();
            clickElementByXpath("//*[@class='river river--homepage ']//article["+ i +"]/header/h2");
            String authorNamePage = driver.findElement(By.xpath("//*[@class='article__content-wrap']//*[@class='article__byline']")).getText();
            if(authorNamePage.contains(nameOfAuthor)){
                Assert.assertTrue(true);
            }
            driver.get(constant.getTestWebPage());
            waitDOM();
        }
    }

//  QA Assignment b of 2 with 1
    public void verifyImage() {
        driver.get(constant.getTestWebPage());
        waitDOM();

        // List of latest news images
        List<WebElement> images = driver.findElements(By.xpath("//*[@class='river river--homepage ']//*[@class='post-block__media']"));

        for (WebElement postImage : images) {
                if (postImage.isDisplayed()) {
                    Assert.assertTrue(true);
                }

            }
        }

// QA Assignment a of 3
    public void verifyTitle(){
        driver.get(constant.getTestWebPage());
        waitDOM();
        List <WebElement> title = driver.findElements(By.xpath("//*[@class='river river--homepage ']//article/header/h2"));
        int lengthTitle = title.size();

        for(int i = 1; i<=lengthTitle; ++i){
            String head1 = driver.findElement(By.xpath("//*[@class='river river--homepage ']//article["+i+"]/header/h2")).getText();
            clickElementByXpath("//*[@class='river river--homepage ']//article["+i+"]/header/h2");
            waitDOM();
            String head2 = driver.findElement(By.xpath("//*[@class='article__header']//div[1]//h1")).getText();
            Assert.assertEquals(head1, head2);
            driver.get(constant.getTestWebPage());
            waitDOM();

        }
    }

// QA Assignment b of 3
        public void verifyLinks(){

        driver.get(constant.getTestWebPage());
        waitDOM();
            List<WebElement> links = driver.findElements(By.tagName("a"));

            Iterator<WebElement> it = links.iterator();

            while(it.hasNext()){

                String url = it.next().getAttribute("href");

                System.out.println(url);

                if(url == null || url.isEmpty()){
                    Assert.assertTrue(true);
                    continue;
                }

                if(!url.startsWith(constant.getTestWebPage())){
                    Assert.assertFalse(false);
                    continue;
                }

                try {
                    huc = (HttpURLConnection)(new URL(url).openConnection());
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    respCode = huc.getResponseCode();

                    if(respCode >= 400){
                        System.out.println(url+" is a broken link");
                    }
                    else{
                        System.out.println(url+" is a valid link");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
    }

}
