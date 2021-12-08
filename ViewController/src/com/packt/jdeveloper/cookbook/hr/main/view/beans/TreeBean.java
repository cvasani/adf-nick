package com.packt.jdeveloper.cookbook.hr.main.view.beans;

import com.packt.jdeveloper.cookbook.hr.components.model.view.DepartmentsRowImpl;
import com.packt.jdeveloper.cookbook.hr.components.model.view.EmployeesRowImpl;
import com.packt.jdeveloper.cookbook.shared.view.util.JSFUtils;

import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class TreeBean {
    private RichPanelFormLayout departmentInfoPanel;
    private RichPanelFormLayout employeeInfoPanel;

    public TreeBean() {
    }

    public void setDepartmentForm(RichPanelFormLayout departmentForm) {
        this.departmentInfoPanel = departmentForm;
    }

    public RichPanelFormLayout getDepartmentForm() {
        return departmentInfoPanel;
    }

    public void setEmployeeForm(RichPanelFormLayout employeeForm) {
        this.employeeInfoPanel = employeeForm;
    }

    public RichPanelFormLayout getEmployeeForm() {
        return employeeInfoPanel;
    }

    public void onTreeNodeSelection(SelectionEvent selectionEvent) {
        // Add event code here...

        // invoke default selection listener via bindings
        JSFUtils.resolveMethodExpression("#{bindings.Departments.treeModel.makeCurrent}", Object.class,
                                         new Class[] { SelectionEvent.class }, new Object[] { selectionEvent });
        // get the tree component from the event
        RichTree richTree = (RichTree) selectionEvent.getSource();
        // make the selected row current
        RowKeySet rowKeySet = richTree.getSelectedRowKeys();
        Object key = rowKeySet.iterator().next();
        richTree.setRowKey(key);
        // get the tree node selected
        JUCtrlHierNodeBinding currentNode = (JUCtrlHierNodeBinding) richTree.getRowData();
        // show or hide the department and employee information
        // panels depending the type of node selected
        this.departmentInfoPanel.setVisible(currentNode.getCurrentRow() instanceof DepartmentsRowImpl);
        this.employeeInfoPanel.setVisible(currentNode.getCurrentRow() instanceof EmployeesRowImpl);
    }


}
