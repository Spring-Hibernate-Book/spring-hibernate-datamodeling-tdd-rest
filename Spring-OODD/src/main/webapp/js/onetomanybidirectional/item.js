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
	var feature=document.getElementsByName("feature");
	if(feature.length > 0){numbers =  new Array();for(var i=0;i<feature.length;i++)
	{
		if(trim(feature[i].value)!=""){
			numbers.push(feature[i].value);flag=true;
		}
	}
	}
	if(flag==false){numbers=null;}
	var formData={"name":name,"featureList":numbers};
	$.ajax({url : "/Spring-OODD/onetomanybidirectional/create",type: "POST",
		data : JSON.stringify(formData),beforeSend: function(xhr) {xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");},
		success: function(data, textStatus, jqXHR){
			document.getElementById("name").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
			initiateItemBoxGroup();

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("name").value="";
			initiateItemBoxGroup();
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}

function loadObjects(){
	$.ajax({
		url : "/Spring-OODD/onetomanybidirectional/findAll",type: "GET",data : {},dataType: "json",
		success: function(data, textStatus, jqXHR){processResponseData(data);},
		error: function (jqXHR, textStatus, errorThrown){
			document.getElementById("customername").value="";
			alert("Error Status Load Objects:"+textStatus);}
	});
	return false;
}
function processResponseData(objects){
	var dyanamicTableRow="<table border=1>"+"<tr>" +"<td>Item Name</td>"+"<td>Feature</td>"+"<td>Actions</td>"+"</tr>";
	var dataRow="";
	$.each(objects, function(itemno, object){dataRow=dataRow+generateTableData(object);});
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	document.getElementById("itemFormResponse").innerHTML=dyanamicTableRow;
}
function generateTableData(object){
	var features = object.featureList;
	var dataRow="<tr>" +"<td>" +object.name+"</td>";dataRow = dataRow+"<td>"+"<select>"+"<option>"+"</option>";
	if(features!=null && features.length > 0){for(var index=0;index<features.length;index++)
	{ var feature = features[index];dataRow = dataRow+"<option value="+feature+">"+feature+"</option>";}}
	dataRow=dataRow+"</select></td>";
	dataRow=dataRow+"<td>" +"<a href=# onclick=deleteObject("+object.id+")>Delete</a>"+
	"|<a href=# onclick=editObject("+object.id+")>Edit</a>"+"</td>"+"</tr>";
	return dataRow;
}
function addMoreFeature(){
	counter++;
	var itemDiv = document.createElement('div');
	itemDiv.setAttribute("id","itemBox"+"_"+counter);
	var remId="removeLink_"+counter;
	var removeLinkUrl = "|<label id="+remId+"><a href='#' onclick='removeFeature("+counter+")'>(-)</a></label>";
	var itemBoxGroupDiv = document.getElementById("itemBoxGroup");
	itemDiv.innerHTML = "<label>Feature:"+"<input type='text' id='feature' name='feature' maxlength='12' size='10'/>" +
	"</label>"+removeLinkUrl;
	itemBoxGroupDiv.appendChild(itemDiv);
}
function removeFeature(index){
	var featurelist=document.getElementsByName("feature");
	var size = featurelist.length;
	if(size==1){counter=1;return false;}
	var itemBoxGroupDiv = document.getElementById("itemBoxGroup");
	itemBoxGroupDiv.removeChild(document.getElementById("itemBox"+"_"+index));
}
function deleteObject(itemid){
	var itemForm={id:itemid};
	delurl="/Spring-OODD/onetomanybidirectional/remove/"+itemid;
	$.ajax({url : delurl,type: "POST",data : itemForm,dataType: "json",
		success: function(data, textStatus, jqXHR){loadObjects();},
		error: function (jqXHR, textStatus, errorThrown){alert("Error Status Delete:"+textStatus);}
	});
}
function update(){
	var name = $("#name").val();
	var id = +$("#itemid").val();
	var flag= false;
	var feature=document.getElementsByName("feature");
	if(feature.length > 0){numbers =  new Array();for(var i=0;i<feature.length;i++){if(trim(feature[i].value)!=""){numbers.push(feature[i].value);flag=true;}}}
	if(flag==false){numbers=null;}
	var formData={"id":id,"name":name,"featureList":numbers};
	$.ajax({
		url : "/Spring-OODD/onetomanybidirectional/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("name").value="";
			initiateItemBoxGroup();
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("customername").value="";
			initiateItemBoxGroup();
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function editObject(itemid){
	editurl="/Spring-OODD/onetomanybidirectional/findById/"+itemid;
	var itemForm={id:itemid};
	$.ajax({url : editurl,type: "GET",data : itemForm,dataType: "json",
		success: function(data, textStatus, jqXHR){
			viewObject(data);
			document.getElementById("subButton").value="Update";},
			error: function (jqXHR, textStatus, errorThrown){alert("Error Status Find Object:"+textStatus);}
	});
}
function viewObject(data){
	var index = 1;
	document.getElementById("name").value=data.name;
	document.getElementById("itemid").value=data.id;
	var numbers = data.featureList;
	if(numbers!=null){
		initiateItemBoxGroup();
		var itemBoxGroupDiv = document.getElementById("itemBoxGroup");
		for(var cnt=0;cnt<numbers.length;cnt++){
			if(cnt==0){
				var featurelist= document.getElementsByName("feature");
				featurelist[cnt].value=numbers[cnt];
			}else{
				index++; 
				var itemDiv = document.createElement('div');
				itemDiv.setAttribute("id","itemBox_"+index);
				var remId="removeLink_"+index;
				var removeLinkUrl = "|<label id="+remId+"><a href='#' onclick='removeFeature("+index+")'>(-)</a></label>";
				itemDiv.innerHTML = "<label>Feature:"+"<input type='text' id='feature' name='feature' maxlength='12' size='10' value='"+numbers[cnt]+"'/></label>"+removeLinkUrl;
				itemBoxGroupDiv.appendChild(itemDiv);
			}
		}
	}
}
function initiateItemBoxGroup(){
	var itemBoxGroupDiv = document.getElementById("itemBoxGroup");
	itemBoxGroupDiv.innerHTML="";
	var itemDiv = document.createElement('div');
	itemDiv.setAttribute("id","itemBox_1");
	var itemBox_1 = "<label>Feature:<input type='text' name='feature' id='feature' size='10' maxlength='12'><a href=# onclick=javascript:addMoreFeature()>(+)</a></label>";
	var removeLink_1="<label id='removeLink_1'></label>";
	var setUrl = itemBox_1+removeLink_1;
	itemDiv.innerHTML=setUrl;
	itemBoxGroupDiv.appendChild(itemDiv);
}
function trim (str){return str.replace (/^\s+|\s+$/g, '');}