package Steps.Objects.Emails;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class AbstractEmail {
    @Nullable
    public abstract String emailSubject();
    @Nullable
    public abstract String emailTitle();
    @Nullable
    public abstract String emailText();
    @Nullable
    public abstract String emailButtonText();
    @Nullable
    public abstract String emailButtonLinkText();
    @Nullable
    public abstract String emailLinkConfirmRegistrationInButton();
    @Nullable
    public abstract String emailLinkConfirmRegistration();
    @Nullable
    public abstract String emailLinkResetPasswordInButton();
    @Nullable
    public abstract String emailLinkResetPassword();
    @Nullable
    public abstract String emailLinkBackToPekama();
    @Nullable
    public abstract String emailLinkUnSubscribe();
    @Nullable
    public abstract String emailLinkPathPart1();
    @Nullable
    public abstract String emailLinkPathPart2();
    @Nullable
    public abstract String emailLinkPathPart3();
    @Nullable
    public abstract String emailLinkMailTo();

    public static Builder builder() {
        return new AutoValue_AbstractEmail.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder emailSubject(@Nullable String emailSubject);

        public abstract Builder emailTitle(@Nullable String emailTitle);

        public abstract Builder emailText(@Nullable String emailText);

        public abstract Builder emailButtonText(@Nullable String emailButtonText);

        public abstract Builder emailButtonLinkText(@Nullable String emailButtonLinkText);

        public abstract Builder emailLinkConfirmRegistrationInButton(@Nullable String emailLinkConfirmRegistrationInButton);

        public abstract Builder emailLinkConfirmRegistration(@Nullable String emailLinkConfirmRegistration);

        public abstract Builder emailLinkMailTo(@Nullable String emailLinkMailTo);

        public abstract Builder emailLinkResetPasswordInButton(@Nullable String emailLinkResetPasswordInButton);

        public abstract Builder emailLinkResetPassword(@Nullable String emailLinkResetPassword);

        public abstract Builder emailLinkBackToPekama(@Nullable String emailLinkBackToPekama);

        public abstract Builder emailLinkUnSubscribe(@Nullable String emailLinkUnSubscribe);

        public abstract Builder emailLinkPathPart1(@Nullable String emailLinkPathPart1);

        public abstract Builder emailLinkPathPart2(@Nullable String emailLinkPathPart2);

        public abstract Builder emailLinkPathPart3(@Nullable String emailLinkPathPart3);

        public abstract AbstractEmail build();
    }




}
