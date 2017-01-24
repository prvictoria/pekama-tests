package com.pekama.app;

import Page.*;
import Steps.*;
import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static Page.CommunityWizard.*;
import static Page.Emails.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaLanding.*;
import static Page.PekamaProject.*;
import static Page.PekamaSignUp.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.fillField;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PekamaProject {
    static final Logger rootLogger = LogManager.getRootLogger();
    String testProjectTitle = "new test project";
    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(), User2.PEKAMA_PASSWORD.getValue(), URL_LogIn);
        rootLogger.info("Create project");
        dashboardNewProject.waitUntil(visible, 15000).click();
        rootLogger.info("NW - New project");
        waitForModalWindow(MW_ProjectTitle);
        rootLogger.info("select project type");
        selectItemInDropdown(MW_Project_SelectType, MW_Project_InputType, CaseType.TRADEMARK.getValue());
        rootLogger.info("select defining");
        selectItemInDropdown(MW_Project_SelectDefining, MW_Project_InputDefining, Countries.PITCAIRN_ISLANDS.getValue());
        rootLogger.info("fill title");
        fillField(MW_Project_Title, testProjectTitle);
        rootLogger.info("submit");
        submitEnabledButton(MW_ProjectFinishButton);
        MW.shouldNot(visible);
        getActualUrl ();
        if ($$(byText(testProjectTitle))==null){Assert.fail("project name not matched of crated");}
    }

    @After
    public void after() {
        rootLogger.info("delete project - ");
        sleep(5000);
        PROJECT_BTN_DELETE.shouldBe(visible).click();
        StepsPekama.submitConfirmAction();
        open(URL_Logout);
        rootLogger.info("Open URL - " +URL_Logout);
    }

    @Test
    public void createProject_A_CheckDefaultState() {
        $$(byText(testProjectTitle)).shouldHaveSize(1);
        rootLogger.info("GUI test passed");
    }

    @Test
    public void createProject_B_editProjectName() {

    }
    @Test
    public void createProject_C_AddNumber() {

    }
    @Test
    public void createProject_D_addClassification() {

    }
    @Test
    public void createProject_E_addCollaborator() {

    }
    @Test
    public void createProject_F_addContact() {

    }
    @Test
    public void createProject_G_addDocument() {

    }
    @Test
    public void createProject_H_addFolder() {

    }
    @Test
    public void createProject_I_addTask() {

    }
    @Test
    public void createProject_L_deployEvent() {

    }
    @Test
    public void createProject_M_addCharges() {

    }
    @Test
    public void createProject_N_changeTypes() {

    }
    @Test
    public void createProject_O_addFamilyProject() {

    }
    @Test
    public void createProject_P_addConversation() {

    }
    @Test
    public void createProject_S_cloneProkect() {

    }
    @Test
    public void createCommunityCase() {

    }




}