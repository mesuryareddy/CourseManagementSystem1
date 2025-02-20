package util;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    public static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            // Ensure the logs directory exists
            File logsDir = new File("logs");
            if (!logsDir.exists()) {
                logsDir.mkdirs(); // Create the directory if it doesn't exist
            }

            LogManager.getLogManager().reset(); // Reset default handlers

            // Create console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomLogsFormatter());
            consoleHandler.setLevel(Level.INFO);

            // Create file handler
            FileHandler fileHandler = new FileHandler("logs/app.log", true);
            fileHandler.setFormatter(new CustomLogsFormatter());
            fileHandler.setLevel(Level.INFO);

            // Add handlers
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
