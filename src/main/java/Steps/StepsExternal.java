package Steps;

import Page.TestsCredentials;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static Page.CommunityWizard.expertRowLabel;
import static Page.TestsCredentials.GMAIL_PASSWORD;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.Emails.*;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsExternal implements StepsFactory{
    static final Logger logging = LogManager.getLogger(StepsExternal.class);
    public static String REDIRECT_LINK;
    public static String checkReportBackLink;
    private static String[] args;

    public static void checkEmailGeneric(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, SelenideElement EMAIL_REDIRECT_LINK, String thisMailingListName){
        logging.info("Login");
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        logging.info("Detect email");
        detectEmail(EMAIL_SUBJECT);
        logging.info("Open email");
        openEmail(EMAIL_SUBJECT);
        logging.info("Check title");
        checkEmailTitle(EMAIL_TITLE);
        logging.info("Check Text");
        checkEmailText(EMAIL_TEXT);
        logging.info("Check Button");
        checkEmailButton(EMAIL_BTN);
        logging.info("Check redirect Link");
        checkRedirectLink(EMAIL_REDIRECT_LINK);
        logging.info("Check report back Link");
        checkReportTitleAndBackLink(EMAIL_TEXT, thisMailingListName);
        logging.info("Check attachment");
        checkEmailReportAttachment();
        logging.info("Delete email");
        deleteEmail();
        logging.info("Empty trash");
        inboxEmptyTrash();
        logging.info("Email deleted");
    };
    public static String checkInboxEmail(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, SelenideElement EMAIL_REDIRECT_LINK) {
        logging.info("Login");
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        detectEmail(EMAIL_SUBJECT);
        logging.info("Open email");
        openEmail(EMAIL_SUBJECT);
        logging.info("Check title");
        checkEmailTitle(EMAIL_TITLE);
        logging.info("Check Text");
        checkEmailText(EMAIL_TEXT);
        logging.info("Check Button");
        checkEmailButton(EMAIL_BTN);
        logging.info("Check redirect Link");
        checkRedirectLink(EMAIL_REDIRECT_LINK);
        logging.info("Delete email");
        deleteEmail();
        logging.info("Empty trash");
        inboxEmptyTrash();
        logging.info("Email deleted");
        return REDIRECT_LINK;
    }
    public static String checkInboxEmail(String GMAIL_LOGIN, String GMAIL_PASSWORD, SelenideElement EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT) {
        logging.info("Login");
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        detectEmail(EMAIL_SUBJECT);
        logging.info("Open email");
        openEmail(EMAIL_SUBJECT);
        logging.info("Check title");
        checkEmailTitle(EMAIL_TITLE);
        logging.info("Check Text");
        checkEmailText(EMAIL_TEXT);
        logging.info("Delete email");
        deleteEmail();
        logging.info("Empty trash");
        inboxEmptyTrash();
        logging.info("Email deleted");
        return REDIRECT_LINK;
    }
    public static String checkEmailReport(String GMAIL_LOGIN, String GMAIL_PASSWORD, String thisMailingListName){
        SelenideElement EMAIL_SUBJECT = EMAIL_REPORT_SUBJECT;
        String EMAIL_TEXT = EMAIL_REPORT_TEXT;
        String EMAIL_TITLE = "Pekama Report \""+thisMailingListName+"\"";
        logging.info("Login");
        signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
        logging.info("Detect email");
        detectEmail(EMAIL_SUBJECT);
        logging.info("Open email");
        openEmail(EMAIL_SUBJECT);
        logging.info("Check title");
        checkEmailTitle(EMAIL_TITLE);
        logging.info("Check Text");
        checkEmailText(EMAIL_TEXT);
        logging.info("Check report back Link");
        checkReportTitleAndBackLink(EMAIL_TEXT, thisMailingListName);
        logging.info("Check attachment");
        checkEmailReportAttachment();
        logging.info("Delete email");
        deleteEmail();
        logging.info("Empty trash");
        inboxEmptyTrash();
        logging.info("Email deleted");
        open(GMAIL_URL_SIGN_OUT);
        return checkReportBackLink;
    }

    //Inbox Steps
    public static void signInGmailInbox(String GMAIL_LOGIN, String GMAIL_PASSWORD) { //Logic for open INBOX twice or more times in one session without logout
        logging.info("Start browser");
        open(INBOX_URL);
        sleep(2000);
        INBOX_SIGNIN.waitUntil(visible, 15000).click();
        sleep(3000);
        if (INBOX_BTN_TRASH.is(visible) == true){
            logging.info("User is logged in and inbox is opened");
        }
        if (GMAIL_PASSWORD_FIELD.is(visible) == true){
            logging.info("Type password");
            GMAIL_PASSWORD_FIELD.shouldBe(visible).sendKeys(GMAIL_PASSWORD);
            logging.info("Submit password");
            GMAIL_SIGNIN_BTN.shouldBe(visible).click();
            logging.info("Inbox opened");
        }
        if(GMAIL_LOGIN_FIELD.is(visible) == true) {
            logging.info("Type email");
            GMAIL_LOGIN_FIELD.sendKeys(GMAIL_LOGIN);
            logging.info("Submit email");
            GMAIL_NEXT_BTN.click();
            logging.info("Type password");
            GMAIL_PASSWORD_FIELD.shouldBe(visible).sendKeys(GMAIL_PASSWORD);
            logging.info("Submit password");
            GMAIL_SIGNIN_BTN.shouldBe(visible).click();
            logging.info("Inbox opened");
        }
        else {
            logging.info("Inbox opened");
        }
    }
    public static void detectEmail(SelenideElement EMAIL_SUBJECT){
        if (EMAIL_SUBJECT.exists() == false) {
            int count = 1;
            do {
                sleep(20000);
                refresh();
                count++;
                logging.info("Email by subject NOT found loop" + count);
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
        sleep(4000);
        if (EMAIL_SUBJECT.exists() == false) {
            Assert.fail("Subject email not found - " + EMAIL_SUBJECT);
        }
        if (EMAIL_SUBJECT.exists() == true) {
            EMAIL_SUBJECT.waitUntil(visible, 15000).click();
            logging.info("Email by subject found");
            sleep(1500);
            INBOX_BTN_DELETE.waitUntil(visible, 10000);
            logging.info("Email opened");
        }
    }

    public static void checkEmailTitle(String EMAIL_TITLE){
        if (EMAIL_TITLE == null) {
            Assert.fail("Title email is - " + EMAIL_TITLE);
        }
        $$(byText(EMAIL_TITLE)).filter(visible);
        logging.info(EMAIL_TITLE + "- email present");
    }
    public static void checkEmailText(String EMAIL_TEXT){
        if (EMAIL_TEXT == null) {
            Assert.fail("Title email is - " + EMAIL_TEXT);
        }
        $$(byText(EMAIL_TEXT)).filter(visible);
        logging.info(EMAIL_TEXT + " - email present");
    }
    public static void checkEmailButton(String EMAIL_BTN){
        if (EMAIL_BTN == null) {
            Assert.fail("Title email is - " + EMAIL_BTN);
        }
        $$(byText(EMAIL_BTN)).filter(visible);
        logging.info(EMAIL_BTN + "- email present");
    }
    public static String checkRedirectLink(SelenideElement EMAIL_REDIRECT_LINK){
        EMAIL_REDIRECT_LINK.waitUntil(visible, 20000);
        REDIRECT_LINK = null;
        REDIRECT_LINK = EMAIL_REDIRECT_LINK.getAttribute("href");
        logging.info("This link present in mail - " + REDIRECT_LINK);
        if (REDIRECT_LINK == null) {
            Assert.fail("Redirect Link not found");
        }
        return REDIRECT_LINK;

    }
    public static String checkReportTitleAndBackLink(String EMAIL_TEXT, String thisMailingListName) {
        checkReportBackLink = null;
        String EMAIL_TITLE = EMAIL_REPORT+" "+"\""+thisMailingListName+"\"";
        $$(byText(EMAIL_TITLE)).filter(visible).getTexts(); //get whole test
        $$(byText(EMAIL_TEXT)).filter(visible);
        logging.info(EMAIL_TITLE+ "- email present");
        checkReportBackLink = EMAIL_REPORT_BACKLINK.getAttribute("href");
        if (checkReportBackLink == null) {
            Assert.fail("Link to pekama reports not found");
        }
        logging.info("Link to mailing list present -  "+checkReportBackLink);
        return checkReportBackLink;
    }
    public static String checkEmailReportAttachment(){
        String attachmentFullTitle = EMAIL_REPORT_ATTACHMENT.getAttribute("title");
        logging.info("This attachment present in mail - " +attachmentFullTitle);
        if (attachmentFullTitle == null) {
            Assert.fail("Redirect Link not found");
        }
        return attachmentFullTitle;
    }
    public static String checkUnsubscribeLink(){
        EMAIL_UNSUBSCRIBE_LINK.shouldBe(visible);
        String link = EMAIL_UNSUBSCRIBE_LINK.getAttribute("href");
        logging.info("Unsubscribe link is - " +link);
        return link;
    }
    public static void deleteEmail() {
        sleep(1000);
        INBOX_BTN_DELETE.waitUntil(visible, 20000).click();
        INBOX_BTN_DELETE.waitUntil(not(visible), 10000);
    }
    public static void inboxEmptyTrash(){
        sleep(1000);
        INBOX_BTN_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        INBOX_BTN_EMPTY_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        INBOX_CONFIRM_EMPTY_TRASH.waitUntil(visible, 10000).click();
        sleep(1000);
        $(byText("Nothing in Trash")).waitUntil(visible, 10000);
        logging.info("Trash cleared");
            if ($(byText("Nothing in Trash")) == null) {
                Assert.fail("Trash NOT cleared");
            }
    }
    public static void submitInboxAlert(){
                    if ((alertIsPresent() != null)) {
            confirm();
            }
    } //not ready

    public static void loginBoxFirstTime(){
        if (true)
        {

        }
    }
    public static void renevAcessToBox(){
    }
    public static void loginBox(){
    }
    public static void checkRootFolder(){
    }
    public static void checkProjectFolder(){
    }
    public static void checkFolder(){
    }
    public static void checkFile(){
    }
    public static void checkFilesRemoved(){
    }

    public static void authGmail(String GMAIL_LOGIN){
        sleep(5000);
        //switchTo().window("Sign in - Google Accounts");
        switchTo().window("Sign in - Google Accounts");
        sleep(5000);
        logging.info("Type email");
        GMAIL_LOGIN_FIELD.shouldBe(visible).sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        GMAIL_NEXT_BTN.shouldBe(visible).click();
        logging.info("Type password");
        GMAIL_PASSWORD_FIELD.shouldBe(visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        GMAIL_SIGNIN_BTN.shouldBe(visible).click();
        logging.info("Inbox opened");
        $(byXpath("//*[@id='submit_approve_access']")).shouldBe(visible);
        $(byXpath("//*[@id='submit_approve_access']")).shouldBe(enabled).click();
        sleep(1000);
        $(byXpath("//*[@id='submit_approve_access']")).waitUntil(not(visible), 15000);
        sleep(3000);
//        close();
//        switchTo().window(0);



    }
    public static void authLinkedin(){
    }

    public static SelenideElement emailSubject(String... args) {
        String buildSubject = String.format(EMAIL_SUBJECT, args);
        SelenideElement actualSubject = $(byXpath(buildSubject));
        return actualSubject;
    }
    @Test
    public void emailSubject (){
        SelenideElement a = emailSubject("new test project - ORL9GP");
        System.out.println(a);
    }
    public static String emailInviteInProjectTitle(String... args){
        String emailTitle = String.format(EMAIL_INVITE_IN_PROJECT_TITLE, args);
        return emailTitle;
    }
    @Test
    public void emailInviteInProjectTitle (){
        String a = emailInviteInProjectTitle("1", "2");
        System.out.println(a);
    }
    public static String emailInviteInProjectText(String... args){
        String emailText = String.format(EMAIL_INVITE_IN_PROJECT_TEXT, args);
        return emailText;
    }
    @Test
    public void emailInviteInProjectText (){
        String a = emailInviteInProjectText("name", "surname", "project title");
        System.out.println(a);
    }
    @Test
    public void externalTestDebug() {
            StepsExternal loginGmailInboxApp = new StepsExternal();
            String GMAIL_LOGIN = TestsCredentials.User4.GMAIL_EMAIL.getValue();
            loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            loginGmailInboxApp.signInGmailInbox(GMAIL_LOGIN, GMAIL_PASSWORD);
            //loginGmailInboxApp.inboxEmptyTrash();


    }



}
