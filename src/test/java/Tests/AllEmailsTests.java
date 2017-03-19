package Tests;
import org.junit.FixMethodOrder;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(AllEmailsTests.class)
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
       TestsPekamaResetPassword.class,
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AllEmailsTests extends AllTestsRunner {

}

