
function ReadIDCard(){
	clearForm();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0"){
	   fillForm();
	   return;
	} 
	alert("读卡错误,错误原因:" + ret);
}
function fillForm() {   
	  var pName=CVR_IDCard.Name;
	  var pCardNo=CVR_IDCard.CardNo;

	  document.form1.deadName.value = pName; 
	  document.form1.deadId.value = pCardNo;
}
function clearForm() {
	  document.form1.deadName.value = ""; 
	  document.form1.deadId.value = "";
}


function ReadIDCard2(){
	clearForm2();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0"){
	   fillForm2();
	   return;
	} 
	alert("读卡错误,错误原因:" + ret);
}
function fillForm2() {   
	var pName=CVR_IDCard.Name;
	var pCardNo=CVR_IDCard.CardNo;
	var pAddress=CVR_IDCard.Address;
    var pBorn=CVR_IDCard.Born;
    var nowDate=new Date();
    var currentTime=nowDate.getFullYear();
    var bornDate=pBorn.substr(0,4);
    var pAge=currentTime-bornDate+1;
    var pSex = CVR_IDCard.Sex;
	document.form2.deadName.value = pName; 
	document.form2.deadId.value = pCardNo;
	document.form2.deadAddress.value=pAddress;
	document.form2.deadAge.value=pAge;
	if (pSex == "1") {
		document.form2.deadSex2.selectedIndex = 0;
	}
	if (pSex == "0") {
		document.form2.deadSex2.selectedIndex = 1;
	}
}
function clearForm2() {
	document.form2.deadName.value = ""; 
	document.form2.deadId.value = "";
	document.form2.deadAddress.value="";
	document.form2.deadAge.value="";
}
function ReadIDCardForQRCode(){
	document.form3.deadId.value = "";
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0"){
	   var pCardNo=CVR_IDCard.CardNo;
	   document.form3.deadId.value = pCardNo;
	   return;
	} 
	alert("读卡错误,错误原因:" + ret);
}
function ReadIDCardForService(){
	document.form4.deadId.value = "";
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0"){
	   var pCardNo=CVR_IDCard.CardNo;
	   document.form4.deadId.value = pCardNo;
	   return;
	} 
	alert("读卡错误,错误原因:" + ret);
}
/*—————————————————————————页面加载时获取下拉列表选项——————————————————————————*/

