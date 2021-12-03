HTTP404 : TechTeamNo1.5

Custom Listener for ADF Table : https://rohanwalia.blogspot.com/2015/01/custom-selection-listener-for-adf-table.html
ADF util for expression evaluation : https://oracle-adf.com/learn-oracle-adf/tips/ADFUtil-class-to-evaluate-set-and-invoke-EL-expressions/
Dynamic way of creating a LOV binding using managed bean : https://adfpractice-fedor.blogspot.com/2014/02/dynamic-lov-binding.html?m=0
validation of attribute values based on custom logic : dynamic way of handling the value validation exceptions : https://andrejusb.blogspot.com/2011/11/custom-exception-registration-for-adf.html
Transient attribute in EO for color as Color object can not be stored in DB what you need a transient attribute for such use-cases : https://github.com/agawish/ADF-Faces-Cookbook
ADF Utils : https://github.com/agawish/ADF-Faces-Cookbook
Multiselect LOV using programming and mbean methods : good example but using where clause : https://oracleerplearning.blogspot.com/2020/04/multi-select-lov-feature-in-adf-and.html
+ both above and below combination gives you best IN clause filtering
ViewCriteria IN Operator in the ADF : https://middlewarebylink.wordpress.com/2012/05/26/how-to-use-a-view-criteria-with-the-in-operator/
+
IN Operator using programming : https://adfnbpel.wordpress.com/2012/07/05/adf-view-criteria-with-in-clause/
Cascading LOV and Discussion : https://community.oracle.com/tech/developers/discussion/3591064/multiselect-cascading-lovs-in-view-criteria
Advance View Criteria VC using binding variables : https://andrejusb.blogspot.com/2013/07/advanced-view-criteria-implementation.html
Contexts in ADF ADFFaces vs FacesContext vs DataContext , Binding : best : awesome : https://palashdabkaraadf12c.blogspot.com/2017/02/difference-between-bindingcontext.html
ADF Logger :code template :  protected ADFLogger logger = ADFLogger.createADFLogger(this.getClass());
ADF Sample Codes  : https://www.oracle.com/downloads/samplecode/jdeveloper-adf-samplecode-downloads.html
ADF OTN : https://www.oracle.com/database/technologies/developer-tools/adf/
Disble the content compression to check the ids of the adf faces while debugging : https://tompeez.wordpress.com/2020/06/04/jdeveloper-afquery-hide-fields-from-add-fields-button/
CSS debugging or CSS level debugging : https://tompeez.wordpress.com/2020/06/04/jdeveloper-afquery-hide-fields-from-add-fields-button/
Disgnostics : ADF Logger , Facecontext breakpoints , ADF Application Disgnostics logging . all three options are awesome.
ADF Logger and Code template : https://multikoop.blogspot.com/2013/02/jdeveloper-code-template-for-adflogger.html
Transient logger : https://youtu.be/o-2r-Max3II?t=424 : awsome
AWESOME SERIES by Duncan Mills : Diagnostic initializer and see entire pageflowScope : https://blogs.oracle.com/groundside/post/adventures-in-adf-logging-part-1
Exact Query execution and logging in debugging : https://techutils.in/blog/2015/04/10/see-exact-query-execution-in-oracle-adf/
OBE Oracle by Example Jdeveloper and ADF : https://docs.oracle.com/cd/E53569_01/tutorials/toc.htm
portability issue : https://adfexpert.blogspot.com/2019/07/solve-javalangclassnotfoundexception.html
JUNIT for Business Component that we can try out while doing a development : https://www.oracle.com/technical-resources/articles/adf/essentials-part5.html
Learn Oracle ADF Slides : https://oracle-adf.com/website/docs/Learn_Oracle_ADF/04-Oracle_11g_ADF_University/Oracle_11g_ADF_I/10Troubleshooting/img31
Trouble Shoot Business Services : https://oracle-adf.com/website/docs/Learn_Oracle_ADF/04-Oracle_11g_ADF_University/Oracle_11g_ADF_I/10Troubleshooting/img2
<Java_Home>/jre/lib/logging.properties  : java.util.logging.ConsoleHandler.level = INFO com.sun.faces.level=FINE 
com.sun.faces.lifecycle.LifecycleImpl = FINE 
A module filter is an optional comma-delimited list of non-case-sensitive strings. You can define a filter, so only messages that match the filter criteria get logged. Use of a wildcard (%) is supported. For example: MODULE=store%, jtf%.
Project Properties dialog box, click Run/Debug/Profile and create a new run configuration, for example, named ADF Debugging. : In the Edit Run Configuration dialog box, for Launch Settings, enter the following Java Options for the default virtual machine: -Djbo.debugoutput=adflogger -Djbo.adflogger.level=FINE Oracle recommends level=FINE for detailed diagnostic messages.
Profiling : Note: For a simple CPU profile report that is sent to standard output, you can use the –Xprof JVM option. To utilize this utility, edit the project properties and select Run/Debug/Profile. Edit an existing configuration or create a new one. In the Launch Settings, add –Xprof to the Java Options field. You can also easily get garbage collection information by using the ­verbose:gc JVM option.
Profiling : Memory and CPU : https://oracle-adf.com/website/docs/Learn_Oracle_ADF/04-Oracle_11g_ADF_University/Oracle_11g_ADF_I/10Troubleshooting/img22
Sample client , HTTP Analyzer Troubleshoot webservices REST SOAP Troubleshoot : https://oracle-adf.com/website/docs/Learn_Oracle_ADF/04-Oracle_11g_ADF_University/Oracle_11g_ADF_I/10Troubleshooting/img24
Code to get binding : https://oraclebpm11g.blogspot.com/2013/05/code-to-access-dcbindingcontainer.html
ADF binding properties : https://docs.oracle.com/middleware/1213/adf/develop/appx-adf-binding-properties.htm
Best way to handled VLAccessor .. not using directly but using AM : or through AM : https://andrejusb.blogspot.com/2012/05/view-link-accessor-usage-performance.html
This is exact oppositive to waht we are duging in previous statement : 5.12.7 How to Automatically Refresh the View Object of the View Accessor 
AM Dirty : Pending changes : af:query for the search screen. Custom Listner : https://andrejusb.blogspot.com/2008/11/adf-query-component-and-view-criteria.html
how to access detailed PVO using VL accessor : https://docs.oracle.com/middleware/1213/adf/develop/adf-bc-vo-master-detail.htm
ROW Finder : Note that the capability of row finders to match rows using non-key attributes extends to master-detail related view objects  : 5.11.4 Programmatically Invoking the Row Finder
Row finder example : https://andrejusb.blogspot.com/2013/07/adf-bc-12c-new-feature-row-finder.html
Row finder always using transient attribute : https://docs.oracle.com/middleware/1212/adf/ADFFD/bcquerying.htm#BCGFFEEI 
bindings : bc.getControlBindings() BindingContainer bc = BindingContext.getCurrent().getCurrentBindingsEntry(); 
bc.getAttributeBindings() bc.getOperationBindings()  #{bindings[attr.containerName][attr.name].inputValue}
From component get the the component binding : https://youtu.be/j7okkGBjE6w?t=2544 Awesome. CollectionModel ,getwrappedData , getCurrentDCIteratorBinding
Awesome get Selected Row or Rows for a given component : https://youtu.be/j7okkGBjE6w?t=2593
Unsaved Data Warning : uncommited : dirty check of data : https://community.oracle.com/tech/developers/discussion/2525449/inform-users-on-unsaved-data-when-switching-between-starting-new-taskflows
Table and Row selection good code : https://youtu.be/AlkRWYXl_Xw?t=721
Query Search advance for view filtering : http://www.learnoracleadf.com/2013/05/programatic-handle-to-adf-query.html
Programmatically handled query : adf Query or search components : http://www.learnoracleadf.com/2013/05/programatic-handle-to-adf-query_5.html
insert row programatically using beans using presentation mbean method : http://www.learnoracleadf.com/2012/10/insert-rows-in-to-adf-view-object.html
how to find if a vo is modified or not : Boolean b = targetVO.getApplicationModule().getTransaction().isDirty();
how to find a view object and biding details using mbean : http://www.learnoracleadf.com/2012/10/how-to-identify-view-object-have-been.html
Mbeam driven taskflow ADF Dynamic region example : http://www.learnoracleadf.com/2012/06/adf-dynamic-region-working-with-oracle.html
groovy pageflowScope : Yes, you can pass Page Flow scope variable to VO bind variable through groovy "adf.context.pageFlowScope.Var"
logging.xml : config\fmwconfig\servers or : %JDEV_USER_DIR%\system11.1.2.1.38.60.81\DefaultDomain\config\fmwconfig\servers\DefaultServer
create : we have declared a static ADFLogger : ADFLogger is part of shared library only : NICK
