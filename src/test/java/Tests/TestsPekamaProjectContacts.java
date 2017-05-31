package Tests;


import Steps.ObjectContact;
import Steps.ObjectUser;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;


import static Page.PekamaProject.*;
import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.TestsCredentials.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekama.*;
import static Steps.StepsPekamaProject.*;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaProjectContacts{
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String OWNER_LOGIN_EMAIL = User3.GMAIL_EMAIL.getValue();
    private static final String OWNER_PASSWORD_PEKAMA = User3.PEKAMA_PASSWORD.getValue();
    private static final String OWNER_TEAM_NAME = User3.TEAM_NAME.getValue();
    private static final String OWNER_XERO_PASSWORD = User3.XERO_PASSWORD.getValue();
    private final static String OWNER_FULL_TEAM_NAME = User3.FULL_TEAM_NAME.getValue();

    private static String testContactName = "name"+ randomString(10);
    private static String testContactSurname = "surname"+ randomString(10);
    private static ObjectContact contact = ObjectContact.newBuilder()
            .contactType("Person")
            .contactFirstName(testContactName)
            .contactLastName(testContactSurname)
            .build()
            .logContactFields();
    private static String projectName;
    private static String projectUrl;
    private static boolean skipBefore = false;
    private static ObjectUser owner = ObjectUser.newBuilder().email(OWNER_LOGIN_EMAIL).passwordPekama(OWNER_PASSWORD_PEKAMA).build();

    @Rule
    public Timeout tests = Timeout.seconds(600);

    @BeforeClass
    public static void beforeClass() throws IOException, MessagingException {
        setEnvironment ();
        setBrowser();
        holdBrowserAfterTest();
        if(skipBefore==false) {
          owner.login(owner.email, owner.passwordPekama, URL_PEKAMA_LOGIN);
        }
        else {rootLogger.info("Before suite was skipped");
        }
        deleteAllContacts();
        openPageWithSpinner(URL_ReportsProjects);
        rootLogger.info("Create project");
        submitEnabledButton(REPORTS_BTN_NEW_PROJECT);
        projectName = submitMwNewProject();
        projectUrl = getActualUrl();
        getWebDriver().quit();
    }
    @Before
    public void login() {
        //clearBrowserCache();
        owner.login(owner.email, owner.passwordPekama, projectUrl);
    }
    @Test
    public void tabContacts_F1_addNewContact_Person() {
        //checkText(PLACEHOLDER_NO_DATA);
        //todo BUG #140196199 https://www.pivotaltracker.com/n/projects/1239770/stories/140196199

        contact.createPerson(PROJECT, null,
                testContactName, testContactSurname,
                null, null, null, null,
                null, null, null,
                null, null, null);

        rootLogger.info("Check the contact is selected");
        $$(byText(testContactName+" "+testContactSurname)).filter(visible).shouldHaveSize(1);

        rootLogger.info("Select relation");
        selectItemInDropdown(projectTabContacts_AddSelectRelation, projectTabContacts_AddRelationInput, ContactRelation.ATTORNEY.getValue());

        rootLogger.info("Add contact to project");
        projectTabContacts_AddContactButton.click();
        projectTabContacts_ContactName.shouldHave(Condition.exactText(testContactName+" "+testContactSurname));
        projectTabContacts_ContactRelation.shouldHave(Condition.exactText((ContactRelation.ATTORNEY.getValue())));

        rootLogger.info("delete contact relation");
        projectTabContacts_ContactDelete.click();
        submitConfirmAction();
    }
    @Test
    public void tabContacts_F2_addExistedContact() {
        rootLogger.info("Select existed contact");
        selectAndAddContact(contact, ContactRelation.DOMESTIC_REPRESENTATIVE.getValue());

        rootLogger.info("Edit fields contact inline");
        projectTabContacts_ContactEdit.click();
        projectTabContacts_FormRelationSelect.selectOption(ContactRelation.CLIENT_COMPANY.getValue());
        fillField(projectTabContacts_FormOwnership, "99");
        fillField(projectTabContacts_FormEntity, "newEntity");
        fillField(projectTabContacts_FormFirstName, "NEWperson");
        fillField(projectTabContacts_FormLastName, "NEWman03");
        fillField(projectTabContacts_FormEmail, "NEWcontact_01_mail@mail.com");
        fillField(projectTabContacts_FormPhone, "newPhone");
        fillField(projectTabContacts_FormFax, "newFax");
        fillField(projectTabContacts_FormMobile, "newMobile");
        fillField(projectTabContacts_FormStreet, "newStreet");
        fillField(projectTabContacts_FormPostal, "newZip");
        fillField(projectTabContacts_FormCity, "newCity");
        fillField(projectTabContacts_FormRegion, "newRegion");
        selectItemInDropdown(projectTabContacts_FormCountrySelect, projectTabContacts_FormCountryInput, nameCountryIreland);
        submitEnabledButton(genericButtonSave);
        sleep(500);
        refresh();

        rootLogger.info("Check saved values");
        projectTabContacts_ContactEdit.shouldBe(visible).click();
        projectTabContacts_FormCountrySelect.shouldHave(Condition.text(nameCountryIreland));
        projectTabContacts_FormRelationSelect.shouldHave(Condition.text(ContactRelation.CLIENT_COMPANY.getValue()));
        String savedOption = projectTabContacts_FormRelationSelect.getSelectedText();
        Assert.assertEquals(ContactRelation.CLIENT_COMPANY.getValue(), savedOption);
        checkInputValue(projectTabContacts_FormOwnership, "99");
        checkInputValue(projectTabContacts_FormEntity, "newEntity");
        checkInputValue(projectTabContacts_FormFirstName, "NEWperson");
        checkInputValue(projectTabContacts_FormLastName, "NEWman03");
        checkInputValue(projectTabContacts_FormEmail, "NEWcontact_01_mail@mail.com");
        checkInputValue(projectTabContacts_FormPhone, "newPhone");
        checkInputValue(projectTabContacts_FormFax, "newFax");
        checkInputValue(projectTabContacts_FormMobile, "newMobile");
        checkInputValue(projectTabContacts_FormStreet, "newStreet");
        checkInputValue(projectTabContacts_FormPostal, "newZip");
        checkInputValue(projectTabContacts_FormCity, "newCity");
        checkInputValue(projectTabContacts_FormRegion, "newRegion");
    }
}
