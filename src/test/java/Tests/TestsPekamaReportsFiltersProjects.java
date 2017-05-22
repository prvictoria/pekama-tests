package Tests;

import Page.TestsCredentials;
import Steps.ObjectCharges;
import Steps.ObjectContact;
import Steps.User;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import javax.mail.MessagingException;
import java.io.IOException;

import static Page.ModalWindows.*;
import static Page.PekamaDashboard.*;
import static Page.PekamaReports.*;
import static Page.PekamaTeamSettings.TAB_MEMBERS_BTN_ADD;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.ContactRelation.*;
import static Page.TestsStrings.*;
import static Page.UrlConfig.*;
import static Page.UrlStrings.*;
import static Steps.ObjectCharges.checkInvoiceRowReports;
import static Steps.ObjectContact.enterPoint.*;
import static Steps.StepsHttpAuth.openUrlWithBaseAuth;
import static Steps.StepsModalWindows.submitMwNewProject;
import static Steps.StepsPekama.*;
import static Steps.StepsPekama.openPageWithSpinner;
import static Steps.StepsPekama.submitEnabledButton;
import static Steps.StepsPekamaProject.selectAndAddContact;
import static Steps.StepsPekamaReports.*;
import static Tests.BeforeTestsSetUp.*;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersProjects {
    static final Logger rootLogger = LogManager.getRootLogger();
}
