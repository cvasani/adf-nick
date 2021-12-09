package com.packt.jdeveloper.cookbook.shared.bc.test.applicationModule;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

public class HRComponentsAppModuleAMFixture {
    private static HRComponentsAppModuleAMFixture fixture1 = new HRComponentsAppModuleAMFixture();
    private ApplicationModule _am;

    private HRComponentsAppModuleAMFixture() {
        _am =
            Configuration.createRootApplicationModule("com.packt.jdeveloper.cookbook.shared.bc.test.HRComponentsAppModule",
                                                      "HrAppModuleLocal");
    }

    public void setUp() {
    }

    public void tearDown() {
    }

    public static HRComponentsAppModuleAMFixture getInstance() {
        return fixture1;
    }

    public void release() throws Exception {
        Configuration.releaseRootApplicationModule(_am, true);
    }

    public ApplicationModule getApplicationModule() {
        return _am;
    }
}
