package Steps.Intrefaces;

import Steps.ObjectUser;
import Steps.Steps.*;

/**
 * Created by VatslauX on 28-May-17.
 */
public interface ILogin {
    public String login();
    public String login(String url);
    public String login(ObjectUser user);
    public String login(ObjectUser user, String url);
}
