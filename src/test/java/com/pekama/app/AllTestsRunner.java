package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestsCommunityDashboard.class,
        TestsCommynityLanding.class,
        TestsComunityWizard.class,
        TestsCommunityProfile.class,

        TestsPekamaLanding.class,
        TestsPekamaLogin.class,
        TestsPekamaResetPassword.class,
        Page.PekamaSignUp.class,
        TestsPekamaSettingsPersonal.class,
        TestsPekamaProject.class,
        TestsPekamaIntegrationBox.class,
        TestsPekamaSettingsTeam.class
})
public class AllTestsRunner {
}
