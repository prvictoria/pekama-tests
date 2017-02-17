package Utils;

/**
 * Created by VatslauX on 17-Feb-17.
 */
public class Exceptions extends Exception {
    public Exceptions() { super(); }
    public Exceptions(String message) { super(message); }
    public Exceptions(String message, Throwable cause) { super(message, cause); }
    public Exceptions(Throwable cause) { super(cause); }
}