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
	var name = $("#bookname").val();
	var city = $("#city").val();
	var formData={"name":name,"city":city};
	$.ajax({
		url : "/Spring-OODD/onetooneunidirectional/mock/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("bookname").value="";
			document.getElementById("city").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("bookname").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#bookname").val();
	var id = +$("#bookId").val();
	var city = $("#city").val();
	var formData={"id":id,"name":name,"city":city};
	$.ajax({
		url : "/Spring-OODD/onetooneunidirectional/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("bookname").value="";
			document.getElementById("city").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("bookname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/onetooneunidirectional/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("bookname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	return false;
}
function deleteObject(bookId){
	var bookForm={id:bookId};
	delurl="/Spring-OODD/onetooneunidirectional/mock/remove/"+bookId;
	$.ajax({
		url : delurl,
		type: "POST",
		data : bookForm,
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
function editObject(bookId){
	editurl="/Spring-OODD/onetooneunidirectional/mock/findById/"+bookId;
	var bookForm={id:bookId};
	$.ajax({
		url : editurl,
		type: "GET",
		data : bookForm,
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
	document.getElementById("bookname").value=data.name;
	document.getElementById("city").value=data.city;
	document.getElementById("bookId").value=data.id;
}
function generateTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+
	"<td>"+itemvalue.city+"</td>"+
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
	"<td>Book Name</td>"+"<td>Shipping City</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("bookFormResponse").innerHTML=dyanamicTableRow;
}