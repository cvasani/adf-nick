CREATE TABLE CASCADING_LOVS (EMPLOYEE_ID NUMBER(6), DEPARTMENT_ID
NUMBER(4));
select * from CASCADING_LOVS;

private String firstName;
private String lastName;
public SessionInfoBean() {
}
public String getFirstName() {
if (firstName == null) {
UserInfoAppModule userInfoAppModule =
(UserInfoAppModule)ADFUtils
.getApplicationModuleForDataControl(
"UserInfoAppModuleDataControl");
firstName = userInfoAppModule.getFirstName();
}
return firstName;
}
public String getLastName() {
if (lastName == null) {
UserInfoAppModule userInfoAppModule =
(UserInfoAppModule)ADFUtils
.getApplicationModuleForDataControl
("UserInfoAppModuleDataControl");
lastName = userInfoAppModule.getLastName();
}
return lastName;
}


