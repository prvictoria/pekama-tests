package com.pekama.app.draft;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LogTest.class,
        TestGoogle.class
})
public class TestRunner {
//    public static void main(String[] args) {
//        JUnitCore.runClasses(TestRunner.class);
//
//    }
}
