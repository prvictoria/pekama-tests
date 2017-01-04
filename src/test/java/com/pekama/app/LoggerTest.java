package com.pekama.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;


import static Page.RunConfig.TEST_ENVIROMENT_COMMUNITY;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by Viachaslau_Balashevi on 1/4/2017.
 */
public class LoggerTest {
    static final Logger rootLogger = LogManager.getRootLogger();
//    static final Logger userLogger = LogManager.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        open(TEST_ENVIROMENT_COMMUNITY);
//        userLogger.info("info");
        rootLogger.info("root-info");
    }
//    @Test
//    public void test2() {
//        open(TEST_ENVIROMENT_COMMUNITY);
//        userLogger.debug("debug");
//        rootLogger.debug("root-debug");
//    }
//    @Test
//    public void test3() {
//        open(TEST_ENVIROMENT_COMMUNITY);
//        userLogger.trace("trace");
//        rootLogger.trace("trace-debug");
//    }
//    @Test
//    public void test4() {
//        open(TEST_ENVIROMENT_COMMUNITY);
//        userLogger.fatal("fatal");
//    }
//    @Test
//    public void test5() {
//        open(TEST_ENVIROMENT_COMMUNITY);
//        userLogger.warn("WARN");
//    }

}
