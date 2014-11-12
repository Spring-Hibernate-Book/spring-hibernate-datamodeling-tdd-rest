function methodCall(methodName){
	var buttonValue = document.getElementById("subButtonMember").value;
	if(buttonValue=="Create Member"){
		createMember();
	} else if(buttonValue=="Update Member"){
		updateMember();
	} 	
	var memberMemberButtonValue = document.getElementById("subButtonMemberMember").value;
	if(memberMemberButtonValue=="Assign") {
		isPresent();
	}	
	return false;
}

function isPresent(){
	var memberid1 = $("#memberSelectBox1").val();
	var memberid2 = $("#memberSelectBox2").val();
	
	var membername1 = $("#memberSelectBox1").find('option:selected').text();
	var membername2 = $("#memberSelectBox2").find('option:selected').text();	
	
	if(null != memberid1 && "" != memberid1 && null!= memberid2 && "" != memberid2) {
		var formData={"memberId1":{"id":memberid1},"memberId2":{"id":memberid2}};
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreference/membermember/mock/isPresent",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				if(!data) {
					createMemberMember(memberid1, memberid2, membername1, membername2);
				} else {
					alert("Member already assigned to this Member");
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

function createMemberMember(memberid1, memberid2, membername1, membername2){
	var formData={"memberId1":{"id":memberid1, "name":membername1},"memberId2":{"id":memberid2, "name":membername2}};
	if(memberid1 != memberid2) {
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreference/membermember/mock/create",
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
		alert("Can't assign member to itself. Please choose a different member.");
	}
	
	return false;
}

function createMember(){
	var name = $("#membername").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanyselfreference/member/mock/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("membername").value="";
				document.getElementById("subButtonMember").value="Create Member";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("membername").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function updateMember(){
	var name = $("#membername").val();
	var id = +$("#memberid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreference/member/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("membername").value="";			
			document.getElementById("subButtonMember").value="Create Member";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("membername").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreference/member/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
			processMember2ResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("membername").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanyselfreference/membermember/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processMemberMemberResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{			
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	return false;
}
function deleteMemberObject(memberid){
	var memberForm={id:memberid};
	delurl="/Spring-OODD/manytomanyselfreference/member/mock/remove/"+memberid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : memberForm,
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

function deleteMemberMemberObject(memberid1,memberid2){
	var formData={"memberId1":{"id":memberid1},"memberId2":{"id":memberid2}};
	delurl="/Spring-OODD/manytomanyselfreference/membermember/mock/remove";
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
function editMemberObject(memberid){
	editurl="/Spring-OODD/manytomanyselfreference/member/mock/findById/"+memberid;
	var memberForm={id:memberid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : memberForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewMemberObject(data);
			document.getElementById("subButtonMember").value="Update Member";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewMemberObject(data){
	document.getElementById("membername").value=data.name;
	document.getElementById("memberid").value=data.id;
}
function generateMemberTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteMemberObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editMemberObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}


function generateMemberMemberTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.memberId1.name+"</td>"+
	"<td>" +itemvalue.memberId2.name+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteMemberMemberObject("+itemvalue.memberId1.id+","+itemvalue.memberId2.id+")>Delete</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Member Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicMemberDropdown = "<select id='memberSelectBox1'><option value=''>Select Member</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateMemberTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicMemberDropdown = dynamicMemberDropdown+dataCombo+"</select>";
	document.getElementById("memberFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("memberCombo1").innerHTML=dynamicMemberDropdown;
}

function processMember2ResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Member Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicMemberDropdown = "<select id='memberSelectBox2'><option value=''>Select Member</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateMemberTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicMemberDropdown = dynamicMemberDropdown+dataCombo+"</select>";
	document.getElementById("memberFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("memberCombo2").innerHTML=dynamicMemberDropdown;
}

function processMemberMemberResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Member Name</td>"+"<td>Member Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateMemberMemberTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("memberMemberFormResponse").innerHTML=dyanamicTableRow;
}