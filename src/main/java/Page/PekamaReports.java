package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class PekamaReports {
    //public static final String COMMENT_008 = "REPORTS";

    public static final String reportsAllCheckbox = "//li[@class='items-header clearfix clearfix']//input[@type='checkbox']";
    public static final String reportsBulkDelete = "//button[@class='btn-link'][contains(.,'Delete')]";
    public static final String reportsBulkMerge = "//button[@class='btn-link'][contains(.,'Merge')]";
    public static final String reportsNoData = "//div/div[2][@class='alert alert-empty ng-binding ng-scope']";
    public static final String reportsSpinner = "//i[@class='pkm-icon-spinner icon-spin']";
    public static final String reportsBtnList = "//button[contains(.,'List')]";
    public static final String reportsBtnReport = "//button[contains(.,'Report')]";
    public static final String reportsBtnNew = "//button[@type='button'][contains(.,'New')]";
    public static final String reportsBtnImport = "//button[@type='button'][contains(.,'Import')]";
    public static final String reportsBtnAddContact = "//button[@type='button'][contains(.,'Add contact')]";
    public static final String reportsListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
    public static final String reportsListRow02 = "//ng-include[@src='reportParams.listTemplate']/li[2]";
    public static final String reportsListRow03 = "//ng-include[@src='reportParams.listTemplate']/li[3]";
    public static final String reportsListRow04 = "//ng-include[@src='reportParams.listTemplate']/li[4]";
    public static final String reportsListRow05 = "//ng-include[@src='reportParams.listTemplate']/li[5]";

    public static final String reports1RowCheckbox = "";
    public static final String reports2RowCheckbox = "";
    public static final String reports3RowCheckbox = "";
    public static final String reports4RowCheckbox = "";
    public static final String reports5RowCheckbox = "";
    public static final String reports1Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li/a";
    public static final String reports2Row = "//section[@id='page']/div[2]/ui-view/div/div[2]/div/div/ul/li[2]/ul/ng-include/li[2]/a";
    public static final String reports3Row = "";
    public static final String reportsContacts = "";
    public static final String reports1RowNameSurname = "css=div.conact-page-name.ng-binding";
    public static final String reports1RowCountry = ".//*[@class='contact-page-country ng-binding'][contains(.,'Ireland')]";
    public static final String reportsFiltersFreeText = "//pkm-filtering//input";

    public static final String reportsContactTailsPath = "";
    public static final String reportsContactRowName = "same";
    public static final String reportsContactRowSurname = "same";
    public static final String reportsContactName = "//*[@class='name ng-binding']";
    public static final String reportsContactEmail = "//span[@ng-if='contact.email']";
    public static final String reportsContactCountry = "//span[@ng-if='contact.country']";
    public static final String reportsContactLinkedCompany = "//span[@ng-if='contact.company']";
    public static final String reportsContactProjects = "//span[@ng-switch='contact.number_of_projects']";
    public static final String reportsContactCharges = "//span[@ng-switch='!contact.total_charges']";

    public static final String reportsContactCheckbox = "//input[@type='checkbox']";
    public static final String reportsContactNewProject = "//button[contains(.,'+ New Project')]";
    public static final String reportsContactEdit = "//div[@class='contact-page-invite-edit']/i[1]";
    public static final String reportsContactDelete = "//div[@class='contact-page-invite-edit']/i[2]";


    public static final String reportsFilters = "";
    public static final String reportsFiltersButtonClear = "//button[@ng-click='clearFilters()']";
    public static final String reportsFiltersButtonSearch = "//button[@ng-click='applyFilters()']";
    public static final String reportsFiltersButtonAdvanced = "";
    public static final String reportsFiltersButtonSaveSearch = "";
    public static final String reportsFiltersButtonSaveAs = "";
    public static final String reportsFiltersButtonUpdate = "";
    public static final String reportsFiltersButtonMailingList = "";
    public static final String reportsFiltersButtonDelete = "";
    public static final String reportsFiltersButton = "";
// public static final String reportsFiltersButton = "";
// public static final String reportsFiltersButton = "";

    public static final String mwMailingList_ = "";

    public static final String reports = "";
}
