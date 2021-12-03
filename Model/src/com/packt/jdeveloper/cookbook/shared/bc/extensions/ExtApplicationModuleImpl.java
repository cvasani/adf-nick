package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.server.ApplicationModuleImpl;

public class ExtApplicationModuleImpl extends ApplicationModuleImpl {

    // create an ADFLogger
    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ExtApplicationModuleImpl.class);

    public ExtApplicationModuleImpl() {
        super();
        // log a trace
        LOGGER.info("ExtApplicationModuleImpl was constructed");
    }
    
    public Object getCustomData(String key) {
    // base class returns no custom data
    return null;
    }
    
    
}
