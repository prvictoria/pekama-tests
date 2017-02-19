package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
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
    //public static final SelenideElement MW_BTN_CANCEL = $(byXpath(mw+"//button[contains(.,'Cancel')]"));
    public static final SelenideElement MW_BTN_CANCEL = $(byXpath(mw+"//button[text()='Cancel']"));
    public static final SelenideElement MW_BTN_ADD = $(byXpath(mw+"//button[contains(.,'Add')]"));
    public static final SelenideElement MW_BTN_OK = $(byXpath(mw+"//button[contains(.,'OK')]"));
    public static final SelenideElement MW_BTN_YES = $(byXpath(mw+"//button[contains(.,'Yes')]"));
    public static final SelenideElement MW_ICON_CLOSE = $(byXpath(mw+"//*[@aria-label='Close']"));
    public static final SelenideElement MW_BTN_CLOSE = $(byXpath(mw+"//*[contains .,'Close' ]"));
    public static final SelenideElement MW_BTN_CREATE = $(byXpath(mw+"//button[text()='Create']"));

    public static final SelenideElement MW_CHECKBOX_ALL_TEAMS = $(byXpath(mw+"//*[@ng-disabled='uiState.disableAllTeams']"));

    public static final SelenideElement MW_TITLE = $(byXpath("//*[@class='modal-title']"));
    public static final SelenideElement MW_INPUT_DATE =  $(byXpath(mw+"//input[@class='form-control date-box']")); //click==today
    public static final SelenideElement MW_ICON_CALENDAR = $("css=.memobox-icon-calendar");

    public static final SelenideElement MW_ERROR = $(".help-block.error");
    public static final SelenideElement MW_SELECT_HIGHLIGHTED = $("span.ui-select-highlight");
    public static final SelenideElement BTN_WITH_TEXT = $(byXpath("//button[contains(.,'')]"));

    public static final SelenideElement genericButtonSave = $(byXpath("//button[contains(.,'Save')]"));
    public static final SelenideElement genericButtonOk = $(byXpath("//button[contains(.,'Ok')]"));
    public static final SelenideElement genericButtonYes = $(byXpath("//button[contains(.,'Yes')]"));
    public static final SelenideElement genericButtonDelete = $(byXpath("//button[contains(.,'Delete')]"));
    public static final SelenideElement genericButtonCancel = $(byXpath("//button[contains(.,'Cancel')]"));
    public static final SelenideElement CSS_SelectHighlighted = $("span.ui-select-highlight");

    public static final SelenideElement MW_GenericButtonOk = $(byXpath("//button[text()='OK']"));
    public static final SelenideElement MW_GenericTitleSimple = $(byXpath("//h3[@class='modal-title']"));

