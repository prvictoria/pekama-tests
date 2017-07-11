package Pages;
import Steps.Page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Xero extends Page {
    public static final SelenideElement extXeroEmail = $(byXpath("//input[@id='email']"));
    public static final SelenideElement extXeroPassword = $(byXpath("//input[@id='password']"));
    public static final SelenideElement extXeroLogin = $(byXpath("//button[@id='submitButton']"));
    public static final SelenideElement extXeroAccept = $(byXpath("//input[@id='submit-button']"));
    public static final SelenideElement extXeroBillTotal = $(byId("invoiceTotal"));
}
