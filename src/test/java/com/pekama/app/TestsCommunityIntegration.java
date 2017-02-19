package com.pekama.app;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.PekamaProject.TAB_INFO_COMMUNITY_BTN_START_NEW;
import static Page.PekamaProject.TAB_INFO_COMMUNITY_TITLE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsCommunityIntegration {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Ignore
    @Test  //todo
    public void createCommunityCaseFormPekama() {
        TAB_INFO_COMMUNITY_TITLE.shouldHave(text("Services from the Pekama IP Community"));
        TAB_INFO_COMMUNITY_BTN_START_NEW.click();

    rootLogger.info("Test passed");
    }
    @Ignore
    @Test
    public void testB_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
    @Ignore
    @Test
    public void testC_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore
    @Test
    public void testD_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Ignore
    @Test
    public void testE_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

}