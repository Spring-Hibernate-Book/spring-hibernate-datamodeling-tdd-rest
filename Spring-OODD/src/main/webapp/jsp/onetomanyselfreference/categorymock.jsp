<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="../../js/onetomanyselfreference/categorymock.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">
		<div>
			<p>
				<b>Category Manager:</b>
			</p>
		</div>
		<form method="post" id="categoriesForm">
			<div id="categoriesDIV">
				<div id="nameGroup">
					<label>Name:<input type="text" name="name" id="name"></label>
					<label><input type="hidden" name="Id" id="Id"></label>
				</div>
			</div>
			<div id="parentIdGroup">
				<div id="parentIdTop">
					<div id="parentIdName">
						<label>Parent Id :</label>
						<label id="parentIdCombo">
							<select name="parentId" id="parentId"></select>
						</label>
					</div>
				</div>
			</div>
			<div id="buttonGroup">
				<input type=submit value="Create" id="subButton"
					onclick="return methodCall()">
			</div>
		</form>
		<div id="categoryFormResponse"></div>
	</div>
</body>
</html>