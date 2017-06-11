package Tests.TestsCommunity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.Header.*;
import static Page.NewCommunity.PageAbout.*;
import static Page.NewCommunity.PageLanding.*;
import static Page.NewCommunity.PageSignIn.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Page.PekamaLogin.*;
import static Page.TestsCredentials.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelector;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.holdBrowserAfterTest;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityGuestFlow {
    static final Logger rootLogger = LogManager.getRootLogger();
    @BeforeClass
    public void setUp() throws IOException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        openUrlIfActualNotEquals(JOIN_URL);
        hideZopim();
        submitCookie(10);
    }
    @BeforeMethod
    public void openTarget() throws IOException {
        refresh();
    }

    @Test
    public void joinPageVerifyLoginContent(){
        openUrlIfActualNotEquals(JOIN_URL);
        JOIN_LOGIN_EMAIL.shouldBe(visible).shouldBe(empty);
        JOIN_LOGIN_PASSWORD.shouldBe(visible).shouldBe(empty);
        Assert.assertTrue(JOIN_LOGIN_REMEMBER_ME_VALUE.attr("class").contains("ng-empty"));
        JOIN_LOGIN_REMEMBER_ME.click();
        Assert.assertTrue(JOIN_LOGIN_REMEMBER_ME_VALUE.attr("class").contains("ng-not-empty"));

        JOIN_LOGIN_RESET_PASSWORD.shouldBe(visible);
        JOIN_LOGIN_SUBMIT.shouldBe(visible).shouldBe(enabled);
    }
    @Test
    public void joinPageVerifySignUpContent(){
        openUrlIfActualNotEquals(JOIN_URL);
        JOIN_NAME.shouldBe(visible).shouldBe(empty);
        JOIN_SURNAME.shouldBe(visible).shouldBe(empty);
        JOIN_COMPANY.shouldBe(visible).shouldBe(empty);
        JOIN_EMAIL.shouldBe(visible).shouldBe(empty);
        JOIN_PASSWORD.shouldBe(visible).shouldBe(empty);
        JOIN_SELECT_BUSINESS_TYPE.shouldHave(text(""));
        JOIN_SELECT_ROLE.shouldHave(text(""));

        JOIN_SIGN_UP_SUBMIT.shouldBe(disabled);
        Assert.assertTrue(JOIN_AGREE_TERMS_VALUE.attr("class").contains("ng-empty"));
        JOIN_AGREE_TERMS.click();
        Assert.assertTrue(JOIN_AGREE_TERMS_VALUE.attr("class").contains("ng-not-empty"));

        JOIN_TERMS_LINK.shouldBe(visible);
    }
    @Test
    public void joinPageVerifyResetPasswordContent(){
        openUrlIfActualNotEquals(JOIN_URL);
        JOIN_LOGIN_RESET_PASSWORD.shouldBe(visible).click();
        JOIN_LOGIN_RESET_PASSWORD.shouldNotBe(visible);
        JOIN_RESET_EMAIL.shouldBe(visible).shouldBe(empty);
        JOIN_RESET_BACK.shouldBe(visible).shouldBe(enabled);
        JOIN_RESET_SUBMIT.shouldBe(visible).shouldBe(enabled);

        JOIN_RESET_BACK.click();
        JOIN_LOGIN_RESET_PASSWORD.shouldBe(visible);
        JOIN_RESET_EMAIL.shouldNotBe(visible);
        JOIN_RESET_BACK.shouldNotBe(visible);
        JOIN_RESET_SUBMIT.shouldNotBe(visible);
    }
    @Test
    public void joinPageValidateEmptyResetPassword(){
        openUrlIfActualNotEquals(JOIN_URL);
        clickSelector(JOIN_LOGIN_RESET_PASSWORD);
        clickSelector(JOIN_RESET_SUBMIT);
        checkText(ERROR_MSG_BLANK_FIELD);
    }
    @Test
    public void aboutPageVerifyContent1(){
        clickSelector(HEADER_TAB_ABOUT);
        ABOUT_BUTTON1_START.shouldBe(visible);
        ABOUT_BUTTON2_START.shouldBe(visible);
        clickSelector(ABOUT_BUTTON1_START);
        Assert.assertEquals(LANDING_URL, getActualUrl());
    }
    @Test
    public void aboutPageVerifyContent2(){
        clickSelector(HEADER_TAB_ABOUT);
        ABOUT_BUTTON1_START.shouldBe(visible);
        ABOUT_BUTTON2_START.shouldBe(visible);
        clickSelector(ABOUT_BUTTON2_START);
        Assert.assertEquals(LANDING_URL, getActualUrl());
    }

}


