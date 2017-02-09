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

    public static String caseRowFirst = "//div[@project='project' and position()=1 and .//ancestor::h3[text()='%s']]/div";
    public static String caseRowByCount = "//div[@project='project' and position()=%s and .//ancestor::h3[text()='%s']]/div";

    //    public static String caseRowByName = "//div[starts-with(@class, 'case-item') and .//ancestor::h3[text()='Patent in Åland Islands']]";
    public static String caseRowByName = "//div[@project='project' and .//ancestor::h3[text()='%s']]/div";
    public static final String ROW_CONTROL_CASE_ROW_FIRST = "//div[@project='project'][position()=1]";
    public static final String ROW_CONTROL_CASE_NAME = "//*[@class='name']//h3";
    public static final String ROW_CONTROL_FIELD_CASE_NAME = "//input[@name='title']";
    public static final String ROW_CONTROL_BTN_SAVE_NAME = "//button[contains(.,'Save')]";
    public static final String ROW_CONTROL_LINK_PROJECT = "//a[@href]";

    public static final String ROW_CONTROL_LABEL_CASE = "//*[@class='type']/span";
    public static final String ROW_CONTROL_BTN_ACTION = "//*[starts-with(@class, 'status')]/button";
    public static final String ROW_CONTROL_LABEL_STATUS = "//*[starts-with(@class, 'status')]/div/span";
    public static final String ROW_CONTROL_BTN_ARCHIVE = "//button[./i]";

    public static final String BTN_WITHDRAW_NAME = "withdraw instructions";
    public static final String BTN_CONFIRM_INSTRUCTION_NAME = "Confirm Instructions";
    public static final String BTN_CONFIRM_COMPLETION_NAME = "Confirm Completion";

    public static final String ROW_CONTROL_PLEDGES_BLOCK = "//cmnt-profile-pledges/ul[1]";
    //Incoming
    public static final String MSG_DEFAULT_INSTRUCTION = "Thank you for all the information. Please consider this as instructions to proceed with this case as discussed.";
    public static final String MSG_DEFAULT_CONFIRM_INSTRUCTIONS = "I'm pleased to confirm safe receipt of your instructions and will execute them on time. I will report immediately once the work is completed.";
    public static final String MSG_DEFAULT_CONFIRM_COMPLETION = "I'm pleased to confirm that your instructions were executed and the work was completed on time.";
    public static final String MSG_DEFAULT_WITHDRAW = "Please DO NOT proceed with this filing. Kindly confirm immediately.";


}
