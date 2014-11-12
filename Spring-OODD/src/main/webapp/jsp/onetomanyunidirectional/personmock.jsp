<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="../../js/onetomanyunidirectional/personmock.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">
		<div>
			<p>
				<b>Person Manager:</b>
			</p>
		</div>
		<form method="post" id="personForm">
			<div id="personDiv">
				<div id="nameGroup">
					<label>Name:<input type="text" name="name" id="name"></label>
					<label><input type="hidden" name="personid" id="personid"></label>
				</div>
			</div>
			<div id="phoneBoxGroup">
				<div id="phoneBox_1">
					<label>Phone:<input type="text" name="phone" id="phone" size="10" maxlength="12"><a href="#" onclick="javascript:addMorePhone()">(+)</a></label>
					<label id="removeLink_1"></label>
				</div>
			</div>
			<div id="buttonGroup"><input type=submit value="Create" id="subButton" onclick="return methodCall()"></div>
		</form>
		<div id="personFormResponse"></div>
	</div>
</body>
</html>