package Page;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekamaReports.elementInRowListReport;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PekamaReportsEvents extends PekamaReports {
    public static final SelenideElement REPORTS_EVENT_ROW(Integer rowCount) {
        final String FROM = "/a";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_DATE(Integer rowCount) {
        final String FROM = "//p";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_DESCRIPTION(Integer rowCount) {
        final String FROM = "//*[@class='descr-header ng-binding']";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_TYPE(Integer rowCount) {
        final String FROM = "//h4";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_PRJ_LOGO(Integer rowCount) {
        final String FROM = "//img";
        SelenideElement element = elementInRowListReport(rowCount, FROM);
        return element;
    }

}
