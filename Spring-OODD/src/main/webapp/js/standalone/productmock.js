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
	var name = $("#productname").val();
	var formData={"name":name};
	$.ajax({
		url : "/Spring-OODD/standalone/mock/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("productname").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("productname").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#productname").val();
	var id = +$("#productid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/standalone/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("productname").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("productname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/standalone/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("productname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	return false;
}
function deleteObject(productid){
	var productForm={id:productid};
	delurl="/Spring-OODD/standalone/mock/remove/"+productid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : productForm,
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
function editObject(productid){
	editurl="/Spring-OODD/standalone/mock/findById/"+productid;
	var productForm={id:productid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : productForm,
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
	document.getElementById("productname").value=data.name;
	document.getElementById("productid").value=data.id;
}
function generateTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+
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
	"<td>Product Name</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("productFormResponse").innerHTML=dyanamicTableRow;
}