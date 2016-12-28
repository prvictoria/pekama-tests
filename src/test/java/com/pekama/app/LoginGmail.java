package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {

    static final Logger LOG = LogManager.getLogger(LoginGmail.class);
    //DOMConfigurator.configure("log4j2.xml");

    @Test
    public void open_page() {
        LOG.debug("Start browser");
        open("https://mail.google.com/mail/u/0/#inbox");

        LOG.info("Type email");
        $(By.name("Email")).sendKeys("testqweeco001@gmail.com");

        LOG.warn("Submit email");
        $(By.name("signIn")).click();
        $(By.id("Passwd")).shouldBe(Condition.visible).sendKeys("123456789qasw1");
        $(By.id("signIn")).shouldBe(Condition.visible).click();

        String title = new String("");
        $(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"); //visible element
        System.out.println($(By.xpath("//a[@class='gb_b gb_db gb_R']")).waitUntil(appears, 10000).getAttribute("title"));
        //assertEquals("Inbox -ххххххххххххх@gmail.com - Gmail", title());

    };
}