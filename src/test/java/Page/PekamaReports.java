package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import Steps.Page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PekamaReports extends Page {

    public static final SelenideElement REPORTS_PAGE_TITLE_PANEL = $(byXpath("//div[@class='content-col']//div[@class='panel-heading']//h4"));
    public static final SelenideElement REPORTS_ALL_CHECKBOX = $(byXpath("//li[@class='items-header clearfix clearfix']//input[@type='checkbox']/following-sibling::i"));
    public static final SelenideElement REPORTS_DELETE = $(byXpath("//button[@class='btn-link'][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_DELETE_EVENTS = $(byXpath("//li/a[@href][contains(.,'Delete')]"));
    public static final SelenideElement REPORTS_MERGE = $(byXpath("//button[@class='btn-link'][contains(.,'Merge')]"));
    public static final SelenideElement REPORTS_PLACEHOLDER_NO_DATA = $(byXpath("//ul[@class='items']//*[@class='alert alert-empty ng-binding ng-scope']"));
    public static final SelenideElement REPORTS_SPINNER = $(byXpath("//i[@class='pkm-icon-spinner icon-spin']"));
    public static final SelenideElement REPORTS_BTN_LIST = $(byXpath("//button[contains(.,'List')]"));
    public static final SelenideElement REPORTS_BTN_REPORT = $(byXpath("//button[contains(.,'Report')]"));
    public static final SelenideElement REPORTS_BTN_NEW_PROJECT = $(byXpath("//button[@type='button'][contains(.,'New')]"));
    public static final SelenideElement REPORTS_BTN_NEW_PROJECT_TEMPLATE = $(byXpath("//button[@type='button'][contains(.,'New')]/following-sibling::button"));
    public static final ElementsCollection REPORTS_PROJECT_TEMPLATES_LIST = $$(byXpath("//button[@type='button'][contains(.,'New')]/following-sibling::ul//a"));
    public static final SelenideElement REPORTS_BTN_IMPORT = $(byXpath("//button[@type='button'][contains(.,'Import')]"));
    public static final SelenideElement REPORTS_BTN_ADD_CONTACT = $(byXpath("//button[@type='button'][contains(.,'Add contact')]"));

    //ROWS
    public static String REPORTS_ROW_BY_INDEX_LIST(Integer rowCount) {
        String count = Integer.toString(rowCount);
        String row = String.format("//ng-include[@src='reportParams.listTemplate']/li[%s]", count);
        return row;
    }
    public static final ElementsCollection REPORTS_LIST_ROWS = $$(byXpath("//ng-include[@src='reportParams.listTemplate']/li"));

    public static final String REPORTS_ListRow01 = "//ng-include[@src='reportParams.listTemplate']/li[1]";
    public static SelenideElement REPORTS_LIST_PROJECT_TILE_ROW1 = $(byXpath(REPORTS_ListRow01+"//h4"));
    public static SelenideElement REPORTS_LIST_PROJECT_SELECT_ROW1 = $(byXpath(REPORTS_ListRow01+"//input[@type='checkbox']/following-sibling::i"));

    //list view
    public static final SelenideElement REPORTS_SORT_ORDER_TYPE = $(byXpath("//a[@class='sortable ng-binding dropdown-toggle']"));

    //SORTING
    public static final SelenideElement REPORTS_SELECT_SORT_ORDER(String order){
        SelenideElement select = $(byXpath("//a[@class='sortable ng-binding dropdown-toggle']/following-sibling::ul//a[text()='"+order+"']"));
        return select;
    }
    public static final SelenideElement REPORTS_SORT_ORDER_DESCENDING = $(byXpath("//div[@class='dropdown sort desc']"));
    public static final SelenideElement REPORTS_SORT_ORDER_ASCENDING = $(byXpath("//div[@class='dropdown sort']"));

    public static final SelenideElement REPORTS_SORT_BY_NONE = $(byLinkText("None"));
    public static final SelenideElement REPORTS_SORT_BY_NAME = $(byLinkText("Name"));
    public static final SelenideElement REPORTS_SORT_BY_PROJECT_TYPE = $(byLinkText("Project Type"));
    public static final SelenideElement REPORTS_SORT_BY_COUNTRY = $(byLinkText("Country"));
    public static final SelenideElement REPORTS_SORT_BY_STATUS = $(byLinkText("Status"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_CREATED = $(byLinkText("Last created"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_MODIFIED = $(byLinkText("Last modified"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_TYPE = $(byLinkText("Type"));
    public static final SelenideElement REPORTS_SORT_BY_LAST_SUB_TYPE = $(byLinkText("Sub Type"));


    //report view






    //MAILING LIST
    public static final SelenideElement REPORTS_MAILING_AREA = $(byXpath("//*[@class='saved']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH = $(byXpath("//*[@type='button' and contains(.,'Save search...')]"));
    public static final String REPORTS_MAILING_LISTS = "//*[@class='search-list']";
    public static SelenideElement reportName;
    public static SelenideElement REPORTS_MAILING_LISTS_ROW_WITH_ML_NAME = $(byXpath("//li[//a[contains(.,'"+ reportName +"')]]")); //ML+
    public static SelenideElement REPORTS_MAILING_LISTS_BTN_UPDATE = $(byXpath("//button[contains(.,'Update')]"));
    public static String REPORTS_MAILING_LISTS_BTN_CALL_ML = "//button[@uib-dropdown-toggle]";
    public static SelenideElement REPORTS_MAILING_LISTS_CALL_MW = $(byLinkText("Mailing List"));
    public static SelenideElement REPORTS_MAILING_LISTS_DELETE_MW = $(byXpath("//li[./a[text()='Mailing List']]//following-sibling::li/a[text()='Delete']"));

    public static final String REPORTS_MAILING_SAVE_SEARCH_DROPDOWN = "//*[@class='save-search-dropdown']";
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_INPUT = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//input"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save']"));
    public static final SelenideElement REPORTS_MAILING_SAVE_SEARCH_DROPDOWN_SAVE_NEW = $(byXpath(REPORTS_MAILING_SAVE_SEARCH_DROPDOWN+"//button[text()='Save as New']"));

    //SIDEBAR FILTERS
    public static final SelenideElement REPORTS_FiltersButtonClear = $(byXpath("//button[@ng-click='clearFilters()']"));
    public static final SelenideElement REPORTS_FiltersButtonSearch = $(byXpath("//button[@ng-click='applyFilters()']"));
    public static final SelenideElement REPORTS_FiltersButtonAdvanced = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonSaveSearch = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonSaveAs = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonUpdate = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonMailingList = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButtonDelete = $(byXpath(""));
    public static final SelenideElement REPORTS_FiltersButton = $(byXpath(""));

    public static final SelenideElement REPORTS_ = $(byXpath(""));


    //search sidebar - all controls

    public static final SelenideElement REPORTS_FiltersFreeText = $(byXpath("//pkm-filtering//input"));
    public static final SelenideElement REPORTS_SEARCH_SIDEBAR = $(byXpath("//*[@class='search-sidebar']"));





}
