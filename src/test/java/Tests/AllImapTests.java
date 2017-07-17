package Tests;
import org.junit.FixMethodOrder;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

    @RunWith(Categories.class)
    @Categories.IncludeCategory(Tests.AllImapTests.class)
    @Suite.SuiteClasses({
            //TestsIMAP.class
    })
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class AllImapTests {

}