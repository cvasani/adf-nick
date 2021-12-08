package com.packt.jdeveloper.cookbook.shared.view.beans;

import javax.faces.event.ActionEvent;

public class LongRunningTaskBean {
    public LongRunningTaskBean() {
    }

    public void longRunningTask(ActionEvent actionEvent) {
        // Add event code here...
        try {
            System.out.println("working");
            Thread.currentThread().sleep(5000);
        } catch (Exception ie) {
            // TODO: Add catch code
            ie.printStackTrace();
        }
    }
}
