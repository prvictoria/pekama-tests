package Page;
import Page.TestsCredentials.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by VatslauX on 20-Feb-17.
 */
public class UrlConfig {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static final String STAGING_PEKAMA = "https://staging.pekama.com";
    public static final String STAGING_COMMUNITY = "https://communitystaging.pekama.com";
    public static final String TEST_PEKAMA = "https://";
    public static final String TEST_COMMUNITY = "https://";
    public static final String PROD_PEKAMA = "https://docketing.pekama.com";
    public static final String PROD_COMMUNITY = "https://community.pekama.com";

    public static String ENVIRONMENT_PEKAMA;
    public static String ENVIRONMENT_COMMUNITY;
    public static String SELECT_HOST;
    public static String MATTER_TYPE_TRADEMARK;
    public static String MATTER_TYPE_PATENT;
    public static String MATTER_TYPE_CRM;
    public static String COMMUNITY_SERVICE;
    public static String INTRODUCER_NAME;

    public static int environment = 1;
    public static void setEnvironment() {
        switch (environment) {
            case 1:
                rootLogger.info("Tests will executed on Staging server");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = CaseType.CRM.getValue();
                COMMUNITY_SERVICE = "Filing an Application";
                INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
//                int thread = 1; // users accounts which will be used by test execution
//                switch (thread){
//                    case 1:
////                        PEKAMA_USER.add(User1.GMAIL_EMAIL.getValue(), User1.PEKAMA_PASSWORD.getValue());
//    //                    PEKAMA_COLLABORATOR.add(User2.GMAIL_EMAIL.getValue());
//                        break;
//                    case 2:
//                        break;

//                }
                break;
            case 2:
                rootLogger.info("Tests will executed on Test server");
                ENVIRONMENT_PEKAMA = TEST_PEKAMA;
                ENVIRONMENT_COMMUNITY = TEST_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = ProductionCaseType.CRM.getValue();
                COMMUNITY_SERVICE = "Filing an Application";
                INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
                break;
            case 3:
                rootLogger.info("Tests will executed on Production server");
                ENVIRONMENT_PEKAMA = PROD_PEKAMA;
                ENVIRONMENT_COMMUNITY = PROD_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = ProductionCaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = ProductionCaseType.PATENT.getValue();
                COMMUNITY_SERVICE = "Filing an Application";
                INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
                break;
        }
    }
//    //Users config
//    public static String USER_EMAIL;
//    public static String EMAIL_PASSWORD;
//    public enum ObjectUser {
//
//        GMAIL_EMAIL(USER_EMAIL),
//        GMAIL_PASSWORD(EMAIL_PASSWORD),
//        PEKAMA_PASSWORD("asui67we34"),
//        LINKEDIN_PASSWORD("asui67we34@Q"),
//        BOX_PASSWORD("32qew8127a12a"),
//        NAME("Test001"),
//        SURNAME("Quality01"),
//        NAME_SURNAME("Test001 Quality01"),
//        TEAM_NAME("QweecoTeam01"),
//        TEAM_CODE("QT01"),
//        FULL_TEAM_NAME("QweecoTeam01 (QT01)"),
//        COMMUNITY_CASE_TYPE("Patent"),
//        COMMUNITY_CASE_COUNTRY("Pitcairn Islands");
//
//
//        private String value;
//        ObjectUser(String gmailLogin) {
//            this.value = gmailLogin;
//        }
//        public String value() {
//            return value;
//        }
//    }
//    @Test
//    public void sometest(){
//        System.out.print(ObjectUser.GMAIL_EMAIL.value);
//    }

    //users and passwords
}
