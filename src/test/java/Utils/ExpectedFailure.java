package Utils;

/**
 * Created by VatslauX on 09-May-17.
 */
import java.lang.annotation.*;

/**
 * Initially this is just a marker annotation to be used by a JUnit4 Test case in conjunction
 * with ExpectedTestFailure @Rule to indicate that a test is supposed to be failing
 */
// https://dzone.com/articles/allowing-junit-tests-pass-test
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface ExpectedFailure {
    // TODO: enhance by adding specific information about what type of failure expected
    //Class<!--? extends Throwable--> assertType() default Throwable.class;

    /**
     * Text based reason for marking test as ExpectedFailure
     * @return String
     */
    String reason() default "";
}