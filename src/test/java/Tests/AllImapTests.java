package Tests;

import Steps.MessagesIMAP;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import static Page.UrlConfig.setEnvironment;

/**
 * Created by VatslauX on 20-Mar-17.
 */
//    @RunWith(Categories.class)
//    @Categories.IncludeCategory(Tests.AllImapTests.class)
    @Suite.SuiteClasses({
            TestsIMAP.class
    })
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class AllImapTests extends AllTestsRunner {

}