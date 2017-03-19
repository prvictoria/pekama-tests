package Tests;

import com.codeborne.selenide.Configuration;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;
import static org.openqa.selenium.logging.LogType.BROWSER;
import static org.openqa.selenium.remote.BrowserType.EDGE;
import static org.openqa.selenium.remote.CapabilityType.LOGGING_PREFS;

/**
 * Created by Viachaslau_Balashevi on 3/8/2017.
 */
public class BeforeTestsSetUp {
    static final Logger rootLogger = LogManager.getRootLogger();

    public static boolean localDriverPath = true;
    public static int testBrowser = 2;
    public static void setBrowser() throws IOException {
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
//                DesiredCapabilities cap = new FirefoxOptions().setLogLevel(Level.OFF).addTo(DesiredCapabilities.firefox());
//                LoggingPreferences prefs = new LoggingPreferences();
//                prefs.enable(BROWSER, Level.WARNING);
//                cap.setCapability(LOGGING_PREFS, prefs);
                browser = MARIONETTE;
                startMaximized = true;
                if (localDriverPath == true){
                    setFirefoxDriverPath();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPath == false){
                    startMaximized = false;
                    FirefoxDriverManager.getInstance().setup();
                }
//                System.out.println(getWebDriver().manage().logs());
                //getWebDriver().manage();
                //getWebDriverLogs(BROWSER, WARNING);
                //setWebDriver(getWebDriverLogs(BROWSER, WARNING));
                //executeJavaScript("console.info()");
                rootLogger.info("Tests will performed in Firefox");
                break;
            case 3:
                browser = INTERNET_EXPLORER;
                startMaximized = false;
                InternetExplorerDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in IE");
                break;
            case 4:
                browser = FIREFOX;
                String firefox_binary_path = "C:\\FirefoxPortable\\FirefoxPortable.exe";
                DesiredCapabilities c = DesiredCapabilities.firefox();
                c.setCapability("firefox_binary", firefox_binary_path);
                c.setCapability("version", "46.0.1");

                if (localDriverPath == true){
                    startMaximized = true;
                    setFirefoxDriverPath();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPath == false){
                    startMaximized = false;
                    FirefoxDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in FF46");
            case 5:
                rootLogger.info("Empty config");
                break;
        }
    }
    public static String setChromeDriverPath() {
        String chromeDriverPath = "src/test/lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return chromeDriverPath;
    }
    public static String setFirefoxDriverPath() {
        String ffDriverPath = "src/test/lib/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        return ffDriverPath;
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
