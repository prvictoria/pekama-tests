package Steps;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static Page.ModalWindows.*;
import static Page.PekamaLogin.*;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class PekamaSteps {
    static final Logger rootLogger = LogManager.getRootLogger();

    public void  loginIntoPekamaByUrl(String PEKAMA_USER_EMAIL, String urlLogIn){
        open(urlLogIn); //HOST define PEKAMA or COMMUNITY redirect after login
        rootLogger.info(urlLogIn+ "opened");
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        $(By.xpath(btnSignup)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }

    public void  submitLoginCredentials(String PEKAMA_USER_EMAIL){
        $(loginField_Email).sendKeys(PEKAMA_USER_EMAIL);
        rootLogger.info(PEKAMA_USER_EMAIL+ " - login selected");
        $(loginField_Password).sendKeys(USER_PEKAMA_PASSWORD);
        $(By.xpath(loginButton_Login)).click();
        $(By.xpath(btnLogin)).shouldBe(Condition.not(visible));
        rootLogger.info("Valid Credentials were submitted");
    }

    public void  submitCookie(){
        rootLogger.info("Check if cookie present");
        rootLogger.info("cookie were submitted");
    }

    public String mailingListCreateNew(String THIS_ML_NAME){
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH)).click();
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).shouldBe(visible);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).shouldBe(disabled);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT)).sendKeys(THIS_ML_NAME);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).click();
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE)).shouldBe(disabled);
        $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN)).pressEscape();
        $(byText(THIS_ML_NAME)).shouldBe(visible);
        rootLogger.info("Mailing List was created - "+THIS_ML_NAME);
        return THIS_ML_NAME;
    }

    public static String mailingListSendReport(String reportName){
        // todo-xpath config
        reportName = "123";
        String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = "//li[//a[contains(.,'"+ reportName +"')]]";
        $(byXpath(REPORTS_MAILING_LISTS+REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+REPORTS_MAILING_LISTS_BTN_CALL_ML)).click();



        $(byXpath(MW)).shouldBe(visible);
        $(byText("Mailing List")).shouldNotBe(Condition.visible);

        rootLogger.info("Take checkbox value");
        String checkboxValue = $(byXpath(MW_MAILING_1USER_SELECT)).getSelectedValue();
        if (checkboxValue == null){
            $(byXpath(MW_MAILING_1USER_SELECT)).click();
            rootLogger.info("Set checkbox");
        }
        rootLogger.info("Set interval");
        $(byXpath(MW_MAILING_1USER_INTERVAL)).sendKeys("999");

        rootLogger.info("Send Project report");
        $(byXpath(MW_MAILING_LIST_BTN_SEND_NOW)).click();
        rootLogger.info("Report was sent");
        return REPORTS_MAILING_LISTS_BTN_CALL_ML;
    }
}
