

import com.epam.core.GuiComponents;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;
import testsecnarios.SeleniumPractise;
import testsecnarios.WikiSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestRunner {

    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);
    public static void main(String args[])
    {

        try
        {
            logger.info("Test execution is started" );
            Result result = JUnitCore.runClasses(SeleniumPractise.class);
            for(Failure failure : result.getFailures())
            {
                failure.getMessage();
            }
            logger.info("Test Execution is ended" );
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
            logger.info("Test Execution is ended" );
        }

    }

}
