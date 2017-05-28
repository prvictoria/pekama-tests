package Tests;

import Steps.*;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.*;

import static Page.PekamaProject.*;
import static Steps.ObjectTask.checkTaskData;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Tests.BeforeTestsSetUp.*;
/**
 * Created by VatslauX on 20-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProjectTasks {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD_PEKAMA = User3.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private final static String OWNER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private static String member1 = null;
    private static String member2 = null;
    private static String member3 = null;

    private static String projectName;
    private static String projectUrl;
    private static boolean skipBefore = false;
    private static ObjectUser owner = ObjectUser.newBuilder().email(OWNER_LOGIN_EMAIL).passwordPekama(OWNER_PASSWORD_PEKAMA).build();

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            owner.login(owner.email, owner.passwordPekama, URL_LogIn);

            rootLogger.info("Create project");
            submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
            projectName = submitMwNewProject();
            projectUrl = getActualUrl();

            deleteAllMembers();
            member1 = createMemberInTeamSettings("fake-member@email.com");
            member2 = createMemberInTeamSettings(User1.GMAIL_EMAIL.getValue());
            member3 = createMemberInTeamSettings(User2.GMAIL_EMAIL.getValue());

            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        //clearBrowserCache();
        owner.login(owner.email, owner.passwordPekama, URL_LogIn);
        checkText(projectName);
        StepsPekamaProject.deleteAllTasks();
    }

    @Test
    public void tabTasks_All_Importances() {
        rootLogger.info("Check default task order - due date acceding");
        String taskName;
        taskName = taskCreate(
                4,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(1);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        taskName = taskCreate(
                3,
                TASK_IMPORTANCE_FATAL,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(2);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_FATAL,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        taskName = taskCreate(
                2,
                TASK_IMPORTANCE_FINAL_DEADLINE,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(3);

        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_FINAL_DEADLINE,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        taskName = taskCreate(
                1,
                TASK_IMPORTANCE_REMINDER,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(4);

        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_REMINDER,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        taskName = taskCreate(
                0,
                TASK_IMPORTANCE_TASK,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(5);

        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_All_Statuses() {
        rootLogger.info("Select all tasks");
        rootLogger.info("Check default task order - due date acceding");
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
        TAB_TASKS_ALL.shouldBe(visible).click();
        TASKS_ROWS.shouldHaveSize(0);

        String taskName;
        taskName = taskCreate(
                4,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(1);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        taskName = taskCreate(
                3,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_IN_PROGRESS);
        TASKS_ROWS.shouldHaveSize(2);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_IN_PROGRESS,
                TASKS_ACTION_FINISH)
        );
        taskName = taskCreate(
                2,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_COMPLETED);
        TASKS_ROWS.shouldHaveSize(3);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_COMPLETED,
                TASKS_ACTION_ACCEPT)
        );

        taskName = taskCreate(
                1,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_APPROVED);
        TASKS_ROWS.shouldHaveSize(4);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_ACCEPTED,
                null)
        );

        taskName = taskCreate(
                0,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_REJECTED);
        TASKS_ROWS.shouldHaveSize(5);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_REJECTED,
                TASKS_ACTION_RESTART)
        );

        taskName = taskCreate(
                -1,
                TASK_IMPORTANCE_DEADLINE,
                MW_TASK_STATUS_CANCELLED);
        TASKS_ROWS.shouldHaveSize(6);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_DEADLINE,
                TASK_STATUS_CANCELLED,
                null)
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_Filters_TasksActiveAndAllFilters() {
        String taskName;
        PROJECT_TAB_TASKS.waitUntil(visible, 15000).click();
        TASKS_ROWS.shouldHaveSize(0);
        taskName = taskCreate(
                0,
                TASK_IMPORTANCE_TASK,
                MW_TASK_STATUS_NEW);
        TASKS_ROWS.shouldHaveSize(1);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        rootLogger.info("Check that active tasks filter selected by default");

        TAB_TASKS_ACTIVE.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding active-link"));
        TAB_TASKS_ALL.shouldBe(visible).shouldHave(attribute("class", "btn-link ng-binding"));
        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_NEW+" state IS in Active filter");
        TASKS_ROWS.shouldHaveSize(1);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_IN_PROGRESS+" state displayed in Active filter");
        taskSelectStatusFormDropDown(PROJECT_TASK_DROPDOWN_STATUS_IN_PROGRESS);
        taskSelectFilterAllOrActive(false);
        TASKS_ROWS.shouldHaveSize(1);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_REJECTED+" state displayed in Active filter");
        taskSelectStatusFormDropDown(PROJECT_TASK_DROPDOWN_STATUS_REJECTED);
        taskSelectFilterAllOrActive(false);
        TASKS_ROWS.shouldHaveSize(1);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_COMPLETED+" state NOT in Active filter");
        taskSelectStatusFormDropDown(PROJECT_TASK_DROPDOWN_STATUS_COMPLETED);
        taskSelectFilterAllOrActive(false);
        TASKS_ROWS.shouldHaveSize(0);
        checkText(PLACEHOLDER_EMPTY_LIST);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_APPROVED+" state NOT in Active filter");
        taskSelectStatusFormDropDown(PROJECT_TASK_DROPDOWN_STATUS_APPROVED);
        taskSelectFilterAllOrActive(false);
        TASKS_ROWS.shouldHaveSize(0);
        checkText(PLACEHOLDER_EMPTY_LIST);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Check that Task "+PROJECT_TASK_DROPDOWN_STATUS_CANCELLED+" state NOT in Active filter");
        taskSelectStatusFormDropDown(PROJECT_TASK_DROPDOWN_STATUS_CANCELLED);
        taskSelectFilterAllOrActive(false);
        TASKS_ROWS.shouldHaveSize(0);
        checkText(PLACEHOLDER_EMPTY_LIST);
        taskSelectFilterAllOrActive(true);

        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_TasksAcceptFlow() {
        String taskAction = null;
        String taskStatus = null;
        String taskName = taskCreate();
        rootLogger.info(taskName);
        TASKS_ROWS.shouldHaveSize(1);
        rootLogger.info("New task - start");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.click();

        rootLogger.info("Finish task status");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_IN_PROGRESS,
                TASKS_ACTION_FINISH)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.click();

        rootLogger.info("Select Accepts task");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_COMPLETED,
                TASKS_ACTION_ACCEPT)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_ACCEPT.click();

        rootLogger.info("Task was accepted");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_ACCEPTED,
                null)
        );
        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_TasksRejectFlow() {
        String taskAction;
        String taskStatus;
        String taskName = taskCreate();
        rootLogger.info(taskName);
        TASKS_ROWS.shouldHaveSize(1);
        TASKS_NAME_IN_FIRST_ROW.shouldHave(text(taskName));
        TASKS_PRIORITY_IN_FIRST_ROW.shouldHave(text("Task"));
        rootLogger.info("New task - start");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_NOT_STARTED,
                TASKS_ACTION_START)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.click();

        rootLogger.info("Select "+TASKS_ACTION_FINISH+" task");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_IN_PROGRESS,
                TASKS_ACTION_FINISH)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.click();

        rootLogger.info("Select "+TASKS_ACTION_REJECT+" task");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_COMPLETED,
                TASKS_ACTION_REJECT)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW_REJECT.click();

        rootLogger.info("Select "+TASKS_ACTION_RESTART+" task");
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_REJECTED,
                TASKS_ACTION_RESTART)
        );
        TASKS_BTN_STATUS_ACTION_IN_FIRST_ROW.click();

        rootLogger.info("Task is in status"+TASK_STATUS_IN_PROGRESS);
        Assert.assertTrue(verifyTaskFirstRow(
                taskName,
                TASK_IMPORTANCE_TASK,
                TASK_STATUS_IN_PROGRESS,
                TASKS_ACTION_FINISH)
        );
        rootLogger.info("Test passed");
    }

    @Test
    public void tabTasks_TasksSorting_ByDueDate() {
        ObjectTask objectTask1 = new ObjectTask();
        objectTask1.create("Task1-date+1", null, 10, null, null, null);
        ObjectTask objectTask2 = new ObjectTask();
        objectTask2.create("Task2-date+0", null, 0, null, null, null);
        ObjectTask objectTask3 = new ObjectTask();
        objectTask3.create("Task1-date-1", null, -10, null, null, null);
        String taskOrder = TAB_TASKS_ACTUAL_ORDER.getText();
        Assert.assertEquals(TASKS_ORDER_DUE_DATE, taskOrder);
        TAB_TASKS_ORDER_ASCENDING.shouldBe(visible);

        TASKS_ROWS.shouldHaveSize(3);
        SelenideElement taskRow1Title = TAB_TASKS_TITLES_LIST.get(0);
        SelenideElement taskRow2Title = TAB_TASKS_TITLES_LIST.get(1);
        SelenideElement taskRow3Title = TAB_TASKS_TITLES_LIST.get(2);

        selectTaskOrderAndCheck(TASKS_ORDER_DUE_DATE,  false);
        taskRow1Title.shouldHave(text((objectTask1.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask3.taskTitle)));

        selectTaskOrderAndCheck(TASKS_ORDER_DUE_DATE,  true);
        taskRow1Title.shouldHave(text((objectTask3.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask1.taskTitle)));

        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_TasksSorting_ByTitle() {
        StepsPekamaProject.deleteAllTasks();

        ObjectTask objectTask3 = new ObjectTask();
        objectTask3.create("Task3-createdFist", null, null, null, null, null);
        ObjectTask objectTask2 = new ObjectTask();
        objectTask2.create("Task2-createdSecond", null, null, null, null, null);
        ObjectTask objectTask1 = new ObjectTask();
        objectTask1.create("Task1-createdLast", null, null, null, null, null);

        TASKS_ROWS.shouldHaveSize(3);
        SelenideElement taskRow1Title = TAB_TASKS_TITLES_LIST.get(0);
        SelenideElement taskRow2Title = TAB_TASKS_TITLES_LIST.get(1);
        SelenideElement taskRow3Title = TAB_TASKS_TITLES_LIST.get(2);

        selectTaskOrderAndCheck(TASKS_ORDER_TITLE,  true);
        taskRow1Title.shouldHave(text((objectTask1.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask3.taskTitle)));

        selectTaskOrderAndCheck(TASKS_ORDER_TITLE,  false);
        taskRow1Title.shouldHave(text((objectTask3.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask1.taskTitle)));

        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_TasksSorting_ByCreation() {
        ObjectTask objectTask3 = new ObjectTask();
        objectTask3.create("createdFist", null, null, null, null, null);
        ObjectTask objectTask2 = new ObjectTask();
        objectTask2.create("createdSecond", null, null, null, null, null);
        ObjectTask objectTask1 = new ObjectTask();
        objectTask1.create("createdLast", null, null, null, null, null);

        TASKS_ROWS.shouldHaveSize(3);
        SelenideElement taskRow1Title = TAB_TASKS_TITLES_LIST.get(0);
        SelenideElement taskRow2Title = TAB_TASKS_TITLES_LIST.get(1);
        SelenideElement taskRow3Title = TAB_TASKS_TITLES_LIST.get(2);

        selectTaskOrderAndCheck(TASKS_ORDER_LAST_CREATED,  false);
        taskRow1Title.shouldHave(text((objectTask1.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask3.taskTitle)));

        selectTaskOrderAndCheck(TASKS_ORDER_LAST_CREATED,  true);
        taskRow1Title.shouldHave(text((objectTask3.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask1.taskTitle)));

        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_TasksSorting_ByEdition() {
        ObjectTask objectTask1 = new ObjectTask();
        objectTask1.create("createdFist", null, null, null, null, null);
        ObjectTask objectTask2 = new ObjectTask();
        objectTask2.create("createdSecond", null, null, null, null, null);
        ObjectTask objectTask3 = new ObjectTask();
        objectTask3.create("createdLast", null, null, null, null, null);

        TASKS_ROWS.shouldHaveSize(3);
        SelenideElement taskRow1Title = TAB_TASKS_TITLES_LIST.get(0);
        SelenideElement taskRow2Title = TAB_TASKS_TITLES_LIST.get(1);
        SelenideElement taskRow3Title = TAB_TASKS_TITLES_LIST.get(2);

        selectTaskOrderAndCheck(TASKS_ORDER_LAST_MODIFIED,  false);
        taskRow1Title.shouldHave(text((objectTask3.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask1.taskTitle)));

        selectTaskOrderAndCheck(TASKS_ORDER_LAST_MODIFIED,  true);
        taskRow1Title.shouldHave(text((objectTask1.taskTitle)));
        taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
        taskRow3Title.shouldHave(text((objectTask3.taskTitle)));
        rootLogger.info("Test passed");
    }
    //TODO sort by first_name  BUG https://www.pivotaltracker.com/n/projects/1239770/stories/145955409
    @Test
    public void tabTasks_TasksSorting_ByAssigneeName() {
        member2 = User1.NAME_SURNAME.getValue();
        member3 = User2.NAME_SURNAME.getValue();

            ObjectTask objectTask1 = new ObjectTask();
            objectTask1.create("createdFist", null, null, null, null, member3);
            ObjectTask objectTask2 = new ObjectTask();
            objectTask2.create("createdSecond", null, null, null, null, member2);
            ObjectTask objectTask3 = new ObjectTask();
            objectTask3.create("createdLast", null, null, null, null, member1);

            TASKS_ROWS.shouldHaveSize(3);
            SelenideElement taskRow1Title = TAB_TASKS_TITLES_LIST.get(0);
            SelenideElement taskRow2Title = TAB_TASKS_TITLES_LIST.get(1);
            SelenideElement taskRow3Title = TAB_TASKS_TITLES_LIST.get(2);

            selectTaskOrderAndCheck(TASKS_ORDER_ASSIGNEE,  true);
            taskRow1Title.shouldHave(text((objectTask1.taskTitle)));
            taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
            taskRow3Title.shouldHave(text((objectTask3.taskTitle)));

            selectTaskOrderAndCheck(TASKS_ORDER_ASSIGNEE,  false);
            taskRow1Title.shouldHave(text((objectTask3.taskTitle)));
            taskRow2Title.shouldHave(text((objectTask2.taskTitle)));
            taskRow3Title.shouldHave(text((objectTask1.taskTitle)));
        rootLogger.info("Test passed");
    }
    @Test
    public void tabTasks_EditCard() {
        ObjectTask task = new ObjectTask();
        task.create("task", null, null, null, null, null);
        openTask(1);
        PROJECT_TASK_CARD_BTN_SAVE.shouldBe(disabled);
        PROJECT_TASK_CARD_BTN_SEND.shouldBe(disabled);
        ObjectTask taskDefault = new ObjectTask();
        taskDefault.getTaskCardData();
        task.editTaskCard("edited", 20, TASK_IMPORTANCE_DEADLINE, null, null);
        checkTaskData(taskDefault, task); //TODO values for comparison
        submitEnabledButton(PROJECT_TASK_CARD_BACK);
    }
    @Test
    public void tabTasks_PostComment() {
        ObjectTask task = new ObjectTask();
        task.create("task", null, null, null, null, null);
        openTask(1);
        checkText("No messages posted yet");
        postComment(LOREM_IPSUM_SHORT);
        checkTextNotPresent("No messages posted yet");
        deleteTaskCard();
    }
}
