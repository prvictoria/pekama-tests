package Page;

/**
 * Created by VatslauX on 29-Dec-16.
 */
public class ModalWindows {
    public static final String MW_ = "";
    public static final String MW = "//div[@class='modal-content']";
    public static final String MW_BTN_SUBMIT = MW+"//button[@submit]";
    public static final String MW_INPUT_NEW_MEMBER_EMAIL = MW+"//*[@id='newFollowerEmail']";


    public static final String MW_GENERIC_Body = "css=.modal-content";
    public static final String MW_GENERIC_Title = "//*[@class='modal-title']";
    public static final String MW_GENERIC_DatepickerField = "css=input.form-control.date-box";
    public static final String MW_GENERIC_DatepickerIcon = "css=.memobox-icon-calendar";
    public static final String MW_GENERIC_OkButton = "//*[@class='btn btn-primary ng-isolate-scope'][contains(.,'OK')]";
    public static final String MW_GENERIC_YesButton = "//*[@class='btn btn-primary'][contains .,'Yes' ]";
    public static final String MW_GENERIC_CrossButoon = "//*[@aria-label='Close']";
    public static final String MW_GENERIC_SaveButton = "//button[@submit][contains(.,'Save')]";
    public static final String MW_GENERIC_CancelButton = "//*[@class='btn btn-secondary'][contains .,'Cancel']";
    public static final String MW_GENERIC_CancelButton2 = "//button[@type='button']//*[@class='memobox-icon-cancel']";
    public static final String MW_GENERIC_CloseButton = "//*[@class='btn btn-primary'][contains .,'Close' ]";
    public static final String MW_GENERIC_ErrorMsg = "css=.help-block.error";
    public static final String MW_GENERIC_SpecificTeam = "//*[@pkm-team-colored-box='chosenOrganization']";
    public static final String MW_GENERIC_TeamsCheckbox = "//*[@pkm-team-colored-box='chosenOrganization']";
    public static final String MW_GENERIC_SelectHighlighted = "css=span.ui-select-highlight";
    public static final String BTN_WITH_TEXT = "//button[contains(.,'')]";
    public static final String genericButtonSave = "//button[contains(.,'Save')]";
    public static final String genericButtonOk = "//button[contains(.,'Ok')]";
    public static final String genericButtonYes = "//button[contains(.,'Yes')]";
    public static final String genericButtonAdd = "//button[contains(.,'add')]";
    public static final String genericButtonDelete = "//button[contains(.,'Delete')]";
    public static final String genericButtonCancel = "//button[contains(.,'Cancel')]";
    public static final String genericSelectHighlighted = "span.ui-select-highlight";
    public static final String MW_GenericBody = "//div[@class='modal-content']";
    public static final String MW_GenericButtonOk = "//button[text()='OK']";
    public static final String MW_GenericTitleSimple = "//h3[@class='modal-title']";


    public static final String MW_BuyProject = "sic!";
    public static final String MW_BuyProjectInputQTY = "//input[@type='number']";
    public static final String MW_BuyProjectTotalPrice = "//span[@class='your-price ng-binding']";
    public static final String MW_BuyProjectDiscount = "//span[@class='your-discount ng-binding']";
    public static final String MW_BuyProjectBuyProjects = "//div/button[contains(.,'Buy Projects')]";
    public static final String MW_checkoutBuy = "";
    public static final String MW_checkoutCardNumberField = "";
    public static final String MW_checkoutCardDate = "";
    public static final String MW_checkoutCardCVV = "";
    public static final String MW_checkoutSubmit = "";


    public static final String MW_EnableVerificationTitle = "//h3";
    public static final String MW_EnableVerificationClose = "//button[@type='button'][contains(.,'Close')]";
    public static final String MW_EnableVerificationNext = "//button[@type='submit'][contains(.,'Next')]";
    public static final String MW_EnableVerificationTelField = "name=phone";
    public static final String MW_EnableVerificationCountrySelect = "//div/div/div/div/div/span";
    public static final String MW_EnableVerificationCoutryField = "//div/div/div/div/input";
    public static final String MW_EnableVerificationConfirmCodeField = "name=two_factor_code";
    public static final String MW_EnableVerificationErrorArea = "//div[2]/form/div";

