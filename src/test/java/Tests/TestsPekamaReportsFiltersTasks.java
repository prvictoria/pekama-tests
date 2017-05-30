package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static Steps.StepsPekamaReports.deleteAllTasks;

/**
 * Created by VatslauX on 19-May-17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsPekamaReportsFiltersTasks {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Ignore
    @Test
    public void tasks_delete_all(){
        deleteAllTasks();
        rootLogger.info("Test passed");
    }
}
