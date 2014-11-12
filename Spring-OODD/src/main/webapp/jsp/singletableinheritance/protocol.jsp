<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/singletableinheritance/protocol.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p><b>Protocol Manager:</b></p>
		</div>
		<form method="post" id="protocolForm">
			<table>
				<tbody>
					<tr>
						<td>Choose Protocol</td>
						<td>
							<select name="protocolChooser" id="protocolChooser">
								<option value="">Select Protocol Type</option>
								<option value="tcp">TCP</option>
								<option value="snmp">SNMP</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>
						<input name="protocolid" id="protocolid" type="hidden">
						<input name="protocolname" id="protocolname" type="text">
						</td>
						<td><input type=submit value="Create" id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="protocolFormResponse"></div>
	</div>
</body>
</html>