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
    
    //In application module implementation class
    //    Altnernate key for composite PVO
    public static void findByAlKey(ApplicationModule am){
    ViewObject vo =am.findViewObject("EmployeeDetails");
    //EmployeeDeptDetailVO has two entity objects EmpEO and
    //DeptEO. The alt key EmpDeptAltKey is defined on
    //EmployeeDeptDetailVO by selecting alt keys from both the
    // EO
    Key empDeptAltKey = new Key(new Object[] { "NKOCHHAR",
    "Executive" });
    //findByAltKey() has the following method signature :
    //findByAltKey(String keyName, Key key, int maxNumOfRows,
    //boolean skipWhere)
    RowIterator iter = vo.findByAltKey("EmployeeDetailsAltKey",empDeptAltKey, -1, false);
    BCUtils.showRowsFromIterator(iter,"datafrom laternate key");
    }
    
    
    public static Row[] findEmpByKey(ApplicationModule am , Integer empId, Integer deptId) {
    // EmployeeDeptDetails has EmployeeEO and DepartmentEO
    // as entity usages
    ViewObject vo = am.findViewObject("EmployeeDetails");
    //EmployeeId is PK in EmployeeEO and DepartmentId
    //is PK in EmployeeEO
    //Both these attributes are makrked as Keys in the
    //EmployeeDeptDetailsVO
//    Note that even if you have added more keys ADF do not complain and used only required keys from the list
    Key key = new Key(new Object[] { empId, deptId });
//    Key key = new Key(new Object[] { empId });
    //-1 implies return all matching rows
    Row row[] = vo.findByKey(key, -1);
    BCUtils.printRow(vo, row[0]);
    return row;
    }

    public static void main(String[] args) {
        String amDef = "com.packt.jdeveloper.cookbook.alltest.test.HRComponentsAppModule";
        String config = "HrAppModuleLocal";
        //Look up the application module instance
        ApplicationModule applicationModule = Configuration.createRootApplicationModule(amDef, config);
        
        //Get the view object from AM
//        ViewObject vo = applicationModule.findViewObject("Departments");
        
        //execute query
//        vo.executeQuery();
        //iterate over rows
//        while (vo.hasNext()) {
//            Row row = vo.next();
//            BCUtils.printRow(vo, row);
//        }
        //Move to first record
//        Row row = vo.first();
        //modify attributes value
//        row.setAttribute("DepartmentName", "HR Services");
        //Commit Transaction
//        applicationModule.getTransaction().commit();
        //Release AM
        
        
//        calling functions
//        findByAlKey(applicationModule);
        findEmpByKey(applicationModule,101,90);
        Configuration.releaseRootApplicationModule(applicationModule, true);
    }

}
