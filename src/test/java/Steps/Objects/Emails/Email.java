package Steps.Objects.Emails;

import Steps.ObjectUser;

import static Page.UrlStrings.EMAIL_CONFIRM_REGISTRATION_LINK;

/**
 * Created by VatslauX on 10-Jul-17.
 */
public class Email {
    private AbstractEmail abstractEmail;
    public AbstractEmail getAbstractEmail() {
        return abstractEmail;
    }
    public void setAbstractEmail(AbstractEmail abstractEmail) {
        this.abstractEmail = abstractEmail;
    }

    public Email buildEmail (EmailTypes emailType, ObjectUser user)  {
        Email email = new Email();
        this.abstractEmail = AbstractEmail.builder()
                .emailSubject("Welcome to Pekama! Just one more click")
                .emailTitle("Almost there...")
                .emailText("To finish registration, please confirm your account.")
                .emailButtonText("Complete my registration")
                .emailLinkConfirmRegistrationInButton(EMAIL_CONFIRM_REGISTRATION_LINK)
                .emailLinkConfirmRegistration(EMAIL_CONFIRM_REGISTRATION_LINK)
                .emailLinkMailTo(user.email)
                .build();
        return this;
    }
}
