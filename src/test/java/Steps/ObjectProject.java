package Steps;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;

import static Page.PekamaDashboard.DASHBOARD_BTN_NEW_PROJECT;
import static Page.PekamaProject.PROJECT_FULL_NAME;
import static Page.PekamaProjectCharges.TAB_CHARGES_LIST;
import static Page.PekamaReports.REPORTS_BTN_NEW_PROJECT;
import static Page.PekamaReportsProjects.*;
import static Page.UrlStrings.*;
import static Steps.StepsPekamaProject.setProjectDefining;
import static Steps.StepsPekamaProject.setProjectSubType;
import static Steps.StepsPekamaProject.setProjectType;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
    public String[] contactNames = null;

    public enum projectEnterPoint {DASHBOARD, REPORTS, CONTACTS, FAMILY, CLONE}
    public void setValues(
            String projectName, String projectMatterType,
            String projectDefining, String projectReference, String[] contactNames){
        this.projectName = projectName;
        this.projectMatterType = projectMatterType;
        this.projectDefining = projectDefining;
        this.projectReference = projectReference;
        this.contactNames = contactNames;
    }
    private void fillModalForm(ObjectProject project){
        this.projectName = project.projectName+"_"+randomString(10);
        this.projectMatterType = project.projectMatterType;
        this.projectDefining = project.projectDefining;
        this.projectReference = project.projectReference+"_"+randomString(10);
        this.projectTmNumber = project.projectTmNumber;

        waitForModalWindow(TILE_MW_PROJECT);
        if(project.projectMatterType!=null) {
            rootLogger.info("Select project type, actual: " + projectType);
            selectItemInDropdown(
                    MW_Project_SelectType, MW_Project_InputType, this.projectMatterType);
        }
        if(project.projectDefining!=null) {
            rootLogger.info("Select defining, actual: " +   this.projectDefining);
            selectItemInDropdown(
                    MW_Project_SelectDefining, MW_Project_InputDefining, this.projectDefining);
        }
        if(project.projectName!=null) {
            fillField(MW_Project_Title, this.projectName, "Title is:"+this.projectName);
        }
        if(project.projectReference!=null) {
            fillField(MW_Project_Reference,  this.projectReference, "REF number is: "+this.projectReference);
        }
        if(project.projectTmNumber!=null && (
                projectType.equals(MATTER_TYPE_TRADEMARK) || projectType.equals(MATTER_TYPE_PATENT))) {
            rootLogger.info("Fill TM number");
            fillField(MW_Project_TMNumber, this.projectTmNumber);
            sleep(1000);
            MW_Project_TMNumber.pressTab();
            sleep(1000);
        }
    return;
    }
    private void submitModal(){
        submitEnabledButton(MW_ProjectFinishButton);
        MW_ProjectFinishButtonWithSpinner.waitUntil(not(visible), 70000);
        sleep(2000);
    }
    private void getProjectValues(){
        sleep(2000);
        this.projectUrl = getActualUrl();
        this.projectFullName = PROJECT_FULL_NAME.getText();
    }
    public void selectProjectValues(String defining, String type, String subType){
        if(defining!=null) {
            sleep(1000);
            this.projectDefining = defining;
            setProjectDefining(defining);
        }
        if(type!=null) {
            sleep(2000);
            this.projectType = type;
            setProjectType(type);
        }
        if(subType!=null){
            sleep(3000);
            this.projectSubType = subType;
            setProjectSubType(subType);
        }
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
        sleep(2000);
        if(MW.isDisplayed()==false){
            getProjectValues();
        }
        return;
    }
    public static Boolean checkReportsProjectRow(Integer rowCount, ObjectProject project){
        if(project==null){
        rootLogger.info("No project row to validate");
        return false;
        }
        if(rowCount==null){
            REPORTS_LIST_ROWS.shouldHaveSize(0);
            checkTextInSelector(REPORTS_PLACEHOLDER_NO_DATA, PLACEHOLDER_NO_DATA);
            return true;
        }
        if(rowCount!=null && project!=null){
            if(project.projectName!=null){
                checkTextInSelector(REPORTS_PROJECT_TITLE(rowCount), project.projectName);
            }
            if(project.projectMatterType!=null){
                checkTextInSelector(REPORTS_PROJECT_MATTER_TYPE(rowCount), project.projectMatterType);
            }
            if(project.projectDefining!=null){
                checkTextInSelector(REPORTS_PROJECT_DEFINING(rowCount), project.projectDefining);
            }
            if(project.projectType!=null){
                checkTextInSelector(REPORTS_PROJECT_TYPE(rowCount), project.projectType);
            }
            if(project.projectSubType!=null){
                checkTextInSelector(REPORTS_PROJECT_SUBTYPE(rowCount), project.projectSubType);
            }
            if(project.projectStatus!=null){
                checkTextInSelector(REPORTS_PROJECT_STATUS(rowCount), project.projectStatus);
            }
            rootLogger.info("Row validation Passed");
            return true;
        }
        rootLogger.info("Row validation Failed");
        return false;
    }
}