function familyOutPut(){	//打印家属二维码	
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    window.print();
    window.document.body.innerHTML=bdhtml;
}
function showUrnCallBack()
{
	if(http_request.readyState==4)
	{
		if(http_request.status==200)
		{
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			var jsonLength=json.length;
			var urnSelect=document.form4.urnChoose;
			for(var i=0;i<jsonLength;i++)
			{
				var new_opt = new Option(json[i].urnName, json[i].urnBeCost); 
				urnSelect.options.add(new_opt);
			}
		}
	}
}
function getSetServiceCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var urnSelect = document.getElementById("setService");
			for (var i = 0; i < jsonLength; i++) {
				var new_opt = new Option(json[i].setName, json[i].setName);
				urnSelect.options.add(new_opt);
			}
		}
	}
}
function showAllFuneralGoodsCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var testTbl = document.getElementById("allFuneralGoods");
			for (var i = 0; i < jsonLength; i++) {
				var row = testTbl.insertRow();

				var funeralGoodsCell1 = row.insertCell();
				var goodsBeCost1 = row.insertCell();
				var goodsRealCost1 = row.insertCell();
				var balanceNumber1=row.insertCell();
				var addGoodsButton1 = row.insertCell();
				var hideInput1 = row.insertCell();

				var gap = row.insertCell();

				var hideInput2 = row.insertCell();
				var funeralGoodsCell2 = row.insertCell();
				var goodsBeCost2 = row.insertCell();
				var goodsRealCost2 = row.insertCell();
				var balanceNumber2=row.insertCell();
				var addGoodsButton2 = row.insertCell();

				funeralGoodsCell1.innerText = json[i].goodsName;
				// goodsBeCost1.innerText=json[i].goodsBeCost;
				goodsBeCost1.innerHTML = "<input type='text' style='width: 80px;' onkeyup='changeGoodBeCost1(this)' value='"
						+ json[i].goodsBeCost + "' disabled='true'>";
				goodsRealCost1.innerHTML = "<input type='text' style='width: 80px;' value='0' onchange='changeGoodsCost1(this)' disabled='true'>";
				balanceNumber1.innerHTML="<input type='text' style='width: 80px;' value='"
					+ json[i].balanceNumber + "' disabled='true'>";
				addGoodsButton1.innerHTML = "<input align='center' type='checkbox' onchange='chooseFuneralGoods1(this)'>";
				hideInput1.innerHTML = "<input type='hidden' value='0'><input type='hidden' value='"
						+ json[i].goodsBeCost + "'>";
				gap.innerText = " ";
				i++;
				if (i < jsonLength) {
					funeralGoodsCell2.innerText = json[i].goodsName;
					// goodsBeCost2.innerText=json[i].goodsBeCost;
					goodsBeCost2.innerHTML = "<input type='text' style='width: 80px;'  onkeyup='changeGoodBeCost2(this)' value='"
							+ json[i].goodsBeCost + "' disabled='true'>";
					goodsRealCost2.innerHTML = "<input type='text' style='width: 80px;' value='0' onchange='changeGoodsCost2(this)' disabled='disabled'>";
					balanceNumber2.innerHTML="<input type='text' style='width: 80px;' value='"
						+ json[i].balanceNumber + "' disabled='true'>";
					addGoodsButton2.innerHTML = "<input align='center' type='checkbox' onchange='chooseFuneralGoods2(this)'>";
					hideInput2.innerHTML = "<input type='hidden' value='0'><input type='hidden' value='"
							+ json[i].goodsBeCost + "'>";
				}
			}
			var te = document.getElementById("all");
			for (var i = 0; i < jsonLength; i++) {
				var row = te.insertRow();
				var funeralGoodsCell1 = row.insertCell();
				var goodsGrade1 = row.insertCell();
				var goodsBeCost1 = row.insertCell();
				var goodsRealCost1 = row.insertCell();
				// var gap=row.insertCell();
				var funeralGoodsCell2 = row.insertCell();
				var goodsGrade2 = row.insertCell();
				var goodsBeCost2 = row.insertCell();
				var goodsRealCost2 = row.insertCell();
				// var gap1=row.insertCell();
				var funeralGoodsCell3 = row.insertCell();
				var goodsGrade3 = row.insertCell();
				var goodsBeCost3 = row.insertCell();
				var goodsRealCost3 = row.insertCell();

				funeralGoodsCell1.innerHTML = "<b>" + json[i].goodsName
						+ "</b>";
				// goodsBeCost1.innerText=json[i].goodsBeCost;
				// goodsRealCost1.innerText="<input type='text' style='width:
				// 80px;' value='"+json[i].goodsBeCost+"'
				// onchange='changeGoodsCost1(this)' disabled='true'>";

				gap.innerText = " ";
				i++;
				if (i < jsonLength) {
					funeralGoodsCell2.innerHTML = "<b>" + json[i].goodsName
							+ "</b>";
					// goodsBeCost2.innerText=json[i].goodsBeCost;
					// goodsRealCost2.innerHTML="<input type='text'
					// style='width: 80px;' value='"+json[i].goodsBeCost+"'
					// onchange='changeGoodsCost2(this)' disabled='disabled'>";

				}
				i++;
				if (i < jsonLength) {
					funeralGoodsCell3.innerHTML = "<b>" + json[i].goodsName
							+ "</b>";
					// goodsBeCost3.innerText=json[i].goodsBeCost;
					// goodsRealCost3.innerHTML="<input type='text'
					// style='width: 80px;' value='"+json[i].goodsBeCost+"'
					// onchange='changeGoodsCost2(this)' disabled='disabled'>";
				}
			}
		} else {
			alert("您请求的页面没有响应！");
		}
	}
}
function changeGoodBeCost1(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]

	var goodsBeCost = tr.cells[1];
	var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1 = goodsBeCostInput[0].value;
	if (checkNumber(goodsBeCost1)) {
		var goodsBeCostHidden = tr.cells[5];
		var goodsBeCostHiddenInput = goodsBeCostHidden
				.getElementsByTagName("input");
		var goodsBeCostHidden1 = goodsBeCostHiddenInput[1].value;

		var allServiceBeCost = document.form4.allBeCost;
		allServiceBeCost.value = parseInt(allServiceBeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		var theWholeCost = document.form4.theWholeCost;
		theWholeCost.value = parseInt(theWholeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		document.form4.lastWholeCost.value = theWholeCost.value;
		// alert(document.form3.lastWholeCost.value);
		goodsBeCostHiddenInput[1].value = goodsBeCost
				.getElementsByTagName("input")[0].value;
		// alert(goodsBeCostHiddenInput[1].value);
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsBeCostInput[0].focus();
	}
}
function chooseFuneralGoods1(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	// var goodsBeCost1=tr.cells[1].innerText;
	var goodsBeCost = tr.cells[1];
	var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1 = goodsBeCostInput[0].value;

	var goodsRealCost = tr.cells[2];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost1 = goodsRealCostInput[0].value;

	if (obj.checked == true) {
		goodsBeCostInput[0].disabled = "";
		goodsRealCostInput[0].disabled = "";
		var preAllBeCost = document.form4.allBeCost.value;
		var preAllRealCost = document.form4.allRealCost.value;
		var curAllBeCost = parseInt(goodsBeCost1) + parseInt(preAllBeCost);
		var curAllRealCost = parseInt(goodsRealCost1)
				+ parseInt(preAllRealCost);
		document.form4.allBeCost.value = curAllBeCost;
		document.form4.allRealCost.value = curAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsRealCost1);
	}
	if (obj.checked == false) {
		goodsBeCostInput[0].disabled = "true";
		goodsRealCostInput[0].disabled = "true";
		var preAllBeCost = document.form4.allBeCost.value;
		var preAllRealCost = document.form4.allRealCost.value;
		var curAllBeCost = preAllBeCost - goodsBeCost1;
		var curAllRealCost = preAllRealCost - goodsRealCost1;
		document.form4.allBeCost.value = curAllBeCost;
		document.form4.allRealCost.value = curAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(goodsBeCost1) + parseInt(goodsRealCost1);
	}
}
function changeGoodBeCost2(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]

	var goodsBeCost = tr.cells[9];
	var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1 = goodsBeCostInput[0].value;
	if (checkNumber(goodsBeCost1)) {
		var goodsBeCostHidden = tr.cells[7];
		var goodsBeCostHiddenInput = goodsBeCostHidden
				.getElementsByTagName("input");
		var goodsBeCostHidden1 = goodsBeCostHiddenInput[1].value;

		var allServiceBeCost = document.form4.allBeCost;
		allServiceBeCost.value = parseInt(allServiceBeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		var theWholeCost = document.form4.theWholeCost;
		theWholeCost.value = parseInt(theWholeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		// document.form3.lastWholeCost.value=theWholeCost.value;
		// //kahsdkahsdkuhaskdhaksjdkjasbdkjakdj
		// alert(document.form3.lastWholeCost.value);
		goodsBeCostHiddenInput[1].value = goodsBeCost
				.getElementsByTagName("input")[0].value;
		// alert(goodsBeCostHiddenInput[1].value);
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsBeCostInput[0].focus();
	}
}


	function chooseFuneralGoods2(obj) {
		var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
		// var goodsBeCost2=tr.cells[8].innerText;
		var goodsBeCost = tr.cells[9];
		var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
		var goodsBeCost2 = goodsBeCostInput[0].value;

		var goodsRealCost = tr.cells[10];
		var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
		var goodsRealCost2 = goodsRealCostInput[0].value;
		if (obj.checked == true) {
			goodsBeCostInput[0].disabled = "";
			goodsRealCostInput[0].disabled = "";
			var preAllBeCost = document.form4.allBeCost.value;
			var preAllRealCost = document.form4.allRealCost.value;
			var curAllBeCost = parseInt(goodsBeCost2) + parseInt(preAllBeCost);
			var curAllRealCost = parseInt(goodsRealCost2)
					+ parseInt(preAllRealCost);
			document.form4.allBeCost.value = curAllBeCost;
			document.form4.allRealCost.value = curAllRealCost;
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					+ parseInt(goodsBeCost2) - parseInt(goodsRealCost2);
		}
		if (obj.checked == false) {
			goodsBeCostInput[0].disabled = "true";
			goodsRealCostInput[0].disabled = "true";
			var preAllBeCost = document.form4.allBeCost.value;
			var preAllRealCost = document.form4.allRealCost.value;
			var curAllBeCost = preAllBeCost - goodsBeCost2;
			var curAllRealCost = preAllRealCost - goodsRealCost2;
			document.form4.allBeCost.value = curAllBeCost;
			document.form4.allRealCost.value = curAllRealCost;
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					- parseInt(goodsBeCost2) + parseInt(goodsRealCost2);
		}
	}
function showData(){
	url="deadId="+form4.deadId.value;
	http_request=createHttpRequest();
	http_request.onreadystatechange=showDataCallback;
	http_request.open('POST',"RegisterServiceAction!showOrderDeadInfo",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function showDataCallback(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			if(json.flag=="1"){
				form4.memberMobile.value=json.memberMobile;
				form4.deadName.value=json.deadName;
				form4.deadSex.value=json.deadSex;
				form4.deadAge.value=json.deadAge;
				form4.deadNumber.value=json.deadNumber;
				
				form4.inTime.value=json.inTime;
				form4.dealerId.value=json.dealerId;
				form4.dealerName.value=json.dealerName;
				form4.directorName.value=json.directorName;
				form4.dealerAddress.value=json.dealerAddress;
				form4.deadExtraInfo.value = json.memo;
				

				
				if(json.deadTime=="0001-01-01 00:00:00"){
					form4.deadTime.value="";
				}
				else{
					
					form4.deadTime.value = json.deadTime.substring(0,11);
				}
				
				form4.deadAddress.value=json.address;
				form4.deadProveUnit.value = json.proofUnit;
				
				form4.invoiceNo.value = json.invoiceNo;
				form4.subsidyNo.value = json.subsidyNo;
				form4.subsidyMoney.value = json.subsidyMoney;
				if(json.deadResidence!=""){
					for(var i=0;i<document.getElementById("deadResidence").options.length;i++)
				    {
				        if(document.getElementById("deadResidence").options[i].value == json.deadResidence)
				        {
				            document.getElementById("deadResidence").options[i].selected=true;
				            break;
				        }
				    }
					
					
				}
				if(json.operatorRelation!=""){
					for(var i=0;i<document.getElementById("operatorRelation").options.length;i++)
				    {
				        if(document.getElementById("operatorRelation").options[i].value == json.operatorRelation)
				        {
				            document.getElementById("operatorRelation").options[i].selected=true;
				            break;
				        }
				    }
					
					
				}
				
//				var deadResidenceSelect = document.form4.deadResidence;
//				var deadResidenceIndex = deadResidenceSelect.selectedIndex;				
//				deadResidenceSelect.options[deadResidenceIndex].text=json.deadResidence;
//				
//				var operatorRelationSelect = document.form4.operatorRelation;
//				var operatorRelationIndex = operatorRelationSelect.selectedIndex;				
//				operatorRelationSelect.options[operatorRelationIndex].text=json.operatorRelation;
				
				if(json.pathogeny!=""){
					for(var i=0;i<document.getElementById("pathogeny").options.length;i++)
				    {
				        if(document.getElementById("pathogeny").options[i].value == json.pathogeny)
				        {
				            document.getElementById("pathogeny").options[i].selected=true;
				            break;
				        }
				    }
				}else{
					document.form4.pathogeny.style.display="none";

				}
				
				if(json.deadReason!=""){
					for(var i=0;i<document.getElementById("deadReason").options.length;i++)
				    {
				        if(document.getElementById("deadReason").options[i].value == json.deadReason)
				        {
				            document.getElementById("deadReason").options[i].selected=true;
				            break;
				        }
				    }
					
					form4.deadReason.onchange();
				}
				if(json.area!=""){
					for(var i=0;i<document.getElementById("area").options.length;i++)
				    {
				        if(document.getElementById("area").options[i].value == json.area)
				        {
				            document.getElementById("area").options[i].selected=true;
				            break;
				        }
				    }
					
				}
				
				if(json.ashesDisposition!=""){
					for(var i=0;i<document.getElementById("ashesDisposition").options.length;i++)
				    {
				        if(document.getElementById("ashesDisposition").options[i].value == json.ashesDisposition)
				        {
				            document.getElementById("ashesDisposition").options[i].selected=true;
				            break;
				        }
				    }
					
				}
				
				if(json.benefitTime=="0001-01-01 00:00:00"){
					form4.benefitTime.value="";
				}
				else{
					
					form4.benefitTime.value = json.benefitTime.substring(0,11);
				}
				if(json.deadReason!=""){
					for(var i=0;i<document.getElementById("deadReason").options.length;i++)
				    {
				        if(document.getElementById("deadReason").options[i].value == json.deadReason)
				        {
				            document.getElementById("deadReason").options[i].selected=true;
				            break;
				        }
				    }
					
					form4.deadReason.onchange();
				}
				
				form4.remainsCarryBeCost.value=json.remainsCarryBeCost;
				form4.remainsCarryRealCost.value=json.remainsCarryRealCost;
				form4.lastRemainsCarryRealCost.value=json.remainsCarryRealCost;
				
				form4.rentCrystalBeCost.value=json.rentCrystalBeCost;
				form4.rentCrystalRealCost.value=json.rentCrystalRealCost;
				form4.lastRentCrystalRealCost.value=json.rentCrystalRealCost;
				
				form4.rentCrystalCarBeCost.value=json.rentCrystalCarBeCost;
				form4.rentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
				form4.lastRentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
				
				form4.watchSpiritVillaBeCost.value=json.watchSpiritVillaBeCost;
				form4.watchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;
				form4.lastWatchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;
				
				form4.watchSpiritCoffinBeCost.value=json.watchSpiritCoffinBeCost;
				form4.watchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
				form4.lastWatchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
				//lastWholeCost存放的是除了火化之外的费用总和
				//theWholeCost存放的是所有实际收费的费用
				form4.theWholeCost.value=parseInt(form4.theWholeCost.value)-parseInt(form4.lastWholeCost.value)+parseInt(json.remainsCarryRealCost)+parseInt(json.rentCrystalRealCost)+parseInt(json.rentCrystalCarRealCost)+parseInt(json.watchSpiritVillaRealCost)+parseInt(json.watchSpiritCoffinRealCost);
				form4.lastWholeCost.value=parseInt(form4.remainsCarryRealCost.value)+parseInt(form4.rentCrystalRealCost.value)+parseInt(form4.rentCrystalCarRealCost.value)+parseInt(form4.watchSpiritVillaRealCost.value)+parseInt(form4.watchSpiritCoffinRealCost.value)+"";
				
				form4.allBeCost.value=parseInt(form4.theWholeCost.value)+parseInt(form4.remainsCarryBeCost.value)+parseInt(form4.rentCrystalBeCost.value)+parseInt(form4.rentCrystalCarBeCost.value)+parseInt(form4.watchSpiritVillaBeCost.value)+parseInt(form4.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
				form4.allRealCost.value=parseInt(form4.allRealCost.value)+parseInt(form4.remainsCarryBeCost.value)+parseInt(form4.rentCrystalBeCost.value)+parseInt(form4.rentCrystalCarBeCost.value)+parseInt(form4.watchSpiritVillaBeCost.value)+parseInt(form4.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
				
				var cg=document.getElementById("cremationGrade");//火化
				for(i=0; i<cg.options.length; i++)
				{
					if (cg.options[i].value ==json.A03) // 动态选择selected默认值
					{
						
						cg.options[i].selected = true;
					}
				}
				getCremationBeCost();
				var mg=document.getElementById("makeBeautyGrade");//美容
				for(i=0; i<mg.options.length; i++)
				{
					if (mg.options[i].value ==json.A01) // 动态选择selected默认值
					{
						mg.options[i].selected = true;
					}
				}
				getMakeBeautyBeCost();
				var lg=document.getElementById("leaveRoomGrade");//告别
				for(i=0; i<lg.options.length; i++)
				{
					if (lg.options[i].value ==json.A02) // 动态选择selected默认值
					{
						lg.options[i].selected = true;
					}
				}
				getLeaveRoomBeCost();
			}
			else
	 		{
				form4.memberMobile.value="";
	 			form4.deadName.value="";
	 			form4.deadNumber.value="";
	 			form4.inTime.value="";
	 			form4.remainsCarryRealCost.value="";
				form4.rentCrystalRealCost.value="";
	 		}
		}
		else{
			alert("显示遗体信息失败！");
		}
	}
}
function changeGoodsCost1(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsBeCost1 = tr.cells[1].innerText;
	var goodsRealCost = tr.cells[2];
	var hideTd1 = tr.cells[5];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost1 = goodsRealCostInput[0].value;
	if (checkNumber(goodsRealCost1)) {
		var hideInput1 = hideTd1.getElementsByTagName("input");
		var hideValue1 = hideInput1[0].value;
		var changeGoodsRealCost1 = goodsRealCost1 - hideValue1;
		var allRealCost = document.form4.allRealCost.value;
		document.form4.allRealCost.value = parseInt(allRealCost)
				- parseInt(hideValue1) + parseInt(goodsRealCost1);
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(hideValue1) - parseInt(goodsRealCost1);
		hideInput1[0].value = goodsRealCost1;
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}

function changeGoodsCost2(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsRealCost = tr.cells[10];
	var hideTd2 = tr.cells[7];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost2 = goodsRealCostInput[0].value;
	if (checkNumber(goodsRealCost2)) {
		var hideInput2 = hideTd2.getElementsByTagName("input");
		var hideValue2 = hideInput2[0].value;
		var changeGoodsRealCost2 = goodsRealCost2 - hideValue2;
		var allRealCost = document.form4.allRealCost.value;
		document.form4.allRealCost.value = parseInt(allRealCost)
				- parseInt(hideValue2) + parseInt(goodsRealCost2);
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(hideValue2) - parseInt(goodsRealCost2);
		hideInput2[0].value = goodsRealCost2;
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}
function getLeaveRoomCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			var jsonLength=json.length;
			var urnSelect1=document.getElementById("farewareHallType");
			for(var i=0;i<jsonLength;i++){
				var new_opt = new Option(json[i].itemName,json[i].itemNo); 
				urnSelect1.options.add(new_opt);
			}
//			var jsonLength=json.length;
			var urnSelect=document.form4.leaveRoomGrade;
			for(var i=0;i<jsonLength;i++){
				var new_opt = new Option(json[i].itemName,json[i].itemNo); 
				urnSelect.options.add(new_opt);
			}
		}
	}
}

/*—————————————————————————————————登记预约信息————————————————————————————————*/
//验证表格一的输入内容，并传输给后台
function validateFormyuhuo()
{
	if(form1.deadId.value=="")
	{
		alert("逝者身份证号码不能为空！");
		form1.deadId.focus();
		return false;
	}
	
	if(form1.deadName.value=="")
	{
		alert("逝者姓名不能为为空！");
		form1.deadName.focus();
		return false;
	}
	if(form1.contactMobile.value=="")
	{
		alert("联系人手机号不能为空！");
		form1.contactMobile.focus();
		return false;
	}
	if(form1.contactName.value=="")
	{
		alert("联系人姓名不能为空！");
		form1.contactName.focus();
		return false;
	}
	if(form1.estimatedTime.value=="")
	{
		alert("预计进馆时间不能为空！");
		form1.estimatedTime.focus();
		return false;
	}
	if(form1.orderTime.value=="")
	{
		alert("预约时间不能为空！");
		form1.orderTime.focus();
		return false;
	}
	var idCard=form1.deadId.value;
	
	if(!(validateIdCard(idCard))){
		alert("身份证不合法！");
		form1.deadId.focus();
		return false;
	}
	
	var phoneNumber=form1.contactMobile.value;
	if(!(validatePhoneNumber(phoneNumber))){
		alert("手机号码错误！");
		form1.contactMobile.focus();
		return false;
	}
	else{
		
		var url="orderInfo.deadId="+form1.deadId.value+"&";
		url=url+"orderInfo.deadName="+form1.deadName.value+"&";
		url=url+"orderInfo.contactMobile="+form1.contactMobile.value+"&";
		url=url+"orderInfo.contactName="+form1.contactName.value+"&";
		url=url+"orderInfo.estimatedDate="+form1.estimatedTime.value+"&";
		url=url+"orderInfo.orderTime="+form1.orderTime.value;
		//取下拉列表的值
		var cType=document.getElementById("CremationType");
		var cTypeIndex=cType.selectedIndex;
		var cremationType=cType.options[cTypeIndex].text;
		
		var bType=document.getElementById("bodyBeautyType");
		var bTypeIndex=bType.selectedIndex;
		var bodyBeautyType=bType.options[bTypeIndex].text;
		
		var fType=document.getElementById("farewareHallType");
		var fTypeIndex=fType.selectedIndex;
		var farewareHallType=fType.options[fTypeIndex].text;
		if(document.form1.cremation01.checked==true)//火化
			{
				url=url+"&"+"orderInfo.chooseCremationType="+cremationType;
			}
		else
		    {
				url=url+"&"+"orderInfo.chooseCremationType=0";
		    }
			
		if(document.form1.bodyBeauty01.checked==true)//美容
			{
			    url=url+"&"+"orderInfo.bodyBeauty="+bodyBeautyType;
			}
		else
			{
				url=url+"&"+"orderInfo.bodyBeauty=0";
			}
		if(document.form1.farewareHall01.checked==true)//告别厅
			{
				url=url+"&"+"orderInfo.farewareHall="+farewareHallType;
			}
		else
			{
				url=url+"&"+"orderInfo.farewareHall=0";
			}
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateFormyuhuoCallback;
		http_request.open('POST',"AddOrderAction!registOrderInfo",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
	}
	
}
//回调函数----Form1
function validateFormyuhuoCallback()
{
	if(http_request.readyState==4)
	{
		if(http_request.status==200)
		{
			alert(http_request.responseText);
//			var json=eval("("+http_request.responseText+")");
//			json=eval("("+json+")");
//			alert(json.returnString);
	    }
		else{
			alert("您所请求的页面没有响应！");
		}
	}
} 

function addCremation(form1) //火化炉checkbox默认未选中，点击checkbox后，使下拉列表disabled转为false，选择业务
{
	if(form1.cremation01.checked)
	{
    	form1.CremationType.disabled=false;
	}
	else
	{
		form1.CremationType.disabled=true;
	}
}
function addBodyBeauty(form1) 
{
	if(form1.bodyBeauty01.checked)
	{
		form1.bodyBeautyType.disabled=false;
	}
	else
	{
		form1.bodyBeautyType.disabled=true;
	}
}
function addFarewareHall(form1)
{
	if( form1.farewareHall01.checked)
	{
		form1.farewareHallType.disabled=false;
	}
	else
	{
		form1.farewareHallType.disabled=true;
	}
}
//关联告别厅应收价格
function getLeaveRoomBeCost() {
	var leaveRoom = document.getElementById("leaveRoomGrade");
	var leaveRoomIndex = leaveRoom.selectedIndex;
	var leaveRoomName = leaveRoom.options[leaveRoomIndex].text;
	if(leaveRoomName!="-请选择-"){
		url = "itemName=" + leaveRoomName;
		http_request = createHttpRequest();
		http_request.onreadystatechange = getLeaveRoomBeCostCallBack;
		http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		http_request.send(url);
	}

	return false;// 阻止页面刷新
}

function getLeaveRoomBeCostCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var leaveRoomBeCost = document.getElementById("leaveRoomBeCost");
			leaveRoomBeCost.value = json.price;
			var leaveRoomRealCost = document
					.getElementById("leaveRoomRealCost");

			var curLeaveRoomBeCost = leaveRoomBeCost.value;
			var curLeaveRoomRealCost = leaveRoomRealCost.value;
			var preLeaveRoomBeCost = document
					.getElementById("preLeaveRoomBeCost").value;
			var preLeaveRoomRealCost = document
					.getElementById("preLeaveRoomRealCost").value;
			var changeLeaveRoomBeCost = curLeaveRoomBeCost - preLeaveRoomBeCost;
			var changeLeaveRoomRealCost = curLeaveRoomRealCost
					- preLeaveRoomRealCost;
			var preAllBeCost = document.getElementById("allBeCost").value;
			var preAllRealCost = document.getElementById("allRealCost").value;
			var allBeCost = parseInt(preAllBeCost)
					+ parseInt(changeLeaveRoomBeCost);
			var allRealCost = parseInt(preAllRealCost)
					+ parseInt(changeLeaveRoomRealCost);
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					+ parseInt(changeLeaveRoomRealCost)
					+ parseInt(changeLeaveRoomBeCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preLeaveRoomBeCost").value = curLeaveRoomBeCost;
			document.getElementById("preLeaveRoomRealCost").value = curLeaveRoomRealCost;
		}
	}
}



function getMakeBeautyBeCost() {
	var makeBeauty = document.getElementById("makeBeautyGrade");
	var makeBeautyIndex = makeBeauty.selectedIndex;
	var makeBeautyName = makeBeauty.options[makeBeautyIndex].text;
	if(makeBeautyName!="-请选择-"){
		url = "itemName=" + makeBeautyName;
		http_request = createHttpRequest();
		http_request.onreadystatechange = getMakeBeautyBeCostCallBack;
		http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		http_request.send(url);
	}
	
	return false;// 阻止页面刷新
}

function getMakeBeautyBeCostCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var makeBeautyBeCost = document.getElementById("makeBeautyBeCost");
			makeBeautyBeCost.value = json.price;
			var makeBeautyRealCost = document
					.getElementById("makeBeautyRealCost");

			var curBeautyBeCost = makeBeautyBeCost.value;
			var curBeautyRealCost = makeBeautyRealCost.value;
			var preMakeBeautyBeCost = document
					.getElementById("preMakeBeautyBeCost").value;
			var preMakeBeautyRealCost = document
					.getElementById("preMakeBeautyRealCost").value;
			var changeBeautyBeCost = curBeautyBeCost - preMakeBeautyBeCost;
			var changeBeautyRealCost = curBeautyRealCost
					- preMakeBeautyRealCost;
			var preAllBeCost = document.getElementById("allBeCost").value;
			var preAllRealCost = document.getElementById("allRealCost").value;
			var allBeCost = parseInt(preAllBeCost)
					+ parseInt(changeBeautyBeCost);
			var allRealCost = parseInt(preAllRealCost)
					+ parseInt(changeBeautyRealCost);
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					+ parseInt(changeBeautyBeCost)
					+ parseInt(changeBeautyRealCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preMakeBeautyBeCost").value = curBeautyBeCost;
			document.getElementById("preMakeBeautyRealCost").value = curBeautyRealCost;
		}
	}
}

