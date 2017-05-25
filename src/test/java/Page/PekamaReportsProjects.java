package Page;

import com.codeborne.selenide.SelenideElement;

import static Steps.StepsPekamaReports.elementInRowListReport;

/**
 * Created by VatslauX on 25-May-17.
 */
public class PekamaReportsProjects extends PekamaReports {

    public static final SelenideElement REPORTS_PROJECT_ (Integer rowCount) {
        return elementInRowListReport(rowCount, "");
    }
    public static final SelenideElement REPORTS_PROJECT_TITLE (Integer rowCount) {
        return elementInRowListReport(rowCount, "//h4");
    }
    public static final SelenideElement REPORTS_PROJECT_MATTER_TYPE (Integer rowCount) {
        return elementInRowListReport(rowCount, "//pkm-project-attributes/li[1]/*");
    }
    public static final SelenideElement REPORTS_PROJECT_DEFINING (Integer rowCount) {
        return elementInRowListReport(rowCount, "//pkm-project-attributes/li[2]");
    }
    public static final SelenideElement REPORTS_PROJECT_TYPE (Integer rowCount) {
        return elementInRowListReport(rowCount, "//pkm-project-attributes/li[3]");
    }
    public static final SelenideElement REPORTS_PROJECT_SUBTYPE (Integer rowCount) {
        return elementInRowListReport(rowCount, "//pkm-project-attributes/li[4]");
    }
    public static final SelenideElement REPORTS_PROJECT_STATUS (Integer rowCount) {
        return elementInRowListReport(rowCount, "//pkm-project-attributes/li[5]");
    }
}
