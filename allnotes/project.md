======== Reading 
One more step that is required to complete the configuration is to use the -Djbo.debugoutput=adflogger and -Djbo.adflogger.level=FINEST options

com.packt.jdeveloper.cookbook.shared.bc.extensions

com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtEntityImpl
com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtEntityDefImpl

com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewObjectImpl
com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewRowImpl

com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtApplicationModuleImpl

com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtDatabaseTransactionFactory
com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtDBTransactionImpl2
connect to local mbean server : https://dkleppinger.blogspot.com/2016/03/how-to-connect-to-localhost-jdeveloper.html
programmatic way to create view or search view : useful for f:query : https://programmersnotes.com/2018/01/populate-rows-view-object-programmatically-without-sql/
logger : protected ADFLogger logger = ADFLogger.createADFLogger(this.getClass());
How to populate a list from a different data control : List binding creation in the UI : https://www.youtube.com/watch?v=ary2htJ1IZI
PLSQL PL/SQL based refcursor : calling PL/SQL returning refcursor and using it as a iterator : https://adfsolutions.anirbanblogs.com/2018/10/advanced-programmatic-view-object-in.html
Dynamic Query : search Example : https://adfsolutions.anirbanblogs.com/2018/10/efficient-dynamic-query-in-adf.html
Taskflow URL format : "/Chapter03/faces/adf.task-flow?adf.tfDoc=%2FWEBINF%2FprogrammaticallyInvokeTaskFlow.xml&adf.tfId=programmaticallyInvokeTaskFlow&taskFlowToCall=calTaskFlowInitializer"
Rajdeep Chaudhari : https://gist.github.com/rschaudhary/766a0d9b5c5a52bfbdbc
Disable Security while testing AM or doing any testing with secured framework : https://community.oracle.com/tech/developers/discussion/1006071/test-appmodule-without-security
    in the adf-config.xml file you find
    <sec:JaasSecurityContext initialContextFactoryClass="oracle.adf.share.security.JAASInitialContextFactory"
    jaasProviderClass="oracle.adf.share.security.providers.jps.JpsSecurityContext"
    authorizationEnforce="false"
    authenticationRequire="true"/>

    Just set the authorizationEnforce and authenticationRequire property to "false". Note that ADF BC does not have programmatic access to this

