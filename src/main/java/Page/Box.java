package Page;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Box extends Page {
    public static final String box = "";
    public static final String boxLoginURL = "https://account.box.com/login";
    public static final String boxFilesURL = "https://app.box.com/files";
    public static final String boxLogoutURL = "https://app.box.com/logout";


    public static final SelenideElement boxConnectDashboardButton = $(byXpath("link=boost my document management"));
    public static final SelenideElement boxConnectStorageButton = $(byXpath("//button[text()='Connect Box']"));
    public static final SelenideElement boxConnectProjectButton = $(byXpath("//button[text()='Connect Box']"));

    public static final SelenideElement boxWindow_TITLE = $(byXpath("Box | Simple Online Collaboration"));
    public static final SelenideElement boxWindowEmail = $(byName("login"));
    public static final SelenideElement boxWindowPassword = $(byName("password"));
    public static final SelenideElement boxWindowSubmit = $(byName("login_submit"));
    public static final SelenideElement boxWindowAccept = $(byXpath("//button[@id='consent_accept_button']"));
    public static final SelenideElement boxWindowReject = $(byXpath("//input[@id='consent_reject_button']"));
    public static final SelenideElement boxSubmit = $(byXpath("//button[@type='submit']"));

    public static final SelenideElement BOX_BTN_SIGN_IN = $(byXpath("//button[@type='submit']"));

    public static final SelenideElement boxList = $(byXpath("//ul[@id='item-list']/li"));
    public static final SelenideElement boxItemName = $(byXpath("//div[@class='item-name-container']"));

    public static final SelenideElement boxNameFolderTeam1 = $(byText("Pekama - Qweeco01 (QT01)"));
    public static final SelenideElement boxNameFolderTeam2 = $(byText("Pekama - Qweeco02 (QT01)"));
    public static final SelenideElement boxNameFolderTeam3 = $(byText("Pekama - Qweeco03 (QT01)"));
    public static final SelenideElement boxNameFolderProject = $(byXpath(""));
    public static final SelenideElement boxNameFolderInner = $(byXpath(""));
    public static final SelenideElement boxNameFileInner = $(byXpath(""));
    public static final SelenideElement boxPlaceholderPath = $(byXpath("//div[@id='empty-folder-content']/span[2]"));
    public static final SelenideElement boxNoFilesPlaceholder = $(byText("Drag and drop files from your desktop or use the file browser."));

}
