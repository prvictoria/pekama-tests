package Page;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class PekamaHeader {
    public static final String HEADER = "//*[@class='content-page-header']";
    public static final String HEADER_DASHBOARD = "//*[@href='/a/dashboard']";
    public static final String HEADER_PROJECTS = "//*[@href='/a/reports/projects']";
    public static final String HEADER_TASKS = "//*[@href='/a/reports/tasks']";
    public static final String HEADER_EVENTS = "//*[@href='/a/reports/events']";
    public static final String HEADER_CHARGES = "//*[@href='/a/reports/charges']";
    public static final String HEADER_CONTACTS = "//*[@href='/a/reports/contacts']";
    public static final String HEADER_TUTORIAL_BTN = "//button[contains(.,'Run Tutorial')]";
    public static final String HEADER_SEARCH_FIELD = HEADER+"//input";
    public static final String HEADER_SEARCH_ICON = HEADER+"//form/button";
    public static final String HEADER_UserAvavtar = HEADER+"//img[@class='img-circle']";
    public static final String HEADER_Username = "//ul[@role='menu']//*[@class='username ng-binding']";
    public static final String HEADER_Teamname = "//ul[@role='menu']//*[@class='ng-binding']";

    public static final String HEADER_UserDropdown = "//*[@class='pkm-icon-down-open']";
    public static final String HEADER_TeamAvatar = "";
    public static final String HEADER_PersonalSettings = "//ul[@role='menu']//*[@href='/a/settings/profile']";
    public static final String HEADER_TeamSettings = "//ul[@role='menu']//*[@href='/a/settings/team']";
    public static final String HEADER_LogOut = "//ul[@role='menu']//*[@pkm-confirm-click='logout()']";
}
