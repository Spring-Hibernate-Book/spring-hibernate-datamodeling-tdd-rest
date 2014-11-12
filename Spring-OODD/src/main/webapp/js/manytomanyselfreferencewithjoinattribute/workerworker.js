function methodCall(){
	var buttonValue = document.getElementById("subButtonWorker").value;
	if(buttonValue=="Create Worker"){
		createWorker();
	} else if(buttonValue=="Update Worker"){
		updateWorker();
	} 	
	var workerWorkerButtonValue = document.getElementById("subButtonWorkerWorker").value;
	if(workerWorkerButtonValue=="Assign") {
		isPresent();
	}	
	return false;
}

function isPresent(){
	var workerid1 = $("#workerSelectBox1").val();
	var workerid2 = $("#workerSelectBox2").val();
	var relationshipType = $("#relationshipTextBox").val();	
	
	if(null != workerid1 && "" != workerid1 && null!= workerid2 && "" != workerid2 && null!= relationshipType && "" != relationshipType) {
		var formData={"workerId1":{"id":workerid1},"workerId2":{"id":workerid2}};
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/workerworker/isPresent",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				if(!data) {
					createWorkerWorker(workerid1, workerid2,relationshipType);
				} else {
					alert("Worker already assigned to this Worker");
				}
			},
			error: function (jqXHR, textStatus, errorThrown)
			{				
				alert("Error Status Create:"+errorThrown);
			}
		});		
	}	
	return false;
}

function createWorkerWorker(workerid1,workerid2,relationshipType){
	var formData={"workerId1":{"id":workerid1},"workerId2":{"id":workerid2},"relationshipType":relationshipType};
	if(workerid1 != workerid2) {
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/workerworker/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{				
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				alert("Error Status Create:"+textStatus);
			}
		});	
	} else {
		alert("Can't assign worker to itself. Please choose a different worker.");
	}
	
	return false;
}

function createWorker(){
	var name = $("#workername").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/worker/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("workername").value="";
				document.getElementById("subButtonWorker").value="Create Worker";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("workername").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function updateWorker(){
	var name = $("#workername").val();
	var id = +$("#workerid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/worker/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("workername").value="";			
			document.getElementById("subButtonWorker").value="Create Worker";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("workername").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/worker/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
			processWorkerResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("workername").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreferencewithjoinattribute/workerworker/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processWorkerWorkerResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{			
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	document.getElementById("relationshipText").innerHTML="<input type='text' id='relationshipTextBox'/>";
	return false;
}

function deleteWorker(workerid){
	var workerForm={id:workerid};
	delurl="/Spring-OODD/manytomanyselfreferencewithjoinattribute/worker/remove/"+workerid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : workerForm,
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

function deleteWorkerWorker(workerid1,workerid2){
	var formData={"workerId1":{"id":workerid1},"workerId2":{"id":workerid2}};
	delurl="/Spring-OODD/manytomanyselfreferencewithjoinattribute/workerworker/remove";
	$.ajax({
		url : delurl,
		type: "POST",
		data : JSON.stringify(formData),
		dataType: "json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
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

function editWorker(workerid){
	editurl="/Spring-OODD/manytomanyselfreferencewithjoinattribute/worker/findById/"+workerid;
	var workerForm={id:workerid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : workerForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewWorker(data);
			document.getElementById("subButtonWorker").value="Update Worker";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewWorker(data){
	document.getElementById("workername").value=data.name;
	document.getElementById("workerid").value=data.id;
}
function generateWorkerTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteWorker("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editWorker("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}


function generateWorkerWorkerTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.workerId1.name+"</td>"+
	"<td>" +itemvalue.workerId2.name+"</td>"+
	"<td>" +itemvalue.relationshipType+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteWorkerWorker("+itemvalue.workerId1.id+","+itemvalue.workerId2.id+")>Delete</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Worker Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicWorkerDropdown = "<select id='workerSelectBox1'><option value=''>Select Worker</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateWorkerTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicWorkerDropdown = dynamicWorkerDropdown+dataCombo+"</select>";
	document.getElementById("workerFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("workerCombo1").innerHTML=dynamicWorkerDropdown;
}

function processWorkerResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Worker Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicWorkerDropdown = "<select id='workerSelectBox2'><option value=''>Select Worker</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateWorkerTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicWorkerDropdown = dynamicWorkerDropdown+dataCombo+"</select>";
	document.getElementById("workerFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("workerCombo2").innerHTML=dynamicWorkerDropdown;
}

function processWorkerWorkerResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Worker Name</td>"+"<td>Worker Name</td>"+"<td>Relationship</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateWorkerWorkerTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("workerWorkerFormResponse").innerHTML=dyanamicTableRow;
}