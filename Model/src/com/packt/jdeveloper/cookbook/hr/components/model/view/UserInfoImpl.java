package com.packt.jdeveloper.cookbook.hr.components.model.view;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 07 17:33:39 IST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UserInfoImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public UserInfoImpl() {
    }
    /**
     * Returns the bind variable value for inEmployeeName.
     * @return bind variable value for inEmployeeName
     */
    public String getinEmployeeName() {
        return (String)getNamedWhereClauseParam("inEmployeeName");
    }

    /**
     * Sets <code>value</code> for bind variable inEmployeeName.
     * @param value value to bind as inEmployeeName
     */
    public void setinEmployeeName(String value) {
        setNamedWhereClauseParam("inEmployeeName", value);
    }
}
