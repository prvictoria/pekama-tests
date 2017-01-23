package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaHeader extends Page {
    public static final SelenideElement HEADER = $(byXpath("//*[@class='content-page-header']"));
    public static final SelenideElement HEADER_DASHBOARD = $(byXpath("//*[@href='/a/dashboard']"));
    public static final SelenideElement HEADER_PROJECTS = $(byXpath("//*[@href='/a/reports/projects']"));
    public static final SelenideElement HEADER_TASKS = $(byXpath("//*[@href='/a/reports/tasks']"));
    public static final SelenideElement HEADER_EVENTS = $(byXpath("//*[@href='/a/reports/events']"));
    public static final SelenideElement HEADER_CHARGES = $(byXpath("//*[@href='/a/reports/charges']"));
    public static final SelenideElement HEADER_CONTACTS = $(byXpath("//*[@href='/a/reports/contacts']"));
    public static final SelenideElement HEADER_TUTORIAL_BTN = $(byXpath("//button[contains(.,'Run Tutorial')]"));
    public static final SelenideElement HEADER_SEARCH_FIELD = $(byXpath(HEADER+"//input"));
    public static final SelenideElement HEADER_SEARCH_ICON = $(byXpath(HEADER+"//form/button"));
    public static final SelenideElement HEADER_UserAvavtar = $(byXpath(HEADER+"//img[@class='img-circle']"));
    public static final SelenideElement HEADER_Username = $(byXpath("//ul[@role='menu']//*[@class='username ng-binding']"));
    public static final SelenideElement HEADER_Teamname = $(byXpath("//ul[@role='menu']//*[@class='ng-binding']"));

    public static final SelenideElement HEADER_UserDropdown = $(byXpath("//*[@class='pkm-icon-down-open']"));
    public static final SelenideElement HEADER_TeamAvatar = $(byXpath(""));
    public static final SelenideElement HEADER_PersonalSettings = $(byXpath("//ul[@role='menu']//*[@href='/a/settings/profile']"));
    public static final SelenideElement HEADER_TeamSettings = $(byXpath("//ul[@role='menu']//*[@href='/a/settings/team']"));
    public static final SelenideElement HEADER_LogOut = $(byXpath("//ul[@role='menu']//*[@pkm-confirm-click='logout()']"));
}
