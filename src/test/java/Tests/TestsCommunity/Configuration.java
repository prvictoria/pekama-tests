package Tests.TestsCommunity;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static Page.UrlConfig.setEnvironment;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;

/**
 * Created by VatslauX on 19-Jun-17.
 */
public class Configuration {

    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment(2);
        setBrowser();
        holdBrowserAfterTest();
    }

}
