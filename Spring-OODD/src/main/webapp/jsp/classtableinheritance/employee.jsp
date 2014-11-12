<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/classtableinheritance/employee.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p><b>Employee Manager:</b></p>
		</div>
		<form method="post" id="employeeForm">
			<table>
				<tbody>
					<tr>
						<td>Choose Employee</td>
						<td>
							<select name="employeeChooser" id="employeeChooser" onchange="setEmployeeValue()">
								<option value="">Select Employee Type</option>
								<option value="permanentEmployee">PermanentEmployee</option>
								<option value="contractorEmployee">ContractorEmployee</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>
						<input name="employeeid" id="employeeid" type="hidden">
						<input name="employeename" id="employeename" type="text">
						</td>
					</tr>
					<tr>
						<td><div id="employeeFirstValue">Enter Value</div></td>
						<td>
							<input name="employeeFirstProperty" id="employeeFirstProperty" type="text">							
						</td>
					</tr>
					<tr>
						<td><div id="employeeSecondValue">Enter Value</div></td>
						<td>
							<input name="employeeSecondProperty" id="employeeSecondProperty" type="text">							
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type=submit value="Create" id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="employeeFormResponse"></div>
	</div>
</body>
</html>