    public static final String MW_TaskTemplateSet = "";
    public static final String MW_TaskTemplateSet_FieldTitle = "//div[@class='modal-body']//input[@name]";
    public static final String MW_TaskTemplateSet_FieldDefining = "//div[@class='modal-body']/div[3]//input";
    public static final String MW_TaskTemplateSet_FieldType = "//div[@class='modal-body']/div[4]//input";
    public static final String MW_TaskTemplateSet_FieldEvent = "//div[@class='modal-body']/div[5]//input";
    public static final String MW_TaskTemplate_FieldTitle = "//div[@class='modal-body']//input[@name='title']";
    public static final String MW_TaskTemplate_Assignor = "//div[@placeholder='Choose assignor...']/span";
    public static final String MW_TaskTemplate_Assignee = "//div[@placeholder='Choose assignee...']/span";
    public static final String MW_TaskTemplate_Importance = "//div[@placeholder='Choose importance...']/span";
    public static final String MW_TaskTemplate_Status = "//div[@placeholder='Choose status...']/span";
    public static final String MW_TaskTemplate_DateOffset = "";
    public static final String MW_TaskTemplate_DateOffsetUnit = "";

    public static final String MW_EventTemplate_EventType = "//pkm-values-dropdown/div/div/span";
    public static final String MW_EventTemplate_AdditionalInfo = "//textarea";
    public static final String MW_EventTemplate_Input = "//div[@name='event_type']/input[1]";

    // public static final String COMMENT_006 = "MODAL WINDOWS";

    public static final String MW_ProjectTitle = "NEW_PROJECT";
    public static final String buttonNewProject = "//div[2]/div/button";
    public static final String field_ProjectType = "//fieldset/div/div/div/span";
    public static final String projectTypeGENERAL = "link=General";
    public static final String field_ProjectDefining = "//fieldset/div[2]/div/div/span";
    public static final String projectDefiningUSA = "//div[4]/a/span";
    public static final String field_ProjectTitle = "name=title";
    public static final String button_Finish = "//button[@type='submit']";

    public static final String modalEmailparametersFieldEmailAddress = "//div/div[2]/div/div/input";
    public static final String modalEmailparametersSubjectLine = "name=template";
    public static final String modalEmailparametersSubjectLineTemplate = "//div[2]/div/form/input";
    public static final String modalEmailparametersShow = "//ng-switch/span";

    public static final String modalTemplatesPicktemplate = "//div/div[2]/div/div/input";
    public static final String modalTemplatesSubmatter = "//div/div/div/span/span[2]/span";
    public static final String modalTemplatesSubMatterType = "//div[3]/div/div/div/span/span";
    public static final String modalTemplatesEventType = "//div[4]/div/div/div/span/span";
    public static final String modalTemplatesMsgTemplate = "//a/div/div/p";

    public static final String MW_Conversation = "//div[@class='modal-content']";
    public static final String MW_ConversationInputSubject = "//div[@class='modal-content']//input[@name='subject']";
    public static final String MW_ConversationInputFollower = "//div[@class='modal-content']//pkm-followers-picker//input";
    public static final String MW_ConversationInputZoneTeams = "//div[@class='modal-content']//pkm-organizations-picker//li/input";
    // public static final String MW_ConversationCancellButton = "";
// public static final String MW_ConversationCancellButton = "";
    public static final String MW_ConversationCreatelButton = "//button[text()='Create']";
    public static final String MW_ConversationCancellButton = "//button[text()='Cancel']";
    public static final String MW_ConversationFollowerField = "//li/input";
    public static final String MW_ConversationFollowerSelect = "css=span.result-name.ng-binding";

    public static final String modalCheckboxPermissionAllTeams = "//*[@ng-disabled='uiState.disableAllTeams']";

    public static final String modalProjectTemplateCancelButton = "//div[2]/button[2]";
    public static final String modalProjectTemplateOklButton = "//div[2]/button";
    public static final String MW_ProjectTemplateProjectTypeInput = "//div[@name='matter_type']/input[1]";
    public static final String MW_ProjectTemplateProjectDefiningInput = "//div[@name='defining']/input[1]";

    public static final String MW_ProjectFinishButton = "//button[@type='submit'][contains(.,'FINISH')]";
    public static final String MW_Project_SelectType = "//div[@name='matter_type']/div/span/span[1]";
    public static final String MW_Project_InputType = "//div[@name='matter_type']/input[1]";
    public static final String MW_Project_SelectDefining = "//div[@name='defining']/div/span/span[1]";
    public static final String MW_Project_InputDefining = "//div[@name='defining']/input[1]";
    public static final String MW_Project_TMNumber = "//input[@name='official_lookup']";
    public static final String MW_Project_Title = "//input[@name='title']";
    public static final String MW_Project_Reference = "//input[@name='number']";

    public static final String modalMembersOkButton = "//div[2]/button";

