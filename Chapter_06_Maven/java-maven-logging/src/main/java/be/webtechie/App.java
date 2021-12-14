package be.webtechie;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) {
        //Logger.getRootLogger().getLoggerRepository().resetConfiguration();
        initLog();

        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
    }

    private static void initLog() {
        PatternLayout logPattern = PatternLayout.newBuilder()
                .withPattern("%d{yyyyMMdd HH:mm:ss,SSS} | %-5p | [%c{1}] | %m%n")
                .build();

        // Log to the console, similar to System.out
        ConsoleAppender console = ConsoleAppender.newBuilder()
                .setName("ConsoleLogger")
                .setLayout(logPattern)
                .build();
        logger.get
        Logger.getRootLogger().addAppender(console);

        // Log to a file to store it for later reference, creating max 5 files of 10MB
        RollingFileAppender file = RollingFileAppender.newBuilder()
                .setName("FileLogger")
                .setConfiguration(new )
                .setFile("logs/app.log")
                .setl
        file.setLayout(logPattern);
        file.setThreshold(Level.INFO);
        file.setAppend(true);
        file.activateOptions();
        file.setMaxFileSize("10MB");
        file.setMaxBackupIndex(5);
        Logger.getRootLogger().addAppender(file);
    }
}
