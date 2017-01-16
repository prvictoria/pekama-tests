package Steps;
import Utils.HttpAuth;
import Utils.Utils;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaPersonalSettings.PERSONAL_DETAILS_INPUT_REGION;
import static Page.PekamaPersonalSettings.personalSettingsSaveButton;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.ERROR_MSG_VALIDATION_LENGTH_255;
import static Page.TestsUrl.urlSingUp;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;

public class PekamaSteps {
    static final Logger rootLogger = LogManager.getRootLogger();
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        open(urlLogIn); //HOST define PEKAMA or COMMUNITY redirect after login
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD, String urlLogIn){
        open(urlLogIn); //HOST define PEKAMA or COMMUNITY redirect after login
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }

    public void  loginByURL(String PEKAMA_USER_EMAIL, String PEKAMA_USER_PASSWORD, String AUTH_URL){
        HttpAuth openHost = new HttpAuth();
        openHost.httpAuthWhithCustomLink(AUTH_URL);
        rootLogger.info(AUTH_URL+"URL opened");
        submitCookie();
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(PEKAMA_USER_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        sleep(1000);
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }


    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        submitCookie();
//        String index = $(byXpath("//body/div[2]/iframe")).getAttribute("z-index");
//        rootLogger.info("z-index "+index);
//        Wait().until(frameToBeAvailableAndSwitchToIt(byXpath("//body/div[2]/iframe")));
//        switchTo().frame($(byXpath("//body/div[2]/iframe"))); // z-index: 16000003
//        $(byXpath("//div[@title='Minimize']"));
//        if($(byXpath("//div[@title='Minimize']"))!= null) {
//            $(byXpath("//div[@title='Minimize']")).hover().click();
//            rootLogger.info("null");
//        }
//        if($(byXpath("//div[@title='Minimize']")).hover().isDisplayed()) {
//            $(byXpath("//div[@title='Minimize']")).hover().click();
//            rootLogger.info("displayed");
//        }
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        sleep(1000);
        rootLogger.info("Valid Credentials were submitted");

    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD){
        submitCookie();
//        String index = $(byXpath("//body/div[2]/iframe")).getAttribute("z-index");
//        rootLogger.info("z-index "+index);
//        Wait().until(frameToBeAvailableAndSwitchToIt(byXpath("//body/div[2]/iframe")));
//        switchTo().frame($(byXpath("//body/div[2]/iframe"))); // z-index: 16000003
//        $(byXpath("//div[@title='Minimize']"));
//        if($(byXpath("//div[@title='Minimize']"))!= null) {
//            $(byXpath("//div[@title='Minimize']")).hover().click();
//            rootLogger.info("null");
//        }
//        if($(byXpath("//div[@title='Minimize']")).hover().isDisplayed()) {
//            $(byXpath("//div[@title='Minimize']")).hover().click();
//            rootLogger.info("displayed");
//        }
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        sleep(1000);
        rootLogger.info("Valid Credentials were submitted");

    }

    public void  submitCookie(){
        rootLogger.info("Check if cookie present");
        sleep(500);
        if ($(byText("Got it!")).isDisplayed()){
            $(byText("Got it!")).click();
            sleep(250);
            rootLogger.info("cookie were submitted");
        }
    }
    public static void waitForSpinnerNotPresent(){
        $(byXpath("//*[@id='progress-indicator']/span")).waitUntil(not(visible), 5000);
        rootLogger.info("spiner not present, page loaded");
    }
    public static void submitConfirmAction(){
        sleep(500);
        $(byXpath(MW)).shouldBe(visible);
        $(byText("Are you sure?")).shouldBe(Condition.visible);
        rootLogger.info("Confirm action modal window opened");
        $(byXpath(MW_BTN_YES)).shouldBe(visible).click();
        sleep(500);
        $(byXpath(MW)).shouldNotBe(visible);
    }
    public static void collapseChatWidget(){
        sleep(500);
        $(byXpath(MW)).shouldBe(visible);
        $(byText("Are you sure?")).shouldBe(Condition.visible);
        rootLogger.info("Confirm action modal window opened");
        $(byXpath(MW_BTN_YES)).shouldBe(visible).click();
        sleep(500);
        $(byXpath(MW)).shouldNotBe(visible);
    }
    public String mailingListCreateNew(String thisMailingListName){
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH)).waitUntil(visible, 5000).click();
        sleep(3000);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).shouldBe(disabled, visible);
        rootLogger.info("type"+thisMailingListName);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT)).sendKeys(thisMailingListName);
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).click();
        sleep(3000);
        $$(byText(thisMailingListName));
        $(byLinkText(thisMailingListName)).waitUntil(visible, 10000);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).pressEscape();
        sleep(500);
        rootLogger.info("Mailing List was created - "+ thisMailingListName);
        return thisMailingListName;
    }
    public static String mailingListSendReport(String thisMailingListName){
        $(byLinkText(thisMailingListName)).click();
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        String pathToReport = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML;
        $(byXpath(pathToReport)).click();
        String actualMailingListRow = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME;
        $(byLinkText(REPORTS_MAILING_LISTS_CALL_MW)).click();

        $(byXpath(MW)).shouldBe(visible);
        $(byText("Mailing List")).shouldBe(Condition.visible);
        rootLogger.info("Set checkbox and Set interval - new ML");
        if ( $(byXpath(MW_MAILING_1USER_SELECT)).is(not(checked))) {
            $(byXpath(MW_MAILING_1USER_SELECT)).waitUntil(visible, 2000).click();

        }
        rootLogger.info("Set checkbox and Set interval - old ML");
        if ( $(byXpath(MW_MAILING_1USER_INTERVAL)).is(not(empty))) {
            rootLogger.info("Send Project report - old report");
            $(byXpath(MW_MAILING_1USER_INTERVAL)).clear();
            $(byXpath(MW_MAILING_1USER_INTERVAL)).sendKeys("999");
            $(byXpath(MW_MAILING_LIST_BTN_SEND_NOW)).waitUntil(visible, 10000).waitUntil(enabled, 10000).click();
            sleep(5000);
        }
            else {
            $(byXpath(MW_MAILING_1USER_INTERVAL)).sendKeys("999");
            rootLogger.info("Send Project report - new report");
            sleep(500);
            $(byXpath(MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW)).waitUntil(enabled, 10000).click();
            $(byXpath(MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW)).waitUntil(hidden, 20000);
            sleep(5000);
            $(byXpath(MW_MAILING_LIST_BTN_SEND_NOW)).waitUntil(visible, 10000).waitUntil(enabled, 10000);
            sleep(1000);
        }
        rootLogger.info("Report was sent");
        $(byXpath(MW)).pressEscape();
        $(byText("Mailing List")).shouldNotBe(Condition.visible);
        sleep(500);

        return actualMailingListRow;
    }
    public static void mailingListDeleteReport(String thisMailingListName){
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        String pathToReportRowMenu = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML;
        if ($(byXpath(pathToReportRowMenu)).is(not(visible))) {
            $(byLinkText(thisMailingListName)).waitUntil(visible, 10000).click();
            sleep(1000);
        }
        $(byXpath(pathToReportRowMenu)).click();
        rootLogger.info("Delete list");
        $(byLinkText(REPORTS_MAILING_LISTS_DELETE_MW)).click();
        sleep(500);
        submitConfirmAction();
 //       $(byText(thisMailingListName)).shouldNotBe(Condition.visible);
    }
    public static void validationFieldByName(int randomLength, String fieldName, String submitButton, String errorMsg) {
        rootLogger.info("Validation field test for - "+fieldName);
        $(byName(fieldName)).clear();
        $(byName(fieldName)).sendKeys(Utils.getRandomString(randomLength));
        rootLogger.info("Entered random string - "+randomLength+"letter length" );
        $(byXpath(submitButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(errorMsg)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+errorMsg);
    }
    public static void validationFieldByXpath(int randomLength, String fieldName, String submitButton, String errorMsg) {
        rootLogger.info("Validation field test for - "+fieldName);
        $(byXpath(fieldName)).clear();
        $(byXpath(fieldName)).sendKeys(Utils.getRandomString(randomLength));
        rootLogger.info("Entered random string - "+randomLength+"letter length" );
        $(byXpath(submitButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(errorMsg)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+errorMsg);
    }
}
