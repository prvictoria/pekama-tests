package Steps;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.CommunityDashboard.*;
import static Page.CommunityProfile.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.CSS_SelectHighlighted;
import static Steps.PekamaSteps.enterCharsetInField;
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
        WIZARD_SELECT_CaseType.click();
        WIZARD_INPUT_CaseType.sendKeys(caseType);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+caseType);
        WIZARD_SELECT_Defining.click();
        WIZARD_INPUT_Defining.sendKeys(country);
        CSS_SelectHighlighted.click();
         rootLogger.info("Selected case type - "+country);
   }
    public static String searchExpertsSubmit() {
        WIZARD_BTN_GetStarted.shouldBe(enabled).click();
        searchQueryUrl = url();
        rootLogger.info("Link to mailing list present -  ");
        return searchQueryUrl;
    }
    public static void searchServicesQuery(String caseType, String country, String price) {
        PROFILE_SELECT_CaseType.click();
        enterCharsetInField(PROFILE_INPUT_CaseType, caseType);
//        PROFILE_INPUT_CaseType.sendKeys(caseType);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+caseType);
        PROFILE_SELECT_Defining.click();
        enterCharsetInField(PROFILE_INPUT_Defining, country);
//        PROFILE_INPUT_Defining.sendKeys(country);
        CSS_SelectHighlighted.click();
        PROFILE_INPUT_PRICE.sendKeys(price);
        rootLogger.info("Selected case type - "+country);
    }
}
