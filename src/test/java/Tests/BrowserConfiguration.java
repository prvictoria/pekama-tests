package Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;

/**
 * Created by VatslauX on 22-Jun-17.
 */
public class BrowserConfiguration {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static String setChromeDriverPath() {
        String chromeDriverPath = "src/test/lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        rootLogger.info("Local driver path is selected");
        return chromeDriverPath;
    }
    public static String setFirefoxDriverPathWin() {
        String ffDriverPath = "src/test/lib/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        rootLogger.info("Windows Local driver path is selected");
        return ffDriverPath;
    }
    public static String setEdgeDriverPathWin() {
        String path = "src/test/lib/MicrosoftWebDriver14393.exe";
        System.setProperty("webdriver.edge.driver", path);
        rootLogger.info("Windows Local driver path is selected");
        return path;
    }
    public static String setFirefoxDriverPathLinux() {
        String ffDriverPath = "src/test/lib/geckodriver";
        System.setProperty("webdriver.gecko.driver", ffDriverPath);
        rootLogger.info("Linux Local driver path is selected");
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

    public enum SelectPathToDriver {WIN, LINUX, WEB}
    public enum SelectBrowsers {CHROME, MARIONETTE, EDGE}
    public BrowserConfiguration setBrowser(SelectBrowsers browsers, SelectPathToDriver pathToDriver, boolean holdBrowserAfterTest) throws IOException {
        holdBrowserAfterTest(holdBrowserAfterTest);
        switch (browsers) {
            case CHROME:
                browser = WebDriverRunner.CHROME;
                switch (pathToDriver){
                    case WIN:
                        setChromeDriverPath();
                        break;
                    case WEB:
                        startMaximized = false;
                        ChromeDriverManager.getInstance().setup();
                        break;
                    case LINUX:
                        break;
                    }
                rootLogger.info("Tests will performed in Chrome");
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
