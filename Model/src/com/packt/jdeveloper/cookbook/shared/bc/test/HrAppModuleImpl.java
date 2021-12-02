package com.packt.jdeveloper.cookbook.shared.bc.test;

import com.packt.jdeveloper.cookbook.shared.bc.database.SQLProcedure;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtApplicationModuleImpl;


import com.packt.jdeveloper.cookbook.shared.bc.test.common.HrAppModule;

import java.math.BigDecimal;

import oracle.jbo.server.ViewLinkImpl;

import com.packt.jdeveloper.cookbook.shared.bc.database.SQLProcedure;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtApplicationModuleImpl;


import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewObjectImpl;




import java.math.BigDecimal;

import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Dec 02 13:48:57 IST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HrAppModuleImpl extends ExtApplicationModuleImpl implements HrAppModule {
    
    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ExtApplicationModuleImpl.class);
    /**
     * This is the default constructor (do not remove).
     */
    public HrAppModuleImpl() {
    }
    
    /**
     * Test method executed by the Business Component Browser.
     */
    public void testSQLProcedure() {
        Number employeeId = new Number(108);

        SQLProcedure procIn =
            new SQLProcedure("TEST_PKG.TEST_PROC_IN", this.getDBTransaction());
        procIn.setIN(employeeId);
        procIn.execute();

        SQLProcedure procInOut =
            new SQLProcedure("TEST_PKG.TEST_PROC_IN_OUT", this.getDBTransaction());
        procInOut.setINOUT(employeeId, Types.NUMERIC);
        procInOut.execute();
        Number managerId =
            procInOut.getOUT(1) != null ? new Number(((BigDecimal)procInOut.getOUT(1)).intValue()) :
            null;
        LOGGER.info("managerId ==> " + managerId);

        SQLProcedure funcIn =
            new SQLProcedure("TEST_PKG.TEST_FUNC_IN", this.getDBTransaction());
        funcIn.setIN(managerId);        
        funcIn.setRETURN(Types.BIGINT); // test out of sequence RETURN
        funcIn.setRETURN(Types.CHAR); // test multiple RETURNs
        funcIn.execute();
        String managerLastName = (String)funcIn.getRETURN();
        LOGGER.info("managerLastName ==> " + managerLastName);

        SQLProcedure funcInOut =
            new SQLProcedure("TEST_PKG.TEST_FUNC_IN_OUT", this.getDBTransaction());
        funcInOut.setRETURN(Types.CHAR);
        funcInOut.setINOUT(managerLastName, Types.CHAR);
        funcInOut.execute();
        String managerFirstName = (String)funcInOut.getOUT(2);
        String email = (String)funcInOut.getRETURN();
        LOGGER.info("managerFirstName ==> " + managerFirstName);
        LOGGER.info("email ==> " + email);
    }


    /**
     * Container's getter for EmployeesView1.
     * @return EmployeesView1
     */
    public ViewObjectImpl getEmployeesView1() {
        return (ViewObjectImpl) findViewObject("EmployeesView1");
    }
}
