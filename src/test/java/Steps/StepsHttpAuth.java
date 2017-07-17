package Steps;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;

import static Tests.BeforeTestsSetUp.testBrowser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsHttpAuth {
   private static final Logger rootLogger = LogManager.getRootLogger();
   public static void openUrlWithBaseAuth(String url) {
        try{
            if(testBrowser==1){
                getWebDriver().manage().window().maximize();
                Selenide.open(url,
                        "",
                        "qweeco",
                        "qw33coStudi0");
                sleep(250);
                rootLogger.info(url+" URL opened");
            }
            if(testBrowser==2) {
                Selenide.open(url,
                        "",
                        "qweeco",
                        "qw33coStudi0");
                sleep(250);
                rootLogger.info(url + " URL opened");
            }
        }
        catch (TimeoutException exception){
             rootLogger.info("Timeout error");
             refresh();
             sleep(5000);
                if(testBrowser==1){
                    getWebDriver().manage().window().maximize();
                    Selenide.open(url,
                            "",
                            "qweeco",
                            "qw33coStudi0");
                    sleep(250);
                    rootLogger.info(url+" URL opened");
                }
                if(testBrowser==2) {
                    Selenide.open(url,
                            "",
                            "qweeco",
                            "qw33coStudi0");
                    sleep(250);
                    rootLogger.info(url + " URL opened");
                }
        }
   }
}
