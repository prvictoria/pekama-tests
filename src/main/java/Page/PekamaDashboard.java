package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

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
    public static final SelenideElement DASHBOARD_ProjectList01 = $(byXpath("//li[@class='item matter ng-scope']/a"));
    public static final SelenideElement DASHBOARD_ProjectList02 = $(byXpath("//li[2][@class='item matter ng-scope']/a"));
    public static final SelenideElement DASHBOARD_ProjectList03 = $(byXpath("//li[3][@class='item matter ng-scope']/a"));
    public static final SelenideElement DASHBOARD_ProjectList04 = $(byXpath("//li[4][@class='item matter ng-scope']/a"));
    public static final SelenideElement DASHBOARD_ProjectList05 = $(byXpath("//li[5][@class='item matter ng-scope']/a"));

    public static final SelenideElement DASHBOARD_Invite = $(byXpath("//button[contains(.,'+ INVITE')]"));
    public static final SelenideElement DASHBOARD_TeamMembersQTY = $(byXpath("//*[@ui-sref='settings.organization.members']"));
    public static final SelenideElement DASHBOARD_AvailableProjectCount = $(byXpath("//a/span"));
    public static final SelenideElement DASHBOARD_BuyProjects = $(byXpath("//button[contains(.,'BUY MORE')]"));
    public static final SelenideElement DASHBOARD_UserAvatar = $(byXpath("???"));
    public static final SelenideElement DASHBOARD_ProjectsLeft = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li[2]/div"));
    public static final SelenideElement DASHBOARD_YourProfileAndTeam = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div/div/div/h4"));
    public static final SelenideElement DASHBOARD_YourTeams = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div"));
    public static final SelenideElement DASHBOARD_TeamName = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div[2]/div/div/span/span[2]/span"));

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
