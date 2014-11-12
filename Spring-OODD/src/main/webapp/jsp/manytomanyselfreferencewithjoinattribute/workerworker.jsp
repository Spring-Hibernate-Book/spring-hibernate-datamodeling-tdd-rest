<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/manytomanyselfreferencewithjoinattribute/workerworker.js"></script>
</head>
<body onload="loadObjects()">
	<div id="container">		
		<table>
			<tbody>
				<tr>
					<td width="350px" height="20%" valign="top">
						<form method="post" id="workerForm">
							<table>
								<tr><td><p><b>Enter Worker Name</b></p></td></tr>
								<tr>
									<td>Name:</td>
									<td><input name="workerid" id="workerid" type="hidden"> <input name="workername" id="workername" type="text"></td>
								</tr>
								<tr>
									<td><input type=submit value="Create Worker" id="subButtonWorker" onclick="return methodCall()"></td>
								</tr>
							</table>
							<div id="workerFormResponse"></div>
						</form>
					</td>					
				</tr>
				
				<tr>
					<td>
						<table>
							<tr><td><p><b>Assign Worker to Worker</b></p></td></tr>
							<tr>
								<td>
									<div id="workerCombo1"></div>
								</td>
								<td>
									<div id="workerCombo2"></div>
								</td>								
							</tr>
							<tr>
								<td><p><b>Enter Relationship</b></p></td>
								<td>
									<div id="relationshipText"></div>
								</td>
							</tr>
							<tr>
								<td><input type=submit value="Assign" id="subButtonWorkerWorker" onclick="return methodCall()"></td>
							</tr>
						</table>
						<div id="workerWorkerFormResponse"></div>
					</td>
				</tr>
				
			</tbody>
		</table>		
	</div>
</body>
</html>