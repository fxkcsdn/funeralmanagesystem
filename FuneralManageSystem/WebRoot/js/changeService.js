function changeService(){
	
	
	var tableName=document.getElementById("showallFuneralGoods");			//获取table中丧葬物品对象
	var len=tableName.rows.length;
	var numbers=0;
	var arrFuneralGoods = new Array();
	var jsonUrn="";
	var jsonGate="";
	var jsonMakeBeauty="";
	var jsonLeaveRoom="";
	var jsonCremation="";
	for(var i=2; i<len; i++){
		var goodsCheckBoxInput1=tableName.rows[i].cells[4].getElementsByTagName("input");
		if(goodsCheckBoxInput1[0].checked){
			var goodsName=tableName.rows[i].cells[0].innerText;
			

			var goodsBeCostCell=tableName.rows[i].cells[1];
			var goodsBeCostInput=goodsBeCostCell.getElementsByTagName("input");
			var goodsBeCost=goodsBeCostInput[0].value;
			
			var goodsRealCostInput=tableName.rows[i].cells[2].getElementsByTagName("input");
			var goodsChangeCost=goodsRealCostInput[0].value;
			var goodsRealCost=goodsBeCost-goodsChangeCost+"";
			arrFuneralGoods[numbers]="{goodsName:'"+goodsName+"',goodsBeCost:'"+goodsBeCost+"',goodsRealCost:'"+goodsRealCost+"'}";

			numbers++;
		}
		var goodsCheckBoxInput2=tableName.rows[i].cells[12].getElementsByTagName("input");
		if(goodsCheckBoxInput2[0]!=null){
			if(goodsCheckBoxInput2[0].checked){
			
				var goodsName=tableName.rows[i].cells[8].innerText;

				var goodsBeCostCell=tableName.rows[i].cells[9];
				var goodsBeCostInput=goodsBeCostCell.getElementsByTagName("input");
				var goodsBeCost=goodsBeCostInput[0].value;
				
				var goodsRealCostInput=tableName.rows[i].cells[10].getElementsByTagName("input");
				var goodsChangeCost=goodsRealCostInput[0].value;
				var goodsRealCost=goodsBeCost-goodsChangeCost+"";
				arrFuneralGoods[numbers]="{goodsName:'"+goodsName+"',goodsBeCost:'"+goodsBeCost+"',goodsRealCost:'"+goodsRealCost+"'}";

				numbers++;
			}
		}
	}
	
	if(document.form1.urnChooseBox.checked==true){
		var urnChoose=document.form1.urnChoose;
		var urnChooseIndex=urnChoose.selectedIndex;
		
		var urnName=urnChoose.options[urnChooseIndex].text;
		
		if(urnName!="-请选择-"){
			var urnBeCost=document.form1.urnBeCost.value;
			var urnChangeCost=document.form1.urnRealCost.value;
			var urnRealCost=urnBeCost-urnChangeCost+"";

			jsonUrn="{'urnName':'"+urnName+"','urnBeCost':'"+urnBeCost+"','urnRealCost':'"+urnRealCost+"'}";

		}
		else{
			jsonUrn="";
			alert("请确认已选择骨灰盒");
			document.form1.urnChoose.focus();
			return false;
		}
	}else{
		jsonUrn="";
	}
	
	var gateCremationType=document.getElementById("GatecremationType").value;
	var GatecremationTypeStatus=document.getElementById("GatecremationTypeStatus").value;
	    jsonGate="{'gateCremationType':'"+gateCremationType+"','GatecremationTypeStatus':'"+GatecremationTypeStatus+"'}";
	    
	if(document.form1.makeBeautyBox.checked==true){
		var makeBeautyGrade=document.form1.makeBeautyGrade;
		var makeBeautyGradeIndex=makeBeautyGrade.selectedIndex;
		var makeBeautyName=makeBeautyGrade.options[makeBeautyGradeIndex].text;
		var beautyStatus=document.form1.beautyStatus.value;
		var status;
	
		
		if(makeBeautyName!="-请选择-"){
			var beauty=document.form1.beauty.value;
			if(beauty!=makeBeautyName){
				status="0";
			}else{
				if(beautyStatus=="美容尚未开始"){
					status="0";
				}
				if(beautyStatus=="美容正在进行"){
					status="1";
				}
				if(beautyStatus=="美容结束"){
					status="2";
				}
				if(beautyStatus==""){
					
				}
				
			}
			var makeBeautyBeCost=document.form1.makeBeautyBeCost.value;
			var makeBeautyChangeCost=document.form1.makeBeautyRealCost.value;
			var makeBeautyRealCost=makeBeautyBeCost-makeBeautyChangeCost+"";

			jsonMakeBeauty="{'makeBeautyName':'"+makeBeautyName+"','makeBeautyBeCost':'"+makeBeautyBeCost+"','makeBeautyRealCost':'"+makeBeautyRealCost+"','status':'"+status+"'}";
		}
		else{
			jsonMakeBeauty="";
			alert("请确认已选择美容");
			document.form1.makeBeautyGrade.focus();
			return false;
		}
	}else{
		jsonMakeBeauty="";
	}
	
	if(document.form1.leaveRoomBox.checked==true){
		var leaveRoomGrade=document.form1.leaveRoomGrade;
		var leaveRoomGradeIndex=leaveRoomGrade.selectedIndex;
		var leaveRoomName=leaveRoomGrade.options[leaveRoomGradeIndex].text;
		var farewellStatus=document.form1.farewellStatus.value;
		var status;

		if(leaveRoomName!="-请选择-"){
			var farewell=document.form1.farewell.value;
			if(farewell!=leaveRoomName){
				status="0";
			}else{
				if(farewellStatus=="告别尚未开始"){
					status="0";
				}
				if(farewellStatus=="告别正在进行"){
					status="1";
				}
				if(farewellStatus=="告别结束"){
					status="2";
				}
				if(farewellStatus==""){
					
				}
				
			}
			var leaveRoomBeCost=document.form1.leaveRoomBeCost.value;
			var leaveRoomChangeCost=document.form1.leaveRoomRealCost.value;
			var leaveRoomRealCost=leaveRoomBeCost-leaveRoomChangeCost+"";
			jsonLeaveRoom="{'leaveRoomName':'"+leaveRoomName+"','leaveRoomBeCost':'"+leaveRoomBeCost+"','leaveRoomRealCost':'"+leaveRoomRealCost+"','status':'"+status+"'}";

		}
		else{
			jsonLeaveRoom="";
			alert("请确认已选择告别厅");
			document.form1.leaveRoomGrade.focus();
			return false;
		}
	}else{
		jsonLeaveRoom="";
	}
	
	if(document.form1.cremationBox.checked==true){
		var cremationGrade=document.form1.cremationGrade;
		var cremationGradeIndex=cremationGrade.selectedIndex;
		var cremationName=cremationGrade.options[cremationGradeIndex].text;
		var cremationStatus=document.form1.farewellStatus.value;
		var status;

		if(cremationName!="-请选择-"){
			var cremationstove=document.form1.cremationstove.value;
			if(cremationstove!=cremationName){
				status="0";
			}else{
				if(cremationStatus=="火化尚未开始"){
					status="0";
				}
				if(cremationStatus=="火化正在进行"){
					status="1";
				}
				if(cremationStatus=="火化结束"){
					status="2";
				}
				if(cremationStatus==""){
					
				}
				
			}
			var cremationBeCost=document.form1.cremationBeCost.value;
			var cremationChangeCost=document.form1.cremationRealCost.value;
			var cremationRealCost=cremationBeCost-cremationChangeCost+"";
			jsonCremation="{'cremationName':'"+cremationName+"','cremationBeCost':'"+cremationBeCost+"','cremationRealCost':'"+cremationRealCost+"','status':'"+status+"'}";

		}
		else{
			jsonCremation="";
			alert("请确认已选择火化炉");
			document.form1.cremationGrade.focus();
			return false;
		}
	}else{
		jsonCremation="";
	}
	
	var urn=jsonUrn+"";
	var gate=jsonGate+"";
	var makeBeauty=jsonMakeBeauty+"";
	var leaveRoom=jsonLeaveRoom+"";
	var cremation=jsonCremation+"";
	
	var funeralGoods=arrFuneralGoods+"";
	url="deadId="+document.form1.deadId.value+"&";
	url=url+"funeralGoods="+funeralGoods+"&";
	url=url+"urn="+urn+"&";
	url=url+"gate="+gate+"&";
	url=url+"makeBeauty="+makeBeauty+"&";
	url=url+"leaveRoom="+leaveRoom+"&";
	url=url+"cremation="+cremation;
	
	http_request=createHttpRequest();
	http_request.onreadystatechange=changeServiceCallBack;
	http_request.open('POST',"ChangeServiceAction!changeService",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function changeServiceCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			alert(http_request.responseText);
			
		}
	}
}
function ReadIDCardf() {
	clearForm();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillFormf();
		return;
	}

	alert("读卡错误,错误原因:" + ret);
}
function fillFormf() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;

	document.form1.servicedeadName.value = pName;
	document.form1.deadId.value = pCardNo;



}
function getCremationServicef() {

	document.getElementById("name").innerHTML="";
	document.getElementById("no").innerHTML="";
	document.getElementById("taxDate").innerHTML="";
	document.getElementById("sex").innerHTML="";
	document.getElementById("age").innerHTML="";
	document.getElementById("address").innerHTML="";
	
	document.getElementById("urn").innerHTML="";
	document.getElementById("urn1").innerHTML="";
	document.getElementById("urn2").innerHTML="";
	document.getElementById("face").innerHTML="";
	document.getElementById("face1").innerHTML="";
	document.getElementById("face2").innerHTML="";
	document.getElementById("farewell").innerHTML="";
	document.getElementById("farewell1").innerHTML="";
	document.getElementById("farewell2").innerHTML="";
	document.getElementById("cremation").innerHTML="";
	document.getElementById("cremation1").innerHTML="";
	document.getElementById("cremation2").innerHTML="";

	document.getElementById("car").innerHTML="";
	document.getElementById("car1").innerHTML="";
	document.getElementById("car2").innerHTML="";

	document.getElementById("currentDateTh2").innerHTML="";
//	document.getElementById("no").innerHTML="";
	var allGoodsTbody=document.getElementById("alladd");
	var allFuneralGoodsRows=allGoodsTbody.rows.length;
	for(var n=0;n<allFuneralGoodsRows;n++){
//		var goodsName1=allGoods.rows[n].cells[0].innerText;
		allGoodsTbody.rows[n].cells[1].innerHTML="";
		allGoodsTbody.rows[n].cells[2].innerHTML="";
		allGoodsTbody.rows[n].cells[3].innerHTML="";
		var goods2=allGoodsTbody.rows[n].cells[4].innerText;
		if(goods2!=""){
			allGoodsTbody.rows[n].cells[5].innerHTML="";
			allGoodsTbody.rows[n].cells[6].innerHTML="";
			allGoodsTbody.rows[n].cells[7].innerHTML="";
		}
		var goods3=allGoodsTbody.rows[n].cells[8].innerText;
		if(goods3!=""){
			allGoodsTbody.rows[n].cells[9].innerHTML="";
			allGoodsTbody.rows[n].cells[10].innerHTML="";
			allGoodsTbody.rows[n].cells[11].innerHTML="";
		}
		
	}
	
	document.getElementById("total").innerHTML="";
	document.getElementById("beCost").innerHTML="";
	document.getElementById("charge").innerHTML="";
	document.getElementById("capitalMoney").innerHTML="";

	
	document.getElementById("tableExcel1").style.display="none";
	
	
	var deadId = document.getElementById("deadId").value;
	
	url = "deadId=" + deadId;

		
	
	if(deadId==null||deadId.toString().length==0)
		return false;
	var data = "deadId="+deadId;
	var url = "UpdateServiceAction!UpdateService";
	sendRequest("post", url, data, getCremationServicefBack);
	

	

}
var getCremationServicefBack = function(result) 
{	
	
	var serviceDetail = eval("(" + result + ")");
	
	serviceDetail = eval("(" + serviceDetail + ")");
	document.getElementById("servicedeadName").value="";
	document.getElementById("deadId").value="";
	document.getElementById("urnName").value="";
	document.getElementById("beauty").value="";
	document.getElementById("farewell").value="";
	document.getElementById("farewellStatus").value="";
	document.getElementById("cremationstove").value="";
	document.getElementById("cremationStatus").value="";

	var urnChooseBox=document.getElementById("urnChooseBox");
	var makeBeautyBox=document.getElementById("makeBeautyBox");
	var leaveRoomBox=document.getElementById("leaveRoomBox");
	var cremationBox=document.getElementById("cremationBox");
  
		
	var service = serviceDetail[0].service;
	var goods = serviceDetail[0].goods;
//	alert(goods[0].goodsName);

	
	document.getElementById("servicedeadName").value=service[0].deadName;
	document.getElementById("deadId").value=service[0].deadId;
	var length = service.length;
	for(var i=0;i<length;i++){
		var CremationTypeNo = service[i].CremationTypeNo;
		
		var status = service[i].status;
		var deadId1=service[i].deadId;
		
		if(CremationTypeNo=="03"&&status=="2"){
			document.getElementById("cremationstove").value=service[i].itemName;
			document.getElementById("cremationBeCost").value = Number(service[i].itemRealCost);
			document.getElementById("cremationRealCost").value = service[i].itemRealCost;
							
			document.getElementById("preCremationBeCost").value = Number(service[i].itemBeCost);
			
			document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;
			document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
			
			document.getElementById("cremationstove").disabled = "true";
							
			document.getElementById("cremationStatus").value = "火化结束";
			cremationBox.disabled="true";
			document.getElementById("cremationStatus").disabled = "true";
			document.getElementById("cremationGrade").disabled = "true";
			document.getElementById("cremationBeCost").disabled = "true";
			document.getElementById("cremationRealCost").disabled = "true";						
		} 
		if(CremationTypeNo=="03"&&status=="1"){

			document.getElementById("cremationstove").value=service[i].itemName;
			document.getElementById("cremationBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("cremationRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
			document.getElementById("preCremationBeCost").value = Number(service[i].itemBeCost);			
			document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
			
			
			document.getElementById("cremationstove").disabled = "true";
			document.getElementById("cremationStatus").value = "火化正在进行";
			document.getElementById("cremationStatus").disabled = "true";
			document.getElementById("cremationGrade").disabled = "true";
			document.getElementById("cremationBeCost").disabled = "true";
			document.getElementById("cremationRealCost").disabled = "true";
				
			}
		if(CremationTypeNo=="03"&&status=="0")
			{
			document.getElementById("cremationstove").value=service[i].itemName;
			document.getElementById("cremationBeCost").value = Number(service[i].itemBeCost);
			
			document.getElementById("cremationRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
			document.getElementById("preCremationBeCost").value = Number(service[i].itemBeCost);				
			document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
			
			
			document.getElementById("cremationstove").disabled = "true";
			document.getElementById("cremationStatus").value = "火化尚未开始";
			document.getElementById("cremationStatus").disabled = "true";
			document.getElementById("cremationGrade").disabled = "";
			document.getElementById("cremationBeCost").disabled = "";
			document.getElementById("cremationRealCost").disabled = "";
				
			}
		if(CremationTypeNo=="02"&&status=="2"){
			
			document.getElementById("farewell").value=service[i].itemName;
			document.getElementById("leaveRoomBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("leaveRoomRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
			document.getElementById("preLeaveRoomBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
			
			document.getElementById("farewell").disabled = "true";
			document.getElementById("farewellStatus").value = "告别结束";
			
			leaveRoomBox.disabled="true";
			document.getElementById("farewellStatus").disabled = "true";
			document.getElementById("leaveRoomBeCost").disabled = "true";
			document.getElementById("leaveRoomRealCost").disabled = "true";
			document.getElementById("leaveRoomGrade").disabled = "true";
			}
			if(CremationTypeNo=="02"&&status=="1"){
				document.getElementById("farewell").value=service[i].itemName;
				document.getElementById("leaveRoomBeCost").value = Number(service[i].itemBeCost);
				document.getElementById("leaveRoomRealCost").value = Number(service[i].itemRealCost);
				document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
				document.getElementById("preLeaveRoomBeCost").value = Number(service[i].itemBeCost);
				document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
			document.getElementById("farewell").disabled = "true";
			document.getElementById("farewellStatus").value = "告别正在进行";
			document.getElementById("farewellStatus").disabled = "true";
			document.getElementById("leaveRoomBeCost").disabled = "";
			document.getElementById("leaveRoomRealCost").disabled = "";
			document.getElementById("leaveRoomGrade").disabled = "";
				
			}
			if(CremationTypeNo=="02"&&status=="0"){
				document.getElementById("farewell").value=service[i].itemName;
				document.getElementById("leaveRoomBeCost").value = Number(service[i].itemBeCost);
				
				document.getElementById("leaveRoomRealCost").value = Number(service[i].itemRealCost);
				document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
				document.getElementById("preLeaveRoomBeCost").value = Number(service[i].itemBeCost);
				document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
			
			document.getElementById("farewell").disabled = "true";
			document.getElementById("farewellStatus").value = "告别尚未开始";
			document.getElementById("farewellStatus").disabled = "true";
			document.getElementById("leaveRoomBeCost").disabled = "";
			document.getElementById("leaveRoomRealCost").disabled = "";
			document.getElementById("leaveRoomGrade").disabled = "";
				
			}
		
			
		if(CremationTypeNo=="01"&&status=="2"){				
			
			document.getElementById("beauty").value=service[i].itemName;					
			document.getElementById("makeBeautyBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("makeBeautyRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
			document.getElementById("preMakeBeautyBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
			document.getElementById("beauty").disabled = "true";
			document.getElementById("beautyStatus").value = "美容结束";
			makeBeautyBox.disabled="true";
			document.getElementById("beautyStatus").disabled = "true";
			document.getElementById("makeBeautyBeCost").disabled = "true";
			document.getElementById("makeBeautyRealCost").disabled = "true";
			document.getElementById("makeBeautyGrade").disabled = "true";
		}
		if(CremationTypeNo=="01"&&status=="1"){
			document.getElementById("beauty").value=service[i].itemName;					
			document.getElementById("makeBeautyBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("makeBeautyRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
			document.getElementById("preMakeBeautyBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
			
			document.getElementById("beauty").disabled = "true";
			document.getElementById("beautyStatus").value = "美容正在进行";
			document.getElementById("beautyStatus").disabled = "true";
			document.getElementById("makeBeautyBeCost").disabled = "";
			document.getElementById("makeBeautyRealCost").disabled = "";
			document.getElementById("makeBeautyGrade").disabled = "";
			
			}
		if(CremationTypeNo=="01"&&status=="0"){
			
			document.getElementById("beauty").value=service[i].itemName;	
								
			document.getElementById("makeBeautyBeCost").value = Number(service[i].itemBeCost);					
			document.getElementById("makeBeautyRealCost").value = Number(service[i].itemRealCost);
			document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyBeCost").value-document.getElementById("makeBeautyRealCost").value;					
			document.getElementById("preMakeBeautyBeCost").value = Number(service[i].itemBeCost);
			document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
		   				   
           document.getElementById("beauty").disabled = "true";
           document.getElementById("beautyStatus").value = "美容尚未开始";
           document.getElementById("beautyStatus").disabled = "true";
           document.getElementById("makeBeautyBeCost").disabled = "";
           document.getElementById("makeBeautyRealCost").disabled = "";
           document.getElementById("makeBeautyGrade").disabled = "";
			
		}
		if(CremationTypeNo=="00"){
			
			   document.getElementById("GatecremationType").value=service[i].itemName;					
			   document.getElementById("GatecremationType").value = status;
				
			}
									
			}
	document.getElementById("servicedeadName").value = service[0].deadName;
	document.getElementById("deadId").value = service[0].deadId;	
	if(service[0].urnName!=null){
	document.getElementById("urnBeCost").value = Number(service[0].urnBeCost);
	document.getElementById("urnRealCost").value = Number(service[0].urnRealCost);

	document.getElementById("urnName").value = service[0].urnName;
	
	document.getElementById("urnRealCost").value=Number(document.getElementById("urnBeCost").value)-Number(document.getElementById("urnRealCost").value);
	document.getElementById("preUrnRealCost").value = Number(document.getElementById("urnRealCost").value);
	document.getElementById("preUrnBeCost").value = Number(service[0].urnBeCost);
	}
	 var allBeCost=document.getElementById("allBeCost").value;
     
     var allRealCost=document.getElementById("allRealCost").value;

		var cremationBeCost=document.getElementById("cremationBeCost").value;
		var cremationRealCost=document.getElementById("cremationRealCost").value;
		var makeBeautyBeCost=document.getElementById("makeBeautyBeCost").value;
		var makeBeautyRealCost=document.getElementById("makeBeautyRealCost").value;
		var leaveRoomBeCost=document.getElementById("leaveRoomBeCost").value;
		var leaveRoomRealCost=document.getElementById("leaveRoomRealCost").value;
		var urnBeCost=document.getElementById("urnBeCost").value;
		var urnRealCost=document.getElementById("urnRealCost").value;

		document.getElementById("allBeCost").value=Number(document.getElementById("allBeCost").value)+Number(cremationBeCost)+Number(makeBeautyBeCost)+Number(leaveRoomBeCost)+Number(urnBeCost);
		document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(cremationRealCost)+Number(makeBeautyRealCost)+Number(leaveRoomRealCost)+Number(urnRealCost);


		var tb=document.getElementById("showallFuneralGoods");
		var rows=tb.rows.length;
		var cells=tb.rows[1].cells.length;
		
		var count = goods.length;
		
		for(var i = 0; i <count; i++){
//			var goods = serviceDetail[0].goods;
			
			var goodsName=goods[i].goodsName;
			
			var goodsRealCost=goods[i].goodsRealCost;
			var goodsBeCost=goods[i].goodsBeCost;
			

			for(var j=2;j<rows;j++){
				var goodName1=tb.rows[j].cells[0].innerText;
				var goodsName2=tb.rows[j].cells[8].innerText;
				
				if(goodsName==goodName1){
					var goodsCheckBoxInput1=tb.rows[j].cells[4].getElementsByTagName("input");
					goodsCheckBoxInput1[0].checked=true;
					var goosBeCost1 =tb.rows[j].cells[1].getElementsByTagName("input");
					goosBeCost1[0].value=goodsBeCost;
					chooseFuneralGoods1(goodsCheckBoxInput1[0]);
//					changeGoodBeCost1(goosBeCost1[0]);
					var reduce=tb.rows[j].cells[2].getElementsByTagName("input");
					reduce[0].value=Number(goodsBeCost)-Number(goodsRealCost);
//					changeGoodsCost1(reduce[0]);
					
											
				}
				if(goodsName==goodsName2){
//					(goodsBeCost);
					var goosBeCost2 =tb.rows[j].cells[9].getElementsByTagName("input");
					goosBeCost2[0].value=goodsBeCost;
					
					var goodsCheckBoxInput2=tb.rows[j].cells[12].getElementsByTagName("input");
					goodsCheckBoxInput2[0].checked=true;
					chooseFuneralGoods2(goodsCheckBoxInput2[0]);
					
					var reduce1=tb.rows[j].cells[10].getElementsByTagName("input");
					reduce1[0].value=Number(goodsBeCost)-Number(goodsRealCost);
//					changeGoodsCost2(reduce1[0]);

					
				}
									
			}
	}
		var table2=document.getElementById("showallFuneralGoods");
		var rows=table2.rows.length;
		
		for(var k=2;k<rows;k++){
			var reduceMoney=table2.rows[k].cells[2].getElementsByTagName("input");
			var reduceMoney1=table2.rows[k].cells[10].getElementsByTagName("input");
			
			var hideMoney=table2.rows[k].cells[5];
			var hideValue=hideMoney.getElementsByTagName("input");
			    hideValue[0].value=reduceMoney[0].value;
			    
			var hideMoney1=table2.rows[k].cells[7];
			var hideValue1=hideMoney1.getElementsByTagName("input");
				hideValue1[0].value=reduceMoney1[0].value;
			
			
			if(reduceMoney[0].value!=0){
				document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(reduceMoney[0].value);
			}
			if(reduceMoney1[0].value!=0){
				document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(reduceMoney1[0].value);
				
			}
			
		}
		document.getElementById("theWholeCost").value=Number(document.getElementById("allBeCost").value)-Number(document.getElementById("allRealCost").value);
		
		if(document.getElementById("urnName").value!=null){
			urnChooseBox.checked=true;			
			var urnChoose=document.getElementById("urnChoose");				
			setSelected(urnChoose,document.getElementById("urnName").value);
			
		}
		if(document.getElementById("urnName").value==""){
			urnChooseBox.checked=false;

			var urnChoose=document.getElementById("urnChoose");
				urnChoose.disabled="true";
				document.getElementById("urnBeCost").disabled="true";
				document.getElementById("urnRealCost").disabled="true";
			setSelected(urnChoose,document.getElementById("urnName").value);				
		}
		
		if(document.getElementById("beauty").value!=null){
						
			makeBeautyBox.checked=true;
			
			var makeBeautyGrade=document.getElementById("makeBeautyGrade");
									
			setSelected(makeBeautyGrade,document.getElementById("beauty").value)
			
		}
		if(document.getElementById("beauty").value==""){
//			alert("美容为空");
			makeBeautyBox.checked=false;
			
			var urnChoose=document.getElementById("makeBeautyGrade");
			makeBeautyGrade.disabled="true";
				document.getElementById("makeBeautyBeCost").disabled="true";
				document.getElementById("makeBeautyRealCost").disabled="true";
			setSelected(makeBeautyGrade,document.getElementById("beauty").value);
		}
		if(document.getElementById("farewell").value!=null){
			
			leaveRoomBox.checked=true;
			
			var leaveRoomGrade=document.getElementById("leaveRoomGrade");
			
			setSelected(leaveRoomGrade,document.getElementById("farewell").value)			
			
		}
		if(document.getElementById("farewell").value==""){
//			alert("告别为空");
			leaveRoomBox.checked=false;
			
			var leaveRoomGrade=document.getElementById("leaveRoomGrade");
				leaveRoomGrade.disabled="true";
				document.getElementById("leaveRoomBeCost").disabled="true";
				document.getElementById("leaveRoomRealCost").disabled="true";
			setSelected(leaveRoomGrade,document.getElementById("farewell").value);
			
		}
		if(document.getElementById("cremationstove").value!=null){
//			alert("火化炉不为空");
							
			cremationBox.checked=true;
			
			var cremationGrade=document.getElementById("cremationGrade");
			
			setSelected(cremationGrade,document.getElementById("cremationstove").value)
			
		}
		if(document.getElementById("cremationstove").value==""){
//			alert("火化炉为空");
			cremationBox.checked=false;
			
			var cremationGrade=document.getElementById("cremationGrade");
				cremationGrade.disabled="true";
				document.getElementById("cremationBeCost").disabled="true";
				document.getElementById("cremationRealCost").disabled="true";
			setSelected(cremationGrade,document.getElementById("cremationstove").value);
			
		}
		
};

