package Steps;
import Steps.StepsFactory;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.util.logging.resources.logging;

import java.sql.DriverManager;
import java.util.logging.Level;

import static Tests.BeforeTestsSetUp.testBrowser;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.OFF;
import static java.util.logging.Level.WARNING;
import static org.junit.Assume.assumeTrue;
import static org.openqa.selenium.logging.LogType.BROWSER;
import static org.openqa.selenium.remote.CapabilityType.LOGGING_PREFS;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsHttpAuth implements StepsFactory {
    static final Logger rootLogger = LogManager.getRootLogger();
    public void httpAuthStagingPekama() {
        //assumeTrue(isFirefox());
        Selenide.open("https://staging.pekama.com/",
                "",
                "qweeco",
                "qw33coStudi0");
        sleep(250);

   }
   public static void httpAuthStagingCommunity() {
        //assumeTrue(isFirefox());
        Selenide.open("https://communitystaging.pekama.com/",
                "",
                "qweeco",
                "qw33coStudi0");
        sleep(250);

   }
    public static void openUrlWithBaseAuth(String url) {
        if(testBrowser==1){
            getWebDriver().manage().window().maximize();
            Selenide.open(url,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            rootLogger.info(url+" URL opened");
        }
        if(testBrowser==2){
        Selenide.open(url,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            rootLogger.info(url+" URL opened");
        }
    }
}
