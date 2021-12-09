package com.packt.jdeveloper.cookbook.shared.bc.test;

import com.packt.jdeveloper.cookbook.shared.bc.test.applicationModule.HRComponentsAppModuleAMFixture;
import com.packt.jdeveloper.cookbook.shared.bc.test.applicationModule.HRComponentsAppModuleAMTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.common.HRComponentsAppModule;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.ApplicationModulePoolStatisticsVO.ApplicationModulePoolStatisticsVOTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.CascadingLovsVO.CascadingLovsVOTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.DepartmentsVO.DepartmentsVOTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.EmployeeCount1VO.EmployeeCount1VOTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.EmployeesVO.EmployeesVOTest;
import com.packt.jdeveloper.cookbook.shared.bc.test.view.ManagersVO.ManagersVOTest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ApplicationModulePoolStatisticsVOTest.class, CascadingLovsVOTest.class,
                      EmployeeCount1VOTest.class, DepartmentsVOTest.class, ManagersVOTest.class, EmployeesVOTest.class,
                      HRComponentsAppModuleAMTest.class
    })
public class AllHRComponentsAppModuleTests {
    @BeforeClass
    public static void setUp() {
        HRComponentsAppModuleAMFixture fixture =
        HRComponentsAppModuleAMFixture.getInstance();
        HRComponentsAppModule _amImpl = (HRComponentsAppModule)fixture.getApplicationModule();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HRComponentsAppModuleAMFixture.getInstance().release();
    }
}
