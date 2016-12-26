package com.pekama.app;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


/**
 * Created by VatslauX on 26-Dec-16.
 */


public class LoginPekama {
    String login = "qweeco";
    String password = "qw33coStudi0";


    @Test
    public void setLogin() {
        open("http://staging.pekama.com");
        //switchTo().alert().authenticateUsing("qweeco", login);
        //open("http://www.staging.pekama.com\\qweeco:qw33coStudi0@staging.pekama.com");
        open("https://staging.pekama.com", "www.staging.pekama.com", "qweeco" , "qw33coStudi0");

        $("#loginEmail").sendKeys("testqweeco001@gmail.com");
        $("#loginPassword").sendKeys("asui67we34");
        $(By.xpath("//button[@type='submit']")).click();

    }

}
