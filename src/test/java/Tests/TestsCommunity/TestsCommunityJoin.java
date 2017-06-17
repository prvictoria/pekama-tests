package Tests.TestsCommunity;

import Page.NewCommunity.PageJoin;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageJoin.*;
import static Page.UrlConfig.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityJoin {
    static final Logger rootLogger = LogManager.getRootLogger();
    private PageJoin pageJoin;
    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
    }
    @BeforeMethod
    public void openTarget() {
        pageJoin = new PageJoin();
        openUrlIfActualNotEquals(JOIN_URL);
    }
    @Test
    public void joinValidate(){
        ObjectUser user = newBuilder().build();
        pageJoin.submitSignUp(user);
        Assert.assertFalse(user.isSignUpSucceed);
        checkText("This field may not be blank.", 5);
        checkText("This field is required.", 2);
    }

}


