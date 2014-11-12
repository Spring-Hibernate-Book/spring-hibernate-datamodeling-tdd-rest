<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/manytomanyselfreference/membermember.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">		
		<table>
			<tbody>
				<tr>
					<td width="350px" height="20%" valign="top">
						<form method="post" id="memberForm">
							<table>
								<tr><td><p><b>Enter Member Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="memberid" id="memberid" type="hidden"> <input name="membername" id="membername" type="text"></td>
								</tr>
								<tr>
									<td><input type=submit value="Create Member" id="subButtonMember" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="memberFormResponse"></div>
						</form>
					</td>					
				</tr>
				
				<tr>
					<td>
						<table>
							<tr><td><p><b>Assign Member to Member</b></p></td></tr>
							<tr>
								<td>
									<div id="memberCombo1"></div>
								</td>
								<td>
									<div id="memberCombo2"></div>
								</td>
							</tr>
							<tr>
								<td><input type=submit value="Assign" id="subButtonMemberMember" onclick="return methodCall()"></td>
							</tr>
						</table>
						<div id="memberMemberFormResponse"></div>
					</td>
				</tr>
				
			</tbody>
		</table>		
	</div>
</body>
</html>