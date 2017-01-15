package com.pekama.app;/**
 * Created by VatslauX on 15-Jan-17.
 */

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaSettingsTeam {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void profile_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        $(byText("Team details")).shouldBe(Condition.visible);
        $(byText("Profile")).find(byLinkText("link=Profile")).shouldBe(Condition.visible);
        $(byText("Members")).find(byLinkText("link=Members")).shouldBe(Condition.visible);
        $(byText("Values")).find(byLinkText("link=Values")).shouldBe(Condition.visible);
        $(byText("Task Templates")).find(byLinkText("link=Task Templates")).shouldBe(Condition.visible);
        $(byText("Message Templates")).find(byLinkText("link=Message Templates")).shouldBe(Condition.visible);
        $(byText("Event Templates")).find(byLinkText("link=Event Templates")).shouldBe(Condition.visible);
        $(byText("Document Templates")).find(byLinkText("link=Document Templates")).shouldBe(Condition.visible);
        $(byText("Storage")).find(byLinkText("link=Storage")).shouldBe(Condition.visible);
        $(byText("Profile")).find(byLinkText("link=Profile")).shouldBe(Condition.visible);
        $(byText("Profile")).find(byLinkText("link=Profile")).shouldBe(Condition.visible);
        rootLogger.info("");
    }
    public void members_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        $(byText("Members")).find(byLinkText("link=Members")).waitUntil(Condition.visible, 10000);
        $(byLinkText("link=Members")).click();
        $(byText("Add")).find(byLinkText("link=Add")).waitUntil(Condition.visible, 8000).click();
        //todo - integrate add member and delete
        rootLogger.info("");
    }
    public void members_testB_AddMember() {
        rootLogger.info("Start test GUI and links");
        $(byText("Members")).find(byLinkText("link=Members")).waitUntil(Condition.visible, 10000);
        $(byLinkText("link=Members")).click();
        $(byText("Add")).find(byLinkText("link=Add")).waitUntil(Condition.visible, 8000).click();
        //todo - integrate add member and delete
        rootLogger.info("");
    }
    @Test
    public void testB_addSameMember() {
        $(byText("Members")).find(byLinkText("link=Members")).waitUntil(Condition.visible, 10000);
        $(byLinkText("link=Members")).click();
        $(byText("Add")).find(byLinkText("link=Add")).waitUntil(Condition.visible, 8000).click();
        //todo - integrate add member
        rootLogger.info("");
    }
    @Test
    public void testC_deleteMember() {
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }

    @Test
    public void values_testA_GUI() {
        rootLogger.info("Start test GUI and links");
        $(byText("Conference")).find(byLinkText("link=Conference")).shouldBe(Condition.visible);
        $(byText("Client Relation (CRM)")).find(byLinkText("link=Client Relation (CRM)")).shouldBe(Condition.visible);
        $(byText("Corporate")).find(byLinkText("link=Corporate")).shouldBe(Condition.visible);
        $(byText("Design")).find(byLinkText("link=Design")).shouldBe(Condition.visible);
        $(byText("Domain Name")).find(byLinkText("link=Domain Name")).shouldBe(Condition.visible);
        $(byText("Immigration")).find(byLinkText("link=Immigration")).shouldBe(Condition.visible);
        $(byText("Company")).find(byLinkText("link=Company")).shouldBe(Condition.visible);
        $(byText("Copyright")).find(byLinkText("link=Copyright")).shouldBe(Condition.visible);
        $(byText("Dispute")).find(byLinkText("link=Dispute")).shouldBe(Condition.visible);
        $(byText("General")).find(byLinkText("link=General")).shouldBe(Condition.visible);
        $(byText("Litigation")).find(byLinkText("link=Litigation")).shouldBe(Condition.visible);
        $(byText("Opposition")).find(byLinkText("link=Opposition")).shouldBe(Condition.visible);
        $(byText("Patent")).find(byLinkText("link=Patent")).shouldBe(Condition.visible);
        $(byText("TeamWork Channel")).find(byLinkText("link=TeamWork Channel")).shouldBe(Condition.visible);
        $(byText("Trademark")).find(byLinkText("link=Trademark")).shouldBe(Condition.visible);
        $(byText("Tasks")).find(byLinkText("link=Tasks")).shouldBe(Condition.visible);
        $(byText("Financials")).find(byLinkText("link=Financials")).shouldBe(Condition.visible);

        rootLogger.info("Defauls state passed");
    }
    @Test
    public void testB_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }
    @Test
    public void testC_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }


    @Test
    public void testD_() {

        rootLogger.info("");
        open("");
        $(By.xpath("")).sendKeys("");
        $(By.xpath("")).shouldBe(Condition.visible);
        $(By.xpath("")).click();
        rootLogger.info("");
    }


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