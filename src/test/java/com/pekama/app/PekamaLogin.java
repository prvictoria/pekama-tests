package com.pekama.app;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.PekamaLogin.*;
import static Page.RunConfig.*;
import static Page.TestData.USER_EMAIL_01;
import static Page.TestData.USER_PEKAMA_PASSWORD;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.thoughtworks.selenium.SeleneseTestNgHelper.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * Created by VatslauX on 26-Dec-16.
 */
public class PekamaLogin {
    static final Logger rootLogger = LogManager.getRootLogger();


    @Before
    public void openUrlLogin() {
        open(urlLogin);
    }
    @After
    public void openUrlLogout() {
        open(urlLogout);
    }
//    @Test
//    public void template() {
//        open(login);
//        $("").sendKeys("");
//        $(By.xpath("")).sendKeys("");
//        $(By.xpath("")).click();
//    }
//    @Test
//    public void openLogin() {
//        open(host);
//        //open("http://.pekama.com");
//
//        //open("http://qweeco:qw33coStudi0@staging.pekama.com");
//        //switchTo().alert().authenticateUsing("qweeco", login);
//        //open("http://www.staging.pekama.com\\qweeco:qw33coStudi0@staging.pekama.com");
//        //open("https://staging.pekama.com", "www.staging.pekama.com", "qweeco" , "qw33coStudi0");
//    };
    @Test
    public void testEnviroment() {
        open(TEST_ENVIROMENT_PEKAMA);
        open(TEST_ENVIROMENT_COMMUNITY);
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

        //Case login only
        $(loginField_Password).sendKeys("asusdsdsdsi67we34");
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        $(loginField_Password).clear();
        $(loginField_Password).shouldBe(empty);
        //Case password only
        $(loginField_Email).sendKeys("testsdsdsds001@gmail.com");
        $(By.xpath(loginButton_Login)).click();
        $(loginField_Email).clear();
        $(loginField_Email).shouldBe(empty);
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        //Case all blank
        $(loginField_Password).clear();
        $(loginField_Email).clear();
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);

    }
    @Test
    public void validCredentials() {
        $(loginField_Email).sendKeys(USER_EMAIL_01);
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        String testUrl = url();

        rootLogger.info(url()+"Dashboard is opened");

    }

}
