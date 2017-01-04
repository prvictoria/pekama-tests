package com.pekama.app.draft;

import com.codeborne.selenide.Selenide;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by VatslauX on 26-Dec-16.
 */
public class TestGoogle {
    @Test
    public void TestGoogle() {
        //Configuration.browser = "chrome";
        Selenide.open("http://wpceb2015.esy.es/");
        Selenide.$(By.xpath("//*[@el='Login']")).click();
        Selenide.$(By.xpath("//*[@data-type='twitter']")).click();
        Selenide.switchTo().window("Twitter / Authorize an application");
        Selenide.$(By.id("username_or_email")).val("test2014ceb@gmail.com").click();
    }
}
