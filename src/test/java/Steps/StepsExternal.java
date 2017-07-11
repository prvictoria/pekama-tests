package Steps;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.NoSuchElementException;

import static Pages.Box.*;
import static Pages.Emails.*;
import static Steps.Steps.clickSelector;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsExternal {
    static final Logger rootLogger = LogManager.getRootLogger();
    static String REDIRECT_LINK;
    public static String checkReportBackLink;
    private static String[] args;

    @Deprecated
    public static void checkEmailGeneric(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, SelenideElement EMAIL_REDIRECT_LINK, String thisMailingListName){
       try {
           signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
           detectEmail(EMAIL_SUBJECT);
           openEmail(EMAIL_SUBJECT);
           checkEmailTitle(EMAIL_TITLE);
           checkEmailText(EMAIL_TEXT);
           checkEmailButton(EMAIL_BTN);
           checkRedirectLink(EMAIL_REDIRECT_LINK);
           checkReportTitleAndBackLink(EMAIL_TEXT, thisMailingListName);
           checkEmailReportAttachment();
           deleteEmail();
           inboxEmptyTrash();
       }
       finally {
           logoutGoogleInbox();
       }
    }
    @Deprecated
    public static String checkInboxEmail(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, SelenideElement EMAIL_REDIRECT_LINK) {
        try {
            signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            detectEmail(EMAIL_SUBJECT);
            openEmail(EMAIL_SUBJECT);
            checkEmailTitle(EMAIL_TITLE);
            checkEmailText(EMAIL_TEXT);
            checkEmailButton(EMAIL_BTN);
            checkRedirectLink(EMAIL_REDIRECT_LINK);
            deleteEmail();
            inboxEmptyTrash();
        }
        finally {
            logoutGoogleInbox();
        }
        return REDIRECT_LINK;
    }
    @Deprecated
    public static String checkInboxEmail(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT) {
        try {
            signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            detectEmail(EMAIL_SUBJECT);
            openEmail(EMAIL_SUBJECT);
            checkEmailTitle(EMAIL_TITLE);
            checkEmailText(EMAIL_TEXT);
            deleteEmail();
            inboxEmptyTrash();
        }
        finally {
            logoutGoogleInbox();
        }
        return REDIRECT_LINK;
    }
    @Deprecated
    public static String checkEmailReport(String GMAIL_LOGIN, String GMAIL_PASSWORD, String thisMailingListName){
        rootLogger.info("Check email - report");
        try {
            SelenideElement EMAIL_SUBJECT = EMAIL_REPORT_SUBJECT;
            String EMAIL_TEXT = EMAIL_REPORT_TEXT;
            String EMAIL_TITLE = "Pekama Report \"" + thisMailingListName + "\"";
            signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            detectEmail(EMAIL_SUBJECT);
            openEmail(EMAIL_SUBJECT);
            checkEmailTitle(EMAIL_TITLE);
            checkEmailText(EMAIL_TEXT);
            checkReportTitleAndBackLink(EMAIL_TEXT, thisMailingListName);
            checkEmailReportAttachment();
            deleteEmail();
            inboxEmptyTrash();
        }
        finally {
            logoutGoogleInbox();
        }
        return checkReportBackLink;
    }

    //Inbox Steps Google
    public static void signInGmailInbox(String GMAIL_LOGIN, String GMAIL_PASSWORD) { //Logic for open INBOX twice or more times in one session without logout
        rootLogger.info("Login GoogleInbox");
        open(INBOX_URL);
        sleep(2000);
        INBOX_SIGNIN.waitUntil(visible, 15000).click();
        sleep(8000);
        if(GMAIL_LOGIN_FIELD.is(visible) == true) {
            rootLogger.info("Type email");
            GMAIL_LOGIN_FIELD.sendKeys(GMAIL_LOGIN);
            rootLogger.info("Submit email");
            GMAIL_NEXT_BTN.click();
            rootLogger.info("Type password");
            GMAIL_PASSWORD_FIELD.shouldBe(visible).sendKeys(GMAIL_PASSWORD);
            if(GMAIL_LOGIN_COOKIE.isSelected()){
                GMAIL_LOGIN_COOKIE.setSelected(false);
                GMAIL_LOGIN_COOKIE.shouldNotBe(selected);
            }
            rootLogger.info("Submit password");
            GMAIL_SIGNIN_BTN.shouldBe(visible).click();
            rootLogger.info("Inbox opened");
        }
        sleep(3000);
        if (INBOX_BTN_TRASH.is(visible) == true){
            rootLogger.info("ObjectUser is logged in and inbox is opened");
            return;
        }
        sleep(3000);
        if (GMAIL_PASSWORD_FIELD.is(visible) == true){
            rootLogger.info("Type password");
            GMAIL_PASSWORD_FIELD.shouldBe(visible).sendKeys(GMAIL_PASSWORD);
            rootLogger.info("Submit password");
            GMAIL_SIGNIN_BTN.shouldBe(visible).click();
            rootLogger.info("Inbox opened");
            return;
        }
        sleep(3000);
        if (INBOX_BTN_TRASH.is(visible) == true){
            rootLogger.info("ObjectUser is logged in and inbox is opened");
            return;
        }
        else {
            Assert.fail("Login failed");
        }
    }
    public static void detectEmail(SelenideElement EMAIL_SUBJECT){
        rootLogger.info("Detect email");
        if (EMAIL_SUBJECT.exists() == false) {
            int count = 0;
            do {
                sleep(20000);
                refresh();
                count++;
                rootLogger.info("Email by subject NOT found loop" + count);
                    if (EMAIL_SUBJECT.exists() == true) {
                        break;
                    }
            } while (count < 10);
        }
        if (EMAIL_SUBJECT.exists() == false) {
            Assert.fail("Email not found - " + EMAIL_SUBJECT);
        }

    }
    public static void openEmail(SelenideElement EMAIL_SUBJECT){
        rootLogger.info("Open email");
        sleep(4000);
        if (EMAIL_SUBJECT.exists() == false) {
            Assert.fail("Subject email not found - " + EMAIL_SUBJECT);
        }
        if (EMAIL_SUBJECT.exists() == true) {
            EMAIL_SUBJECT.waitUntil(visible, 15000).click();
            rootLogger.info("Email by subject found");
            sleep(1500);
            INBOX_BTN_DELETE.waitUntil(visible, 10000);
            rootLogger.info("Email opened");
        }
    }

    public static void checkEmailTitle(String EMAIL_TITLE){
        rootLogger.info("Check title");
        if (EMAIL_TITLE == null) {
            Assert.fail("Title email is - " + EMAIL_TITLE);
        }
        $$(byText(EMAIL_TITLE)).filter(visible);
        rootLogger.info(EMAIL_TITLE + "- email present");
    }
    public static void checkEmailText(String EMAIL_TEXT){
        rootLogger.info("Check email Text");
        if (EMAIL_TEXT == null) {
            Assert.fail("Title email is - " + EMAIL_TEXT);
        }
        $$(byText(EMAIL_TEXT)).filter(visible);
        rootLogger.info(EMAIL_TEXT + " - email present");
    }
    public static void checkEmailButton(String EMAIL_BTN){
        rootLogger.info("Check email Button");
        if (EMAIL_BTN == null) {
            Assert.fail("Title email is - " + EMAIL_BTN);
        }
        $$(byText(EMAIL_BTN)).filter(visible);
        rootLogger.info(EMAIL_BTN + "- email present");
    }
    public static String checkRedirectLink(SelenideElement EMAIL_REDIRECT_LINK){
        rootLogger.info("Check redirect Link");
        EMAIL_REDIRECT_LINK.waitUntil(visible, 20000);
        REDIRECT_LINK = null;
        REDIRECT_LINK = EMAIL_REDIRECT_LINK.getAttribute("href");
        rootLogger.info("This link present in mail - " + REDIRECT_LINK);
        if (REDIRECT_LINK == null) {
            Assert.fail("Redirect Link not found");
        }
        return REDIRECT_LINK;

    }
    public static String checkReportTitleAndBackLink(String EMAIL_TEXT, String thisMailingListName) {
        rootLogger.info("Check report back Link");
        checkReportBackLink = null;
        String EMAIL_TITLE = EMAIL_REPORT+" "+"\""+thisMailingListName+"\"";
        $$(byText(EMAIL_TITLE)).filter(visible).getTexts(); //get whole test
        $$(byText(EMAIL_TEXT)).filter(visible);
        rootLogger.info(EMAIL_TITLE+ "- email present");
        checkReportBackLink = EMAIL_REPORT_BACKLINK.getAttribute("href");
        if (checkReportBackLink == null) {
            Assert.fail("Link to pekama reports not found");
        }
        rootLogger.info("Link to mailing list present -  "+checkReportBackLink);
        return checkReportBackLink;
    }
    public static String checkEmailReportAttachment(){
        rootLogger.info("Check attachment");
        String attachmentFullTitle = EMAIL_REPORT_ATTACHMENT.getAttribute("title");
        rootLogger.info("This attachment present in mail - " +attachmentFullTitle);
        if (attachmentFullTitle == null) {
            Assert.fail("Redirect Link not found");
        }
        return attachmentFullTitle;
    }
    public static String checkUnsubscribeLink() {
        String link = null;
        try {
            rootLogger.info("Check Unsubscribe Link");
            EMAIL_UNSUBSCRIBE_LINK.shouldBe(visible);
            link = EMAIL_UNSUBSCRIBE_LINK.getAttribute("href");
            rootLogger.info("Unsubscribe link is - " + link);
            return link;
        }
        catch (NoSuchElementException e) {
            rootLogger.info("Unsubscribe link not found - " + link);
        }
        return link;
    }
    public static void deleteEmail() {
        rootLogger.info("Delete email");
        sleep(1000);
        INBOX_BTN_DELETE.waitUntil(visible, 20000).click();
        INBOX_BTN_DELETE.waitUntil(not(visible), 10000);
    }
    public static void inboxEmptyTrash(){
        rootLogger.info("Empty trash");
        sleep(1000);
        INBOX_BTN_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        INBOX_BTN_EMPTY_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        INBOX_CONFIRM_EMPTY_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        $(byText("Nothing in Trash")).waitUntil(visible, 10000);
        rootLogger.info("Trash cleared");
            if ($(byText("Nothing in Trash")) == null) {
                Assert.fail("Trash NOT cleared");
            }
    }
    public static void submitInboxAlert(){
                    if ((alertIsPresent() != null)) {
            confirm();
            }
    } //not need for now
    public static void logoutGoogleInbox(){
        rootLogger.info("Logout Google");
        open("https://accounts.google.com/SignOutOptions?hl=en&continue=https://inbox.google.com/%3Fcid%3Dimp");
        if ($(byId("signout")).isDisplayed()) {
            $(byId("signout")).click();
            sleep(3000);
            checkText("Sign in with your Google Account");
        }
    }
    public static void loginBoxFirstTime(){
        if (true)
        {

        }
    }
    public static void renevAcessToBox(){    }
    public static void loginBox(){    }
    public static void checkRootFolder(){    }
    public static void checkProjectFolder(){    }
    public static void checkFolder(){    }
    public static void checkFile(){    }
    public static void checkFilesRemoved(){    }

    public static void submitAuthGmail(ObjectUser user) {
        sleep(5000);
        switchToGoogleWindow();

        rootLogger.info("Type email");
        fillField(GMAIL_LOGIN_FIELD, user.email, "Type email");
        rootLogger.info("Submit email");
        GMAIL_NEXT_BTN.shouldBe(visible).click();

        rootLogger.info("Type password");
        fillField(GMAIL_PASSWORD_FIELD, user.passwordEmail, "Type password");
        rootLogger.info("Submit password");
        GMAIL_NEXT_SUBMIT_PASSWORD.shouldBe(visible).click();

        rootLogger.info("Select user");
        checkText(user.email);
        sleep(4000);
        clickSelector($(byText(user.email)));
        rootLogger.info("Allow access to account");
        sleep(4000);
        submitEnabledButton(GMAIL_ALLOW_ACCESS);

        sleep(4000);
        switchToPekamaWindow();
    }
    public static void loginBox(String login, String password){
        rootLogger.info("Login BOX");
        open(boxLoginURL);
        sleep(6000);
        if(BOX_BTN_SIGN_IN.isDisplayed()) {
            boxWindowEmail.sendKeys(login);
            boxWindowPassword.sendKeys(password);
            BOX_BTN_SIGN_IN.click();
            rootLogger.info("Login submitted");
            sleep(4000);
        }
    }
    public static void loginBox(String targetFolderUrl, String login, String password){
        rootLogger.info("Login BOX");
        open(targetFolderUrl);
        sleep(6000);
        if(BOX_BTN_SIGN_IN.isDisplayed()) {
            boxWindowEmail.sendKeys(login);
            boxWindowPassword.sendKeys(password);
            BOX_BTN_SIGN_IN.click();
            rootLogger.info("Login submitted");
            sleep(4000);
        }
    }

    public static void authLinkedin(){
    }

    public static SelenideElement emailSubject(String... args) {
        String buildSubject = String.format(EMAIL_SUBJECT, args);
        SelenideElement actualSubject = $(byXpath(buildSubject));
        return actualSubject;
    }





}
