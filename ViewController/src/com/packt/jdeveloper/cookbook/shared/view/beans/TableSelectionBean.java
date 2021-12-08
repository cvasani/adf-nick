package com.packt.jdeveloper.cookbook.shared.view.beans;

import com.packt.jdeveloper.cookbook.shared.view.util.JSFUtils;

import java.util.Iterator;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class TableSelectionBean {
    public TableSelectionBean() {
    }

    public void customSelectionListner(SelectionEvent selectionEvent) {
        // Add event code here...

        // invoke makeCurrent via method expression
        JSFUtils.invokeMethodExpression("#{bindings.Employees.collectionModel.makeCurrent}", Object.class,
                                        SelectionEvent.class, selectionEvent);
        // get selected data
        RichTable table = (RichTable) selectionEvent.getSource();
//        JUCtrlHierNodeBinding selectedRowData = (JUCtrlHierNodeBinding) table.getSelectedRowData();
        RowKeySet keySet = table.getSelectedRowKeys();
//        Iterator<Object> iterator = keySet.iterator();
//        while (iterator.hasNext()) {
//            Object next = iterator.next();
//            System.out.println(next.toString());
//        }
            
//        // process selected data
//        String[] attrbNames = selectedRowData.getAttributeNames();
//        for (String attrbName : attrbNames) {
//            Object attrbValue = selectedRowData.getAttribute(attrbName);
//            System.out.println("attrbName: " + attrbName + ", attrbValue: " + attrbValue);
//        }
    }
}
