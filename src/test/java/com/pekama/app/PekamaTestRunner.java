package com.pekama.app;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PekamaLanding.class,
        PekamaLogin.class,
        PekamaResetPassword.class,
        Page.PekamaSignUp.class
})
public class PekamaTestRunner {
}
