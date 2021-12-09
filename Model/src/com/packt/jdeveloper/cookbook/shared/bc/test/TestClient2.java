package com.packt.jdeveloper.cookbook.shared.bc.test;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

public class TestClient2 {
    public TestClient2() {
        super();
    }

    public static void main(String[] args) {
        TestClient2 testClient = new TestClient2();
        
        String amDef =
        "com.packt.jdeveloper.cookbook.shared.bc.test.HRComponentsAppModule";
        String config = "HrAppModuleLocal";
        ApplicationModule am =
        Configuration.createRootApplicationModule("com.packt.jdeveloper.cookbook.shared.bc.test.HRComponentsAppModule",
                                                  "HrAppModuleLocal");
        // Work with your appmodule and view object here
        //Find Department View Object Instance
        ViewObject vo = am.findViewObject("Departments");
        //Execute Department query
        vo.executeQuery();
        //Fetch the first record
        Row deptRow = vo.first();
//        printRow(vo, deptRow);
        System.out.println(deptRow.getAttribute(0));
        // Clean up resources
        Configuration.releaseRootApplicationModule(am, true);

    }
}
