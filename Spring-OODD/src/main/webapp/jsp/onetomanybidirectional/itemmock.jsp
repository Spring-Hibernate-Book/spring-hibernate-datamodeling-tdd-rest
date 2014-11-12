<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="../../js/onetomanybidirectional/itemmock.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">
		<div>
			<p>
				<b>Item Manager:</b>
			</p>
		</div>
		<form method="post" id="itemForm">
			<div id="itemDiv">
				<div id="nameGroup">
					<label>Name:<input type="text" name="name" id="name"></label>
					<label><input type="hidden" name="itemid" id="itemid"></label>
				</div>
			</div>
			<div id="itemBoxGroup">
				<div id="itemBox_1">
					<label>Feature:<input type="text" name="feature" id="feature" size="10" maxlength="12"><a href="#" onclick="javascript:addMoreFeature()">(+)</a></label>
					<label id="removeLink_1"></label>
				</div>
			</div>
			<div id="buttonGroup"><input type=submit value="Create" id="subButton" onclick="return methodCall()"></div>
		</form>
		<div id="itemFormResponse"></div>
	</div>
</body>
</html>
