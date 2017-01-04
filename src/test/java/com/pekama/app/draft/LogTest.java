package com.pekama.app.draft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


/**
 * Created by Viachaslau_Balashevi on 1/4/2017.
 */
public class LogTest {
    static final Logger rootLogger = LogManager.getRootLogger();
//    static final Logger userLogger = LogManager.getLogger(LogTest.class);

    @Test
    public void test1() {
//        Selenide.open(TEST_ENVIROMENT_COMMUNITY);
        System.out.println("test1");
        rootLogger.info("root-info");
    }
    @Test
    public void test2() {
//        open(TEST_ENVIROMENT_COMMUNITY);
        System.out.println("test2");
//        userLogger.debug("debug");
        rootLogger.debug("root-debug");
    }
    @Test
    public void test3() {
//        open(TEST_ENVIROMENT_COMMUNITY);
        System.out.println("test3");
//        userLogger.trace("trace");
        rootLogger.trace("root-trace");
    }
    @Test
    public void test4() {
//        open(TEST_ENVIROMENT_COMMUNITY);
        System.out.println("test4");
        rootLogger.fatal("root-fatal");
    }
    @Test
    public void test5() {
//        Selenide.open(TEST_ENVIROMENT_COMMUNITY);
        System.out.println("test5");
        rootLogger.warn("root-warn");
    }

}
