/**
 * Created by VatslauX on 29-Dec-16.
 */
package com.pekama.app;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CommunityDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void before() {
        open("");
    }
    @After
    public void after() {
        open("");
    }

    @Test
    public void template() {
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
    }
}
