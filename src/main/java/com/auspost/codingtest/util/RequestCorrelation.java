package com.auspost.codingtest.util;

import org.slf4j.Logger;



public class RequestCorrelation {

    public static final String CORRELATION_ID_HEADER = "correlationId";


    private static final ThreadLocal<String> id = new ThreadLocal<>();


    public static void setId(String correlationId) {
        id.set(correlationId);
    }

    public static String getId() {
        return id.get();
    }
    public static void logResponse(Logger logger, String message) {
        message = message + " responded for [CorrelId: " + id.get() +" ]";
        logger.info(message);

    }
    public static void logError(Logger logger, String message) {
        message = message + " caught exception for [CorrelId: " + id.get() +"]";
        logger.error(message);

    }
}
