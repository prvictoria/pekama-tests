package com.pekama.app;

import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Page.PageGitHubLogin.*;


/**
 * Created by Viachaslau_Balashevi on 12/28/2016.
 */
public class DemoTest {


    private static Steps steps;

    @BeforeMethod(description = "init browser")
    public void  setUp(){
        steps = new Steps();
        steps.initBrowser();
    }
    @AfterMethod(description = "close")
    public void tearDown(){
        steps.closeBrowser();
    }

    @Test
    public static void userCanLogin() throws InterruptedException {

//        steps.loginGitHub(USER_NAME_GITHUB, USER_PASSWORD_GITHUB);
//        Assert.assertTrue(steps.isUserLoggedIn(USER_NAME_GITHUB));

    }


}
