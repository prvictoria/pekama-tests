package Tests.TestsCommunity;

import Pages.UrlConfiguration;
import Tests.BrowserConfiguration;
import org.testng.annotations.*;

import java.io.IOException;

import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BrowserConfiguration.SelectBrowsers.MARIONETTE;
import static Tests.BrowserConfiguration.SelectBrowsers.PHANTOMJS;
import static Tests.BrowserConfiguration.SelectPathToDriver.WEB;
import static Tests.BrowserConfiguration.SelectPathToDriver.WIN;


public class Configuration {

    @BeforeClass
    public void setUp() throws IOException {
        new UrlConfiguration().setEnvironment (2);
        new BrowserConfiguration().setBrowser(PHANTOMJS, WIN, false);
        holdBrowserAfterTest();
    }
    @AfterClass
    public void clean(){}

    @BeforeMethod
    public void beforeTest(){}

    @AfterTest
    public void closeBrowser(){}

}
