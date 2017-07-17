package Tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;

/**
 * Created by Viachaslau_Balashevi on 3/8/2017.
 *
 * Disable FF save mode
 *hello, you could try this - enter about:config into the firefox location bar (confirm the info message in case it shows up) & search for the preference named toolkit.startup.max_resumed_crashes. double-click it and change its value to something very high like 999999 (maybe setting it to 0 or -1 will also work, however i haven't tried that myself).
 *
 */
@Deprecated
public class BeforeTestsSetUp {
    static final Logger rootLogger = LogManager.getRootLogger();

    public static boolean localDriverPathWindows = true;
    public static boolean localDriverPathLinux = false;
    public static boolean localDriverPathWeb = false;
    public enum PathToDriver {WIN, LINUX, WEB}
    public static int testBrowser = 2;
    public static void setBrowser() throws IOException {
        switch (testBrowser) {
            case 1:
                browser = CHROME;
                if (localDriverPathWindows == true){
                    setChromeDriverPath();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPathWeb == true){
                    startMaximized = false;
                    ChromeDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in Chrome");
                break;
            case 2:
                browser = PHANTOMJS;
                startMaximized = true;
                if (localDriverPathLinux == true){
                    setFirefoxDriverPathLinux();
                    rootLogger.info("Linux Local driver path is selected");}
                if (localDriverPathWindows == true){
                    setFirefoxDriverPathWin();
                    rootLogger.info("Windows Local driver path is selected");}
                if (localDriverPathWeb == true){
                    startMaximized = false;
                    PhantomJsDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in PhantomJS");
                break;
            case 3:
                browser = MARIONETTE;
                startMaximized = true;
                if (localDriverPathLinux == true){
                    setFirefoxDriverPathLinux();
                    rootLogger.info("Linux Local driver path is selected");}
                if (localDriverPathWindows == true){
                    setFirefoxDriverPathWin();
                    rootLogger.info("Windows Local driver path is selected");}
                if (localDriverPathWeb == true){
                    startMaximized = false;
                    FirefoxDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in Firefox");
                break;
            case 4:
                browser = INTERNET_EXPLORER;
                startMaximized = false;
                InternetExplorerDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in IE");
                break;
            case 5:
                browser = FIREFOX;
                String firefox_binary_path = "C:\\FirefoxPortable\\FirefoxPortable.exe";
                DesiredCapabilities c = DesiredCapabilities.firefox();
                c.setCapability("firefox_binary", firefox_binary_path);
                c.setCapability("version", "46.0.1");

                if (localDriverPathWindows == true){
                    startMaximized = true;
                    setFirefoxDriverPathWin();
                    rootLogger.info("Local driver path is selected");}
                if (localDriverPathWindows == false){
                    startMaximized = false;
                    FirefoxDriverManager.getInstance().setup();
                }
                rootLogger.info("Tests will performed in FF46");
            case 6:
                rootLogger.info("Empty config");
                break;
        }
    }
    public static String setChromeDriverPath() {
        String chromeDriverPath = "src/test/resources/binary/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return chromeDriverPath;
    }
    public static String setFirefoxDriverPathWin() {
        String ffDriverPath = "src/test/resources/binary/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        return ffDriverPath;
    }
    public static String setFirefoxDriverPathLinux() {
        String ffDriverPath = "src/test/resources/binary/geckodriver";
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
