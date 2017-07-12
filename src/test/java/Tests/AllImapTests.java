package Tests;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

/**
 * Created by VatslauX on 20-Mar-17.
 */
//    @RunWith(Categories.class)
//    @Categories.IncludeCategory(Tests.AllImapTests.class)
    @Suite.SuiteClasses({
            //TestsIMAP.class
    })
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class AllImapTests {

}