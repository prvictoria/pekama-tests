package com.pekama.app.draft;

import com.codeborne.selenide.Selenide;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.codeborne.selenide.Selenide.open;
import static com.pekama.app.BeforeTestsSetUp.setBrowser;

/**
 * Created by VatslauX on 26-Dec-16.
 */
public class TestGoogle {
    @Test
    public void TestGoogle() {
        //Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        open("http://wpceb2015.esy.es/");
        Selenide.$(By.xpath("//*[@el='Login']")).click();
        Selenide.$(By.xpath("//*[@data-type='twitter']")).click();
        Selenide.switchTo().window("Twitter / Authorize an application");
        Selenide.$(By.id("username_or_email")).val("test2014ceb@gmail.com").click();
    }
    @Test
    public void date(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        System.out.println("Current Date Time : " + datetime);
    }
    @Test
    public void getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //2017-01-10 need- 2017/01/10
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        System.out.println("Current Date Time : " + datetime);
    }
    @Test
    public void firefoxTest()
    {
        String ffDriverPath = "C:\\Users\\Viachaslau_Balashevi\\IdeaProjects\\pekama-tests\\src\\lib\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.facebook.com");
        System.out.println("Title———–"+driver.getTitle());
        driver.quit();
    }
    @Test
    public void firefoxTest2x()
    {
        String ffDriverPath = "C:\\Users\\Viachaslau_Balashevi\\IdeaProjects\\pekama-tests\\src\\lib\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.facebook.com");
        System.out.println("Title———–"+driver.getTitle());
        try {

            Thread.sleep(300);

        } catch (InterruptedException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }driver.quit();
    }
    WebDriver driver;

    @Test
    public void geckoDriverIssue() {

        try {
            FirefoxProfile fp = new FirefoxProfile();
            DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
            firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, fp);
            firefoxCapabilities.setCapability("marionette", true);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);

            String webSite = "http://www.cbsnews.com/";
            driver.get(webSite);

        } catch (Exception e) {
        } finally {
            driver.quit();
        }
    }
    @Test
    public void firefoxTest11()
    {   setBrowser();
        open("http://google.com");
    }
}
