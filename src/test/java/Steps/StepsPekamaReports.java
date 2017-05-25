package Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.PekamaReportsContacts.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.*;
import static Steps.ObjectContact.contactType.*;
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
    public static void clickDeleteAndConfirm(){
        sleep(2000);
        if(REPORTS_DELETE.isDisplayed()) {
            REPORTS_DELETE.click();
            submitConfirmAction();
            sleep(2000);
            waitForSpinnerNotPresent();
        }
    }
    public static void deleteAllObjectOnReportPage(String pageUrl){
        rootLogger.info("Delete all objects on: "+pageUrl);
        openUrlIfActualNotEquals(pageUrl);
        if(REPORTS_LIST_ROWS.size()==0){
            rootLogger.info("No objects");
            return;
        }
        else {
            REPORTS_ALL_CHECKBOX.setSelected(true);
            sleep(2000);
            checkColourInSelector(REPORTS_ALL_CHECKBOX, "42, 164, 245");
            if(REPORTS_LIST_ROWS.size()==0){
                rootLogger.info("No objects");
                return;
            }
            clickDeleteAndConfirm();
            checkTextInSelector(REPORTS_PLACEHOLDER_NO_DATA, PLACEHOLDER_NO_DATA);
        }
    }
    public static void deleteAllProjects(){
        rootLogger.info("Delete all Tasks");
        deleteAllObjectOnReportPage(URL_ReportsProjects);
    }
    public static void deleteAllTasks(){
        rootLogger.info("Delete all Tasks");
        deleteAllObjectOnReportPage(URL_ReportsTasks);
    }
    public static void deleteAllEvents(){
        rootLogger.info("Delete all Events");
        deleteAllObjectOnReportPage(URL_ReportsEvents);
    }
    public static void deleteAllCharges(){
        rootLogger.info("Delete all Charges");
        deleteAllObjectOnReportPage(URL_ReportsCharges);
    }
    public static void deleteAllContacts(){
        rootLogger.info("Delete all Contacts");
        deleteAllObjectOnReportPage(URL_ReportsContacts);
    }

    public static boolean reportsCheckContactRow(Integer rowCount, String name, String surname, String email, String country) {
        String row = REPORTS_ROW_BY_INDEX_LIST(rowCount);
        SelenideElement contactName = $(byXpath(row+REPORTS_CONTACT_ROW_NAME));
        SelenideElement contactEmail = $(byXpath(row+REPORTS_CONTACT_ROW_EMAIL));
        SelenideElement contactCountry = $(byXpath(row+REPORTS_CONTACT_ROW_COUNTRY));
        contactName.shouldHave(text(name+" "+surname));
        contactEmail.shouldHave(text(email));
        contactCountry.shouldHave(text(country));
        return  true;
    }
    public static Boolean reportsCheckContactRow(contactType contactType, Integer rowCount, ObjectContact contact,  String projects, String charges,  Integer relationCount) {
        rootLogger.info("Check contact row #"+rowCount);
        SelenideElement contactName = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_NAME);
        SelenideElement contactEmail = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_EMAIL);
        SelenideElement contactCountry = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_COUNTRY);
        SelenideElement contactCompany = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_COMPANY);
        SelenideElement contactProjects = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_PROJECTS);
        SelenideElement contactCharges = elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_CHARGES_TOTAL);
        ElementsCollection contactRelations = $$(byXpath(REPORTS_ROW_BY_INDEX_LIST(rowCount)+REPORTS_CONTACT_ROW_RELATIONS));
        if(rowCount<10) {
            if (contactType==PERSON) {
                contactName.shouldHave(text(contact.contactNameSurname));
                if (contact.contactCompany != null) {
                    contactCompany.shouldHave(text(contact.contactCompany));
                }
            }
            if(contactType==COMPANY){
                contactName.shouldHave(text(contact.contactLegalEntity));
            }
            if (contactType==PERSON || contactType==COMPANY){
                if (contact.contactEmail != null) {
                    contactEmail.shouldHave(text(contact.contactEmail));
                }
                if (contact.contactCountry != null) {
                    contactCountry.shouldHave(text(contact.contactCountry));
                }
                if (projects==null) {
                    contactProjects.shouldHave(text("no projects"));
                }
                if (projects!=null) {
                    contactProjects.shouldHave(text(projects));
                }
                if (charges==null) {
                    contactCharges.shouldHave(text("no charges"));
                }
                if (charges!=null) {
                    contactCharges.shouldHave(text(charges));
                }
                if (relationCount != null) {
                    contactRelations.shouldHaveSize(relationCount);
                }
            }
        }
        return  true;
    }
    public static SelenideElement elementInRowListReport(Integer rowCount, final String path){
        if(rowCount>10){Assert.fail("Only 10 rows on the page");}
        String rowBtn = REPORTS_ROW_BY_INDEX_LIST(rowCount)+path;
        SelenideElement btn = $(byXpath(rowBtn));
        return btn;
    }
    public static void clickContactNewProject(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACTS_NEW_PROJECT_BTN));
    }
    public static void clickContactNewProject(String contactName){
        String rowBtn = REPORTS_CONTACT_ROW_BY_NAME(contactName)+REPORTS_CONTACTS_NEW_PROJECT_BTN;
        SelenideElement btn = $(byXpath(rowBtn));
        submitEnabledButton(btn);
    }
    public static void clickContactEdit(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACTS_EDIT_BTN));
    }
    public static void clickContactEdit(String contactName){
        String rowBtn = REPORTS_CONTACT_ROW_BY_NAME(contactName)+REPORTS_CONTACTS_EDIT_BTN;
        SelenideElement btn = $(byXpath(rowBtn));
        submitEnabledButton(btn);
    }
    public static void clickContactDelete(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACTS_DELETE_BTN));
        submitConfirmAction();
        sleep(500);
    }
    public static void clickContactDelete(String contactName){
        String rowBtn = REPORTS_CONTACT_ROW_BY_NAME(contactName)+REPORTS_CONTACTS_DELETE_BTN;
        SelenideElement btn = $(byXpath(rowBtn));
        submitEnabledButton(btn);
        submitConfirmAction();
        sleep(500);
    }
    public static void saveContactForm(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACT_FORM_SAVE));
    }
    public static void saveContactForm(String contactName){
        submitEnabledButton($(byXpath(REPORTS_CONTACT_ROW_BY_NAME(contactName)+REPORTS_CONTACT_FORM_SAVE)));
    }
    public static void selectRowListReport(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_SELECT));
    }
    public static void selectContactRow(String contactName){
        submitEnabledButton($(byXpath(REPORTS_CONTACT_ROW_BY_NAME(contactName)+REPORTS_CONTACT_ROW_SELECT)));
    }
    public static void clickContactRowProjectLink(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_PROJECTS));
    }
    public static void clickContactRowChargesLink(Integer rowCount){
        submitEnabledButton(elementInRowListReport(rowCount, REPORTS_CONTACT_ROW_PROJECTS));
    }
    public static void callImportContactModal(){
        if(getActualUrl().equals(URL_ReportsContacts)==false){
            openPageWithSpinner(URL_ReportsContacts);
        }
        submitEnabledButton(REPORTS_BTN_IMPORT);
        waitForModalWindow("Choose CSV file");
    }
    public static Boolean checkActualSortOrderInReports(String order, Boolean orderIsAscending){
        if(order == null|| orderIsAscending == null){
            Assert.fail("Null in parameters not valid");
        }
        if(order!=null) {
            String ActualTaskOrder = REPORTS_SORT_ORDER_TYPE.getText();
            Assert.assertEquals(order, ActualTaskOrder);
            if (orderIsAscending != null) {
                if (orderIsAscending == true) {
                    REPORTS_SORT_ORDER_ASCENDING.shouldBe(visible);
                    return true;
                } else {
                    REPORTS_SORT_ORDER_DESCENDING.shouldBe(visible);
                    return true;
                }
            }
            return true;
        }
        else return false;
    }
    public static Boolean selectSortOrderAndCheck(String order, Boolean orderIsAscending){
        if (order != null) {
            clickSelector(REPORTS_SORT_ORDER_TYPE);
            clickSelector(REPORTS_SELECT_SORT_ORDER(order));
            String ActualTaskOrder = REPORTS_SORT_ORDER_TYPE.getText();
            Assert.assertEquals(order, ActualTaskOrder);
            if (orderIsAscending != null) {
                if (orderIsAscending == true) {
                    REPORTS_SORT_ORDER_ASCENDING.shouldBe(visible);
                    return true;
                } else {
                    REPORTS_SORT_ORDER_DESCENDING.shouldBe(visible);
                    return true;
                }
            }
        }
        return false;
    }
    public static void mergeContactsSelected(ObjectContact contact){
        rootLogger.info("Merge selected contacts");
        clickSelector(REPORTS_MERGE);
        waitForModalWindow("Merge Contacts");
        MW_BTN_OK.shouldBe(disabled);
        if(contact.contactType.equals("Company")) {
            selectItemInDropdown(
                    MW_MergeContact_Select,
                    MW_MergeContact_Input,
                    contact.contactLegalEntity);
        }
        if(contact.contactType.equals("Person")) {
            selectItemInDropdown(
                    MW_MergeContact_Select,
                    MW_MergeContact_Input,
                    contact.contactFirstName);
        }
        submitEnabledButton(MW_BTN_OK);
    }

    public static void mergeContactsAll(ObjectContact contact){
        clickSelector(REPORTS_ALL_CHECKBOX);
        clickSelector(REPORTS_MERGE);
        rootLogger.info("Merge all contacts");
        waitForModalWindow("Merge Contacts");
        MW_BTN_OK.shouldBe(disabled);
        if(contact.contactType.equals("Company")) {
            selectItemInDropdown(
                    MW_MergeContact_Select,
                    MW_MergeContact_Input,
                    contact.contactLegalEntity);
        }
        if(contact.contactType.equals("Person")) {
            selectItemInDropdown(
                    MW_MergeContact_Select,
                    MW_MergeContact_Input,
                    contact.contactFirstName);
        }
        submitEnabledButton(MW_BTN_OK);
    }
}
