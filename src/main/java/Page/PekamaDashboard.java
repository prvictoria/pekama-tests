package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaDashboard extends Page {
    public static final SelenideElement dashboardProjectsTitle = $(byXpath("//h4[contains(.,'Projects')]"));
    public static final SelenideElement dashboardYourProfileTitle = $(byXpath("//h4[contains(.,'Your Profile And Team')]"));
    public static final SelenideElement dashboardUpcomingTitle = $(byXpath("//h4[contains(.,'UPCOMING')]"));
    public static final SelenideElement dashboardTasksTitle = $(byXpath("//h4[contains(.,'Tasks')]"));

    public static final SelenideElement dashboardSelectProjectTemplatesButton = $(byXpath("xpath=//button[@type='button'][2]"));
    public static final SelenideElement dashboardSelectProjectTemplatesTemplate01 = $(byXpath("//a[@ng-click='applyTemplate(template)'][1]"));
    public static final SelenideElement dashboardNewProject = $(byXpath("//button[@type='button'][contains(.,'+ NEW')]"));
    public static final SelenideElement dashboardProjectList01 = $(byXpath("//li[@class='item matter ng-scope']/a"));
    public static final SelenideElement dashboardProjectList02 = $(byXpath("//li[2][@class='item matter ng-scope']/a"));
    public static final SelenideElement dashboardProjectList03 = $(byXpath("//li[3][@class='item matter ng-scope']/a"));
    public static final SelenideElement dashboardProjectList04 = $(byXpath("//li[4][@class='item matter ng-scope']/a"));
    public static final SelenideElement dashboardProjectList05 = $(byXpath("//li[5][@class='item matter ng-scope']/a"));

    public static final SelenideElement dashboardInvite = $(byXpath("link=+ INVITE"));
    public static final SelenideElement dashboardTeamMembersQTY = $(byXpath("//*[@ui-sref='settings.organization.members']"));
    public static final SelenideElement dashboardAvailableProjectCount = $(byXpath("//a/span"));
    public static final SelenideElement dashboardBuyProjects = $(byXpath("link=*BUY MORE"));
    public static final SelenideElement dashboardUserAvatar = $(byXpath("???"));
    public static final SelenideElement dashboardProjectsLeft = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li[2]/div"));
    public static final SelenideElement dashboardYourProfileAndTeam = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div/div/div/h4"));
    public static final SelenideElement dashboardYourTeams = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div"));
    public static final SelenideElement dashboardTeamName = $(byXpath("//section[@id='page']/div[2]/div/div/div/div[2]/pkm-dashboard-account/div/div[2]/div/div[2]/ul/li/div[2]/div/div/span/span[2]/span"));

    public static final SelenideElement dashboardNoProjects = $(byXpath("//pkm-dashboard-projects//div[starts-with(@class, 'alert alert-empty')]"));

    public static final SelenideElement dashboardUpcoming = $(byXpath(""));
    public static final SelenideElement dashboardUpcomingToday = $(byXpath("//button[@type='button'][contains(.,'today')]"));
    public static final SelenideElement dashboardUpcomingMonth = $(byXpath("//button[@type='button'][contains(.,'month')]"));
    public static final SelenideElement dashboardUpcomingWeek = $(byXpath("//button[@type='button'][contains(.,'week')]"));
    public static final SelenideElement dashboardUpcomingDay = $(byXpath("xpath=(//button[@type='button'])[8]"));
    public static final SelenideElement dashboardUpcomingPastButton = $(byXpath("xpath=(//button[@type='button'])[3]"));
    public static final SelenideElement dashboardUpcomingNextButton = $(byXpath("xpath=(//button[@type='button'])[4]"));

    public static final SelenideElement dashboardUpcomingMyDeadlines = $(byXpath("//li/button[contains(.,'My Deadlines')]"));
    public static final SelenideElement dashboardUpcomingMyDeadlinesSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'My Deadlines')]"));
    public static final SelenideElement dashboardUpcomingAllDeadlines = $(byXpath("//li/button[contains(.,'All Deadlines')]"));
    public static final SelenideElement dashboardUpcomingAllDeadlinesSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'All Deadlines')]"));
    public static final SelenideElement dashboardUpcomingMyTasks = $(byXpath("//li/button[contains(.,'My Tasks')]"));
    public static final SelenideElement dashboardUpcomingMyTasksSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'My Tasks')]"));
    public static final SelenideElement dashboardUpcomingAllTasks = $(byXpath("//li/button[contains(.,'All Tasks')]"));
    public static final SelenideElement dashboardUpcomingAllTasksSelected = $(byXpath("//li[@class='active-calendar-filter' and contains(.,'All Tasks')]"));

    public static final SelenideElement dashboardTasks = $(byXpath(""));
    public static final SelenideElement dashboardTasksToDo = $(byLinkText("To Do"));
    public static final SelenideElement dashboardTasksDoing = $(byLinkText("Doing"));
}
