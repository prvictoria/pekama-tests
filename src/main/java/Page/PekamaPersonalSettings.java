package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PekamaPersonalSettings {
 public static final String PERSONAL_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/profile']";
 public static final String TEAM_SETTINGS_BTN = "//*[@class='header-panel-nav']//*[@href='/a/settings/team']";

 //tabs data attribbute "data-target"

    public static final String personalSettingsTabPersonal = "//*[@id='basic-info']";
    public static final String personalSettingsTabSecurity = "//*[@id='login-security']";
    public static final String personalSettingsTabEmails = "//*[@id='emails']";
    public static final String personalSettingsTabSignature = "//*[@id='signature']";
    public static final String personalSettingsTabIMAP = "//*[@id='imap']";
    public static final String personalSettingsTabTimeTracker = "//*[@id='timeTracker']";

   //tab fields find by NAME
    public static final String PERSONAL_DETAILS_TAB_TITLE = "//*[@data-target='#basic-info']"; //id
    public static final String PERSONAL_DETAILS_INPUT_NAME = "first_name";
    public static final String PERSONAL_DETAILS_INPUT_SURNAME = "last_name";
    public static final String PERSONAL_DETAILS_INPUT_PHONE = "phone_number";
    public static final String PERSONAL_DETAILS_INPUT_FAX = "fax_number";
    public static final String PERSONAL_DETAILS_INPUT_MOBILE = "cellphone_number";
    public static final String PERSONAL_DETAILS_INPUT_LEGAL_ENTITY = "legal_entity_name";
    public static final String PERSONAL_DETAILS_INPUT_STREET = "street_address";
    public static final String PERSONAL_DETAILS_INPUT_ZIP = "postal_code";
    public static final String PERSONAL_DETAILS_INPUT_CITY = "city";
    public static final String PERSONAL_DETAILS_INPUT_REGION = "region";
    public static final String PERSONAL_DETAILS_COUNTRY_SELECT = "//*[@name='country']//span";
    public static final String PERSONAL_DETAILS_COUNTRY_INPUT = "//*[@name='country']//input";
    public static final String PERSONAL_DETAILS_SAVE_BTN = personalSettingsTabPersonal+"//button[contains(.,'Save')]";
    public static final String PERSONAL_DETAILS_COUNTRY_PLACEHOLDER = "Choose country...";

    //tab fields find by NAME
    public static final String SECURITY_TAB_TITLE = "//*[@data-target='#login-security']";
    public static final String SECURITY_TAB_CURRENT_PASSWORD = "original_password";
    public static final String SECURITY_TAB_NEW_PASSWORD = "password1";
    public static final String SECURITY_TAB_CONFIRM_PASSWORD = "password2";
    public static final String SECURITY_SAVE_BTN = personalSettingsTabSecurity+"//button[contains(.,'Save')]";
    public static final String SECURITY_ENABLE_BTN = "//button[contains(.,'Enable')]";



    //tab fields find by NAME
    public static final String EMAILS_TAB_TITLE = "//*[@data-target='#emails']";
    public static final String EMAILS_TAB_RADIO_ALWAYS = "//label[contains(.,'Always')]//input[@type='radio']";
    public static final String EMAILS_TAB_RADIO_OFFLINE = "//label[contains(.,'offline')]//input[@type='radio']";
    public static final String EMAILS_TAB_RADIO_NO_EMAILS = "//label[contains(.,'No email notifications')]//input[@type='radio']";
    public static final String EMAILS_TAB_RECIEVE_ATTACHMENTS = "//label[contains(.,'Receive attachments')]//input[@type='checkbox']";
    public static final String EMAILS_TAB_GET_COPY_OWN_EMAILS = "//label[contains(.,'Send me a copy')]//input[@type='checkbox']";
    public static final String EMAILS_TAB_ = "']";

    //tab fields find by NAME
    public static final String SIGNATURE_TAB_TITLE = "//*[@data-target='#signature']";
    public static final String SIGNATURE_TAB_TEXT_EDITOR = "//*[starts-with(@id, 'taTextElement')]";
    public static final String SIGNATURE_SAVE_BTN = personalSettingsTabSignature+"//button[contains(.,'Save')]";

    //tab fields find by NAME
    public static final String IMAP_TAB_TITLE = "//*[@data-target='#imap']";
    public static final String IMAP_SERVER_NAME = "imap.gmail.com";
    public static final String IMAP_SERVER_PORT = "993";
    public static final SelenideElement IMAP_TAB_FIELD_USENAME = $(byXpath(personalSettingsTabIMAP+"//input[@name='username']"));
    public static final SelenideElement IMAP_TAB_FIELD_PASSWORD = $(byXpath(personalSettingsTabIMAP+"//input[@name='password']"));;
    public static final SelenideElement IMAP_TAB_FIELD_SERVER_NAME = $(byXpath(personalSettingsTabIMAP+"//input[@name='location']"));
    public static final SelenideElement IMAP_TAB_FIELD_PORT = $(byXpath(personalSettingsTabIMAP+"//input[@name='port']"));
    public static final SelenideElement IMAP_TAB_BTN_CONNCET_GMAIL = $(byXpath(personalSettingsTabIMAP+"//button[contains(./text(),'Connect Gmail')]"));
    public static final SelenideElement IMAP_TAB_BTN_SAVE_AND_CHECK = $(byXpath(personalSettingsTabIMAP+"//button[contains(.,'Save & Check')]"));
    public static final SelenideElement IMAP_TAB_BTN_CHECK = $(byXpath(personalSettingsTabIMAP+"//div[@class='pull-right']/button[contains(.,'Check')]"));
    public static final SelenideElement IMAP_TAB_BTN_DELETE = $(byXpath(personalSettingsTabIMAP+""));
    public static final SelenideElement IMAP_TAB_SSL = $(byXpath(personalSettingsTabIMAP+"//input[@name='use_ssl']"));


  //tab fields find by NAME
    public static final String TIME_TRACKER_TAB_TITLE = "//*[@data-target='#timeTracker']";



}
