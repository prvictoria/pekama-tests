package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;

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
        //$(By.xpath("//div[@role='button' and contains(.,'COMPOSE')]")).click();
        //$(byTitle("Inbox - testqweeco001@gmail.com - Gmail")).waitUntil(appears, 10000); //visible element
        //$(byTitle("Inbox - testqweeco001@gmail.com - Gmail")).should(exist);
        //assertEquals("Inbox - testqweeco001@gmail.com - Gmail", title());
        String title = new String("");
        $(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"); //visible element

        System.out.println($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));

        //assertEquals(attribute("title", "Google Account: Test Account (testqweeco001@gmail.com)"), $(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));
        //assertThat(attribute("title", "Google Account: Test Account (testqweeco001@gmail.com)").isIn($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title")));

    };
}