/*————————————————————————————————————预约遗体挂号————————————————————————————————*/
//访问服务器generateRemainNumber,生成遗体编号
function validatePhoneNumber(phoneNumber){
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
    if(isMob.test(phoneNumber)||isPhone.test(phoneNumber)){
        return true;
    }
    else{
        return false;
    }
}
function validateIdCard(idCard) {
	// 15位和18位身份证号码的正则表达式
	var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	var regIdCardOld = /^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$/;
	// 如果通过该验证，说明身份证格式正确，但准确性还需计算
	if (regIdCard.test(idCard)) {
		if (idCard.length == 18) {
			var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2); // 将前17位加权因子保存在数组里
			var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); // 这是除以11后，可能产生的11位余数、验证码，也保存成数组
			var idCardWiSum = 0; // 用来保存前17位各自乖以加权因子后的总和
			for (var i = 0; i < 17; i++) {
				idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
			}
			var idCardMod = idCardWiSum % 11;// 计算出校验码所在数组的位置
			var idCardLast = idCard.substring(17);// 得到最后一位身份证号码
			// 如果等于2，则说明校验码是10，身份证号码最后一位应该是X
			if (idCardMod == 2) {
				if (idCardLast == "X" || idCardLast == "x") {
					return true;
				} else {
					return false;
				}
			} else {
				// 用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
				if (idCardLast == idCardY[idCardMod]) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
	if (regIdCardOld.test(idCard)) {
		return true;
	} else {
		return false;
	}
}
function getRemainNumber(form2)
{
	http_request=createHttpRequest();
	http_request.onreadystatechange=getRemainNoCallback;
	http_request.open('GET',"GenerateRemainNumberAction!makeRemainNumber",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(null);
	return false;
}
function getRemainNoCallback()
{
	if(http_request.readyState==4)
	{
		if(http_request.status==200)
		{
//			alert(http_request.responseText);
			var json=eval("("+http_request.responseText+")");
			//json=eval("("+json+")");
			//alert(json.returnString);
			form2.deadNumber.value=json;
			//form2.deadName.ReadOnly=true;
	    }
		else{
			alert("您所请求的页面没有响应！");
		}
	}
}
//预约挂号界面读取身份证后，访问后台，关联预约信息
function form2Data(form2)
{
	var url="deadId="+form2.deadId.value;
	http_request=createHttpRequest();
	http_request.onreadystatechange=form2DataCallback;
	http_request.open('POST',"AssociateOrderAction!searchOrderInfo",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;
}


//回调函数----Form2--deadId
function form2DataCallback()
{
	if(http_request.readyState==4)
	{
		if(http_request.status==200)
		{
//			alert(http_request.responseText);
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			form2.deadName.value=json[0].deadName;
			form2.orderTime.value=json[0].orderTime;
			form2.orderNumber.value=json[0].orderNumber;
			form2.contactMobile.value=json[0].contactMobile;
			form2.contactName.value=json[0].contactName;
		}
		else
 		{
 					form2.deadName.value="";
 					form2.contactMobile.value="";
 					form2.contactName.value="";
 					form2.estimatedTime.value="";
 					form2.orderTime.value="";
 					alert("您所请求的页面没有响应！");	
	     }
	 }
}
//form2：提交预约挂号信息，复用正常挂号信息Action
function validateForm2(form)

{
	
	
	if(form.inTime.value=="")
	{
		alert("遗体进馆时间不能为空！");
		form.inTime.focus();
		return false;
	}
//	if(form.deadTime.value==""){
//		alert("逝者死亡时间不能为空");
//		form.deadTime.focus();
//		return false;
//	}
	
	/*if(form.deadSex.value=="")
	{
		alert("逝者性别不能为空！");
		form.deadSex.focus();
		return false;
	}*/
	if(form.deadAge.value=="")
	{
		alert("逝者年龄不能为空！");
		form.deadAge.focus();
		return false;
	}
	if(form.deadAddress.value=="")
	{
		alert("家庭住址不能为空！");
		form.deadAddress.focus();
		return false;
	}	
	/*if(form.deadType.value=="")
	{
		alert("死亡类型不能为空！");
		form.deadType.focus();
		return false;
	}*/
	
//	if(form.deadReason.value=="")
//	{
//		alert("死亡原因不能为空！");
//		form.deadReason.focus();
//		return false;
//	}
//	if(form.deadProveUnit.value=="")
//	{
//		alert("死亡证明出具单位不能为空！");
//		form.deadProveUnit.focus();
//		return false;
//	}
//	if(form.memberMobile.value=="")
//	{
//		alert("丧属手机号码不能为空！");
//		form.memberMobile.focus();
//		return false;
//	}
//	var phoneNumber=form.memberMobile.value;
//	if(!(validatePhoneNumber(phoneNumber))){
//		alert("电话号码格式错误，请检查后重新输入！");
//		form.memberMobile.focus();
//		return false;
//	}
//	else{
		//获取性别下拉列表中的选中项
		var sexSelect=document.getElementById("deadSex2");
		var sexIndex=sexSelect.selectedIndex ;
		var deadSex=sexSelect.options[sexIndex].text;
		
		url="deadInfo.deadId="+form.deadId.value+"&";
		url=url+"deadInfo.deadName="+form.deadName.value+"&";
		url=url+"deadInfo.deadSex="+deadSex+"&";
//		url=url+"deadInfo.memberMobile="+form.memberMobile.value+"&";
		//url=url+"deadInfo.deadNumber="+form.deadNumber.value+"&";
		if(form.deadAge.value!="")
		{
			url=url+"deadInfo.deadAge="+form.deadAge.value+"&";
		}
		url=url+"deadInfo.inTime="+form.inTime.value+"&";
		
//		url=url+"deadInfo.deadTime="+form.deadTime.value+"&";
		url=url+"deadInfo.deadAddress="+form.deadAddress.value+"&";
//		url=url+"deadInfo.deadProveUnit="+form.deadProveUnit.value+"&";
		//获取死亡类型下拉列表中的选中项
//		var kindSelect=form.deadKind;
//		var kindIndex=kindSelect.selectedIndex;
//		var deadKind=kindSelect.options[kindIndex].text;
//		
//		var deadReasonSelect=form.deadReason;
//		var deadReasonIndex=deadReasonSelect.selectedIndex;
//		var deadReason=deadReasonSelect.options[deadReasonIndex].text;
//		url=url+"deadInfo.deadKind="+deadKind+"&";
//		url=url+"deadInfo.deadReason="+deadReason;
		//url=url+"deadInfo.deadNumber="+form.deadNumber.value;
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateForm2Callback;
		http_request.open('POST',"DeadInfoRegisterAction!registOrderDeadInfo",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		
		return false;
//	}
}
function validateForm2Callback()
{
	if(http_request.readyState==4){
		if(http_request.status==200){
			alert(http_request.responseText);
			
		}
		else{
			alert("您所请求的页面没有响应！");
		}
	}
}
function updateData()
{
	var deadId=document.form3.deadId.value;
	url="deadId="+deadId;
	http_request=createHttpRequest();
	http_request.onreadystatechange=updateDataCallback;
	http_request.open('POST',"PrintQRCodeAction!getQRCodeOrderInfo",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function updateDataCallback() // 改变二维码下面用于打印的table中遗体信息显示的大小
{
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			if (json.flag == "1") {
//				form3.memberMobile.value = json.memberMobile;
				form3.deadNumber.value = json.deadNumber;
				form3.inTime.value = json.inTime;
				form3.deadName.value = json.deadName;
				// <font style='font-size:20px;'>"+deadName+"</font>
				document.getElementById("deadInfoPrint").style.display = "";
				var deadNumber = json.deadNumber;
				// "<font style='font-size:15px;'>"+invoiceNo+"</font>"
				if (deadNumber.length == 1) {
					var deadNumberString = "遗体号：Vip0" + deadNumber;
					document.getElementById("deadNumberShow").innerHTML = "<font style='font-size:15px;'>"
							+ deadNumberString + "</font>";
				} else {
					document.getElementById("deadNumberShow").innerHTML = "<font style='font-size:15px;'>"
							+ "编号：" + deadNumber + "</font>";
				}
				document.getElementById("deadNamePrint").innerHTML = "<font style='font-size:15px;'>"
						+ "姓名：" + json.deadName + "</font>";
				document.getElementById("deadSexPrint").innerHTML = "<font style='font-size:15px;'>"
						+ "性别：" + json.deadSex + "</font>";
				document.getElementById("deadAgePrint").innerHTML = "<font style='font-size:15px;'>"
						+ "年龄：" + json.deadAge + "</font>";
				document.getElementById("deadAddressPrint").innerHTML = "<font style='font-size:15px;'>"
						+ "住址：" + json.address + "</font>";
			} else {
				form3.memberMobile.innerText = "";
				form3.deadName.innerText = "";
				form3.deadNumber.innerText = "";
				form3.inTime.innerText = "";
				form3.deadNumber.innerText = "";

				document.getElementById("deadNumberShow").innerText = "";
				document.getElementById("deadNamePrint").innerText = "";
				document.getElementById("deadSexPrint").innerText = "";
				document.getElementById("deadAgePrint").innerText = "";
				document.getElementById("deadAddressPrint").innerText = "";
//				form2.deadNamePrint.innerText = "";
//				form2.deadSexPrint.innerText = "";
//				form2.deadAgePrint.innerText = "";
//				form2.deadAddressPrint.innerText = "";
			}
		} else {
			alert("您所请求的页面没有响应");
		}
	}
}

function produceCode(){			//生成二维码
	url="deadId="+form3.deadId.value+"&";
	url=url+"deadNumber="+form3.deadNumber.value;
	http_request=createHttpRequest();
	http_request.onreadystatechange=produceCodeCallback;
	http_request.open('POST',"PrintQRCodeAction2",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}


function produceCodeCallback(){
	if(http_request.readyState==4){
		if(http_request.status==200){
//			alert(http_request.responseText);
			alert("生成二维码成功");
//			document.getElementById("QRCode").src="Images/bbb.png";
			var deadIdNumber=form3.deadId.value;
			if(deadIdNumber.length==1){
				deadIdNumber=""+"0"+deadIdNumber;
			}
			var remainsNumber=form3.deadNumber.value;
			document.getElementById("QRCode").src="PrintQRCodeAction2?deadId="+deadIdNumber+"&deadNumber="+remainsNumber+"";
			return false;
		}
	}
}



function printCode(){	//打印二维码
	produceCodeCallback();
	var deadId = document.form3.deadId.value;
//	var deadNamePrint=document.form3.deadNamePrint;
//	deadNamePrint.value=document.form3.deadName.value;
//	document.getElementById("deadNamePrint").style.display ="block";
//	document.getElementById("deadNamePrint").value="遗体二维码："+document.form3.deadName.value;
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    top.right.focus();
    top.right.print();
    window.document.body.innerHTML=bdhtml;
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"); // 重新绑定选项卡标签的各个触发事件
	document.getElementById("printDeadInfoPage").click();
	document.form3.deadId.value = deadId;
	document.form3.deadId.onchange();
}

function createFamilyCode(){
	var deadNamePrint=document.form3.deadNamePrint;
	deadNamePrint.value=document.form3.deadName.value;
	deadNamePrint.style.display ="none";
	var familyInfo=document.form3.deadId.value;
	var familyInfoStr=familyInfo.substring(0,familyInfo.length-1);
	var familyInfoString="J"+familyInfoStr;
	url="deadId="+familyInfoString+"&";
	url=url+"deadNumber="+form3.deadNumber.value;
	http_request=createHttpRequest();
	http_request.onreadystatechange=produceFamilyCodeCallback;
	http_request.open('POST',"PrintQRCodeAction2",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}

function produceFamilyCodeCallback(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			alert("生成二维码成功");
			var familyInfo=document.form3.deadId.value;
			var familyInfoStr=familyInfo.substring(0,familyInfo.length-1);
			var familyInfoString="J"+familyInfoStr;
			var remainsNumber=form3.deadNumber.value;
			if(remainsNumber.length==1){
				remainsNumber="0"+remainsNumber;
			}
			document.getElementById("QRCode").src="PrintQRCodeAction2?deadId="+familyInfoString+"&deadNumber="+remainsNumber+"";
			return false;
		}
	}
}

function familyOutPut(){	//打印家属二维码
	var deadId = document.form3.deadId.value;
	produceFamilyCodeCallback();
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    top.right.focus();
    top.right.print();
    window.document.body.innerHTML=bdhtml;
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"); // 重新绑定选项卡标签的各个触发事件
	document.getElementById("printDeadInfoPage").click();
	document.form3.deadId.value = deadId;
	document.form3.deadId.onchange();
}




function getUrnBeCost() {

	var urnChooseBox = document.getElementById("urnChooseBox");
	if (urnChooseBox.checked == true) {
		var urnChoose = document.getElementById("urnChoose");
		var urnChooseIndex = urnChoose.selectedIndex;
		var urnName = urnChoose.options[urnChooseIndex].text;
		url = "urnName=" + urnName;
		http_request = createHttpRequest();
		http_request.onreadystatechange = getUrnBeCostCallBack;
		http_request.open('POST', "GetUrnBeCostAction!getUrnBeCost", false);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		http_request.send(url);
		return false;// 阻止页面刷新
	}
}

function getUrnBeCostCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			// var urnBeCost=document.getElementById("urnBeCost");
			var urnBeCost = document.form4.urnBeCost;
			urnBeCost.value = json.urnBeCost;
			var urnRealCost = document.getElementById("urnRealCost");

			var curUrnBeCost = urnBeCost.value;
			var curUrnRealCost = urnRealCost.value;
			var preUrnBeCost = document.getElementById("preUrnBeCost").value;
			var preUrnRealCost = document.getElementById("preUrnRealCost").value;
			var changeUrnBeCost = curUrnBeCost - preUrnBeCost;
			var changeUrnRealCost = curUrnRealCost - preUrnRealCost;
			var preAllBeCost = document.getElementById("allBeCost").value;
			var preAllRealCost = document.getElementById("allRealCost").value;
			var allBeCost = parseInt(preAllBeCost) + parseInt(changeUrnBeCost);
			var allRealCost = parseInt(preAllRealCost)
					+ parseInt(changeUrnRealCost);
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					+ parseInt(changeUrnBeCost) + parseInt(changeUrnRealCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preUrnBeCost").value = curUrnBeCost;
			document.getElementById("preUrnRealCost").value = curUrnRealCost;
		}
	}
}



function getCremationBeCost() {
	var cremation = document.getElementById("cremationGrade");
	var cremationIndex = cremation.selectedIndex;
	var cremationName = cremation.options[cremationIndex].text;
	url = "itemName=" + cremationName;
	if(cremationName!="-请选择-"){
		
	
	
	http_request = createHttpRequest();
	http_request.onreadystatechange = getCremationBeCostCallBack;
	http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	}
	return false;// 阻止页面刷新
}

function getCremationBeCostCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var cremationBeCost = document.getElementById("cremationBeCost");
			cremationBeCost.value = json.price;
			var cremationRealCost = document
					.getElementById("cremationRealCost");

			var curCremationBeCost = cremationBeCost.value;
			var curCremationRealCost = cremationRealCost.value;
			var preCremationBeCost = document
					.getElementById("preCremationBeCost").value;
			var preCremationRealCost = document
					.getElementById("preCremationRealCost").value;
			var changeCremationBeCost = curCremationBeCost - preCremationBeCost;
			var changeCremationRealCost = curCremationRealCost
					- preCremationRealCost;
			var preAllBeCost = document.getElementById("allBeCost").value;
			var preAllRealCost = document.getElementById("allRealCost").value;
			var allBeCost = parseInt(preAllBeCost)
					+ parseInt(changeCremationBeCost);
			var allRealCost = parseInt(preAllRealCost)
					+ parseInt(changeCremationRealCost);
			document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
					+ parseInt(changeCremationRealCost)
					+ parseInt(changeCremationBeCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preCremationBeCost").value = curCremationBeCost;
			document.getElementById("preCremationRealCost").value = curCremationRealCost;
		}
	}
}

function cancelUrnCost() {
	if (document.getElementById("urnChooseBox").checked == false) {
		document.getElementById("urnChoose").disabled = "true";
		document.getElementById("urnBeCost").disabled = "true";
		document.getElementById("urnRealCost").disabled = "true";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curUrnBeCost = document.getElementById("urnBeCost").value;
		var curUrnRealCost = document.getElementById("urnRealCost").value;
		var lastAllBeCost = curAllBeCost - curUrnBeCost;
		var lastAllRealCost = curAllRealCost - curUrnRealCost;
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = document.form4.theWholeCost.value
				- parseInt(curUrnBeCost) + parseInt(curUrnRealCost);
	}
	if (document.getElementById("urnChooseBox").checked == true) {
		document.getElementById("urnChoose").disabled = "";
		document.getElementById("urnBeCost").disabled = "";
		document.getElementById("urnRealCost").disabled = "";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curUrnBeCost = document.getElementById("urnBeCost").value;
		var curUrnRealCost = document.getElementById("urnRealCost").value;
		var lastAllBeCost = parseInt(curAllBeCost) + parseInt(curUrnBeCost);
		var lastAllRealCost = parseInt(curAllRealCost)
				+ parseInt(curUrnRealCost);
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(curUrnBeCost) - parseInt(curUrnRealCost);
	}
}

