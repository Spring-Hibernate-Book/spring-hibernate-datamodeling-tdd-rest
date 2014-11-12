<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/manytomanybidirectional/clientaccountmock.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">		
		<table>
			<tbody>
				<tr>
					<td width="350px" height="20%" valign="top">
						<form method="post" id="clientForm">
							<table>
								<tr><td><p><b>Enter Client Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="clientid" id="clientid" type="hidden"> <input name="clientname" id="clientname" type="text"></td>
								</tr>
								<tr>
									<td><input type=submit value="Create Client" id="subButtonClient" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="clientFormResponse"></div>
						</form>
					</td>
					
					<td width="350px" height="20%" valign="top">
						<form method="post" id="accountForm">
							<table>
								<tr><td><p><b>Enter Account Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="accountid" id="accountid" type="hidden"> <input name="accountnumber" id="accountnumber" type="text"></td>
								</tr>
								<tr>
									<td colspan="2"><input type=submit value="Create Account" id="subButtonAccount" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="accountFormResponse"></div>
						</form>
					</td>
				</tr>
				
				<tr>
					<td>
						<table>
							<tr><td><p><b>Assign Client to Account</b></p></td></tr>
							<tr>
								<td>
									<div id="clientCombo"></div>
								</td>
								<td>
									<div id="accountCombo"></div>
								</td>
							</tr>
							<tr>
								<td><input type=submit value="Assign" id="subButtonClientAccount" onclick="return methodCall()"></td>
							</tr>
						</table>
						<div id="clientAccountFormResponse"></div>
					</td>
				</tr>
				
			</tbody>
		</table>		
	</div>
</body>
</html>