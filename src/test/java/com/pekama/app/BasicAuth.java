package com.pekama.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.io.File;

/**
 * Created by VatslauX on 27-Dec-16.
 */
public class BasicAuth {
    FirefoxProfile firefoxProfile = new ProfilesIni().getProfile("default");
    File ffPluginAutoAuth = new File("D:\\autoauth-2.1-fx+fn.xpi");
    firefoxProfile.addExtension(ffPluginAutoAuth);
    FirefoxDriver driver = new FirefoxDriver(firefoxProfile);
}
