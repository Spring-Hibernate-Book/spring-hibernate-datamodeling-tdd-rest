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
	var name = $("#protocolname").val();
	var type = $("#protocolChooser").val();	
	
	if(type == "" || type == null) {
		alert("Please choose Protocol Type");
		return false;
	}
	
	if(name == "" || name == null) {
		alert("Please enter Protocol name");
		return false;
	}
	var formData={"name":name,"type":type};
	$.ajax({
		url : "/Spring-OODD/singletableinheritance/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("protocolname").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("protocolname").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#protocolname").val();
	var id = +$("#protocolid").val();
	var type = $("#protocolChooser").val();
	var formData={"id":id,"name":name,"type":type};
	$.ajax({
		url : "/Spring-OODD/singletableinheritance/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("protocolname").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("protocolname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/singletableinheritance/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("protocolname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	$('#protocolChooser').get(0).selectedIndex = 0;
	return false;
}
function deleteObject(protocolid){
	var protocolForm={id:protocolid};
	var delurl="/Spring-OODD/singletableinheritance/remove/"+protocolid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : protocolForm,
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
function editObject(protocolid){
	var editurl="/Spring-OODD/singletableinheritance/findById/"+protocolid;
	var protocolForm={id:protocolid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : protocolForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewObject(data);			
			document.getElementById("subButton").value="Update";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewObject(data){	
	$("#protocolChooser").val(data.type);
	document.getElementById("protocolname").value=data.name;
	document.getElementById("protocolid").value=data.id;
}
function generateTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.type+"</td>"+
	"<td>" +itemvalue.name+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteObject("+itemvalue.id+")>Delete</a>"+
	"|<a href=# onclick=editObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}
function processResponseData(data){	
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Type</td>"+
	"<td>Name</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(data, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("protocolFormResponse").innerHTML=dyanamicTableRow;
}