package Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class ModalWindows extends Page {
    private static final String mw = "//div[@class='modal-content']";
    public static final SelenideElement MW = $(byXpath("//div[@class='modal-content']"));
    public static final SelenideElement MW_BTN_SUBMIT = $(byXpath(mw+"//button[@submit]"));
    public static final SelenideElement MW_INPUT_NEW_MEMBER_EMAIL = $(byXpath(mw+"//*[@id='newFollowerEmail']"));

//reports
//mailing list
    public static final SelenideElement MW_MAILING_LIST_BTN_ACTIVATE = $(byXpath(mw+"//*[contains(.,'Activate')]"));
    public static final SelenideElement MW_MAILING_LIST_BTN_SAVE_AND_SEND_NOW = $(byXpath(mw+"//button[contains(.,'Save & Send Now')]"));
    public static final SelenideElement MW_MAILING_LIST_BTN_SEND_NOW = $(byXpath(mw+"//button[contains(.,'Send Now')]"));
    public static final SelenideElement MW_MAILING_LIST_CHECKBOX_ALL = $(byXpath(mw+"//input[@ng-model='all']"));
    public static final SelenideElement MW_MAILING_1USER_SELECT = $(byXpath(mw+"//ul//li[2]//input[@type='checkbox']"));
    public static final SelenideElement MW_MAILING_1USER_INTERVAL = $(byXpath(mw+"//ul//li[2]//input[@type='number']"));


    public static final SelenideElement MW_BTN_SAVE = $(byXpath(mw+"//button[contains(.,'Save')]"));
    public static final SelenideElement MW_BTN_CANCEL = $(byXpath(mw+"//button[contains(.,'Cancel')]"));
    public static final SelenideElement MW_BTN_ADD = $(byXpath(mw+"//button[contains(.,'Add')]"));
    public static final SelenideElement MW_BTN_OK = $(byXpath(mw+"//button[contains(.,'OK')]"));
    public static final SelenideElement MW_BTN_YES = $(byXpath(mw+"//button[contains(.,'Yes')]"));
    public static final SelenideElement MW_ICON_CLOSE = $(byXpath(mw+"//*[@aria-label='Close']"));
    public static final SelenideElement MW_BTN_CLOSE = $(byXpath(mw+"//*[contains .,'Close' ]"));


    public static final SelenideElement MW_TITLE = $(byXpath("//*[@class='modal-title']"));
    public static final SelenideElement MW_INPUT_DATE =  $(byXpath(mw+"//input[@class='form-control date-box']")); //click==today
    public static final SelenideElement MW_ICON_CALENDAR = $("css=.memobox-icon-calendar");

    public static final SelenideElement MW_ERROR = $(".help-block.error");
    public static final SelenideElement MW_SELECT_HIGHLIGHTED = $("span.ui-select-highlight");
    public static final SelenideElement BTN_WITH_TEXT = $(byXpath("//button[contains(.,'')]"));

    public static final SelenideElement genericButtonSave = $(byXpath("//button[contains(.,'Save')]"));
    public static final SelenideElement genericButtonOk = $(byXpath("//button[contains(.,'Ok')]"));
    public static final SelenideElement genericButtonYes = $(byXpath("//button[contains(.,'Yes')]"));
    public static final SelenideElement genericButtonAdd = $(byXpath("//button[contains(.,'add')]"));
    public static final SelenideElement genericButtonDelete = $(byXpath("//button[contains(.,'Delete')]"));
    public static final SelenideElement genericButtonCancel = $(byXpath("//button[contains(.,'Cancel')]"));
    public static final SelenideElement CSS_SelectHighlighted = $("span.ui-select-highlight");

    public static final SelenideElement MW_GenericButtonOk = $(byXpath("//button[text()='OK']"));
    public static final SelenideElement MW_GenericTitleSimple = $(byXpath("//h3[@class='modal-title']"));

// buy projects
    public static final SelenideElement MW_BuyProject = $(byXpath("sic!"));
    public static final SelenideElement MW_BuyProjectInputQTY = $(byXpath("//input[@type='number']"));
    public static final SelenideElement MW_BuyProjectTotalPrice = $(byXpath("//span[@class='your-price ng-binding']"));
    public static final SelenideElement MW_BuyProjectDiscount = $(byXpath("//span[@class='your-discount ng-binding']"));
    public static final SelenideElement MW_BuyProjectBuyProjects = $(byXpath("//div/button[contains(.,'Buy Projects')]"));
    public static final SelenideElement MW_checkoutBuy = $(byXpath(""));
    public static final SelenideElement MW_checkoutCardNumberField = $(byXpath(""));
    public static final SelenideElement MW_checkoutCardDate = $(byXpath(""));
    public static final SelenideElement MW_checkoutCardCVV = $(byXpath(""));
    public static final SelenideElement MW_checkoutSubmit = $(byXpath(""));

// 2 step verifcation
    public static final SelenideElement MW_EnableVerificationTitle = $(byXpath("//h3"));
    public static final SelenideElement MW_EnableVerificationClose = $(byXpath("//button[@type='button'][contains(.,'Close')]"));
    public static final SelenideElement MW_EnableVerificationNext = $(byXpath("//button[@type='submit'][contains(.,'Next')]"));
    public static final SelenideElement MW_EnableVerificationTelField = $(byName("phone"));
    public static final SelenideElement MW_EnableVerificationCountrySelect = $(byXpath("//div/div/div/div/div/span"));
    public static final SelenideElement MW_EnableVerificationCoutryField = $(byXpath("//div/div/div/div/input"));
    public static final SelenideElement MW_EnableVerificationConfirmCodeField = $(byName("two_factor_code"));
    public static final SelenideElement MW_EnableVerificationErrorArea = $(byXpath("//div[2]/form/div"));
//templates
    public static final SelenideElement MW_TaskTemplateSet = $(byXpath(""));
    public static final SelenideElement MW_TaskTemplateSet_FieldTitle = $(byXpath("//div[@class='modal-body']//input[@name]"));
    public static final SelenideElement MW_TaskTemplateSet_FieldDefining = $(byXpath("//div[@class='modal-body']/div[3]//input"));
    public static final SelenideElement MW_TaskTemplateSet_FieldType = $(byXpath("//div[@class='modal-body']/div[4]//input"));
    public static final SelenideElement MW_TaskTemplateSet_FieldEvent = $(byXpath("//div[@class='modal-body']/div[5]//input"));
    public static final SelenideElement MW_TaskTemplate_FieldTitle = $(byXpath("//div[@class='modal-body']//input[@name='title']"));
    public static final SelenideElement MW_TaskTemplate_Assignor = $(byXpath("//div[@placeholder='Choose assignor...']/span"));
    public static final SelenideElement MW_TaskTemplate_Assignee = $(byXpath("//div[@placeholder='Choose assignee...']/span"));
    public static final SelenideElement MW_TaskTemplate_Importance = $(byXpath("//div[@placeholder='Choose importance...']/span"));
    public static final SelenideElement MW_TaskTemplate_Status = $(byXpath("//div[@placeholder='Choose status...']/span"));
    public static final SelenideElement MW_TaskTemplate_DateOffset = $(byXpath(""));
    public static final SelenideElement MW_TaskTemplate_DateOffsetUnit = $(byXpath(""));

    public static final SelenideElement MW_EventTemplate_EventType = $(byXpath(mw+"//pkm-values-dropdown/div/div/span"));
    public static final SelenideElement MW_EventTemplate_Input = $(byXpath(mw+"//div[@name='event_type']/input[1]"));
    public static final SelenideElement MW_EventTemplate_AdditionalInfo = $(byXpath(mw+"//textarea"));

    public static final SelenideElement MW_EVENT_SELECT_TYPE = MW_EventTemplate_EventType;
    public static final SelenideElement MW_EVENT_INPUT_TYPE = MW_EventTemplate_Input;
    public static final SelenideElement MW_EVENT_INPUT_INFO = MW_EventTemplate_AdditionalInfo;
    public static final SelenideElement MW_EVENT_INPUT_DATE = MW_INPUT_DATE;

    public static final SelenideElement modalEmailparametersFieldEmailAddress = $(byXpath("//div/div[2]/div/div/input"));
    public static final SelenideElement modalEmailparametersSubjectLine = $(byName("template"));
    public static final SelenideElement modalEmailparametersSubjectLineTemplate = $(byXpath("//div[2]/div/form/input"));
    public static final SelenideElement modalEmailparametersShow = $(byXpath("//ng-switch/span"));

    public static final SelenideElement modalTemplatesPicktemplate = $(byXpath("//div/div[2]/div/div/input"));
    public static final SelenideElement modalTemplatesSubmatter = $(byXpath("//div/div/div/span/span[2]/span"));
    public static final SelenideElement modalTemplatesSubMatterType = $(byXpath("//div[3]/div/div/div/span/span"));
    public static final SelenideElement modalTemplatesEventType = $(byXpath("//div[4]/div/div/div/span/span"));
    public static final SelenideElement modalTemplatesMsgTemplate = $(byXpath("//a/div/div/p"));

    //conversation
    public static final SelenideElement MW_ConversationInputSubject = $(byXpath("//div[@class='modal-content']//input[@name='subject']"));
    public static final SelenideElement MW_ConversationInputFollower = $(byXpath("//div[@class='modal-content']//pkm-followers-picker//input"));
    public static final SelenideElement MW_ConversationInputZoneTeams = $(byXpath("//div[@class='modal-content']//pkm-organizations-picker//li/input"));

    public static final SelenideElement MW_ConversationCreatelButton = $(byXpath("//button[text()='Create']"));
    public static final SelenideElement MW_ConversationCancellButton = $(byXpath("//button[text()='Cancel']"));
    public static final SelenideElement MW_ConversationFollowerField = $(byXpath("//li/input"));
    public static final SelenideElement MW_ConversationFollowerSelect = $("span.result-name.ng-binding");

    public static final SelenideElement modalCheckboxPermissionAllTeams = $(byXpath("//*[@ng-disabled='uiState.disableAllTeams']"));
    //ProjectValues templates
    public static final SelenideElement modalProjectTemplateCancelButton = $(byXpath("//div[2]/button[2]"));
    public static final SelenideElement modalProjectTemplateOklButton = $(byXpath("//div[2]/button"));
    public static final SelenideElement MW_ProjectTemplateProjectTypeInput = $(byXpath("//div[@name='matter_type']/input[1]"));
    public static final SelenideElement MW_ProjectTemplateProjectDefiningInput = $(byXpath("//div[@name='defining']/input[1]"));
    //New project

    public static final SelenideElement MW_ProjectFinishButton = $(byXpath(mw+"//button[@type='submit'][contains(.,'FINISH')]"));
    public static final SelenideElement MW_Project_SelectType = $(byXpath(mw+"//div[@name='matter_type']/div/span/span[1]"));
    public static final SelenideElement MW_Project_InputType = $(byXpath(mw+"//div[@name='matter_type']/input[1]"));
    public static final SelenideElement MW_Project_SelectDefining = $(byXpath(mw+"//div[@name='defining']/div/span/span[1]"));
    public static final SelenideElement MW_Project_InputDefining = $(byXpath(mw+"//div[@name='defining']/input[1]"));
    public static final SelenideElement MW_Project_TMNumber = $(byXpath(mw+"//input[@name='official_lookup']"));
    public static final SelenideElement MW_Project_Title = $(byXpath(mw+"//input[@name='title']"));
    public static final SelenideElement MW_Project_Reference = $(byXpath(mw+"//input[@name='number']"));
    //MW new folder
    public static final SelenideElement MW_NEW_FOLDER_INPUT_NAME = $(byXpath(mw+"//input[@name='name']"));
    public static final SelenideElement modalMembersOkButton = $(byXpath("//div[2]/button"));
    //MW deploy doc templates
    public static final SelenideElement MW_DeployDoc_Title = $("css=h3.modal-title");
    public static final SelenideElement MW_DEPLOY_DOC_INPUT_FILE_NAME = $(byXpath("//input[@name='name']"));
    public static final SelenideElement MW_DeployDoc_01TemplateWord = $(byXpath("//li/label[text()='New Word document']"));
    public static final SelenideElement MW_DeployDoc_02TemplateExcel = $(byXpath("//li/label[text()='New Excel sheet']"));
    public static final SelenideElement MW_DeployDoc_03TemplateCustom = $(byXpath("//li[3]/label"));
    public static final SelenideElement MW_DeployDoc_FilterName = $(byXpath(""));
    public static final SelenideElement MW_DeployDoc_SelectDefining = $(byXpath(""));
    public static final SelenideElement MW_DeployDoc_SelectType = $(byXpath(""));
    public static final SelenideElement MW_DeployDoc_SelectEvent = $(byXpath(""));
    public static final SelenideElement MW_DEPLOY_DOC_BTN_CREATE = $(byXpath("//button[contains(.,'Create')]"));
    public static final SelenideElement MW_DeployDoc_ButtonCancel = $(byXpath("//button[contains(.,'Cancel')]"));

    public static final SelenideElement MW_DeployTask_Title = $(byName("title"));
    public static final SelenideElement MW_DeployTask_SelectEvent = $(byXpath("//div[@id='task-template-picker']/div/div/div/span/span"));
    public static final SelenideElement MW_DeployTask_InputEvent = $(byXpath("//input[@placeholder='Select an event...']"));
    public static final SelenideElement MW_DeployTask_01Template = $(byXpath("//div[@id='task-template-picker']/div[2]/div/div/label/span"));
    public static final SelenideElement MW_DeployTask_Apply = $(byXpath("//button[contains(.,'Apply')]"));
    public static final SelenideElement MW_DeployTask_Cancel = $(byXpath(""));

    public static final SelenideElement MW_TaskAssignorField = $(byXpath(""));
    public static final SelenideElement MW_TaskAssineeField = $(byXpath(""));
    public static final SelenideElement MW_TaskAssignorSelect = $(byXpath("css=span.ng-binding.ng-scope"));
    public static final SelenideElement MW_TaskAssineeSelect = $(byXpath("//div[2]/div/div/span/span[2]/span"));
    public static final SelenideElement MW_TaskImportanceField = $(byXpath(""));
    public static final SelenideElement MW_TaskImportanceSelect = $(byXpath("//pkm-values-dropdown/div/div/span/span[2]"));
//    public static final SelenideElement MW_TaskImportanceReminder = $(byXpath(""));
//    public static final SelenideElement MW_TaskImportanceDeadline = $(byXpath(""));
//    public static final SelenideElement MW_TaskImportanceFinalDeadline = $(byXpath(""));
//    public static final SelenideElement MW_TaskImportanceTask = $(byXpath(""));
//    public static final SelenideElement MW_TaskImportanceFatal = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusField = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusSelect = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusNew = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusInProgress = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusCompleted = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusApproved = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusRejected = $(byXpath(""));
//    public static final SelenideElement MW_TaskStatusCanceled = $(byXpath(""));
//    public static final SelenideElement MW_Task = $(byXpath(""));

    public static final SelenideElement MW_Financial = $(byXpath(""));
    public static final SelenideElement MW_FinancialClickFrom = $(byXpath("//span[2]/span"));
    public static final SelenideElement MW_FinancialInputFrom = $(byXpath(""));
    public static final SelenideElement MW_FinancialClickTo = $(byXpath("//div[2]/div/div/span/span"));
    public static final SelenideElement MW_FinancialInputTo = $(byXpath(""));
    public static final SelenideElement MW_FinancialClickBy = $(byXpath("//div[3]/div/div/span/span[2]/span"));
    public static final SelenideElement MW_FinancialInputBy = $(byXpath(""));
    public static final SelenideElement MW_FinancialClickStatus = $(byXpath("//div[2]/div/div/div/span/span[2]/span"));
    public static final SelenideElement MW_FinancialInputStatus = $(byXpath(""));
    public static final SelenideElement MW_FinancialClickType = $(byXpath("//pkm-values-dropdown/div/div/span/span"));
    public static final SelenideElement MW_FinancialInputType = $(byXpath(""));
    public static final SelenideElement MW_FinancialInputDueDate = $(byXpath("generic datepicker!"));
    public static final SelenideElement MW_FinancialInputItem = $(byXpath("//textarea"));
    public static final SelenideElement MW_FinancialClickCurrency = $(byXpath("//div[3]/div[2]/pkm-values-dropdown/div/div/span/span"));
    public static final SelenideElement MW_FinancialInputCurrency = $(byXpath(""));
    public static final SelenideElement MW_FinancialInputQty = $(byXpath("//input[@name='quantity']"));
    public static final SelenideElement MW_FinancialInputTimeHour = $(byXpath("//input[@name='hours']"));
    public static final SelenideElement MW_FinancialInputTimeMin = $(byXpath("//input[@name='minutes']"));
    public static final SelenideElement MW_FinancialInputTimeRate = $(byXpath("//input[@name='hourly_rate']"));
    public static final SelenideElement MW_FinancialInputPrice = $(byXpath("//input[@name='price']"));
    public static final SelenideElement MW_FinancialInputVat = $(byXpath("//input[@name='vat']"));
    public static final SelenideElement MW_FinancialInputDisc = $(byXpath("//input[@name='discount']"));
    public static final SelenideElement MW_FinancialInputTotal = $(byXpath(""));

    public static final SelenideElement MW_TimeTrackerSave = $(byXpath(""));
    public static final SelenideElement MW_TimeTrackerPause = $(byXpath(""));
    public static final SelenideElement MW_TimeTrackerDiscard = $(byXpath("//label[@class='danger']/input"));




    public static final SelenideElement MW_Number = $(byXpath(""));



    public static final SelenideElement MW_Document = $(byXpath(""));

    public static final SelenideElement COMMENT_007 = $(byText("DATEPICKER"));
    public static final SelenideElement MW_DATAPICKER_TODAY = $(byXpath(mw+"//i[@class='pkm-icon-calendar']"));
    public static final SelenideElement dueDateInputField = $("input.form-control.date-box");
    public static final SelenideElement datepickerToday = $(".day.active.today");
    public static final SelenideElement calendarBody = $("table.table-condensed");
    public static final SelenideElement calendarDay = $(byXpath("//*[@data-action='selectDay'][contains .,'' ]"));
    public static final SelenideElement calendarDay01 = $(byXpath("//*[@data-action='selectDay'][contains .,'1' ]"));
    public static final SelenideElement calendarDay02 = $(byXpath("//*[@data-action='selectDay'][contains .,'2' ]"));
    public static final SelenideElement calendarDay03 = $(byXpath("//*[@data-action='selectDay'][contains .,'3' ]"));
    public static final SelenideElement calendarDay04 = $(byXpath("//*[@data-action='selectDay'][contains .,'4' ]"));
    public static final SelenideElement calendarDay05 = $(byXpath("//*[@data-action='selectDay'][contains .,'5' ]"));
    public static final SelenideElement calendarDay06 = $(byXpath("//*[@data-action='selectDay'][contains .,'6' ]"));
    public static final SelenideElement calendarDay07 = $(byXpath("//*[@data-action='selectDay'][contains .,'7' ]"));
    public static final SelenideElement calendarDay08 = $(byXpath("//*[@data-action='selectDay'][contains .,'8' ]"));
    public static final SelenideElement calendarDay09 = $(byXpath("//*[@data-action='selectDay'][contains .,'9' ]"));
    public static final SelenideElement calendarDay10 = $(byXpath("//*[@data-action='selectDay'][contains .,'10' ]"));
    //EVENT
    public static final SelenideElement MW_eventTypeApplicationFiled = $(byXpath("//*[@class='ng-binding ng-scope'][contains .,'Application filed']"));
    public static final SelenideElement MW_eventTypeApplicationPublished = $(byXpath("//*[@class='ng-binding ng-scope'][contains .,'Application published' ]"));
    public static final SelenideElement MW_eventTypeSubMatterCreated = $(byXpath("//*[@class='ng-binding ng-scope'][contains .,'Sub Matter Created' ]"));
    //MW new Contact
    public static final SelenideElement MW_Contact_ = $(byXpath(""));
    public static final SelenideElement MW_Contact_SelectType = $(byXpath("//select[@name='type']"));
    public static final String CONTACT_TYPE_PERSON = "Person";
    public static final String CONTACT_TYPE_COMPANY = "Company";
    public static final SelenideElement MW_Contact_Entity = $(byXpath("//input[@name='legal_entity_name']"));
    public static final SelenideElement MW_Contact_NAME = $(byXpath(mw+"//input[@name='first_name']"));
    public static final SelenideElement MW_Contact_SURNAME = $(byXpath(mw+"//input[@name='last_name']"));
    public static final SelenideElement MW_Contact_Select_COMPANY = $(byXpath(mw+""));
    public static final SelenideElement MW_Contact_Select_COMPANY_NAME = $(byXpath(mw+""));
    public static final SelenideElement MW_Contact_Input_COMPANY = $(byXpath(mw+""));
    public static final SelenideElement MW_Contact_EMAIL = $(byXpath(mw+"//input[@name='email']"));
    public static final SelenideElement MW_Contact_PHONE = $(byXpath(mw+"//input[@name='phone_number']"));
    public static final SelenideElement MW_Contact_FAX = $(byXpath(mw+"//input[@name='fax_number']"));
    public static final SelenideElement MW_Contact_MOBILE = $(byXpath(mw+"//input[@name='cellphone_number']"));
    public static final SelenideElement MW_Contact_STREET = $(byXpath(mw+"//input[@name='street_address']"));
    public static final SelenideElement MW_Contact_ZIP = $(byXpath(mw+"//input[@name='postal_code']"));
    public static final SelenideElement MW_Contact_CITY = $(byXpath(mw+"//input[@name='city']"));
    public static final SelenideElement MW_Contact_REGION = $(byXpath(mw+"//input[@name='region']"));
    public static final SelenideElement MW_Contact_SelectCountry = $(byXpath("//div[@name='country']/div/span"));
    public static final SelenideElement MW_Contact_SelectCountryName = $(byXpath("//div[@name='country']/div/span/span/span"));
    public static final SelenideElement MW_Contact_InputCountry = $(byXpath("//div[@name='country']/input"));
    //MW Merge Contact
    public static final SelenideElement MW_MergeContact_ = $(byXpath(""));
    public static final SelenideElement MW_MergeContact_Select = $(byXpath("//div[@class='modal-body']//span[text()='Pick one...']"));
    public static final SelenideElement MW_MergeContact_Input = $(byXpath("//div[@class='modal-body']//input[@type='search']"));
    //MW Task
    public static final SelenideElement MW_TaskImportanceDeadline = $(byXpath("//span[text()='Deadline']"));
    public static final SelenideElement MW_TaskImportanceFatal = $(byXpath("//span[text()='Fatal']"));
    public static final SelenideElement MW_TaskImportanceFinalDeadline = $(byXpath("//span[text()='Final Deadline']"));
    public static final SelenideElement MW_TaskImportanceReminder = $(byXpath("//span[text()='Reminder']"));
    public static final SelenideElement MW_TaskImportanceTask = $(byXpath("//span[text()='Task']"));
    public static final SelenideElement MW_TaskStatusNew = $(byXpath("//span[text()='New']"));
    public static final SelenideElement MW_TaskStatusInProgress = $(byXpath("//span[text()='In Progress']"));
    public static final SelenideElement MW_TaskStatusCompleted = $(byXpath("//span[text()='Completed']"));
    public static final SelenideElement MW_TaskStatusApproved = $(byXpath("//span[text()='Approved']"));
    public static final SelenideElement MW_TaskStatusRejected = $(byXpath("//span[text()='Rejected']"));
    public static final SelenideElement MW_TaskStatusCancelled = $(byXpath("//span[text()='Cancelled']"));
    //MW Status
    public static final SelenideElement mwUpdateSatus_Select = $(byXpath(""));
    public static final SelenideElement mwUpdateSatus_Input = $(byXpath(""));
    public static final SelenideElement mwUpdateImportance_Select = $(byXpath(""));
    public static final SelenideElement mwUpdateImportance_Input = $(byXpath(""));
    public static final SelenideElement mwUpdateAssignor_Select = $(byXpath(""));
    public static final SelenideElement mwUpdateAssignor_Input = $(byXpath(""));
    public static final SelenideElement mwUpdateAssignee_Select = $(byXpath(""));
    public static final SelenideElement mwUpdateAssignee_Input = $(byXpath(""));
    //New Class
    public static final String mwClasses_Title = "Classes & Goods";
    public static final SelenideElement mwClasses_SelectClassType = $(byXpath(mw+"//label[contains(.,'Class Type')]/following-sibling::div//span[@class='ng-binding ng-scope']"));
    public static final SelenideElement mwClasses_FieldClassType = $(byXpath(mw+"//input[@type='search']"));
    public static final SelenideElement mwClasses_FieldClass = $(byXpath(mw+"//input[@name='class_no']"));
    public static final SelenideElement mwClasses_FieldDescription = $(byXpath(mw+"//textarea[@name='description']"));

    //MW Event
    public static final SelenideElement wmEvent_ = $(byXpath(""));
    public static final SelenideElement wmEvent_SelectType = $(byXpath("//div/div[2]/pkm-values-dropdown/div/div/span"));
    public static final SelenideElement wmEvent_SelectInput = $(byXpath("//pkm-values-dropdown/div/input"));
    public static final SelenideElement wmEvent_FieldInfo = $(byXpath("//textarea[@name='number']"));
    public static final SelenideElement wmEvent_SAVE = $(byXpath(""));

    //MW SHARE PROJECT and Change collaborators
    public static final SelenideElement MW_SHARE_PROJECT = $(byXpath(mw+""));
    public static final SelenideElement MW_SHARE_PROJECT_EMAIL = $(byXpath(mw+"//input[@name='email']"));
    public static final SelenideElement MW_SHARE_PROJECT_BTN_FIND = $(byXpath(mw+"//button[contains(text(),'Find')]"));
    public static String MW_SHARE_PROJECT_SELECT_TEAM = mw+"//strong[contains(.,'%s')]//preceding-sibling::input";
    public static final SelenideElement MW_SHARE_PROJECT_SELECT_ROLE = $(byXpath(mw+"//select"));
    public static final String ROLE_VIEWER = "Viewer";
    public static final String ROLE_COLLABORATOR = "Collaborator";
    public static final String ROLE_ADMIN = "Admin";


    public static final SelenideElement MW_COMMUNITY_Title = $(byXpath(mw+"//h2"));
    public static final SelenideElement MW_COMMUNITY_Text = $(byXpath(mw+"//p"));
    public static final SelenideElement MW_COMMUNITY_Link = $(byXpath(mw+"/div[@class='link']"));
    public static final SelenideElement MW_COMMUNITY_No = $(byXpath(mw+"//button[contains(text(),'No')]"));
    public static final SelenideElement MW_COMMUNITY_Yes = $(byXpath(mw+"//button[contains(text(),'Yes')]"));
    public static final SelenideElement MW_COMMUNITY_LinkTextNoSendEmail = $(byText("I already asked the expert not to proceed"));
    public static final SelenideElement MW_COMMUNITY_Text1 = $(byText("Please DO NOT proceed with this filing. Kindly confirm immediately."));
    public static final SelenideElement MW_COMMUNITY_Text2 = $(byText("Thank you for all the information. Please consider this as instructions to proceed with this case as discussed."));
    public static final SelenideElement MW_COMMUNITY_Text3 = $(byText("Thank you for all the information. Please consider this as instructions to proceed with this case as discussed."));
    public static final SelenideElement MW_COMMUNITY_Text4 = $(byText("Thank you for all the information. Please consider this as instructions to proceed with this case as discussed."));
    public static final SelenideElement MW_COMMUNITY_Text5 = $(byText("Thank you for all the information. Please consider this as instructions to proceed with this case as discussed."));

    public static final SelenideElement MW_BOOST_YOUR_PROFILE = $(byXpath(mw+""));
    public static final SelenideElement MW_BOOST_YOUR_PROFILE_BTN_START_NEW_CASE = $(byXpath(mw+"//button[contains(text(),'Start a new case')]"));
    public static final SelenideElement MW_BOOST_YOUR_PROFILE_BTN_REFER_ATTORNEY = $(byXpath(mw+"//button[contains(text(),'Refer an attorney now')]"));
    public static final SelenideElement MW_COMMUNITY_INVITE_ATTORNEY_BTN_INVITE = $(byXpath(mw+"//button[text()='invite']"));
    public static final SelenideElement MW_COMMUNITY_INVITE_ATTORNEY_BTN_CANCEL = $(byXpath(mw+"//button[text()='cancel']"));
    public static final SelenideElement MW_COMMUNITY_INVITE_FIELD_EMAIL = $(byXpath(mw+"//*[@id='attorneyEmail']"));
    public static final SelenideElement MW_COMMUNITY_INVITE_FIELD_MESSAGE = $(byXpath(mw+"//*[@id='inviteMessage']"));

    //Default standard invitation
    public static final SelenideElement MW_COMMUNITY_CONFIRM_TITLE = $(byText("Are you sure you want to leave the default invitation message unchaged?"));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_TEXT = $(byText("Notice that the default message doesn't have a name on the first line."));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_DISMISS = $(byXpath(mw+"//button[contains(text(),'No, let me change the message')]"));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_SUBMIT = $(byXpath(mw+"//button[contains(text(),\"I'm sure\")]"));

    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_TITLE = $(byText("Are you sure you want to leave the default invitation message unchaged?"));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_TEXT = $(byText("Notice that the default message doesn't have a name on the first line."));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_DISMISS = $(byXpath(mw+"//button[contains(text(),'No, let me change the message')]"));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_SUBMIT = $(byXpath(mw+"//button[contains(text(),'Yes, I'm sure')]"));

}
