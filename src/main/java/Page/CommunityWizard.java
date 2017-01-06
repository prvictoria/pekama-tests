package Page;

/**
 * Created by Viachaslau_Balashevi on 12/29/2016.
 */
public class CommunityWizard {
    public static final String WIZARD_ = "";
    public static final String WIZARD_STEP1 = "//section/ui-view/div[1]";
    public static final String WIZARD_STEP2 = "//section/ui-view/div[2]";
    public static final String WIZARD_STEP3 = "//section/ui-view/div[3]";
    public static final String WIZARD_STEP4 = "//section/ui-view/div[4]";
    public static final String WIZARD_STEP5 = "//section/ui-view/div[5]";

    public static final String WIZARD_BTN_GetStarted = "//button[contains(text(),'Get Started')]";
    public static final String WIZARD_BTN_NEXT = "//button[contains(text(),'Next')]";

    public static final String WIZARD_BTN_YES = WIZARD_STEP2+"//div[@ class='toggle-group mb-3']/label[1]";
    public static final String WIZARD_BTN_NO = WIZARD_STEP2+"//div[@ class='toggle-group mb-3']/label[2]";

}
