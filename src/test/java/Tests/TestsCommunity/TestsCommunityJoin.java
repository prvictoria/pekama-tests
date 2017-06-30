package Tests.TestsCommunity;

import Page.NewCommunity.PageJoin;
import Steps.MessagesIMAP;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageJoin.*;
import static Page.UrlConfig.*;
import static Steps.ObjectUser.Users.USER_05;
import static Steps.ObjectUser.newBuilder;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityJoin extends Configuration{
    static final Logger rootLogger = LogManager.getRootLogger();
    ObjectUser invited;
    private PageJoin pageJoin;
    static boolean skipBefore;

    @BeforeMethod
    public void openTarget() {
        if (skipBefore==false) {
            pageJoin = new PageJoin();
            openUrlIfActualNotEquals(JOIN_URL);
            hideZopim();
            submitCookie(10);
            refresh();
        }
    }
    @Test (priority = 100)
    public void joinValidate(){
        ObjectUser user = newBuilder().build();
        pageJoin.submitSignUp(user);
        Assert.assertFalse(user.isSignUpSucceed);
        checkText("This field may not be blank.", 6);
        checkText("This field is required.", 3);
    }
    @Test (priority = 200)
    public void joinSuccess(){
        skipBefore = true;
        invited = new ObjectUser(newBuilder()).buildUser(USER_05);
        pageJoin.submitSignUp(invited);
        Assert.assertTrue(invited.isSignUpSucceed);
    }
    @Test (priority = 201, dependsOnMethods = { "joinSuccess" })
    public void joinGetEmail() {
        skipBefore = false;
        invited = new ObjectUser(newBuilder()).buildUser(USER_05);
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailSignUp(invited.email, invited.passwordEmail);
        Assert.assertTrue(validationResult);
        rootLogger.info("Test passed");
    }
}


