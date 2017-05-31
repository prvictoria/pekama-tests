package Steps;

import Page.TestsCredentials;
import org.junit.Test;

//import static Steps.BuildUser.newBuilder;
import static Page.TestsCredentials.*;
import static Page.TestsCredentials.Countries.AFGHANISTAN;
import static Page.TestsCredentials.Countries.NETHERLANDS_ANTILES;
import static Page.TestsCredentials.Countries.PITCAIRN_ISLANDS;
import static Page.TestsCredentials.ProductionCaseType.PATENT;
import static Page.UrlStrings.URL_PEKAMA_LOGIN;
import static Steps.ObjectContact.enterPoint.REPORT;
import static Steps.ObjectEvent.PatentEventTypes.ABANDONMENT;
import static Steps.ObjectEvent.PatentEventTypes.GRANT;
import static Steps.ObjectFile.FileTypes.JPG;
import static Steps.ObjectFile.FileTypes.PDF;
import static Steps.ObjectFile.FileTypes.SVG;
import static Steps.ObjectUser.*;
import static Steps.ObjectUser.Users.OWNER;
import static Steps.ObjectUser.Users.USER_04;
import static Utils.Utils.getDate;


/**
 * Created by Viachaslau_Balashevi on 5/26/2017.
 */
public class TestClasses {
    private static final String OWNER_LOGIN_EMAIL = null;
    private static final String OWNER_PASSWORD_PEKAMA = null;
    private static ObjectUser owner = newBuilder().email(OWNER_LOGIN_EMAIL).passwordPekama(OWNER_PASSWORD_PEKAMA).build();
    private static ObjectUser user = newBuilder().build();

    @Test
    public  void enumTest() {
        System.out.println(TrademarkEvents.APPLICATION_REGISTERED.toString());
        System.out.println(TrademarkEvents.APPLICATION_REGISTERED.getValue());
//        System.out.println(User1.GMAIL_PASSWORD.value);
//        System.out.println(User1.TEAM_CODE.value);
//        System.out.println(User1.FULL_TEAM_NAME.value);

    }
    @Test
    public void test_build_user() {
        ObjectUser user1 = newBuilder().phone("333-44-5555").isLoginSucceed(true).build();
        System.out.println(user1);
        System.out.println(user1.phone);
        System.out.println(user1.isLoggedIn);
        System.out.println("================================");

        owner.login(owner.email, owner.passwordPekama, URL_PEKAMA_LOGIN);
        System.out.println(owner);
        System.out.println(owner.phone);
        System.out.println(owner.isLoggedIn);
        System.out.println("================================");

        ObjectUser owner = new ObjectUser(newBuilder()).buildUser(OWNER);
        ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_04);
    }
    @Test
    public void test_build_file(){
        ObjectFile build = ObjectFile.newBuilder().isFile(false).build();
        System.out.println(build);

        ObjectFile file = ObjectFile.newBuilder().isFile(true).fileName("qqwqwqwq").build();
        System.out.println(file);
        System.out.println(file.isFile);
        System.out.println(file.fileName);
        System.out.println("================================");

        ObjectFile file3 = ObjectFile.newBuilder().fileName("").fileExtension("").build();
        ObjectFile genericFile = ObjectFile.newBuilder().build();
        ObjectFile fileJpg = new ObjectFile(ObjectFile.newBuilder()).buildFile(JPG);
    }
    @Test
    public void test_build_project(){
        ObjectProject project = ObjectProject.newBuilder()
                .projectMatterType(CaseType.PATENT.getValue())
                .projectDefining("CANADA")
                .projectName("EVENTS_")
                .build();
        project.logProjectFields();
    }
    @Test
    public void test_build_event(){
        ObjectEvent event = ObjectEvent.newBuilder()
                .eventRelevantToMatterType("PATENT")
                .eventRelevantToDefining("Canada")
                .eventRelevantToType("Any")
                .eventType("Abandonement")
                .eventDateFormToday(0)
                .eventDate(getDate (0))
                .eventInfo("INFO_01")
                .build();
        event.logEventFields();

        ObjectEvent event1 = new ObjectEvent(ObjectEvent.newBuilder()).buildEventInPatent(GRANT);
    }
    @Test
    public void test_build_contact(){
        ObjectContact contact = ObjectContact.newBuilder()
                .contactType("Company")
                .contactLegalEntity("Law firm")
                .contactEmail("boss@dot.com")
                .contactCountry(PITCAIRN_ISLANDS.getValue())
                .build()
                .logContactFields();
        ObjectContact person = ObjectContact.newBuilder()
                .contactType("Person")
                .contactFirstName("")
                .contactLastName("")
                .build()
                .logContactFields();
    }
}
