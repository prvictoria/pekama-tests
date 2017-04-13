package Steps;
import Page.TestsCredentials;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import static Page.CommunityDashboard.*;
import static Page.CommunityOutgoing.*;
import static Page.CommunityProfile.*;
import static Page.CommunityWizard.*;
import static Page.ModalWindows.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class StepsCommunity implements StepsFactory{
    static final Logger rootLogger = LogManager.getRootLogger();
    public static String searchQueryUrl;

    public static void searchExpertsQuery(String caseType, String country, String service) {
//        if(country==null && defaultValues==true) {
//            country = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
//        }
//        if(service==null && defaultValues==true) {
//            service = COMMUNITY_SERVICE;
//        }
        if(caseType!=null) {
            rootLogger.info("Selected case type - " + caseType);
            selectItemInDropdown(
                    WIZARD_SELECT_CaseType,
                    WIZARD_INPUT_CaseType,
                    caseType
            );
        }
        if(country!=null) {
            rootLogger.info("Selected defining - " + country);
            selectItemInDropdown(
                    WIZARD_SELECT_Defining,
                    WIZARD_INPUT_Defining,
                    country
            );
        }
        if(service!=null) {
            rootLogger.info("Selected service - "+service);
            selectItemInDropdown(
                    WIZARD_SELECT_Service,
                    WIZARD_INPUT_Service,
                    service
            );
        }
        sleep(1000);
   }
    @Deprecated
    public static String searchExpertsSubmit() {
        WIZARD_BTN_GetStarted.shouldBe(enabled).click();
        searchQueryUrl = url();
        rootLogger.info("Link to mailing list present - "+searchQueryUrl);
        return searchQueryUrl;
    }
    public static void searchServicesQuery(String PROFILE_SERVICE_CASE_TYPE, String PROFILE_SERVICE_COUNTRY, String price) {
        PROFILE_SELECT_CaseType.click();
        fillField(PROFILE_INPUT_CaseType, PROFILE_SERVICE_CASE_TYPE);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected case type - "+PROFILE_SERVICE_CASE_TYPE);

        PROFILE_SELECT_Defining.click();
        fillField(PROFILE_INPUT_Defining, PROFILE_SERVICE_COUNTRY);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected defining is - "+PROFILE_SERVICE_COUNTRY);

        PROFILE_SELECT_ExpertType.click();
        fillField(PROFILE_INPUT_ExpertType, COMMUNITY_SERVICE);
        CSS_SelectHighlighted.click();
        rootLogger.info("Selected service is - "+ COMMUNITY_SERVICE);

        PROFILE_INPUT_PRICE.clear();
        PROFILE_INPUT_PRICE.sendKeys(price);
        rootLogger.info("Selected case type - "+PROFILE_SERVICE_COUNTRY);
    }
    public static String findServiceRow(String PROFILE_SERVICE_CASE_TYPE, String PROFILE_SERVICE_COUNTRY) {
        String profileServiceRow = "//div[contains(.,'"+PROFILE_SERVICE_CASE_TYPE+"')]/following-sibling::div[contains(.,'"+PROFILE_SERVICE_COUNTRY+"')]/following-sibling::div//button[1]";
        return profileServiceRow;
    }
    public static String SERVICE_ROW = "//div[@class='row' and contains(.,'%s') and contains(.,'%s')]";
    public static void findServiceRow(boolean rowPresentOnPage, String... args) {
        String profileServiceRow = String.format(SERVICE_ROW, args);
        if ($(byXpath(profileServiceRow)).exists()!=rowPresentOnPage)
        {
            Assert.fail("Service present element state is - "+$(byXpath(profileServiceRow)).exists());
        }
    }
    public static void clickServiceRowEdit(String profileServiceCaseType, String profileServiceCountry) {
        //       String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[1]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[1]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }
    public static void clickServiceRowDelete(String profileServiceCaseType, String profileServiceCountry) {
        // String profileServiceRow = "//div[contains(.,'"+profileServiceCaseType+"')]/following-sibling::div[contains(.,'"+profileServiceCountry+"')]/following-sibling::div//button[2]";
        String profileServiceRow = "//div[@class='row' and contains(.,'"+ profileServiceCaseType +"') and contains(.,'"+ profileServiceCountry +"')]//button[2]";
        SelenideElement PROFILE_SERVICE_EDIT = $(byXpath(profileServiceRow));
        PROFILE_SERVICE_EDIT.click();
    }
    public static void changeServiceRate(String profileServiceCaseType, String profileServiceCountry, String newPrice) {
        SelenideElement serviceRateField = $(byXpath("//div[@class='row ng-scope' and contains(.,'"+profileServiceCaseType+"') and contains(.,'"+profileServiceCountry+"')]//input[@name='rate']"));
        serviceRateField.clear();
        serviceRateField.val(newPrice);
        serviceRateField.shouldHave(Condition.value(newPrice));
    }

    public static void selectExtpert(String expertName) {

    }

    public static void dismissMwConfirmAction(SelenideElement mwTitle, SelenideElement mwText, SelenideElement btnDismiss) {
        rootLogger.info("Check that MW '"+mwTitle+"' is present");
        mwTitle.shouldBe(visible);
        mwText.shouldBe(visible);
        rootLogger.info("Dismiss warning modal window - "+mwTitle);
        btnDismiss.click();
        mwTitle.shouldNotBe(visible);
        rootLogger.info("MW '"+mwTitle+"' closed");
    }
    public static void acceptMwConfirmAction(SelenideElement mwTitle, SelenideElement mwText, SelenideElement btnAccept) {
        rootLogger.info("Check that MW '"+mwTitle+"' is present");
        mwTitle.shouldBe(visible);
        mwText.shouldBe(visible);
        rootLogger.info("Accept confirm modal window - "+mwTitle);
        btnAccept.click();
        mwTitle.shouldNotBe(visible);
        rootLogger.info("MW closed");
    }
    public static void acceptReturnToFirstWizardStep() {
        rootLogger.info("Check that MW '"+MW_COMMUNITY_RETURN_TO_WIZARD_TITLE+"' is present");
        MW_COMMUNITY_RETURN_TO_WIZARD_TITLE.shouldBe(visible);
        MW_COMMUNITY_RETURN_TO_WIZARD_TEXT.shouldBe(visible);
        rootLogger.info("Accept confirm modal window - "+MW_COMMUNITY_RETURN_TO_WIZARD_TITLE);
        MW_COMMUNITY_BTN_YES.click();
        MW_COMMUNITY_RETURN_TO_WIZARD_TITLE.shouldNotBe(visible);
        rootLogger.info("MW closed");
    }
    public static boolean acceptCancelCase(boolean sendMsgToCollaborator) {
        rootLogger.info("Check that MW '"+MW_CANCEL_CASE_TITLE+"' is present");
        MW_CANCEL_CASE_TITLE.shouldBe(visible);
        //MW_CANCEL_CASE_TEXT.shouldBe(visible);
        MW_CANCEL_LINK_SUBMIT_WITHOUT_MSG.shouldHave(text(MW_CANCEL_LINK_TEXT));
        rootLogger.info("Accept confirm modal window - "+MW_CANCEL_CASE_TITLE);
        if (sendMsgToCollaborator==true){
            rootLogger.info("Message will send to Collaborator");
            MW_COMMUNITY_BTN_YES.click();}
        if (sendMsgToCollaborator==false){
            rootLogger.info("Cancel case without notification");
            MW_CANCEL_LINK_SUBMIT_WITHOUT_MSG.click();}
        MW_CANCEL_CASE_TITLE.shouldNotBe(visible);
        rootLogger.info("MW closed");
        sleep(1000);
        return true;
    }
    public static boolean acceptWithdrawCase(boolean sendMsgToCollaborator) {
        rootLogger.info("Check that MW '"+MW_WITHDRAW_CASE_TITLE+"' is present");
        MW_WITHDRAW_CASE_TITLE.waitUntil(visible, 20000);
        //MW_WITHDRAW_CASE_TEXT.shouldBe(visible);
        MW_WITHDRAW_LINK_SUBMIT_WITHOUT_MSG.shouldHave(text(MW_WITHDRAW_LINK_TEXT));
        rootLogger.info("Accept confirm modal window - "+MW_WITHDRAW_CASE_TITLE);
        if (sendMsgToCollaborator==true){
            rootLogger.info("Message will send to Collaborator");
            MW_COMMUNITY_BTN_YES.click();}
        if (sendMsgToCollaborator==false){
            rootLogger.info("Withdraw case without notification");
            MW_WITHDRAW_LINK_SUBMIT_WITHOUT_MSG.click();}
        MW_WITHDRAW_CASE_TITLE.shouldNotBe(visible);
        rootLogger.info("MW closed");
        sleep(1000);
        return true;
    }
    public static boolean acceptConfirmInstruction(boolean sendMsgToCollaborator) {
        rootLogger.info("Check that MW '"+MW_CONFIRM_INSTRUCTIONS_TITLE+"' is present");
        MW_CONFIRM_INSTRUCTIONS_TITLE.shouldBe(visible);
        //MW_CONFIRM_INSTRUCTIONS_TEXT.shouldBe(visible);
        MW_CONFIRM_INSTRUCTIONS_LINK_SUBMIT_WITHOUT_MSG.shouldHave(text(MW_CONFIRM_INSTRUCTIONS_LINK_TEXT));
        rootLogger.info("Accept confirm modal window - "+MW_CONFIRM_INSTRUCTIONS_TITLE);
        if (sendMsgToCollaborator==true){
            rootLogger.info("Message will send to Collaborator");
            MW_COMMUNITY_BTN_YES.click();}
        if (sendMsgToCollaborator==false){
            rootLogger.info("Confirm instrustion without notification");
            MW_CONFIRM_INSTRUCTIONS_LINK_SUBMIT_WITHOUT_MSG.click();}
        MW_CONFIRM_INSTRUCTIONS_TITLE.shouldNotBe(visible);
        rootLogger.info("MW closed");
        sleep(1000);
        return true;
    }
    public static boolean acceptCompletion(boolean sendMsgToCollaborator) {
        rootLogger.info("Check that MW '"+MW_CONFIRM_COMPLETION_TITLE+"' is present");
        MW_CONFIRM_COMPLETION_TITLE.shouldBe(visible);
        //MW_CONFIRM_COMPLETION_TEXT.shouldBe(visible);
        MW_CONFIRM_COMPLETION_LINK_SUBMIT_WITHOUT_MSG.shouldHave(text(MW_CONFIRM_COMPLETION_LINK_TEXT));
        rootLogger.info("Accept confirm modal window - "+MW_CONFIRM_COMPLETION_TITLE);
        if (sendMsgToCollaborator==true){
            rootLogger.info("Message will send to Collaborator");
            MW_COMMUNITY_BTN_YES.click();}
        if (sendMsgToCollaborator==false){
            rootLogger.info("Complete case without notification");
            MW_CONFIRM_COMPLETION_LINK_SUBMIT_WITHOUT_MSG.click();}
        MW_CONFIRM_COMPLETION_TITLE.shouldNotBe(visible);
        rootLogger.info("MW closed");
        sleep(1000);
        return true;
    }
    public static boolean checkIfExpertPresent(String teamName) {
        String row = String.format(expertRowLabel, teamName);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(teamName+" - expert present on page");
        return true;
    }
    public static boolean selectExpert(String teamName) {
        String row = String.format(expertRowLabel, teamName);
        $(byXpath(row)).waitUntil(visible, 20000).click();
        rootLogger.info(teamName+" - expert selected");
        return true;
    }
    public static boolean checkWizard1StepSelection(String CASE_TYPE, String TEST_CASE_COUNTRY, String COMMUNITY_SERVICE){
        WIZARD_SELECT_CaseType.shouldHave(text(CASE_TYPE));
        WIZARD_SELECT_Defining.shouldHave(text(TEST_CASE_COUNTRY));
        WIZARD_SELECT_Service.shouldHave(text(COMMUNITY_SERVICE));
        return true;
    }
    //Wizard steps
    public static String submitWizard1Step(String caseType, String country, String service){
        rootLogger.info("1st Step - Search");
        searchExpertsQuery(caseType, country, service);
        //String searchQueryUrl = searchExpertsSubmit();
        return searchQueryUrl;
    }
    public static String submitWizard1Step(String caseType){
        searchExpertsQuery(caseType, TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue(), COMMUNITY_SERVICE);
        //String searchQueryUrl = searchExpertsSubmit();
        return searchQueryUrl;
    }
    public static String wizardSelectExpert(String expertTeamName){
        rootLogger.info("Select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.waitUntil(visible, 20000).shouldBe(disabled);
        selectExpert(expertTeamName);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);
        return expertTeamName;
    }
    public static String submitWizard2Step(String caseName){
        rootLogger.info("2nd Step - select NEXT");
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();
        sleep(2000);
        return caseName;
    }
    public static String submitWizard3Step(){
        rootLogger.info("3rd Step - select Send Instruction");
        BTN_SEND_INSTRUCTION.waitUntil(visible, 20000).click();
        String a = "";
        return a;
    }
    public static String submitWizard4Step(){
        rootLogger.info("4th Step - select Instruct Now");
        WIZARD_BTN_INSTRUCT_NOW.waitUntil(visible, 20000).click();
        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);
        String a = "";
        return a;
    }
    //draft rows
    public static boolean checkCaseNameFirstRow(String caseType, String caseCountry) {
        String caseName = caseType+" in "+caseCountry;
        rootLogger.info(caseName);
        String row = String.format(caseRowNameFirst, caseName);
        $(byXpath(row)).shouldBe(visible).shouldHave(text(caseType));
        rootLogger.info(caseName+" - row with this case name displayed");
        return true;
    }
    public static boolean checkCaseNameFirstRow(String caseName) {
        String row = String.format(caseRowNameFirst, caseName);
        $(byXpath(row)).waitUntil(visible, 15000).shouldHave(text(caseName));
        rootLogger.info(caseName+" - row with this case name displayed");
        return true;
    }
    public static String getCountedCaseRow(String caseType, String caseCountry, int rowCount) {
        String count = Integer.toString (rowCount) ;
        String caseName = caseType+" in "+caseCountry;
        rootLogger.info(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(caseName+" - row with this case name displayed");
        return row;
    }
    public static String getFirstCaseRow(String caseType, String caseCountry) {
        String count = "1" ;
        String caseName = caseType+" in "+caseCountry;
        rootLogger.debug(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(caseName+" - row with this case name displayed");
        return row;
    }
    public static String getFirstCaseRow(String caseName) {
        String count = "1" ;
        rootLogger.debug(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.info(caseName+" - row with this case name displayed");
        return row;
    }

    public static boolean checkCaseStatus(String caseType, String caseCountry, int rowCount, String status) {
        String count = Integer.toString (rowCount) ;
        String caseName = caseType+" in "+caseCountry;
        rootLogger.info(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_LABEL_STATUS);
        SelenideElement statusLabel = $(byXpath(row+ROW_CONTROL_LABEL_STATUS));
        rootLogger.info(statusLabel);
        statusLabel.shouldHave(text(status));
        rootLogger.info(caseName+" - have status: "+status);
        return true;
    }
    public static boolean checkCaseStatus(String caseName, int rowCount, String status) {
        String count = Integer.toString (rowCount);
        rootLogger.info(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_LABEL_STATUS);
        SelenideElement statusLabel = $(byXpath(row+ROW_CONTROL_LABEL_STATUS));
        rootLogger.debug(statusLabel);
        statusLabel.shouldHave(text(status));
        rootLogger.info(caseName+" - have status: "+status);
        return true;
    }
    public static boolean checkCaseStatus(String caseName, String status) {
        String count = Integer.toString (1) ;
        rootLogger.info(caseName);
        String row = String.format(caseRowByCount, count, caseName);
        rootLogger.info(row);
        $(byXpath(row)).waitUntil(visible, 20000);
        rootLogger.debug(ROW_CONTROL_LABEL_STATUS);
        SelenideElement statusLabel = $(byXpath(row+ROW_CONTROL_LABEL_STATUS));
        rootLogger.debug(statusLabel);
        statusLabel.shouldHave(text(status));
        rootLogger.info(caseName+" - have status: "+status);
        return true;
    }
    public static String editCaseName(String caseName) {
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_CASE_NAME);
        SelenideElement name = $(byXpath(row+ROW_CONTROL_CASE_NAME));
        rootLogger.debug(name);
        name.shouldHave(text(caseName)).click();
        String newName = "NEW_NAME_"+randomString(10);
        SelenideElement nameField = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_FIELD_CASE_NAME));
        nameField.clear();
        $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_FIELD_CASE_NAME)).sendKeys(newName);
        SelenideElement saveBtn = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_BTN_SAVE_NAME));
        saveBtn.click();
        sleep(1000);
        $$(byText(newName)).shouldHaveSize(1);
        rootLogger.info(caseName+" - was changed to: "+newName);
        return newName;
    }
    public static boolean editCaseName(String caseName, int nameLength) {
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_CASE_NAME);
        SelenideElement name = $(byXpath(row+ROW_CONTROL_CASE_NAME));
        rootLogger.debug(name);
        name.shouldHave(text(caseName)).click();
        String newName = "NEW_NAME_"+randomString(nameLength);
        SelenideElement nameField = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_FIELD_CASE_NAME));
        nameField.clear();
        $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_FIELD_CASE_NAME)).sendKeys(newName);
        SelenideElement saveBtn = $(byXpath(ROW_CONTROL_CASE_ROW_FIRST+ROW_CONTROL_BTN_SAVE_NAME));
        saveBtn.click();
        $$(byText(ERROR_MSG_VALIDATION_LENGTH_1024)).shouldHaveSize(1);
        rootLogger.info("Max length validation present");
        return true;
    }
    public static boolean archiveCase(String caseName) {
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_BTN_ARCHIVE);
        SelenideElement archiveBtn = $(byXpath(row+ROW_CONTROL_BTN_ARCHIVE));
        rootLogger.debug(archiveBtn);
        archiveBtn.click();
        submitConfirmAction("Confirm action");
        sleep(3000);
        archiveBtn.shouldNotBe(visible);
        $(byXpath(row)).shouldNotBe(visible);
        rootLogger.info(caseName+" - case archived");
        return true;
    }
    public static boolean cancelCase(String caseName, boolean sendMsgToCollaborator) {
        String status = COMMUNITY_STATUS_CANCELLED;
        rootLogger.info("Cancel case - "+caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).waitUntil(visible, 20000);
        rootLogger.debug(ROW_CONTROL_BTN_ACTION);
        SelenideElement btn = $(byXpath(row+ROW_CONTROL_BTN_ACTION));
        rootLogger.debug(btn);
        btn.click();
        acceptCancelCase(sendMsgToCollaborator);
        sleep(1000);
        checkCaseStatus(caseName, status);
        rootLogger.info(caseName+" - case was canceled");
        return true;
    }
    public static boolean withdrawCase(String caseName, boolean sendMsgToCollaborator) {
        rootLogger.info("withdraw case");
        String status = COMMUNITY_STATUS_WITHDRAWN;
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_BTN_ACTION);
        SelenideElement btn = $(byXpath(row+ROW_CONTROL_BTN_ACTION));
        rootLogger.debug(btn);
        btn.click();
        acceptWithdrawCase(sendMsgToCollaborator);
        sleep(1000);
        checkCaseStatus(caseName, status);
        rootLogger.info(caseName+" - case was canceled");
        return true;
    }
    public static boolean confirmInstruction(String caseName, boolean sendMsgToCollaborator) {
        String status = COMMUNITY_STATUS_CONFIRMED;
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_BTN_ACTION);
        SelenideElement btn = $(byXpath(row+ROW_CONTROL_BTN_ACTION));
        rootLogger.debug(btn);
        btn.click();
        acceptConfirmInstruction(sendMsgToCollaborator);
        sleep(1000);
        checkCaseStatus(caseName, status);
        rootLogger.info(caseName+" - instruction were confirmed");
        return true;
    }
    public static boolean confirmCompletion(String caseName, boolean sendMsgToCollaborator) {
        String status = COMMUNITY_STATUS_COMPLETED;
        rootLogger.info(caseName);
        String row = String.format(caseRowByName, caseName);
        rootLogger.info(row);
        $(byXpath(row)).shouldBe(visible);
        rootLogger.debug(ROW_CONTROL_BTN_ACTION);
        SelenideElement btn = $(byXpath(row+ROW_CONTROL_BTN_ACTION));
        rootLogger.debug(btn);
        btn.click();
        acceptCompletion(sendMsgToCollaborator);
        sleep(1000);
        checkCaseStatus(caseName, status);
        rootLogger.info(caseName+" - instruction were confirmed");
        return true;
    }
    public static String createCase() {
        rootLogger.info("1st Step - Search");
        String expertTeam = TestsCredentials.User1.TEAM_NAME.getValue();
        String caseType = MATTER_TYPE_PATENT;
        String caseCountry = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "DEFAULT_CASE"+randomString(10);

        searchExpertsQuery(caseType, caseCountry, COMMUNITY_SERVICE);
        rootLogger.info("2nd Step - select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd Step - select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType+" in "+caseCountry));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();
        sleep(3000);

        rootLogger.info("4th Step - Send Instruction");
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();

        rootLogger.info("5th Step - select Instruct Now");
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();

        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);

        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, 1, status);
        return caseName;
    }
    public static String createCase(String expertTeam) {
        rootLogger.info("1st Step - Search");
        String caseType = MATTER_TYPE_PATENT;
        String caseCountry = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
        String status = COMMUNITY_STATUS_SENT;
        String caseName = "DEFAULT_CASE_"+randomString(10);

        searchExpertsQuery(caseType, caseCountry, COMMUNITY_SERVICE);
        rootLogger.info("2nd Step - select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd Step - select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType+" in "+caseCountry));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();
        sleep(3000);

        rootLogger.info("4th Step - select NEXT");
        BTN_SEND_INSTRUCTION.shouldBe(visible).click();

        rootLogger.info("5th Step - select NEXT");
        WIZARD_BTN_INSTRUCT_NOW.shouldBe(visible).click();

        waitForModalWindow("Congratulations!");
        MW_CONGRATULATION_OK.click();
        MW.shouldNotBe(visible);

        sleep(2000);
        checkCaseNameFirstRow(caseName);
        checkCaseStatus(caseName, 1, status);
        return caseName;
    }
    public static String createDraftCase(String expertTeam) {
        rootLogger.info("1st Step - Search");
        String caseType = MATTER_TYPE_PATENT;
        String caseCountry = TestsCredentials.Countries.PITCAIRN_ISLANDS.getValue();
        String caseName = "DEFAULT_DRAFT_CASE" + randomString(10);
        searchExpertsQuery(caseType, caseCountry, COMMUNITY_SERVICE);
        rootLogger.info("2nd Step - select expert");
        WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS.shouldBe(disabled);
        selectExpert(expertTeam);
        submitEnabledButton(WIZARD_BTN_GENERIC_REQUEST_INSTRUCTIONS);

        rootLogger.info("3rd Step - select NEXT");
        WIZARD_FIELD_CASE_NAME.shouldHave(value(caseType + " in " + caseCountry));
        fillField(WIZARD_FIELD_CASE_NAME, caseName);
        WIZARD_BTN_NEXT.click();
        sleep(3000);

        rootLogger.info("4th Step - select NEXT");
        BTN_SEND_INSTRUCTION.shouldBe(visible);
        return caseName;
    }
    public static boolean logoutCommunity(){
        COMMUNITY_HEADER_UserDropdown.shouldBe(visible).click();
        COMMUNITY_HEADER_LogOut.shouldBe(visible).click();
        sleep(3000);
        String urlAfterLogout = url();
        rootLogger.info(urlAfterLogout);
        assertEquals(ENVIRONMENT_COMMUNITY +"/", urlAfterLogout);
        rootLogger.info("User is logged out and redirected to Landing");
        return true;
    }
    public static void checkOutgoingDetailedCaseView(String caseName, String caseStatus){
        COMMUNITY_TAB_TITLE.shouldHave(text("Outgoing Cases"));
        checkText(caseName);
        checkText("Show all outgoing cases");
        checkText(caseStatus);
    }
    public static void checkIncomingDetailedCaseView(String caseName, String caseStatus){
        COMMUNITY_TAB_TITLE.shouldHave(text("Incoming Cases"));
        checkText(caseName);
        checkText("Show all incoming cases");
        checkText(caseStatus);
    }
}
