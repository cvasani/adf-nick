package com.packt.jdeveloper.cookbook.alltest.test.view.EmployeesVO;

import com.packt.jdeveloper.cookbook.alltest.test.applicationModule.HRComponentsAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class EmployeesVOTest {
    private HRComponentsAppModuleAMFixture fixture1 = HRComponentsAppModuleAMFixture.getInstance();

    public EmployeesVOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("Employees");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
