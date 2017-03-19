package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PekamaPersonalSettings extends Page {
 public static final SelenideElement PERSONAL_SETTINGS_BTN = $(byXpath("//*[@class='header-panel-nav']//*[@href='/a/settings/profile']"));
 public static final SelenideElement TEAM_SETTINGS_BTN = $(byXpath("//*[@class='header-panel-nav']//*[@href='/a/settings/team']"));

 //tabs data attribbute "data-target"

    public static final String personalSettingsTabPersonal = "//*[@id='basic-info']";
    public static final String personalSettingsTabSecurity = "//*[@id='login-security']";
    public static final String personalSettingsTabEmails = "//*[@id='emails']";
    public static final String personalSettingsTabSignature = "//*[@id='signature']";
    public static final String personalSettingsTabIMAP = "//*[@id='imap']";
    public static final String personalSettingsTabTimeTracker = "//*[@id='timeTracker']";

   //tab fields find by NAME
    public static final SelenideElement PERSONAL_DETAILS_TAB_TITLE = $(byXpath("//*[@data-target='#basic-info']")); //id
    public static final SelenideElement PERSONAL_DETAILS_INPUT_NAME = $(byName("first_name"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_SURNAME = $(byName("last_name"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_PHONE = $(byName("phone_number"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_FAX = $(byName("fax_number"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_MOBILE = $(byName("cellphone_number"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_LEGAL_ENTITY = $(byName("legal_entity_name"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_STREET = $(byName("street_address"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_ZIP = $(byName("postal_code"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_CITY = $(byName("city"));
    public static final SelenideElement PERSONAL_DETAILS_INPUT_REGION = $(byName("region"));
    public static final SelenideElement PERSONAL_DETAILS_COUNTRY_SELECT = $(byXpath("//*[@name='country']//span"));
    public static final SelenideElement PERSONAL_DETAILS_COUNTRY_INPUT = $(byXpath("//*[@name='country']//input"));
    public static final SelenideElement PERSONAL_DETAILS_SAVE_BTN = $(byXpath(personalSettingsTabPersonal+"//button[contains(.,'Save')]"));
    public static final SelenideElement PERSONAL_DETAILS_COUNTRY_PLACEHOLDER = $(byXpath("Choose country..."));

    //tab fields find by NAME
    public static final SelenideElement SECURITY_TAB_TITLE = $(byXpath("//*[@data-target='#login-security']"));
    public static final SelenideElement SECURITY_TAB_CURRENT_PASSWORD = $(byName("original_password"));
    public static final SelenideElement SECURITY_TAB_NEW_PASSWORD = $(byName("password1"));
    public static final SelenideElement SECURITY_TAB_CONFIRM_PASSWORD = $(byName("password2"));
    public static final SelenideElement SECURITY_SAVE_BTN = $(byXpath(personalSettingsTabSecurity+"//button[contains(.,'Save')]"));
    public static final SelenideElement SECURITY_ENABLE_BTN = $(byXpath("//button[contains(.,'Enable')]"));



    //tab fields find by NAME
    public static final SelenideElement EMAILS_TAB_TITLE = $(byXpath("//*[@data-target='#emails']"));
    public static final SelenideElement EMAILS_TAB_RADIO_ALWAYS = $(byXpath("//label[contains(.,'Always')]//input[@type='radio']"));
    public static final SelenideElement EMAILS_TAB_RADIO_OFFLINE = $(byXpath("//label[contains(.,'offline')]//input[@type='radio']"));
    public static final SelenideElement EMAILS_TAB_RADIO_NO_EMAILS = $(byXpath("//label[contains(.,'No email notifications')]//input[@type='radio']"));
    public static final SelenideElement EMAILS_TAB_RECEIVE_ATTACHMENTS = $(byXpath("//label[contains(.,'Receive attachments')]//input[@type='checkbox']"));
    public static final SelenideElement EMAILS_TAB_GET_COPY_OWN_EMAILS = $(byXpath("//label[contains(.,'Send me a copy')]//input[@type='checkbox']"));
    public static final SelenideElement EMAILS_TAB_ = $(byXpath("']"));

    //tab fields find by NAME
    public static final SelenideElement SIGNATURE_TAB_TITLE = $(byXpath("//*[@data-target='#signature']"));
    public static final SelenideElement SIGNATURE_TAB_TEXT_EDITOR = $(byXpath("//*[starts-with(@id, 'taTextElement')]"));
    public static final SelenideElement SIGNATURE_SAVE_BTN = $(byXpath(personalSettingsTabSignature+"//button[contains(.,'Save')]"));
 public static final SelenideElement SIGNATURE_DELETE_BTN = $(byXpath(personalSettingsTabSignature+"//button[contains(.,'Delete')]"));

    //tab fields find by NAME
    public static final SelenideElement IMAP_TAB_TITLE = $(byXpath("//*[@data-target='#imap']"));
    public static final String IMAP_SERVER_NAME = "imap.gmail.com";
    public static final String IMAP_SERVER_PORT = "993";
    public static final SelenideElement IMAP_TAB_FIELD_USENAME = $(byXpath(personalSettingsTabIMAP+"//input[@name='username']"));
    public static final SelenideElement IMAP_TAB_FIELD_PASSWORD = $(byXpath(personalSettingsTabIMAP+"//input[@name='password']"));;
    public static final SelenideElement IMAP_TAB_FIELD_SERVER_NAME = $(byXpath(personalSettingsTabIMAP+"//input[@name='location']"));
    public static final SelenideElement IMAP_TAB_FIELD_PORT = $(byXpath(personalSettingsTabIMAP+"//input[@name='port']"));
    public static final SelenideElement IMAP_TAB_BTN_CONNECT_GMAIL = $(byXpath(personalSettingsTabIMAP+"//button[contains(./text(),'Connect Gmail')]"));
    public static final SelenideElement IMAP_TAB_BTN_SAVE_AND_CHECK = $(byXpath("//button[contains(.,'Save & Check')]"));
    public static final SelenideElement IMAP_TAB_BTN_CHECK = $(byXpath("//div[@class='pull-right']/button[contains(.,'Check')]"));
    public static final SelenideElement IMAP_TAB_BTN_DELETE = $(byXpath("//div[@id='imap']//button[contains(.,'Delete')]"));
    public static final SelenideElement IMAP_TAB_SSL = $(byXpath("//input[@name='use_ssl']"));


  //tab fields find by NAME
    public static final SelenideElement TIME_TRACKER_TAB_TITLE = $(byXpath("//*[@data-target='#timeTracker']"));



}
