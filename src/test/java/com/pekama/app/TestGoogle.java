package com.pekama.app;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by VatslauX on 26-Dec-16.
 */
public class TestGoogle {
    @Test
    public void TestGoogle() {
        //Configuration.browser = "chrome";
        open("http://wpceb2015.esy.es/");
        $(By.xpath("//*[@el='Login']")).click();
        $(By.xpath("//*[@data-type='twitter']")).click();
        switchTo().window("Twitter / Authorize an application");
        $(By.id("username_or_email")).val("test2014ceb@gmail.com").click();
    }
}
