import org.apache.log4j.*;

public class Logging {
    private static Logger logger = Logger.getLogger(Logging.class);
    static FileAppender fileAppender = new FileAppender();

    public static void info(String message){
        fileAppender.setFile("application.log");
        fileAppender.setThreshold(Level.INFO);
        fileAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m\n"));
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);
        logger.info(message);
    }

    public static void error(String message) {
        fileAppender.setFile("application.log");
        fileAppender.setThreshold(Level.ERROR);
        fileAppender.setLayout(new PatternLayout("%d{dd/MM/yyyy HH-mm-ss} %-5p %c{1}:%L - %m\n"));
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);
        logger.error(message);
    }
}
