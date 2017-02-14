package Utils;

import Page.TestsCredentials;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import static Page.TestsUrl.URL_COMMUNITY_LOGIN;
import static com.codeborne.selenide.Selenide.open;

public class ParallelComputerTest {

    @Test
    public void test() {
        Class[] cls={
                ParallelTest1.class,
                ParallelTest2.class
        };
        //Parallel among classes
        JUnitCore.runClasses(ParallelComputer.classes(), cls);
        //Parallel among methods in a class
        JUnitCore.runClasses(ParallelComputer.methods(), cls);
        //Parallel all methods in all classes
        JUnitCore.runClasses(new ParallelComputer(true, true), cls);
    }
    public static class ParallelTest1{
        static final Logger rootLogger = LogManager.getRootLogger();
        private String expertTeam = TestsCredentials.User2.TEAM_NAME.getValue();
        private String expertEmail = TestsCredentials.User2.GMAIL_EMAIL.getValue();
        private String expertPassword = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
        private String supplierEmail = TestsCredentials.User1.GMAIL_EMAIL.getValue();
        private String supplierPassword = TestsCredentials.User1.PEKAMA_PASSWORD.getValue();
        private static String caseName;
        @Test public void a(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");

        }
        @Test public void b(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");
        }
    }
    public static class ParallelTest2{
        static final Logger rootLogger = LogManager.getRootLogger();
        private String expertTeam = TestsCredentials.User2.TEAM_NAME.getValue();
        private String expertEmail = TestsCredentials.User2.GMAIL_EMAIL.getValue();
        private String expertPassword = TestsCredentials.User2.PEKAMA_PASSWORD.getValue();
        private String supplierEmail = TestsCredentials.User1.GMAIL_EMAIL.getValue();
        private String supplierPassword = TestsCredentials.User1.PEKAMA_PASSWORD.getValue();
        private static String caseName;
        @Test public void a(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");
        }
        @Test public void b(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");
        }
    }
}