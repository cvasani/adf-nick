package com.packt.jdeveloper.cookbook.shared.view;

import com.packt.jdeveloper.cookbook.shared.bc.test.UserInfoAppModuleImpl;

import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;



public class SessionInfoBean  {
    private String firstName;
    private String lastName;

    public SessionInfoBean() {
    }

    public String getFirstName() {
        if (firstName == null) {
            UserInfoAppModuleImpl userInfoAppModule =
                (UserInfoAppModuleImpl) ADFUtils.getApplicationModuleForDataControl("UserInfoAppModuleDataControl");
            firstName = userInfoAppModule.getFirstName();
        }
        return firstName;
    }

    public String getLastName() {
        if (lastName == null) {
            UserInfoAppModuleImpl userInfoAppModule =
                (UserInfoAppModuleImpl) ADFUtils.getApplicationModuleForDataControl("UserInfoAppModuleDataControl");
            lastName = userInfoAppModule.getLastName();
        }
        return lastName;
    }
}
