package Tests;
import Steps.MessagesIMAP;
import Tests.TestsCommunity.TestsCommunityGuestFlow;
import Tests.TestsCommunity.TestsCommunityLogin;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
//@Categories.ExcludeCategory(AllEmailsTests.class) //Emails not stable
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessagesIMAP.class,
        TestsCommunityLogin.class,
        TestsCommunityGuestFlow.class
})
public class AllCommunityTestsRunner extends AllTestsRunner {
}
