package com.packt.jdeveloper.cookbook.shared.bc.utilviews;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.jbo.ApplicationModule;
import oracle.jbo.AttributeDef;
import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.StructureDef;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaItem;
import oracle.jbo.ViewCriteriaItemValue;
import oracle.jbo.ViewObject;
import oracle.jbo.common.ViewCriteriaRowImpl;
import oracle.jbo.server.ViewObjectImpl;

public class BCUtils {
    public BCUtils() {
        super();
    }

    public static void printRow(ViewObject vo, Row r) {
        String viewObjName = vo.getName();
        System.out.println("Printing attribute for a row in VO '" + viewObjName + "'");
        StructureDef def = r.getStructureDef();
        StringBuilder sb = new StringBuilder();
        int numAttrs = def.getAttributeCount();
        AttributeDef[] attrDefs = def.getAttributeDefs();
        for (int z = 0; z < numAttrs; z++) {
            Object value = r.getAttribute(z);
            sb.append(z > 0 ? " " : "")
              .append(attrDefs[z].getName())
              .append("=")
              .append(value == null ? "<null>" : value)
              .append(z < numAttrs - 1 ? "\n" : "");
        }
        System.out.println(sb.toString());
    }

    public static void printVC(ViewCriteria vc) {
        List list = vc.getRows();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            ViewCriteriaRowImpl row = (ViewCriteriaRowImpl) iter.next();
            ViewCriteriaItem[] vcitems = row.getCriteriaItemArray();
            for (ViewCriteriaItem vcitem : vcitems) {
                if (vcitem != null) {
                    System.out.println("[ VC Item :" + vcitem.getColumnName() + "Operator " + vcitem.getOperator() +
                                       " Value: " + vcitem.getValue() + " ]");
                    if (vcitem.getValueCount() > 1) {
                        ArrayList<ViewCriteriaItemValue> values = vcitem.getValues();
                        for (ViewCriteriaItemValue vcItemValue : values)
                            System.out.println(" [[ Multi select value :" + vcItemValue.getValue() + " ]]");
                    } else if ((vcitem.getValue()) instanceof ViewCriteria) {
                        System.out.println("<Nested VC is found...>");
                        //Call recursively
                        printVC((ViewCriteria) vcitem.getValue());
                    }
                }
            }
        }
    }

    /**
     * This is a custom method defined in an application module.
     * It sets the query on a view object at runtime
     */
    public static void modifyQueryDynamically(ViewObjectImpl voImpl, String newQueryString) {
        // FULLSQL_MODE_AUGMENTATION flag that tells framework that
        // you want to keep the query set through setQuery() for
        // the rest of the execution using this view object
        voImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        closeAllRowsets(voImpl);
        //clear the previous WHERE clause and parameters
        voImpl.setWhereClause(null);
        voImpl.setWhereClauseParams(null);
        //Query can be set fully in expert mode
        voImpl.setQuery(newQueryString);
    }

    /**
     * Close all row set when query changes
     * to avoid the orphan Rowset
     */
    public static void closeAllRowsets(ViewObject vo) {
        RowSet[] rowSets = vo.getRowSets();
        if (rowSets == null || rowSets.length == 0) {
            return;
        }
        for (RowSet rowSet : rowSets) {
            rowSet.closeRowSet();
        }
    }

    public static void showRowsfromPVO(ViewObject vo, String comment) {
        System.out.println("+++++++++++ " + comment + " ++++++++++++");
        vo.reset();
        while (vo.hasNext()) {
            Row r = vo.next();
            String fn = (String) r.getAttribute("FirstName");
            String ln = (String) r.getAttribute("LastName");
            Boolean sel = (Boolean) r.getAttribute("MarkedForOperation");
            System.out.println(fn + " " + ln + " [" + sel + "]");
        }
    }

    public static void showRowsFromRowSet(RowSet rowset, String comment) {
        System.out.println("+++++++++++ " + comment + " ++++++++++++");
        rowset.reset();
        while (rowset.hasNext()) {
            Row r = rowset.next();
            String fn = (String) r.getAttribute("FirstName");
            String ln = (String) r.getAttribute("LastName");
            Boolean sel = (Boolean) r.getAttribute("MarkedForOperation");
            System.out.println(fn + " " + ln + " [" + sel + "]");
        }
    }

    public static void showRowsFromIterator(RowIterator rowiterator, String comment) {
        System.out.println("+++++++++++ " + comment + " ++++++++++++");
        rowiterator.reset();
        while (rowiterator.hasNext()) {
            Row r = rowiterator.next();
            String fn = (String) r.getAttribute("FirstName");
            String ln = (String) r.getAttribute("LastName");
            String sel = (String) r.getAttribute("DepartmentName");
            System.out.println(fn + " " + ln + " [" + sel + "]");
        }
    }
    
    
    /**
     * Simplifies calling a stored function with bind variables
     *
     * You can use the NUMBER, DATE, and VARCHAR2 constants in this
     * class to indicate the function return type for these three common types,
     * otherwise use one of the JDBC types in the java.sql.Types class.
     *
     * NOTE: If you want to invoke a stored procedure without any bind variables
     * ====  then you can just use the basic getDBTransaction().executeCommand()
     *
     * @param sqlReturnType JDBC datatype constant of function return value
     * @param stmt stored function statement
     * @param bindVars Object array of parameters
     * @return function return value as an Object
     */
    protected Object callStoredFunction(ViewObjectImpl vo , int sqlReturnType, String stmt, Object[] bindVars) {
        CallableStatement st = null;
        try {
            st = vo.getDBTransaction().createCallableStatement("begin ? := " + stmt + "; end;", 0);
            st.registerOutParameter(1, sqlReturnType);
            if (bindVars != null) {
                for (int z = 0; z < bindVars.length; z++) {
                    st.setObject(z + 2, bindVars[z]);
                }
            }
            st.executeUpdate();
            return st.getObject(1);
        } catch (SQLException e) {
            throw new JboException(e);
        }
    }
}