// buy projects
    public static final String MW_BUY_PROJECTS_TITLE = "Buy Projects";
    public static final SelenideElement MW_BUY_PROJECTS_InputQTY = $(byXpath("//input[@type='number']"));
    public static final SelenideElement MW_BUY_PROJECTS_TotalPrice = $(byXpath("//span[@class='your-price ng-binding']"));
    public static final SelenideElement MW_BUY_PROJECTS_Discount = $(byXpath("//span[@class='your-discount ng-binding']"));
    public static final SelenideElement MW_BUY_PROJECTS_BTN = $(byXpath("//div/button[contains(.,'Buy Projects')]"));
    public static final String MW_CHECKOUT_MODAL = "//main[@class='Modal']";
    public static final SelenideElement MW_CHECKOUT = $(byXpath("//*[@id='container']//main[@class='Modal']"));
    public static final SelenideElement MW_CHECKOUT_TITLE = $(byXpath(MW_CHECKOUT_MODAL+"//h1"));
    public static final SelenideElement MW_CHECKOUT_CardNumberField = $(byXpath(MW_CHECKOUT_MODAL+"//input[@placeholder='Card number']"));
    public static final SelenideElement MW_CHECKOUT_CardDate = $(byXpath(MW_CHECKOUT_MODAL+"//input[@placeholder='MM / YY']"));
    public static final SelenideElement MW_CHECKOUT_CardCVV = $(byXpath(MW_CHECKOUT_MODAL+"//input[@placeholder='CVC']"));
    public static final SelenideElement MW_CHECKOUT_REMEMBER = $(byXpath(MW_CHECKOUT_MODAL+""));
    public static final SelenideElement MW_CHECKOUT_Submit = $(byXpath(MW_CHECKOUT_MODAL+"//button[@type='submit']"));
    //MW Members
    public static final SelenideElement MW_MEMBERS_EMAIL = $(byXpath(mw+"//input[@name='email']"));
    // 2 step verifcation
    public static final SelenideElement MW_EnableVerificationTitle = $(byXpath("//h3"));
    public static final SelenideElement MW_EnableVerificationClose = $(byXpath("//button[@type='button'][contains(.,'Close')]"));
    public static final SelenideElement MW_EnableVerificationNext = $(byXpath("//button[@type='submit'][contains(.,'Next')]"));
    public static final SelenideElement MW_EnableVerificationTelField = $(byName("phone"));
    public static final SelenideElement MW_EnableVerificationCountrySelect = $(byXpath("//div/div/div/div/div/span"));
    public static final SelenideElement MW_EnableVerificationCoutryField = $(byXpath("//div/div/div/div/input"));
    public static final SelenideElement MW_EnableVerificationConfirmCodeField = $(byName("two_factor_code"));
    public static final SelenideElement MW_EnableVerificationErrorArea = $(byXpath("//div[2]/form/div"));

    //MW template set
    //templates Task Template Set
    public static final String MW_TASK_SET_TITLE = "Task Template Set";
    public static final String MW_MESSAGE_TEMPLATE_TITLE = "Message Template";
    public static final String MW_EVENT_SET_TITLE = "Event Template Set";
    public static final String MW_DOC_TEMPLATE_TITLE = "Documents Template";
    public static final SelenideElement MW_SET_NAME = $(byXpath("//div[@class='modal-body']//input[@name]"));
    public static final SelenideElement MW_SET_MULTICHOICE_DEFINING = $(byXpath("//div[@class='modal-body']/div[3]//input"));
    public static final SelenideElement MW_SET_MULTICHOICE_TYPE = $(byXpath("//div[@class='modal-body']/div[4]//input"));
    public static final SelenideElement MW_SET_MULTICHOICE_EVENT = $(byXpath("//div[@class='modal-body']/div[5]//input"));
    //MSG template
    public static final SelenideElement MW_SET_TEXT_EDITOR = $(byXpath(mw+"//*[starts-with(@id, 'taTextElement')]"));


    //templates Task Template
    public static final String MW_TASK_TEMPLATE_TITLE = "Task Template";
    public static final SelenideElement MW_TaskTemplate_FieldTitle = $(byXpath("//div[@class='modal-body']//input[@name='title']"));
    public static final SelenideElement MW_TaskTemplate_Assignor = $(byXpath("//div[@placeholder='Choose assignor...']/span"));
    public static final SelenideElement MW_TaskTemplate_Assignee = $(byXpath("//div[@placeholder='Choose assignee...']/span"));
    public static final SelenideElement MW_TaskTemplate_Importance = $(byXpath("//div[@placeholder='Choose importance...']/span"));
    public static final SelenideElement MW_TaskTemplate_INPUT_Importance = $(byXpath("//div[@placeholder='Choose importance...']/span"));
    public static final SelenideElement MW_TaskTemplate_Status = $(byXpath("//div[@placeholder='Choose status...']/span"));
    public static final SelenideElement MW_TaskTemplate_DateOffset = $(byXpath(mw+"//input[@name='date_offset']"));
    public static final SelenideElement MW_TaskTemplate_DateOffsetUnit = $(byXpath(mw+"//select[@name='date_unit']"));
    //mw event template
    public static final String MW_EVENT_TEMPLATE_TITLE = "Event Template";
    public static final SelenideElement MW_EVENT_SELECT_TYPE = $(byXpath(mw+"//pkm-values-dropdown/div/div/span"));
    public static final SelenideElement MW_EVENT_INPUT_TYPE =  $(byXpath(mw+"//div[@name='event_type']/input[1]"));
    public static final SelenideElement MW_EVENT_INPUT_INFO = $(byXpath(mw+"//textarea"));
    public static final SelenideElement MW_EVENT_INPUT_DATE = MW_INPUT_DATE;
    public static final SelenideElement MW_EVENT_Template_DateOffset = $(byXpath(mw+"//input[@name='date_offset']"));
    public static final SelenideElement MW_EVENT_Template_DateOffsetUnit = $(byXpath(mw+"//select[@name='date_unit']"));

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
    public static final SelenideElement MW_CONVERSATION_INPUT_Subject = $(byXpath(mw+"//input[@name='subject']"));
    public static final SelenideElement MW_CONVERSATION_INPUT_Follower = $(byXpath(mw+"//pkm-followers-picker//input"));
    public static final SelenideElement MW_CONVERSATION_INPUT_TEAMS = $(byXpath(mw+"//pkm-organizations-picker//li/input"));
    public static final SelenideElement MW_CONVERSATION_BTN_INVITE = $(byXpath(mw+"//button[contains(., 'invite to Pekama')]"));
    public static final SelenideElement MW_CONVERSATION_BTN_ADD_GUEST = $(byXpath(mw+"//button[contains(., 'add as guest')]"));


    public static final SelenideElement MW_CONVERSATION_FollowerField = $(byXpath(mw+"//li/input"));
    public static final SelenideElement MW_CONVERSATION_FollowerSelect =  $(byXpath(mw+""));

    //Team settings
    //Project templates
    public static final String MW_PROJECT_TEMPLATE_TITLE = "Project Template";
    public static final SelenideElement MW_ProjectTemplateProjectTypeInput = $(byXpath(mw+"//div[@name='matter_type']/input[1]"));
    public static final SelenideElement MW_ProjectTemplateProjectDefiningInput = $(byXpath(mw+"//div[@name='defining']/input[1]"));
    //MW Status
    public static final String MW_STATUS_TITLE = "Status";
    public static final String PROJECT_STATUS_STATE_ACTIVE = "Active";
    public static final String PROJECT_STATUS_STATE_CLOSED = "Closed";
    public static final String MW_STATUS_UNDEFINED = "? undefined:undefined ?";
    public static final SelenideElement MW_STATUS_VALUE = $(byXpath(mw+"//input[@name='value']"));
    public static final SelenideElement MW_STATUS_SELECT_STATE = $(byXpath(mw+"//select"));
    //MW type
    public static final String MW_TYPE_TITLE = "Type";
    public static final String MW_SUBTYPE_TITLE = "Sub Type";
    public static final String MW_EVENT_TYPE_TITLE = "Event Type";
    public static final String MW_REFERENCE_TYPE_TITLE = "Reference Type";
    public static final SelenideElement MW_STATUS_RELEVANT_ALL = $(byXpath(mw+"//label[contains(., 'Relevant to all')]/input"));
    public static final SelenideElement MW_STATUS_INPUT_RELEVANT = $(byXpath(mw+"//input[@role='combobox']"));
    //Mw defining country
    public static final String MW_COUNTRY_TITLE = "Country";
    public static final SelenideElement MW_STATUS_INPUT_COUNTRY = $(byXpath(mw+"//input[@name='value']"));
    public static final SelenideElement MW_STATUS_INPUT_CODE = $(byXpath(mw+"//input[@name='code']"));
    public static final SelenideElement MW_STATUS_INPUT_NUMBER = $(byXpath(mw+"//input[@name='number']"));
    public static final SelenideElement MW_STATUS_IS_GROUP = $(byXpath(mw+"//input[@type='checkbox']"));

    //NEW
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
    public static final SelenideElement MW_TaskStatusField = $(byXpath(""));
    public static final SelenideElement MW_TaskStatusSelect = $(byXpath(""));
    public static final SelenideElement MW_TaskStatusCanceled = $(byXpath(""));
    public static final SelenideElement MW_Task = $(byXpath(""));
    //MW CHARGES
    public static final SelenideElement MW_CHARGES_ = $(byXpath(mw+""));
    public static final SelenideElement MW_CHARGES_SELECT_FROM = $(byXpath(mw+"//label[contains(.,'From:')]/following-sibling::div//span[@class='ng-binding ng-scope']"));
    public static final SelenideElement MW_CHARGES_INPUT_FROM = $(byXpath(mw+"//label[contains(.,'From:')]/following-sibling::div//input[@type='search']"));
    public static final SelenideElement MW_CHARGES_SELECT_TO = $(byXpath(mw+"//label[contains(.,'To:')]/following-sibling::div//span[@class='ng-binding ng-scope']"));
    public static final SelenideElement MW_CHARGES_INPUT_TO = $(byXpath(mw+""));
    public static final SelenideElement MW_CHARGES_SELECT_BY = $(byXpath(mw+""));
    public static final SelenideElement MW_CHARGES_INPUT_BY = $(byXpath(mw+""));
    public static final SelenideElement MW_CHARGES_SELECT_STATUS = $(byXpath(mw+"//div[2]/div/div/div/span/span[2]/span"));
    public static final SelenideElement MW_CHARGES_INPUT_STATUS = $(byXpath(mw+""));
    public static final SelenideElement MW_CHARGES_SELECT_TYPE = $(byXpath(mw+"//label[contains(.,'Type:')]/following-sibling::pkm-values-dropdown"));
    public static final SelenideElement MW_CHARGES_INPUT_TYPE = $(byXpath(mw+"//label[contains(.,'Type:')]/following-sibling::pkm-values-dropdown//input[@type='search']"));
    public static final String CHARGES_TYPE_EXPENSES = "Direct expenses";
    public static final String CHARGES_TYPE_ASSOCIATE = "Foreign associate charges";
    public static final String CHARGES_TYPE_FEES = "Government Fees";
    public static final String CHARGES_TYPE_SERVICE = "Service Charges";
    public static final String CHARGES_TYPE_TIME = "Time Recorded";


    public static final SelenideElement MW_CHARGES_INPUT_DATE = MW_INPUT_DATE;
    public static final SelenideElement MW_CHARGES_INPUT_ITEM = $(byXpath(mw+"//textarea"));
    public static final SelenideElement MW_CHARGES_SELECT_CURRENCY = $(byXpath(mw+"//label[contains(.,'Currency:')]/following-sibling::pkm-values-dropdown"));
    public static final SelenideElement MW_CHARGES_INPUT_CURRENCY = $(byXpath(mw+"//label[contains(.,'Currency:')]/following-sibling::pkm-values-dropdown//input[@type='search']"));
    public static final SelenideElement MW_CHARGES_INPUT_QTY = $(byXpath(mw+"//input[@name='quantity']"));
    public static final SelenideElement MW_CHARGES_INPUT_HOUR = $(byXpath(mw+"//input[@name='hours']"));
    public static final SelenideElement MW_CHARGES_INPUT_MIN = $(byXpath(mw+"//input[@name='minutes']"));
    public static final SelenideElement MW_CHARGES_INPUT_RATE = $(byXpath(mw+"//input[@name='hourly_rate']"));
    public static final SelenideElement MW_CHARGES_INPUT_PRICE = $(byXpath(mw+"//input[@name='price']"));
    public static final SelenideElement MW_CHARGES_INPUT_VAT = $(byXpath(mw+"//input[@name='vat']"));
    public static final SelenideElement MW_CHARGES_INPUT_DISCOUNT = $(byXpath(mw+"//input[@name='discount']"));
    public static final SelenideElement MW_CHARGES_TOTAL = $(byXpath(mw+"//label[contains(.,'Total:')]/following-sibling::input"));
    //MW TIEM TRACKER
    public static final SelenideElement MW_TimeTrackerSave = $(byXpath(mw+""));
    public static final SelenideElement MW_TimeTrackerPause = $(byXpath(mw+""));
    public static final SelenideElement MW_TimeTrackerDiscard = $(byXpath(mw+"//label[@class='danger']/input"));




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

    public static final SelenideElement mwUpdateStatus_Select = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateStatus_Input = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateImportance_Select = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateImportance_Input = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateAssignor_Select = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateAssignor_Input = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateAssignee_Select = $(byXpath(mw+""));
    public static final SelenideElement mwUpdateAssignee_Input = $(byXpath(mw+""));
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

    //Community
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

    //Default standard confirmation
    public static final SelenideElement MW_COMMUNITY_BTN_YES = $(byXpath(mw+"//button[text()='Yes']"));
    public static final SelenideElement MW_COMMUNITY_BTN_NO = $(byXpath(mw+"//button[text()='No']"));
    //MW return to 1st wizard step
    public static final SelenideElement MW_COMMUNITY_RETURN_TO_WIZARD_TITLE = $(byText("Are you sure you want to initiate a new supplier request?"));
    public static final SelenideElement MW_COMMUNITY_RETURN_TO_WIZARD_TEXT = $(byText("You can always access the draft of the current request from the \"Outgoing Cases\" tab."));
    
    //MW cancel case
    public static final SelenideElement MW_CANCEL_CASE_TITLE = $(byText("Are you sure you want to cancel this case?"));
    public static final SelenideElement MW_CANCEL_CASE_TEXT = $(byText("We will tell all the experts you chose that this case was cancelled.")); //Have team name
    public static final SelenideElement MW_CANCEL_LINK_SUBMIT_WITHOUT_MSG = $(byXpath("//a[contains(@href, '')]"));
    public static final String MW_CANCEL_LINK_TEXT = " I already told the experts not to proceed\n";
    //MW withdraw case
    public static final SelenideElement MW_WITHDRAW_CASE_TITLE = $(byText("Are you sure you want to withdraw instructions for this case?"));
    public static final SelenideElement MW_WITHDRAW_CASE_TEXT = $(byText("We will immediately send a message")); //Have team name
    public static final SelenideElement MW_WITHDRAW_LINK_SUBMIT_WITHOUT_MSG = $(byXpath("//a[contains(@href, '')]"));
    public static final String MW_WITHDRAW_LINK_TEXT = "I already asked the expert not to proceed";
    //MW confirm instruction
    public static final SelenideElement MW_CONFIRM_INSTRUCTIONS_TITLE = $(byText("Are you sure you want to confirm the receipt of instructions for this case?"));
    public static final SelenideElement MW_CONFIRM_INSTRUCTIONS_TEXT = $(byText("We will immediately send a message telling Iosif Franz that the instructions are received and you are able to execute them on time.")); //Have team name
    public static final SelenideElement MW_CONFIRM_INSTRUCTIONS_LINK_SUBMIT_WITHOUT_MSG = $(byXpath("//a[contains(@href, '')]"));
    public static final String MW_CONFIRM_INSTRUCTIONS_LINK_TEXT = "I already confirmed instructions and timely execution";
    //MW confirm completion
    public static final SelenideElement MW_CONFIRM_COMPLETION_TITLE = $(byText("Are you sure you want to confirm that you completed the instructions for this case?"));
    public static final SelenideElement MW_CONFIRM_COMPLETION_TEXT = $(byText("We will immediately send a message telling Iosif Franz that the instructions are fully executed and the work has been completed.")); //Have team name
    public static final SelenideElement MW_CONFIRM_COMPLETION_LINK_SUBMIT_WITHOUT_MSG = $(byXpath("//a[contains(@href, '')]"));
    public static final String MW_CONFIRM_COMPLETION_LINK_TEXT = "I already confirmed completion";

    //MW Boost with standard text
    public static final SelenideElement MW_COMMUNITY_CONFIRM_TITLE = $(byText("Are you sure you want to leave the default invitation message unchaged?"));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_TEXT = $(byText("Notice that the default message doesn't have a name on the first line."));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_DISMISS = $(byXpath(mw+"//button[contains(text(),'No, let me change the message')]"));
    public static final SelenideElement MW_COMMUNITY_CONFIRM_SUBMIT = $(byXpath(mw+"//button[contains(text(),\"I'm sure\")]"));

    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_TITLE = $(byText("Are you sure you want to leave the default invitation message unchaged?"));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_TEXT = $(byText("Notice that the default message doesn't have a name on the first line."));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_DISMISS = $(byXpath(mw+"//button[contains(text(),'No, let me change the message')]"));
    public static final SelenideElement MW_CONFIRM_INVITE_ATTOTNEY_SUBMIT = $(byXpath(mw+"//button[contains(text(),'Yes, I'm sure')]"));
    //MW Congratulations!
    public static final SelenideElement MW_CONGRATULATION_OK = $(byXpath(mw+"//button"));
    public static final SelenideElement MW_BTN_NEXT = $(byXpath(mw+"//button[contains(text(),'next')]"));
}
