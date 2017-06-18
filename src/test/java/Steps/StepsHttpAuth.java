package Steps;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Tests.BeforeTestsSetUp.testBrowser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assume.assumeTrue;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsHttpAuth {
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
