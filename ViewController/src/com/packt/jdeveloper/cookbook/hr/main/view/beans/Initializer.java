package com.packt.jdeveloper.cookbook.hr.main.view.beans;

import com.packt.jdeveloper.cookbook.shared.bc.test.HRComponentsAppModuleImpl;
import com.packt.jdeveloper.cookbook.shared.bc.test.common.HRComponentsAppModule;
import com.packt.jdeveloper.cookbook.shared.view.taskflows.TaskFlowBaseBean;
import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;

public class Initializer extends TaskFlowBaseBean {

    public void initialize() {
        // get the application module
        HRComponentsAppModuleImpl hrComponentsAppModule =
            (HRComponentsAppModuleImpl) ADFUtils.getApplicationModuleForDataControl("HRComponentsAppModuleDataControl");
        if (hrComponentsAppModule != null) {
            // call the initializer method
            hrComponentsAppModule.prepare();
        }

        super.initialize();
    }

    @Override
    public void finalize() {
        // TODO Implement this method
        super.finalize();
    }
}