    public static final String MW_DeployDoc_Title = "css=h3.modal-title";
    public static final String MW_DeployDoc_FileName = "//input[@name='name']";
    public static final String MW_DeployDoc_01TemplateWord = "//li/label[text()='New Word document']";
    public static final String MW_DeployDoc_02TemplateExcel = "//li/label[text()='New Excel sheet']";
    public static final String MW_DeployDoc_03TemplateCustom = "//li[3]/label";
    public static final String MW_DeployDoc_FilterName = "";
    public static final String MW_DeployDoc_SelectDefining = "";
    public static final String MW_DeployDoc_SelectType = "";
    public static final String MW_DeployDoc_SelectEvent = "";
    public static final String MW_DeployDoc_ButtonCreate = "//button[contains(.,'Create')]";
    public static final String MW_DeployDoc_ButtonCancel = "//button[contains(.,'Cancel')]";

    public static final String MW_DeployTask_Title = "";
    public static final String MW_DeployTask_SelectEvent = "//div[@id='task-template-picker']/div/div/div/span/span";
    public static final String MW_DeployTask_InputEvent = "//input[@placeholder='Select an event...']";
    public static final String MW_DeployTask_01Template = "//div[@id='task-template-picker']/div[2]/div/div/label/span";
    public static final String MW_DeployTask_Apply = "//button[contains(.,'Apply')]";
    public static final String MW_DeployTask_Cancel = "";

    public static final String MW_TaskAssignorField = "";
    public static final String MW_TaskAssineeField = "";
    public static final String MW_TaskAssignorSelect = "css=span.ng-binding.ng-scope";
    public static final String MW_TaskAssineeSelect = "//div[2]/div/div/span/span[2]/span";
    public static final String MW_TaskImportanceField = "";
    public static final String MW_TaskImportanceSelect = "//pkm-values-dropdown/div/div/span/span[2]";
//    public static final String MW_TaskImportanceReminder = "";
//    public static final String MW_TaskImportanceDeadline = "";
//    public static final String MW_TaskImportanceFinalDeadline = "";
//    public static final String MW_TaskImportanceTask = "";
//    public static final String MW_TaskImportanceFatal = "";
//    public static final String MW_TaskStatusField = "";
//    public static final String MW_TaskStatusSelect = "";
//    public static final String MW_TaskStatusNew = "";
//    public static final String MW_TaskStatusInProgress = "";
//    public static final String MW_TaskStatusCompleted = "";
//    public static final String MW_TaskStatusApproved = "";
//    public static final String MW_TaskStatusRejected = "";
//    public static final String MW_TaskStatusCanceled = "";
//    public static final String MW_Task = "";

    public static final String MW_Financial = "";
    public static final String MW_FinancialClickFrom = "//span[2]/span";
    public static final String MW_FinancialInputFrom = "";
    public static final String MW_FinancialClickTo = "//div[2]/div/div/span/span";
    public static final String MW_FinancialInputTo = "";
    public static final String MW_FinancialClickBy = "//div[3]/div/div/span/span[2]/span";
    public static final String MW_FinancialInputBy = "";
    public static final String MW_FinancialClickStatus = "//div[2]/div/div/div/span/span[2]/span";
    public static final String MW_FinancialInputStatus = "";
    public static final String MW_FinancialClickType = "//pkm-values-dropdown/div/div/span/span";
    public static final String MW_FinancialInputType = "";
    public static final String MW_FinancialInputDueDate = "generic datepicker!";
    public static final String MW_FinancialInputItem = "//textarea";
    public static final String MW_FinancialClickCurrency = "//div[3]/div[2]/pkm-values-dropdown/div/div/span/span";
    public static final String MW_FinancialInputCurrency = "";
    public static final String MW_FinancialInputQty = "//input[@name='quantity']";
    public static final String MW_FinancialInputTimeHour = "//input[@name='hours']";
    public static final String MW_FinancialInputTimeMin = "//input[@name='minutes']";
    public static final String MW_FinancialInputTimeRate = "//input[@name='hourly_rate']";
    public static final String MW_FinancialInputPrice = "//input[@name='price']";
    public static final String MW_FinancialInputVat = "//input[@name='vat']";
    public static final String MW_FinancialInputDisc = "//input[@name='discount']";
    public static final String MW_FinancialInputTotal = "";

    public static final String MW_TimeTrackerSave = "";
    public static final String MW_TimeTrackerPause = "";
    public static final String MW_TimeTrackerDiscard = "//label[@class='danger']/input";

