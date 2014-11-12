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
	var name = $("#estatename").val();
	var type = $("#estateChooser").val();	
	var estateProperty = $("#estateProperty").val();
	var formData = "";
	if(type == "" || type == null) {
		alert("Please choose Estate Type");
		return false;
	}	
	if(name == "" || name == null) {
		alert("Please enter Estate name");
		return false;
	}
	if(type == "building") {
		formData={"type":"building","name":name,"floors":estateProperty};
	} else if (type == "land") {
		formData={"type":"land","name":name,"area":estateProperty};
	}
	$.ajax({
		url : "/Spring-OODD/concretetableinheritance/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("estatename").value="";
			document.getElementById("estateProperty").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
			setEstateValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("estatename").value="";
			document.getElementById("estateProperty").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#estatename").val();
	var id = +$("#estateid").val();
	var type = $("#estateChooser").val();
	var estateProperty = $("#estateProperty").val();
	
	if(type == "building") {
		formData={"type":"building","id":id,"name":name,"floors":estateProperty};
	} else if (type == "land") {
		formData={"type":"land","id":id,"name":name,"area":estateProperty};
	}
	
	$.ajax({
		url : "/Spring-OODD/concretetableinheritance/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("estatename").value="";
			document.getElementById("estateProperty").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
			setEstateValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("estatename").value="";
			document.getElementById("estateProperty").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/concretetableinheritance/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("estatename").value="";
			document.getElementById("estateProperty").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	$('#estateChooser').get(0).selectedIndex = 0;
	return false;
}
function deleteObject(estateid){
	var estateForm={id:estateid};
	var delurl="/Spring-OODD/concretetableinheritance/remove/"+estateid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : estateForm,
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
function editObject(estateid){
	var editurl="/Spring-OODD/concretetableinheritance/findById/"+estateid;
	var estateForm={id:estateid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : estateForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewObject(data);			
			document.getElementById("subButton").value="Update";
			setEstateValue();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewObject(data){
	$("#estateChooser").val(data.type);
	document.getElementById("estatename").value=data.name;
	document.getElementById("estateid").value=data.id;
	if(data.type == "building") {
		document.getElementById("estateProperty").value=data.floors;
	} else if(data.type == "land") {
		document.getElementById("estateProperty").value=data.area;
	}	
}
function generateTableData(itemvalue){
	var floorValue = (itemvalue.floors != "" && itemvalue.floors != null) ? itemvalue.floors : "-";
	var areaValue = (itemvalue.area != "" && itemvalue.area != null) ? itemvalue.area : "-";
	var dataRow="<tr>" +
	"<td>" +itemvalue.type+"</td>"+
	"<td>" +itemvalue.name+"</td>"+
	"<td align='center'>" +floorValue+"</td>"+
	"<td align='center'>" +areaValue+"</td>"+
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
	"<td>Floors</td>"+
	"<td>Area</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("estateFormResponse").innerHTML=dyanamicTableRow;
}
function setEstateValue() {
	var estateChooserValue = $("#estateChooser").val();
	if(estateChooserValue == "building") {
		$("#estateType").html("Floors");
	} else if(estateChooserValue == "land") {
		$("#estateType").html("Area");
	} else {
		$("#estateType").html("Enter Value");
	}
	return false;
}