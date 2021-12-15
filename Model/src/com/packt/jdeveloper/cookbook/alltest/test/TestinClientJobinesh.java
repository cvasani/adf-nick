package com.packt.jdeveloper.cookbook.alltest.test;

import com.packt.jdeveloper.cookbook.shared.bc.utilviews.BCUtils;

import oracle.jbo.*;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.*;
import oracle.jbo.domain.Number;

public class TestinClientJobinesh {
    public TestinClientJobinesh() {
        super();
    }

    public static void main(String[] args) {
        String amDef = "com.packt.jdeveloper.cookbook.alltest.test.HRComponentsAppModule";
        String config = "HrAppModuleLocal";
        //Look up the application module instance
        ApplicationModule applicationModule = Configuration.createRootApplicationModule(amDef, config);
        
        //Get the view object from AM
        ViewObject vo = applicationModule.findViewObject("Departments");
        //execute query
        vo.executeQuery();
        //iterate over rows
        while (vo.hasNext()) {
            Row row = vo.next();
            BCUtils.printRow(vo, row);
        }
        //Move to first record
        Row row = vo.first();
        //modify attributes value
//        row.setAttribute("DepartmentName", "HR Services");
        //Commit Transaction
//        applicationModule.getTransaction().commit();
        //Release AM
        Configuration.releaseRootApplicationModule(applicationModule, true);
    }

}
