<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/onetoonebidirectional/customermock.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p>
				<b>Customer Manager:</b>
			</p>
		</div>
		<form method="post" id="customerForm">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><input name="customerid" id="customerid" type="hidden"> <input
							name="customername" id="customername" type="text"></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>Amount:</label></td>
						<td><input type="text" name="amount" value="" id="amount" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type=submit value="Create"
							id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="customerFormResponse"></div>
	</div>
</body>
</html>