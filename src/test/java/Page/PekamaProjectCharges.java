package Page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by VatslauX on 20-May-17.
 */
public class PekamaProjectCharges extends PekamaProject{
    //TAB CHARGES
    public static SelenideElement TAB_CHARGES_ACTUAL_ORDER = $(byXpath(CONTROLS_ROW+"//a[contains(.,'"+TASKS_ORDER+"')]"));        //order value

    public static final SelenideElement TAB_CHARGES = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_CollapseForm = $(byXpath("//div/ng-include/div/a[@class='link-task']"));
    public static final SelenideElement TAB_CHARGES_BackToListLink = $(byLinkText("link=Back to all finances"));
    public static final SelenideElement TAB_CHARGES_BTN_DELETE = TAB_CONTROL_DELETE;
    public static final SelenideElement TAB_CHARGES_XERO = $(byXpath("//button[@ng-click='bulkToXero()']"));

    public static final ElementsCollection TAB_CHARGES_LIST = $$(byXpath("//div[@class='items-list with-caret ng-scope finances']/div"));
    public static String TAB_CHARGES_ROW_BY_INDEX(Integer rowCount) {
        String count = Integer.toString(rowCount);
        String row = String.format("//div[@class='items-list with-caret ng-scope finances']/div[%s]", count);
        return row;
    }
    public static final String TAB_CHARGES_ROW_SELECT = "//input";
    public static final String TAB_CHARGES_ROW_TEAM = "//*[@object='object']//span";
    public static final String TAB_CHARGES_ROW_TEAMS = "//*[@object='object']//li[%s]/a";
    public static final String TAB_CHARGES_ROW_FROM_TO = "//*[@class='link-task']//span[1]";
    public static final String TAB_CHARGES_ROW_TYPE = "//*[@class='link-task']//span[2]";
    public static final String TAB_CHARGES_ROW_PRICE = "//*[@class='link-task']//span[3]";
    public static final String TAB_CHARGES_ROW_DATE = "//*[@class='link-task']//span[4]";


    public static final SelenideElement TAB_CHARGES_FORM_SELECT_FROM = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_FROM = $(byXpath(""));    public static final SelenideElement TAB_CHARGES_FORM_SELECT_TO = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_TO = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_SELECT_BY = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_BY = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_SELECT_STATUS = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_STATUS = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_SELECT_TYPE = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_TYPE = $(byXpath(""));

    public static final SelenideElement TAB_CHARGES_FORM_DATE = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_ITEM = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_SELECT_CURRENCY = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_INPUT_CURRENCY = $(byXpath(""));


    public static final SelenideElement TAB_CHARGES_FORM_QTY = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_TIME_HOUR = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_TIME_MIN = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_RATE = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_PRICE = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_VAT = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_DISC = $(byXpath(""));
    public static final SelenideElement TAB_CHARGES_FORM_TOTAL = $(byXpath(""));
}
