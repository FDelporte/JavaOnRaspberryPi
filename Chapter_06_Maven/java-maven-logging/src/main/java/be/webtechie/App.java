package be.webtechie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        // This logging doesn't use the logger
        System.out.println("Logger Name: " + logger.getName());
        System.out.println("Level: " + logger.getLevel().toString());

        // This logging is handled by the logger
        logger.trace("Trace message");
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
    }
}
