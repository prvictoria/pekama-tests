package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;
import static Page.UrlConfig.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Emails extends Page {
    //reset password
    public static final SelenideElement EMAIL_RESET_PASSWORD = $(byXpath(""));
    public static final String EMAIL_RESET_PASSWORD_TITLE = "Password Restoration";
    public static final String EMAIL_RESET_PASSWORD_TEXT = "You've received this e-mail because you requested to reset the password for your user account. Press the button bellow to complete restoration.";
    public static final String EMAIL_RESET_PASSWORD_BTN = "Reset Password";
    public static final SelenideElement EMAIL_RESET_PASSWORD_SUBJECT = $(byXpath("//span[contains(.,'Password Restoration [Pekama]')]"));
    public static final SelenideElement EMAIL_RESET_PASSWORD_BACKLINK = $(byXpath("//td/a[contains(@href, '"+SELECT_HOST+"/accounts/password/reset/')]"));
    public static String EMAIL_RESET_PASSWORD_LINK = SELECT_HOST+"/accounts/password/reset/";
    public static String EMAIL_SUBJECT = "//span[contains(.,'%1$s')]";


    //Email confirm registration
    public static final SelenideElement EMAIL_CONFIRM_REGISTRATION = $(byXpath(""));
    public static final SelenideElement EMAIL_CONFIRM_REGISTRATION_SUBJECT = $(byXpath("//span[contains(.,'Confirm Registration [Pekama]')]"));
    public static final String EMAIL_CONFIRM_REGISTRATION_TITLE = "Registration Complete";
    public static final String EMAIL_CONFIRM_REGISTRATION_TEXT = "To finish registration, please confirm your account.";
    public static final String EMAIL_CONFIRM_REGISTRATION_BTN = "Confirm Account";
    public static final String EMAIL_CONFIRM_REGISTRATION_YOUR_EMAIL_IS = "Your sign in email is:";
    public static final SelenideElement EMAIL_CONFIRM_REGISTRATION_BACKLINK = $(byXpath("//td/a[contains(@href, '"+SELECT_HOST+"/accounts/confirm/')]"));
    public static final String EMAIL_CONFIRM_REGISTRATION_LINK = ENVIRONMENT_PEKAMA+"/accounts/confirm/";
    
    //Invite in TEAM
    public static final SelenideElement EMAIL_INVITE_IN_TEAM = $(byXpath(""));
    public static final SelenideElement EMAIL_INVITE_IN_TEAM_SUBJECT = $(byXpath("//span[contains(.,'invited you to join')]"));
    public static final String EMAIL_INVITE_IN_TEAM_TITLE = "You're invited to";
    public static final String EMAIL_INVITE_IN_TEAM_TEXT = " has sent you an invitation to join";
    public static final String EMAIL_INVITE_IN_TEAM_BTN = "Join";
    public static final SelenideElement EMAIL_INVITE_IN_TEAM_BACKLINK = $(byXpath("//td/a[contains(@href, '"+SELECT_HOST+"/accounts/invitation/')]"));

    //Email Invite in Community
    public static final SelenideElement EMAIL_INVITE_IN_COMMUNITY = $(byXpath(""));
    public static final SelenideElement EMAIL_INVITE_IN_COMMUNITY_SUBJECT = $(byXpath("//span[contains(.,'invited you to join Pekama Community')]"));
    public static final String EMAIL_INVITE_IN_COMMUNITY_TITLE = "You're invited to Pekama Community";
    public static final String EMAIL_INVITE_IN_COMMUNITY_TEXT = "Entered by user text";
    public static final String EMAIL_INVITE_IN_COMMUNITY_BTN = "Join Pekama Community";
    public static final SelenideElement EMAIL_INVITE_IN_COMMUNITY_BACKLINK = $(byXpath("//td/a[contains(@href, '"+ ENVIRONMENT_COMMUNITY +"/community/activate/')]"));
    //Emails congratulation - supplier was instructed
    public static final SelenideElement EMAIL_CONGRATULATION_SUBJECT = $(byXpath("//span[contains(.,'Case successfully instructed via Pekama!')]"));
    public static final String EMAIL_CONGRATULATION_TITLE = "Congratulations, you instructed";
    public static final String EMAIL_CONGRATULATION_TEXT = "Your community score will be raised once the work is marked as completed";
    //Emails Thank you for inviting
    public static final SelenideElement EMAIL_THANKS_FOR_INVITING_SUBJECT = $(byXpath("//span[contains(.,'Thank you for inviting')]"));
    public static final String EMAIL_CONGRATULATION_FOR_INVITING_TITLE = "Congratulations";
    public static final String EMAIL_CONGRATULATION_FOR_INVITING_TEXT = "your community score will be increased and this will lead to improved placement in your jurisdiction.";
    //Email  Invite in ProjectValues
    public static final SelenideElement EMAIL_INVITE_IN_PROJECT = $(byXpath(""));
    public static final  SelenideElement EMAIL_INVITE_IN_PROJECT_SUBJECT = $(byXpath("//span[contains(.,'invited you')]")); //Test002 Quality02 invited you to TM.PN.028318 new test project - TPTTUX ProjectValues
    //public static String EMAIL_INVITE_IN_PROJECT_SUBJECT = "'%s' '%s' invited you to TM.PN.028318 new test project - TPTTUX ProjectValues"; //Test002 Quality02 invited you to TM.PN.028318 new test project - TPTTUX ProjectValues
    public static String EMAIL_INVITE_IN_PROJECT_TITLE = "%s %s invited you to collaborate";
    public static String EMAIL_INVITE_IN_PROJECT_TEXT = "%s %s from created a project for %s and invites you to join in."; //User name, Surname, ProjectValues name
    public static final String EMAIL_INVITE_IN_PROJECT_BTN = "Join ProjectValues";
    public static final SelenideElement EMAIL_INVITE_IN_PROJECT_BACKLINK = $(byXpath("//td/a[contains(@href, '/n/legal/submatter/')]"));


    // Report email
    public static final String EMAIL_REPORT = "Pekama Report";
    public static final SelenideElement EMAIL_REPORT_SUBJECT = $(byXpath("//span[contains(.,'Pekama Report')]"));
    public static final SelenideElement EMAIL_REPORT_TITLE = $(byXpath(""));
    public static final String EMAIL_REPORT_TEXT = "This is the report that you configured in Pekama.";
    public static final SelenideElement EMAIL_REPORT_ATTACHMENT = $(byXpath("//div[@title][contains(.,'csv')]"));
    public static final SelenideElement EMAIL_REPORT_ATTACHMENT_ABSOLUTE_PATH = $(byXpath("//div[@title][contains(.,'Projects Report Mailing List')][contains(.,'csv')]"));
    public static final SelenideElement EMAIL_REPORT_BACKLINK = $(byXpath("//a[contains(@href, '"+SELECT_HOST+"/filters/mailinglist/edit/')]"));
    public static final SelenideElement EMAIL_UNSUBSCRIBE_LINK = $(byXpath("//a[@class='gmail_msg' and contains(@href, '"+SELECT_HOST+"/filters/mailinglist/unsubscribe/') and @target='_blank' and text()='here']"));

    //INBOX gmail app
    public static final String INBOX_URL = "https://www.google.com/inbox/";
    public static final SelenideElement INBOX_SIGNIN = $(byXpath("//a[contains(text(),'Sign in')]"));
    public static final SelenideElement INBOX_BTN_DONE = $(byXpath("//*[@title='Mark done']"));
    public static final SelenideElement INBOX_BTN_INBOX = $(byXpath("//nav[@id]/div/ul//span[contains(.,'Inbox')]"));
    public static final SelenideElement INBOX_BTN_DELETE = $(byXpath("//div[@jsaction='global.none']/ul/li[3][@role='button' and @title='Delete']"));
    public static final SelenideElement INBOX_MENU_REPLY = $(byXpath("//div[@role='button'][@title='Reply, Forward & more']"));
    public static final SelenideElement INBOX_MENU_SHOW_ORIGINAL = $(byXpath("//span[@class='do'][@title='Show original']"));
    public static final SelenideElement INBOX_BTN_TRASH = $(byXpath("//span[@title='Trash']"));
    public static final SelenideElement INBOX_BTN_EMPTY_TRASH = $(byXpath("//button[contains(.,'EMPTY TRASH NOW')]"));
    public static final SelenideElement INBOX_CONFIRM_EMPTY_TRASH = $(byXpath("//div[@role='dialog']//div/div[contains(.,'OK')]"));

    //Common gmail app - not used NOW
    public static final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    public static final String GMAIL_URL_LOG_OUT = "https://www.google.com/accounts/Logout"; //username  remains
    public static final String GMAIL_URL_SIGN_OUT = "https://accounts.google.com/Logout?continue=https%3A%2F%2Faccounts.google.com%2FServiceLogin%3Fsacu%3D1&il=true&zx=icxpgruz0yao";

    public static final SelenideElement GMAIL_LOGIN_COOKIE = $(byName("PersistentCookie"));
    public static final SelenideElement GMAIL_LOGIN_FIELD = $(byName("Email"));
    public static final SelenideElement GMAIL_PASSWORD_FIELD = $(byId("Passwd"));
    public static final SelenideElement GMAIL_SIGNIN_BTN = $(byId("signIn"));
    public static final SelenideElement GMAIL_NEXT_BTN = $(byId("next"));
    public static final SelenideElement GMAIL_INBOX_BTN = $(byXpath("//div[@role='navigation']"));
    public static final SelenideElement GMAIL_SELECT_MENU = $(byXpath("//*[@data-tooltip='Select']/div[1]/div"));
    public static final SelenideElement GMAIL_ARCHIVE_BTN = $(byXpath("//*[@data-tooltip='Archive']"));
    //      Outlook app not used now
    public static final SelenideElement GMAIL_EMAIL_LIST = $(byXpath("//div[@role='main']//table/tbody/tr"));
    public static final SelenideElement GMAIL_EMAIL_LIST_SUBJECT = $(byXpath("//div[@role='link']//span[@id]"));
    public static final SelenideElement GMAIL_ROW_NUMBER = $(byXpath("[1]"));
    public static final SelenideElement GMAIL_EMAIL_SUBJECT_PATH = $(byXpath(""));

    public static final SelenideElement OUTLOOK_URL = $(byXpath("https://outlook.live.com/owa/?path=/mail/inbox"));
    public static final SelenideElement OUTLOOK_INBOX_LIST = $(byXpath("//div[@role='listbox']//div[@data-convid][2]/*[@role='option']"));
    public static final SelenideElement OUTLOOK_INBOX_EMAIL_SUBJECT = $(byXpath("//tbody/tr[1]//b[1]"));


    //generic email strings
    public static final SelenideElement EMAIL_SUBJECT_InviteInTeam = $(byXpath("//span[contains(.,'')]"));
    public static final SelenideElement EMAIL_SUBJECT_ForAdmin = $(byText(""));
    public static final SelenideElement EMAIL_SUBJECT_Report_LastWeek = $(byText("Pekama Report \"Last week's Events\""));
    public static final SelenideElement EMAIL_SUBJECT_Report_6 = $(byText("Pekama Report \"My Recent and Approaching Tasks\""));
    public static final SelenideElement EMAIL_SUBJECT_Report_7 = $(byText("Pekama Report \"Report Financials - All\""));
    public static final SelenideElement EMAIL_SUBJECT_Report_8 = $(byText(""));
    public static final SelenideElement EMAIL_SUBJECT_Report_9 = $(byText(""));
    public static final SelenideElement EMAIL_SUBJECT_Report_10 = $(byText(""));
    public static final SelenideElement EMAIL_SUBJECT_11 = $(byText("Ready to confirm instructions in Pekama?"));
    public static final SelenideElement EMAIL_SUBJECT_12 = $(byText("Ready to confirm completion in Pekama?"));




    public static final SelenideElement EMAIL_SUBJECT_EXPIRED_BOX_TOKEN = $(byText("Your Box.com token has expired"));
}
