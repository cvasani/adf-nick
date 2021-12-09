package com.packt.jdeveloper.cookbook.shared.view.beans;

import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;

public class queryLimitedResultsMessageBean {
    public queryLimitedResultsMessageBean() {
    }

    public String getQueryLimitedResultsMessage() {
        // Add event code here...
        return (String)ADFUtils.findOperation(
        "queryLimitedResultsMessage").execute();
    }
}
