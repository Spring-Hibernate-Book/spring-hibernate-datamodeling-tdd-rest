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
	var name = $("#name").val();
	var parentId = document.getElementById("parentId").value;
	if(parentId !=null){
		parentId = parseInt(parentId);
	}
	var formData ={"name":name,"parentId":parentId};
	$.ajax({
		url : "/Spring-OODD/onetomanyselfreference/mock/create",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("name").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			document.getElementById("parentId").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#name").val();
	var id = $("#Id").val();
	var parentId = $("#parentId").val();
	if(id==parentId){
		alert("Parent Id and Owner Id are same. Please choose a different parent.");
		return false;
	}
	var formData={"id":id,"name":name,"parentId":parentId};
	$.ajax({
		url : "/Spring-OODD/onetomanyselfreference/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("name").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function deleteObject(Id){
	var productForm={id:Id};
	delurl="/Spring-OODD/onetomanyselfreference/mock/remove/"+Id;
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
function editObject(Id){
	editurl="/Spring-OODD/onetomanyselfreference/mock/findById/"+Id;
	var itemForm={id:Id};
	$.ajax({
		url : editurl,
		type: "GET",
		data : itemForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR){
			viewObject(data);
			document.getElementById("subButton").value="Update";
			document.getElementById("Id").value=Id;
		},
		error: function (jqXHR, textStatus, errorThrown){
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewObject(data){
	document.getElementById("name").value=data.name;
	var _pId = data.parentId ;
	setComboBoxValue(_pId);
}
function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/onetomanyselfreference/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	return false;
}
function processResponseData(responsedata){
	var dyanamicTableRow="<table border=1>"+
	"<tr>" +
	"<td>Category</td>"+"<td>Parent_Id</td>"+
	"<td>Actions</td>"+
	"</tr>";
	var parentDropdown = "<select  id=parentId>";
	var dataRow="";
	var dropdown = "";
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue,itemno);
		dropdown =dropdown+createDropDown(itemvalue);
	});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	if(dropdown!=""){
		parentDropdown = parentDropdown+dropdown;
	}
	parentDropdown = parentDropdown+"</select>";
	document.getElementById("categoryFormResponse").innerHTML=dyanamicTableRow;
	document.getElementById("parentIdCombo").innerHTML=parentDropdown;
}
function generateTableData(itemvalue,itemNo){
	var parentId = itemvalue.parentId;
	if(null == parentId) parentId="";
	var dataRow="";
	if(parentId!=""){
		dataRow="<tr>" +
		"<td>" +itemvalue.name+"</td>"+
		"<td>"+parentId+"</td>"+
		"<td>" +
		"<a href=# onclick=deleteObject("+itemvalue.id+")>Delete</a>"+
		"|<a href=# onclick=editObject("+itemvalue.id+")>Edit</a>"+
		"</td>"+
		"</tr>";
	}else{
		dataRow="<tr>" +
		"<td>" +itemvalue.name+"</td>"+
		"<td>"+parentId+"</td>"+"<td></td>"+"</tr>";
	}
	return dataRow;
}
function createDropDown(itemvalue){
	var _Id = itemvalue.id;
	var dropDown = "";
	if(null !=_Id && _Id!=""){
		dropDown = "<option value='"+_Id+"'>"+_Id+"</option>";
	}
	return dropDown ;
}
function setComboBoxValue(_pId){
	var parentIdCombo = document.getElementById("parentId");
	if(null !=parentIdCombo){
		var parentDropdown = "<select  id=parentId>";
		for(var index = 0;index<parentIdCombo.length;index++){
			var _Id =parentIdCombo.options[index].value ;
			var dropDown = "";
			if(null !=_Id && _Id!=""){
				if(_pId == _Id){
					dropDown = "<option value='"+_Id+"' selected='selected'>"+_Id+"</option>";
				}else
					dropDown = "<option value='"+_Id+"'>"+_Id+"</option>";
			}
			parentDropdown = parentDropdown + dropDown ;
		}
		parentDropdown = parentDropdown +"</select>";
		document.getElementById("parentIdCombo").innerHTML=parentDropdown;
	}
}