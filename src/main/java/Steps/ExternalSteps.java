package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static Page.DirectLinks.COMMUNITY_WIZARD;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static Page.PekamaEmails.*;
import static org.junit.Assert.assertEquals;


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
        $(byXpath(INBOX_BTN_INBOX)).waitUntil(visible, 5000);
    }
    public void checkInboxEmail(String EMAIL_TITLE, String EMAIL_TEXT, String EMAIL_BTN, String EMAIL_REDIRECT_LINK, String EMAIL_SUBJECT){
        $(byXpath(EMAIL_SUBJECT)).waitUntil(visible, 10000).click();
        $(byXpath(INBOX_BTN_DONE)).waitUntil(visible, 10000);
        logging.info("Email present");
        $$(byText(EMAIL_TITLE)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_TEXT)).filter(visible).shouldHave(size(1));
        $$(byText(EMAIL_BTN)).filter(visible).shouldHave(size(1));
        logging.info(EMAIL_TITLE+ "- email present");
        String actualBackLink = $(By.xpath(EMAIL_REDIRECT_LINK)).getAttribute("href");
        logging.info("Back link to Pekama - " +actualBackLink);
//        $(byXpath(INBOX_BTN_DONE)).click();
//        $(byXpath(INBOX_BTN_DONE)).shouldBe(disappear);
        logging.info("Email archived");
        if (EMAIL_SUBJECT.equals(EMAIL_RESET_PASSWORD_SUBJECT)){
            open(actualBackLink);
            logging.info("Open reset password link");
        }

    }

}
