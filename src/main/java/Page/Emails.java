package Page;

import static Page.PekamaReports.*;
import static Page.TestsUrlConfiguration.SELECT_HOST;

public class Emails {
    public static final String EMAIL_RESET_PASSWORD = "";
    public static final String EMAIL_RESET_PASSWORD_TITLE = "Password Restoration";
    public static final String EMAIL_RESET_PASSWORD_TEXT = "You've received this e-mail because you requested to reset the password for your user account. Press the button bellow to complete restoration.";
    public static final String EMAIL_RESET_PASSWORD_BTN = "Reset Password";
    public static final String EMAIL_RESET_PASSWORD_SUBJECT = "//span[contains(.,'Password Restoration [Pekama]')]";
    public static final String EMAIL_RESET_PASSWORD_BACKLINK = "//td/a[contains(@href, '"+SELECT_HOST+"/accounts/password/reset/')]";

    public static final String EMAIL_CONFIRM_REGISTRATION = "";
    public static final String EMAIL_CONFIRM_REGISTRATION_SUBJECT = "//span[contains(.,'Confirm Registration [Pekama]')]";
    public static final String EMAIL_CONFIRM_REGISTRATION_TITLE = "Registration Complete";
    public static final String EMAIL_CONFIRM_REGISTRATION_TEXT = "To finish registration, please confirm your account.";
    public static final String EMAIL_CONFIRM_REGISTRATION_BTN = "Confirm Account";
    public static final String EMAIL_CONFIRM_REGISTRATION_BACKLINK = "//td/a[contains(@href, '"+SELECT_HOST+"/accounts/confirm/')]";

    public static final String EMAIL_REPORT = "Pekama Report";
    public static final String EMAIL_REPORT_SUBJECT = "//span[contains(.,'Pekama Report')]";
    public static final String EMAIL_REPORT_TITLE = "";
    public static final String EMAIL_REPORT_TEXT = "This is the report that you configured in Pekama. You will get it every 999 days.";
    public static final String EMAIL_REPORT_ATTACHMENT = "//div[@title][contains(.,'csv')]";
    public static final String EMAIL_REPORT_ATTACHMENT_ABSOLUTE_PATH = "//div[@title][contains(.,'Projects Report Mailing List')][contains(.,'csv')]";
    public static final String EMAIL_REPORT_BACKLINK = "//a[contains(@href, '"+SELECT_HOST+"/filters/mailinglist/edit/')]";

//    public static final String EMAIL_SUBJ_CONFIRM_REGISTRATION = "";
//    public static final String EMAIL_SUBJ_IVITE_IN_PEKAMA = "";
//    public static final String EMAIL_SUBJ_INVITE_IN_PROJECT = "";
//    public static final String EMAIL_SUBJ_TO_TEAM_ADMIN = "";
//    public static final String EMAIL_SUBJ_COMMUNITY = "";

    public static final String INBOX_URL = "https://www.google.com/inbox/";
    public static final String INBOX_SIGNIN = "//a[contains(text(),'Sign in')]";
    public static final String INBOX_BTN_DONE = "//*[@title='Mark done']";
    public static final String INBOX_BTN_INBOX = "//nav[@id]/div/ul//span[contains(.,'Inbox')]";
    public static final String INBOX_BTN_DELETE = "//div[@jsaction='global.none']/ul/li[3][@role='button' and @title='Delete']";
    public static final String INBOX_MENU_REPLY = "//div[@role='button'][@title='Reply, Forward & more']";
    public static final String INBOX_MENU_SHOW_ORIGINAL = "//span[@class='do'][@title='Show original']";
    public static final String INBOX_BTN_TRASH = "//span[@title='Trash']";
    public static final String INBOX_BTN_EMPTY_TRASH = "//button[contains(.,'EMPTY TRASH NOW')]";
    public static final String INBOX_CONFIRM_EMPTY_TRASH = "//div[@role='dialog']//div/div[contains(.,'OK')]";

    public static final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    public static final String GMAIL_URL_LOG_OUT = "https://www.google.com/accounts/Logout"; //username  remains
    public static final String GMAIL_URL_SIGN_OUT = "https://accounts.google.com/Logout?continue=https%3A%2F%2Faccounts.google.com%2FServiceLogin%3Fsacu%3D1&il=true&zx=icxpgruz0yao";

    public static final String GMAIL_LOGIN_FIELD = "#Email";
    public static final String GMAIL_PASSWORD_FIELD = "#Passwd";
    public static final String GMAIL_SIGNIN_BTN = "#signIn";
    public static final String GMAIL_NEXT_BTN = "#next";
    public static final String GMAIL_INBOX_BTN = "//div[@role='navigation']";
    public static final String GMAIL_SELECT_MENU = "//*[@data-tooltip='Select']/div[1]/div";
    public static final String GMAIL_ARCHIVE_BTN = "//*[@data-tooltip='Archive']";

//    public static final String GMAIL_EMAIL_LIST = "//div[@role='main']//table/tbody/tr";
//    public static final String GMAIL_EMAIL_LIST_SUBJECT = "//div[@role='link']//span[@id]";
//    public static final String GMAIL_ROW_NUMBER = "[1]";
//    public static final String GMAIL_EMAIL_SUBJECT_PATH = GMAIL_EMAIL_LIST+GMAIL_ROW_NUMBER+GMAIL_EMAIL_LIST;
//
//    public static final String OUTLOOK_URL = "https://outlook.live.com/owa/?path=/mail/inbox";
//    public static final String OUTLOOK_INBOX_LIST = "//div[@role='listbox']//div[@data-convid][2]/*[@role='option']";
//    public static final String OUTLOOK_INBOX_EMAIL_SUNJECT = "//tbody/tr[1]//b[1]";

    public static final String EMAIL_SUBJECT_InviteInTeam = "//span[contains(.,'')]";
    public static final String EMAIL_SUBJECT_ForAdmin = "";
    public static final String EMAIL_SUBJECT_Report_LastWeek = "Pekama Report \"Last week's Events\"";
    public static final String EMAIL_SUBJECT_Report_6 = "Pekama Report \"My Recent and Approaching Tasks\"";
    public static final String EMAIL_SUBJECT_Report_7 = "Pekama Report \"Report Financials - All\"";
    public static final String EMAIL_SUBJECT_Report_8 = "";
    public static final String EMAIL_SUBJECT_Report_9 = "";
    public static final String EMAIL_SUBJECT_Report_10 = "";
    public static final String EMAIL_SUBJECT_11 = "Ready to confirm instructions in Pekama?";
    public static final String EMAIL_SUBJECT_12 = "Ready to confirm completion in Pekama?";
    public static final String EMAIL_SUBJECT_13 = "";
    public static final String EMAIL_SUBJECT_EXPIRED_BOX_TOKEN = "Your Box.com token has expired";
}