package Pages;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.Page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaDashboard extends Page {
    public static final SelenideElement DASHBOARD_ProjectsTitle = $(byXpath("//h4[contains(.,'Projects')]"));
    public static final SelenideElement DASHBOARD_YourProfileTitle = $(byXpath("//h4[contains(.,'Your Profile And Team')]"));
    public static final SelenideElement DASHBOARD_UpcomingTitle = $(byXpath("//h4[contains(.,'UPCOMING')]"));
    public static final SelenideElement DASHBOARD_TasksTitle = $(byXpath("//h4[contains(.,'Tasks')]"));

    public static final SelenideElement DASHBOARD_BTN_PROJECT_TEMPLATES = $(byXpath("//button[@type='button'][2]"));
    public static final SelenideElement DASHBOARD_SelectProjectTemplatesTemplate01 = $(byXpath("//a[@ng-click='applyTemplate(template)'][1]"));
    public static final SelenideElement DASHBOARD_BTN_NEW_PROJECT = $(byXpath("//button[@type='button'][contains(.,'+ NEW')]"));
    public static final ElementsCollection DROPDOWN_PROJECT_TEMPLATES_LIST = $$(byXpath("//button[@type='button']/following-sibling::ul//a"));
//DASHBOARD PROFILE =========================================================================
    public static final String DASHBOARD_PROFILE = "//*[@class='dashboard-profile']";
    public static final SelenideElement DASHBOARD_PROFILE_AVATAR = $(byXpath(DASHBOARD_PROFILE+"//div[contains(@class,'user-photo')]/img"));
    public static final SelenideElement DASHBOARD_PROFILE_HI = $(byXpath(DASHBOARD_PROFILE+"//h4"));
    public static final SelenideElement DASHBOARD_PROFILE_SETTINGS_LINK = $(byXpath(DASHBOARD_PROFILE+"//*[@href='/a/settings/profile']"));
    public static final SelenideElement DASHBOARD_PROFILE_ACTIVE_TEAM_NAME = $(byXpath(DASHBOARD_PROFILE+"//div[text()='Your Teams']/following-sibling::*[@class='right']//span"));
    public static final SelenideElement DASHBOARD_PROFILE_TEAM_SELECT = $(byXpath(DASHBOARD_PROFILE+"//div[text()='Your Teams']/following-sibling::*[@class='right']//span"));
    public static final SelenideElement DASHBOARD_PROFILE_TEAM_INPUT = $(byXpath(DASHBOARD_PROFILE+"//div[text()='Your Teams']/following-sibling::*[@class='right']//input[@type='search']"));
    public static final SelenideElement DASHBOARD_PROFILE_BOOST_BTN = $(byXpath(DASHBOARD_PROFILE+"//*[text()='boost my document management']"));

//    public static final SelenideElement DASHBOARD_PROFILE_ = $(byXpath(DASHBOARD_PROFILE+""));

    public static final SelenideElement DASHBOARD_PROFILE_MEMBERS_LINK = $(byXpath(DASHBOARD_PROFILE+"//*[@href='/a/settings/team/members']/span"));
    public static final SelenideElement DASHBOARD_INVITE = $(byXpath(DASHBOARD_PROFILE+"//button[contains(.,'+ INVITE')]"));
    public static final SelenideElement DASHBOARD_PROFILE_MEMBERS_QTY = $(byXpath(DASHBOARD_PROFILE+"//*[@href='/a/settings/team/members']/span"));
    public static final ElementsCollection DASHBOARD_PROFILE_MEMBERS_LIST = $$(byXpath(DASHBOARD_PROFILE+"//*[@class='profile-members-list']/li"));
    public static final SelenideElement DASHBOARD_PROFILE_MEMBER_ROW_INDEX = $(byXpath(DASHBOARD_PROFILE+"//*[@class='profile-members-list']/li[%s]"));
    public static final SelenideElement DASHBOARD_PROFILE_MEMBER_ROW_FIRST = $(byXpath(DASHBOARD_PROFILE+"//*[@class='profile-members-list']/li[1]"));
    public static final SelenideElement DASHBOARD_PROFILE_MEMBER_ROW_LAST = $(byXpath(DASHBOARD_PROFILE+"//*[@class='profile-members-list']/li[last()]"));
    public static final SelenideElement DASHBOARD_PROFILE_MEMBER_ROW_NAMED = $(byXpath(DASHBOARD_PROFILE+""));

    public static final SelenideElement DASHBOARD_PROJECTS_QTY = $(byXpath(DASHBOARD_PROFILE+"//div[text()='Project Memoboxes Left']/following-sibling::*[@class='right']//span"));
    public static final SelenideElement DASHBOARD_BUY_MORE_BTN = $(byXpath(DASHBOARD_PROFILE+"//button[contains(.,'BUY MORE')]"));



    public static final SelenideElement DASHBOARD_NoProjects = $(byXpath("//pkm-dashboard-projects//div[starts-with(@class, 'alert alert-empty')]"));

    public static final SelenideElement DASHBOARD_Upcoming = $(byXpath(""));
    public static final SelenideElement DASHBOARD_UpcomingToday = $(byXpath("//button[@type='button'][contains(.,'today')]"));
    public static final SelenideElement DASHBOARD_UpcomingMonth = $(byXpath("//button[@type='button'][contains(.,'month')]"));
    public static final SelenideElement DASHBOARD_UpcomingWeek = $(byXpath("//button[@type='button'][contains(.,'week')]"));
    public static final SelenideElement DASHBOARD_UpcomingDay = $(byXpath("xpath=(//button[@type='button'])[8]"));
    public static final SelenideElement DASHBOARD_UpcomingPastButton = $(byXpath("xpath=(//button[@type='button'])[3]"));
    public static final SelenideElement DASHBOARD_UpcomingNextButton = $(byXpath("xpath=(//button[@type='button'])[4]"));

    public static final SelenideElement DASHBOARD_UpcomingMyDeadlines = $(byXpath("//li/button[contains(.,'My Deadlines')]"));
    public static final SelenideElement DASHBOARD_UpcomingMyDeadlinesSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'My Deadlines')]"));
    public static final SelenideElement DASHBOARD_UpcomingAllDeadlines = $(byXpath("//li/button[contains(.,'All Deadlines')]"));
    public static final SelenideElement DASHBOARD_UpcomingAllDeadlinesSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'All Deadlines')]"));
    public static final SelenideElement DASHBOARD_UpcomingMyTasks = $(byXpath("//li/button[contains(.,'My Tasks')]"));
    public static final SelenideElement DASHBOARD_UpcomingMyTasksSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'My Tasks')]"));
    public static final SelenideElement DASHBOARD_UpcomingAllTasks = $(byXpath("//li/button[contains(.,'All Tasks')]"));
    public static final SelenideElement DASHBOARD_UpcomingAllTasksSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'All Tasks')]"));
    public static final SelenideElement DASHBOARD_UpcomingMyDeadlines_RADIO = $(byXpath("//li[./button[contains(.,'My Deadlines')]]"));
    public static final SelenideElement DASHBOARD_UpcomingAllDeadlines_RADIO = $(byXpath("//li[./button[contains(.,'All Deadlines')]]"));
    public static final SelenideElement DASHBOARD_UpcomingMyTasks_RADIO = $(byXpath("//li[./button[contains(.,'My Tasks')]]"));
    public static final SelenideElement DASHBOARD_UpcomingAllTasks_RADIO = $(byXpath("//li[./button[contains(.,'All Tasks')]]"));

    public static final SelenideElement DASHBOARD_Tasks = $(byXpath(""));
    public static final SelenideElement DASHBOARD_TasksToDo = $(byLinkText("To Do"));
    public static final SelenideElement DASHBOARD_TasksDoing = $(byLinkText("Doing"));
}
