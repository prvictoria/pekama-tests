package com.pekama.app;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;

/**
 * Created by Viachaslau_Balashevi on 3/8/2017.
 */
public class BeforeTestsSetUp {
    static final Logger rootLogger = LogManager.getRootLogger();

    public static boolean localDriverPath = false;
    public static int testBrowser = 5;
    public static void setBrowser() {
        switch (testBrowser) {
            case 1:
                browser = CHROME;
                if (localDriverPath == true){
                    setChromeDriverPath();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPath == false){
                    startMaximized = false;
                    ChromeDriverManager.getInstance().setup();
                }
//                browserSize = "1700x1000";
//                getWebDriver().manage().window().setPosition(new Point(0, 0));
//                getWebDriver().manage().window().setSize(new Dimension(1800, 1000));
//                System.out.print("position " + getWebDriver().manage().window().getPosition());
//                System.out.print("size " + getWebDriver().manage().window().getSize());
                rootLogger.info("Tests will performed in Chrome");
                break;

            case 2:
                //setFirefoxDriverPath();
                browser = MARIONETTE;
                startMaximized = true;
                if (localDriverPath == true){
                    setFirefoxDriverPath();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPath == false){
                    startMaximized = false;
                    FirefoxDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in Firefox");
                break;

            case 3:
                browser = INTERNET_EXPLORER;
                startMaximized = false;
                InternetExplorerDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in IE");
                break;

            case 4:
                browser = EDGE;
                startMaximized = false;
                EdgeDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in EDGE");
                break;

            case 5:
                browser = FIREFOX;
//                String firefox_binary_path = "C:\\FirefoxPortable\\FirefoxPortable.exe";
//                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//                capabilities.setCapability("firefox_binary", firefox_binary_path);
//                capabilities.setCapability("version", "46.0.1");

//                if (localDriverPath == true){
//                    startMaximized = true;
//                    setFirefoxDriverPath();
//                    rootLogger.info("Local driver path is selected");}
//                if (localDriverPath == false){
//                    startMaximized = false;
//                    FirefoxDriverManager.getInstance().setup();
//                }
                rootLogger.info("Tests will performed in FF46");
                break;
        }
    }
    public static void setChromeDriverPath() {
        String chromeDriverPath = "C:\\Users\\Viachaslau_Balashevi\\IdeaProjects\\pekama-tests\\src\\lib\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }
    public static void setFirefoxDriverPath() {
        String ffDriverPath = "C:\\Users\\Viachaslau_Balashevi\\IdeaProjects\\pekama-tests\\src\\lib\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
    }
    public static void holdBrowserAfterTest() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = false;
    }
    public static void holdBrowserAfterTest(boolean value) {
        Configuration test = new Configuration();
        test.holdBrowserOpen = value;
    }
}
