var counter = 1;
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
	var numbers = null;
	var flag= false;
	var phone=document.getElementsByName("phone");
	if(phone.length > 0){
		numbers =  new Array();
		for(var i=0;i<phone.length;i++){
			if(trim(phone[i].value)!=""){
				numbers.push(phone[i].value);
				flag=true;
			}
		}
	}
	if(flag==false){numbers=null;}
	var formData={"name":name,"numbers":numbers};
	$.ajax({url : "/Spring-OODD/onetomanyunidirectional/mock/create",
			type: "POST",data : 
			JSON.stringify(formData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(data, textStatus, jqXHR){
				document.getElementById("name").value="";
				document.getElementById("subButton").value="Create";
				loadObjects();
				initiatePhoneBoxGroup();

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			initiatePhoneBoxGroup();
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/onetomanyunidirectional/mock/findAll",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR){
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown){
			document.getElementById("name").value="";
			alert("Error Status Load Objects:"+textStatus);
		}
	});
	return false;
}
function initiatePhoneBoxGroup(){
	var phoneBoxGroupDiv = document.getElementById("phoneBoxGroup");
	phoneBoxGroupDiv.innerHTML="";
	var phoneDiv = document.createElement('div');
	phoneDiv.setAttribute("id","phoneBox_1");
	var phoneBox_1 = "<label>Phone:<input type='text' name='phone' id='phone' size='10' maxlength='12'><a href=# onclick=javascript:addMorePhone()>(+)</a></label>";
	var removeLink_1="<label id='removeLink_1'></label>";
	var setUrl = phoneBox_1+removeLink_1;
	phoneDiv.innerHTML=setUrl;
	phoneBoxGroupDiv.appendChild(phoneDiv);
}
function processResponseData(objects){
	var dyanamicTableRow="<table border=1>"+"<tr>" +"<td>Person Name</td>"+"<td>Numbers</td>"+"<td>Actions</td>"+"</tr>";
	var dataRow="";
	$.each(objects, function(itemno, object){dataRow=dataRow+generateTableData(object);});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("personFormResponse").innerHTML=dyanamicTableRow;
}
function generateTableData(object){
	var phones = object.numbers;
	var dataRow="<tr>" +"<td>" +object.name+"</td>";dataRow = dataRow+"<td>"+"<select>"+"<option>"+"</option>";
	if(phones!=null && phones.length > 0){
		for(var index=0;index<phones.length;index++){ 
			var phone = phones[index];
			dataRow = dataRow+"<option value="+phone+">"+phone+"</option>";
		}
	}
	dataRow=dataRow+"</select></td>";dataRow=dataRow+"<td>" +"<a href=# onclick=deleteObject("+object.id+")>Delete</a>"+"|<a href=# onclick=editObject("+object.id+")>Edit</a>"+"</td>"+"</tr>";
	return dataRow;
}
function addMorePhone(){
	counter++;
	var phoneDiv = document.createElement('div');
	phoneDiv.setAttribute("id","phoneBox"+"_"+counter);
	var remId="removeLink_"+counter;
	var removeLinkUrl = "|<label id="+remId+"><a href='#' onclick='removePhone("+counter+")'>(-)</a></label>";
	var phoneBoxGroupDiv = document.getElementById("phoneBoxGroup");
	phoneDiv.innerHTML = "<label>Phone:"+"<input type='text' id='phone' name='phone' mqxlength='12' size='10'/></label>"+removeLinkUrl;
	phoneBoxGroupDiv.appendChild(phoneDiv);
}
function removePhone(index){
	var phonelist=document.getElementsByName("phone");
	var size = phonelist.length;
	if(size==1){counter=1;return false;}
	var phoneBoxGroupDiv = document.getElementById("phoneBoxGroup");
	phoneBoxGroupDiv.removeChild(document.getElementById("phoneBox"+"_"+index));
}
function deleteObject(personid){
	var personForm={id:personid};
	delurl="/Spring-OODD/onetomanyunidirectional/mock/remove/"+personid;
	$.ajax({url : delurl,
		  	type: "POST",
		  	data : personForm,
		  	dataType: "json",
		  	success: function(data, textStatus, jqXHR){
		  		loadObjects();
		  	},
		  	error: function (jqXHR, textStatus, errorThrown){
		  		alert("Error Status Delete:"+textStatus);
		  	}
	});
}
function update(){
	var name = $("#name").val();
	var id = +$("#personid").val();
	var flag= false;
	var phone=document.getElementsByName("phone");
	if(phone.length > 0){
		numbers =  new Array();
		for(var i=0;i<phone.length;i++){
			if(trim(phone[i].value)!=""){
				numbers.push(phone[i].value);
				flag=true;
			}
		}
	}
	if(flag==false){numbers=null;}
	var formData={"id":id,"name":name,"numbers":numbers};
	$.ajax({
		url : "/Spring-OODD/onetomanyunidirectional/mock/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("name").value="";
			initiatePhoneBoxGroup();
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			initiatePhoneBoxGroup();
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function editObject(personid){
	editurl="/Spring-OODD/onetomanyunidirectional/mock/findById/"+personid;
	var personForm={id:personid};
	$.ajax({url : editurl,
			type: "GET",
			data : personForm,
			dataType: "json",
			success: function(data, textStatus, jqXHR){
				viewObject(data);
				document.getElementById("subButton").value="Update";
			},
			error: function (jqXHR, textStatus, errorThrown){
				alert("Error Status Find Object:"+textStatus);
			}
	});
}
function viewObject(data){
	var index = 1;
	document.getElementById("name").value=data.name;
	document.getElementById("personid").value=data.id;
	var numbers = data.numbers;
	if(numbers!=null){
		initiatePhoneBoxGroup();
		var phoneBoxGroupDiv = document.getElementById("phoneBoxGroup");
		for(var cnt=0;cnt<numbers.length;cnt++){
			if(cnt==0){
				var phones= document.getElementsByName("phone");
				phones[cnt].value=numbers[cnt];
			}else{
				index++; 
				var phoneDiv = document.createElement('div');
				phoneDiv.setAttribute("id","phoneBox_"+index);
				var remId="removeLink_"+index;
				var removeLinkUrl = "|<label id="+remId+"><a href='#' onclick='removePhone("+index+")'>(-)</a></label>";
				phoneDiv.innerHTML = "<label>Phone:"+"<input type='text' id='phone' name='phone' mqxlength='12' size='10' value='"+numbers[cnt]+"'/></label>"+removeLinkUrl;
				phoneBoxGroupDiv.appendChild(phoneDiv);
			}
		}
	}
}
function trim (str){return str.replace (/^\s+|\s+$/g, '');}