package com.packt.jdeveloper.cookbook.shared.bc.test.common.serviceinterface;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import oracle.jbo.service.errors.ServiceException;

import oracle.webservices.annotations.PortableWebService;
import oracle.webservices.annotations.SDODatabinding;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Dec 04 13:52:50 IST 2021
// ---------------------------------------------------------------------
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, style = SOAPBinding.Style.DOCUMENT)
@PortableWebService(targetNamespace = "/com/packt/jdeveloper/cookbook/shared/bc/test/common/",
                    name = "HRComponentsAppModuleService",
                    wsdlLocation =
                    "com/packt/jdeveloper/cookbook/shared/bc/test/common/serviceinterface/HRComponentsAppModuleService.wsdl")
@SDODatabinding(schemaLocation =
                "com/packt/jdeveloper/cookbook/shared/bc/test/common/serviceinterface/HRComponentsAppModuleService.xsd")
public interface HRComponentsAppModuleService {
    public static final String NAME =
        "{/com/packt/jdeveloper/cookbook/shared/bc/test/common/}HRComponentsAppModuleService";

    /**
     * Exported method adjustCommission from HRComponentsAppModule.
     */
    @WebMethod(action = "/com/packt/jdeveloper/cookbook/shared/bc/test/common/adjustCommission",
               operationName = "adjustCommission")
    @RequestWrapper(targetNamespace = "/com/packt/jdeveloper/cookbook/shared/bc/test/common/types/",
                    localName = "adjustCommission")
    @ResponseWrapper(targetNamespace = "/com/packt/jdeveloper/cookbook/shared/bc/test/common/types/",
                     localName = "adjustCommissionResponse")
    void adjustCommission(@WebParam(mode = WebParam.Mode.IN, name = "commissionPctAdjustment")
                          BigDecimal commissionPctAdjustment) throws ServiceException;
}
