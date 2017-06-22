package Tests.TestsCommunity;

import Page.UrlConfig;
import Tests.BrowserConfiguration;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static Tests.BrowserConfiguration.SelectBrowsers.EDGE;
import static Tests.BrowserConfiguration.SelectBrowsers.MARIONETTE;
import static Tests.BrowserConfiguration.SelectPathToDriver.WIN;

/**
 * Created by VatslauX on 19-Jun-17.
 */
public class Configuration {

    @BeforeClass
    public void setUp() throws IOException {
        UrlConfig.setEnvironment (2);
        new BrowserConfiguration().setBrowser(MARIONETTE, WIN, false);
        return;
    }

}
