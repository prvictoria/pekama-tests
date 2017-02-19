package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestsCommunityDashboard.class,
        TestsCommynityLanding.class,
        TestsCommunityWizard.class,
        TestsCommunityProfile.class,
        TestsCommunityOutgoing.class
})
public class AllCommunityTestsRunner extends AllTestsRunner{
}
