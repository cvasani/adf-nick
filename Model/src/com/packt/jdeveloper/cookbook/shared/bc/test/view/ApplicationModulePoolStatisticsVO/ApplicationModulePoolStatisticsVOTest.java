package com.packt.jdeveloper.cookbook.shared.bc.test.view.ApplicationModulePoolStatisticsVO;

import com.packt.jdeveloper.cookbook.shared.bc.test.applicationModule.HRComponentsAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class ApplicationModulePoolStatisticsVOTest {
    private HRComponentsAppModuleAMFixture fixture1 = HRComponentsAppModuleAMFixture.getInstance();

    public ApplicationModulePoolStatisticsVOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("ApplicationModulePoolStatistics");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
