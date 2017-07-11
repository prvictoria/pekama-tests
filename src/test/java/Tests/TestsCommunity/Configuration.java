package Tests.TestsCommunity;

import Pages.UrlConfiguration;
import Tests.BrowserConfiguration;
import org.testng.annotations.*;

import java.io.IOException;

import static Tests.BrowserConfiguration.SelectBrowsers.MARIONETTE;
import static Tests.BrowserConfiguration.SelectPathToDriver.WIN;


public class Configuration {

    @BeforeClass
    public void setUp() throws IOException {
        new UrlConfiguration().setEnvironment (2);
        new BrowserConfiguration().setBrowser(MARIONETTE, WIN, false);

    }
    @AfterClass
    public void clean(){}

    @BeforeMethod
    public void beforeTest(){}

    @AfterTest
    public void closeBrowser(){}

}
