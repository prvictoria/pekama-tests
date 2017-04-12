package Tests;

import Page.TestsCredentials;
import Steps.MessagesIMAP;
import Steps.MessagesValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Page.CommunityDashboard.teamName;
import static Page.TestsStrings.LOREM_IPSUM_SHORT;
import static Page.UrlConfig.setEnvironment;
import static Steps.Messages.EMAIL_SUBJECT_CONGRATULATION_CASE_CREATED;
import static Steps.MessagesIMAP.detectEmailIMAP;

/**
 * Created by Viachaslau_Balashevi on 4/12/2017.
 */
public class TestsIMAP {
    private static final String REQUESTER_EMAIL = null;
    private static final String EXPERT_TEAM_NAME = null;
    private static final String REQUESTER_EMAIL_PASSWORD = null;
    private static final String INVITED_EMAIL = null;
    private static final String INVITED_PASSWORD = null;
    private static final String REQUESTER_NAME_SURNAME = null;

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

        return;
    }
    @Test
    public void validateEmailInviteInProject(){

        return;
    }
    public void validateEmailInviteInCommunity(){

        return;
    }
    @Test
    public void validateEmailInviteInPekama(){
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
    public void validateEmailReport(){

        return;
    }
    @Test
    public void deleteAllEmails(){
        String login = REQUESTER_EMAIL;
        String password = REQUESTER_EMAIL_PASSWORD;
        MessagesIMAP emailTask = new MessagesIMAP();
        emailTask.searchEmailDeleteAll(
                login,
                password);
    }
}
