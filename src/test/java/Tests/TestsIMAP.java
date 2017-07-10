package Tests;

import Steps.MessagesIMAP;
import Steps.ObjectProject;
import Steps.ObjectUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import static Page.TestsStrings.LOREM_IPSUM_SHORT;
import static Page.UrlConfig.setEnvironment;
import static Steps.IMessagesValidator.ValidationInviteInProject.projectBackLink;
import static Steps.IMessagesValidator.ValidationInviteInTeamUnregistered.teamBackLink;

/**
 * Created by Viachaslau_Balashevi on 4/12/2017.
 */
public class TestsIMAP {
    static final Logger rootLogger = LogManager.getRootLogger();
    private static final String REQUESTER_EMAIL = null;
    private static final String EXPERT_TEAM_NAME = null;
    private static final String REQUESTER_EMAIL_PASSWORD = null;
    private static final String REQUESTER_NAME_SURNAME = null;

    private static final String INVITED_EMAIL = null;
    private static final String INVITED_PASSWORD = null;
    private static final String INVITER_NAME_SURNAME = null;
    private static final String INVITER_NAME_FULL_TEAM_NAME = null;
    private static final String INVITER_NAME = null;
    private static final String REPORT_NAME = null;
    private static final String PROJECT_TITLE = null;

    @Before
    public void beforeTest(){
        setEnvironment();
    }
    @Test
    public void validateEmailSignUp(){
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailSignUp(login, password);
        Assert.assertTrue(validationResult);
        return;
    }
    @Test
    public void validateEmailResetPassword(){

        return;
    }
    @Test
    public void validateEmailCongratulation(){
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        String teamName = EXPERT_TEAM_NAME;

        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailCongratulation(
                login,
                password,
                teamName);
        Assert.assertTrue(validationResult);
        return;
    }
    @Test
    public void validateEmailCongratulationForInvite(){
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        String invitedEmail = INVITED_EMAIL;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailCongratulationForInvite(
                login,
                password,
                invitedEmail);
        Assert.assertTrue(validationResult);
        return;
    }
    @Test
    public void validateEmailInviteInTeam(){
        String login = INVITED_EMAIL;
        String password = INVITED_PASSWORD;
        String inviterNameSurname = INVITER_NAME_SURNAME;
        String inviterFullTeamName = INVITER_NAME_FULL_TEAM_NAME;
        String inviterName = INVITER_NAME;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInTeamUnregistered(
                login, password,
                inviterNameSurname, inviterName, inviterFullTeamName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(teamBackLink);
        rootLogger.info("Link invite to Team is: "+teamBackLink);
        return;
    }
    @Test
    public void validateEmailInviteInProject(ObjectUser inviter, ObjectUser invited, ObjectProject project){
        rootLogger.info("Check invite email");
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInProject(invited.email, invited.passwordEmail, inviter.nameSurname, project.projectName);
        Assert.assertTrue(validationResult);
        Assert.assertNotNull(projectBackLink);
        rootLogger.info("Test passed");
        return;
    }
    public void validateEmailInviteInCommunity(){
        String login = INVITED_EMAIL;
        String password = INVITED_PASSWORD;
        String name_surname = REQUESTER_NAME_SURNAME;
        String customText = LOREM_IPSUM_SHORT;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailInviteInCommunity(
                login,
                password,
                name_surname,
                customText);
        Assert.assertTrue(validationResult);
        return;
    }
    @Test
    public void validateEmailInviteInPekama(){

        return;
    }
    @Test
    public void validateEmailReport(){
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        String reportSchedule = "999";
        String reportName = REPORT_NAME;
        MessagesIMAP validation = new MessagesIMAP();
        Boolean validationResult = validation.validateEmailReport(login, password, reportSchedule, reportName);
        Assert.assertTrue(validationResult);
        return;
    }
    @Test
    public void deleteAllEmails() throws MessagingException {
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.imapSearchEmailDeleteAll(
                login,
                password);
    }
}