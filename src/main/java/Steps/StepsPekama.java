package Steps;
import Utils.Utils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.Set;

import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaPersonalSettings.SIGNATURE_TAB_TEXT_EDITOR;
import static Page.PekamaProject.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Steps.StepsHttpAuth.httpAuthUrl;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsPekama implements StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        httpAuthUrl(urlLogIn);
        hideZopim();
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD, String urlLogIn){
        httpAuthUrl(urlLogIn);
        hideZopim();
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        btnSignup.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }
    public void  loginByURL(String PEKAMA_USER_EMAIL, String PEKAMA_USER_PASSWORD, String AUTH_URL){
        httpAuthUrl(AUTH_URL);
        rootLogger.info(AUTH_URL+"URL opened");
        submitCookie();
        hideZopim();
        fillField(loginField_Email,PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        fillField(loginField_Password, PEKAMA_USER_PASSWORD);
        submitEnabledButton(loginButton_Login);
        sleep(4000);
        btnLogin.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(2000);
    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        submitCookie();
        hideZopim();
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        sleep(500);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        sleep(500);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        sleep(1000);
        rootLogger.info("Valid Credentials were submitted");

    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD){
        submitCookie();
        hideZopim();
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        sleep(1000);
        rootLogger.info("Valid Credentials were submitted");

    }
    public static void  submitCookie(){
        rootLogger.info("Check if cookie present");
        sleep(500);
        if ($(byText("Got it!")).isDisplayed()){
            $(byText("Got it!")).click();
            sleep(250);
            rootLogger.info("cookie were submitted");
        }
    }
    public static void waitForSpinnerNotPresent(){
        $(byXpath("//*[@id='progress-indicator']/span")).waitUntil(not(visible), 10000);
    }
    public static void submitConfirmAction(){
        sleep(500);
        MW.shouldBe(visible);
        $(byText("Are you sure?")).shouldBe(Condition.visible);
        rootLogger.info("Confirm action modal window opened");
        MW_BTN_YES.shouldBe(visible).click();
        sleep(500);
        MW.shouldNotBe(visible);
    }
    public static void submitConfirmAction(String modalTitle){
        sleep(500);
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.shouldBe(visible);
        MW.shouldHave(text(modalTitle));
        rootLogger.info("Confirm action modal '"+modalTitle+"' was opened");
        MW_BTN_YES.shouldBe(visible).click();
        sleep(500);
        MW.shouldNotBe(visible);
    }
    public static String mailingListCreateNew(String thisMailingListName){
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH);
        REPORTS_MAILING_SAVE_SEARCH.waitUntil(visible, 15000).click();
        sleep(3000);
        scrollDown();
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE.waitUntil(visible, 10000).shouldBe(disabled);
        rootLogger.info("type"+thisMailingListName);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT.sendKeys(thisMailingListName);
        rootLogger.info("click"+REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE.click();
        sleep(3000);
        $$(byText(thisMailingListName));
        $(byLinkText(thisMailingListName)).waitUntil(visible, 10000);
        REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE.pressEscape();
        sleep(500);
        rootLogger.info("Mailing List was created - "+ thisMailingListName);
        return thisMailingListName;
    }
    public static String mailingListSendReport(String thisMailingListName){
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
        String actualMailingListRow = REPORTS_MAILING_LISTS+ mailingListRowByName;
        if($(byXpath(pathToReport)).isDisplayed()==false){
            $(byLinkText(thisMailingListName)).click();
            sleep(3000);}
        $(byXpath(pathToReport)).click();
        REPORTS_MAILING_LISTS_CALL_MW.click();

        MW.shouldBe(visible);
        $(byText("Mailing List")).shouldBe(Condition.visible);
        rootLogger.info("Verify checkbox value");
            if ( MW_MAILING_1USER_SELECT.is(not(checked))) {
                MW.pressEscape();
                $(byText("Mailing List")).shouldNotBe(Condition.visible);
                sleep(500);
                return true;
            }
            else{return false;}
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
            $(byLinkText(thisMailingListName)).click();
            sleep(1000);

            $(byXpath(pathToReportRowMenu)).click();
            rootLogger.info("Delete list");
            REPORTS_MAILING_LISTS_DELETE_MW.click();
            sleep(500);
            submitConfirmAction();
        }
        rootLogger.info("Reports not present");
    }

    public static void validationFieldByXpath(int randomLength, String fieldName, String submitButton, String errorMsg) {
        rootLogger.info("Validation field test for - "+fieldName);
        $(byXpath(fieldName)).clear();
        $(byXpath(fieldName)).sendKeys(Utils.randomString(randomLength));
        rootLogger.info("Entered random string - "+randomLength+"letter length" );
        $(byXpath(submitButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(errorMsg)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+errorMsg);
    }
    public static void fillField(SelenideElement fieldName, String enteredValue) {
        fieldName.waitUntil(visible, 30000);
        rootLogger.info("Input data");
        fieldName.clear();
        fieldName.shouldHave(Condition.value("")).val(enteredValue);
        fieldName.shouldHave(Condition.value(enteredValue));
        rootLogger.info("This data was entered - "+enteredValue);
   }
    public static void checkInputValue(SelenideElement selector, String enteredValue) {
        selector.shouldBe(visible);
        selector.shouldHave(Condition.value(enteredValue));
        rootLogger.info("This text present in element: "+enteredValue);
    }
    public static void submitEnabledButton(SelenideElement buttonName) {
        buttonName.waitUntil(visible, 15000);
        buttonName.waitUntil(enabled, 15000);
        buttonName.click();
        sleep(500);
        rootLogger.info("Button was clicked");
    }
    public static boolean waitForModalWindow(String modalTitle) {
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.waitUntil(visible, 15000).shouldBe(visible);
        //MW.should(matchText(modalTitle));
        MW.shouldHave(text(modalTitle));
        rootLogger.info("modal window '"+modalTitle+"' was opened");
        return true;
    }
    public static void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, String inputValue) {
        rootLogger.info("select - "+inputValue);
        uiSelectName.shouldBe(visible).click();
        fillField(uiSelectInput, inputValue);
        CSS_SelectHighlighted.waitUntil(visible, 15000).click();
        rootLogger.info("selected - "+inputValue);
    }
    public static String getActualUrl () {
        sleep(1500);
        String currentUrl = url();
        rootLogger.info("opened URL is - "+currentUrl);
        return currentUrl;
    }
    public static boolean waitForTextPresent(String text) {
        rootLogger.info("Wait for - " + text);
        $(byText(text)).waitUntil(exist, 20000);
        if ($(byText(text)) == null) {
            Assert.fail("Text not present on page -" + text);
        }
        return true;
    }
    public static void scrollUp() {
        executeJavaScript("scrollTo(0, -1000)");
    }
    public static void scrollDown() {
        executeJavaScript("scrollTo(0, 1000)");
    }
    public static void collapseZopim(boolean collapse) {
        if(collapse==true){
            executeJavaScript("$zopim.livechat.window.hide()");
            rootLogger.info("Zopim collapsed");}
        if(collapse==false){
            executeJavaScript("$zopim.livechat.window.show()");
            rootLogger.info("Zopim displayed");}
        //JQuery=kill document.querySelectorAll('.zopim').forEach(function(elm){elm.parentNode.removeChild(elm)})
    }
    public static void hideZopim(){
        executeJavaScript("$zopim.livechat.hideAll()");
        rootLogger.info("Zopim collapsed");
    }
    public static void scrollCustom(int value) {
        executeJavaScript("scrollTo(0, "+value+")");
    }
    public static boolean checkText(String textString, int size) {
        $(byText(textString)).waitUntil(exist, 20000);
        $$(byText(textString)).filter(visible).shouldHaveSize(size);
        return true;
    }
    public static boolean checkValue(String textString, int size) {
        $(byValue(textString)).waitUntil(exist, 20000);
        $$(byValue(textString)).filter(visible).shouldHaveSize(size);
        return true;
    }
    public static boolean checkText(String textString) {
        $(byText(textString)).waitUntil(exist, 20000);
        $$(byText(textString)).filter(visible).shouldHaveSize(1);
        return true;
    }
    public static boolean checkValue(String textString) {
        $(byValue(textString)).waitUntil(exist, 20000);
        $$(byValue(textString)).filter(visible).shouldHaveSize(1);
        return true;
    }

    public static boolean checkTextNotPresent(String textString, int waitTime) {
        sleep(waitTime);
        $$(byText(textString)).filter(visible).shouldHaveSize(0);
        return true;
    }
    public static boolean checkTextNotPresent(String textString) {
        sleep(3000);
        $$(byText(textString)).filter(visible).shouldHaveSize(0);
        return true;
    }

    public static void selectTeam(String... args) {
        String searchedRadio = String.format(MW_SHARE_PROJECT_SELECT_TEAM, args);
        $(byXpath(searchedRadio)).shouldBe(visible);
        $(byXpath(searchedRadio)).click();
    }

    public static void fileMenuMakeAction(String actionName, String... args) {
        String menu = String.format(TAB_DOCS_FILES_MENU_OPEN, args);
        $(byXpath(menu)).shouldBe(visible);
        $(byXpath(menu)).click();
        String action = String.format(actionName, args);
        $(byXpath(action)).shouldBe(visible);
        $(byXpath(action)).click();
        rootLogger.info("Action done");
    }
    public static void clickFileRow(String... args) {
        String row = String.format(TAB_DOCS_FILES_EXPAND_FILE, args);
        $(byXpath(row)).shouldBe(visible);
        $(byXpath(row)).click();
        rootLogger.info(args+" - row opened");
    }
    public static void clickFolderRow(String... args) {
        String row = String.format(TAB_DOCS_FILES_EXPAND_FOLDER, args);
        $(byXpath(row)).shouldBe(visible);
        $(byXpath(row)).click();
        rootLogger.info(args+" - row opened");
    }
    public static void createProject(
            String projectType,
            String projectDefining,
            String projectName) {
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("Select project type, actual: "+projectType);
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        rootLogger.info("Select defining, actual: "+projectDefining);
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        rootLogger.info("Fill title");
        fillField(MW_Project_Title, projectName);
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(exist);
        sleep(1000);
        checkText(projectName);
        rootLogger.info("Created project: "+projectName);
    }
    public static String createProject() {
        String projectType = CaseType.TRADEMARK.getValue();
        String projectDefining = Countries.PITCAIRN_ISLANDS.getValue();
        String projectName = "DEFAULT_PROJECT_"+randomString(10);
        waitForModalWindow(TILE_MW_PROJECT);
        rootLogger.info("Select project type, actual: "+projectType);
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, projectType);
        rootLogger.info("Select defining, actual: "+projectDefining);
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, projectDefining);
        rootLogger.info("Fill title");
        fillField(MW_Project_Title, projectName);
        submitEnabledButton(MW_ProjectFinishButton);
        MW.waitUntil(not(visible), 20000);
        sleep(1000);
        checkText(projectName);
        rootLogger.info("Created project: "+projectName);
        return projectName;
    }
    //in root in Project
    public static void createFileInRoot(SelenideElement fileType, String fileName) {
        PROJECT_TAB_DOCS.waitUntil(visible, 15000).click();
        TAB_DOCS_BTN_ADD.waitUntil(enabled, 15000).click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        modalWindowDeployFileTemplate(fileType, fileName);

    }
    public static void modalWindowDeployFileTemplate(SelenideElement fileType, String fileName) {
        waitForModalWindow(TITLE_MW_ADD_DOCUMENT);
        MW_DEPLOY_DOC_BTN_CREATE.shouldBe(disabled);
        fileType.shouldBe(Condition.visible).click();
        fillField(MW_DEPLOY_DOC_INPUT_FILE_NAME, fileName);
        submitEnabledButton(MW_DEPLOY_DOC_BTN_CREATE);
        MW.shouldNotBe(Condition.visible);
        $(byText(fileName)).shouldBe(Condition.visible);
        rootLogger.info(fileName+" - file present");
    }
    public static void modalWindowCreateFolder(String folderName) {
        waitForModalWindow(TITLE_MW_NEW_FOLDER);
        MW_BTN_SAVE.shouldBe(disabled);
        fillField(MW_NEW_FOLDER_INPUT_NAME, folderName);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(Condition.visible);
        $(byText(folderName)).shouldBe(Condition.visible);
        rootLogger.info(folderName+" - Folder present");
    }
    public static String createNumber() {
        String codeType = "Equinox code";
        String codeValue = "2000/17/55-asd";
        rootLogger.info("Create "+codeType+"with value - "+codeValue);
        PROJECT_TAB_INFO.waitUntil(visible, 15000).click();
        scrollDown();
        selectItemInDropdown(TAB_INFO_NumberNewSelect, TAB_INFO_NumberNewField, codeType);
        fillField(TAB_INFO_NumberReferenceField, codeValue);
        submitEnabledButton(TAB_INFO_NumberAdd);
        TAB_INFO_NumberRow01Type.shouldHave(text(codeType));
        rootLogger.info(codeType+" - Number was created");
        return codeValue;
    }
    public static String createClassification() {
        //default
        String classNumber = "12";
        String classDescripton = "Class description";
        String classType = "Up-to-date";
        PROJECT_TAB_INFO.waitUntil(visible, 15000).click();
        scrollDown();
        TAB_INFO_ClassesAdd.waitUntil(visible, 20000).click();
        waitForModalWindow(mwClasses_Title);
        MW_BTN_OK.shouldBe(disabled);
        mwClasses_SelectClassType.shouldHave(text(classType));
        fillField(mwClasses_FieldClass, classNumber);
        fillField(mwClasses_FieldDescription, classDescripton);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(classType)).shouldHaveSize(1);
        $$(byText(classDescripton)).shouldHaveSize(1);
        rootLogger.info(classDescripton+" - Class was created");
        return classType;
    }
    public static String createFolderInRoot(String folderName) {
        PROJECT_TAB_DOCS.waitUntil(visible, 15000).click();
        TAB_DOCS_BTN_ADD.waitUntil(enabled, 15000).click();
        rootLogger.info("Add folder");
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        modalWindowCreateFolder(folderName);
        rootLogger.info(folderName+" - Folder present");
        return folderName;
    }
    public static String createTask(String taskName) {
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
        TAB_TASKS_ADD.waitUntil(enabled, 15000).click();
        TAB_TASKS_NEW_TASK.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_DeployTask_Title, taskName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(taskName)).shouldHaveSize(1);
        rootLogger.info(taskName+" - Task created");
        return taskName;
    }
    public static String createEvent(String eventTypeName) {
        scrollUp();
        rootLogger.info("Deploy new event");
        projectButtonPlus.shouldBe(visible).click();
        projectPlusNewEvent.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        MW_INPUT_DATE.click();
        MW_INPUT_DATE.pressEscape();
        fillField(MW_EVENT_INPUT_INFO, LOREM_IPSUM_SHORT);
        selectItemInDropdown(MW_EVENT_SELECT_TYPE, MW_EVENT_INPUT_TYPE, eventTypeName);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(visible);
        $$(byText(eventTypeName)).filter(visible).shouldHaveSize(1);
        rootLogger.info(eventTypeName+" - Event created");
        return eventTypeName;
    }
    public static String createCharge(String chargeType, String currency, String price) {
        PROJECT_TAB_CHARGES.waitUntil(visible, 15000).click();
        TAB_CHARGES_ADD.waitUntil(enabled, 15000).click();
        rootLogger.info("Create charge");
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, chargeType);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, currency);
        fillField(MW_CHARGES_INPUT_PRICE, price);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        return  chargeType;
    }

    public static void selectOption(SelenideElement optionSelector,String optionName) {
        optionSelector.selectOption(new String[]{optionName});
    }
    public static boolean checkMember(String email) {
        String row = String.format(BTN_DELETE_MEMBER, email);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(email+" - member is the Team");
        return true;
    }
    public static void deleteMember(String email) {
        String row = String.format(BTN_DELETE_MEMBER, email);
        $(byXpath(row)).shouldBe(visible);
        $(byXpath(row)).click();
        submitConfirmAction();
        $(byXpath(row)).shouldNotBe(visible);
        rootLogger.info(email+" - member was deleted");
    }
    public static void openPageWithSpinner(String reportPage){
        open(reportPage);
        sleep(3000);
        waitForSpinnerNotPresent();
        rootLogger.info(reportPage+" - is opened");
    };

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
    public static SelenideElement valueGetRowByName(String valueName) {
        String row = String.format(settingsValueRow, valueName);
        SelenideElement valueRow = $(byXpath(row));
        return valueRow;
    }
    public static SelenideElement valueGetRowState(String valueName) {
        String row = String.format(settingsValueState, valueName);
        SelenideElement valueRow = $(byXpath(row));
        return valueRow;
    }
    public static SelenideElement valueGetRowEdit(String valueName) {
        String row = String.format(settingsValueEdit, valueName);
        SelenideElement valueRow = $(byXpath(row));
        return valueRow;
    }
    public static SelenideElement valueGetRowDelete(String valueName) {
        String row = String.format(settingsValueDelete, valueName);
        SelenideElement valueRow = $(byXpath(row));
        return valueRow;
    }

    public static boolean valueCheckRowIsDisplayed(String valueName, boolean present) {
        SelenideElement row = valueGetRowByName(valueName);
        sleep(1000);
        if (present){
            row.waitUntil(visible, 20000);}
        if (!present){
            row.waitUntil(not(visible), 20000);
        }
        return true;
    }
    public static boolean valueCheckStatusState(String valueName, String state) {
        SelenideElement row = valueGetRowState(valueName);
        row.waitUntil(visible, 20000);
        row.shouldHave(text(state));
        return true;
    }
    public static boolean valueDelete(String valueName) {
        SelenideElement btnDelete = valueGetRowDelete(valueName);
        rootLogger.info(btnDelete);
        btnDelete.click();
        submitConfirmAction();
        return true;
    }
    public static boolean checkPageTitle(String expectedTitle) {
        int i=0;
        while (i>20){
            getWebDriver().getTitle();
            sleep(1000);
            i++;
            if(getWebDriver().getTitle()!=null){break;}
        }
        if (expectedTitle.equals(getWebDriver().getTitle())==false){
            rootLogger.debug("false");
            return false;
        }
        rootLogger.debug("true");
        return true;
    }
    public static void switchToChildWindow() {
        for(String winHandle : getWebDriver().getWindowHandles()){
            rootLogger.info(winHandle);
            switchTo().window(winHandle);
            getActualUrl();
        }
    }
    public static boolean checkThatWindowsQtyIs(int windowsQty) {
        Set<String> windows = getWebDriver().getWindowHandles();
        rootLogger.info("Actual windows qty is: "+windows.size());
        if (windows.size()!=windowsQty){
            rootLogger.debug("false");
            return false;
        }
        rootLogger.debug("true");
        return true;
    }
    public static void handlingWindow(String expectedTitle) {
        // Store the current window handle
        String winHandleBefore = getWebDriver().getWindowHandle();

        // Perform the click operation that opens new window

        // Switch to new window opened
        for(String winHandle : getWebDriver().getWindowHandles()){
            getWebDriver().switchTo().window(winHandle);
        }
        // Perform the actions on new window

        // Close the new window, if that window no more required
        getWebDriver().close();

        // Switch back to original browser (first window)
        getWebDriver().switchTo().window(winHandleBefore);

        // Continue with original browser (first window)
    }

    public static String fillTextEditor(String message){
        SIGNATURE_TAB_TEXT_EDITOR.click();
        SIGNATURE_TAB_TEXT_EDITOR.clear();
        SIGNATURE_TAB_TEXT_EDITOR.sendKeys(message);
        SIGNATURE_TAB_TEXT_EDITOR.shouldHave(text(message));
        rootLogger.info("Next text was entered: "+message);
        sleep(500);
        return message;
    }
}
