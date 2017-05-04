package Draft;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static Page.PekamaLanding.BTN_LOGIN;
import static Page.PekamaLogin.lOGIN_TITLE;
import static Page.PekamaLogin.lOGIN_TITLE_TEXT;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by VatslauX on 27-Dec-16.
 */
public class BasicAuth {
//public class BasicAuth extends IntegrationTest{
////    FirefoxProfile firefoxProfile = new ProfilesIni().getProfile("default");
////    File ffPluginAutoAuth = new File("D:\\autoauth-2.1-fx+fn.xpi");
////    firefoxProfile.addExtension(ffPluginAutoAuth);
////    FirefoxDriver driver = new FirefoxDriver(firefoxProfile);
//    //    @Test
////    public void template() {
////        open(login);
////        $("").sendKeys("");
////        $(By.xpath("")).sendKeys("");
////        $(By.xpath("")).click();
////    }
    @Ignore
    @Test
    public void openLogin() {

        //open("http://www.staging.pekama.com");

        //open("http://qweeco:qw33coStudi0@staging.pekama.com");
        //switchTo().alert().authenticateUsing("qweeco", login);
        //open("http://www.staging.pekama.com\\qweeco:qw33coStudi0@staging.pekama.com");
        //open("https://staging.pekama.com", "www.staging.pekama.com", "qweeco" , "qw33coStudi0");
        BTN_LOGIN.shouldBe(Condition.visible).click();
        sleep(1000);
        lOGIN_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(lOGIN_TITLE_TEXT));
    };

    @Ignore
        @Test
        public void canPassBasicAuthInFirefox() {
            assumeTrue(isFirefox());
            Selenide.open("https://staging.pekama.com/",
                    "",
                    "qweeco",
                    "qw33coStudi0");
            sleep(3000);
            $(byText("about")).waitUntil(visible, 10000);
            assertThat(source(), containsString("\"authenticated\": true,"));
//            Selenide.open("http://httpbin.org/basic-auth/user/passwd",
//                    "",
//                    "user",
//                    "passwd");
//            $(By.tagName("pre")).waitUntil(visible, 10000);
//            assertThat(source(), containsString("\"authenticated\": true,"));
        }
    @Ignore
        @Test
        public void canPassBasicAuthInHtmlUnit() {
            assumeTrue(isHtmlUnit());
            Selenide.open("http://httpbin.org/basic-auth/user/passwd",
                    "",
                    "user",
                    "passwd");
            assertThat(source(), containsString("\"authenticated\": true,"));
        }
    @Ignore
        @Test
        public void canPassBasicAuthInPhantomJS() {
            assumeTrue(isPhantomjs());
            Selenide.open("http://httpbin.org/basic-auth/user/passwd",
                    "",
                    "user",
                    "passwd");
            $(By.tagName("pre")).waitUntil(visible, 10000);
            assertThat(source(), containsString("\"authenticated\": true,"));
        }
    @Ignore
        @Test
        public void canPassBasicAuthInChrome() {
            assumeTrue(isChrome());
            Selenide.open("http://httpbin.org/basic-auth/user/passwd",
                    "",
                    "user",
                    "passwd");
            $(By.tagName("pre")).waitUntil(visible, 10000);
            assertThat(source(), containsString("\"authenticated\": true,"));
        }
    @Ignore
        @Test
        public void canPassBasicAuthInIe() {
            assumeTrue(isIE());
            Selenide.open("http://httpbin.org/basic-auth/user/passwd",
                    "",
                    "user",
                    "passwd");
            assertThat(source(), containsString("WebDriver"));
        }
    }
