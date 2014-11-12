function methodCall(){
	var buttonValue = document.getElementById("subButtonUser").value;
	if(buttonValue=="Create User"){
		createUser();
	} else if(buttonValue=="Update User"){
		updateUser();
	} 
	
	var groupButtonValue = document.getElementById("subButtonGroup").value;
	if(groupButtonValue=="Create Group") {
		createGroup();
	} else if(groupButtonValue=="Update Group") {
		updateGroup();
	} 
	
	var userGroupButtonValue = document.getElementById("subButtonUserGroup").value;
	if(userGroupButtonValue=="Assign") {
		isPresent();
	}
	
	return false;
}

function isPresent(){
	var userid = $("#userSelectBox").val();
	var groupid = $("#groupSelectBox").val();
	
	var username = $("#userSelectBox").find('option:selected').text();
	var groupname = $("#groupSelectBox").find('option:selected').text();
	
	if(null != userid && "" != userid && null!= groupid && "" != groupid) {
		var formData={"userDto":{"id":userid},"groupDto":{"id":groupid}};
		$.ajax({
			url : "/Spring-OODD/manytomanyunidirectional/usergroup/mock/isPresent",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				if(!data) {
					createUserGroup(userid, groupid, username, groupname);
				} else {
					alert("User already assigned to this group");
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

function createUserGroup(userid,groupid, username, groupname){
	var formData={"userDto":{"id":userid, "name":username},"groupDto":{"id":groupid, "name":groupname}};
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/usergroup/mock/create",
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

function createUser(){
	var name = $("#username").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanyunidirectional/user/mock/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("username").value="";
				document.getElementById("subButtonUser").value="Create User";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("username").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function createGroup(){
	var name = $("#groupname").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanyunidirectional/group/mock/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("groupname").value="";
				document.getElementById("subButtonGroup").value="Create Group";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("groupname").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function updateUser(){
	var name = $("#username").val();
	var id = +$("#userid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/user/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("username").value="";			
			document.getElementById("subButtonUser").value="Create User";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("username").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function updateGroup(){
	var name = $("#groupname").val();
	var id = +$("#groupid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/group/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("groupname").value="";			
			document.getElementById("subButtonGroup").value="Create Group";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("groupname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/user/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processUserResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("username").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/group/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processGroupResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("groupname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanyunidirectional/usergroup/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processUserGroupResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{			
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	return false;
}
function deleteUserObject(userid){
	var userForm={id:userid};
	delurl="/Spring-OODD/manytomanyunidirectional/user/mock/remove/"+userid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : userForm,
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
function deleteGroupObject(groupid){
	var groupForm={id:groupid};
	delurl="/Spring-OODD/manytomanyunidirectional/group/mock/remove/"+groupid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : groupForm,
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
function deleteUserGroupObject(userid,groupid){
	var formData={"userDto":{"id":userid},"groupDto":{"id":groupid}};
	delurl="/Spring-OODD/manytomanyunidirectional/usergroup/mock/remove";
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
function editUserObject(userid){
	editurl="/Spring-OODD/manytomanyunidirectional/user/mock/findById/"+userid;
	var userForm={id:userid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : userForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewUserObject(data);
			document.getElementById("subButtonUser").value="Update User";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function editGroupObject(groupid){
	editurl="/Spring-OODD/manytomanyunidirectional/group/mock/findById/"+groupid;
	var groupForm={id:groupid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : groupForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewGroupObject(data);
			document.getElementById("subButtonGroup").value="Update Group";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewUserObject(data){
	document.getElementById("username").value=data.name;
	document.getElementById("userid").value=data.id;
}
function viewGroupObject(data){
	document.getElementById("groupname").value=data.name;
	document.getElementById("groupid").value=data.id;
}
function generateUserTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteUserObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editUserObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateGroupTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteGroupObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editGroupObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateUserGroupTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.userDto.name+"</td>"+
	"<td>" +itemvalue.groupDto.name+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteUserGroupObject("+itemvalue.userDto.id+","+itemvalue.groupDto.id+")>Delete</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function processUserResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>User Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicUserDropdown = "<select id='userSelectBox'><option value=''>Select User</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateUserTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicUserDropdown = dynamicUserDropdown+dataCombo+"</select>";
	document.getElementById("userFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("userCombo").innerHTML=dynamicUserDropdown;
}

function processGroupResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Group Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicGroupDropdown = "<select id='groupSelectBox'><option value=''>Select Group</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateGroupTableData(itemvalue);
		dataCombo = dataCombo + "<option value='"+itemvalue.id+"'>"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicGroupDropdown = dynamicGroupDropdown+dataCombo+"</select>";
	document.getElementById("groupFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("groupCombo").innerHTML=dynamicGroupDropdown;
}

function processUserGroupResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>User Name</td>"+"<td>Group Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateUserGroupTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("userGroupFormResponse").innerHTML=dyanamicTableRow;
}