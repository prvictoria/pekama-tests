package Steps;

import Pages.UrlConfiguration;
import Steps.Objects.Emails.*;
import org.junit.Test;

//import static Steps.BuildUser.newBuilder;
import javax.mail.MessagingException;

import java.io.IOException;
import java.util.ArrayList;

import static Pages.DataCredentials.*;
import static Pages.DataCredentials.Countries.PITCAIRN_ISLANDS;
import static Pages.DataCredentials.MatterType.PATENT;
import static Pages.UrlStrings.*;
import static Steps.ObjectEvent.PatentEventTypes.GRANT;
import static Steps.ObjectFile.FileTypes.JPG;
import static Steps.ObjectUser.*;
import static Steps.ObjectUser.Users.*;
import static Steps.ObjectUser.Users.USER_04;
import static Steps.ObjectUser.Users.USER_05;
import static Utils.Utils.getDate;
import static Utils.Utils.*;

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
        ObjectUser fakeUser = newBuilder()
                .name(randomString(101))
                .surname(randomString(20))
                .build();
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

        ObjectEvent event1 = new ObjectEvent(ObjectEvent.newBuilder()).buildEventInPatent(GRANT, 10);
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
    @Test
    public void testNewEmailsBuilder(){
        ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_05);
        ReferenceEmail email = new ReferenceEmail().buildEmail(EmailTypes.SIGN_UP, user);
        rootLogger.info(email.getAbstractEmail().emailSubject());


    }
    @Test
    public void newImapServiceCrearInbox() throws MessagingException, InterruptedException {
        ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_05);
        new ImapService()
                .setProperties()
                .connectStore(user)
                .openFolder()
                .markEmailsForDeletion()
                .clearFolder()
                .closeStore();
    }
    @Test
    public void newImapServiceValidateEmail() throws MessagingException, InterruptedException, IOException {
        new UrlConfiguration().setEnvironment(1);
        ObjectUser user = new ObjectUser(newBuilder()).buildUser(USER_05);
        ArrayList<ObjectUser> users = new ArrayList<ObjectUser>();
        users.add(user);
        new ValidatorEmailSignUp()
                .buildReferenceEmail(users.get(0))
                .getEmailFormInbox()
                .buildValidator()
                .checkEmailBody()
                .assertValidationResult()
                .getSignUpLink();
        rootLogger.info(user.email);
    }
    @Test
    public void builderTestInviteInProject(){
        new UrlConfiguration().setEnvironment(1);
        ObjectUser user1 = new ObjectUser(newBuilder()).buildUser(USER_05);
        ObjectUser user2 = new ObjectUser(newBuilder()).buildUser(USER_01);
        ObjectProject project1 = ObjectProject.newBuilder().projectName("AAAAAAA").build();
        ValidatorEmailInviteInProject buildReferenceEmail = new ValidatorEmailInviteInProject().buildReferenceEmail(user1, user2, project1);
    }
}
