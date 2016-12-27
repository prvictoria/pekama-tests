package com.pekama.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


/**
 * Created by VatslauX on 26-Dec-16.
 */


public class LoginPekama {
    String email = "qweeco";
    String password = "qw33coStudi0";
    String hostProduction = "https://docketing.pekama.com/";
    String hostStaging = "http://qweeco:qw33coStudi0@staging.pekama.com";
    String host = hostProduction;
    String login = "https://pekama.com/accounts/login/";
    String urlLogout = "https://pekama.com/accounts/logout/";
    @Before
    public void openUrlLogin() {
        open(login);
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
    }
    @Test
    public void invalidLogin() {
        $("#loginEmail").sendKeys("1a2a3a12aa31231@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void invalideLoginAndPassword() {
        $("#loginEmail").sendKeys("teastaaaqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui2132367we34");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void blankLogin() {
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void blankPassword() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void blankLoginAndPassword() {
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void emptyLogin() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginEmail").sendKeys("");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void emptyPassword() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $("#loginPassword").sendKeys("");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void epmtyLoginAndPassword() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void validCredentials() {
        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();
    }

}
