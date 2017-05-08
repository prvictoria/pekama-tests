package Steps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static Page.ModalWindows.*;
import static Page.PekamaConversationProject.*;
import static Page.PekamaLogin.*;
import static Page.PekamaPersonalSettings.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlStrings.*;
import static Steps.StepsHttpAuth.*;
import static Steps.StepsModalWindows.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsPekama extends StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();

    public void  loginByURL(String PEKAMA_USER_EMAIL, String PEKAMA_USER_PASSWORD, String AUTH_URL){
        openUrlWithBaseAuth(AUTH_URL);
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
    public static void  submitCookie(int waitTimeSec){
        rootLogger.info("Check if cookie present");
        int i = 0;
        while ($(byText("Got it!")).isDisplayed()==false && i<waitTimeSec){
            sleep(1000);
            i++;
            if($(byText("Got it!")).isDisplayed()){
                $(byText("Got it!")).click();
                rootLogger.info("cookie were submitted");
                return;
                }
        }
        if($(byText("Got it!")).isDisplayed()) {
            $(byText("Got it!")).click();
            rootLogger.info("cookie were submitted");
            return;
        }
    }
    public static void waitForSpinnerNotPresent(){
        $(byXpath("//*[@id='progress-indicator']/span")).waitUntil(not(visible), 10000);
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
        //rootLogger.info("Button was clicked");
    }

    public static void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, String inputValue) {
        rootLogger.info("select - "+inputValue);
        uiSelectName.waitUntil(visible, 20000).click();
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
        sleep(1500);
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
            Assert.fail("Page is not Community");
        }
    }
    public static void switchToPekamaWindow(){
        rootLogger.info("Switch to Pekama window");
        switchTo().window(PAGE_TITLE_PEKAMA);
        if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
            Assert.fail("No redirect to Community");
        }
    }




    public static void selectOption(SelenideElement optionSelector,String optionName) {
        optionSelector.selectOption(new String[]{optionName});
    }
    public static void checkReceiveEmailOptions(Boolean receiveAlways, Boolean receiveOffline, Boolean noEmails, Boolean getAttachment, Boolean getCopyOwnMsg){
        if(receiveAlways==true) {
            rootLogger.info("Check receive get emails Always radio is selected");
            EMAILS_TAB_RADIO_ALWAYS.waitUntil(visible, 20000).shouldBe(selected);
        }
        if(receiveAlways==false) {
            rootLogger.info("Check receive get emails Always radio is NOT selected");
            EMAILS_TAB_RADIO_ALWAYS.waitUntil(visible, 20000).shouldNotBe(selected);
        }
        if(receiveOffline==true) {
            rootLogger.info("Check receive get emails if user is offline radio is selected");
            EMAILS_TAB_RADIO_OFFLINE.waitUntil(visible, 20000).shouldBe(selected);
            EMAILS_TAB_RECEIVE_ATTACHMENTS.shouldBe(disabled);
        }
        if(receiveOffline==false) {
            rootLogger.info("Check receive get emails if user is offline radio is NOT selected");
            EMAILS_TAB_RADIO_OFFLINE.waitUntil(visible, 20000).shouldNotBe(selected);
        }
        if(noEmails==true) {
            rootLogger.info("Check that Always radio is selected");
            EMAILS_TAB_RADIO_NO_EMAILS.waitUntil(visible, 20000).shouldBe(selected);
        }
        if(noEmails==false) {
            rootLogger.info("Check that Always radio is NOT selected");
            EMAILS_TAB_RADIO_NO_EMAILS.waitUntil(visible, 20000).shouldNotBe(selected);
        }
        if(getAttachment==true) {
            rootLogger.info("Check that get Attachment checkbox is set");
            EMAILS_TAB_RECEIVE_ATTACHMENTS.waitUntil(visible, 20000).shouldBe(checked);
        }
        if(getAttachment==false) {
            rootLogger.info("Check that get Attachment checkbox is un-set");
            EMAILS_TAB_RECEIVE_ATTACHMENTS.waitUntil(visible, 20000).shouldNotBe(checked);
        }
        if(getCopyOwnMsg==true) {
            rootLogger.info("Check that get copy own messages as emails checkbox is set");
            EMAILS_TAB_GET_COPY_OWN_EMAILS.waitUntil(visible, 20000).shouldBe(checked);
        }
        if(getCopyOwnMsg==false) {
            rootLogger.info("Check that get copy own messages as emails checkbox is un-set");
            EMAILS_TAB_GET_COPY_OWN_EMAILS.waitUntil(visible, 20000).shouldNotBe(checked);
        }
    }
    public static void selectReceiveEmailOptions(Boolean receiveAlways, Boolean receiveOffline, Boolean noEmails, Boolean getAttachment, Boolean getCopyOwnMsg){
        if(receiveAlways==true) {
            rootLogger.info("Select receive get emails Always radio");
            EMAILS_TAB_RADIO_ALWAYS.waitUntil(visible, 20000).setSelected(true);
            EMAILS_TAB_RADIO_ALWAYS.shouldBe(selected);
        }
        if(receiveOffline==true) {
            rootLogger.info("Select receive get emails if user is offline radio");
            EMAILS_TAB_RADIO_OFFLINE.waitUntil(visible, 20000).setSelected(true);
            EMAILS_TAB_RADIO_OFFLINE.shouldBe(selected);
        }
        if(noEmails==true) {
            rootLogger.info("Select no Always radio");
            EMAILS_TAB_RADIO_NO_EMAILS.waitUntil(visible, 20000).setSelected(true);
            EMAILS_TAB_RADIO_NO_EMAILS.shouldBe(selected);
            EMAILS_TAB_RECEIVE_ATTACHMENTS.shouldBe(disabled);
        }
        if(getAttachment==true && noEmails==false) {
            rootLogger.info("Set get Attachment checkbox");
            EMAILS_TAB_RECEIVE_ATTACHMENTS.waitUntil(visible, 20000).setSelected(true);
            EMAILS_TAB_RECEIVE_ATTACHMENTS.shouldBe(checked);
        }
        if(getAttachment==false && noEmails==false) {
            rootLogger.info("Set get Attachment checkbox");
            EMAILS_TAB_RECEIVE_ATTACHMENTS.waitUntil(visible, 20000).setSelected(false);
            EMAILS_TAB_RECEIVE_ATTACHMENTS.shouldNotBe(checked);
        }
        if(getCopyOwnMsg==true) {
            rootLogger.info("Set get copy own messages as emails checkbox");
            EMAILS_TAB_GET_COPY_OWN_EMAILS.waitUntil(visible, 20000).setSelected(true);
            EMAILS_TAB_GET_COPY_OWN_EMAILS.shouldBe(checked);
        }
        if(getCopyOwnMsg==false) {
            rootLogger.info("Set get copy own messages as emails checkbox");
            EMAILS_TAB_GET_COPY_OWN_EMAILS.waitUntil(visible, 20000).setSelected(false);
            EMAILS_TAB_GET_COPY_OWN_EMAILS.shouldNotBe(checked);
        }
    }
    public static boolean checkMember(String email) {
        String row = String.format(BTN_DELETE_MEMBER, email);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(email+" - member is the Team");
        return true;
    }
    public static boolean checkMemberInactive(String email) {
        String inactiveEmail = email.toLowerCase()+" (inactive)";
        String row = String.format(BTN_DELETE_MEMBER, inactiveEmail);
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
    public static void deleteMemberInactive(String email) {
        String inactiveEmail = email.toLowerCase()+" (inactive)";
        String row = String.format(BTN_DELETE_MEMBER, inactiveEmail);
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
    public static void deleteAllMembers() {
        openUrlWithBaseAuth(URL_Members);
        sleep(3000);
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
    public static void deleteLoopIconX() {
        sleep(2000);
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

    public static void openSettingsTabPersonalDetails(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        PERSONAL_DETAILS_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void openSettingsTabSecurity(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        SECURITY_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void openSettingsTabEmails(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        EMAILS_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void openSettingsTabSignature(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        SIGNATURE_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void openSettingsTabIMAP(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        IMAP_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void openSettingsTabTimeTracker(){
        openUrlWithBaseAuth(URL_PersonalSettings);
        TIME_TRACKER_TAB_TITLE.waitUntil(visible, 15000).click();
    }
    public static void postMessage(String text){
        rootLogger.info("Post message");
        fillTextEditor(text);
        submitEnabledButton(CONVERSATION_BTN_POST);
        MESSAGES_LIST.filter(visible).shouldHaveSize(1);
        MESSAGE_FIRST_TEXT.shouldHave(text(text));
    }
    public static void expandTextEditorInTeamChat(){
        CONVERSATION_LABEL_ACTIVE_TAB.shouldHave(text(CONVERSATION_TEAM_TAB_NAME));
        CONVERSATION_INPUT_TEXT_COLLAPSED.shouldBe(visible).click();
        sleep(4000);
    }
    public static String absolutePath(String path) {
        return new File(path).getAbsolutePath();
    }
    public static void uploadFile(String fileName, SelenideElement input){
        String relativePath = "src/test/java/UploadFiles/"+fileName;
        String absolutePath = absolutePath(relativePath);
        rootLogger.info(absolutePath);
        sleep(2000);
        input.waitUntil(exist, 20000).sendKeys(absolutePath);
        sleep(3000);
    }
    public enum UploadFiles {JPG, ICO, PNG, SVG, PDF,  WORD, EXCEL, ZIP, GOOGLE};
    public static void executeAutoItScript(String fileName) throws IOException {
        String relativePath = "src/test/java/ScriptsAutoIt/"+fileName;
        sleep(2000);
        String scriptPath = absolutePath(relativePath);
        rootLogger.info(scriptPath);
        Runtime.getRuntime().exec(scriptPath);
        sleep(4000);
    }
    public static String executeAutoItScript(UploadFiles fileType) throws IOException {
        String[] scriptNames = {"script_upload_jpeg_ff.exe", "script_upload_icon_ff.exe", "script_upload_png_ff.exe", "script_upload_svg_ff.exe", "script_upload_pdf_ff.exe", "script_upload_wordx_ff.exe", "script_upload_excelx_ff.exe", "script_upload_zip_ff.exe", "script_upload_googledoc_ff.exe"};
        String scriptName = null;
        String[] fileNames = {"jpeg.jpg", "icon.ico", "png.png", "svg.svg", "pdf.pdf", "word.docx", "excel.xl", "zip.zip", "googledoc.gdoc"};
        String fileName = null;
        switch(fileType) {
            case JPG:
                scriptName = scriptNames[0];
                fileName = fileNames[0];
                break;
            case ICO:
                scriptName = scriptNames[1];
                fileName = fileNames[1];
                break;
            case PNG:
                scriptName = scriptNames[2];
                fileName = fileNames[2];
                break;
            case SVG:
                scriptName = scriptNames[3];
                fileName = fileNames[3];
                break;
            case PDF:
                scriptName = scriptNames[4];
                fileName = fileNames[4];
                break;
            case WORD:
                scriptName = scriptNames[5];
                fileName = fileNames[5];
                break;
            case EXCEL:
                scriptName = scriptNames[6];
                fileName = fileNames[6];
                break;
            case ZIP:
                scriptName = scriptNames[7];
                fileName = fileNames[7];
                break;
            case GOOGLE:
                scriptName = scriptNames[8];
                fileName = fileNames[8];
                break;
        }
        String relativePath = "src/test/java/ScriptsAutoIt/"+ scriptName;
        sleep(3000);
        String scriptPath = absolutePath(relativePath);
        rootLogger.info(scriptPath);
        Runtime.getRuntime().exec(scriptPath);
        sleep(8000);
        return fileName;
    }
    public static void addMember(String email, SelenideElement button){
        button.shouldBe(visible).shouldBe(enabled).click();
        submitAddMemberWindow(email,  true);
    }

}
