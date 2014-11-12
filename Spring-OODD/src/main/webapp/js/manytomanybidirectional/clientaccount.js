function methodCall(){
	var buttonValue = document.getElementById("subButtonClient").value;
	if(buttonValue=="Create Client"){
		createClient();
	} else if(buttonValue=="Update Client"){
		updateClient();
	} 
	
	var accountButtonValue = document.getElementById("subButtonAccount").value;
	if(accountButtonValue=="Create Account") {
		createAccount();
	} else if(accountButtonValue=="Update Account") {
		updateAccount();
	} 
	
	var clientAccountButtonValue = document.getElementById("subButtonClientAccount").value;
	if(clientAccountButtonValue=="Assign") {
		isPresent();
	}	
	return false;
}

function isPresent(){
	var clientid = $("#clientSelectBox").val();
	var accountid = $("#accountSelectBox").val();
	if(null != clientid && "" != clientid && null!= accountid && "" != accountid) {
		var formData={"clientDto":{"id":clientid},"accountDto":{"id":accountid}};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectional/clientaccount/isPresent",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				if(!data) {
					createClientAccount(clientid, accountid);
				} else {
					alert("Client already assigned to this Account");
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

function createClientAccount(clientid,accountid){
	var formData={"clientDto":{"id":clientid},"accountDto":{"id":accountid}};
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/clientaccount/create",
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
	return false;
}

function createClient(){
	var name = $("#clientname").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectional/client/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("clientname").value="";
				document.getElementById("subButtonClient").value="Create Client";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("clientname").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function createAccount(){
	var number = $("#accountnumber").val();
	if(null != number && "" != number) {
		var formData={"number":number};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectional/account/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("accountnumber").value="";
				document.getElementById("subButtonAccount").value="Create Account";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("accountnumber").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function updateClient(){
	var name = $("#clientname").val();
	var id = +$("#clientid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/client/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("clientname").value="";			
			document.getElementById("subButtonClient").value="Create Client";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("clientname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function updateAccount(){
	var number = $("#accountnumber").val();
	var id = +$("#accountid").val();
	var formData={"id":id,"number":number};
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/account/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("accountnumber").value="";			
			document.getElementById("subButtonAccount").value="Create Account";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("accountnumber").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/client/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processClientResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("clientname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/account/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processAccountResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("accountnumber").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectional/clientaccount/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processClientAccountResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{			
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	return false;
}
function deleteClientObject(clientid){
	var clientForm={id:clientid};
	delurl="/Spring-OODD/manytomanybidirectional/client/remove/"+clientid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : clientForm,
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
function deleteAccountObject(accountid){
	var accountForm={id:accountid};
	delurl="/Spring-OODD/manytomanybidirectional/account/remove/"+accountid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : accountForm,
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
function deleteClientAccountObject(clientid,accountid){
	var formData={"clientDto":{"id":clientid},"accountDto":{"id":accountid}};
	delurl="/Spring-OODD/manytomanybidirectional/clientaccount/remove";
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
function editClientObject(clientid){
	editurl="/Spring-OODD/manytomanybidirectional/client/findById/"+clientid;
	var clientForm={id:clientid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : clientForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewClientObject(data);
			document.getElementById("subButtonClient").value="Update Client";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function editAccountObject(accountid){
	editurl="/Spring-OODD/manytomanybidirectional/account/findById/"+accountid;
	var accountForm={id:accountid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : accountForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewAccountObject(data);
			document.getElementById("subButtonAccount").value="Update Account";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewClientObject(data){
	document.getElementById("clientname").value=data.name;
	document.getElementById("clientid").value=data.id;
}
function viewAccountObject(data){
	document.getElementById("accountnumber").value=data.number;
	document.getElementById("accountid").value=data.id;
}
function generateClientTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteClientObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editClientObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateAccountTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.number+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteAccountObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editAccountObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateClientAccountTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.clientDto.name+"</td>"+
	"<td>" +itemvalue.accountDto.number+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteClientAccountObject("+itemvalue.clientDto.id+","+itemvalue.accountDto.id+")>Delete</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function processClientResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Client Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicClientDropdown = "<select id='clientSelectBox'><option value=''>Select Client</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateClientTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicClientDropdown = dynamicClientDropdown+dataCombo+"</select>";
	document.getElementById("clientFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("clientCombo").innerHTML=dynamicClientDropdown;
}

function processAccountResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Account Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicAccountDropdown = "<select id='accountSelectBox'><option value=''>Select Account</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateAccountTableData(itemvalue);
		dataCombo = dataCombo + "<option value='"+itemvalue.id+"'>"+itemvalue.number+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicAccountDropdown = dynamicAccountDropdown+dataCombo+"</select>";
	document.getElementById("accountFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("accountCombo").innerHTML=dynamicAccountDropdown;
}

function processClientAccountResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Client Name</td>"+"<td>Account Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateClientAccountTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("clientAccountFormResponse").innerHTML=dyanamicTableRow;
}