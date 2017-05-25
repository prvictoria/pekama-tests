package Page;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekamaReports.elementInRowListReport;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PekamaReportsCharges extends PekamaReports {
    //CHARGES
    public final static String CHARGES_STATUS_NOT_BILLED = "not billed";
    public final static String CHARGES_STATUS_BILLED = "billed";
    public final static String CHARGES_STATUS_BILLED_AND_PAID = "billed and paid";
    public static final SelenideElement REPORTS_CHARGES_ROW(Integer rowCount) {
        final String FROM = "/a";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_DESCRIPTION(Integer rowCount) {
        final String FROM = "//h4";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_FROM(Integer rowCount) {
        final String FROM = "//ul/li[1]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_BY(Integer rowCount) {
        final String FROM = "//ul/li[1]/span";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TO(Integer rowCount) {
        final String FROM = "//ul/li[2]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_DATE(Integer rowCount) {
        final String FROM = "//*[@class='date ng-binding']";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TOTAL(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[1]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_TYPE(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[2]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_CHARGES_STATUS(Integer rowCount) {
        final String FROM = "//*[@class='footer']/div[3]";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
}

