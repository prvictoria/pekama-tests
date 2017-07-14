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
        TestsPekamaReports.class,
        TestsPekamaReportsFiltersProjects.class,
        TestsPekamaReportsFiltersTasks.class,
        TestsPekamaReportsFiltersEvents.class,
        TestsPekamaReportsFiltersCharges.class,
        TestsPekamaReportsFiltersContacts.class,

        TestsPekamaDashboard.class,
        TestsPekamaLanding.class,
        TestsPekamaLogin.class,
        TestsPekamaResetPassword.class, //not important for now
        TestsPekamaSignUp.class,

        TestsPekamaSettingsPersonal.class,
        TestsPekamaSettingsTeam.class,
        TestsPekamaSettingValues.class,

        TestsMessages.class,
        TestsPekamaIntegrationBox.class,
        TestsPekamaProject.class,
        TestsPekamaProjectInfo.class,
        TestsPekamaProjectContacts.class,
        //TestsPekamaProjectDocs.class,
        TestsPekamaProjectTasks.class,
        TestsPekamaProjectCharges.class,

        TestsPekamaTemplates.class


})
public class AllPekamaTestsRunner {

}
