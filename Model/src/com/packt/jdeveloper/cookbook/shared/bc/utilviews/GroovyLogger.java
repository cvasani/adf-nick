package com.packt.jdeveloper.cookbook.shared.bc.utilviews;

import oracle.adf.share.logging.ADFLogger;

public class GroovyLogger {
    public GroovyLogger() {
        super();
    }
    
    private static ADFLogger LOGGER =
    ADFLogger.createADFLogger(GroovyLogger.class);
    
    public static <T> T log(String groovyExpression, T data) {
    LOGGER.info("GroovyLogger ==> Expression: " +
    groovyExpression + ", Data: " + data);
    return data;
    }
}
