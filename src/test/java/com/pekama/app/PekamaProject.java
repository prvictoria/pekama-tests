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
import static Page.PekamaLanding.BTN_SIGN_UP;
import static Page.PekamaSignUp.SIGN_UP_TITLE;
import static Page.PekamaSignUp.SIGN_UP_TITLE_TEXT;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.TestsUrl.*;
import static Steps.StepsCommunity.*;
import static Steps.StepsExternal.*;
import static Steps.StepsPekama.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class PekamaProject {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Before
    public void before() {
        Configuration test = new Configuration();
        test.holdBrowserOpen = true;
        rootLogger.info("Open host");
        StepsPekama loginIntoPekama = new StepsPekama();
        loginIntoPekama.loginByURL(User2.GMAIL_EMAIL.getValue(), User2.PEKAMA_PASSWORD.getValue(), URL_LogIn);
        rootLogger.info("Create project");

    }

    @After
    public void after() {
        rootLogger.info("delete project - ");
        open(URL_Logout);
        rootLogger.info("Open URL - " +URL_Logout);
    }

    @Test
    public void createProject_A_CheckDefaultState() {

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
    public void createCommunityCase() {

    }




}