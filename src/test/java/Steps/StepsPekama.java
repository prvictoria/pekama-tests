package Steps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriverException;
import java.util.Set;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.DROPDOWN_PROJECT_TEMPLATES_LIST;
import static Page.PekamaLogin.*;
import static Page.PekamaPersonalSettings.*;
import static Page.PekamaProject.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Utils.Utils.getDate;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsPekama implements StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();
    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        openUrlWithBaseAuth(urlLogIn);
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
        openUrlWithBaseAuth(urlLogIn);
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
        openUrlWithBaseAuth(AUTH_URL);
        rootLogger.info(AUTH_URL+"URL opened");
        fillField(loginField_Email,PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        fillField(loginField_Password, PEKAMA_USER_PASSWORD);
        sleep(3000);
        submitCookie();
        hideZopim();
        submitEnabledButton(loginButton_Login);
        btnLogin.waitUntil(not(visible), 15000);
        rootLogger.info("Valid Credentials were submitted");
    }
    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        hideZopim();
        submitCookie();
        loginField_Email.sendKeys(PEKAMA_USER_EMAIL);
        sleep(500);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        loginField_Password.sendKeys(GENERIC_PEKAMA_PASSWORD);
        sleep(500);
        loginButton_Login.click();
        btnLogin.waitUntil(not(visible), 15000);
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
        sleep(1000);
        if ($(byText("Got it!")).isDisplayed()){
            $(byText("Got it!")).click();
            rootLogger.info("cookie were submitted");
        }
    }
    public static void waitForSpinnerNotPresent(){
        $(byXpath("//*[@id='progress-indicator']/span")).waitUntil(not(visible), 10000);
    }

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

    public static void validationFieldByXpath(int randomLength, String fieldName, String submitButton, String errorMsg) {
        rootLogger.info("Validation field test for - "+fieldName);
        $(byXpath(fieldName)).clear();
        $(byXpath(fieldName)).sendKeys(randomString(randomLength));
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
        sleep(500);
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

    public static void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, String inputValue) {
        rootLogger.info("select - "+inputValue);
        uiSelectName.waitUntil(visible, 15000).click();
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
    public static boolean hideZopim(){
        try{executeJavaScript("$zopim.livechat.hideAll()");
        rootLogger.info("Zopim collapsed");
        return true;
        }
        catch (WebDriverException e) {
            rootLogger.info("Zopim not found error");
            return false;
       }
    }
    public static void scrollCustom(int value) {
        executeJavaScript("scrollTo(0, "+value+")");
    }
    public static void scrollToElement(SelenideElement element) throws Exception {
        executeJavaScript("arguments[0].scrollIntoView(true)", element);
        sleep(250);
    } //need to check if works
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
    public static void deleteAllMembers(String testTeamNameSurname) {
        openUrlWithBaseAuth(URL_Members);
        checkText(testTeamNameSurname);
        if ($$(byXpath(ICON_DELETE_MEMBER)).size()!=0){
            do {
                $(byXpath(ICON_DELETE_MEMBER)).shouldBe(visible);
                $(byXpath(ICON_DELETE_MEMBER)).click();
                submitConfirmAction();
                sleep(2000);
            }
            while ($$(byXpath(ICON_DELETE_MEMBER)).size()!=0);
            rootLogger.info("All - members were deleted");
        }
    }
    public static void deleteLoopIconX(String testTeamNameSurname) {
        checkText(testTeamNameSurname);
        if ($$(byXpath(ICON_DELETE_MEMBER)).size()!=0){
            do {
                $(byXpath(ICON_DELETE_MEMBER)).shouldBe(visible);
                $(byXpath(ICON_DELETE_MEMBER)).click();
                submitConfirmAction();
                sleep(2000);
            }
            while ($$(byXpath(ICON_DELETE_MEMBER)).size()!=0);
            rootLogger.info("All - members were deleted");
        }
    }
    public static void openPageWithSpinner(String reportPage){
        openUrlWithBaseAuth(reportPage);
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
    public static void deleteCookies(){
       getWebDriver().manage().deleteAllCookies();
    }
    public static void deleteCookiesGmail(String cookieName){
        getWebDriver().manage().deleteCookieNamed(cookieName);
    }
    public static boolean deleteProject(){
        rootLogger.info("Delete Pekama project");
        scrollUp();
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        submitConfirmAction();
        String url = URL_Dashboard;
        if (url.equals(getActualUrl())){
            return true;
        }
        else return false;
    }
    public static boolean checkTextLoop(String displayedText){
        if ($(byText(displayedText)).exists() == false) {
            int count = 0;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info(displayedText+" not detected, loop#: "+count);
                if ($(byText(displayedText)).exists() == true) {
                    rootLogger.info(displayedText+" is displayed");
                    return true;
                }
            } while (count < 5);
            rootLogger.info(displayedText+" NOT displayed");
            return false;
        }
        rootLogger.info(displayedText+" is displayed");
        return true;
    }
    public static boolean checkTextLoop(String displayedText, int loopLength){
        if ($(byText(displayedText)).exists() == false) {
            int count = 0;
            do {
                sleep(loopLength);
                refresh();
                count++;
                rootLogger.info(displayedText+" not detected, loop#: "+count);
                if ($(byText(displayedText)).exists() == true) {
                    rootLogger.info(displayedText+" is displayed");
                    return true;
                }
            } while (count < 5);
        rootLogger.info(displayedText+" NOT displayed");
        return false;
        }
        rootLogger.info(displayedText+" is displayed");
        return true;
    }
    public static boolean checkMatchedTextLoop(String displayedText, int loopLength){
        if ($(withText(displayedText)).exists() == false) {
            int count = 0;
            do {
                sleep(loopLength);
                refresh();
                count++;
                rootLogger.info(displayedText+" not detected, loop#: "+count);
                if ($(withText(displayedText)).exists() == true) {
                    rootLogger.info(displayedText+" is displayed");
                    return true;
                }
            } while (count < 5);
            rootLogger.info(displayedText+" NOT displayed");
            return false;
        }
        rootLogger.info(displayedText+" is displayed");
        return true;
    }
    public static boolean checkTextNotPresentLoop(String displayedText){
        if ($(byText(displayedText)).exists() == true) {
            int count = 0;
            do {
                sleep(12000);
                refresh();
                count++;
                rootLogger.info(displayedText+" still displayed, loop#: "+count);
                if ($(byText(displayedText)).exists() == false) {
                    rootLogger.info(displayedText+" NOT displayed");
                    return true;
                }
            } while (count < 5);
            rootLogger.info(displayedText+" is displayed");
            return false;
        }
        rootLogger.info(displayedText+" NOT displayed");
        return true;
    }
    public static boolean checkTextNotPresentLoop(String displayedText, int loopLength){
        if ($(byText(displayedText)).exists() == true) {
            int count = 0;
            do {
                sleep(loopLength);
                refresh();
                count++;
                rootLogger.info(displayedText+" still displayed, loop#: "+count);
                if ($(byText(displayedText)).exists() == false) {
                    rootLogger.info(displayedText+" NOT displayed");
                    return true;
                }
            } while (count < 5);
            rootLogger.info(displayedText+" is displayed");
            return false;
        }
        rootLogger.info(displayedText+" NOT displayed");
        return true;
    }
    public static void switchToCommunityWindow(){
        rootLogger.info("Switch to Community window");
        switchTo().window(PAGE_TITLE_COMMUNITY);
        if (checkPageTitle(PAGE_TITLE_COMMUNITY)==false){
            Assert.fail("Page is no Community");
        }
    }
    public static void switchToPekamaWindow(){
        rootLogger.info("Switch to Pekama window");
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
    }

    public static String taskCreate(){
        taskAddNew();
        String taskName = taskNewModalSetName();
        taskNewModalSubmit();
        return taskName;
    }
    public static String taskDeploy(String eventType, String templateName){
        taskAddDeploy();
        String taskName = taskModalDeployTemplate(eventType, templateName);
        return taskName;
    }

    public static String taskCreate(int dueDateFromToday, String importance, String status){
        taskAddNew();
        String taskName = taskNewModalSetName();
        taskNewModalSetDueDateFromToday(dueDateFromToday);
        taskNewModalSelectImportance(importance);
        taskNewModalSelectStatus(status);
        taskNewModalSubmit();
        return taskName;
    }
    public static boolean verifyTaskFirstRow(String taskName, String importance, String status, String action){
        String taskStatus;
        String taskAction;
                rootLogger.info("Check task row state");
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text(importance));
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
        rootLogger.info("Task status is - "+taskStatus);
        if (status.equals(TASK_STATUS_ACCEPTED)){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(TASK_STATUS_ACCEPTED)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);

            return true;
        }
        if (status.equals(TASK_STATUS_CANCELLED)){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(TASK_STATUS_CANCELLED)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);
            rootLogger.info("User not able to do any action");
            return true;
        }
        if(status.equals(TASK_STATUS_COMPLETED))
        {
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldHave(text("accept")).getText();
            rootLogger.info("User able to - "+taskAction+" task");
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldHave(text("reject")).getText();
            rootLogger.info("User able to - "+taskAction+" task");
            return true;
        }
        else {
            taskAction = TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldHave(text(action)).getText();
            rootLogger.info("User able to - " + taskAction + " task");
            return true;
        }
    }
    public static boolean verifyTaskFirstRow(String taskName, String importance, String status, boolean assigneeNotDefined){
        String taskStatus;
        rootLogger.info("Check task row state");
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text(importance));
        taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
        rootLogger.info("Task status is - "+taskStatus);
        if (assigneeNotDefined==true){
            taskStatus = TASKS_BTN_STATUS_IN_FIRST_ROW.shouldHave(text(status)).getText();
            rootLogger.info("Task was "+taskStatus);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.shouldNot(exist);
            TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.shouldNot(exist);
            return true;
        }
        return false;
    }
    public static String taskSelectStatusFormDropDown(String statusName){
        try {
            TASKS_BTN_STATUS_IN_FIRST_ROW.waitUntil(visible, 15000).click();
            TASKS_STATUS_SELECTED_IN_DROPDOWN_MENU(statusName).click();
            sleep(1000);
            return statusName;
        }
        catch (Exception e)
        {   rootLogger.info("ERROR: Status "+statusName+" not selected");
            return null;
        }
    }
    public final static SelenideElement PROJECT_TEMPLATE_SELECTED_IN_DROPDOWN_MENU(String templateProjectName) {
        String selectedTemplatePath = "//button[@type='button']/following-sibling::ul//a[@href and text()='%s']";
        String selectedTemplateString = String.format(selectedTemplatePath, templateProjectName);
        SelenideElement selectedTemplate = $(byXpath(selectedTemplateString));
        return selectedTemplate;
    }
    public static String taskSelectProjectTemplateFormDropDown(String templateProjectName){
        sleep(1500);
        int size = DROPDOWN_PROJECT_TEMPLATES_LIST.size();
        if (size==0){Assert.fail("Dropdown not present");}
        try {
            PROJECT_TEMPLATE_SELECTED_IN_DROPDOWN_MENU(templateProjectName).click();
            sleep(1000);
            return templateProjectName;
        }
        catch (Exception e)
        {   rootLogger.info("ERROR: Status "+templateProjectName+" not selected");
            return null;
        }
    }
    public static boolean taskAddNew(){
        try {
            rootLogger.info("Call new task MW in Project");
            PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
            TAB_TASKS_ADD.waitUntil(visible, 20000).click();
            TAB_TASKS_NEW_TASK.waitUntil(visible, 20000).click();
            return true;
        }
        catch (Exception e){return false;}
    }
    public static boolean taskAddDeploy(){
        try {
            rootLogger.info("Call new Task Templates MW in Project");
            PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
            TAB_TASKS_ADD.waitUntil(visible, 20000).click();
            TAB_TASKS_DEPLOY_TASK.waitUntil(visible, 20000).click();
            return true;
        }
        catch (Exception e){return false;}
    }
    public static boolean taskSelectFilterAllOrActive(boolean selectAllFilter){
        if (selectAllFilter == true) {
            try {
                TAB_TASKS_ALL.waitUntil(visible, 15000).click();
                TAB_TASKS_ALL.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding active-link"));
                TAB_TASKS_ACTIVE.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding"));
               return true;
            } catch (Exception e) {
                return false;
            }
        }
        if (selectAllFilter == false) {
            try {
                TAB_TASKS_ACTIVE.waitUntil(visible, 15000).click();
                TAB_TASKS_ACTIVE.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding active-link"));
                TAB_TASKS_ALL.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding"));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    private static String taskNewModalSetName(){
        String taskName = "TASK_" + randomString(10);
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_TASK_NAME, taskName);
        return taskName;
    }
    private static String taskModalDeployTemplate(String eventType, String templateSetName){
        waitForModalWindow("Task Templates");
        MW_DeployTask_Apply.shouldBe(disabled);
        selectItemInDropdown(
                MW_DeployTask_SelectEvent,
                MW_DeployTask_InputEvent,
                eventType);
        String selectTaskTemplateByNamePath = mw+"//label[@class='clickable' and ./span[text()='"+ templateSetName +"']]/input";
        $(byXpath(selectTaskTemplateByNamePath)).click();
        submitEnabledButton(MW_DeployTask_Apply);
        MW.waitUntil(not(visible), 15000);
        return templateSetName;
    }
    private static String taskNewModalSetName(String taskName){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        MW_BTN_OK.shouldBe(disabled);
        fillField(MW_TASK_NAME, taskName);
        return taskName;
    }
    private static void taskNewModalSetDueDateFromToday(int dueDateFromToday){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        fillField(MW_TASK_INPUT_DUE_DATE, getDate(dueDateFromToday));
        sleep(500);
        MW.click();

    }
    private static void taskNewModalSelectAssignor(String assignor){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        selectItemInDropdown(
                MW_TASK_SELECT_ASSIGNOR,
                MW_TASK_INPUT_ASSIGNOR,
                assignor
        );
    }
    private static void taskNewModalSelectAssignee(String assignee){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        selectItemInDropdown(
                MW_TASK_SELECT_ASSIGNEE,
                MW_TASK_INPUT_ASSIGNEE,
                assignee
        );
    }
    private static void taskNewModalSelectImportance(String importance){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        selectItemInDropdown(
                MW_TASK_SELECT_IMPORTANCE,
                MW_TASK_INPUT_IMPORTANCE,
                importance
        );
    }
    private static void taskNewModalSelectStatus(String status){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        selectItemInDropdown(
                MW_TASK_INPUT_STATUS,
                MW_TASK_SELECT_STATUS,
                status
        );
    }
    private static void taskNewModalSubmit(){
        waitForModalWindow(TITLE_MW_NEW_TASK);
        submitEnabledButton(MW_BTN_OK);
        MW.waitUntil(not(visible), 15000);
    }

    public static boolean setAutoDeploy(boolean selectAutoCheckbox){
        if(selectAutoCheckbox==true){
            templateRow.waitUntil(visible, 15000).click();
            TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected).setSelected(true);
            TEMPLATES_AUTO_DEPLOY.shouldBe(selected);
            return selectAutoCheckbox;
        }
        if(selectAutoCheckbox==false){
            templateRow.waitUntil(visible, 15000).click();
            TEMPLATES_AUTO_DEPLOY.shouldBe(selected).setSelected(false);
            TEMPLATES_AUTO_DEPLOY.shouldNotBe(selected);
            return selectAutoCheckbox;
        }
        return false;
    }
    public static Integer checkTemplatesFilters(
            String defining,
            String type,
            String event,
            Integer listSize){
        rootLogger.info("Check template filters");
        refresh();
        SETTINGS_DELETE_X.waitUntil(visible, 15000);
        if(defining!=null) {
            rootLogger.info("Select DEFINING filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_DEFINING,
                    TEMPLATES_FILTER_INPUT_DEFINING,
                    defining);
        }
        if(type!=null) {
            rootLogger.info("Select TYPE filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_TYPE,
                    TEMPLATES_FILTER_INPUT_TYPE,
                    type);
        }
        if(event!=null) {
            rootLogger.info("Select EVENT filter");
            selectItemInDropdown(
                    TEMPLATES_FILTER_SELECT_EVENT,
                    TEMPLATES_FILTER_INPUT_EVENT,
                    event);
        }
        if(listSize!=null) {
            rootLogger.info("Check templates list size");
            TEMPLATES_LIST.shouldHaveSize(listSize);}
        return listSize;
    }

    public static String setProjectDefining(String defining){
                if(defining!=null) {
                    selectItemInDropdown(
                            TAB_INFO_SELECT_Defining,
                            TAB_INFO_INPUT_Defining,
                            defining);
                    sleep(1500);
                    checkText(defining);
                    return defining;
                }
                return null;
    }
    public static String setProjectType(String type){
        if(type!=null) {
            selectItemInDropdown(
                    TAB_INFO_SELECT_Type,
                    TAB_INFO_INPUT_Type,
                    type);
            sleep(1500);
            checkText(type);
            return type;
        }
        return null;
    }
    public static String setProjectSubType(String subType){
        if(subType!=null) {
            selectItemInDropdown(
                    TAB_INFO_SELECT_SubType,
                    TAB_INFO_INPUT_SubType,
                    subType);
            sleep(1500);
            checkText(subType);
            return subType;
        }
        return null;
    }
    @Test
    public void testDebug(){
        setEnvironment();
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(
                User1.GMAIL_EMAIL.getValue(),
                User1.PEKAMA_PASSWORD.getValue(),
                URL_COMMUNITY_LOGIN);
        refresh();
        hideZopim();
    }
}
