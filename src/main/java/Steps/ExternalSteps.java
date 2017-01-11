package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.Emails.*;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;


/**
 * Created by VatslauX on 03-Jan-17.
 */
public class ExternalSteps {
    static final Logger logging = LogManager.getLogger(ExternalSteps.class);


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
    public String checkInboxEmail(String EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, String EMAIL_REDIRECT_LINK) {
        if ($(byXpath(EMAIL_SUBJECT)).exists() == false) {
            int count = 1;
            do {
                sleep(10000);
                refresh();
                count++;
                logging.info("Email by subject NOT found loop"+count);
                if ($(byXpath(EMAIL_SUBJECT)).exists() == true) {
                    break;
                }
            } while (count < 10);
        }
        String actualBackLink = null;
        if ($(byXpath(EMAIL_SUBJECT)).exists() == true) {
            $(byXpath(EMAIL_SUBJECT)).waitUntil(visible, 10000).click();
            logging.info("Email by subject found");
            $(byXpath(INBOX_BTN_DONE)).waitUntil(visible, 10000);
            logging.info("Email present");
            $$(byText(EMAIL_TITLE)).filter(visible);
            $$(byText(EMAIL_TEXT)).filter(visible);
            $$(byText(EMAIL_BTN)).filter(visible);
            logging.info(EMAIL_TITLE + "- email present");
            actualBackLink = $(By.xpath(EMAIL_REDIRECT_LINK)).getAttribute("href");
            logging.info("This link present in mail - " + actualBackLink);
            $(byXpath(INBOX_BTN_DONE)).waitUntil(visible, 10000).click();

            //           $(byXpath(INBOX_BTN_DONE)).waitUntil(not(visible), 10000);
            if (actualBackLink == null) {
                Assert.fail("Redirect Link not found");
            }
            logging.info("Email archived");
//            if ((alertIsPresent() != null)) {
//            confirm();
//            }
        }
//        if (EMAIL_SUBJECT.equals(EMAIL_RESET_PASSWORD_SUBJECT)){
//            open(actualBackLink);
//            logging.info("Open reset password link");
//        }
        return actualBackLink;
    }
    public static String checkInboxEmailReport(String EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, String EMAIL_REDIRECT_LINK){
        $(byXpath(EMAIL_SUBJECT)).waitUntil(visible, 10000).click();
        logging.info("Email by subject found");
        $(byXpath(INBOX_BTN_DONE)).waitUntil(visible, 10000);
        $$(byText(EMAIL_TEXT)).filter(visible).shouldHave(size(1));
        logging.info(EMAIL_TITLE+ "- email present");
        String actualBackLink = $(By.xpath(EMAIL_REDIRECT_LINK)).getAttribute("href");
        logging.info("This link present in mail - " +actualBackLink);
        $(byXpath(INBOX_BTN_DONE)).click();
        $(byXpath(INBOX_BTN_DONE)).shouldBe(disappear);
        logging.info("Email archived");
            if (actualBackLink == null) {
                Assert.fail("Redirect Link not found");
            }
        return actualBackLink;
    }
    // todo - check attachment logic
    public static String checkEmailReportAttachment(String EMAIL_SUBJECT, String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, String EMAIL_REDIRECT_LINK){
        $(byXpath(EMAIL_SUBJECT)).waitUntil(visible, 10000).click();
        logging.info("Email by subject found");
        $(byXpath(INBOX_BTN_DONE)).waitUntil(visible, 10000);
        $$(byText(EMAIL_TEXT)).filter(visible).shouldHave(size(1));
        logging.info(EMAIL_TITLE+ "- email present");
        String actualBackLink = $(By.xpath(EMAIL_REDIRECT_LINK)).getAttribute("href");
        logging.info("This link present in mail - " +actualBackLink);
        $(byXpath(INBOX_BTN_DONE)).click();
        $(byXpath(INBOX_BTN_DONE)).shouldBe(disappear);
        logging.info("Email archived");
        if (actualBackLink == null) {
            Assert.fail("Redirect Link not found");
        }
        return actualBackLink;
    }

}
