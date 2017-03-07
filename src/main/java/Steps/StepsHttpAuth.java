package Steps;
import Steps.StepsFactory;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.Test;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assume.assumeTrue;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsHttpAuth implements StepsFactory {
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
    public static void openUrlWithBaseAuth(String AUTH_URL) {
        if(isChrome());
        {
        //    ChromeDriverManager.getInstance().setup();
            Selenide.open(AUTH_URL,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            getWebDriver().manage().window().maximize();
        }
        if(isMarionette());
        {
            //FirefoxDriverManager.getInstance().setup();
            Selenide.open(AUTH_URL,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            getWebDriver().manage().window().maximize();
        }
        if(isIE());
        {
            //InternetExplorerDriverManager.getInstance().setup();
            Selenide.open(AUTH_URL,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            getWebDriver().manage().window().maximize();
        }
        if(isEdge());
        {
            //EdgeDriverManager.getInstance().setup();
            Selenide.open(AUTH_URL,
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(250);
            getWebDriver().manage().window().maximize();
        }

    }
}
