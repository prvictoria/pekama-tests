package com.pekama.app.draft;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 25-Dec-16.
 */

public class CheckGmailEmail {
    final static String emailSubject = "";
    final static String emailFirst = "//tbody/tr[@class='zA yO'][2]";
    final static String emailFirstSubject = "//span[@id=':d1']";

    @Test
    public void open_page(){
        $(By.xpath(emailFirst));
        $(By.xpath(emailFirst+emailFirstSubject)).shouldHave(Condition.text(emailSubject));

    }
}
