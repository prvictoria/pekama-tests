package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.Emails.*;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;



public class ExternalSteps {
    static final Logger logging = LogManager.getLogger(ExternalSteps.class);
    public static String REDIRECT_LINK;

    public void signInGmailInbox(String GMAIL_LOGIN, String GMAIL_PASSWORD) {
        logging.info("Start browser");
        open(INBOX_URL);
        $(byXpath(INBOX_SIGNIN)).waitUntil(visible, 10000).click();
        logging.info("Type email");
        $(By.name(GMAIL_LOGIN_FIELD)).sendKeys(GMAIL_LOGIN);
        logging.info("Submit email");
        $(GMAIL_NEXT_BTN).click();
        logging.info("Type password");
        $(GMAIL_PASSWORD_FIELD).shouldBe(visible).sendKeys(GMAIL_PASSWORD);
        logging.info("Submit password");
        $(GMAIL_SIGNIN_BTN).shouldBe(visible).click();
        logging.info("Inbox opened");
        //      $(byXpath(INBOX_BTN_INBOX)).waitUntil(visible, 5000);
    }
    public static String checkInboxEmail(String EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, String EMAIL_REDIRECT_LINK) {
        REDIRECT_LINK = null;
        if ($(byXpath(EMAIL_SUBJECT)).exists() == false) {
            int count = 1;
            do {
                sleep(15000);
                refresh();
                count++;
                logging.info("Email by subject NOT found loop"+count);
                if ($(byXpath(EMAIL_SUBJECT)).exists() == true) {
                    break;
                }
            } while (count < 10);
        }
        if ($(byXpath(EMAIL_SUBJECT)).exists() == true) {
            $(byXpath(EMAIL_SUBJECT)).waitUntil(visible, 10000).click();
            logging.info("Email by subject found");
            sleep(1500);
            $(byXpath(INBOX_BTN_DELETE)).waitUntil(visible, 10000);
            logging.info("Email present");
            $$(byText(EMAIL_TITLE)).filter(visible);
            $$(byText(EMAIL_TEXT)).filter(visible);
            $$(byText(EMAIL_BTN)).filter(visible);
            logging.info(EMAIL_TITLE + "- email present");
            REDIRECT_LINK = $(By.xpath(EMAIL_REDIRECT_LINK)).getAttribute("href");
            logging.info("This link present in mail - " + REDIRECT_LINK);
            $(byXpath(INBOX_BTN_DELETE)).waitUntil(visible, 10000).click();
            sleep(500);
            $(byXpath(INBOX_BTN_DELETE)).waitUntil(not(visible), 10000);
            if (REDIRECT_LINK == null) {
                Assert.fail("Redirect Link not found");
            }
            logging.info("Email deleted");
//            if ((alertIsPresent() != null)) {
//            confirm();
//            }
        }
       return REDIRECT_LINK;
    }
    public static String checkInboxEmailReport(String EMAIL_TEXT, String thisMailingListName){
        if ($(byXpath(EMAIL_REPORT_SUBJECT)).exists() == false) {
            int count = 1;
            do {
                sleep(15000);
                refresh();
                count++;
                logging.info("Email by subject NOT found loop"+count);
                if ($(byXpath(EMAIL_REPORT_SUBJECT)).exists() == true) {
                    break;
                }
            } while (count < 10);
        }
        $(byXpath(EMAIL_REPORT_SUBJECT)).waitUntil(visible, 10000).click();
        logging.info("Email by subject found");
        sleep(1500);
        $(byXpath(INBOX_BTN_DELETE)).waitUntil(visible, 10000);
        String EMAIL_TITLE = EMAIL_REPORT+" "+"\""+thisMailingListName+"\"";
        $$(byText(EMAIL_TITLE)).filter(visible).getTexts(); //get whole test
        $$(byText(EMAIL_TEXT)).filter(visible);
        logging.info(EMAIL_TITLE+ "- email present");

        String backLink = $(By.xpath(EMAIL_REPORT_BACKLINK)).getAttribute("href");
        logging.info("Link to pekama reports present - " +backLink);
        if (backLink == null) {
            Assert.fail("Link to pekama reports not found");
        }
        ExternalSteps.checkEmailReportAttachment();
        ExternalSteps.inboxEmptyTrash();
        return backLink;
    }

    public static String checkEmailReportAttachment(){
        String attachmentFullTitle = $(byXpath(EMAIL_REPORT_ATTACHMENT)).getAttribute("title");
        logging.info("This attachment present in mail - " +attachmentFullTitle);
        if (attachmentFullTitle == null) {
            Assert.fail("Redirect Link not found");
        }
        return attachmentFullTitle;
    }
    public static void inboxEmptyTrash(){
        sleep(1000);
        $(byXpath(INBOX_BTN_TRASH)).waitUntil(visible, 10000).click();
        $(byXpath(INBOX_BTN_EMPTY_TRASH)).waitUntil(visible, 10000).click();
        $(byXpath(INBOX_BTN_TRASH)).waitUntil(visible, 10000).click();
        sleep(1000);
        $(byText("Nothing in Trash")).waitUntil(visible, 10000);
        logging.info("Trash cleared");
            if ($(byText("Nothing in Trash")) == null) {
                Assert.fail("Trash NOT cleared");
            }

    }


}
