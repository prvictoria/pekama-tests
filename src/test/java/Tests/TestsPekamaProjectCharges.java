package Tests;

import Steps.*;
import com.codeborne.selenide.ex.SoftAssertionError;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;


import static Page.PekamaProject.*;
import static Page.PekamaTeamSettings.*;
import static Steps.ObjectCharges.checkInvoiceRow;
import static Steps.Steps.clickSelector;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import static Page.Xero.*;
import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
/**
 * Created by VatslauX on 20-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProjectCharges {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD = User3.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private static final String OWNER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final static String OWNER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();
    private static ObjectContact contact1 = new ObjectContact();
    private static ObjectContact contact2 = new ObjectContact();
    private static ObjectContact contact3 = new ObjectContact();
    private static ObjectCharges invoice1Sort = new ObjectCharges();
    private static ObjectCharges invoice2Sort = new ObjectCharges();
    private static ObjectCharges invoice3Sort = new ObjectCharges();
    private static ObjectUser user = ObjectUser.newBuilder().email(OWNER_LOGIN_EMAIL).passwordPekama(OWNER_PASSWORD).build();

    private static String projectName;
    private static String projectUrl;
    private static boolean skipBefore = false;

    @Rule
    public Timeout tests = Timeout.seconds(600);
    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
            user.loginByURL(user.email, user.passwordPekama, URL_LogIn);

            rootLogger.info("Create project");
            submitEnabledButton(DASHBOARD_BTN_NEW_PROJECT);
            projectName = submitMwNewProject();
            projectUrl = getActualUrl();

            deleteAllMembers();
            addMember("A-member@email.com", TAB_MEMBERS_BTN_ADD);
            addMember("B-member@office.eu", TAB_MEMBERS_BTN_ADD);

            deleteAllContacts();
            rootLogger.info("Create contacts in reports");
            contact1.createCompany(REPORT, "ip lawyers ltd",
                    null, null,
                    null, null,null,
                    null, null,
                    null, null);
            contact2.createCompany(REPORT, "Law firm",
                    null, null,
                    null, null,null,
                    null, null,
                    null, null);
            contact3.createPerson(REPORT, null,
                    "Name", "Surname", null,
                    null, null,
                    null, null,null,
                    null, null,
                    null, null);
            openPageWithSpinner(URL_ReportsProjects);

            rootLogger.info("Add contacts to project");
            openUrlWithBaseAuth(projectUrl);
            selectAndAddContact(contact1, DOMESTIC_REPRESENTATIVE.getValue());
            selectAndAddContact(contact2, OWNER_COMPANY.getValue());
            selectAndAddContact(contact3, INVESTOR.getValue());
            getWebDriver().quit();
        }
        else {rootLogger.info("Before suite was skipped");
        }
    }
    @Before
    public void login() {
        clearBrowserCache();
        user.loginByURL(user.email, user.passwordPekama, projectUrl);
    }

    @Test
    public void tabCharges_B1_sort_by_date(){
        StepsPekamaProject.deleteAllCharges();

        invoice1Sort.create(OWNER_TEAM_NAME, contact1.contactLegalEntity,
                null, "Billed",
                CHARGES_TYPE_EXPENSES, 10,
                "abc", GBP, 1);
        invoice2Sort.create(OWNER_TEAM_NAME, null,
                "A-member", "Not Billed",
                CHARGES_TYPE_ASSOCIATE, 0,
                "def",ILS, 999);
        invoice3Sort.create(OWNER_TEAM_NAME, contact3.contactNameSurname,
                "B-member", "Billed & Paid",
                CHARGES_TYPE_FEES, -10,
                "xyz",USD, 100);

        rootLogger.info("Validate sort order and rows by: "+"Date");
        selectSortOrderInProject(null, false);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
        selectSortOrderInProject("Date", true);
        checkInvoiceRow(1, invoice3Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice1Sort);
    }
    @Test
    public void tabCharges_B2_sort_by_last_created(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"Last created");
        selectSortOrderInProject("Last created", false);
        checkInvoiceRow(1, invoice3Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice1Sort);
        selectSortOrderInProject("Last created", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
    }
    @Test
    public void tabCharges_B3_sort_by_last_modified(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"Last modified");
        selectSortOrderInProject("Last modified", false);
        checkInvoiceRow(1, invoice3Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice1Sort);
        selectSortOrderInProject("Last modified", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
    }
    @Test
    public void tabCharges_B4_sort_by_amount(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"Amount");
        selectSortOrderInProject("Amount", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice3Sort);
        checkInvoiceRow(3, invoice2Sort);
        selectSortOrderInProject("Amount", false);
        checkInvoiceRow(1, invoice2Sort);
        checkInvoiceRow(2, invoice3Sort);
        checkInvoiceRow(3, invoice1Sort);
    }
    @Test
    public void tabCharges_B5_sort_by_from(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"From");
        selectSortOrderInProject("From", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
        selectSortOrderInProject("From", false);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
    }
    @Test
    public void tabCharges_B6_sort_by_to(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"To");
        selectSortOrderInProject("To", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(3, invoice2Sort);
        checkInvoiceRow(2, invoice3Sort);
        selectSortOrderInProject("To", false);
        checkInvoiceRow(1, invoice2Sort);
        checkInvoiceRow(2, invoice3Sort);
        checkInvoiceRow(3, invoice1Sort);
    }
    @Test
    public void tabCharges_B7_sort_by_type(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"Type");
        selectSortOrderInProject("Type", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice3Sort);
        selectSortOrderInProject("Type", false);
        checkInvoiceRow(1, invoice3Sort);
        checkInvoiceRow(2, invoice2Sort);
        checkInvoiceRow(3, invoice1Sort);
    }
    @Test
    public void tabCharges_B8_sort_by_status(){
        clickSelector(PROJECT_TAB_CHARGES);
        rootLogger.info("Validate sort order and rows by: "+"Status");
        selectSortOrderInProject("Status", true);
        checkInvoiceRow(1, invoice1Sort);
        checkInvoiceRow(3, invoice3Sort);
        checkInvoiceRow(2, invoice2Sort);
        selectSortOrderInProject("Status", false);
        checkInvoiceRow(1, invoice2Sort);
        checkInvoiceRow(2, invoice3Sort);
        checkInvoiceRow(3, invoice1Sort);
    }

    @Test
    public void tabCharges_modal_validation_empty() {
        String bigDecimal = "12345678901234567890";
        String floatString1 = "1.2345678901234567890";
        String floatString2 = "123456789012345678.90";
        StepsPekamaProject.deleteAllCharges();
        callChargesModal();

        rootLogger.info("Validation empty field");
        MW_CHARGES_SELECT_FROM.shouldHave(text(OWNER_FULL_TEAM_NAME));
        fillField(MW_CHARGES_INPUT_ITEM, LOREM_IPSUM_SHORT);
        submitEnabledButton(MW_BTN_OK);
        checkText(ERROR_MSG_REQUIRED_FIELD, 2);
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible), 20000);

        rootLogger.info("Validation max value HOUR, MIN, RATE");
        callChargesModal();
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_HOUR, bigDecimal);
        fillField(MW_CHARGES_INPUT_MIN, bigDecimal);
        fillField(MW_CHARGES_INPUT_RATE, bigDecimal);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 18 digits in total.", 2);
        checkText("Ensure this value is less than or equal to 2147483647.", 2);
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible), 20000);
    }
    @Test
    public void tabCharges_modal_validation_max_value() {
        String bigDecimal = "12345678901234567890";
        String floatString1 = "1.2345678901234567890";
        String floatString2 = "123456789012345678.90";
        StepsPekamaProject.deleteAllCharges();
        callChargesModal();

        rootLogger.info("Validation max value - QTY, PRICE, VAT, DISCOUNT");
        callChargesModal();
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        fillField(MW_CHARGES_INPUT_QTY, bigDecimal);
        fillField(MW_CHARGES_INPUT_PRICE, bigDecimal);
        fillField(MW_CHARGES_INPUT_VAT, bigDecimal);
        fillField(MW_CHARGES_INPUT_DISCOUNT, bigDecimal);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure this value is less than or equal to 2147483647.");
        checkText("Ensure that there are no more than 18 digits in total.", 2);
        checkText("Ensure that there are no more than 7 digits in total.");
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible), 20000);
    }
    @Test
    public void tabCharges_modal_validation_float() {
        String bigDecimal = "12345678901234567890";
        String floatString1 = "1.2345678901234567890";
        String floatString2 = "123456789012345678.90";
        StepsPekamaProject.deleteAllCharges();

        callChargesModal();
        rootLogger.info("Validation float - PRICE should be decimal");
        callChargesModal();
        selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE, CHARGES_TYPE_ASSOCIATE);
        selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, GBP);
        MW_CHARGES_INPUT_PRICE.clear();
        MW_CHARGES_INPUT_PRICE.setValue(floatString1);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 4 decimal places." );

        MW_CHARGES_INPUT_PRICE.clear();
        MW_CHARGES_INPUT_PRICE.setValue(floatString2);
        submitEnabledButton(MW_BTN_OK);
        checkText("Ensure that there are no more than 14 digits before the decimal point." );
        MW_BTN_CANCEL.click();
        MW.waitUntil(not(visible),20000);
        rootLogger.info("Test passed");
    }
    @Test
    public void tabCharges_Xero_A_SendBill()  throws SoftAssertionError {
        StepsPekamaProject.deleteAllCharges();

        String xeroLogin = OWNER_LOGIN_EMAIL;
        String xeroPassword = OWNER_XERO_PASSWORD;
        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, EUR, price);
        rootLogger.info("Start Xero flow");

        callXeroModal();
        if ($(byText("Invoice created")).isDisplayed() == false) {
            rootLogger.info("Modal window not displayed");
            try {
                switchTo().window(PAGE_TITLE_XERO_LOGIN);
                String url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_XERO_LOGIN)==false){
                    Assert.fail("Xero window NOT found");}
                fillField(extXeroEmail, xeroLogin);
                fillField(extXeroPassword, xeroPassword);
                submitEnabledButton(extXeroLogin);
                rootLogger.info("Xero login window submitted");

                switchTo().window(PAGE_TITLE_XERO_AUTH);
                url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_XERO_AUTH)==false){
                    Assert.fail("Window Xero Authorise window not found");}
                submitEnabledButton(extXeroAccept);
                rootLogger.info("Xero auth window submitted");
                sleep(5000);

                switchTo().window(PAGE_TITLE_PEKAMA);
                url = getActualUrl();
                rootLogger.info(url);
                if (checkPageTitle(PAGE_TITLE_PEKAMA)==false){
                    Assert.fail("Return to Pekama failed");}
                sleep(2000);
            }
            catch (SoftAssertionError e) {
                rootLogger.info("Return to Pekama failed");
            }
        }

        if ($(byText("Invoice created")).isDisplayed()) {
            rootLogger.info("Modal window displayed");
            waitForModalWindow("Invoice created");
            submitEnabledButton(MW_BTN_YES);
            MW.waitUntil(not(visible), 10000);
            sleep(5000);
            checkThatWindowsQtyIs(2);
            for(String winHandle : getWebDriver().getWindowHandles()){
                rootLogger.info(winHandle);
                switchTo().window(winHandle);
                getActualUrl();
            }

            if (checkPageTitle(PAGE_TITLE_XERO_LOGIN)==true){
                try {
                    getActualUrl();
                    fillField(extXeroEmail, xeroLogin);
                    fillField(extXeroPassword, xeroPassword);
                    submitEnabledButton(extXeroLogin);
                    sleep(5000);
                    rootLogger.info("Xero login window submitted");
                } catch (SoftAssertionError e) {
                    if (checkPageTitle(PAGE_TITLE_XERO_LOGIN) == false) {
                        rootLogger.info("Xero window NOT found");
                    }
                }
            }
            if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == true){
                try {
                    getActualUrl();
                    sleep(3000);
                } catch (SoftAssertionError e) {
                    if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == false) {
                        rootLogger.info("Window Xero Authorise not found - goto label");
                    }
                }
            }
        }
        sleep(3000);
        checkText("5,000.00", 2);
        close();
        rootLogger.info("Test passed");
    }
    @Test
    public void tabCharges_Xero_B_ValidationNotSameCurrency(){
        StepsPekamaProject.deleteAllCharges();

        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, EUR, price);
        createCharge(testSearchChargesType, USD, price);
        rootLogger.info("Start Xero flow");
        callXeroModal();

        waitForModalWindow("ERRORS");
        checkText("Financials have different currency codes");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
    }
    @Test
    public void tabCharges_Xero_B_ValidationNotAllowedCurrency(){
        StepsPekamaProject.deleteAllCharges();

        String price = "5000";
        rootLogger.info("Create Charge");
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;
        createCharge(testSearchChargesType, USD, price);
        createCharge(testSearchChargesType, USD, price);
        rootLogger.info("Start Xero flow");
        callXeroModal();

        waitForModalWindow("ERRORS");
        checkText("Organisation is not subscribed to currency USD");
        submitEnabledButton(MW_BTN_OK);
        MW.shouldNotBe(visible);
    }
    @Test//(timeout=240000)
    public void tabCharges_Xero_C_MergeCharges(){
        StepsPekamaProject.deleteAllCharges();

        String xeroLogin = OWNER_LOGIN_EMAIL;
        String xeroPassword = OWNER_XERO_PASSWORD;
        String price1 = "7777";
        String price2 = "1111";
        String testSearchChargesType = CHARGES_TYPE_ASSOCIATE;

        rootLogger.info("Create 2 Charges");
        createCharge(testSearchChargesType, EUR, price1);
        createCharge(testSearchChargesType, EUR, price2);

        rootLogger.info("Start Xero flow");
        callXeroModal();
        if ($(byText("Invoice created")).isDisplayed()) {
            rootLogger.info("Modal window displayed");
            waitForModalWindow("Invoice created");
            submitEnabledButton(MW_BTN_YES);
            MW.shouldNotBe(visible);}
        sleep(2000);
        try {
            switchTo().window(PAGE_TITLE_XERO_LOGIN);
            String url = getActualUrl();
            rootLogger.info(url);

            fillField(extXeroEmail, xeroLogin);
            fillField(extXeroPassword, xeroPassword);
            submitEnabledButton(extXeroLogin);
            rootLogger.info("Xero login window submitted");}
        catch (SoftAssertionError e) {
            if (checkPageTitle(PAGE_TITLE_XERO_LOGIN) == false) {
                rootLogger.info("Xero window NOT found");
            }
        }
        sleep(6000);
        try {
            switchTo().window(PAGE_TITLE_XERO_BILLING);
            sleep(6000);
            String url = getActualUrl();
            rootLogger.info(url);
        }
        catch (SoftAssertionError e) {
            if (checkPageTitle(PAGE_TITLE_XERO_BILLING) == false) {
                rootLogger.info("Window Xero Authorise not found");
            }

        }
        finally {
            sleep(3000);
            checkText("7,777.00", 2);
            checkText("1,111.00", 2);
            extXeroBillTotal.shouldHave(value("8,888.00"));
            checkValue("8,888.00", 2);
            close();
            rootLogger.info("Test passed");
        }
    }
    @Test
    public void tabCharges_Z_delete_all(){
        StepsPekamaProject.deleteAllCharges();
        checkText(PLACEHOLDER_EMPTY_LIST);
        ObjectCharges invoice1 = new ObjectCharges();
        invoice1.create(CHARGES_TYPE_EXPENSES, GBP, 10);
        ObjectCharges invoice2 = new ObjectCharges();
        invoice2.create(CHARGES_TYPE_ASSOCIATE, ILS, 999);
        ObjectCharges invoice3 = new ObjectCharges();
        invoice3.create(CHARGES_TYPE_FEES, USD, 100);
        ObjectCharges invoice4 = new ObjectCharges();
        invoice4.create(CHARGES_TYPE_SERVICE, EUR, 1);
        ObjectCharges check = new ObjectCharges();
        checkInvoiceRow(1, invoice1);
        checkInvoiceRow(2, invoice2);
        checkInvoiceRow(3, invoice3);
        checkInvoiceRow(4, invoice4);
        StepsPekamaProject.deleteAllCharges();
        checkText(PLACEHOLDER_EMPTY_LIST);
    }
}
