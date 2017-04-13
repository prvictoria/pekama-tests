package Tests;
import Steps.MessagesIMAP;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
//@Categories.ExcludeCategory(AllEmailsTests.class) //Emails not stable
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessagesIMAP.class, //conditional clean emails
        TestsCommunityIntegration.class,
        TestCommunityIntegrationRedirect.class,
        TestsCommynityLanding.class,
        TestsCommunityDashboard.class,
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
        TestsPekamaReports.class, //last suite
        MessagesIMAP.class, //conditional clean emails
})
public class AllTestsRunner {

}
