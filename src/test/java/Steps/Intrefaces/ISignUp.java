package Steps.Intrefaces;

import Steps.ObjectUser;
import Steps.Steps.*;

/**
 * Created by VatslauX on 29-May-17.
 */
public interface ISignUp {
    Boolean submitSignUp(ObjectUser user);
    Boolean validateSubmitSignUp(Boolean submittedDataIsValid);
}