package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static Page.CommunityDashboard.*;
import static Page.CommunityProfile.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.CSS_SelectHighlighted;
import static Steps.PekamaSteps.enterCharsetInField;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
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
    public static void searchServicesQuery(String PROFILE_SERVICE_CASE_TYPE, String PROFILE_SERVICE_COUNTRY, String price) {
        PROFILE_SELECT_CaseType.click();
        enterCharsetInField(PROFILE_INPUT_CaseType, PROFILE_SERVICE_CASE_TYPE);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+PROFILE_SERVICE_CASE_TYPE);
        PROFILE_SELECT_Defining.click();
        enterCharsetInField(PROFILE_INPUT_Defining, PROFILE_SERVICE_COUNTRY);
        CSS_SelectHighlighted.click();
        PROFILE_INPUT_PRICE.clear();
        PROFILE_INPUT_PRICE.sendKeys(price);
        rootLogger.info("Selected case type - "+PROFILE_SERVICE_COUNTRY);
    }
    public static String findServiceRow(String PROFILE_SERVICE_CASE_TYPE, String PROFILE_SERVICE_COUNTRY) {
        String profileServiceRow = "//div[contains(.,'"+PROFILE_SERVICE_CASE_TYPE+"')]/following-sibling::div[contains(.,'"+PROFILE_SERVICE_COUNTRY+"')]/following-sibling::div//button[1]";
        return profileServiceRow;
    }

    public static String TEST_CASE_USER_CREATED_SUCCESS = "//div[@class='row' and contains(.,'%s') and contains(.,'%s')]";

    public static void findServiceRow(boolean rowPresentOnPage, String... args) {
        String profileServiceRow = String.format(TEST_CASE_USER_CREATED_SUCCESS, args);
        if ($(byXpath(profileServiceRow)).exists()!=rowPresentOnPage)
        {
            Assert.fail("Service present element state is - "+$(byXpath(profileServiceRow)).exists());
        }
    }
    public static void clickServiceRowEdit(String profileServiceCaseType, String profileServiceCountry) {
        //       String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[1]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[1]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }
    public static void clickServiceRowDelete(String profileServiceCaseType, String profileServiceCountry) {
        // String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[2]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[2]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }
    public static void changeServiceRate(String profileServiceCaseType, String profileServiceCountry, String newPrice) {
        SelenideElement serviceRateField = $(byXpath("//div[@class='row ng-scope' and contains(.,'"+profileServiceCaseType+"') and contains(.,'"+profileServiceCountry+"')]//input[@name='rate']"));
        serviceRateField.clear();
        serviceRateField.val(newPrice);
        serviceRateField.shouldHave(Condition.value(newPrice));
    }

}
