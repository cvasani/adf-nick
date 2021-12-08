package com.packt.jdeveloper.cookbook.shared.view;

import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class SelectManyShuttleBean {
    public SelectManyShuttleBean() {
    }

    public void onEmployeeShuttleInit(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        JUCtrlListBinding employeesList = (JUCtrlListBinding) ADFUtils.findCtrlBinding("Employees");
        employeesList.clearSelectedIndices();

    }

    public void onSelectManyShuttleDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        if (DialogEvent.Outcome
                       .ok
                       .equals(dialogEvent.getOutcome())) {
            JUCtrlListBinding employeesList = (JUCtrlListBinding) ADFUtils.findCtrlBinding("Employees");
            Object[] employeeIds = employeesList.getSelectedValues();
            for (Object employeeId : employeeIds) {
                // handle selection
                System.out.println("employeeId");
                    

            }
        }
    }
}