function cancelBeautyCost() {
	if (document.getElementById("makeBeautyBox").checked == false) {
		document.getElementById("makeBeautyGrade").disabled = "true";
		document.getElementById("makeBeautyBeCost").disabled = "true";
		document.getElementById("makeBeautyRealCost").disabled = "true";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curBeautyBeCost = document.getElementById("makeBeautyBeCost").value;
		var curBeautyRealCost = document.getElementById("makeBeautyRealCost").value;
		var lastAllBeCost = curAllBeCost - curBeautyBeCost;
		var lastAllRealCost = curAllRealCost - curBeautyRealCost;
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(curBeautyBeCost) + parseInt(curBeautyRealCost);
	}
	if (document.getElementById("makeBeautyBox").checked == true) {
		document.getElementById("makeBeautyGrade").disabled = "";
		document.getElementById("makeBeautyBeCost").disabled = "";
		document.getElementById("makeBeautyRealCost").disabled = "";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curBeautyBeCost = document.getElementById("makeBeautyBeCost").value;
		var curBeautyRealCost = document.getElementById("makeBeautyRealCost").value;
		var lastAllBeCost = parseInt(curAllBeCost) + parseInt(curBeautyBeCost);
		var lastAllRealCost = parseInt(curAllRealCost)
				+ parseInt(curBeautyRealCost);
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(curBeautyBeCost) - parseInt(curBeautyRealCost);
	}
}

function cancelLeaveRoomCost() {
	if (document.getElementById("leaveRoomBox").checked == false) {
		document.getElementById("leaveRoomGrade").disabled = "true";
		document.getElementById("leaveRoomBeCost").disabled = "true";
		document.getElementById("leaveRoomRealCost").disabled = "true";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curLeaveRoomBeCost = document.getElementById("leaveRoomBeCost").value;
		var curLeaveRoomRealCost = document.getElementById("leaveRoomRealCost").value;
		var lastAllBeCost = curAllBeCost - curLeaveRoomBeCost;
		var lastAllRealCost = curAllRealCost - curLeaveRoomRealCost;
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(curLeaveRoomBeCost) + parseInt(curLeaveRoomRealCost);
	}
	if (document.getElementById("leaveRoomBox").checked == true) {
		document.getElementById("leaveRoomGrade").disabled = "";
		document.getElementById("leaveRoomBeCost").disabled = "";
		document.getElementById("leaveRoomRealCost").disabled = "";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curLeaveRoomBeCost = document.getElementById("leaveRoomBeCost").value;
		var curLeaveRoomRealCost = document.getElementById("leaveRoomRealCost").value;
		var lastAllBeCost = parseInt(curAllBeCost)
				+ parseInt(curLeaveRoomBeCost);
		var lastAllRealCost = parseInt(curAllRealCost)
				+ parseInt(curLeaveRoomRealCost);
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(curLeaveRoomBeCost) - parseInt(curLeaveRoomRealCost);
	}
}

