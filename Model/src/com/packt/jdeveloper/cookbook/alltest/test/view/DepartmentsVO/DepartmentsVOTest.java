package com.packt.jdeveloper.cookbook.alltest.test.view.DepartmentsVO;

import com.packt.jdeveloper.cookbook.alltest.test.applicationModule.HRComponentsAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class DepartmentsVOTest {
    private HRComponentsAppModuleAMFixture fixture1 = HRComponentsAppModuleAMFixture.getInstance();

    public DepartmentsVOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("Departments");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
