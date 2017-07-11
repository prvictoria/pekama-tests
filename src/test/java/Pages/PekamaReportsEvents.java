package Pages;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekamaReports.elementInRowListReport;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PekamaReportsEvents extends PekamaReports {
    public static final SelenideElement REPORTS_EVENT_ROW(Integer rowCount) {
        final String PATH = "/a";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_DATE(Integer rowCount) {
        final String PATH = "//p";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_DESCRIPTION(Integer rowCount) {
        final String PATH = "//*[@class='descr-header ng-binding']";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_TEAM_CREATOR(Integer rowCount) {
        final String PATH = "//*[@class='descr-header ng-binding']/span";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_TYPE(Integer rowCount) {
        final String PATH = "//h4";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }
    public static final SelenideElement REPORTS_EVENT_PRJ_LOGO(Integer rowCount) {
        final String PATH = "//img";
        SelenideElement element = elementInRowListReport(rowCount, PATH);
        return element;
    }

}
