package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {
    @Test
    public void open_page() {
        open("https://mail.google.com/mail/u/0/#inbox");
        $(By.name("Email")).sendKeys("testqweeco001@gmail.com");
        $(By.name("signIn")).click();
        $(By.id("Passwd")).shouldBe(Condition.visible).sendKeys("123456789qasw1");
        $(By.id("signIn")).shouldBe(Condition.visible).click();

    };
}