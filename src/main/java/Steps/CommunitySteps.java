package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.CommunityDashboard.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.CSS_SelectHighlighted;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by VatslauX on 03-Jan-17.
 */
public class CommunitySteps {
    static final Logger rootLogger = LogManager.getLogger(CommunitySteps.class);
    public static String searchQueryUrl;
    public static void searchExpertsQuery(String caseType, String country) {
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        COMMUNITY_WIZARD_SELECT_CaseType.click();
        COMMUNITY_WIZARD_INPUT_CaseType.sendKeys(caseType);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+caseType);
        COMMUNITY_WIZARD_SELECT_Defining.click();
        COMMUNITY_WIZARD_INPUT_Defining.sendKeys(country);
        CSS_SelectHighlighted.click();
         rootLogger.info("Selected case type - "+country);
   }
    public static String searchExpertsSubmit() {
        WIZARD_BTN_GetStarted.shouldBe(enabled).click();
        searchQueryUrl = url();
        rootLogger.info("Link to mailing list present -  ");
        return searchQueryUrl;
    }
    public static void searchServicesQuery(String caseType, String country) {
        WIZARD_BTN_GetStarted.shouldBe(visible).shouldBe(disabled);
        COMMUNITY_PROFILE_SELECT_CaseType.click();
        COMMUNITY_PROFILE_INPUT_CaseType.sendKeys(caseType);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+caseType);
        COMMUNITY_PROFILE_SELECT_Defining.click();
        COMMUNITY_PROFILE_INPUT_Defining.sendKeys(country);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+country);
    }
}
