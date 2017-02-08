package Page;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityOutgoing extends Page {
    public static String caseRow;
    public static String caseRowNameFirst = "//div[@project='project'][position()=1]//h3[contains(.,'%s')]";

    public static String caseRowFirst = "//div[@project='project' and position()=1 and .//ancestor::h3[text()='%s']]";
    public static String caseRowByCount = "//div[@project='project' and position()=%s and .//ancestor::h3[text()='%s']]";

//    public static String caseRowByName = "//div[starts-with(@class, 'case-item') and .//ancestor::h3[text()='Patent in Åland Islands']]";
    public static String caseRowByName = "//div[@project='project' and .//ancestor::h3[text()='%s']]";

    public static String ROW_CONTROL_CASE_NAME = caseRow+"//*[@class='name']//h3";
    public static String ROW_CONTROL_FIELD_CASE_NAME = caseRow+"//input[@name='title']";
    public static String ROW_CONTROL_LABEL_CASE = caseRow+"//*[@class='type']/span";
    public static String ROW_CONTROL_BTN_ACTION = caseRow+"//*[@class='status']/button";
    public static String ROW_CONTROL_LABEL_STATUS = caseRow+"//*[@class='status']/div/span";
    public static String ROW_CONTROL_BTN_ARCHIVE = caseRow+"//button[./i]";
}
