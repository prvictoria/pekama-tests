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
import static Steps.ObjectUser.Users.USER_03;
import static Steps.ObjectUser.Users.USER_04;
import static Steps.ObjectUser.newBuilder;
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
public class TestsPekamaProjectCommunity {
}
