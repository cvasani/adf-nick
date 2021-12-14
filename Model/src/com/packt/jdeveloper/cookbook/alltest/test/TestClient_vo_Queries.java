package com.packt.jdeveloper.cookbook.alltest.test;

import com.packt.jdeveloper.cookbook.hr.components.model.view.DepartmentsImpl;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

public class TestClient_vo_Queries {
    public TestClient_vo_Queries() {
        super();
    }

    public static void main(String[] args) {
        TestClient_vo_Queries testClient = new TestClient_vo_Queries();
        
        String amDef =
        "com.packt.jdeveloper.cookbook.alltest.test.HRComponentsAppModule";
        String config = "HrAppModuleLocal";
        ApplicationModule am =
        Configuration.createRootApplicationModule("com.packt.jdeveloper.cookbook.alltest.test.HRComponentsAppModule",
                                                  "HRComponentsAppModuleJDBCLocal");
        // Work with your appmodule and view object here
        //Find Department View Object Instance
        DepartmentsImpl vo = (DepartmentsImpl)am.findViewObject("Departments");
        //Execute Department query
        String[] allViewCriteriaNames = vo.getViewCriteriaManager().getAllViewCriteriaNames();
        for (String vc : allViewCriteriaNames)
        {
            System.out.println("getting given vc" + vc);
            vo.printVC(vo.getViewCriteria(vc));
            }
        vo.executeQuery();
        //Fetch the first record
        Row deptRow = vo.first();
//        printRow(vo, deptRow);
        System.out.println(deptRow.getAttribute(0));
        // Clean up resources
        Configuration.releaseRootApplicationModule(am, true);

    }
}
