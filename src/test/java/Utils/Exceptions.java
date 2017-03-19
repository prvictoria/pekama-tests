package Utils;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Exceptions extends Exception {
    public Exceptions() { super(); }
    public Exceptions(String message) { super(message); }
    public Exceptions(String message, Throwable cause) { super(message, cause); }
    public Exceptions(Throwable cause) { super(cause); }
}