package Page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Page.TestsCredentials.*;

/**
 * Created by VatslauX on 20-Feb-17.
 */
public class UrlConfig {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static final String STAGING_PEKAMA = "https://staging.pekama.com";
    public static final String STAGING_COMMUNITY = "https://communitystaging.pekama.com";
    public static final String TEST_PEKAMA = "";
    public static final String TEST_COMMUNITY = "";
    public static final String PROD_PEKAMA = "https://docketing.pekama.com";
    public static final String PROD_COMMUNITY = "https://community.pekama.com";

    public static String ENVIRONMENT_PEKAMA;
    public static String ENVIRONMENT_COMMUNITY;
    public static String SELECT_HOST;
    public static String MATTER_TYPE_TRADEMARK;
    public static String MATTER_TYPE_PATENT;

    public static final int environment = 1;
    public static void setEnvironment() {
        switch (environment) {
            case 1:
                rootLogger.info("Tests will executed on Staging server");
                ENVIRONMENT_PEKAMA = STAGING_PEKAMA;
                ENVIRONMENT_COMMUNITY = STAGING_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                break;
            case 2:
                rootLogger.info("Tests will executed on Test server");
                ENVIRONMENT_PEKAMA = TEST_PEKAMA;
                ENVIRONMENT_COMMUNITY = TEST_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = CaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = CaseType.PATENT.getValue();
                break;
            case 3:
                rootLogger.info("Tests will executed on Production server");
                ENVIRONMENT_PEKAMA = PROD_PEKAMA;
                ENVIRONMENT_COMMUNITY = PROD_COMMUNITY;
                SELECT_HOST = ENVIRONMENT_PEKAMA;
                MATTER_TYPE_TRADEMARK = ProductionCaseType.TRADEMARK.getValue();
                MATTER_TYPE_PATENT = ProductionCaseType.PATENT.getValue();
                break;
        }
    }


    //users and passwords
}
