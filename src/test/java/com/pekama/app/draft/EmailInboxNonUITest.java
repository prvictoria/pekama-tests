package com.pekama.app.draft;

import Page.TestsCredentials;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

//import utils.EmailInboxChecker;

public class EmailInboxNonUITest {

	private static final String USERNAME = "hist45@tut.by";
	private static final String PASSWORD = "2271941";
	private Logger log = Logger.getLogger(EmailInboxNonUITest.class);

	@Test
	public void checkIncomingMessages() {
		log.info("Test " + EmailInboxNonUITest.class + " started");
		int incomingEmailsAmount = EmailInboxChecker.checkEmailsAmount(USERNAME, PASSWORD);
		Assert.assertNotNull(incomingEmailsAmount);
		log.info("Test " + EmailInboxNonUITest.class + " ended \n" + "------------------------------------");

	}
}
