package com.pekama.app;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.SoftAsserts;
import io.github.bonigarcia.wdm.*;
import org.junit.Rule;
import org.junit.experimental.categories.Categories;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.isIE;
import static org.junit.Assume.assumeTrue;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@Categories.ExcludeCategory(AllEmailsTests.class) //Emails not stable
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestsCommunityIntegration.class,
        TestsCommunityDashboard.class,
        TestsCommynityLanding.class,
        TestsCommunityWizard.class,
        TestsCommunityProfile.class,
        TestsCommunityOutgoing.class,
        TestsCommunityIncoming.class,

        TestsPekamaDashboard.class,
        TestsPekamaLanding.class,
        TestsPekamaLogin.class,
        TestsPekamaResetPassword.class, //not important for now
        TestsPekamaSignUp.class,
        TestsPekamaSettingsPersonal.class,
        TestsPekamaProject.class,
        TestsMessages.class,
        //TestsPekamaIntegrationBox.class, //not ready
        TestsPekamaSettingsTeam.class,
        TestsPekamaSettingValues.class,
        TestsPekamaTemplates.class,
        TestsPekamaReports.class //last suite
})
public class AllTestsRunner {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(600); // 10 seconds max per method tested
    @Rule public SoftAsserts softAsserts = new SoftAsserts();

    public static void setBrowser() {
        browser = FIREFOX;
//        startMaximized = false;

        if(browser.equals("chrome"));
             {//browserSize = "1700x1000";
                 ChromeDriverManager.getInstance().setup();
                 getWebDriver().manage().window().maximize();
//                 getWebDriver().manage().window().setPosition(new Point(0,0));
//                 getWebDriver().manage().window().setSize(new Dimension(1800,1000));
////
//startMaximized = true;
//                System.out.print("position "+getWebDriver().manage().window().getPosition());
//                System.out.print("size "+getWebDriver().manage().window().getSize());
             }
//        if (browser.equals("marionette")) ;
//            {FirefoxDriverManager.getInstance().setup();}
//        if (browser.equals("ie")) ;
//            {InternetExplorerDriverManager.getInstance().setup();}
//        if (browser.equals("edge")) ;
//            {EdgeDriverManager.getInstance().setup();}


    }
    public static void holdBrowserAfterTest() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = false;
    }
    public static void holdBrowserAfterTest(boolean value) {
        Configuration test = new Configuration();
        test.holdBrowserOpen = value;
    }
}
