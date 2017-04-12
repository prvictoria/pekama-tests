package Tests;

import Page.TestsCredentials;
import Steps.MessagesIMAP;
import Steps.MessagesValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Page.CommunityDashboard.teamName;
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

        return;
    }
    @Test
    public void validateEmailReport(){

        return;
    }
}
