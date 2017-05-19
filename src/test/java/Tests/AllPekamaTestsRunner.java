package Tests;
import Steps.MessagesIMAP;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessagesIMAP.class,
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
        TestsPekamaReports.class,
        //TestsPekamaReportsFiltersProjects.class,
        //TestsPekamaReportsFiltersTasks.class,
        //TestsPekamaReportsFiltersEvents.class,
        TestsPekamaReportsFiltersCharges.class,
        //TestsPekamaReportsFiltersContacts.class
})
public class AllPekamaTestsRunner extends AllTestsRunner {

}
