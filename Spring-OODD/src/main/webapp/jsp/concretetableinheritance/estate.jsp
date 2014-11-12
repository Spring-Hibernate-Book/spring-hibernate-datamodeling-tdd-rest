<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/concretetableinheritance/estate.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p><b>Estate Manager:</b></p>
		</div>
		<form method="post" id="estateForm">
			<table>
				<tbody>
					<tr>
						<td>Choose Estate</td>
						<td>
							<select name="estateChooser" id="estateChooser" onchange="setEstateValue()">
								<option value="">Select Estate Type</option>
								<option value="building">Building</option>
								<option value="land">Land</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>
						<input name="estateid" id="estateid" type="hidden">
						<input name="estatename" id="estatename" type="text">
						</td>
					</tr>
					<tr>
						<td><div id="estateType">Enter Value</div></td>
						<td>
							<input name="estateProperty" id="estateProperty" type="text">							
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type=submit value="Create" id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="estateFormResponse"></div>
	</div>
</body>
</html>