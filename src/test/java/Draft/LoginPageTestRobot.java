package Draft;

/**
 * Created by Viachaslau_Balashevi on 1/22/2017.
 */
public interface LoginPageTestRobot<T> {

    public LoginPageTestRobot login(final String name);

    public LoginPageTestRobot password(final String password);

    public T submit();
    //who? what? how?

}
