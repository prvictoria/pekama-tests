package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Page.TestsUrl.*;
import static Page.PekamaLogin.*;
import static Page.TestsUrlConfiguration.TEST_ENVIROMENT_COMMUNITY;
import static Page.TestsUrlConfiguration.TEST_ENVIROMENT_PEKAMA;
import static Page.TestsCredentials.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.submitEnabledButton;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.pekama.app.AllTestsRunner.holdBrowserAfterTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class TestsPekamaLogin {
    String testDashboardUrl = "";
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void openUrlLogin() {
        holdBrowserAfterTest();
        httpAuthUrl(URL_LogIn);
    }
    @After
    public void openUrlLogout() {
        open(URL_Logout);
    }

    @Test
    public void testEnviroment() {
        open(TEST_ENVIROMENT_PEKAMA);
        rootLogger.info(TEST_ENVIROMENT_PEKAMA+" - opened");
        open(TEST_ENVIROMENT_COMMUNITY);
        rootLogger.info(TEST_ENVIROMENT_COMMUNITY+" - opened");
    }
    @Test
    public void invalidPassword() {
        fillField(loginField_Email, "testqweeco001@gmail.com");
        fillField(loginField_Password, "12345");
        submitEnabledButton(loginButton_Login);

        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void invalidLogin() {
        fillField(loginField_Email, "1a2a3a12aa31231@gmail.com");
        fillField(loginField_Password, "asui67we34");
        submitEnabledButton(loginButton_Login);

        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void invalidLoginAndPassword() {
        fillField(loginField_Email, "teastaaaqweeco001@gmail.com");
        fillField(loginField_Password, "asui2132367we34");
        submitEnabledButton(loginButton_Login);

        loginError.shouldHave(Condition.exactText(loginErrorMsg));
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
    }
    @Test
    public void blankLoginPassword() {
        $(loginField_Email).waitUntil(visible, 30000).getAttribute("required");
        assertTrue($(loginField_Email).getAttribute("required"), true);
        loginField_Password.getAttribute("required");
        assertTrue(loginField_Password.getAttribute("required"), true);

        fillField(loginField_Password, "asui2132367we34");
        loginButton_Login.click();
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        loginField_Password.clear();
        loginField_Password.shouldBe(empty);
        rootLogger.info("Login only was submitted");

        fillField(loginField_Email, "teastaaaqweeco001@gmail.com");
        loginButton_Login.click();
        loginField_Email.clear();
        loginField_Email.shouldBe(empty);
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        rootLogger.info("Password only was submitted");

        loginField_Password.clear();
        loginField_Email.clear();
        loginButton_Login.click();
        btnLogin.shouldBe(visible);
        btnSignup.shouldBe(visible);
        rootLogger.info("Blank Credentials were submitted");
    }
    @Test
    public void validCredentials() {
        loginField_Email.waitUntil(visible, 20000).sendKeys(User1.GMAIL_EMAIL.getValue());
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1000);
        String testDashboardUrl = url();
        assertEquals(URL_Dashboard, testDashboardUrl);
        rootLogger.info(url()+"Dashboard is opened");
        open(URL_Logout);
    }
}
