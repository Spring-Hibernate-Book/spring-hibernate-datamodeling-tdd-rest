<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/standalone/product.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p><b>Product Manager:</b></p>
		</div>
		<form method="post" id="productForm">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td>
						<input name="productid" id="productid" type="hidden">
						<input name="productname" id="productname" type="text">
						</td>
						<td><input type=submit value="Create" id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="productFormResponse"></div>
	</div>
</body>
</html>