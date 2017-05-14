package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.TestsStrings.PLACEHOLDER_NO_DATA;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by VatslauX on 02-May-17.
 */
public class StepsPekamaReports extends StepsFactory {
    static final Logger rootLogger = LogManager.getRootLogger();
    public static String mailingListCreateNew(String thisMailingListName){
        rootLogger.info("Create new mailing list");
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH);
        REPORTS_MAILING_SAVE_SEARCH.waitUntil(visible, 15000).click();
        sleep(3000);
        scrollDown();
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("type"+thisMailingListName);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT.sendKeys(thisMailingListName);
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE
                .waitUntil(visible, 10000)
                .shouldBe(enabled)
                .click();
        sleep(3000);
        //$$(byText(thisMailingListName));
        $(byLinkText(thisMailingListName)).waitUntil(visible, 10000);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE.pressEscape();
        sleep(500);
        rootLogger.info("Mailing List was created - "+ thisMailingListName);
        return thisMailingListName;
    }
    public static String mailingListSendReport(String thisMailingListName){
        rootLogger.info("Send report");
        String mailingListRowByName = "//li[//a[contains(.,'"+thisMailingListName+"')]]";
        String pathToReport = "//*[@class='search-list']//button[@uib-dropdown-toggle]";
        String actualMailingListRow = REPORTS_MAILING_LISTS+ mailingListRowByName;
        if($(byXpath(pathToReport)).isDisplayed()==false){
            $(byLinkText(thisMailingListName)).click();
            sleep(3000);}
        $(byXpath(pathToReport)).click();
        REPORTS_MAILING_LISTS_CALL_MW.click();

        MW.shouldBe(visible);
        $(byText("Mailing List")).shouldBe(Condition.visible);
        rootLogger.info("Set checkbox and Set interval - new ML");
        if ( MW_MAILING_1USER_SELECT.is(not(checked))) {
            MW_MAILING_1USER_SELECT.waitUntil(visible, 2000).click();

        }
        if ( MW_MAILING_1USER_INTERVAL.is(not(empty))) {
            rootLogger.info("Set checkbox and Set interval - old ML detected");
            MW_MAILING_1USER_INTERVAL.clear();
            MW_MAILING_1USER_INTERVAL.sendKeys("999");
            MW_MAILING_LIST_BTN_SEND_NOW.waitUntil(visible, 10000).waitUntil(enabled, 10000).click();
            sleep(5000);
        }
        else {
            MW_MAILING_1USER_INTERVAL.sendKeys("999");
            rootLogger.info("Send new report");
            sleep(500);
            MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW.waitUntil(enabled, 60000).click();
            MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW.waitUntil(hidden, 60000);
            sleep(2000);
            MW_MAILING_LIST_BTN_SEND_NOW.waitUntil(visible, 60000).waitUntil(enabled, 30000);
            sleep(1000);
        }
        rootLogger.info("Report was sent");
        MW.pressEscape();
        $(byText("Mailing List")).shouldNotBe(Condition.visible);
        sleep(500);

        return actualMailingListRow;
    }
    public static boolean mailingListCheckboxValue(String thisMailingListName){
        String mailingListRowByName = "//li[//a[contains(.,'"+thisMailingListName+"')]]";
        String pathToReport = "//*[@class='search-list']//button[@uib-dropdown-toggle]";
        String actualMailingListRow = REPORTS_MAILING_LISTS+mailingListRowByName;
        if($(byXpath(pathToReport)).isDisplayed()==false){
            $(byLinkText(thisMailingListName)).click();
            sleep(3000);}
        $(byXpath(pathToReport)).click();
        REPORTS_MAILING_LISTS_CALL_MW.click();

        MW.shouldBe(visible);
        $(byText("Mailing List")).shouldBe(Condition.visible);
        rootLogger.info("Verify checkbox value");
        sleep(3000);
        if ( MW_MAILING_1USER_SELECT.is(not(checked))) {
            MW.pressEscape();
            $(byText("Mailing List")).shouldNotBe(Condition.visible);
            sleep(500);
            return true;
        }
        else{return false;}
    }
    public static void mailingListDeleteReport(String thisMailingListName){
        rootLogger.info("Delete mailing list");
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        String pathToReportRowMenu = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML;
        if ($(byXpath(pathToReportRowMenu)).is(not(visible))) {
            $(byLinkText(thisMailingListName)).waitUntil(visible, 10000).click();
            sleep(1000);
        }
        $(byXpath(pathToReportRowMenu)).click();
        rootLogger.info("Delete list");
        REPORTS_MAILING_LISTS_DELETE_MW.click();
        sleep(500);
        submitConfirmAction();
        //       $(byText(thisMailingListName)).shouldNotBe(Condition.visible);
    }
    public static void mailingListDetectAndDelete(String thisMailingListName){
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ thisMailingListName +"')]]";
        String pathToReportRowMenu = REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML;
        if ($(byLinkText(thisMailingListName)).isDisplayed()==false){
            sleep(5000);}
        while ($(byLinkText(thisMailingListName)).is(visible)) {
            rootLogger.info("Mailing list detected: "+thisMailingListName);
            $(byLinkText(thisMailingListName)).waitUntil(visible, 10000).click();
            sleep(1000);

            $(byXpath(pathToReportRowMenu)).waitUntil(visible, 10000).click();
            rootLogger.info("Delete list");
            REPORTS_MAILING_LISTS_DELETE_MW.waitUntil(visible, 10000).click();
            submitConfirmAction();
            sleep(2000);
        }
        rootLogger.info("Reports not present");
    }
    public static void deleteAllProjects(){
        rootLogger.info("Delete all Projects");
        openPageWithSpinner(URL_ReportsProjects);
        if($(byText(PLACEHOLDER_NO_DATA)).isDisplayed()){
            rootLogger.info("No charges in reports");
            return;
        }
        else {
            REPORTS_AllCheckbox.setSelected(true);
            sleep(2000);
            String selectedColor = REPORTS_AllCheckbox.getCssValue("color");
            Assert.assertTrue(selectedColor.equals("rgb(42, 164, 245)"));
            REPORTS_DELETE.waitUntil(visible, 15000).click();
            submitConfirmAction();
            sleep(4000);
        }
    }
    public static void deleteAllTasks(){
        rootLogger.info("Delete all Tasks");
        openPageWithSpinner(URL_ReportsTasks);
        if($(byText(PLACEHOLDER_NO_DATA)).isDisplayed()){
            rootLogger.info("No charges in reports");
            return;
        }
        else {
            REPORTS_AllCheckbox.setSelected(true);
            sleep(2000);
            String selectedColor = REPORTS_AllCheckbox.getCssValue("color");
            Assert.assertTrue(selectedColor.equals("rgb(42, 164, 245)"));
            REPORTS_DELETE.waitUntil(visible, 15000).click();
            submitConfirmAction();
            sleep(4000);
        }
    }
    public static void deleteAllEvents(){
        rootLogger.info("Delete all Events");
        openPageWithSpinner(URL_ReportsEvents);
        if($(byText(PLACEHOLDER_NO_DATA)).isDisplayed()){
            rootLogger.info("No charges in reports");
            return;
        }
        else {
            REPORTS_AllCheckbox.setSelected(true);
            sleep(2000);
            String selectedColor = REPORTS_AllCheckbox.getCssValue("color");
            Assert.assertTrue(selectedColor.equals("rgb(42, 164, 245)"));
            REPORTS_EVENTS_DELETE.waitUntil(visible, 15000).click();
            submitConfirmAction();
            sleep(4000);
        }
    }
    public static void deleteAllCharges(){
        rootLogger.info("Delete all Charges");
        openPageWithSpinner(URL_ReportsCharges);
        if($(byText(PLACEHOLDER_NO_DATA)).isDisplayed()){
            rootLogger.info("No charges in reports");
            return;
        }
        else {
            REPORTS_AllCheckbox.setSelected(true);
            sleep(2000);
            String selectedColor = REPORTS_AllCheckbox.getCssValue("color");
            Assert.assertTrue(selectedColor.equals("rgb(42, 164, 245)"));
            REPORTS_DELETE.waitUntil(visible, 15000).click();
            submitConfirmAction();
            sleep(4000);
        }
    }
    public static void deleteAllContacts(){
        rootLogger.info("Delete all Contacts");
        openPageWithSpinner(URL_ReportsContacts);
        REPORTS_AllCheckbox.waitUntil(visible, 20000).click();
        REPORTS_DELETE.waitUntil(visible, 20000).click();
        submitConfirmAction();
        sleep(4000);
    }
    public static boolean reportsCheckContactRow(int rowCount, String name, String surname, String email, String country) {
        String count = Integer.toString (rowCount);
        String row = String.format(REPORTS_ContactRowByCount, count);
        SelenideElement contactName = $(byXpath(row+REPORTS_ContactNameSurname));
        SelenideElement contactEmail = $(byXpath(row+REPORTS_ContactEmail));
        SelenideElement contactCountry = $(byXpath(row+REPORTS_ContactCountry));
        contactName.shouldHave(text(name+" "+surname));
        contactEmail.shouldHave(text(email));
        contactCountry.shouldHave(text(country));
        return  true;
    }
}