    public static final String MW_Contact = "";
    public static final String MW_Number = "";
    public static final String MW_Document = "";

    public static final String COMMENT_007 = "DATEPICKER";
    public static final String dueDateInputField = "css=input.form-control.date-box";
    public static final String datepickerToday = "css=.day.active.today";
    public static final String calendarBody = "css=table.table-condensed";
    public static final String calendarDay = "//*[@data-action='selectDay'][contains .,'' ]";
    public static final String calendarDay01 = "//*[@data-action='selectDay'][contains .,'1' ]";
    public static final String calendarDay02 = "//*[@data-action='selectDay'][contains .,'2' ]";
    public static final String calendarDay03 = "//*[@data-action='selectDay'][contains .,'3' ]";
    public static final String calendarDay04 = "//*[@data-action='selectDay'][contains .,'4' ]";
    public static final String calendarDay05 = "//*[@data-action='selectDay'][contains .,'5' ]";
    public static final String calendarDay06 = "//*[@data-action='selectDay'][contains .,'6' ]";
    public static final String calendarDay07 = "//*[@data-action='selectDay'][contains .,'7' ]";
    public static final String calendarDay08 = "//*[@data-action='selectDay'][contains .,'8' ]";
    public static final String calendarDay09 = "//*[@data-action='selectDay'][contains .,'9' ]";
    public static final String calendarDay10 = "//*[@data-action='selectDay'][contains .,'10' ]";
    public static final String MW_eventTypeApplicationFiled = "//*[@class='ng-binding ng-scope'][contains .,'Application filed']";
    public static final String MW_eventTypeApplicationPublished = "//*[@class='ng-binding ng-scope'][contains .,'Application published' ]";
    public static final String MW_eventTypeSubMatterCreated = "//*[@class='ng-binding ng-scope'][contains .,'Sub Matter Created' ]";

    public static final String MW_Contact_ = "";
    public static final String MW_Contact_SelectType = "//select[@name='type']";
    public static final String MW_Contact_FieldEntity = "//input[@name='legal_entity_name']";
    public static final String MW_Contact_Field = "";
    public static final String MW_Contact_SelectCountry = "//div[@name='country']/div/span";
    public static final String MW_Contact_SelectCountryName = "//div[@name='country']/div/span/span/span";
    public static final String MW_Contact_InputCountry = "//div[@name='country']/input";

    public static final String MW_MergeContact_ = "";
    public static final String MW_MergeContact_Select = "//div[@class='modal-body']//span[text()='Pick one...']";
    public static final String MW_MergeContact_Input = "//div[@class='modal-body']//input[@type='search']";

    public static final String MW_TaskImportanceDeadline = "//span[text()='Deadline']";
    public static final String MW_TaskImportanceFatal = "//span[text()='Fatal']";
    public static final String MW_TaskImportanceFinalDeadline = "//span[text()='Final Deadline']";
    public static final String MW_TaskImportanceReminder = "//span[text()='Reminder']";
    public static final String MW_TaskImportanceTask = "//span[text()='Task']";
    public static final String MW_TaskStatusNew = "//span[text()='New']";
    public static final String MW_TaskStatusInProgress = "//span[text()='In Progress']";
    public static final String MW_TaskStatusCompleted = "//span[text()='Completed']";
    public static final String MW_TaskStatusApproved = "//span[text()='Approved']";
    public static final String MW_TaskStatusRejected = "//span[text()='Rejected']";
    public static final String MW_TaskStatusCancelled = "//span[text()='Cancelled']";

    public static final String MW_MAILING_LIST = "";


    public static final String MW_COMMUNITY_body = "//div[@class='modal-content']";
    public static final String MW_COMMUNITY_Title = "//div[@class='modal-content']//h2";
    public static final String MW_COMMUNITY_Text = "//div[@class='modal-content']//p";
    public static final String MW_COMMUNITY_Link = "//div[@class='modal-community-footer ng-scope']/div[@class='link']";
    public static final String MW_COMMUNITY_No = "//div[@class='modal-content']//button[contains(text(),'No')]";
    public static final String MW_COMMUNITY_Yes = "//div[@class='modal-content']//button[contains(text(),'Yes')]";
    public static final String MW_COMMUNITY_LinkTextNoSendEmail = "I already asked the expert not to proceed";
    public static final String MW_COMMUNITY_Text1 = "Please DO NOT proceed with this filing. Kindly confirm immediately.";
    public static final String MW_COMMUNITY_Text2 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MW_COMMUNITY_Text3 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MW_COMMUNITY_Text4 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MW_COMMUNITY_Text5 = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";



}
