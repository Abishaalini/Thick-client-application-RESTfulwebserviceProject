<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<html>

<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/Employees.js"></script>

<body>

<form id="formItem" name="formItem" method="post">
Employee Name:
<input id="EmployeeName" name="EmployeeName" type="text"
class="form-control form-control-sm">
<br> Employee Email:
<input id="EmployeeEmail" name="EmployeeEmail" type="text"
class="form-control form-control-sm">
<br> Employee Type:
<input id="EmployeeType" name="EmployeeType" type="text"
class="form-control form-control-sm">
<br> Employee Contact:
<input id="EmployeeContact" name="EmployeeContact" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input id="btnUpdate" name="btnUpdate" type="button" value="Update"
class="btn btn-warning">
<input id="btnRemove" name="btnRemove" type="button" value="Remove"
class="btn btn-danger">
</form>

<br>
<div id="divItemsGrid">
<%
Employee empObj = new Employee();
out.print(empObj.readEmployee());
%>
</div>
</body>
</html>