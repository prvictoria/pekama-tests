package com.pekama.app;
import Page.TestsCredentials;
import Steps.PekamaSteps;
import Utils.HttpAuth;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static Page.CommunityDashboard.COMMUNITY_HEADER_LOGIN;
import static Page.CommunityDashboard.*;
import static Page.CommunityLanding.*;
import static Page.DirectLinks.*;
import static Page.PekamaLogin.*;
import static Page.TestsUrlConfiguration.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommunityProfile {
    static final Logger log = LogManager.getLogger(CommunityProfile.class);
    public static String SELECT_HOST = COMMUNITY;
    String PEKAMA_USER_EMAIL = TestsCredentials.User3.GMAIL_EMAIL.getValue();
    @Before
    public void openUrlLogin() {

        log.info("Open host");
        HttpAuth openHost = new HttpAuth();
        openHost.httpAuthStgingCommunity();
        $(By.xpath(LANDING_LOGIN)).shouldBe(Condition.visible).click();
        PekamaSteps login = new PekamaSteps();
        login.submitLoginCredentials(PEKAMA_USER_EMAIL);
        log.info("Redirect back after login");
        $(By.xpath(COMMUNITY_TAB_Profile)).shouldBe(Condition.visible).shouldHave(Condition.text("my profile")).click();
    }
//    @After
//    public void openUrlLogout() {
//        open(urlLogout);
//    }
    @Test
    public void openResetPassword() {

    }
}
