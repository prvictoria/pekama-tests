package com.pekama.app;
import Page.PekamaSignUp;
import com.codeborne.selenide.Configuration;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        //TestsCommunityIntegration.class,
        TestsCommunityDashboard.class,
        TestsCommynityLanding.class,
        TestsCommunityWizard.class,
        TestsCommunityProfile.class,
        TestsCommunityOutgoing.class,
        TestsCommunityIncoming.class,

        TestsPekamaDashboard.class,
        TestsPekamaLanding.class,
        TestsPekamaLogin.class,
        TestsPekamaResetPassword.class,
        TestsPekamaSignUp.class,
        TestsPekamaSettingsPersonal.class,
        TestsPekamaProject.class,
        TestsMessages.class,
        //TestsPekamaIntegrationBox.class,
        TestsPekamaSettingsTeam.class,
        TestsPekamaSettingValues.class,
        TestsPekamaTemplates.class,
        TestsPekamaReports.class //last suite
})
public class AllTestsRunner {
    public static void holdBrowserAfterTest() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
    }
    public static void holdBrowserAfterTest(boolean value) {
        Configuration test = new Configuration();
        test.holdBrowserOpen = value;
    }
}
