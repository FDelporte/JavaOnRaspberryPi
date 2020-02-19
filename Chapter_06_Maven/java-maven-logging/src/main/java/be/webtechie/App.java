package be.webtechie;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args ) {
        Logger.getRootLogger().getLoggerRepository().resetConfiguration();
        initLog();

        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
    }

    private static void initLog() {
        PatternLayout logPattern = new PatternLayout("%d{yyyyMMdd HH:mm:ss,SSS} | %-5p | [%c{1}] | %m%n");

        // Log to the console, similar to System.out
        ConsoleAppender console = new ConsoleAppender();
        console.setName("ConsoleLogger");
        console.setLayout(logPattern);
        console.setThreshold(Level.DEBUG);
        console.activateOptions();
        Logger.getRootLogger().addAppender(console);

        // Log to a file to store it for later reference
        FileAppender file = new FileAppender();
        file.setName("FileLogger");
        file.setFile("app.log");
        file.setLayout(logPattern);
        file.setThreshold(Level.INFO);
        file.setAppend(true);
        file.activateOptions();
        Logger.getRootLogger().addAppender(file);
    }
}
