package Steps.Objects.Emails;

import Steps.ObjectUser;

import static Pages.Emails.EMAIL_RESET_PASSWORD_LINK;
import static Pages.UrlStrings.EMAIL_CONFIRM_REGISTRATION_LINK;
import static Pages.UrlStrings.REPORT_BACK_LINK;
import static Pages.UrlStrings.REPORT_UNSUBSCRIBE_LINK;

final public class ReferenceEmail {
    private AbstractEmail abstractEmail;
    public AbstractEmail getAbstractEmail() {
        return abstractEmail;
    }
    public void setAbstractEmail(AbstractEmail abstractEmail) {
        this.abstractEmail = abstractEmail;
    }
    public static String reportSchedule;
    public static String reportName;

    public ReferenceEmail buildEmail (EmailTypes emailType, ObjectUser user)  {
        new ReferenceEmail();
        switch (emailType) {
            case DEFAULT:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("")
                        .emailTitle("")
                        .emailText("")
                        .emailButtonLinkText("")
                        .emailButtonText("")
                        .emailLinkConfirmRegistrationInButton("")
                        .emailLinkConfirmRegistration("")
                        .build();
                break;
            case REPORT:
                //Pekama only
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Pekama Report \""+reportName+"\" - ")
                        .emailText("This is the report that you configured in Pekama. You will get it every "+reportSchedule+" days.")
                        .emailLinkBackToPekama(REPORT_BACK_LINK)
                        .emailLinkUnSubscribe(REPORT_UNSUBSCRIBE_LINK)
                        .build();
                break;
            case SIGN_UP:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Welcome to Pekama! Just one more click")
                        .emailTitle("Almost there...")
                        .emailText("To finish registration, please confirm your account.")
                        .emailButtonLinkText("Complete my registration")
                        .emailButtonText("Complete my registration")
                        .emailLinkConfirmRegistrationInButton(EMAIL_CONFIRM_REGISTRATION_LINK)
                        .emailLinkConfirmRegistration(EMAIL_CONFIRM_REGISTRATION_LINK)
                        .emailLinkMailTo(user.email)
                        .build();
                break;
            case RESET_PASSWORD:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Password Restoration [Pekama]")
                        .emailTitle("Password Restoration")
                        .emailText("You've received this e-mail because you requested to reset the password for your user account. Press the button bellow to complete restoration.")
                        .emailButtonLinkText("Reset Password")
                        .emailButtonText("Reset Password")
                        .emailLinkResetPasswordInButton(EMAIL_RESET_PASSWORD_LINK)
                        .emailLinkResetPassword(EMAIL_RESET_PASSWORD_LINK)
                        .build();
                break;

        }
        return this;
    }
}
