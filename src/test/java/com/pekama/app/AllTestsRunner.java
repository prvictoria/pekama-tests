package com.pekama.app;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
//@Categories.ExcludeCategory(AllEmailsTests.class) //Emails not stable
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
        TestsPekamaIntegrationBox.class,
        TestsPekamaSettingsTeam.class,
        TestsPekamaSettingValues.class,
        TestsPekamaTemplates.class,
        TestsPekamaReports.class //last suite
})
public class AllTestsRunner {
    static final Logger rootLogger = LogManager.getRootLogger();
//    @Rule
//    public Timeout tests = Timeout.seconds(600);
//    @Rule
//    public SoftAsserts softAsserts = new SoftAsserts();

    public static int testBrowser = 1;
    public static void setBrowser() {
        switch (testBrowser) {
            case 1:
                browser = CHROME;
                startMaximized = false;
//                browserSize = "1700x1000";
//                ChromeDriverManager.getInstance().setup();
//                getWebDriver().manage().window().maximize();
//                getWebDriver().manage().window().setPosition(new Point(0, 0));
//                getWebDriver().manage().window().setSize(new Dimension(1800, 1000));
//                System.out.print("position " + getWebDriver().manage().window().getPosition());
//                System.out.print("size " + getWebDriver().manage().window().getSize());
                rootLogger.info("Tests will performed in Chrome");
                break;
            case 2:
                browser = MARIONETTE;
                FirefoxDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in Firefox");
                break;
            case 3:
                browser = INTERNET_EXPLORER;
                InternetExplorerDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in IE");
                break;
            case 4:
                browser = EDGE;
                EdgeDriverManager.getInstance().setup();
                rootLogger.info("Tests will performed in EDGE");
                break;
        }
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
