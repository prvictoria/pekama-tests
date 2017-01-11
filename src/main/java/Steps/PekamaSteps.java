package Steps;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class PekamaSteps {
    static final Logger rootLogger = LogManager.getRootLogger();
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
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
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }
    public void  submitCookie(){
        rootLogger.info("Check if cookie present");
        rootLogger.info("cookie were submitted");
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

    public String mailingListCreateNew(String thisMailingListName){
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH)).click();
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).shouldBe(visible);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).shouldBe(visible);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT)).sendKeys(thisMailingListName);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).click();
        sleep(300);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).pressEscape();
        $(byText(thisMailingListName)).shouldBe(visible);
        rootLogger.info("Mailing List was created - "+ thisMailingListName);
        return thisMailingListName;
    }

    public static String mailingListSendReport(String thisMailingListName){
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        $(byXpath(REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML)).click();
        String actualMailingListRow = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME;
        //select open
        $(byLinkText(REPORTS_MAILING_LISTS_CALL_MW)).click();
        $(byXpath(MW)).shouldBe(visible);
        $(byText("Mailing List")).shouldBe(Condition.visible);

        rootLogger.info("Take checkbox value");
        String checkboxValue = $(byXpath(MW_MAILING_1USER_SELECT)).getSelectedValue();
//        if (checkboxValue == null){
            $(byXpath(MW_MAILING_1USER_SELECT)).click();
            rootLogger.info("Set checkbox");
//        }
        rootLogger.info("Set interval");
        $(byXpath(MW_MAILING_1USER_INTERVAL)).sendKeys("999");
        rootLogger.info("Send Project report");
        $(byXpath(MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW)).click();
        $(byXpath(MW_MAILING_LIST_BTN_SEND_NOW)).waitUntil(exactText("Send Now"), 10000);
        rootLogger.info("Report was sent");
        $(byXpath(MW)).pressEscape();
        $(byText("Mailing List")).shouldNotBe(Condition.visible);
        return actualMailingListRow;
    }
    public static void mailingListDeleteReport(String thisMailingListName){
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        $(byXpath(REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML)).click();
        String actualMailingListRow = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME;
        rootLogger.info("Delete list");
        $(byLinkText(REPORTS_MAILING_LISTS_DELETE_MW)).click();
        submitConfirmAction();
        $(byText(thisMailingListName)).shouldNotBe(Condition.visible);
    }
}
