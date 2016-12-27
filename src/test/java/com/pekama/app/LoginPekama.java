package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by VatslauX on 26-Dec-16.
 */


public class LoginPekama {
    public static final String hostEmail = "qweeco";
    public static final String hostPassword = "qw33coStudi0";
    public static final String hostProduction = "https://docketing.pekama.com";
    public static final String hostStaging = "http://qweeco:qw33coStudi0@staging.pekama.com";
    public static final String host = hostProduction;
    public static final String urlLogin = host+"/accounts/login/";
    public static final String urlLogout = host+"/accounts/logout/";
    public static final String btnLogin = "//a[@href='/accounts/login/']";
    public static final String btnSignup = "//a[@href='/signup/']";
    public static final String loginError = "//ul[@class='errorlist nonfield']/li";
    public static final String loginErrorMsg = "Please enter a correct email address and password. Note that both fields may be case-sensitive.";

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
    public void invalidPassword() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("12345");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void invalidLogin() {
        $("#loginEmail").sendKeys("1a2a3a12aa31231@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void invalidLoginAndPassword() {
        $("#loginEmail").sendKeys("teastaaaqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui2132367we34");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(loginError)).shouldHave(Condition.exactText(loginErrorMsg));
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
    }
    @Test
    public void blankLoginPassword() {
        $("#loginEmail").getAttribute("required");
        assertTrue( $("#loginEmail").getAttribute("required"), true);
        //assertThat("Required attribute present", $("#loginEmail").getAttribute("required"), equalTo(true));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());
        $("#loginPassword").getAttribute("required");
        assertTrue( $("#loginPassword").getAttribute("required"), true);

        //Case login only
        $("#loginPassword").sendKeys("asusdsdsdsi67we34");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        $("#loginPassword").clear();
        $("#loginPassword").shouldBe(empty);
        //Case password only
        $("#loginEmail").sendKeys("testsdsdsds001@gmail.com");
        $(By.xpath("//button[@type='submit']")).click();
        $("#loginEmail").clear();
        $("#loginEmail").shouldBe(empty);
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);
        //Case all blank
        $("#loginPassword").clear();
        $("#loginEmail").clear();
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(btnLogin)).shouldBe(visible);
        $(By.xpath(btnSignup)).shouldBe(visible);

    }
    @Test
    public void validCredentials() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
    }

}
