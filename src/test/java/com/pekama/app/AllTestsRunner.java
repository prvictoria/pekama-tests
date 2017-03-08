package com.pekama.app;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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


}
