package Steps;

import org.apache.logging.log4j.*;

import static Pages.ModalWindows.*;
import static Pages.PekamaProject.*;
import static Pages.DataStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekamaProject.*;
import static Utils.Utils.getDate;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by VatslauX on 12-May-17.
 */
public class ObjectTask {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String taskTitle = null;
    public String taskStatus = null;
    public String taskStage = null;
    public String taskDueDate = null;
    public String taskAssignor = null;
    public String taskAssignee = null;
    public String taskImportance = null;
    public String taskComment = null;
    public String[] taskComments = null;
    public String[] tasksStatusesActive = null;
    public String[] tasksStatusesPassive = null;
    public String[] tasksAll = null;
    public Boolean taskZoneAll = false;
    public String[] taskTeams = null;
    public String linkeProjectName;
    public String taskUrl;



    //Objects
    public ObjectTask create(
            String taskTitle, String taskStatus,
            Integer dueDateFromToday, String taskImportance,
            String taskAssignor, String taskAssignee){
        this.taskTitle = taskTitle;
        this.taskStatus = taskStatus;
        this.taskDueDate = taskDueDate;
        this.taskImportance = taskImportance;
        this.taskAssignor = taskAssignor;
        this.taskAssignee = taskAssignee;

        taskAddNew();
        waitForModalWindow(TITLE_MW_NEW_TASK);
        if(taskTitle!=null){
            taskNewModalSetName(taskTitle);
        }
        if(dueDateFromToday!=null){
            this.taskDueDate = taskNewModalSetDueDateFromToday(dueDateFromToday);
        }

        if(taskStatus!=null){
            taskNewModalSelectStatus(taskStatus);
        }
        if(taskStatus==null){
            this.taskStatus = MW_TASK_SELECT_STATUS.getText();
        }

        if(taskImportance!=null){
            taskNewModalSelectImportance(taskImportance);
        }
        if(taskImportance==null){
            this.taskImportance = MW_TASK_SELECT_IMPORTANCE.getText();
        }

        if(taskAssignor!=null){
            taskNewModalSelectAssignor(taskAssignor);
        }
        if(taskAssignor==null){
            this.taskAssignor = MW_TASK_SELECT_ASSIGNOR.getText();
        }

        if(taskAssignee!=null){
            taskNewModalSelectAssignee(taskAssignee);
        }
        if(taskAssignee==null){
            this.taskAssignee = MW_TASK_SELECT_ASSIGNEE.getText();
        }

        if(taskTitle!=null){
            taskNewModalSubmit();
        }
        rootLogger.info("============================");
        rootLogger.info("Next task field created: ");
        rootLogger.info("Title: "+this.taskTitle);
        rootLogger.info("Status: "+this.taskStatus);
        rootLogger.info("DueDate: "+this.taskDueDate);
        rootLogger.info("Importance: "+this.taskImportance);
        rootLogger.info("Assignor: "+this.taskAssignor);
        rootLogger.info("Assignee: "+this.taskAssignee);
        rootLogger.info("============================");
    return this;
    }

    public void editTaskCard(String taskTitle,
                     Integer dueDateFromToday, String taskImportance,
                     String taskAssignor, String taskAssignee){
        this.taskTitle = taskTitle;
        this.taskDueDate = taskDueDate;
        this.taskImportance = taskImportance;
        this.taskAssignor = taskAssignor;
        this.taskAssignee = taskAssignee;
        PROJECT_TASK_CARD_BTN_SAVE.shouldBe(disabled);
        if(taskTitle!=null){
            fillField(PROJECT_TASK_CARD_TITLE, taskTitle);
        }
        if(dueDateFromToday!=null){
            this.taskDueDate = fillField(PROJECT_TASK_CARD_DATE, getDate(dueDateFromToday));
        }
        if(taskImportance!=null){
            selectItemInDropdown(
                    PROJECT_TASK_CARD_SELECT_IMPORTANCE,
                    PROJECT_TASK_CARD_INPUT_IMPORTANCE,
                    taskImportance);
        }
        if(taskAssignor!=null){
            selectItemInDropdown(
                    PROJECT_TASK_CARD_SELECT_ASSIGNOR,
                    PROJECT_TASK_CARD_INPUT_ASSIGNOR,
                    taskAssignor);
        }
        if(taskAssignee!=null){
            selectItemInDropdown(
                    PROJECT_TASK_CARD_SELECT_ASSIGNEE,
                    PROJECT_TASK_CARD_INPUT_ASSIGNEE,
                    taskAssignee);
        }
        if(taskTitle!=null){
            submitEnabledButton(PROJECT_TASK_CARD_BTN_SAVE);
        }
    }

    public void getTaskCardData(){
        this.taskTitle = PROJECT_TASK_CARD_TITLE.getValue();
        this.taskDueDate = PROJECT_TASK_CARD_DATE.getValue();
        this.taskImportance = PROJECT_TASK_CARD_SELECT_IMPORTANCE.getText();
        this.taskAssignor = PROJECT_TASK_CARD_SELECT_ASSIGNOR.getText();
        this.taskAssignee = PROJECT_TASK_CARD_SELECT_ASSIGNEE.getText();
        rootLogger.info("============================");
        rootLogger.info("Task fields: ");
        rootLogger.info("Title: "+this.taskTitle);
        rootLogger.info("DueDate: "+this.taskDueDate);
        rootLogger.info("Importance: "+this.taskImportance);
        rootLogger.info("Assignor: "+this.taskAssignor);
        rootLogger.info("Assignee: "+this.taskAssignee);
        rootLogger.info("============================");
    }
    public static Boolean checkTaskData(ObjectTask taskActual, ObjectTask taskExpected){
        if(taskActual.taskTitle.equalsIgnoreCase(taskExpected.taskTitle) &&
           taskActual.taskDueDate.equalsIgnoreCase(taskActual.taskDueDate) &&
           taskActual.taskImportance.equalsIgnoreCase(taskActual.taskImportance) &&
           taskActual.taskAssignor.equalsIgnoreCase(taskActual.taskAssignor) &&
           taskActual.taskAssignee.equalsIgnoreCase(taskActual.taskAssignee))
            {
        return true;
        }
        else  return false;
    }
    public String addComment(String text){
        return text;
    }
    public void delete(){

    }
}
