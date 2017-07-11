package Utils;
import Pages.DataCredentials;
import Steps.StepsPekama;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import static Pages.UrlStrings.URL_COMMUNITY_LOGIN;
import static com.codeborne.selenide.Selenide.open;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
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
        private String expertTeam = DataCredentials.User2.TEAM_NAME.getValue();
        private String expertEmail = DataCredentials.User2.GMAIL_EMAIL.getValue();
        private String expertPassword = DataCredentials.User2.PEKAMA_PASSWORD.getValue();
        private String supplierEmail = DataCredentials.User1.GMAIL_EMAIL.getValue();
        private String supplierPassword = DataCredentials.User1.PEKAMA_PASSWORD.getValue();
        private static String caseName;
        @Ignore
        @Test public void a(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");

        }
        @Ignore
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
        private String expertTeam = DataCredentials.User2.TEAM_NAME.getValue();
        private String expertEmail = DataCredentials.User2.GMAIL_EMAIL.getValue();
        private String expertPassword = DataCredentials.User2.PEKAMA_PASSWORD.getValue();
        private String supplierEmail = DataCredentials.User1.GMAIL_EMAIL.getValue();
        private String supplierPassword = DataCredentials.User1.PEKAMA_PASSWORD.getValue();
        private static String caseName;
        @Ignore
        @Test public void a(){
            rootLogger.info("Open host");
            StepsPekama loginIntoPekama = new StepsPekama();
            loginIntoPekama.loginByURL(
                    supplierEmail,
                    supplierPassword,
                    URL_COMMUNITY_LOGIN);
            rootLogger.info("Redirect back after login");
        }
        @Ignore
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