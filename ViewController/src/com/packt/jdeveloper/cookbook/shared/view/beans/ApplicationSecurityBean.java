package com.packt.jdeveloper.cookbook.shared.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

public class ApplicationSecurityBean {
    public ApplicationSecurityBean() {
    }

    public void onApplicationSecurity(ActionEvent actionEvent) {
        // Add event code here...
        // check for user having the 'AllowEmployeeChanges' role
        if (ADFContext.getCurrent().getSecurityContext()
        .isUserInRole("AllowEmployeeChanges")) {
        FacesContext context =
        FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "User is allowed to edit the employee data.", null));
        }
    }
}
