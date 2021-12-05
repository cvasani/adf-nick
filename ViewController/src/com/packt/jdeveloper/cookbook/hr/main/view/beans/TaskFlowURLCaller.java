package com.packt.jdeveloper.cookbook.hr.main.view.beans;

import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;

public class TaskFlowURLCaller {
    private String getURL;

    public TaskFlowURLCaller() {
        super();
    }

    public String ProgrammaticallyInvokeTaskFlow() {
        // setup task flow parameters
        Map<String, Object> parameters = new java.util.HashMap<String, Object>();
        parameters.put("taskFlowToCall", "callMethodFlowInitializer");
        // construct and return the task flow's URL
        return getTaskFlowURL("/WEBINF/programmaticallyInvokeTaskFlow.xml#programmaticallyInvokeTaskFlow", parameters);
    }

    private String getTaskFlowURL(String taskFlowSpecs, Map<String, Object> parameters) {
        // create a TaskFlowId from the task flow specification
        TaskFlowId tfid = TaskFlowId.parse(taskFlowSpecs);
        // construct the task flow URL
        String taskFlowURL = ControllerContext.getInstance().getTaskFlowURL(false, tfid, parameters);
        // remove the application context path from the URL
        FacesContext fc = FacesContext.getCurrentInstance();
        String taskFlowContextPath = fc.getExternalContext().getRequestContextPath();
        return taskFlowURL.replaceFirst(taskFlowContextPath, "");
    }

    public String callAction() {
        // Add event code here...
        return null;
    }

    public void setGetURL(String getURL) {
        this.getURL = getURL;
    }

    public String getGetURL() {
        String getURL = this.ProgrammaticallyInvokeTaskFlow();
        return getURL;
    }
}
