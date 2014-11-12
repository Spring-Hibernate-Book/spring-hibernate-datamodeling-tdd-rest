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
	var name = $("#stuname").val();
	var mentor=$("#mentor").val();
	var formData={"name":name,"mentorName":mentor};
	$.ajax({
		url : "/Spring-OODD/onetooneselfreference/mock/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("stuname").value="";
			document.getElementById("mentor").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("stuname").value="";
			document.getElementById("mentor").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#stuname").val();
	var id = $("#stuid").val();
	var mentor = $("#mentor").val();
	var formData={"id":id,"name":name,"mentorName":mentor};
	$.ajax({
		url : "/Spring-OODD/onetooneselfreference/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("stuname").value="";
			document.getElementById("mentor").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("stuname").value="";
			document.getElementById("mentor").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/onetooneselfreference/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("stuname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	return false;
}
function deleteObject(stuid){
	var studentForm={id:stuid};
	delurl="/Spring-OODD/onetooneselfreference/mock/remove/"+stuid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : studentForm,
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
function editObject(stuid){
	editurl="/Spring-OODD/onetooneselfreference/mock/findById/"+stuid;
	var studentForm={id:stuid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : studentForm,
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
	document.getElementById("stuname").value=data.name;
	document.getElementById("mentor").value=data.mentorName;
	document.getElementById("stuid").value=data.id;
}
function generateTableData(itemvalue,itemNo){
	var mentorName = itemvalue.mentorName;
	if(null == mentorName) mentorName="";
	var dataRow="";
	if(mentorName!=""){
		dataRow="<tr>" +
		"<td>" +itemvalue.name+"</td>"+
		"<td>"+mentorName+"</td>"+
		"<td>" +
		"<a href=# onclick=deleteObject("+itemvalue.id+")>Delete</a>"+
		"|<a href=# onclick=editObject("+itemvalue.id+")>Edit</a>"+
		"</td>"+
		"</tr>";
	}else{
		dataRow="<tr>" +
		"<td>" +itemvalue.name+"</td>"+
		"<td>"+mentorName+"</td>"+"<td></td>"+"</tr>";
	}
	return dataRow;
}
function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Student Name</td>"+"<td>Mentor Name</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue,itemno);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("studentFormResponse").innerHTML=dyanamicTableRow;
}