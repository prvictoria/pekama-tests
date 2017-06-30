package Tests.TestsCommunity;

import Page.UrlConfig;
import Tests.BrowserConfiguration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.IOException;

import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Tests.BrowserConfiguration.SelectBrowsers.CHROME;
import static Tests.BrowserConfiguration.SelectBrowsers.EDGE;
import static Tests.BrowserConfiguration.SelectBrowsers.MARIONETTE;
import static Tests.BrowserConfiguration.SelectPathToDriver.WEB;
import static Tests.BrowserConfiguration.SelectPathToDriver.WIN;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.WebDriver.*;
import static org.openqa.selenium.logging.LogType.BROWSER;

/**
 * Created by VatslauX on 19-Jun-17.
 */
public class Configuration {

    @BeforeClass
    public void setUp() throws IOException {
        UrlConfig.setEnvironment (2);
        new BrowserConfiguration().setBrowser(MARIONETTE, WIN, false);

    }
    @AfterClass
    public void clean(){}

    @BeforeMethod
    public void beforeTest(){}

    @AfterTest
    public void closeBrowser(){}

}
