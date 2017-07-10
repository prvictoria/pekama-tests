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
    @Deprecated
    public static void setEnvironment() {
        switch (environment) {
            case 1:
                rootLogger.info("Tests will executed on Staging server against Pekama");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = CaseType.CRM.getValue();
                COMMUNITY_SERVICE = "Filing an Application";
                INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
                break;
            case 2:
                rootLogger.info("Tests will executed on Staging server against Community");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_COMMUNITY;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = CaseType.CRM.getValue();
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
    public static void setEnvironment(Integer environment) {
        switch (environment) {
            case 1:
                rootLogger.info("Tests will executed on Staging server against Pekama");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = CaseType.CRM.getValue();
                COMMUNITY_SERVICE = "Filing an Application";
                INTRODUCER_NAME = "Rand, Kaldor & Zane LLP (RKNZ)";
                break;
            case 2:
                rootLogger.info("Tests will executed on Staging server against Community");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_COMMUNITY;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                MATTER_TYPE_CRM = CaseType.CRM.getValue();
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
}