function cancelCremationCost() {
	if (document.getElementById("cremationBox").checked == false) {
		document.getElementById("cremationGrade").disabled = "true";
		document.getElementById("cremationBeCost").disabled = "true";
		document.getElementById("cremationRealCost").disabled = "true";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curCremationBeCost = document.getElementById("cremationBeCost").value;
		var curCremationRealCost = document.getElementById("cremationRealCost").value;
		var lastAllBeCost = curAllBeCost - curCremationBeCost;
		var lastAllRealCost = curAllRealCost - curCremationRealCost;
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(curCremationBeCost) + parseInt(curCremationRealCost);
	}
	if (document.getElementById("cremationBox").checked == true) {
		document.getElementById("cremationGrade").disabled = "";
		document.getElementById("cremationBeCost").disabled = "";
		document.getElementById("cremationRealCost").disabled = "";
		var curAllBeCost = document.getElementById("allBeCost").value;
		var curAllRealCost = document.getElementById("allRealCost").value;
		var curCremationBeCost = document.getElementById("cremationBeCost").value;
		var curCremationRealCost = document.getElementById("cremationRealCost").value;
		var lastAllBeCost = parseInt(curAllBeCost)
				+ parseInt(curCremationBeCost);
		var lastAllRealCost = parseInt(curAllRealCost)
				+ parseInt(curCremationRealCost);
		document.getElementById("allBeCost").value = lastAllBeCost;
		document.getElementById("allRealCost").value = lastAllRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(curCremationBeCost) - parseInt(curCremationRealCost);
	}
}
function changeUrnBeCost() {
	var urnBeCost = document.getElementById("urnBeCost");
	var curUrnBeCost = urnBeCost.value;
	if (checkNumber(curUrnBeCost)) {

		var preUrnBeCost = document.getElementById("preUrnBeCost").value;
		var preAllBeCost = document.getElementById("allBeCost").value;
		var changeUrnBeCost = curUrnBeCost - preUrnBeCost;
		var allBeCost = parseInt(preAllBeCost) + parseInt(changeUrnBeCost);
		document.getElementById("allBeCost").value = allBeCost;
		document.getElementById("preUrnBeCost").value = curUrnBeCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(changeUrnBeCost);
	}
}
function changeUrnRealCost() {
	var urnRealCost = document.getElementById("urnRealCost");
	var curUrnRealCost = urnRealCost.value;
	if (checkNumber(curUrnRealCost)) {
		var preUrnRealCost = document.getElementById("preUrnRealCost").value;
		var preAllRealCost = document.getElementById("allRealCost").value;
		var changeUrnRealCost = curUrnRealCost - preUrnRealCost;
		var allRealCost = parseInt(preAllRealCost)
				+ parseInt(changeUrnRealCost);
		document.getElementById("allRealCost").value = allRealCost;
		document.getElementById("preUrnRealCost").value = curUrnRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(changeUrnRealCost);
	} else {
		urnRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}
function changeBeautyBeCost() {

	var BeautyBeCost = document.getElementById("makeBeautyBeCost");
	var curBeautyBeCost = BeautyBeCost.value;
	if (checkNumber(curBeautyBeCost)) {

		var preBeautyBecost = document.getElementById("preMakeBeautyBeCost").value;
		var preAllBeCost = document.getElementById("allBeCost").value;

		var changeBeautyBeCost = curBeautyBeCost - preBeautyBecost;
		
		var allBeCost = parseInt(preAllBeCost) + parseInt(changeBeautyBeCost);
		document.getElementById("allBeCost").value = allBeCost;
		document.getElementById("preMakeBeautyBeCost").value = curBeautyBeCost;
		

		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(changeBeautyBeCost);
	}
}
function changeBeautyRealCost() {
	var BeautyRealCost = document.getElementById("makeBeautyRealCost");
	var curBeautyRealCost = BeautyRealCost.value;
	if (checkNumber(curBeautyRealCost)) {
		var preBeautyRealCost = document
				.getElementById("preMakeBeautyRealCost").value;
		var preAllRealCost = document.getElementById("allRealCost").value;
		var changeBeautyRealCost = curBeautyRealCost - preBeautyRealCost;
		var allRealCost = parseInt(preAllRealCost)
				+ parseInt(changeBeautyRealCost);
		document.getElementById("allRealCost").value = allRealCost;
		document.getElementById("preMakeBeautyRealCost").value = curBeautyRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(changeBeautyRealCost);
	} else {
		BeautyRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}
function changeLeaveRoomBeCost() {

	var LeaveRoomBeCost = document.getElementById("leaveRoomBeCost");
	var curLeaveRoomBeCost = LeaveRoomBeCost.value;
	if (checkNumber(curLeaveRoomBeCost)) {

		var preLeaveRoomBeCost = document.getElementById("preLeaveRoomBeCost").value;
		var preAllBeCost = document.getElementById("allBeCost").value;

		var changeLeaveRoomBeCost = curLeaveRoomBeCost - preLeaveRoomBeCost;

		var allBeCost = parseInt(preAllBeCost)
				+ parseInt(changeLeaveRoomBeCost);
		document.getElementById("allBeCost").value = allBeCost;
		document.getElementById("preLeaveRoomBeCost").value = curLeaveRoomBeCost;

		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(changeLeaveRoomBeCost);
	}

}
function changeLeaveRoomRealCost() {
	var leaveRoomRealCost = document.getElementById("leaveRoomRealCost");
	var curLeaveRoomRealCost = leaveRoomRealCost.value;
	if (checkNumber(curLeaveRoomRealCost)) {
		var preLeaveRoomRealCost = document
				.getElementById("preLeaveRoomRealCost").value;
		var preAllRealCost = document.getElementById("allRealCost").value;
		var changeLeaveRoomRealCost = curLeaveRoomRealCost
				- preLeaveRoomRealCost;
		var allRealCost = parseInt(preAllRealCost)
				+ parseInt(changeLeaveRoomRealCost);
		document.getElementById("allRealCost").value = allRealCost;
		document.getElementById("preLeaveRoomRealCost").value = curLeaveRoomRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(changeLeaveRoomRealCost);
	} else {
		leaveRoomRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}

function changeCremationBeCost() {

	var cremationBeCost = document.getElementById("cremationBeCost");
	var curCremationBeCost = cremationBeCost.value;
	if (checkNumber(curCremationBeCost)) {

		var preCremationBeCost = document.getElementById("preCremationBeCost").value;
		var preAllBeCost = document.getElementById("allBeCost").value;

		var changeCremationBeCost = curCremationBeCost - preCremationBeCost;

		var allBeCost = parseInt(preAllBeCost)
				+ parseInt(changeCremationBeCost);
		document.getElementById("allBeCost").value = allBeCost;
		document.getElementById("preCremationBeCost").value = curCremationBeCost;

		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				+ parseInt(changeCremationBeCost);
	}

}
function changeCremationRealCost() {
	var cremationRealCost = document.getElementById("cremationRealCost");
	var curCremationRealCost = cremationRealCost.value;
	if (checkNumber(curCremationRealCost)) {
		var preCremationRealCost = document
				.getElementById("preCremationRealCost").value;
		var preAllRealCost = document.getElementById("allRealCost").value;
		var changeCremationRealCost = curCremationRealCost
				- preCremationRealCost;
		var allRealCost = parseInt(preAllRealCost)
				+ parseInt(changeCremationRealCost);
		document.getElementById("allRealCost").value = allRealCost;
		document.getElementById("preCremationRealCost").value = curCremationRealCost;
		document.form4.theWholeCost.value = parseInt(document.form4.theWholeCost.value)
				- parseInt(changeCremationRealCost);
	} else {
		cremationRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}
function decideYourChoose()
{
	var tableName=document.getElementById("allFuneralGoods");			//获取table中丧葬物品对象
	var len=tableName.rows.length;
	var numbers=0;
	var arrFuneralGoods = new Array();
	var jsonUrn="";
	var jsonMakeBeauty="";
	var jsonLeaveRoom="";
	var jsonCremation="";
	for(var i=2; i<len; i++)
	{
		var goodsCheckBoxInput1=tableName.rows[i].cells[4].getElementsByTagName("input");
		if(goodsCheckBoxInput1[0].checked)
		{
			var goodsName=tableName.rows[i].cells[0].innerText;
			var goodsBeCostInput=tableName.rows[i].cells[1].getElementsByTagName("input");
			var goodsBeCost=goodsBeCostInput[0].value;
			var goodsRealCostInput=tableName.rows[i].cells[2].getElementsByTagName("input");
			var goodsRealCost=goodsRealCostInput[0].value;
			arrFuneralGoods[numbers]="{goodsName:'"+goodsName+"',goodsBeCost:'"+goodsBeCost+"',goodsRealCost:'"+goodsRealCost+"'}";
//			arrFuneralGoods[numbers]="{'goodsName':'"+goodsName+"','goodsBeCost':'"+goodsBeCost+"','goodsRealCost':'"+goodsRealCost+"'}";
//			arrFuneralGoods[numbers]='{"goodsName":'+goodsName+',"goodsBeCost":'+goodsBeCost+',"goodsRealCost":'+goodsRealCost+'}';
//			arrFuneralGoods[numbers]={"goodsName":goodsName,"goodsBeCost":goodsBeCost,"goodsRealCost":goodsRealCost};
			numbers++;
		}
		var goodsCheckBoxInput2=tableName.rows[i].cells[12].getElementsByTagName("input");
		if(goodsCheckBoxInput2[0]!=null)
		{
			if(goodsCheckBoxInput2[0].checked)
			{
				//alert("文本框被选中！");
				var goodsName=tableName.rows[i].cells[8].innerText;
				var goodsBeCostInput=tableName.rows[i].cells[9].getElementsByTagName("input");
				var goodsBeCost=goodsBeCostInput[0].value;
				var goodsRealCostInput=tableName.rows[i].cells[10].getElementsByTagName("input");
				var goodsRealCost=goodsRealCostInput[0].value;
				arrFuneralGoods[numbers]="{goodsName:'"+goodsName+"',goodsBeCost:'"+goodsBeCost+"',goodsRealCost:'"+goodsRealCost+"'}";
//				arrFuneralGoods[numbers]="{'goodsName':'"+goodsName+"','goodsBeCost':'"+goodsBeCost+"','goodsRealCost':'"+goodsRealCost+"'}";
//				arrFuneralGoods[numbers]='{"goodsName":'+goodsName+',"goodsBeCost":'+goodsBeCost+',"goodsRealCost":'+goodsRealCost+'}';
//				arrFuneralGoods[numbers]={"goodsName":goodsName,"goodsBeCost":goodsBeCost,"goodsRealCost":goodsRealCost};
				numbers++;
			}
		}
	}

	
	if (document.form4.urnChooseBox.checked == true) {
		var urnChoose = document.form4.urnChoose;
		var urnChooseIndex = urnChoose.selectedIndex;

		var urnName = urnChoose.options[urnChooseIndex].text;
		if (urnName != "-请选择-") {
			var urnBeCost = document.form4.urnBeCost.value;
			var urnChangeCost = document.form4.urnRealCost.value;
			var urnRealCost = urnBeCost - urnChangeCost + "";
			// jsonUrn='{"urnName":'+urnName+',"urnBeCost":'+urnBeCost+',"urnRealCost":'+urnRealCost+'}';
			jsonUrn = "{'urnName':'" + urnName + "','urnBeCost':'" + urnBeCost
					+ "','urnRealCost':'" + urnRealCost + "'}";
			// "{'json':'jsonvalue','bool':true,'int':1,'double':'20.5'}"
		} else {
			jsonUrn = "";
			alert("请确认已选择骨灰盒");
			document.form4.urnChoose.focus();
			return false;
		}
	}

	if (document.form4.makeBeautyBox.checked == true) {
		var makeBeautyGrade = document.form4.makeBeautyGrade;
		var makeBeautyGradeIndex = makeBeautyGrade.selectedIndex;
		var makeBeautyName = makeBeautyGrade.options[makeBeautyGradeIndex].text;
		if (makeBeautyName != "-请选择-") {

			var makeBeautyBeCost = document.form4.makeBeautyBeCost.value;
			var makeBeautyChangeCost = document.form4.makeBeautyRealCost.value;
			var makeBeautyRealCost = makeBeautyBeCost - makeBeautyChangeCost
					+ "";
			if(makeBeautyRealCost==0){
				jsonMakeBeauty="";
			}else{
				
			
			// jsonMakeBeauty='{"makeBeautyName":'+makeBeautyName+',"makeBeautyBeCost":'+makeBeautyBeCost+',"makeBeautyRealCost":'+makeBeautyRealCost+'}';
			jsonMakeBeauty = "{'makeBeautyName':'" + makeBeautyName
					+ "','makeBeautyBeCost':'" + makeBeautyBeCost
					+ "','makeBeautyRealCost':'" + makeBeautyRealCost + "'}";
			}
		} else {
			jsonMakeBeauty = "";
			alert("请确认已选择美容");
			document.form4.makeBeautyGrade.focus();
			return false;
		}
	}
	

	if (document.form4.leaveRoomBox.checked == true) {
		var leaveRoomGrade = document.form4.leaveRoomGrade;
		var leaveRoomGradeIndex = leaveRoomGrade.selectedIndex;
		var leaveRoomName = leaveRoomGrade.options[leaveRoomGradeIndex].text;
		if (leaveRoomName != "-请选择-") {
			var leaveRoomBeCost = document.form4.leaveRoomBeCost.value;
			var leaveRoomChangeCost = document.form4.leaveRoomRealCost.value;
			var leaveRoomRealCost = leaveRoomBeCost - leaveRoomChangeCost + "";
			jsonLeaveRoom = "{'leaveRoomName':'" + leaveRoomName
					+ "','leaveRoomBeCost':'" + leaveRoomBeCost
					+ "','leaveRoomRealCost':'" + leaveRoomRealCost + "'}";
			// jsonLeaveRoom='{"leaveRoomName":'+leaveRoomName+',"leaveRoomBeCost":'+leaveRoomBeCost+',"leaveRoomRealCost":'+leaveRoomRealCost+'}';
			// jsonLeaveRoom={"leaveRoomName":leaveRoomName,"leaveRoomBeCost":leaveRoomBeCost,"leaveRoomRealCost":leaveRoomRealCost};
		} else {
			jsonLeaveRoom = "";
			alert("请确认已选择告别厅");
			document.form4.leaveRoomGrade.focus();
			return false;
		}
	}

	if (document.form4.cremationBox.checked == true) {
		var cremationGrade = document.form4.cremationGrade;
		var cremationGradeIndex = cremationGrade.selectedIndex;
		var cremationName = cremationGrade.options[cremationGradeIndex].text;
		if (cremationName != "-请选择-") {
			var cremationBeCost = document.form4.cremationBeCost.value;
			var cremationChangeCost = document.form4.cremationRealCost.value;
			var cremationRealCost = cremationBeCost - cremationChangeCost + "";
			jsonCremation = "{'cremationName':'" + cremationName
					+ "','cremationBeCost':'" + cremationBeCost
					+ "','cremationRealCost':'" + cremationRealCost + "'}";
			// jsonCremation='{"cremationName":'+cremationName+',"cremationBeCost":'+cremationBeCost+',"cremationRealCost":'+cremationRealCost+'}';
			// jsonCremation={"cremationName":cremationName,"cremationBeCost":cremationBeCost,"cremationRealCost":cremationRealCost};
		} else {
			jsonCremation = "";
			alert("请确认已选择火化炉");
			document.form4.cremationGrade.focus();
			return false;
		}
	}
	var status;
	if(document.form4.makeBeautyBox.checked==true)
	{
		status="2";
	}
	var urn=jsonUrn+"";
	var makeBeauty=jsonMakeBeauty+"";
	var leaveRoom=jsonLeaveRoom+"";
	var cremation=jsonCremation+"";
	var funeralGoods=arrFuneralGoods+"";
	url="deadId="+document.form4.deadId.value+"&";
	url=url+"funeralGoods="+funeralGoods+"&";
	url=url+"urn="+urn+"&";
	url=url+"makeBeauty="+makeBeauty+"&";
	url=url+"leaveRoom="+leaveRoom+"&";
	url=url+"cremation="+cremation+"&";
	url=url+"status="+status;
	http_request=createHttpRequest();
	http_request.onreadystatechange=decideYourChooseCallBack;
	http_request.open('POST',"AddOrderServiceAction!addOrderService",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}

function decideYourChooseCallBack()
{
	if(http_request.readyState==4)
	{
		if(http_request.status==200)
		{
			alert(http_request.responseText);
		}
	}
}

	

	
					
	
function outPrintServiceList(){
	var printContent = document.getElementById("goodsPartList");
	var iframe = document.createElement('IFRAME');
 	var doc = null;
 	
 	iframe.setAttribute('id', 'printIframe');
 	iframe.setAttribute('style', 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
 	
 	document.body.appendChild(iframe);
 	doc = iframe.contentWindow.document;
 
 	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();
	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}
function gotoRegisterServicePage() {
	var deadId = document.form3.deadId.value;
	var printDeadInfoPageLi = document.getElementById("serviceRegisterPage");
	printDeadInfoPageLi.click();
	document.form4.deadId.value = deadId;
	document.form4.deadId.onchange();
}
function goToReturnCoffin() { // 页面跳转
	window.location.reload("rentCoffin.jsp?deadId="
			+ document.form2.deadId.value + "&index=" + 2 + "&type=" + "yuyue");
}
function checkNumber(number) {
	var reg = /^\d+(\.\d+)?$/;
	if (reg.test(number) == true) {
		return true;
	} else {
		return false;
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
	
	getFee(deadId);
	

	url = "deadId=" + deadId;
	
	

	http_request = createHttpRequest();

	http_request.onreadystatechange = getCremationServicefCallBack;

	http_request.open('POST', "getCremationServiceAction!getCremationService",
			false);

	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	http_request.send(url);

	return false;// 结束时间

}

function getCremationServicefCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result = http_request.responseText;
//			alert(result)
			var json = eval("(" + result + ")");	
			var jsonValue = json.returnString;		
			var jsonValue2 = eval("(" + jsonValue + ")");
			var length =jsonValue2.length;		
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

            
			for(var i=0;i<length;i++){
				var CremationTypeNo = jsonValue2[i].CremationTypeNo;
				
				var status = jsonValue2[i].status;
				var deadId1=jsonValue2[i].deadId;
				
				if(CremationTypeNo=="03"&&status=="2"){
					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value = jsonValue2[i].itemRealCost;								
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);					
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

					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("cremationRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);			
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
					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemBeCost);
					
					document.getElementById("cremationRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);				
					document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
					
					
					document.getElementById("cremationstove").disabled = "true";
					document.getElementById("cremationStatus").value = "火化尚未开始";
					document.getElementById("cremationStatus").disabled = "true";
					document.getElementById("cremationGrade").disabled = "";
					document.getElementById("cremationBeCost").disabled = "";
					document.getElementById("cremationRealCost").disabled = "";
						
					}
				if(CremationTypeNo=="02"&&status=="2"){
					
					document.getElementById("farewell").value=jsonValue2[i].itemName;
					document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
					document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
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
						document.getElementById("farewell").value=jsonValue2[i].itemName;
						document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
						document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
						document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
					document.getElementById("farewell").disabled = "true";
					document.getElementById("farewellStatus").value = "告别正在进行";
					document.getElementById("farewellStatus").disabled = "true";
					document.getElementById("leaveRoomBeCost").disabled = "";
					document.getElementById("leaveRoomRealCost").disabled = "";
					document.getElementById("leaveRoomGrade").disabled = "";
						
					}
					if(CremationTypeNo=="02"&&status=="0"){
						document.getElementById("farewell").value=jsonValue2[i].itemName;
						document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						
						document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
						document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
						document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
					
					document.getElementById("farewell").disabled = "true";
					document.getElementById("farewellStatus").value = "告别尚未开始";
					document.getElementById("farewellStatus").disabled = "true";
					document.getElementById("leaveRoomBeCost").disabled = "";
					document.getElementById("leaveRoomRealCost").disabled = "";
					document.getElementById("leaveRoomGrade").disabled = "";
						
					}
				
					
				if(CremationTypeNo=="01"&&status=="2"){
					
					
					document.getElementById("beauty").value=jsonValue2[i].itemName;					
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
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
					document.getElementById("beauty").value=jsonValue2[i].itemName;					
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
					
					document.getElementById("beauty").disabled = "true";
					document.getElementById("beautyStatus").value = "美容正在进行";
					document.getElementById("beautyStatus").disabled = "true";
					document.getElementById("makeBeautyBeCost").disabled = "";
					document.getElementById("makeBeautyRealCost").disabled = "";
					document.getElementById("makeBeautyGrade").disabled = "";
					
					}
				if(CremationTypeNo=="01"&&status=="0"){
					
					document.getElementById("beauty").value=jsonValue2[i].itemName;	
					
					
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);					
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyBeCost").value-document.getElementById("makeBeautyRealCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
				   
				   
                   document.getElementById("beauty").disabled = "true";
                   document.getElementById("beautyStatus").value = "美容尚未开始";
                   document.getElementById("beautyStatus").disabled = "true";
                   document.getElementById("makeBeautyBeCost").disabled = "";
                   document.getElementById("makeBeautyRealCost").disabled = "";
                   document.getElementById("makeBeautyGrade").disabled = "";
					
				}
				if(CremationTypeNo=="00"){
					
					   document.getElementById("GatecremationType").value=jsonValue2[i].itemName;					
					   document.getElementById("GatecremationType").value = status;
						
					}
				document.getElementById("servicedeadName").value = jsonValue2[i].deadName;
				document.getElementById("deadId").value = jsonValue2[i].deadId;	
				if(jsonValue2[i].urnName!=null){
				document.getElementById("urnBeCost").value = Number(jsonValue2[i].urnBeCost);
				document.getElementById("urnRealCost").value = Number(jsonValue2[i].urnRealCost);
				document.getElementById("urnName").value = jsonValue2[i].urnName;
				
				document.getElementById("urnRealCost").value=Number(document.getElementById("urnBeCost").value)-Number(document.getElementById("urnRealCost").value);
				document.getElementById("preUrnRealCost").value = Number(document.getElementById("urnRealCost").value);
				document.getElementById("preUrnBeCost").value = Number(jsonValue2[i].urnBeCost);
				}
															
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
			changeGood(deadId1);
			var table2=document.getElementById("showallFuneralGoods");
			var rows=table2.rows.length;
			
			for(var k=2;k<rows;k++){
				var reduceMoney=table2.rows[k].cells[2].getElementsByTagName("input");
				var reduceMoney1=table2.rows[k].cells[9].getElementsByTagName("input");
				
				var hideMoney=table2.rows[k].cells[4];
				var hideValue=hideMoney.getElementsByTagName("input");
				    hideValue[0].value=reduceMoney[0].value;
				    
				
				
				
				var hideMoney1=table2.rows[k].cells[6];
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
//				alert("美容为空");
				makeBeautyBox.checked=false;
				
				var urnChoose=document.getElementById("makeBeautyGrade");
				makeBeautyGrade.disabled="true";
					document.getElementById("makeBeautyBeCost").disabled="true";
					document.getElementById("makeBeautyRealCost").disabled="true";
				setSelected(makeBeautyGrade,document.getElementById("beauty").value);
			}
			if(document.getElementById("farewell").value!=null){
//				alert("告别不为空");				
				leaveRoomBox.checked=true;
				
				var leaveRoomGrade=document.getElementById("leaveRoomGrade");
				
				setSelected(leaveRoomGrade,document.getElementById("farewell").value)
								
			}
			if(document.getElementById("farewell").value==""){
//				alert("告别为空");
				leaveRoomBox.checked=false;
				
				var leaveRoomGrade=document.getElementById("leaveRoomGrade");
					leaveRoomGrade.disabled="true";
					document.getElementById("leaveRoomBeCost").disabled="true";
					document.getElementById("leaveRoomRealCost").disabled="true";
				setSelected(leaveRoomGrade,document.getElementById("farewell").value);
				
			}
			if(document.getElementById("cremationstove").value!=null){
//				alert("火化炉不为空");
								
				cremationBox.checked=true;
				
				var cremationGrade=document.getElementById("cremationGrade");
				
				setSelected(cremationGrade,document.getElementById("cremationstove").value)
				
			}
			if(document.getElementById("cremationstove").value==""){
//				alert("火化炉为空");
				cremationBox.checked=false;
				
				var cremationGrade=document.getElementById("cremationGrade");
					cremationGrade.disabled="true";
					document.getElementById("cremationBeCost").disabled="true";
					document.getElementById("cremationRealCost").disabled="true";
				setSelected(cremationGrade,document.getElementById("cremationstove").value);
				
			}
				
		}

	}
	
}
function getPathogenyDetail() {
	var deadReasonSelect = document.form4.deadReason;
	var deadReasonIndex = deadReasonSelect.selectedIndex;
	var deadReason = deadReasonSelect.options[deadReasonIndex].text;
	
	if (deadReason == "病故") {
		document.form4.pathogeny.style.display = "";
	}
	if (deadReason != "病故") {
		document.form4.pathogeny.style.display = "none";
	}
}
function decideInvoice() {
	var deadId = document.form4.deadId.value;
	var deadName = document.form4.deadName.value;
	var deadSex = document.form4.deadSex.value;
	var deadAge = document.form4.deadAge.value;
	
	var invoiceNo = document.form4.invoiceNo.value;
	if(invoiceNo==""){
			invoiceNo="0";
	}
	
	var subsidyNo = document.form4.subsidyNo.value;
	if(subsidyNo==""){
		subsidyNo="0";
	}
	var benefitTime = document.form4.benefitTime.value;
	if(benefitTime==""){
		benefitTime="0001-01-01 00:00:00 ";
	}
	
	var subsidyMoney = document.form4.subsidyMoney.value;
	if(subsidyMoney==""){
		subsidyMoney="0";
	}
	var dealerId = document.form4.dealerId.value;
	var dealerName = document.form4.dealerName.value;
	var directorName = document.form4.directorName.value;
	var dealerAddress = document.form4.dealerAddress.value;
	var deadTime = document.form4.deadTime.value;
	
	if(deadTime==""){
		alert("死亡时间为空，请输入死亡时间");
		form4.deadTime.focus();
		return false;
	}

	var deadKindSelect = document.form4.deadKind;
	var deadKindIndex = deadKindSelect.selectedIndex;
	var deadKind = deadKindSelect.options[deadKindIndex].text;

	var deadReasonSelect = document.form4.deadReason;
	var deadReasonIndex = deadReasonSelect.selectedIndex;
	var deadReason = deadReasonSelect.options[deadReasonIndex].text;

	var deadAddress = document.form4.deadAddress.value;

	var areaSelect = document.form4.area;
	var areaIndex = areaSelect.selectedIndex;
	var area = areaSelect.options[areaIndex].text;

	var deadProveUnit = document.form4.deadProveUnit.value;
	var deadExtraInfo = document.form4.deadExtraInfo.value;
	var memberMobile = document.form4.memberMobile.value;
	if (!(validatePhoneNumber(memberMobile))) {
		alert("电话号码格式错误，请检查后重新输入！");
		form4.memberMobile.focus();
		return false;
	}
	url = "deadInfo.deadId=" + deadId + "&"; //
	url = url + "deadInfo.deadName=" + deadName + "&";
	url = url + "deadInfo.deadSex=" + deadSex + "&";
	url = url + "deadInfo.deadAge=" + deadAge + "&";
	url = url + "deadInfo.memberMobile=" + memberMobile + "&";
	url = url + "deadInfo.dealerId=" + dealerId + "&";
	url = url + "deadInfo.dealerName=" + dealerName + "&";
	url = url + "deadInfo.directorName=" + directorName + "&";
	url = url + "deadInfo.dealerAddress=" + dealerAddress + "&";
	url = url + "deadInfo.deadTime=" + deadTime + "&";
	url = url + "deadInfo.deadKind=" + deadKind + "&";
	url = url + "deadInfo.deadReason=" + deadReason + "&";
	url = url + "deadInfo.deadAddress=" + deadAddress + "&";

	if (area != "请选择...") {
		url = url + "deadInfo.area=" + area + "&";
	} else {
		alert("请选择死者所属区域"); // 死者所属区域
		areaSelect.focus();
		return false;
	}

	url = url + "deadInfo.deadProveUnit=" + deadProveUnit + "&";
	url = url + "deadInfo.deadExtraInfo=" + deadExtraInfo + "&";
	url = url + "invoiceNo=" + invoiceNo + "&";
	url = url + "benefitTime=" + benefitTime + "&";
	url = url + "subsidyNo=" + subsidyNo + "&";

	if (deadReason != "请选择") {
		if (deadReason == "病故") {
			var pathogenySelect = document.form4.pathogeny;
			var pathogenyIndex = pathogenySelect.selectedIndex;
			var pathogeny = pathogenySelect.options[pathogenyIndex].text;
			url = url + "deadInfo.pathogeny=" + pathogeny + "&"; // 具体病因
		}
		if (deadReason != "病故") {
			url = url + "deadInfo.pathogeny=" + "" + "&"; // 具体病因
		}
	} else {
		alert("请选择死亡原因");
		deadReasonSelect.focus();
		return false;
	}

	var operatorRelationSelect = document.form4.operatorRelation;
	var operatorRelationIndex = operatorRelationSelect.selectedIndex;
	var operatorRelation = operatorRelationSelect.options[operatorRelationIndex].text;
	if (operatorRelation != "请选择...") {
		url = url + "deadInfo.operatorRelation=" + operatorRelation + "&"; // 与经办人的关系
	} else {
		alert("请选择与经办人的关系");
		operatorRelationSelect.focus();
		return false;
	}

	var deadResidenceSelect = document.form4.deadResidence;
	var deadResidenceIndex = deadResidenceSelect.selectedIndex;
	var deadResidence = deadResidenceSelect.options[deadResidenceIndex].text;
	if (deadResidence != "请选择...") {
		url = url + "deadInfo.deadResidence=" + deadResidence + "&"; // 户籍所在地
	} else {
		alert("请选择户籍所在地");
		deadResidenceSelect.focus();
		return false;
	}

	var ashesDispositionSelect = document.form4.ashesDisposition;
	var ashesDispositionIndex = ashesDispositionSelect.selectedIndex;
	var ashesDisposition = ashesDispositionSelect.options[ashesDispositionIndex].text;
	if (ashesDisposition != "请选择...") {
		url = url + "deadInfo.ashesDisposition=" + ashesDisposition + "&"; // 骨灰去向
	} else {
		alert("请选择骨灰去向");
		ashesDispositionSelect.focus();
		return false;
	}

	url = url + "subsidyMoney=" + subsidyMoney;
	http_request = createHttpRequest();
	http_request.onreadystatechange = decideInvoiceAndSubsidyCallback;
	http_request.open('POST', "DeadInfoRegisterAction!decideInvoiceAndSubsidy",
			false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	
	return false;// 阻止页面刷新
}

function decideInvoiceAndSubsidyCallback() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			alert(http_request.responseText);
		}
	}
}
function outPutServiceList() {
	document.getElementById("name").innerHTML = "";
	document.getElementById("no").innerHTML = "";
	document.getElementById("taxDate").innerHTML = "";
	document.getElementById("sex").innerHTML = "";
	document.getElementById("age").innerHTML = "";
	document.getElementById("address").innerHTML = "";

	document.getElementById("urn").innerHTML = "";
	document.getElementById("urn1").innerHTML = "";
	document.getElementById("urn2").innerHTML = "";
	document.getElementById("face").innerHTML = "";
	document.getElementById("face1").innerHTML = "";
	document.getElementById("face2").innerHTML = "";
	document.getElementById("farewell").innerHTML = "";
	document.getElementById("farewell1").innerHTML = "";
	document.getElementById("farewell2").innerHTML = "";
	document.getElementById("cremation").innerHTML = "";
	document.getElementById("cremation1").innerHTML = "";
	document.getElementById("cremation2").innerHTML = "";


	document.getElementById("car").innerHTML = "";
	document.getElementById("car1").innerHTML = "";
	document.getElementById("car2").innerHTML = "";
	document.getElementById("car3").innerHTML = "";
	document.getElementById("car4").innerHTML = "";
	document.getElementById("car5").innerHTML = "";
	document.getElementById("watch").innerHTML = "";
	document.getElementById("watch1").innerHTML = "";
	document.getElementById("watch2").innerHTML = "";
	document.getElementById("crystal").innerHTML = "";
	document.getElementById("crystal1").innerHTML = "";
	document.getElementById("crystal2").innerHTML = "";

	document.getElementById("currentDateTh2").innerHTML = "";
	document.getElementById("no").innerHTML = "";
	var allGoodsTbody = document.getElementById("all");
	var allFuneralGoodsRows = allGoodsTbody.rows.length;
	for (var n = 0; n < allFuneralGoodsRows; n++) {
		// var goodsName1=allGoods.rows[n].cells[0].innerText;
		allGoodsTbody.rows[n].cells[1].innerHTML = "";
		allGoodsTbody.rows[n].cells[2].innerHTML = "";
		allGoodsTbody.rows[n].cells[3].innerHTML = "";
		var goods2 = allGoodsTbody.rows[n].cells[4].innerText;
		if (goods2 != "") {
			allGoodsTbody.rows[n].cells[5].innerHTML = "";
			allGoodsTbody.rows[n].cells[6].innerHTML = "";
			allGoodsTbody.rows[n].cells[7].innerHTML = "";
		}
		var goods3 = allGoodsTbody.rows[n].cells[8].innerText;
		if (goods3 != "") {
			allGoodsTbody.rows[n].cells[9].innerHTML = "";
			allGoodsTbody.rows[n].cells[10].innerHTML = "";
			allGoodsTbody.rows[n].cells[11].innerHTML = "";
		}

	}

	document.getElementById("total").innerHTML = "";
	document.getElementById("beCost").innerHTML = "";
	document.getElementById("charge").innerHTML = "";
	document.getElementById("capitalMoney").innerHTML = "";

	// 清空先前生成的表单后生成新的表单
	document.getElementById("name").innerHTML = "<font style='font-weight:bold;'>"
			+ form4.deadName.value + "</font>";
	// document.getElementById("no").innerHTML="<font
	// style='font-weight:bold;font-size:22px'>"+form4.deadNumber.value+"</font>";
	document.getElementById("currentDateTh2").innerHTML = "<font style='font-weight:bold;'>"
			+"VIP"+ form4.deadNumber.value + "</font>";
	document.getElementById("no").innerHTML = "<font style='font-weight:bold;'>"
			+ document.getElementById("invoiceNo").value + "</font>";
	var car1Cost = form4.remainsCarryBeCost.value;
	var car1RealCost = form4.remainsCarryRealCost.value;
	var car2Cost=form4.rentCrystalCarBeCost.value;
	var car2RealCost=form4.rentCrystalCarRealCost.value;
	var crystalCost=form4.rentCrystalBeCost.value;
	var crystalRealCost=form4.rentCrystalRealCost.value;
	var watchSpiritCost=parseInt(form4.watchSpiritVillaBeCost.value)+parseInt(form4.watchSpiritCoffinBeCost.value);
	var watchSpiritRealCost=parseInt(form4.watchSpiritVillaRealCost.value)+parseInt(form4.watchSpiritCoffinRealCost.value);


	var allServiceShouldCost = form4.allBeCost.value;

	if (form4.theWholeCost.value != "0") {
		document.getElementById("total").innerHTML = "<font style='font-weight:bold;'>"
				+ form4.theWholeCost.value + "</font>";
	}
	if (allServiceShouldCost != "0") {
		document.getElementById("beCost").innerHTML = "<font style='font-weight:bold;'>"
				+ allServiceShouldCost + "</font>";
	}
	if (form4.allRealCost.value != "0") {
		document.getElementById("charge").innerHTML = "<font style='font-weight:bold;'>"
				+ "-" + form4.allRealCost.value + "</font>";
	}

	if (car1Cost != 0) {
		document.getElementById("car1").innerHTML = "<font style='font-weight:bold;'>"
				+ car1Cost + "</font>";
		var charge3 = car1Cost - car1RealCost;
		if (charge3 != 0) {
			document.getElementById("car2").innerHTML = "<font style='font-weight:bold;'>"
					+ "-" + charge3 + "</font>";
		}
	}
	if(car2Cost!=0){
		document.getElementById("car4").innerHTML="<font style='font-weight:bold;'>"+car2Cost+"</font>";
		var charge4=car2Cost-car2RealCost;
		if(charge4!=0){
			document.getElementById("car5").innerHTML="<font style='font-weight:bold;'>"+"-"+charge4+"</font>";
		}
	}
	if(crystalCost!=0){
		document.getElementById("crystal").innerHTML="<font style='font-weight:bold;'>"+"8元/时"+"</font>";
		document.getElementById("crystal1").innerHTML="<font style='font-weight:bold;'>"+crystalCost+"</font>";
		var charge1=crystalCost-crystalRealCost;
		if(charge1!=0){
			document.getElementById("crystal2").innerHTML="<font style='font-weight:bold;'>"+"-"+charge1+"</font>";
		}
	}
	if(watchSpiritCost!=0){
		
		document.getElementById("watch1").innerHTML="<font style='font-weight:bold;'>"+watchSpiritCost+"</font>";
		var charge2=watchSpiritCost-watchSpiritRealCost;
		if(charge2!=0){
			document.getElementById("watch2").innerHTML="<font style='font-weight:bold;'>"+"-"+charge2+"</font>";
		}	
	}



	var urn = document.getElementById("urnChoose");
	var urn1 = urn.selectedIndex;
	if ((document.getElementById("urnChooseBox").checked)
			&& (urn.options[urn1].text != "-请选择-")) {
		document.getElementById("urn").innerHTML = "<font style='font-weight:bold;'>"
				+ urn.options[urn1].text + "</font>";
		document.getElementById("urn1").innerHTML = "<font style='font-weight:bold;'>"
				+ document.getElementById("urnBeCost").value + "</font>";
		if (document.getElementById("urnRealCost").value != "0") {
			document.getElementById("urn2").innerHTML = "<font style='font-weight:bold;'>"
					+ "-"
					+ document.getElementById("urnRealCost").value
					+ "</font>";
		} else {
			document.getElementById("urn2").innerHTML = "";
		}
	}

	var beauty = document.getElementById("makeBeautyGrade");
	var beauty1 = beauty.selectedIndex;
	if ((document.getElementById("makeBeautyBox").checked)
			&& (beauty.options[beauty1].text) != "-请选择-") {
		document.getElementById("face").innerHTML = "<font style='font-weight:bold;'>"
				+ beauty.options[beauty1].text + "</font>";
		document.getElementById("face1").innerHTML = "<font style='font-weight:bold;'>"
				+ document.getElementById("makeBeautyBeCost").value + "</font>";
		if (document.getElementById("makeBeautyRealCost").value != "0") {
			document.getElementById("face2").innerHTML = "<font style='font-weight:bold;'>"
					+ "-"
					+ document.getElementById("makeBeautyRealCost").value
					+ "</font>";
		} else {
			document.getElementById("face2").innerHTML = "";
		}

	}

	var leave = document.getElementById("leaveRoomGrade");
	var leave1 = leave.selectedIndex;
	if ((document.getElementById("leaveRoomBox").checked)
			&& (leave.options[leave1].text != "-请选择-")) {
		document.getElementById("farewell").innerHTML = "<font style='font-weight:bold;'>"
				+ leave.options[leave1].text + "</font>";
		document.getElementById("farewell1").innerHTML = "<font style='font-weight:bold;'>"
				+ document.getElementById("leaveRoomBeCost").value + "</font>";
		if (document.getElementById("leaveRoomRealCost").value != "0") {
			document.getElementById("farewell2").innerHTML = "<font style='font-weight:bold;'>"
					+ "-"
					+ document.getElementById("leaveRoomRealCost").value
					+ "</font>";
		} else {
			document.getElementById("farewell2").innerHTML = "";
		}
	}

	var cremation = document.getElementById("cremationGrade");
	var cremation1 = cremation.selectedIndex;
	if ((document.getElementById("cremationBox").checked)
			&& (cremation.options[cremation1].text != "-请选择-")) {
		document.getElementById("cremation").innerHTML = "<font style='font-weight:bold;'>"
				+ cremation.options[cremation1].text + "</font>";
		document.getElementById("cremation1").innerHTML = "<font style='font-weight:bold;'>"
				+ document.getElementById("cremationBeCost").value + "</font>";
		if (document.getElementById("cremationRealCost").value != "0") {
			document.getElementById("cremation2").innerHTML = "<font style='font-weight:bold;'>"
					+ "-"
					+ document.getElementById("cremationRealCost").value
					+ "</font>";
		} else {
			document.getElementById("cremation2").innerHTML = "";
		}

	}
	var rows = allFuneralGoods.rows.length;
	
	var tableName = document.getElementById("allFuneralGoods");
	var allGoods = document.getElementById("allGoods");
	var rows1 = allGoods.rows.length;

	if (rows > 2) {
		for (var i = 2; i < rows; i++) {
			var goodsCheckBoxInput1 = tableName.rows[i].cells[4]
					.getElementsByTagName("input");
			var goodsCheckBoxInput2 = tableName.rows[i].cells[12]
					.getElementsByTagName("input");
			if (goodsCheckBoxInput1[0].checked) {
				var goodsName = allFuneralGoods.rows[i].cells[0].innerText;

				var goodsBeCost = allFuneralGoods.rows[i].cells[1]
						.getElementsByTagName("input")[0].value;

				var goodsRealCostInput = allFuneralGoods.rows[i].cells[2]
						.getElementsByTagName("input");
				var goodsRealCost = goodsRealCostInput[0].value;
				for (var n = 3; n < rows1; n++) {
					var goodsName1 = allGoods.rows[n].cells[0].innerText;
					var goodsName2 = allGoods.rows[n].cells[4].innerText;
					var goodsName3 = allGoods.rows[n].cells[8].innerText;
					if (goodsName == goodsName1) {
						allGoods.rows[n].cells[2].innerHTML = "<font style='font-weight:bold;'>"
								+ goodsBeCost + "</font>";
						if (goodsRealCost != "0") {
							allGoods.rows[n].cells[3].innerHTML = "<font style='font-weight:bold;'>"
									+ "-" + goodsRealCost + "</font>";
						} else {
							allGoods.rows[n].cells[3].innerHTML = "";
						}
						break;
					} else if (goodsName == goodsName2) {
						allGoods.rows[n].cells[6].innerHTML = "<font style='font-weight:bold;'>"
								+ goodsBeCost + "</font>";
						if (goodsRealCost != "0") {
							allGoods.rows[n].cells[7].innerHTML = "<font style='font-weight:bold;'>"
									+ "-" + goodsRealCost + "</font>";
						} else {
							allGoods.rows[n].cells[7].innerHTML = "";
						}
						break;
					}

					else if (goodsName == goodsName3) {
						allGoods.rows[n].cells[10].innerHTML = "<font style='font-weight:bold;'>"
								+ goodsBeCost + "</font>";
						if (goodsRealCost != "0") {
							allGoods.rows[n].cells[11].innerHTML = "<font style='font-weight:bold;'>"
									+ "-" + goodsRealCost + "</font>";
						} else {
							allGoods.rows[n].cells[11].innerHTML = "";
						}
						break;
					}
				}
			}
			if (goodsCheckBoxInput2[0] != null) {
				if (goodsCheckBoxInput2[0].checked) {
					var goodsName = allFuneralGoods.rows[i].cells[8].innerText;

					var goodsBeCost = allFuneralGoods.rows[i].cells[9]
							.getElementsByTagName("input")[0].value;

					var goodsRealCostInput = allFuneralGoods.rows[i].cells[10]
							.getElementsByTagName("input");
					var goodsRealCost = goodsRealCostInput[0].value;
					for (var n = 4; n < rows1; n++) {
						var goodsName1 = allGoods.rows[n].cells[0].innerText;
						var goodsName2 = allGoods.rows[n].cells[4].innerText;
						var goodsName3 = allGoods.rows[n].cells[8].innerText;
						if (goodsName == goodsName1) {
							allGoods.rows[n].cells[2].innerHTML = "<font style='font-weight:bold;'>"
									+ goodsBeCost + "</font>";
							if (goodsRealCost != "0") {
								allGoods.rows[n].cells[3].innerHTML = "<font style='font-weight:bold;'>"
										+ "-" + goodsRealCost + "</font>";
							} else {
								allGoods.rows[n].cells[3].innerHTML = "";
							}
							break;
						} else if (goodsName == goodsName2) {
							allGoods.rows[n].cells[6].innerHTML = "<font style='font-weight:bold;'>"
									+ goodsBeCost + "</font>";
							if (goodsRealCost != "0") {
								allGoods.rows[n].cells[7].innerHTML = "<font style='font-weight:bold;'>"
										+ "-" + goodsRealCost + "</font>";
							} else {
								allGoods.rows[n].cells[7].innerHTML = "";
							}
							break;
						}

						else if (goodsName == goodsName3) {
							allGoods.rows[n].cells[10].innerHTML = "<font style='font-weight:bold;'>"
									+ goodsBeCost + "</font>";
							if (goodsRealCost != "0") {
								allGoods.rows[n].cells[11].innerHTML = "<font style='font-weight:bold;'>"
										+ "-" + goodsRealCost + "</font>";
							} else {
								allGoods.rows[n].cells[11].innerHTML = "";
							}
							break;
						}
					}
				}
			}
		}
	}
	var myDate = new Date();
	document.getElementById("taxDate").innerHTML = "<font style='font-weight:bold;'>"
			+ myDate.toLocaleDateString()
			+ " "
			+ myDate.toLocaleTimeString()
			+ "</font>";
	document.getElementById("sex").innerHTML = "<font style='font-weight:bold;'>"
			+ document.form4.deadSex.value + "</font>";
	document.getElementById("age").innerHTML = "<font style='font-weight:bold;'>"
			+ document.form4.deadAge.value + "</font>";
	document.getElementById("address").innerHTML = "<font style='font-weight:bold;font-size:14px;'>"
			+ document.form4.deadAddress.value + "</font>";
	// 显示大写金额
	var number = document.getElementById("total").innerText;
	if (!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test(number)) {
		return false;
	}
	var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
	number += "00";
	var point = number.indexOf('.');
	if (point >= 0) {
		number = number.substring(0, point) + number.substr(point + 1, 2);
	}
	unit = unit.substr(unit.length - number.length);
	for (var i = 0; i < number.length; i++) {
		str += '零壹贰叁肆伍陆柒捌玖'.charAt(number.charAt(i)) + unit.charAt(i);
	}
	// document.getElementById(idCHN).value = str.replace(/零(仟|佰|拾|角)/g,
	// "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g,
	// "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g,
	// "").replace(/元$/g, "元整");
	document.getElementById("capitalMoney").innerHTML = "<font style='font-weight:bold;'>"
			+ str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(
					/零(万|亿|元)/g, "$1").replace(/(亿)万|(拾)/g, "$1$2").replace(
					/^元零?|零分/g, "").replace(/元$/g, "元整") + "</font>";
}
function sumCost(){
	var remainsCarryBeCost = document.getElementById("remainsCarryBeCost").value;
	var remainsCarryRealCost = document.getElementById("remainsCarryRealCost").value;
	
	var rentCrystalBeCost = document.getElementById("rentCrystalBeCost").value;
	var rentCrystalRealCost = document.getElementById("rentCrystalRealCost").value;

	
	var rentCrystalCostChange = Number(rentCrystalBeCost)-Number(rentCrystalRealCost);
	
	var rentCrystalCarBeCost = document.getElementById("rentCrystalCarBeCost").value;
	var rentCrystalCarRealCost = document.getElementById("rentCrystalCarRealCost").value;
	var rentCrystalCarCostChange = Number(rentCrystalCarBeCost)-Number(rentCrystalCarRealCost);	
	
	var watchSpiritVillaBeCost = document.getElementById("watchSpiritVillaBeCost").value;
	var watchSpiritVillaRealCost = document.getElementById("watchSpiritVillaRealCost").value;
	var watchSpiritVillaCostChange = Number(watchSpiritVillaBeCost)-Number(watchSpiritVillaRealCost);	
	
	var watchSpiritCoffinBeCost = document.getElementById("watchSpiritCoffinBeCost").value;
	var watchSpiritCoffinRealCost = document.getElementById("watchSpiritCoffinRealCost").value;
	var watchSpiritCoffinCostChange = Number(watchSpiritCoffinBeCost)-Number(watchSpiritCoffinRealCost);
	
	var leaveRoomBox = document.getElementById("leaveRoomBox");
	var urnChooseBox = document.getElementById("urnChooseBox");
	var makeBeautyBox = document.getElementById("makeBeautyBox");
	var cremationBox = document.getElementById("cremationBox");
	var leaveRoomBeCost = document.getElementById("leaveRoomBeCost").value;
	var urnBeCost = document.getElementById("urnBeCost").value;
	var makeBeautyBeCost = document.getElementById("makeBeautyBeCost").value;
	var cremationBeCost = document.getElementById("cremationBeCost").value;
	var leaveRoomRealCost = document.getElementById("leaveRoomRealCost").value;
	var urnRealCost = document.getElementById("urnRealCost").value;
	var makeBeautyRealCost = document.getElementById("makeBeautyRealCost").value;
	var cremationRealCost = document.getElementById("cremationRealCost").value;
	if(!leaveRoomBox.checked==true){
		 leaveRoomBeCost = "";
		 leaveRoomRealCost = "";

	}
	if(!urnChooseBox.checked==true){
		urnBeCost="";
		urnRealCost="";
	}
	if(!makeBeautyBox.checked==true){
		makeBeautyBeCost="";
		makeBeautyRealCost="";
		
	}
	if(!cremationBox.checked==true){
		cremationBeCost="";
		cremationRealCost="";
	}
	var sumBeCost =Number(remainsCarryBeCost)+Number(rentCrystalBeCost)+Number(rentCrystalCarBeCost)+Number(watchSpiritVillaBeCost)+Number(watchSpiritCoffinBeCost)+Number(leaveRoomBeCost)+Number(urnBeCost)+Number(makeBeautyBeCost)+Number(cremationBeCost);
	var sumRealCost = Number(remainsCarryBeCost)-Number(remainsCarryRealCost)+rentCrystalCostChange+rentCrystalCarCostChange+watchSpiritVillaCostChange+watchSpiritCoffinCostChange+Number(leaveRoomRealCost)+Number(urnRealCost)+Number(makeBeautyRealCost)+Number(cremationRealCost);
	var tableName = document.getElementById("allFuneralGoods"); // 获取table中丧葬物品对象
	var len = tableName.rows.length;
	for (var i = 2; i < len; i++) {
		var goodsCheckBoxInput1 = tableName.rows[i].cells[4].getElementsByTagName("input");
		if (goodsCheckBoxInput1[0].checked) {
			var goodsBeCostCell = tableName.rows[i].cells[1];
			var goodsBeCostInput = goodsBeCostCell.getElementsByTagName("input");
			var goodsBeCost = goodsBeCostInput[0].value;
			var goodsRealCostInput = tableName.rows[i].cells[2].getElementsByTagName("input");
			var goodsRealCost = goodsRealCostInput[0].value;
			sumBeCost=sumBeCost+Number(goodsBeCost);
			sumRealCost=sumRealCost+Number(goodsRealCost);
		}
			var goodsCheckBoxInput2 = tableName.rows[i].cells[12].getElementsByTagName("input");
		if (goodsCheckBoxInput2[0] != null) {
			if (goodsCheckBoxInput2[0].checked) {
		// alert("文本框被选中！");				
				var goodsBeCostCell = tableName.rows[i].cells[9];
				var goodsBeCostInput = goodsBeCostCell.getElementsByTagName("input");
				var goodsBeCost = goodsBeCostInput[0].value;
				var goodsRealCostInput = tableName.rows[i].cells[10].getElementsByTagName("input");
				var goodsRealCost = goodsRealCostInput[0].value;
				sumBeCost=sumBeCost+Number(goodsBeCost);
				sumRealCost=sumRealCost+Number(goodsRealCost);
		

	}
}
	
		
	}
	document.getElementById("sumBeCost").value=sumBeCost;
	document.getElementById("sumRealCost").value=sumRealCost;
	
	
}
function changeAllBeCost(){
	
	document.getElementById("theWholeCost").value=Number(document.getElementById("allBeCost").value)-Number(document.getElementById("allRealCost").value);
	
}
function changeAllRealCost(){
	document.getElementById("theWholeCost").value=Number(document.getElementById("allBeCost").value)-Number(document.getElementById("allRealCost").value);

	
}
function decideMixedServiceCost() { // 确定火化服务之外的服务费用
	var deadId = form4.deadId.value;
	var remainsCarryRealCost = form4.remainsCarryRealCost.value;
	var rentCrystalRealCost = form4.rentCrystalRealCost.value;
	var rentCrystalCarRealCost = form4.rentCrystalCarRealCost.value;
	var watchSpiritVillaRealCost = form4.watchSpiritVillaRealCost.value;
	var watchSpiritCoffinRealCost = form4.watchSpiritCoffinRealCost.value;
	url = "deadId=" + deadId + "&";
	url = url + "remainsCarryRealCost=" + remainsCarryRealCost + "&";
	url = url + "rentCrystalRealCost=" + rentCrystalRealCost + "&";
	url = url + "rentCrystalCarRealCost=" + rentCrystalCarRealCost + "&";
	url = url + "watchSpiritVillaRealCost=" + watchSpiritVillaRealCost + "&";
	url = url + "watchSpiritCoffinRealCost=" + watchSpiritCoffinRealCost;
	http_request = createHttpRequest();
	http_request.onreadystatechange = decideMixedCostCallBack;
	http_request.open('POST', "DecideMixedCostAction!decideMixedCost", false); //
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	// return false;//阻止页面刷新
}

function decideMixedCostCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			alert(http_request.responseText);
		}
	}
}
function chooseSetService() {
	var setServiceCheckBox = document.form4.setServiceCheckBox;
	if (setServiceCheckBox.checked == false) {
		location.reload();
	}
	if (setServiceCheckBox.checked == true) {
		var setService = document.form4.setService;
		setService.disabled = "";
		document.form4.createSetOrder.disabled = "";
		document.form4.printSetServiceList.disabled = "";
	}
}
function showSetServiceDetail() {
	var setNameSelect = document.form4.setService;
	var setNameIndex = setNameSelect.selectedIndex;
	var setName = setNameSelect.options[setNameIndex].text;
	url = "setName=" + setName;
	http_request = createHttpRequest();
	http_request.onreadystatechange = showSetServiceDetailCallBack;
	http_request.open('POST', "RegisterServiceAction!showSetServiceDetail",
			false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);

	http_request = createHttpRequest();
	http_request.onreadystatechange = showSetGoodsDetailCallBack;
	http_request
			.open('POST', "RegisterServiceAction!showSetGoodsDetail", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
}
function showSetGoodsDetailCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			clearTable();
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var allFuneralGoodsTable = document
					.getElementById("allFuneralGoods");
			var rowsLength = allFuneralGoodsTable.rows.length;
			var setServiceDetailContent = document.form4.setServiceDetailContent;
			setServiceDetailContent.value = "";
			var setServiceAllCost = document.form4.setServiceAllCost;
			setServiceAllCost.value = "0";
			for (var i = 0; i < jsonLength; i++) {
				// var new_opt = new Option(json[i].setName, json[i].setName);
				// urnSelect.options.add(new_opt);
				var goodsName = json[i].setGoods;
				var goodsBeCost = json[i].setGoodsPrice;
				setServiceDetailContent.value = setServiceDetailContent.value
						+ goodsName + ":" + goodsBeCost + ";";
				setServiceAllCost.value = parseInt(setServiceAllCost.value)
						+ parseInt(goodsBeCost);
				for (var j = 2; j < rowsLength; j++) {
					var goodName1 = allFuneralGoodsTable.rows[j].cells[0].innerText;
					var goodsName2 = allFuneralGoodsTable.rows[j].cells[7].innerText;
					if (goodsName == goodName1) {
						var goodsCheckBoxInput1 = allFuneralGoodsTable.rows[j].cells[3]
								.getElementsByTagName("input");
						var goodsBeCostInput1 = allFuneralGoodsTable.rows[j].cells[1]
								.getElementsByTagName("input");
						var goodsRealCostInput1 = allFuneralGoodsTable.rows[j].cells[2]
								.getElementsByTagName("input");
						goodsCheckBoxInput1[0].checked = "true";
						goodsCheckBoxInput1[0].onchange();
				//		goodsCheckBoxInput1[0].disabled = "true";
						goodsBeCostInput1[0].value = goodsBeCost;
						goodsBeCostInput1[0].onkeyup();
						goodsBeCostInput1[0].disabled = "true";
						// alert(goodsBeCostInput1[0]);
				//		goodsRealCostInput1[0].disabled = "true";

					}
					if ((goodsName2 != null) && (goodsName == goodsName2)) {
						var goodsCheckBoxInput2 = allFuneralGoodsTable.rows[j].cells[10]
								.getElementsByTagName("input");
						var goodsBeCostInput2 = allFuneralGoodsTable.rows[j].cells[8]
								.getElementsByTagName("input");
						var goodsRealCostInput2 = allFuneralGoodsTable.rows[j].cells[9]
								.getElementsByTagName("input");
						goodsCheckBoxInput2[0].checked = "true";
						goodsCheckBoxInput2[0].onchange();
				//		goodsCheckBoxInput2[0].disabled = "true";
						goodsBeCostInput2[0].value = goodsBeCost;
						goodsBeCostInput2[0].onkeyup();
						goodsBeCostInput2[0].disabled = "true";
						// alert(goodsBeCostInput2.length);
				//		goodsRealCostInput2[0].disabled = "true";
					}
				}
			}
		}
	}
}

function showSetServiceDetailCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var cg = document.form4.cremationGrade;// 火化
			for (var i = 0; i < cg.options.length; i++) {
				if (cg.options[i].text == json.furnace) // 动态选择selected默认值
				{
					cg.options[i].selected = true;
					var cremationBeCost = document.form4.cremationBeCost;
					cremationBeCost.value = json.furnacePrice;
					var preSetFurnaceBeCost = document.form4.preSetFurnaceBeCost; // 保存前一个状态的套餐选择的火化炉应收价格

					var allBeCost = document.form4.allBeCost; // 火化服务费用应收
					var allBeCostHidden = document.form4.allBeCostHidden; // 前一个状态的火化服务应收

					var theWholeCost = document.form4.theWholeCost; // 总费用
					var lastWholeCost = document.form4.lastWholeCost; // 前一个状态的总费用应收

					var allBeCostValue = allBeCost.value;
					var theWholeCostValue = theWholeCost.value;
					allBeCost.value = parseInt(allBeCostValue)
							- parseInt(preSetFurnaceBeCost.value)
							+ parseInt(cremationBeCost.value);
					allBeCostHidden.value = allBeCost.value;
					theWholeCost.value = parseInt(theWholeCostValue)
							- parseInt(preSetFurnaceBeCost.value)
							+ parseInt(cremationBeCost.value);
					lastWholeCost.value = theWholeCost.value;

					preSetFurnaceBeCost.value = json.furnacePrice;
				}
			}
			cg.disabled = "true";
			document.form4.leaveRoomBeCost.disabled = "true";
			document.form4.leaveRoomRealCost.disabled = "true";
			document.form4.leaveRoomBox.disabled = "true";

			var fw = document.form4.leaveRoomGrade; // 告别厅
			for (var i = 0; i < fw.options.length; i++) {
				if (fw.options[i].text == json.farewellRoom) // 动态选择selected默认值
				{
					fw.options[i].selected = true;
					var leaveRoomBeCost = document.form4.leaveRoomBeCost;
					leaveRoomBeCost.value = json.farewellRoomPrice;
					var preSetFarewellBeCost = document.form4.preSetFarewellBeCost; // 保存前一个状态的套餐选择的火化炉应收价格

					var allBeCost = document.form4.allBeCost; // 火化服务费用应收
					var allBeCostHidden = document.form4.allBeCostHidden; // 前一个状态的火化服务应收

					var theWholeCost = document.form4.theWholeCost; // 总费用
					var lastWholeCost = document.form4.lastWholeCost; // 前一个状态的总费用应收

					var allBeCostValue = allBeCost.value;
					var theWholeCostValue = theWholeCost.value;
					allBeCost.value = parseInt(allBeCostValue)
							- parseInt(preSetFarewellBeCost.value)
							+ parseInt(leaveRoomBeCost.value);
					allBeCostHidden.value = allBeCost.value;
					theWholeCost.value = parseInt(theWholeCostValue)
							- parseInt(preSetFarewellBeCost.value)
							+ parseInt(leaveRoomBeCost.value);
					lastWholeCost.value = theWholeCost.value;

					preSetFarewellBeCost.value = json.farewellRoomPrice;
				}
			}
			fw.disabled = "true";
			document.form4.cremationBeCost.disabled = "true";
			document.form4.cremationRealCost.disabled = "true";
			document.form4.cremationBox.disabled = "true";
		}
	}
}
function clearTable() {
	var allFuneralGoodsTable = document.getElementById("allFuneralGoods");
	var rowsLength = allFuneralGoodsTable.rows.length;
	for (var i = 2; i < rowsLength; i++) {
		var goodsCheckBoxInput1 = allFuneralGoodsTable.rows[i].cells[3]
				.getElementsByTagName("input");
		var goodsCheckBoxInput2 = allFuneralGoodsTable.rows[i].cells[10]
				.getElementsByTagName("input");
		if (goodsCheckBoxInput1[0].checked == true) {
			goodsCheckBoxInput1[0].checked = false;
			goodsCheckBoxInput1[0].onchange();
		}
		if ((goodsCheckBoxInput2 != null)
				&& (goodsCheckBoxInput2[0].checked == true)) {
			goodsCheckBoxInput2[0].checked = false;
			goodsCheckBoxInput2[0].onchange();
		}
		goodsCheckBoxInput1[0].disabled = "";
		goodsCheckBoxInput2[0].disabled = "";
	}
}
function CreatePrintPage() {
	var deadId = document.form4.deadId.value;
	url = "deadId=" + deadId;
	http_request = createHttpRequest();
	http_request.onreadystatechange = CreatePrintPageCallBack;
	http_request.open('POST', "GetTaxInfoAction!getTaxInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	return false;
}

function CreatePrintPageCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var deadId = document.form4.deadId.value;
			var deadName = json.deadName;
			var deadSex = json.deadSex;
			var deadAge = json.deadAge;
			var address = json.address;
			LODOP.PRINT_INIT("套打EMS的模版");
			LODOP.SET_PRINT_PAGESIZE(1, '210mm', '120mm', '1');
			// LODOP.ADD_PRINT_SETUP_BKIMG("<img border='1'
			// src='http://192.168.1.102:8080/FuneralManageSystem/Images/tax.jpg'/>");
			LODOP
					.ADD_PRINT_SETUP_BKIMG("<img border='1' src='http://localhost:8080/FuneralManageSystem/Images/tax.jpg'/>");
			LODOP.ADD_PRINT_TEXT('80mm', '125mm', 150, 20, "姓名：" + deadName);
			LODOP.ADD_PRINT_TEXT('80mm', '150mm', 150, 20, "性别：" + deadSex);
			LODOP.ADD_PRINT_TEXT('80mm', '170mm', 150, 20, "年龄：" + deadAge);
			LODOP.ADD_PRINT_TEXT('85mm', '125mm', 200, 20, "身份证号码：" + deadId);
			LODOP.ADD_PRINT_TEXT('90mm', '125mm', 200, 20, "地址：" + address);

		}
	}
}
function CreateCremationPrint() {

	var deadId = document.form4.deadId.value;
	url = "deadId=" + deadId;
	http_request = createHttpRequest();
	http_request.onreadystatechange = CreateCremationPrintCallBack;
	http_request.open('POST', "GetCremationInfoAction!getCremationInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);

	return false;
}
function CreateCremationPrintCallBack() {
	var deadIdValue = document.form4.deadId.value;
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var deadName1 = json.deadName;
			var deadSex1 = json.deadSex;
			var address1 = json.address;
			var address2 = address1.substring(0, 15);
			var address3 = address1.substring(15, 30);

			var deadTime1 = json.deadTime;
			var inTime1 = json.inTime;
			var proofUnit1 = json.proofUnit;
			var deadReason1 = json.deadReason;
			var invoiceNo = json.invoiceNo;
			LODOP.PRINT_INITA('0mm', '26mm', '360mm', '180mm', "套打EMS的模版"); // '0mm','26mm','240mm','100mm',
			LODOP.SET_PRINT_PAGESIZE(2, '150mm', '350mm', '123');
			// LODOP.ADD_PRINT_SETUP_BKIMG("<img border='1'
			// src='http://192.168.1.102:8080/FuneralManageSystem/Images/tax.jpg'/>");
			LODOP
					.ADD_PRINT_SETUP_BKIMG("<img border='1' src='http://localhost:8080/FuneralManageSystem/Images/tax.jpg'/>");
			var deadTimeYear = deadTime1.substr(0, 4);
			var deadTimeMonth = deadTime1.substr(5, 2);
			var deadTimeDate = deadTime1.substr(8, 2);
			var deadId = deadIdValue.substr(6, 8);

			LODOP.ADD_PRINT_HTM('40mm', '125mm', 150, 20,
					"<font style='font-size:15px;'>" + invoiceNo + "</font>");
			LODOP.ADD_PRINT_HTM('40mm', '220mm', 150, 20,
					"<font style='font-size:15px;'>" + invoiceNo + "</font>");

			// LODOP.ADD_PRINT_HTM('65mm','35mm',150,20,"<font
			// style='font-size:20px'>"+deadTimeYear+"</font>");
			// LODOP.ADD_PRINT_HTM('65mm','50mm',150,20,"<font
			// style='font-size:20px'>"+deadTimeMonth+"</font>");
			// LODOP.ADD_PRINT_HTM('65mm','65mm',150,20,"<font
			// style='font-size:20px'>"+deadTimeDate+"</font>");
			// LODOP.ADD_PRINT_HTM('75mm','35mm',150,20,"<font
			// style='font-size:20px'>"+proofUnit1+"</font>");
			// LODOP.ADD_PRINT_HTM('95mm','35mm',150,20,"<font
			// style='font-size:20px'>"+deadReason1+"</font>");
			//			
			LODOP.ADD_PRINT_HTM('55mm', '115mm', 150, 20,
					"<font style='font-size:19px;'>" + deadName1 + "</font>");
			LODOP.ADD_PRINT_HTM('65mm', '115mm', 150, 20,
					"<font style='font-size:19px;'>" + deadSex1 + "</font>");
			LODOP.ADD_PRINT_HTM('73mm', '105mm', 200, 20,
					"<font style='font-size:13px;'>" + address2 + "</font>");
			LODOP.ADD_PRINT_HTM('78mm', '105mm', 200, 20,
					"<font style='font-size:13px;'>" + address3 + "</font>");
			var bornDate = deadId.substr(0, 4) + "年" + deadId.substr(4, 2)
					+ "月" + deadId.substr(6, 2) + "日";
			LODOP.ADD_PRINT_HTM('85mm', '105mm', 200, 20,
					"<font style='font-size:20px;'>" + bornDate + "</font>");

			var deadTime = deadTime1.substr(0, deadTime1.length - 2);
			var deadTimeYear = deadTime.substr(0, 4);
			var deadTimeMonth = deadTime.substr(5, 2);
			var deadTimeDay = deadTime.substr(8, 2);
			var deadTimeStr = deadTimeYear + "年" + deadTimeMonth + "月"
					+ deadTimeDay + "日";
			LODOP.ADD_PRINT_HTM('95mm', '105mm', 200, 20,
					"<font style='font-size:20px;'>" + deadTimeStr + "</font>");

			var inTime = inTime1.substr(0, inTime1.length - 2);
			var inTimeYear = inTime.substr(0, 4);
			var inTimeMonth = inTime.substr(5, 2);
			var inTimeDay = inTime.substr(8, 2);
			var inTimeStr = inTimeYear + "年" + inTimeMonth + "月" + inTimeDay
					+ "日";
			LODOP.ADD_PRINT_HTM('105mm', '105mm', 200, 20,
					"<font style='font-size:20px;'>" + inTimeStr + "</font>");

			LODOP.ADD_PRINT_HTM('55mm', '205mm', 150, 20,
					"<font style='font-size:19px;'>" + deadName1 + "</font>");
			LODOP.ADD_PRINT_HTM('65mm', '205mm', 150, 20,
					"<font style='font-size:19px;'>" + deadSex1 + "</font>");
			LODOP.ADD_PRINT_HTM('73mm', '195mm', 200, 20,
					"<font style='font-size:13px;'>" + address2 + "</font>");
			LODOP.ADD_PRINT_HTM('78mm', '195mm', 200, 20,
					"<font style='font-size:13px;'>" + address3 + "</font>");
			LODOP.ADD_PRINT_HTM('85mm', '195mm', 200, 20,
					"<font style='font-size:20px;'>" + bornDate + "</font>");
			LODOP.ADD_PRINT_HTM('95mm', '195mm', 200, 20,
					"<font style='font-size:20px;'>" + deadTimeStr + "</font>");
			LODOP.ADD_PRINT_HTM('105mm', '195mm', 200, 20,
					"<font style='font-size:20px;'>" + inTimeStr + "</font>");
		}
	}
}
// 惠民补助打印
function CreateSubsidyPrintPage() {

	var deadId = document.form4.deadId.value;
	url = "deadId=" + deadId;
	http_request = createHttpRequest();
	http_request.onreadystatechange = CreateSubsidyPrintCallBack;
	http_request.open('POST', "GetSubsidyInfoAction!getSubsidyInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);

	return false;
}

function CreateSubsidyPrintCallBack() {
	var deadId = document.form4.deadId.value;

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var deadName = json.deadName;
			var dealerName = json.dealerName;
			var dealerId = json.dealerId;
			var invoiceNo = json.invoiceNo;
			var subsidyMoney = json.subsidyMoney;
			var subsidyMoney2 = subsidyMoney;

			var date = new Date();
			year = date.getFullYear();
			month = date.getMonth() + 1;
			day = date.getDate();

			if (!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test(subsidyMoney)) {
				return false;
			}
			var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
			subsidyMoney += "00";
			var point = subsidyMoney.indexOf('.');
			if (point >= 0) {
				subsidyMoney = subsidyMoney.substring(0, point)
						+ subsidyMoney.substr(point + 1, 2);
			}
			unit = unit.substr(unit.length - subsidyMoney.length);
			for (var i = 0; i < subsidyMoney.length; i++) {
				str += '零壹贰叁肆伍陆柒捌玖'.charAt(subsidyMoney.charAt(i))
						+ unit.charAt(i);
			}
			// document.getElementById(idCHN).value = str.replace(/零(仟|佰|拾|角)/g,
			// "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g,
			// "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g,
			// "").replace(/元$/g, "元整");
			var subsidyMoney1 = str.replace(/零(仟|佰|拾|角)/g, "零").replace(
					/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(
					/(亿)万|(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g,
					"元整");
			LODOP.PRINT_INIT("套打EMS的模版");
			LODOP.SET_PRINT_PAGESIZE(1, '180mm', '270mm', '1');
			// LODOP.ADD_PRINT_SETUP_BKIMG("<img border='1'
			// src='http://192.168.1.102:8080/FuneralManageSystem/Images/tax.jpg'/>");
			// LODOP.ADD_PRINT_SETUP_BKIMG("<img border='1'
			// src='http://localhost:8080/FuneralManageSystem/Images/tax.jpg'/>");
			LODOP.ADD_PRINT_HTM('25mm', '55mm', 150, 20,
					"<font style='font-size:15px;'>" + invoiceNo + "</font>");
			LODOP.ADD_PRINT_HTM('35mm', '55mm', 150, 20,
					"<font style='font-size:20px;'>" + deadName + "</font>");

			LODOP.ADD_PRINT_HTM('25mm', '80mm', 150, 20,
					"<font style='font-size:15px;'>" + year + "</font>");
			LODOP.ADD_PRINT_HTM('25mm', '95mm', 150, 20,
					"<font style='font-size:15px;'>" + month + "</font>");
			LODOP.ADD_PRINT_HTM('25mm', '110mm', 50, 20,
					"<font style='font-size:15px;'>" + day + "</font>");

			LODOP.ADD_PRINT_HTM('35mm', '105mm', 150, 20,
					"<font style='font-size:20px;'>" + deadId + "</font>");

			LODOP.ADD_PRINT_HTM('51mm', '55mm', 150, 20,
					"<font style='font-size:20px;'>" + dealerName + "</font>");

			LODOP.ADD_PRINT_HTM('51mm', '105mm', 150, 20,
					"<font style='font-size:20px;'>" + dealerId + "</font>");
			LODOP.ADD_PRINT_HTM('61mm', '55mm', 150, 20,
					"<font style='font-size:20px;'>" + subsidyMoney1
							+ "</font>");
			LODOP.ADD_PRINT_HTM('61mm', '145mm', 200, 20,
					"<font style='font-size:20px;'>" + subsidyMoney2
							+ "</font>");

		}
	}
}
function remainsCarryChangeWholeCost() { // 遗体接运实收联动总费用变化
	var lastRemainsCarryRealCost = form4.lastRemainsCarryRealCost.value;
	var currentRemainsCarryRealCost = form4.remainsCarryRealCost.value;
	if (checkNumber(currentRemainsCarryRealCost)) {
		form4.theWholeCost.value = parseInt(form4.theWholeCost.value)
				- parseInt(lastRemainsCarryRealCost)
				+ parseInt(currentRemainsCarryRealCost);
		form4.allRealCost.value = parseInt(form4.allRealCost.value)
				- (parseInt(currentRemainsCarryRealCost) - parseInt(lastRemainsCarryRealCost));
		form4.lastRemainsCarryRealCost.value = currentRemainsCarryRealCost;
	} else {
		form4.remainsCarryRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function rentCrystalChangeWholeCost() { // 租用棺材联动总费用变化
	var lastRentCrystalRealCost = form4.lastRentCrystalRealCost.value;
	var currentRentCrystalRealCost = form4.rentCrystalRealCost.value;
	if (checkNumber(currentRentCrystalRealCost)) {
		form4.theWholeCost.value = parseInt(form4.theWholeCost.value)
				- parseInt(lastRentCrystalRealCost)
				+ parseInt(currentRentCrystalRealCost);
		form4.allRealCost.value = parseInt(form4.allRealCost.value)
				- (parseInt(currentRentCrystalRealCost) - parseInt(lastRentCrystalRealCost));
		form4.lastRentCrystalRealCost.value = currentRentCrystalRealCost;
	} else {
		form4.rentCrystalRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function rentCrystalCarChangeWholeCost() { // 运送棺材费用联动总费用变化
	var lastRentCrystalCarRealCost = form4.lastRentCrystalCarRealCost.value;
	var currentRentCrystalCarRealCost = form4.rentCrystalCarRealCost.value;
	if (checkNumber(currentRentCrystalCarRealCost)) {
		form4.theWholeCost.value = parseInt(form4.theWholeCost.value)
				- parseInt(lastRentCrystalCarRealCost)
				+ parseInt(currentRentCrystalCarRealCost);
		form4.allRealCost.value = parseInt(form4.allRealCost.value)
				- (parseInt(currentRentCrystalCarRealCost) - parseInt(lastRentCrystalCarRealCost));
		form4.lastRentCrystalCarRealCost.value = currentRentCrystalCarRealCost;
	} else {
		form4.rentCrystalCarRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function watchSpiritVillaChangeWholeCost() { // 守灵别墅费用变化联动总费用变化
	var lastWatchSpiritVillaRealCost = form4.lastWatchSpiritVillaRealCost.value;
	var currentWatchSpiritVillaRealCost = form4.watchSpiritVillaRealCost.value;
	if (checkNumber(currentWatchSpiritVillaRealCost)) {
		form4.theWholeCost.value = parseInt(form4.theWholeCost.value)
				- parseInt(lastWatchSpiritVillaRealCost)
				+ parseInt(currentWatchSpiritVillaRealCost);
		form4.allRealCost.value = parseInt(form4.allRealCost.value)
				- (parseInt(currentWatchSpiritVillaRealCost) - parseInt(lastWatchSpiritVillaRealCost));
		form4.lastWatchSpiritVillaRealCost.value = currentWatchSpiritVillaRealCost;
	} else {
		form4.watchSpiritVillaRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function watchSpiritCoffinChangeWholeCost() { // 守灵别墅用棺费用变化联动总费用变化
	var lastWatchSpiritCoffinRealCost = form4.lastWatchSpiritCoffinRealCost.value;
	var currentWatchSpiritCoffinRealCost = form4.watchSpiritCoffinRealCost.value;
	if (checkNumber(currentWatchSpiritCoffinRealCost)) {
		form4.theWholeCost.value = parseInt(form4.theWholeCost.value)
				- parseInt(lastWatchSpiritCoffinRealCost)
				+ parseInt(currentWatchSpiritCoffinRealCost);
		form4.allRealCost.value = parseInt(form4.allRealCost.value)
				- (parseInt(currentWatchSpiritCoffinRealCost) - parseInt(lastWatchSpiritCoffinRealCost));
		form4.lastWatchSpiritCoffinRealCost.value = currentWatchSpiritCoffinRealCost;
	} else {
		form4.watchSpiritCoffinRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}