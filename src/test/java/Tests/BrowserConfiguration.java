package Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by VatslauX on 22-Jun-17.
 */
public class BrowserConfiguration {

    static final Logger rootLogger = LogManager.getRootLogger();
    //TODO Victory phantom path
    public static String setPhantomDriverPath() {
        String path = "src/test/resources/binary/";
        System.setProperty("..driver", path);
        rootLogger.info("Local driver path is selected: "+path);
        return path;
    }
    public static String setChromeDriverPath() {
        String path = "src/test/resources/binary/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        rootLogger.info("Local driver path is selected: "+path);
        return path;
    }
    public static String setFirefoxDriverPathWin() {
        String path = "src/test/resources/binary/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", path);
        rootLogger.info("Windows Local driver path is selected: "+path);
        return path;
    }
    public static String setEdgeDriverPathWin() {
        String path = "src/test/resources/binary/MicrosoftWebDriver14393.exe";
        System.setProperty("webdriver.edge.driver", path);
        rootLogger.info("Windows Local driver path is selected:"+path );
        return path;
    }
    public static String setFirefoxDriverPathLinux() {
        String path = "src/test/resources/binary/geckodriver";
        System.setProperty("webdriver.gecko.driver", path);
        rootLogger.info("Linux Local driver path is selected");
        return path;
    }
    public static String setPhantomjsDriver() {
        String path = "src/test/resources/binary/phantomjs.exe";
        System.setProperty("phantomjs.binary.path", path);
        rootLogger.info("Windows Local driver path is selected: "+path);
        return path;
    }
    public static void holdBrowserAfterTest() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = false;
    }
    public static void holdBrowserAfterTest(boolean value) {
        Configuration test = new Configuration();
        test.holdBrowserOpen = value;
    }
    public void setRemoteDriver(){
        RemoteWebDriver remote = (RemoteWebDriver)
                getWebDriver();
        WebDriverRunner.setWebDriver(remote);
        remote.manage().window().maximize();
        return;
    }
    public void maximizeBrowser(){
        WebDriver driver = getWebDriver();
        driver.manage().window().maximize();
    }
    public void browserCapabilities(){

    }

    public static WebDriver  setChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    public enum SelectPathToDriver {WIN, LINUX, WEB}
    public enum SelectBrowsers {CHROME, PHANTOMJS, MARIONETTE, EDGE}
    public BrowserConfiguration setBrowser(SelectBrowsers browsers, SelectPathToDriver pathToDriver, boolean holdBrowserAfterTest) throws IOException {
        holdBrowserAfterTest(holdBrowserAfterTest);
        switch (browsers) {
            case CHROME:
                browser = WebDriverRunner.CHROME;
                switch (pathToDriver){
                    case WIN:
                        setChromeDriverPath();
                        setWebDriver(setChromeOptions());
                        //Todo close after tests?
                        break;
                    case WEB:
                        ChromeDriverManager.getInstance().setup();
                        break;
                    case LINUX:
                        break;
                    }
                rootLogger.info("Tests will performed in Chrome");
                break;

            case PHANTOMJS:
                browser = WebDriverRunner.PHANTOMJS;
                switch (pathToDriver) {
                    case WIN:
                        setPhantomjsDriver();
                        break;
                    case WEB:
                        PhantomJsDriverManager.getInstance().setup();
                        break;
                    }
                rootLogger.info("Tests will performed in PhantomJS");
                break;

            case MARIONETTE:
                browser = WebDriverRunner.MARIONETTE;
                startMaximized = true;
                switch (pathToDriver) {
                    case WIN:
                        setFirefoxDriverPathWin();
                        break;
                    case WEB:
                        startMaximized = false;
                        FirefoxDriverManager.getInstance().setup();
                        break;
                    case LINUX:
                        setFirefoxDriverPathLinux();
                        break;
                }
                rootLogger.info("Tests will performed in New Firefox ");
                break;

            case EDGE:
                browser = WebDriverRunner.EDGE;
                startMaximized = true;
                switch (pathToDriver) {
                    case WIN:
                        setEdgeDriverPathWin();
                        break;
                    case WEB:
                        EdgeDriverManager.getInstance().setup();
                        break;
                }
                rootLogger.info("Tests will performed in EDGE");
                break;
        }
        return this;
    }

}
