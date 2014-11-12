function methodCall(methodName){
	var buttonValue = document.getElementById("subButtonManuscript").value;
	if(buttonValue=="Create Manuscript"){
		createManuscript();
	} else if(buttonValue=="Update Manuscript"){
		updateManuscript();
	} 
	
	var authorButtonValue = document.getElementById("subButtonAuthor").value;
	if(authorButtonValue=="Create Author") {
		createAuthor();
	} else if(authorButtonValue=="Update Author") {
		updateAuthor();
	} 
	
	var manuscriptAuthorButtonValue = document.getElementById("subButtonManuscriptAuthor").value;
	if(manuscriptAuthorButtonValue=="Assign") {
		isPresent();
	}	
	return false;
}

function isPresent(){
	var manuscriptid = $("#manuscriptSelectBox").val();
	var authorid = $("#authorSelectBox").val();
	var publisher = $("#publisherTextbox").val();
	
	if(null != manuscriptid && "" != manuscriptid && null!= authorid && "" != authorid && null!= publisher && "" != publisher) {
		var formData={"manuscriptDto":{"id":manuscriptid},"authorDto":{"id":authorid}};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscriptauthor/isPresent",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				if(!data) {
					createManuscriptAuthor(manuscriptid, authorid, publisher);
				} else {
					alert("Manuscript already assigned to this Author");
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

function createManuscriptAuthor(manuscriptid,authorid,publisher){
	var formData={"manuscriptDto":{"id":manuscriptid},"authorDto":{"id":authorid},"publisher":publisher};
	
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscriptauthor/create",
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

function createManuscript(){
	var name = $("#manuscriptname").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscript/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("manuscriptname").value="";
				document.getElementById("subButtonManuscript").value="Create Manuscript";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("manuscriptname").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function createAuthor(){
	var name = $("#authorname").val();
	if(null != name && "" != name) {
		var formData={"name":name};
		$.ajax({
			url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/author/create",
			type: "POST",
			data : JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR)
			{
				document.getElementById("authorname").value="";
				document.getElementById("subButtonAuthor").value="Create Author";
				loadObjects();
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				document.getElementById("authorname").value="";
				alert("Error Status Create:"+textStatus);
			}
		});
	}	
	return false;
}

function updateManuscript(){
	var name = $("#manuscriptname").val();
	var id = +$("#manuscriptid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscript/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("manuscriptname").value="";			
			document.getElementById("subButtonManuscript").value="Create Manuscript";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("manuscriptname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function updateAuthor(){
	var name = $("#authorname").val();
	var id = +$("#authorid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/author/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("authorname").value="";			
			document.getElementById("subButtonAuthor").value="Create Author";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("authorname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscript/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("manuscriptname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/author/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processAuthorResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("authorname").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	
	$.ajax({
		url : "/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscriptauthor/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processManuscriptAuthorResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{			
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	document.getElementById("publisherText").innerHTML="<input type='text' id='publisherTextbox'/>";
	return false;
}
function deleteManuscriptObject(manuscriptid){
	var manuscriptForm={id:manuscriptid};
	delurl="/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscript/remove/"+manuscriptid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : manuscriptForm,
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
function deleteAuthorObject(authorid){
	var authorForm={id:authorid};
	delurl="/Spring-OODD/manytomanybidirectionalwithjoinattribute/author/remove/"+authorid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : authorForm,
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
function deleteManuscriptAuthorObject(manuscriptid,authorid){
	var formData={"manuscriptDto":{"id":manuscriptid},"authorDto":{"id":authorid}};
	
	delurl="/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscriptauthor/remove";
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
function editManuscriptObject(manuscriptid){
	editurl="/Spring-OODD/manytomanybidirectionalwithjoinattribute/manuscript/findById/"+manuscriptid;
	var manuscriptForm={id:manuscriptid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : manuscriptForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewManuscriptObject(data);
			document.getElementById("subButtonManuscript").value="Update Manuscript";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function editAuthorObject(authorid){
	editurl="/Spring-OODD/manytomanybidirectionalwithjoinattribute/author/findById/"+authorid;
	var authorForm={id:authorid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : authorForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewAuthorObject(data);
			document.getElementById("subButtonAuthor").value="Update Author";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewManuscriptObject(data){
	document.getElementById("manuscriptname").value=data.name;
	document.getElementById("manuscriptid").value=data.id;
}
function viewAuthorObject(data){
	document.getElementById("authorname").value=data.name;
	document.getElementById("authorid").value=data.id;
}
function generateManuscriptTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteManuscriptObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editManuscriptObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateAuthorTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.name+"</td>"+	
	"<td>" +
	"<a href=# onclick=deleteAuthorObject("+itemvalue.id+")>Delete</a>"+
	"&nbsp;|&nbsp;<a href=# onclick=editAuthorObject("+itemvalue.id+")>Edit</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function generateManuscriptAuthorTableData(itemvalue){
	var dataRow="<tr>" +
	"<td>" +itemvalue.manuscriptDto.name+"</td>"+
	"<td>" +itemvalue.authorDto.name+"</td>"+
	"<td>" +itemvalue.publisher+"</td>"+
	"<td>" +
	"<a href=# onclick=deleteManuscriptAuthorObject("+itemvalue.manuscriptDto.id+","+itemvalue.authorDto.id+")>Delete</a>"+
	"</td>"+
	"</tr>";
	return dataRow;
}

function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Manuscript Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicManuscriptDropdown = "<select id='manuscriptSelectBox'><option value=''>Select Manuscript</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateManuscriptTableData(itemvalue);
		dataCombo = dataCombo + "<option value="+itemvalue.id+">"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicManuscriptDropdown = dynamicManuscriptDropdown+dataCombo+"</select>";
	document.getElementById("manuscriptFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("manuscriptCombo").innerHTML=dynamicManuscriptDropdown;
}

function processAuthorResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Author Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dynamicAuthorDropdown = "<select id='authorSelectBox'><option value=''>Select Author</option>";
	
	var dataRow="";
	var dataCombo = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateAuthorTableData(itemvalue);
		dataCombo = dataCombo + "<option value='"+itemvalue.id+"'>"+itemvalue.name+"</option>";
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	dynamicAuthorDropdown = dynamicAuthorDropdown+dataCombo+"</select>";
	document.getElementById("authorFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("authorCombo").innerHTML=dynamicAuthorDropdown;
}

function processManuscriptAuthorResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Manuscript Name</td>"+"<td>Author Name</td>"+"<td>Publisher Name</td>"+"<td>Actions</td>"+
	"</tr>";
	
	var dataRow="";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateManuscriptAuthorTableData(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("manuscriptAuthorFormResponse").innerHTML=dyanamicTableRow;
}