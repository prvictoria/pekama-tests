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

import static Page.CommunityDashboard.*;
import static Page.DirectLinks.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CommunityDashboard {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Before
    public void before() {
        open(COMMUNITY_DASHBOARD);
    }
//    @After
//    public void after() {
//        open("");
//    }

    @Test
    public void checkDashboardGui() {
        $(By.xpath(COMMUNITY_HEADER_LOGO)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_MANAGEMENT)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_SIGNUP)).shouldBe(Condition.visible);
        $(By.xpath(COMMUNITY_HEADER_LOGIN)).shouldBe(Condition.visible);
        rootLogger.info("All elements in Header present on default screen");
        $(By.xpath(COMMUNITY_TAB_Supplier)).shouldBe(Condition.visible).shouldHave(Condition.text("find a supplier"));
        $(By.xpath(COMMUNITY_TAB_Incoming)).shouldBe(Condition.visible).shouldHave(Condition.text("incoming cases"));
        $(By.xpath(COMMUNITY_TAB_Outgoing)).shouldBe(Condition.visible).shouldHave(Condition.text("outgoing cases"));
        $(By.xpath(COMMUNITY_TAB_Profile)).shouldBe(Condition.visible).shouldHave(Condition.text("become a supplier"));

        rootLogger.info("Tabs present on default screen - user isn`t logged in");
    }
    @Test
    public void template() {
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
}
