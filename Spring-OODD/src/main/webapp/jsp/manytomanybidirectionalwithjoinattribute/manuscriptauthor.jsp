<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/manytomanybidirectionalwithjoinattribute/manuscriptauthor.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">		
		<table>
			<tbody>
				<tr>
					<td width="350px" height="20%" valign="top">
						<form method="post" id="manuscriptForm">
							<table>
								<tr><td><p><b>Enter Manuscript Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="manuscriptid" id="manuscriptid" type="hidden"> <input name="manuscriptname" id="manuscriptname" type="text"></td>
								</tr>
								<tr>
									<td><input type=submit value="Create Manuscript" id="subButtonManuscript" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="manuscriptFormResponse"></div>
						</form>
					</td>
					
					<td width="350px" height="20%" valign="top">
						<form method="post" id="authorForm">
							<table>
								<tr><td><p><b>Enter Author Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="authorid" id="authorid" type="hidden"> <input name="authorname" id="authorname" type="text"></td>
								</tr>
								<tr>
									<td colspan="2"><input type=submit value="Create Author" id="subButtonAuthor" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="authorFormResponse"></div>
						</form>
					</td>
				</tr>
				
				<tr>
					<td>
						<table>
							<tr><td><p><b>Assign Author to Manuscript</b></p></td></tr>
							<tr>
								<td>
									<div id="manuscriptCombo"></div>
								</td>
								<td>
									<div id="authorCombo"></div>
								</td>								
							</tr>
							<tr>
								<td><p><b>Enter Publisher Name</b></p></td>
								<td>
									<div id="publisherText"></div>
								</td>
							</tr>
							<tr>
								<td><input type=submit value="Assign" id="subButtonManuscriptAuthor" onclick="return methodCall()"></td>
							</tr>
						</table>
						<div id="manuscriptAuthorFormResponse"></div>
					</td>
				</tr>
				
			</tbody>
		</table>		
	</div>
</body>
</html>