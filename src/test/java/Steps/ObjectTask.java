package Steps;

import org.apache.logging.log4j.*;

import static Page.ModalWindows.*;
import static Page.TestsStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekamaProject.*;

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
    public String taskMessage = null;
    public String[] taskMessages = null;
    public String[] tasksStatusesActive = null;
    public String[] tasksStatusesPassive = null;
    public String[] tasksAll = null;
    public Boolean taskZoneAll = false;
    public String[] taskTeams = null;



    //Objects
    public ObjectTask create(
            String taskTitle, String taskStatus, Integer dueDateFromToday, String taskImportance,
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

}
