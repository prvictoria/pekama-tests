package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CommunityDashboard.class,
        CommynityLanding.class,
        ComunityWizard.class
})
public class AllCommunityTestsRunner {
}
