package Tests.TestsCommunity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageSignIn.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityLogin {
    static final Logger rootLogger = LogManager.getRootLogger();
    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }

    @Test
    public void loginPageOpen(){
        openUrlIfActualNotEquals(JOIN_URL);
    }
}


