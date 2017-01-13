package com.pekama.app;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.TestsUrl.*;
import static Page.PekamaLogin.*;
import static Page.TestsUrlConfiguration.TEST_ENVIROMENT_COMMUNITY;
import static Page.TestsUrlConfiguration.TEST_ENVIROMENT_PEKAMA;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by VatslauX on 26-Dec-16.
 */
public class PekamaLogin {
    String testDashboardUrl = "";
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void openUrlLogin() {
        HttpAuth openHost = new HttpAuth();
        String AUTH_URL = urlLogIn;
        openHost.httpAuthWhithCustomLink(AUTH_URL);
    }
    @After
    public void openUrlLogout() {
        open(urlLogout);
    }

    @Test
    public void testEnviroment() {
        open(TEST_ENVIROMENT_PEKAMA);
        rootLogger.info(TEST_ENVIROMENT_PEKAMA+ "opened");
        open(TEST_ENVIROMENT_COMMUNITY);
        rootLogger.info(TEST_ENVIROMENT_COMMUNITY+ "opened");
    }
    @Test
    public void invalidPassword() {
        $(loginField_Email).sendKeys("testqweeco001@gmail.com");
        $(loginField_Password).sendKeys("12345");
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void invalidLogin() {
        $(loginField_Email).sendKeys("1a2a3a12aa31231@gmail.com");
        $(loginField_Password).sendKeys("asui67we34");
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void invalidLoginAndPassword() {
        $(loginField_Email).sendKeys("teastaaaqweeco001@gmail.com");
        $(loginField_Password).sendKeys("asui2132367we34");
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void blankLoginPassword() {
        $(loginField_Email).getAttribute("required");
        assertTrue($(loginField_Email).getAttribute("required"), true);
        //assertThat("Required attribute present", $(loginField_Email).getAttribute("required"), equalTo(true));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());
        $(loginField_Password).getAttribute("required");
        assertTrue($(loginField_Password).getAttribute("required"), true);

        $(loginField_Password).sendKeys("asusdsdsdsi67we34");
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        $(loginField_Password).clear();
        $(loginField_Password).shouldBe(empty);
        rootLogger.info("Login only was submitted");

        $(loginField_Email).sendKeys("testsdsdsds001@gmail.com");
        $(By.xpath(loginButton_Login)).click();
        $(loginField_Email).clear();
        $(loginField_Email).shouldBe(empty);
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        rootLogger.info("Password only was submitted");

        $(loginField_Password).clear();
        $(loginField_Email).clear();
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        rootLogger.info("Blank Credentials were submitted");
    }
    @Test
    public void validCredentials() {
        $(loginField_Email).sendKeys(User1.GMAIL_EMAIL.getValue());
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(1000);
        String testDashboardUrl = url();
        assertEquals(urlDashboard, testDashboardUrl);
        rootLogger.info(url()+"Dashboard is opened");

    }

}
