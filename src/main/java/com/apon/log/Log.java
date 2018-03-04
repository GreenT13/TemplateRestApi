package com.apon.log;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger("com.apon");

    public static void logError(String message) {
        logger.error(message);
    }

    public static void logError(String message, Exception e) {
        logger.error(message, e);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }
}
