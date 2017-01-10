package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaReports {


    public static final String REPORTS_AllCheckbox = "//li[@class='items-header clearfix clearfix']//input[@type='checkbox']";
    public static final String REPORTS_BulkDelete = "//button[@class='btn-link'][contains(.,'Delete')]";
    public static final String REPORTS_BulkMerge = "//button[@class='btn-link'][contains(.,'Merge')]";
    public static final String REPORTS_NoData = "//div/div[2][@class='alert alert-empty ng-binding ng-scope']";
    public static final String REPORTS_Spinner = "//i[@class='pkm-icon-spinner icon-spin']";
    public static final String REPORTS_BtnList = "//button[contains(.,'List')]";
    public static final String REPORTS_BtnReport = "//button[contains(.,'Report')]";
    public static final String REPORTS_BtnNew = "//button[@type='button'][contains(.,'New')]";
    public static final String REPORTS_BtnImport = "//button[@type='button'][contains(.,'Import')]";
    public static final String REPORTS_BtnAddContact = "//button[@type='button'][contains(.,'Add contact')]";
//list view
//detailes view
    public static final String REPORTS_ListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
    public static final String REPORTS_ListRow02 = "//ng-include[@src='reportParams.listTemplate']/li[2]";
    public static final String REPORTS_ListRow03 = "//ng-include[@src='reportParams.listTemplate']/li[3]";
    public static final String REPORTS_ListRow04 = "//ng-include[@src='reportParams.listTemplate']/li[4]";
    public static final String REPORTS_ListRow05 = "//ng-include[@src='reportParams.listTemplate']/li[5]";

    public static final String REPORTS_1RowCheckbox = "";
    public static final String REPORTS_2RowCheckbox = "";
    public static final String REPORTS_3RowCheckbox = "";
    public static final String REPORTS_4RowCheckbox = "";
    public static final String REPORTS_5RowCheckbox = "";
    public static final String REPORTS_1Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li/a";
    public static final String REPORTS_2Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li[2]/a";
    public static final String REPORTS_3Row = "";
    public static final String REPORTS_Contacts = "";
    public static final String REPORTS_1RowNameSurname = "css=div.conact-page-name.ng-binding";
    public static final String REPORTS_1RowCountry = ".//*[@class='contact-page-country ng-binding'][contains(.,'Ireland')]";
    public static final String REPORTS_FiltersFreeText = "//pkm-filtering//input";

//search sidebar - all controls
    public static final String REPORTS_SEARCH_SIDEBAR = "//*[@class='search-sidebar']";
    public static final String REPORTS_MAILING_AREA = "//*[@class='saved']";
    public static final String REPORTS_MAILING_SAVE_SEARCH = "//*[@type='button' and contains(.,'Save search...')]";
    public static final String REPORTS_MAILING_LISTS = "//*[@class='search-list']";
    public static String REPORT_NAME;
    public static String REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = REPORTS_MAILING_LISTS+"//li[//a[contains(.,'"+REPORT_NAME+"')]]";
    public static String REPORTS_MAILING_LISTS_BTN_UPDATE = REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+"//button[contains(.,'Update')]";
    public static String REPORTS_MAILING_LISTS_BTN_CALL_ML = REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME+"//button[@uib-dropdown-toggle]";


    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN = "//*[@class='save-search-dropdown']";
    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT = REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//input";
    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE = REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[contains(.,'Save')]";
    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE_NEW = REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[contains(.,'Save as New')]";

//    public static final String REPORTS_MAILING_ = "";
//    public static final String REPORTS_MAILING_ = "";

    public static final String REPORTS_FiltersButtonClear = "//button[@ng-click='clearFilters()']";
    public static final String REPORTS_FiltersButtonSearch = "//button[@ng-click='applyFilters()']";
    public static final String REPORTS_FiltersButtonAdvanced = "";
    public static final String REPORTS_FiltersButtonSaveSearch = "";
    public static final String REPORTS_FiltersButtonSaveAs = "";
    public static final String REPORTS_FiltersButtonUpdate = "";
    public static final String REPORTS_FiltersButtonMailingList = "";
    public static final String REPORTS_FiltersButtonDelete = "";
    public static final String REPORTS_FiltersButton = "";

    public static final String REPORTS_ = "";
//Specific pages
public static final String REPORTS_ContactTailsPath = "";
    public static final String REPORTS_ContactRowName = "same";
    public static final String REPORTS_ContactRowSurname = "same";
    public static final String REPORTS_ContactName = "//*[@class='name ng-binding']";
    public static final String REPORTS_ContactEmail = "//span[@ng-if='contact.email']";
    public static final String REPORTS_ContactCountry = "//span[@ng-if='contact.country']";
    public static final String REPORTS_ContactLinkedCompany = "//span[@ng-if='contact.company']";
    public static final String REPORTS_ContactProjects = "//span[@ng-switch='contact.number_of_projects']";
    public static final String REPORTS_ContactCharges = "//span[@ng-switch='!contact.total_charges']";
    public static final String REPORTS_ContactCheckbox = "//input[@type='checkbox']";
    public static final String REPORTS_ContactNewProject = "//button[contains(.,'+ New Project')]";
    public static final String REPORTS_ContactEdit = "//div[@class='contact-page-invite-edit']/i[1]";
    public static final String REPORTS_ContactDelete = "//div[@class='contact-page-invite-edit']/i[2]";
}
