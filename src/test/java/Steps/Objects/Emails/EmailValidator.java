package Steps.Objects.Emails;

import Steps.ObjectProject;
import Steps.ObjectUser;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@AutoValue
public abstract class EmailValidator {
    abstract String html();
    abstract ImapService actualEmail();
    abstract ReferenceEmail referenceEmail();
    @Nullable
    abstract ArrayList<ObjectUser> users();
    //1-st user is invited/recipient
    //2-nd user is owner/inviter/sender,
    @Nullable
    abstract ObjectProject project();

    public static Builder builder() {
        return new AutoValue_EmailValidator.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder html(String html);

        public abstract Builder actualEmail(ImapService actualEmail);

        public abstract Builder referenceEmail(ReferenceEmail referenceEmail);

        public abstract Builder users(ArrayList<ObjectUser> users);

        public abstract Builder project(ObjectProject project);

        public abstract EmailValidator build();
    }
}
