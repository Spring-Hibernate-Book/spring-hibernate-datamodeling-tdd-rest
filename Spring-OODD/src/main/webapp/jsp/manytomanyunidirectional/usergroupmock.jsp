<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/manytomanyunidirectional/usergroupmock.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">		
		<table>
			<tbody>
				<tr>
					<td width="350px" height="20%" valign="top">
						<form method="post" id="userForm">
							<table>
								<tr><td><p><b>Enter User Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="userid" id="userid" type="hidden"> <input name="username" id="username" type="text"></td>
								</tr>
								<tr>
									<td><input type=submit value="Create User" id="subButtonUser" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="userFormResponse"></div>
						</form>
					</td>
					
					<td width="350px" height="20%" valign="top">
						<form method="post" id="groupForm">
							<table>
								<tr><td><p><b>Enter Group Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="groupid" id="groupid" type="hidden"> <input name="groupname" id="groupname" type="text"></td>
								</tr>
								<tr>
									<td colspan="2"><input type=submit value="Create Group" id="subButtonGroup" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="groupFormResponse"></div>
						</form>
					</td>
				</tr>
				
				<tr>
					<td>
						<table>
							<tr><td><p><b>Assign User to Group</b></p></td></tr>
							<tr>
								<td>
									<div id="userCombo"></div>
								</td>
								<td>
									<div id="groupCombo"></div>
								</td>
							</tr>
							<tr>
								<td><input type=submit value="Assign" id="subButtonUserGroup" onclick="return methodCall()"></td>
							</tr>
						</table>
						<div id="userGroupFormResponse"></div>
					</td>
				</tr>
				
			</tbody>
		</table>		
	</div>
</body>
</html>