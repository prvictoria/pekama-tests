package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestsPekamaDashboard.class,
        TestsPekamaLanding.class,
        TestsPekamaLogin.class,
        TestsPekamaResetPassword.class,
        TestsPekamaSignUp.class,
        TestsPekamaSettingsPersonal.class,
        TestsPekamaProject.class,
        TestsMessages.class,
        TestsPekamaIntegrationBox.class,
        TestsPekamaSettingsTeam.class,
        TestsPekamaSettingValues.class,
        TestsPekamaTemplates.class,
        TestsPekamaReports.class
})
public class AllPekamaTestsRunner extends AllTestsRunner{

}
