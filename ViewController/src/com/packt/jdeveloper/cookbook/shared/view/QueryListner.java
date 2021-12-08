package com.packt.jdeveloper.cookbook.shared.view;

import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;

import com.packt.jdeveloper.cookbook.shared.view.util.JSFUtils;

import java.util.List;

import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.event.QueryOperationEvent;
import oracle.adf.view.rich.model.AttributeCriterion;

public class QueryListner {
    public QueryListner() {
    }

    public void customQueryListner(QueryEvent queryEvent) {
        // Add event code here...
        // handle the presence of certain query criterion data
        List criteria = queryEvent.getDescriptor()
                                  .getConjunctionCriterion()
                                  .getCriterionList();
        for (int i = 0; i < criteria.size(); i++) {
            AttributeCriterion criterion = (AttributeCriterion) criteria.get(i);
            // do some special processing when a particular
            // criterion was used
            if ("SomeCriterionName".equals(criterion.getAttribute().getName()) &&
                criterion.getValues().get(0) != null) {
                // do something, for instance a rollback
                ADFUtils.findOperation("Rollback").execute();
                break;
            }
        }

    }

    public void queryOperationListner(QueryOperationEvent queryOperationEvent) {
        // Add event code here...
        // handle RESET operation only
        if (QueryOperationEvent.Operation.RESET.name()
        .equalsIgnoreCase(queryOperationEvent.getOperation()
        .name())) {
        // execute custom reset
        ADFUtils.findOperation("resetEmployees").execute();
        } else {
        // default framework handling for all other
        // af:query operations
        JSFUtils.invokeMethodExpression(
        "#{bindings.EmployeesCriteriaQuery.processQueryOperation}",
        Object.class, QueryOperationEvent.class,
        queryOperationEvent);
        }
    }
}
