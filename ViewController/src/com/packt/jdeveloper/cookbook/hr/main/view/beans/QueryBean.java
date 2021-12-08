package com.packt.jdeveloper.cookbook.hr.main.view.beans;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.render.ClientEvent;
import org.apache.myfaces.trinidad.util.Service;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

public class QueryBean {
    public QueryBean() {
        super();
    }   
    
    public void onEmployeeEdit(ClientEvent clientEvent) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExtendedRenderKitService service = Service.getRenderKitService(facesContext,
    ExtendedRenderKitService.class);
    service.addScript(facesContext,
    "AdfPage.PAGE.findComponentByAbsoluteId('editEmployee').show();");
    }
}
