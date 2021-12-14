package com.packt.jdeveloper.cookbook.alltest.test.view.EmployeeCount1VO;

import com.packt.jdeveloper.cookbook.alltest.test.applicationModule.HRComponentsAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class EmployeeCount1VOTest {
    private HRComponentsAppModuleAMFixture fixture1 = HRComponentsAppModuleAMFixture.getInstance();

    public EmployeeCount1VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("EmployeeCount1");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
