package Steps;
import Utils.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaProject.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.BTN_DELETE_MEMBER;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Steps.StepsHttpAuth.*;
import static Utils.Utils.getDate;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class StepsPekama implements StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        httpAuthUrl(urlLogIn);
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
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        loginField_Password.sendKeys(PEKAMA_USER_PASSWORD);
        loginButton_Login.click();
        sleep(1000);
        btnLogin.shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
        sleep(2000);
    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        submitCookie();
        //collapseZopim(true);
        hideZopim();
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
        sleep(1000);
        rootLogger.info("Valid Credentials were submitted");

    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL, String USER_PEKAMA_PASSWORD){
        submitCookie();
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        loginButton_Login.click();
        btnLogin.shouldBe(Condition.not(visible));
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
        REPORTS_MAILING_SAVE_SEARCH.waitUntil(visible, 5000).click();
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
//        String pathToReport = REPORTS_MAILING_LISTS+mailingListRowByName+REPORTS_MAILING_LISTS_BTN_CALL_ML;
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
        rootLogger.info("Set checkbox and Set interval - old ML");
        if ( MW_MAILING_1USER_INTERVAL.is(not(empty))) {
            rootLogger.info("Send ProjectValues report - old report");
            MW_MAILING_1USER_INTERVAL.clear();
            MW_MAILING_1USER_INTERVAL.sendKeys("999");
            MW_MAILING_LIST_BTN_SEND_NOW.waitUntil(visible, 10000).waitUntil(enabled, 10000).click();
            sleep(5000);
        }
        else {
            MW_MAILING_1USER_INTERVAL.sendKeys("999");
            rootLogger.info("Send ProjectValues report - new report");
            sleep(500);
            MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW.waitUntil(enabled, 10000).click();
            MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW.waitUntil(hidden, 20000);
            sleep(5000);
            MW_MAILING_LIST_BTN_SEND_NOW.waitUntil(visible, 10000).waitUntil(enabled, 10000);
            sleep(1000);
        }
        rootLogger.info("Report was sent");
        MW.pressEscape();
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
        REPORTS_MAILING_LISTS_DELETE_MW.click();
        sleep(500);
        submitConfirmAction();
        //       $(byText(thisMailingListName)).shouldNotBe(Condition.visible);
    }

    public static void validationFieldByName(int randomLength, String fieldName, String submitButton, String errorMsg) {
        rootLogger.info("Validation field test for - "+fieldName);
        $(byName(fieldName)).clear();
        $(byName(fieldName)).sendKeys(Utils.randomString(randomLength));
        rootLogger.info("Entered random string - "+randomLength+"letter length" );
        $(byXpath(submitButton)).shouldBe(Condition.enabled).click();
        sleep(500);
        $(byText(errorMsg)).shouldBe(Condition.visible);
        rootLogger.info("Validation present - "+errorMsg);
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
        buttonName.waitUntil(visible, 10000);
        buttonName.waitUntil(enabled, 10000);
        buttonName.click();
        sleep(500);
        rootLogger.info("Button was clicked");
    }
    public static boolean waitForModalWindow(String modalTitle) {
        rootLogger.info("Wait for '"+modalTitle+"' modal window");
        MW.shouldBe(visible);
        //MW.should(matchText(modalTitle));
        MW.shouldHave(text(modalTitle));
        rootLogger.info("modal window '"+modalTitle+"' was opened");
        return true;
    }
    public static void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, String inputValue) {
        rootLogger.info("select - "+inputValue);
        uiSelectName.click();
        fillField(uiSelectInput, inputValue);
        CSS_SelectHighlighted.click();
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
        $(byText(textString)).waitUntil(visible, 10000);
        $$(byText(textString)).filter(visible).shouldHaveSize(size);
        return true;
    }
    public static boolean checkText(String textString) {
        $(byText(textString)).waitUntil(visible, 15000);
        $$(byText(textString)).filter(visible).shouldHaveSize(1);
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
    public static void createProject(String projectType, String projectDefining, String projectName) {
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
    //in root
    public static void createFileInRoot(SelenideElement fileType, String fileName) {
        projectTabDocs.click();
        TAB_DOCS_BTN_ADD.click();
        TAB_DOC_NEW_DOCUMENT.shouldBe(Condition.visible).click();
        deployFileTemplate(fileType, fileName);

    }
    public static void deployFileTemplate(SelenideElement fileType, String fileName) {
        waitForModalWindow(TITLE_MW_ADD_DOCUMENT);
        MW_DEPLOY_DOC_BTN_CREATE.shouldBe(disabled);
        fileType.shouldBe(Condition.visible).click();
        fillField(MW_DEPLOY_DOC_INPUT_FILE_NAME, fileName);
        submitEnabledButton(MW_DEPLOY_DOC_BTN_CREATE);
        MW.shouldNotBe(Condition.visible);
        $(byText(fileName)).shouldBe(Condition.visible);
        rootLogger.info(fileName+" - file present");
    }
    public static void createFolder(String folderName) {
        waitForModalWindow(TITLE_MW_NEW_FOLDER);
        MW_BTN_SAVE.shouldBe(disabled);
        fillField(MW_NEW_FOLDER_INPUT_NAME, folderName);
        submitEnabledButton(MW_BTN_SAVE);
        MW.shouldNotBe(Condition.visible);
        $(byText(folderName)).shouldBe(Condition.visible);
        rootLogger.info(folderName+" - Folder present");
    }
    public static void createFolderInRoot(String folderName) {
        TAB_DOCS_BTN_ADD.click();
        rootLogger.info("Add folder");
        TAB_DOC_ADD_FOLDER.shouldBe(Condition.visible).click();
        createFolder(folderName);
        rootLogger.info(folderName+" - Folder present");
    }
    public static void createTask(String taskName) {
        projectTabTasks.click();
        TAB_TASKS_ADD.click();
        TAB_TASKS_NEW_TASK.shouldBe(visible).click();
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_DeployTask_Title, taskName);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
        $$(byText(taskName)).shouldHaveSize(1);
        rootLogger.info(taskName+" - Task created");
    }
    public static void createEvent(String eventTypeName) {
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
    }
    public static void createCharge(String chargeType, String currency, String price) {
        projectTabFin.click();
        TAB_CHARGES_ADD.click();
        rootLogger.info("Create charge");
        waitForModalWindow(TITLE_MW_CHARGE);
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, chargeType);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, currency);
        fillField(MW_CHARGES_INPUT_PRICE, price);
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNot(visible);
        checkTextNotPresent(placeholderEmptyList);
        checkText(CHARGES_TYPE_ASSOCIATE);
        checkText(getDate(0));
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



    @Test
    public void fileMenuMakeAction (){
        fileMenuMakeAction (TAB_DOCS_FILES_MENU_RENAME, "new name");
    }
}
