package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeDef;
import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.Variable;
import oracle.jbo.VariableValueManager;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaComponent;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewAttributeDefImpl;
import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;

public class ExtViewObjectImpl extends ViewObjectImpl {

    private static final String NEW_ROW_AT_END = "NewRowAtEnd";
    private static final String ROW_COUNT_LIMIT = "RowCountLimit";
    private static ADFLogger LOGGER = ADFLogger.createADFLogger(ExtViewObjectImpl.class);

    private Key currentRowKeyBeforeRollback = null;
    private int rangeStartBeforeRollback = 0;
    private int rangePosOfCurrentRowBeforeRollback = 0;

    public ExtViewObjectImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public ExtViewObjectImpl() {
        super();
    }


    /**
     * Recipe: Setting a View object attribute's Queryable property programmatically.
     *
     * Helper to set/unset an attribute's queriable property.
     *
     * @param attribute the attribute index
     * @param condition the condition (true/false)
     */
    protected void setQueriable(int attribute, boolean condition) {
        // get the attribute definition
        AttributeDef def = getAttributeDef(attribute);
        // set/unset only if needed
        if (def != null && def.isQueriable() != condition) {
            // set/unset queriable
            ViewAttributeDefImpl attributeDef = (ViewAttributeDefImpl) def;
            attributeDef.setQueriable(condition);
        }
    }

    /**
     * Recipe: Conditionally inserting new rows at the end of the RowSet.
     *
     * Overriden insertRow() to conditionally insert new rows at the end of the
     * RowSet based on the custom property NEW_ROW_AT_END.
     *
     * @param row, the Row to insert.
     */
    public void insertRow(Row row) {
        // check for overriden behavior based on custom property
        if ("true".equalsIgnoreCase((String) this.getProperty(NEW_ROW_AT_END))) {
            // get the last row in the RowSet
            Row lastRow = this.last();
            if (lastRow != null) {
                // get index of last row
                int lastRowIdx = this.getRangeIndexOf(lastRow);
                // insert row after the last row
                this.insertRowAtRangeIndex(lastRowIdx + 1, row);
                // set inserted row as the current row
                this.setCurrentRow(row);
            } else {
                super.insertRow(row);
            }
        } else {
            // default behavior: insert at current RowSet slot
            super.insertRow(row);
        }
    }

    /**
     * Recipe: Using findAndSetCurrentRowByKey() to set the View object currency.
     * Recipe: Restoring the current row after a transaction rollback.
     *
     * Overriden in order to allow managing read-only View objects by key.
     */
    protected void create() {
        super.create();
        // allow read-only View objects to use findByKey() methods
        this.setManageRowsByKey(true);
    }

