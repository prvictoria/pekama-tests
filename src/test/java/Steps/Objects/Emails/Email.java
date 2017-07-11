package Steps.Objects.Emails;

import Steps.ObjectUser;

import static Pages.UrlStrings.EMAIL_CONFIRM_REGISTRATION_LINK;

final public class Email {
    private AbstractEmail abstractEmail;
    public AbstractEmail getAbstractEmail() {
        return abstractEmail;
    }
    public void setAbstractEmail(AbstractEmail abstractEmail) {
        this.abstractEmail = abstractEmail;
    }

    public Email buildEmail (EmailTypes emailType, ObjectUser user)  {
        new Email();
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
        return this;
    }
}
