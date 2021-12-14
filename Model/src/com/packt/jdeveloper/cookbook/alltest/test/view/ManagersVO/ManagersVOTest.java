package com.packt.jdeveloper.cookbook.alltest.test.view.ManagersVO;

import com.packt.jdeveloper.cookbook.alltest.test.applicationModule.HRComponentsAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class ManagersVOTest {
    private HRComponentsAppModuleAMFixture fixture1 = HRComponentsAppModuleAMFixture.getInstance();

    public ManagersVOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("Managers");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
