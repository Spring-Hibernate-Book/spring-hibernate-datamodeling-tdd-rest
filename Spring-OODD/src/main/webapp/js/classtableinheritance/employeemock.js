function methodCall(){
	var buttonValue = document.getElementById("subButton").value;
	if(buttonValue=="Create"){
		create();
	}else if(buttonValue=="Update"){
		update();
	}
	return false;
}
function create(){
	var name = $("#employeename").val();
	var type = $("#employeeChooser").val();	
	var employeeFirstProperty = $("#employeeFirstProperty").val();
	var employeeSecondProperty = $("#employeeSecondProperty").val();
	var formData = "";
	if(type == "" || type == null) {
		alert("Please choose Employee Type");
		return false;
	}	
	if(name == "" || name == null) {
		alert("Please enter Employee name");
		return false;
	}
	if(type == "permanentEmployee") {
		formData={"type":"permanentEmployee","name":name,"leaves":employeeFirstProperty,"salary":employeeSecondProperty};
	} else if (type == "contractorEmployee") {
		formData={"type":"contractorEmployee","name":name,"hourlyRate":employeeFirstProperty,"overtimeRate":employeeSecondProperty};
	}
	$.ajax({
		url : "/Spring-OODD/classtableinheritance/mock/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("employeename").value="";
			document.getElementById("employeeFirstProperty").value="";
			document.getElementById("employeeSecondProperty").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
			setEmployeeValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("employeename").value="";
			document.getElementById("employeeFirstProperty").value="";
			document.getElementById("employeeSecondProperty").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#employeename").val();
	var id = +$("#employeeid").val();
	var type = $("#employeeChooser").val();
	var employeeFirstProperty = $("#employeeFirstProperty").val();
	var employeeSecondProperty = $("#employeeSecondProperty").val();
	
	if(type == "permanentEmployee") {
		formData={"type":"permanentEmployee","id":id,"name":name,"leaves":employeeFirstProperty,"salary":employeeSecondProperty};
	} else if (type == "contractorEmployee") {
		formData={"type":"contractorEmployee","id":id,"name":name,"hourlyRate":employeeFirstProperty,"overtimeRate":employeeSecondProperty};
	}
	
	$.ajax({
		url : "/Spring-OODD/classtableinheritance/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("employeename").value="";
			document.getElementById("employeeFirstProperty").value="";
			document.getElementById("employeeSecondProperty").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
			setEmployeeValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("employeename").value="";
			document.getElementById("employeeFirstProperty").value="";
			document.getElementById("employeeSecondProperty").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/classtableinheritance/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("employeename").value="";
			document.getElementById("employeeFirstProperty").value="";
			document.getElementById("employeeSecondProperty").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	$('#employeeChooser').get(0).selectedIndex = 0;
	return false;
}
function deleteObject(employeeid){
	var employeeForm={id:employeeid};
	var delurl="/Spring-OODD/classtableinheritance/mock/remove/"+employeeid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : employeeForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Delete:"+textStatus);
		}
	});
}
function editObject(employeeid){
	var editurl="/Spring-OODD/classtableinheritance/mock/findById/"+employeeid;
	var employeeForm={id:employeeid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : employeeForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewObject(data);			
			document.getElementById("subButton").value="Update";
			setEmployeeValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewObject(data){
	$("#employeeChooser").val(data.type);
	document.getElementById("employeename").value=data.name;
	document.getElementById("employeeid").value=data.id;
	if(data.type == "permanentEmployee") {
		document.getElementById("employeeFirstProperty").value=data.leaves;
		document.getElementById("employeeSecondProperty").value=data.salary;
	} else if(data.type == "contractorEmployee") {
		document.getElementById("employeeFirstProperty").value=data.hourlyRate;
		document.getElementById("employeeSecondProperty").value=data.overtimeRate;
	}	
}
function generateTableData(itemvalue){
	var leaves = (itemvalue.leaves != "" && itemvalue.leaves != null) ? itemvalue.leaves : "-";
	var salary = (itemvalue.salary != "" && itemvalue.salary != null) ? itemvalue.salary : "-";
	var hourlyRate = (itemvalue.hourlyRate != "" && itemvalue.hourlyRate != null) ? itemvalue.hourlyRate : "-";
	var overtimeRate = (itemvalue.overtimeRate != "" && itemvalue.overtimeRate != null) ? itemvalue.overtimeRate : "-";	
	
	var dataRow="<tr>" +
	"<td>" +itemvalue.type+"</td>"+
	"<td>" +itemvalue.name+"</td>"+
	"<td align='center'>" +leaves+"</td>"+
	"<td align='center'>" +salary+"</td>"+
	"<td align='center'>" +hourlyRate+"</td>"+
	"<td align='center'>" +overtimeRate+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteObject("+itemvalue.id+")>Delete</a>"+
	"|<a href=# onclick=editObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}
function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Type</td>"+
	"<td>Name</td>"+
	"<td>Leaves</td>"+
	"<td>Salary</td>"+
	"<td>Hourly Rate</td>"+
	"<td>Overtime Rate</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("employeeFormResponse").innerHTML=dyanamicTableRow;
}
function setEmployeeValue() {
	var employeeChooserValue = $("#employeeChooser").val();
	if(employeeChooserValue == "permanentEmployee") {
		$("#employeeFirstValue").html("Leaves");
		$("#employeeSecondValue").html("Salary");
	} else if(employeeChooserValue == "contractorEmployee") {
		$("#employeeFirstValue").html("Hourly Rate");
		$("#employeeSecondValue").html("Overtime Rate");
	} else {
		$("#employeeFirstValue").html("Enter Value");
		$("#employeeSecondValue").html("Enter Value");
	}
	return false;
}