    /**
     * Recipe: Using findAndSetCurrentRowByKey() to set the View object currency.
     *
     * Helper to refresh the view.
     */
    public void refreshView() {
        Key curRowKey = null;
        int rangePosOfCurRow = -1;
        int rangeStart = -1;

        // get and save the current row
        Row currentRow = getCurrentRow();
        // do this only if we have a current row
        if (currentRow != null) {
            // get the row information
            curRowKey = currentRow.getKey();
            rangePosOfCurRow = getRangeIndexOf(currentRow);
            rangeStart = getRangeStart();
        }

        // execute the View object query
        executeQuery();

        // if we have a current row, restore it
        if (currentRow != null) {
            setRangeStart(rangeStart);
            findAndSetCurrentRowByKey(curRowKey, rangePosOfCurRow);
        }
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     *
     * Overriden in order to restore current row after a rollback operation.
     *
     * @param TransactionEvent, the transaction event.
     */
    public void beforeRollback(TransactionEvent TransactionEvent) {
        if (isExecuted()) {
            // get the current row
            ViewRowImpl currentRow = (ViewRowImpl) getCurrentRow();
            if (currentRow != null) {
                // save the current row's key
                currentRowKeyBeforeRollback = currentRow.getKey();
                // save range start
                rangeStartBeforeRollback = getRangeStart();
                // get index of current row in range
                rangePosOfCurrentRowBeforeRollback = getRangeIndexOf(currentRow);
            }
        }
        super.beforeRollback(TransactionEvent);
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     *
     * Overriden in order to restore current row after a rollback operation.
     *
     * @param TransactionEvent, the transaction event.
     */
    public void afterRollback(TransactionEvent TransactionEvent) {
        super.afterRollback(TransactionEvent);
        // check for current row key to restore
        if (currentRowKeyBeforeRollback != null) {
            // execute View object's query
            executeQuery();
            // set start range
            setRangeStart(rangeStartBeforeRollback);
            // set current row in range
            findAndSetCurrentRowByKey(currentRowKeyBeforeRollback, rangePosOfCurrentRowBeforeRollback);
        }

        // reset
        currentRowKeyBeforeRollback = null;
    }

    /**
     * Recipe: Dynamically changing the View object's query WHERE clause.
     *
     * Overriden in order to alter the query's WHERE clause.
     *
     * @param sqlBuffer, the query SQL buffer
     * @param noUserParams, the number of bind variables in the query
     * @return
     */
    protected boolean buildWhereClause(StringBuffer sqlBuffer, int noUserParams) {

        // framework processing
        boolean appended = super.buildWhereClause(sqlBuffer, noUserParams);

        // check for a row count limit
        String rowCountLimit = (String) this.getProperty(ROW_COUNT_LIMIT);
        // if a row count limit exists, limit the query
        if (rowCountLimit != null) {
            // check to see if a WHERE clause was appended; if not, we will append it
            if (!appended) {
                // append WHERE clause
                sqlBuffer.append(" WHERE ");
                // indicate that a where clause was added
                appended = true;
            }
            // a WHERE clause was appended by the framework; just ammend it
            else {
                sqlBuffer.append(" AND ");
            }

            // add ROWNUM limit based on the pre-defined custom property
            sqlBuffer.append("(ROWNUM <= " + rowCountLimit + ")");
        }

        // a true/false indicator whether a WHERE clause was appended
        // is returned to the framework
        return appended;
    }

    protected void setBindVariableValue(Object[] bindVariables, String name, Object value) {
        // iterate all bind variables
        for (Object bindVariable : bindVariables) {
            // check for the specific bind variable name
            if (((Object[]) bindVariable)[0].toString().equals(name)) {
                // set the bind variable's new value
                ((Object[]) bindVariable)[1] = value;
                return;
            }
        }
    }

    /**
     * Recipe: Creating View Criteria programmatically.
     *
     * @param providerViewObject, a View object providing additional criteria values.
     * @param attribNames, an array of attribute names used to get the data from the provider View object
     *                     and set the criteria rows.
     *
     *  Other reference https://docs.oracle.com/middleware/1213/adf/api-reference-model/oracle/jbo/ViewCriteriaRow.html
     *  // Create and populate criteria rows to support query-by-example.
//    ViewObject empView = appMod.createViewObject("Emp", "mypackage1.EmpView");
//    ViewCriteria vc = empView.createViewCriteria();
//
//    ViewCriteriaRow vcRow1 = vc.createViewCriteriaRow();
//    ViewCriteriaItem jobItem = vcRow1.ensureCriteriaItem("Job");
//    jobItem.setOperator("LIKE");
//    jobItem.getValues().get(0).setValue("MANAGER");
//    vc.add(vcRow1);
//
//    ViewCriteriaRow vcRow2 = vc.createViewCriteriaRow();
//    ViewCriteriaItem deptnoItem = vcRow2.ensureCriteriaItem("Deptno");
//    deptnoItem.setOperator("=");
//    deptnoItem.getValues().get(0).setValue(new Integer(10));
//    vcRow2.setConjunction(vcRow2.VCROW_CONJ_NOT | vcRow2.VCROW_CONJ_AND);
//    vc.add(vcRow2);
//
//    empView.applyViewCriteria(vc);
//    empView.executeQuery();
     */
    public void searchUsingAdditionalCriteria(ViewObject providerViewObject, String[] attribNames) {
        // create the criteria
        ViewCriteria vc = this.createViewCriteria();
        // set the view criteria name
        vc.setName("searchUsingAdditionalCriteria");
        // AND with previous criteria
        vc.setConjunction(ViewCriteriaComponent.VC_CONJ_AND);

        // get criteria item data from the provider View object
        RowSetIterator it = providerViewObject.createRowSetIterator(null);
        it.reset();
        while (it.hasNext()) {
            Row providerRow = it.next();
            // add a criteria item for each attribute
            for (String attribName : attribNames) {
                try {
                    // create the criteria item
                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
                    // set the criteria item value
                    vcRow.setAttribute(attribName, providerRow.getAttribute(attribName));
                    //                    vcRow.setO
                    // add criteria item to the view criteria
                    vc.insertRow(vcRow);
                } catch (JboException e) {
                    LOGGER.severe(e);
                }
            }
        }

        // done with iterating provider View object
        it.closeRowSetIterator();

        // apply the criteria to this View object
        this.applyViewCriteria(vc, true);
        // THIS IS TO CHECK THAT HOW VC GETS APPENDED USING AND
        // REFERENCE SQL GENERATED HERE > [4277] ViewObjectImpl.buildQuery(5286) SELECT Employees.COMMISSION_PCT,         Employees.DEPARTMENT_ID,         Employees.EMAIL,         Employees.EMPLOYEE_ID,         Employees.FIRST_NAME,         Employees.HIRE_DATE,         Employees.LAST_NAME,         Employees.MANAGER_ID,         Employees.PHONE_NUMBER,         Employees.SALARY,         Employees.JOB_ID FROM EMPLOYEES Employees WHERE ( ( ( (Employees.DEPARTMENT_ID = :vc_temp_1 ) )  OR ( (Employees.EMPLOYEE_ID = :vc_temp_2 ) )  OR ( (Employees.DEPARTMENT_ID = :vc_temp_3 ) )  OR ( (Employees.EMPLOYEE_ID = :vc_temp_4 ) ) )  AND ( ( (Employees.DEPARTMENT_ID = :vc_temp_5 ) )  OR ( (Employees.EMPLOYEE_ID = :vc_temp_6 ) )  OR ( (Employees.DEPARTMENT_ID = :vc_temp_7 ) )  OR ( (Employees.EMPLOYEE_ID = :vc_temp_8 ) ) ) )
        //        vc = this.createViewCriteria();
        //        // set the view criteria name
        //        vc.setName("searchUsingAdditionalCriteria2");
        //        // AND with previous criteria
        //        vc.setConjunction(ViewCriteriaComponent.VC_CONJ_AND);
        //
        //        // get criteria item data from the provider View object
        //        it = providerViewObject.createRowSetIterator(null);
        //        it.reset();
        //        while (it.hasNext()) {
        //            Row providerRow = it.next();
        //            // add a criteria item for each attribute
        //            for (String attribName : attribNames) {
        //                try {
        //                    // create the criteria item
        //                    ViewCriteriaRow vcRow = vc.createViewCriteriaRow();
        //                    // set the criteria item value
        //                    vcRow.setAttribute(attribName, providerRow.getAttribute(attribName));
        //        //                    vcRow.setO
        //                    // add criteria item to the view criteria
        //                    vc.insertRow(vcRow);
        //                } catch (JboException e) {
        //                    LOGGER.severe(e);
        //                }
        //            }
        //        }

        this.applyViewCriteria(vc, true);
        // execute the View object's query
        this.executeQuery();
    }

    /*  Note that the technique used in the recipe does not remove the view criteria associated with
    the view object, it simply resets the values of the bind variables associated with the view
    criteria. In order to completely remove the view criteria associated with a particular view
    object, call the ViewObjectImpl method removeViewCriteria(). This method first
    unapplies the specific view criteria and then completely removes them from the view object.
    If you want to unapply the view criteria without removing them from the view object, use
    the removeApplyViewCriteriaName() method. Furthermore, you can also clear all the
    view object view criteria in effect by calling applyViewCriteria() on the view object and
    specifying null for the view criteria name. Finally, to clear any view criteria in effect, you can
    also delete all the view criteria rows from it using the remove() method. Any of the above
    calls will alter the view criteria for the lifetime of the specific view object instance until the next
    time any of these calls are invoked again. */


    public void clearCriteriaVariableValues(String[] criteriaNames) {
        // iterate all view criteria names
        for (String criteriaName : criteriaNames) {
            // get the view criteria
            ViewCriteria vc = this.getViewCriteria(criteriaName);
            if (vc != null) {
                VariableValueManager vvm = vc.ensureVariableManager();
                Variable[] variables = vvm.getVariables();
                for (Variable var : variables) {
                    vvm.setVariableValue(var, null);
                }
            }
        }
    }

    /* This recipe shows you a technique that you can use to handle case-insensitive (or
    case sensitive for that matter) searching for strings, when using view criteria for a view
    object. The framework provides various methods, such as setUpperColumns() and
    isUpperColumns(), for instance, at various view criteria levels (ViewCriteria,
    ViewCriteriaRow and ViewCriteriaItem) that can be used to construct generic helper
    methods to handle case searching. This technique can be used to allow case-insensitive
    or case-sensitive searches in your application based on some controlling user interface
    component or some application configuration option. For instance, a custom search form
    can be constructed with a checkbox component indicating whether the search will be case
    sensitive or not. */

    public void setViewCriteriaCaseInsensitive(boolean bCaseInsensitive) {
        // get all View Criteria managed by this view object
        ViewCriteria[] vcList = getAllViewCriterias();
        if (vcList != null) {
            // iterate over all view criteria
            for (ViewCriteria vc : vcList) {
                // set case-insensitive or case-sensitive as
                // indicated by the bCaseInsensitive parameter
                if (vc.isUpperColumns() != bCaseInsensitive)
                    vc.setUpperColumns(bCaseInsensitive);
            }
        }
    System.out.println("getAllViewCriterias");
    }

}
