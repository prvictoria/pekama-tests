package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestsCommunityDashboard.class,
        TestsCommynityLanding.class,
        TestsComunityWizard.class,
        TestsCommunityProfile.class
})
public class AllCommunityTestsRunner {
}
