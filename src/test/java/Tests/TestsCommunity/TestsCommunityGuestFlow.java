package Tests.TestsCommunity;

import Page.NewCommunity.PageJoin;
import Steps.ObjectUser;
import org.apache.logging.log4j.*;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static Page.NewCommunity.PageAbout.*;
import static Page.NewCommunity.PageLanding.*;
import static Page.NewCommunity.PageJoin.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.newBuilder;
import static Steps.Steps.clickSelector;
import static Steps.StepsPekama.*;
import static Tests.BeforeTestsSetUp.*;
import static Tests.BeforeTestsSetUp.setBrowser;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 11-Jun-17.
 */
public class TestsCommunityGuestFlow extends Configuration{
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final ObjectUser registeredUser = new ObjectUser(newBuilder()).buildUser(USER_01);
    private static final ObjectUser newUser = null;
    private static final ObjectUser forgottenPasswordUser = new ObjectUser(newBuilder()).buildUser(USER_04);;
    private PageJoin pageJoin;

    @BeforeMethod
    public void openTarget() {
        pageJoin = new PageJoin();
        openUrlIfActualNotEquals(JOIN_URL);
        hideZopim();
        submitCookie(10);

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
        pageJoin.selectAgreeTerms();
        JOIN_SIGN_UP_SUBMIT.shouldBe(enabled);

        JOIN_TERMS_LINK.shouldBe(visible);
    }
    @Test
    public void joinOpenTerms(){
        openUrlIfActualNotEquals("https://staging.pekama.com/about/terms/");
        openUrlIfActualNotEquals(JOIN_URL);
        clickSelector(JOIN_TERMS_LINK);
        sleep(4000);
        switchToTermsWindow();
        Assert.assertEquals(getActualUrl(), "https://staging.pekama.com/about/terms/");
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
    public void aboutPageVerifyContent1(){
        openUrlIfActualNotEquals(LANDING_URL);
        pageJoin.clickAboutTab();
        ABOUT_BUTTON1_START.shouldBe(visible);
        ABOUT_BUTTON2_START.shouldBe(visible);
        clickSelector(ABOUT_BUTTON1_START);
        Assert.assertEquals("https://communitystaging.pekama.com/grow_network", getActualUrl());
    }
    @Test
    public void aboutPageVerifyContent2(){
        openUrlIfActualNotEquals(ABOUT_URL);
        ABOUT_BUTTON1_START.shouldBe(visible);
        ABOUT_BUTTON2_START.shouldBe(visible);
        clickSelector(ABOUT_BUTTON2_START);
        Assert.assertEquals("https://communitystaging.pekama.com/grow_network", getActualUrl());
    }

    @Test
    public void joinPageEmptyResetPasswordValidate(){
        ObjectUser user = newBuilder().email(null).build();
        openUrlIfActualNotEquals(LANDING_URL);
        pageJoin.clickSingInTab();
        pageJoin.submitResetPassword(user);
        pageJoin.validateSubmitResetPassword(false, ERROR_MSG_BLANK_FIELD);
    }
    @Test
    public void joinPageResetPasswordFakeEmail(){
        ObjectUser user = newBuilder().email("123456@email.com").build();
        openUrlIfActualNotEquals(LANDING_URL);
        pageJoin.clickSingInTab();
        pageJoin.submitResetPassword(user);
        pageJoin.validateSubmitResetPassword(true, null);
    }
    @Test
    public void joinPageOpenBlog(){
        openUrlIfActualNotEquals(JOIN_URL);
        pageJoin.clickBlogTab().switchToBlog();
    }
}


