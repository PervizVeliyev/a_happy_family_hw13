import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Logging {
    private static final Logger logger = (Logger) LogManager.getLogger(Logging.class.getName());

    public static void info(String message){
        logger.info(message);
    }
    public static void error(String message) {logger.error(message);}
}
