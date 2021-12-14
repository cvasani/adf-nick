package com.packt.jdeveloper.cookbook.alltest.test.applicationModule;

import com.bea.core.repackaged.jdt.internal.compiler.ast.ThrowStatement;

import com.packt.jdeveloper.cookbook.alltest.test.HRComponentsAppModuleImpl;
import com.packt.jdeveloper.cookbook.alltest.test.common.HRComponentsAppModule;

import org.junit.*;
import static org.junit.Assert.*;

public class HRComponentsAppModuleAMTest {
    private HRComponentsAppModuleImpl _amImpl;

    public HRComponentsAppModuleAMTest() {
    }

    @Before
    public void setUp() {
        HRComponentsAppModuleAMFixture fixture = HRComponentsAppModuleAMFixture.getInstance();
        this._amImpl = (HRComponentsAppModuleImpl)fixture
        .getApplicationModule();
//        this.setAmImpl(_amImpl);
    }

//    public void setAmImpl(HRComponentsAppModule _amImpl) {
//        this._amImpl = _amImpl;
//    }
//
//    public HRComponentsAppModule getAmImpl() {
//        return _amImpl;
//    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTestSQLProcedure() {
    }

    @Test
    public void testRemoveEmployeeFromCollection() {
    }

    @Test
    public void testSearchEmployeesUsingAdditionalCriteria() {
    }

    @Test
    public void testGetUserAuthorityLevel() {
    }

    @Test
    public void testGetUserPrincipalName() {
    }

    @Test
    public void testGetApplicationModulePoolStatistics2() {
    }

    @Test
    public void testAdjustCommission() {
    }

    @Test
    public void testPrepare() {
    }

    @Test
    public void testResetEmployees() {
        String employees = _amImpl.exportEmployees();
        assertNotNull("This should be nulll", employees);
    }
    
    @Test
    public void testExportEmployees() {
        String employees = _amImpl.exportEmployees();
        assertNull("This should be nulll", employees);
    }
}
