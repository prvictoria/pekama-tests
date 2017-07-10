package Steps.Objects.Emails;

public class ValidateEmailSignUp implements IEmailValidator {

    @Override
    public boolean checkEmailBody(AbstractEmail email) {
        return false;
    }
}
