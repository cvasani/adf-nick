package com.packt.jdeveloper.cookbook.hr.components.model.view;

import com.packt.jdeveloper.cookbook.hr.components.model.entities.DepartmentImpl;


import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewRowImpl;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Dec 03 14:50:12 IST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsRowImpl extends ExtViewRowImpl {

    public static final int ENTITY_DEPARTMENT = 0;
    private static final int MAX_EMPLOYEES_UPDATE = 5;

    public boolean isAttributeUpdateable(int prameterIdx) {
        // get the number of employees for the specific department
        int departmentEmployeeCount = this.getEmployees() != null ? this.getEmployees().getRowCount() : 0;
        // set all attributes to non-updatable if the department has more than a specified number of employees
        return (departmentEmployeeCount > MAX_EMPLOYEES_UPDATE) ? false : super.isAttributeUpdateable(prameterIdx);
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        DepartmentId,
        DepartmentName,
        ManagerId,
        LocationId,
        IsNewRow,
        Employees;
        private static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        protected int index() {
            return DepartmentsRowImpl.AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return DepartmentsRowImpl.AttributesEnum.firstIndex() + DepartmentsRowImpl.AttributesEnum
                                                                                      .staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = DepartmentsRowImpl.AttributesEnum.values();
            }
            return vals;
        }

        protected static DepartmentsRowImpl.AttributesEnum getDepartmentId() {
            return DepartmentId;
        }

        protected static DepartmentsRowImpl.AttributesEnum getDepartmentName() {
            return DepartmentName;
        }

        protected static DepartmentsRowImpl.AttributesEnum getManagerId() {
            return ManagerId;
        }

        protected static DepartmentsRowImpl.AttributesEnum getLocationId() {
            return LocationId;
        }

        protected static DepartmentsRowImpl.AttributesEnum getEmployees() {
            return Employees;
        }

        protected static void setVals(DepartmentsRowImpl.AttributesEnum[] vals) {
            DepartmentsRowImpl.AttributesEnum.vals = vals;
        }

        protected static DepartmentsRowImpl.AttributesEnum[] getVals() {
            return vals;
        }
    }


    public static final int DEPARTMENTID = AttributesEnum.DepartmentId.index();
    public static final int DEPARTMENTNAME = AttributesEnum.DepartmentName.index();
    public static final int MANAGERID = AttributesEnum.ManagerId.index();
    public static final int LOCATIONID = AttributesEnum.LocationId.index();
    public static final int ISNEWROW = AttributesEnum.IsNewRow.index();
    public static final int EMPLOYEES = AttributesEnum.Employees.index();

    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsRowImpl() {
    }


    /**
     * Gets Department entity object.
     * @return the Department
     */
    public DepartmentImpl getDepartment() {
        return (DepartmentImpl) getEntity(ENTITY_DEPARTMENT);
    }

    /**
     * Gets the attribute value for DEPARTMENT_ID using the alias name DepartmentId.
     * @return the DEPARTMENT_ID
     */
    public oracle.jbo.domain.Number getDepartmentId() {
        return (oracle.jbo.domain.Number) getAttributeInternal(DEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as attribute value for DEPARTMENT_ID using the alias name DepartmentId.
     * @param value value to set the DEPARTMENT_ID
     */
    public void setDepartmentId(oracle.jbo.domain.Number value) {
        setAttributeInternal(DEPARTMENTID, value);
    }

    /**
     * Gets the attribute value for DEPARTMENT_NAME using the alias name DepartmentName.
     * @return the DEPARTMENT_NAME
     */
    public String getDepartmentName() {
        return (String) getAttributeInternal(DEPARTMENTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for DEPARTMENT_NAME using the alias name DepartmentName.
     * @param value value to set the DEPARTMENT_NAME
     */
    public void setDepartmentName(String value) {
        setAttributeInternal(DEPARTMENTNAME, value);
    }

    /**
     * Gets the attribute value for MANAGER_ID using the alias name ManagerId.
     * @return the MANAGER_ID
     */
    public oracle.jbo.domain.Number getManagerId() {
        return (oracle.jbo.domain.Number) getAttributeInternal(MANAGERID);
    }

    /**
     * Sets <code>value</code> as attribute value for MANAGER_ID using the alias name ManagerId.
     * @param value value to set the MANAGER_ID
     */
    public void setManagerId(oracle.jbo.domain.Number value) {
        setAttributeInternal(MANAGERID, value);
    }

    /**
     * Gets the attribute value for LOCATION_ID using the alias name LocationId.
     * @return the LOCATION_ID
     */
    public Number getLocationId() {
        return (Number) getAttributeInternal(LOCATIONID);
    }

    /**
     * Sets <code>value</code> as attribute value for LOCATION_ID using the alias name LocationId.
     * @param value value to set the LOCATION_ID
     */
    public void setLocationId(Number value) {
        setAttributeInternal(LOCATIONID, value);
    }

    /**
     * Gets the attribute value for IS_NEW_ROW using the alias name IsNewRow.
     * @return the IS_NEW_ROW
     */
    public Boolean getIsNewRow() {
        // return true if the row status is New
        return Row.STATUS_NEW == this.getDepartment().getEntityState();
//        return (Boolean) getAttributeInternal(ISNEWROW);
    }

    /**
     * Sets <code>value</code> as attribute value for IS_NEW_ROW using the alias name IsNewRow.
     * @param value value to set the IS_NEW_ROW
     */
    public void setIsNewRow(Boolean value) {
        setAttributeInternal(ISNEWROW, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link Employees.
     */
    public RowIterator getEmployees() {
        return (RowIterator) getAttributeInternal(EMPLOYEES);
    }


}

