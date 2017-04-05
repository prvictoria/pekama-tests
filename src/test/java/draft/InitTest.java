package draft;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by VatslauX on 16-Mar-17.
 */
public class InitTest {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Ignore
    @Test
    public void newTest1(){
        browser = CHROME;
        startMaximized = false;
        ChromeDriverManager.getInstance().setup();
        getWebDriver().manage().window().maximize();
        openUrlWithBaseAuth("https://mvnrepository.com");
        //open("https://mvnrepository.com");
    }
    @Ignore
    @Test
    public void newTest2(){
        browser = MARIONETTE;
        startMaximized = true;
        FirefoxDriverManager.getInstance().setup();
        //getWebDriver().manage().window().maximize();
        open("https://mvnrepository.com");
    }
}
