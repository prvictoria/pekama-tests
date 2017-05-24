package Steps;

import org.apache.logging.log4j.*;

import static Page.PekamaDashboard.DASHBOARD_BTN_NEW_PROJECT;
import static Page.PekamaReports.REPORTS_BTN_NEW_PROJECT;
import static Page.UrlStrings.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static com.codeborne.selenide.Selenide.sleep;

import static Page.ModalWindows.*;
import static Page.ModalWindows.MW_ProjectFinishButton;
import static Page.ModalWindows.MW_Project_TMNumber;
import static Page.TestsStrings.*;
import static Page.UrlConfig.MATTER_TYPE_PATENT;
import static Page.UrlConfig.*;
import static Steps.ObjectProject.projectEnterPoint.*;
import static Steps.StepsModalWindows.waitForModalWindow;
import static Steps.StepsPekama.*;
import static Utils.Utils.*;


/**
 * Created by VatslauX on 20-May-17.
 */
public class ObjectProject {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String project = null;
    public String projectName = null;
    public String projectNumber = null;
    public String projectFullName = null;
    public String projectMatterType = null;
    public String projectDefining = null;
    public String projectType = null;
    public String projectSubType = null;
    public String projectStatus = null;
    public String projectNotes = null;
    public String projectReference = null;
    public String projectTmNumber = null;
    public String projectUrl = null;

    enum projectEnterPoint {DASHBOARD, REPORTS, CONTACTS, FAMILY, CLONE}
    public void setValues(
            String projectName, String projectMatterType,
            String projectDefining, String projectReference){
    }
    private void fillModalForm(ObjectProject project){
        this.projectName = project.projectName+"_"+randomString(10);
        this.projectNumber = project.projectNumber;
        this.projectFullName = project.projectFullName;
        this.projectMatterType = project.projectMatterType;
        this.projectDefining = project.projectDefining;
        this.projectType = project.projectType;
        this.projectSubType = project.projectSubType;
        this.projectStatus = project.projectStatus;
        this.projectNotes = project.projectNotes;
        this.projectReference = project.projectReference+"_"+randomString(10);
        this.projectTmNumber = project.projectTmNumber;
        this.projectUrl = project.projectUrl;

        waitForModalWindow(TILE_MW_PROJECT);
        if(projectType!=null) {
            rootLogger.info("Select project type, actual: " + projectType);
            selectItemInDropdown(
                    MW_Project_SelectType, MW_Project_InputType, this.projectMatterType);
        }
        if(projectDefining!=null) {
            rootLogger.info("Select defining, actual: " + projectDefining);
            selectItemInDropdown(
                    MW_Project_SelectDefining, MW_Project_InputDefining,  this.projectNumber);
        }
        if(project.projectName!=null) {
            fillField(MW_Project_Title, this.projectName, "Title is:"+this.projectName);
        }
        if(project.projectReference!=null) {
            rootLogger.info("Fill ref number");
            fillField(MW_Project_Reference,  this.projectReference);
        }
        if(project.projectTmNumber!=null && (projectType.equals(MATTER_TYPE_TRADEMARK) || projectType.equals(MATTER_TYPE_PATENT))) {
            rootLogger.info("Fill TM number");
            fillField(MW_Project_TMNumber, this.projectTmNumber);
            sleep(1000);
            MW_Project_TMNumber.pressTab();
            sleep(1000);
        }


    }
    private void submitModal(){
        sleep(2000);
        submitEnabledButton(MW_ProjectFinishButton);
        sleep(3000);
    }
    private void getProjectValues(){
        this.projectUrl = getActualUrl();
    }
    public void create(projectEnterPoint enterPoint, ObjectProject project){
        switch (enterPoint){
            case DASHBOARD:
                openUrlIfActualNotEquals(URL_PEKAMA_DASHBOARD);
                submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);

                break;
            case REPORTS:
                openUrlIfActualNotEquals(URL_ReportsProjects);
                submitEnabledButton(REPORTS_BTN_NEW_PROJECT);
                break;

            case CONTACTS:
                openUrlIfActualNotEquals(URL_ReportsContacts);
                break;

            case FAMILY:
                openUrlIfActualNotEquals(project.projectUrl);
                break;

            case CLONE:
                openUrlIfActualNotEquals(project.projectUrl);
                break;
        }
        if(enterPoint!=CLONE){
            fillModalForm(project);
            submitModal();
        }
        getProjectValues();

    }


}
