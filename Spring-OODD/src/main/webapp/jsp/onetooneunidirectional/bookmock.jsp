<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="../../js/onetooneunidirectional/bookmock.js"></script>
</head>


<body onload="loadObjects()">
	<div id="container">
		<div>
			<p>
				<b>Book Manager:</b>
			</p>
		</div>
		<form method="post" id="bookForm">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><input name="bookId" id="bookId" type="hidden"> <input
							name="bookname" id="bookname" type="text"></td>
					</tr>
					<tr>
						<td class="tdLabel"><label for="ad002_dob" class="label">City:</label></td>
						<td><input type="text" name="city" value="" id="city" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type=submit value="Create"
							id="subButton" onclick="return methodCall()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="bookFormResponse"></div>
	</div>
</body>
</html>