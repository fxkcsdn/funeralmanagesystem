
function showSetServiceDetail() {
	var setNameSelect = document.form3.setService;
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
			var setServiceDetailContent = document.form3.setServiceDetailContent;
			setServiceDetailContent.value = "";
			var setServiceAllCost = document.form3.setServiceAllCost;
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
			var cg = document.form3.cremationGrade;// 火化
			for (var i = 0; i < cg.options.length; i++) {
				if (cg.options[i].value == json.furnace) // 动态选择selected默认值
				{
					cg.options[i].selected = true;
					var cremationBeCost = document.form3.cremationBeCost;
					cremationBeCost.value = json.furnacePrice;
					var preSetFurnaceBeCost = document.form3.preSetFurnaceBeCost; // 保存前一个状态的套餐选择的火化炉应收价格

					var allBeCost = document.form3.allBeCost; // 火化服务费用应收
					var allBeCostHidden = document.form3.allBeCostHidden; // 前一个状态的火化服务应收

					var theWholeCost = document.form3.theWholeCost; // 总费用
					var lastWholeCost = document.form3.lastWholeCost; // 前一个状态的总费用应收

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
			document.form3.leaveRoomBeCost.disabled = "true";
			document.form3.leaveRoomRealCost.disabled = "true";
			document.form3.leaveRoomBox.disabled = "true";

			var fw = document.form3.leaveRoomGrade; // 告别厅
			for (var i = 0; i < fw.options.length; i++) {
				if (fw.options[i].value == json.farewellRoom) // 动态选择selected默认值
				{
					fw.options[i].selected = true;
					var leaveRoomBeCost = document.form3.leaveRoomBeCost;
					leaveRoomBeCost.value = json.farewellRoomPrice;
					var preSetFarewellBeCost = document.form3.preSetFarewellBeCost; // 保存前一个状态的套餐选择的火化炉应收价格

					var allBeCost = document.form3.allBeCost; // 火化服务费用应收
					var allBeCostHidden = document.form3.allBeCostHidden; // 前一个状态的火化服务应收

					var theWholeCost = document.form3.theWholeCost; // 总费用
					var lastWholeCost = document.form3.lastWholeCost; // 前一个状态的总费用应收

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
			document.form3.cremationBeCost.disabled = "true";
			document.form3.cremationRealCost.disabled = "true";
			document.form3.cremationBox.disabled = "true";
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

function getDetails() {
	var deadReasonSelect = document.form1.deadReason;
	var deadReasonIndex = deadReasonSelect.selectedIndex;
	var deadReason = deadReasonSelect.options[deadReasonIndex].text;
	if (deadReason == "病故") {
		document.form1.pathogeny.style.display = "";
	}
	if (deadReason != "病故") {
		document.form1.pathogeny.style.display = "none";
	}
}

function gotoRegisterServicePage() {
	var deadId = document.form2.deadId.value;
	
	var serviceRegisterPage = document.getElementById("serviceRegisterPage");
	serviceRegisterPage.click();
	

	document.form3.deadId.value = deadId;
	document.form3.deadId.onchange();
}

function goToPrintDeadInfo() {
	var deadId = document.form1.deadId.value;
	var deadName = document.form1.deadName.value;
	var memberMobile = document.form1.memberMobile.value;
	var inTime = document.form1.inTime.value;
	var printDeadInfoPageLi = document.getElementById("printDeadInfoPage");
	printDeadInfoPageLi.click();
	document.form2.deadId.value = deadId;
	document.form2.deadName.value = deadName;
	document.form2.memberMobile.value = memberMobile;
	document.form2.inTime.value = inTime;
	document.form2.deadId.onchange();
	url = "deadId=" + deadId;
	http_request = createHttpRequest();
	http_request.onreadystatechange = goToPrintDeadInfoCallBack;
	http_request.open('POST', "RegisterServiceAction!showDeadInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
}

function goToPrintDeadInfoCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			document.form2.deadNumber.value = json.deadNumber;
		}
	}
}

function showUrnCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var urnSelect = document.getElementById("urnChoose");
			for (var i = 0; i < jsonLength; i++) {
				var new_opt = new Option(json[i].urnName, json[i].urnBeCost);
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
				var addGoodsButton1 = row.insertCell();
				var hideInput1 = row.insertCell();

				var gap = row.insertCell();

				var hideInput2 = row.insertCell();
				var funeralGoodsCell2 = row.insertCell();
				var goodsBeCost2 = row.insertCell();
				var goodsRealCost2 = row.insertCell();
				var addGoodsButton2 = row.insertCell();

				funeralGoodsCell1.innerText = json[i].goodsName;
				// goodsBeCost1.innerText=json[i].goodsBeCost;
				goodsBeCost1.innerHTML = "<input type='text' style='width: 80px;' onkeyup='changeGoodBeCost1(this)' value='"
						+ json[i].goodsBeCost + "' disabled='true'>";
				goodsRealCost1.innerHTML = "<input type='text' style='width: 80px;' value='0' onchange='changeGoodsCost1(this)' disabled='true'>";
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
		var goodsBeCostHidden = tr.cells[4];
		var goodsBeCostHiddenInput = goodsBeCostHidden
				.getElementsByTagName("input");
		var goodsBeCostHidden1 = goodsBeCostHiddenInput[1].value;

		var allServiceBeCost = document.form3.allBeCost;
		allServiceBeCost.value = parseInt(allServiceBeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		var theWholeCost = document.form3.theWholeCost;
		theWholeCost.value = parseInt(theWholeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		document.form3.lastWholeCost.value = theWholeCost.value;
		// alert(document.form3.lastWholeCost.value);
		goodsBeCostHiddenInput[1].value = goodsBeCost
				.getElementsByTagName("input")[0].value;
		// alert(goodsBeCostHiddenInput[1].value);
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsBeCostInput[0].focus();
	}
}

function changeGoodBeCost2(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]

	var goodsBeCost = tr.cells[8];
	var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1 = goodsBeCostInput[0].value;
	if (checkNumber(goodsBeCost1)) {
		var goodsBeCostHidden = tr.cells[6];
		var goodsBeCostHiddenInput = goodsBeCostHidden
				.getElementsByTagName("input");
		var goodsBeCostHidden1 = goodsBeCostHiddenInput[1].value;

		var allServiceBeCost = document.form3.allBeCost;
		allServiceBeCost.value = parseInt(allServiceBeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsBeCostHidden1);
		var theWholeCost = document.form3.theWholeCost;
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
		var preAllBeCost = document.form3.allBeCost.value;
		var preAllRealCost = document.form3.allRealCost.value;
		var curAllBeCost = parseInt(goodsBeCost1) + parseInt(preAllBeCost);
		var curAllRealCost = parseInt(goodsRealCost1)
				+ parseInt(preAllRealCost);
		document.form3.allBeCost.value = curAllBeCost;
		document.form3.allRealCost.value = curAllRealCost;
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				+ parseInt(goodsBeCost1) - parseInt(goodsRealCost1);
	}
	if (obj.checked == false) {
		goodsBeCostInput[0].disabled = "true";
		goodsRealCostInput[0].disabled = "true";
		var preAllBeCost = document.form3.allBeCost.value;
		var preAllRealCost = document.form3.allRealCost.value;
		var curAllBeCost = preAllBeCost - goodsBeCost1;
		var curAllRealCost = preAllRealCost - goodsRealCost1;
		document.form3.allBeCost.value = curAllBeCost;
		document.form3.allRealCost.value = curAllRealCost;
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				- parseInt(goodsBeCost1) + parseInt(goodsRealCost1);
	}
}

function changeGoodsCost1(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsBeCost1 = tr.cells[1].innerText;
	var goodsRealCost = tr.cells[2];
	var hideTd1 = tr.cells[4];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost1 = goodsRealCostInput[0].value;
	if (checkNumber(goodsRealCost1)) {
		var hideInput1 = hideTd1.getElementsByTagName("input");
		var hideValue1 = hideInput1[0].value;
		var changeGoodsRealCost1 = goodsRealCost1 - hideValue1;
		var allRealCost = document.form3.allRealCost.value;
		document.form3.allRealCost.value = parseInt(allRealCost)
				- parseInt(hideValue1) + parseInt(goodsRealCost1);
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				+ parseInt(hideValue1) - parseInt(goodsRealCost1);
		hideInput1[0].value = goodsRealCost1;
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}

function changeGoodsCost2(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsRealCost = tr.cells[9];
	var hideTd2 = tr.cells[6];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost2 = goodsRealCostInput[0].value;
	if (checkNumber(goodsRealCost2)) {
		var hideInput2 = hideTd2.getElementsByTagName("input");
		var hideValue2 = hideInput2[0].value;
		var changeGoodsRealCost2 = goodsRealCost2 - hideValue2;
		var allRealCost = document.form3.allRealCost.value;
		document.form3.allRealCost.value = parseInt(allRealCost)
				- parseInt(hideValue2) + parseInt(goodsRealCost2);
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				+ parseInt(hideValue2) - parseInt(goodsRealCost2);
		hideInput2[0].value = goodsRealCost2;
	} else {
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}

function chooseFuneralGoods2(obj) {
	var tr = obj.parentNode.parentNode;// 得到按钮[obj]的父元素[td]的父元素[tr]
	// var goodsBeCost2=tr.cells[8].innerText;
	var goodsBeCost = tr.cells[8];
	var goodsBeCostInput = goodsBeCost.getElementsByTagName("input");
	var goodsBeCost2 = goodsBeCostInput[0].value;

	var goodsRealCost = tr.cells[9];
	var goodsRealCostInput = goodsRealCost.getElementsByTagName("input");
	var goodsRealCost2 = goodsRealCostInput[0].value;
	if (obj.checked == true) {
		goodsBeCostInput[0].disabled = "";
		goodsRealCostInput[0].disabled = "";
		var preAllBeCost = document.form3.allBeCost.value;
		var preAllRealCost = document.form3.allRealCost.value;
		var curAllBeCost = parseInt(goodsBeCost2) + parseInt(preAllBeCost);
		var curAllRealCost = parseInt(goodsRealCost2)
				+ parseInt(preAllRealCost);
		document.form3.allBeCost.value = curAllBeCost;
		document.form3.allRealCost.value = curAllRealCost;
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				+ parseInt(goodsBeCost2) - parseInt(goodsRealCost2);
	}
	if (obj.checked == false) {
		goodsBeCostInput[0].disabled = "true";
		goodsRealCostInput[0].disabled = "true";
		var preAllBeCost = document.form3.allBeCost.value;
		var preAllRealCost = document.form3.allRealCost.value;
		var curAllBeCost = preAllBeCost - goodsBeCost2;
		var curAllRealCost = preAllRealCost - goodsRealCost2;
		document.form3.allBeCost.value = curAllBeCost;
		document.form3.allRealCost.value = curAllRealCost;
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				- parseInt(goodsBeCost2) + parseInt(goodsRealCost2);
	}
}

function getLeaveRoomCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var urnSelect = document.getElementById("leaveRoomGrade");
			for (var i = 0; i < jsonLength; i++) {
				var new_opt = new Option(json[i].itemName, json[i].itemName);
				urnSelect.options.add(new_opt);
			}
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
		document.form3.theWholeCost.value = document.form3.theWholeCost.value
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				+ parseInt(curCremationBeCost) - parseInt(curCremationRealCost);
	}
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
			var urnBeCost = document.form3.urnBeCost;
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
			document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
					+ parseInt(changeUrnBeCost) + parseInt(changeUrnRealCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preUrnBeCost").value = curUrnBeCost;
			document.getElementById("preUrnRealCost").value = curUrnRealCost;
		}
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		

		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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

		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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

		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
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
		document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
				- parseInt(changeCremationRealCost);
	} else {
		cremationRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}

function getMakeBeautyBeCost() {
	var makeBeauty = document.getElementById("makeBeautyGrade");
	var makeBeautyIndex = makeBeauty.selectedIndex;
	var makeBeautyName = makeBeauty.options[makeBeautyIndex].text;
	url = "itemName=" + makeBeautyName;
	http_request = createHttpRequest();
	http_request.onreadystatechange = getMakeBeautyBeCostCallBack;
	http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
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
			document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
					+ parseInt(changeBeautyBeCost)
					+ parseInt(changeBeautyRealCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preMakeBeautyBeCost").value = curBeautyBeCost;
			document.getElementById("preMakeBeautyRealCost").value = curBeautyRealCost;
		}
	}
}

function getLeaveRoomBeCost() {
	var leaveRoom = document.getElementById("leaveRoomGrade");
	var leaveRoomIndex = leaveRoom.selectedIndex;
	var leaveRoomName = leaveRoom.options[leaveRoomIndex].text;
	url = "itemName=" + leaveRoomName;
	http_request = createHttpRequest();
	http_request.onreadystatechange = getLeaveRoomBeCostCallBack;
	http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
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
			document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
					+ parseInt(changeLeaveRoomRealCost)
					+ parseInt(changeLeaveRoomBeCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preLeaveRoomBeCost").value = curLeaveRoomBeCost;
			document.getElementById("preLeaveRoomRealCost").value = curLeaveRoomRealCost;
		}
	}
}

function getCremationBeCost() {
	var cremation = document.getElementById("cremationGrade");
	var cremationIndex = cremation.selectedIndex;
	var cremationName = cremation.options[cremationIndex].text;
	
	url = "itemName=" + cremationName;
	http_request = createHttpRequest();
	http_request.onreadystatechange = getCremationBeCostCallBack;
	http_request.open('POST', "GetServiceBeCostAction!getServiceBeCost", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
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
			document.form3.theWholeCost.value = parseInt(document.form3.theWholeCost.value)
					+ parseInt(changeCremationRealCost)
					+ parseInt(changeCremationBeCost);
			document.getElementById("allBeCost").value = allBeCost;
			document.getElementById("allRealCost").value = allRealCost;
			document.getElementById("preCremationBeCost").value = curCremationBeCost;
			document.getElementById("preCremationRealCost").value = curCremationRealCost;
		}
	}
}

function decideYourChoose() {

	var tableName = document.getElementById("allFuneralGoods"); // 获取table中丧葬物品对象
	var len = tableName.rows.length;
	var numbers = 0;
	var arrFuneralGoods = new Array();
	var jsonUrn = "";
	var jsonMakeBeauty = "";
	var jsonLeaveRoom = "";
	var jsonCremation = "";
	for (var i = 2; i < len; i++) {
		var goodsCheckBoxInput1 = tableName.rows[i].cells[3]
				.getElementsByTagName("input");
		if (goodsCheckBoxInput1[0].checked) {
			var goodsName = tableName.rows[i].cells[0].innerText;

			// var goodsBeCost=tr.cells[1];
			// var goodsBeCostInput=goodsBeCost.getElementsByTagName("input");
			// var goodsBeCost1=goodsBeCostInput[0].value;
			// var goodsBeCost=tableName.rows[i].cells[1].innerText;
			var goodsBeCostCell = tableName.rows[i].cells[1];
			var goodsBeCostInput = goodsBeCostCell
					.getElementsByTagName("input");
			var goodsBeCost = goodsBeCostInput[0].value;

			var goodsRealCostInput = tableName.rows[i].cells[2]
					.getElementsByTagName("input");
			var goodsChangeCost = goodsRealCostInput[0].value;
			var goodsRealCost = goodsBeCost - goodsChangeCost + "";
			arrFuneralGoods[numbers] = "{goodsName:'" + goodsName
					+ "',goodsBeCost:'" + goodsBeCost + "',goodsRealCost:'"
					+ goodsRealCost + "'}";
			// arrFuneralGoods[numbers]="{'goodsName':'"+goodsName+"','goodsBeCost':'"+goodsBeCost+"','goodsRealCost':'"+goodsRealCost+"'}";
			// arrFuneralGoods[numbers]='{"goodsName":'+goodsName+',"goodsBeCost":'+goodsBeCost+',"goodsRealCost":'+goodsRealCost+'}';
			// arrFuneralGoods[numbers]={"goodsName":goodsName,"goodsBeCost":goodsBeCost,"goodsRealCost":goodsRealCost};
			numbers++;
		}
		var goodsCheckBoxInput2 = tableName.rows[i].cells[10]
				.getElementsByTagName("input");
		if (goodsCheckBoxInput2[0] != null) {
			if (goodsCheckBoxInput2[0].checked) {
				// alert("文本框被选中！");
				var goodsName = tableName.rows[i].cells[7].innerText;
				// var goodsBeCost=tableName.rows[i].cells[8].innerText;
				var goodsBeCostCell = tableName.rows[i].cells[8];
				var goodsBeCostInput = goodsBeCostCell
						.getElementsByTagName("input");
				var goodsBeCost = goodsBeCostInput[0].value;

				var goodsRealCostInput = tableName.rows[i].cells[9]
						.getElementsByTagName("input");
				var goodsChangeCost = goodsRealCostInput[0].value;
				var goodsRealCost = goodsBeCost - goodsChangeCost + "";
				arrFuneralGoods[numbers] = "{goodsName:'" + goodsName
						+ "',goodsBeCost:'" + goodsBeCost + "',goodsRealCost:'"
						+ goodsRealCost + "'}";
				// arrFuneralGoods[numbers]="{'goodsName':'"+goodsName+"','goodsBeCost':'"+goodsBeCost+"','goodsRealCost':'"+goodsRealCost+"'}";
				// arrFuneralGoods[numbers]='{"goodsName":'+goodsName+',"goodsBeCost":'+goodsBeCost+',"goodsRealCost":'+goodsRealCost+'}';
				// arrFuneralGoods[numbers]={"goodsName":goodsName,"goodsBeCost":goodsBeCost,"goodsRealCost":goodsRealCost};
				numbers++;
			}
		}
	}

	if (document.form3.urnChooseBox.checked == true) {
		var urnChoose = document.form3.urnChoose;
		var urnChooseIndex = urnChoose.selectedIndex;

		var urnName = urnChoose.options[urnChooseIndex].text;
		if (urnName != "-请选择-") {
			var urnBeCost = document.form3.urnBeCost.value;
			var urnChangeCost = document.form3.urnRealCost.value;
			var urnRealCost = urnBeCost - urnChangeCost + "";
			// jsonUrn='{"urnName":'+urnName+',"urnBeCost":'+urnBeCost+',"urnRealCost":'+urnRealCost+'}';
			jsonUrn = "{'urnName':'" + urnName + "','urnBeCost':'" + urnBeCost
					+ "','urnRealCost':'" + urnRealCost + "'}";
			// "{'json':'jsonvalue','bool':true,'int':1,'double':'20.5'}"
		} else {
			jsonUrn = "";
			alert("请确认已选择骨灰盒");
			document.form3.urnChoose.focus();
			return false;
		}
	}

	if (document.form3.makeBeautyBox.checked == true) {
		var makeBeautyGrade = document.form3.makeBeautyGrade;
		var makeBeautyGradeIndex = makeBeautyGrade.selectedIndex;
		var makeBeautyName = makeBeautyGrade.options[makeBeautyGradeIndex].text;
		if (makeBeautyName != "-请选择-") {

			var makeBeautyBeCost = document.form3.makeBeautyBeCost.value;
			var makeBeautyChangeCost = document.form3.makeBeautyRealCost.value;
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
			document.form3.makeBeautyGrade.focus();
			return false;
		}
	}
	

	if (document.form3.leaveRoomBox.checked == true) {
		var leaveRoomGrade = document.form3.leaveRoomGrade;
		var leaveRoomGradeIndex = leaveRoomGrade.selectedIndex;
		var leaveRoomName = leaveRoomGrade.options[leaveRoomGradeIndex].text;
		if (leaveRoomName != "-请选择-") {
			var leaveRoomBeCost = document.form3.leaveRoomBeCost.value;
			var leaveRoomChangeCost = document.form3.leaveRoomRealCost.value;
			var leaveRoomRealCost = leaveRoomBeCost - leaveRoomChangeCost + "";
			jsonLeaveRoom = "{'leaveRoomName':'" + leaveRoomName
					+ "','leaveRoomBeCost':'" + leaveRoomBeCost
					+ "','leaveRoomRealCost':'" + leaveRoomRealCost + "'}";
			// jsonLeaveRoom='{"leaveRoomName":'+leaveRoomName+',"leaveRoomBeCost":'+leaveRoomBeCost+',"leaveRoomRealCost":'+leaveRoomRealCost+'}';
			// jsonLeaveRoom={"leaveRoomName":leaveRoomName,"leaveRoomBeCost":leaveRoomBeCost,"leaveRoomRealCost":leaveRoomRealCost};
		} else {
			jsonLeaveRoom = "";
			alert("请确认已选择告别厅");
			document.form3.leaveRoomGrade.focus();
			return false;
		}
	}

	if (document.form3.cremationBox.checked == true) {
		var cremationGrade = document.form3.cremationGrade;
		var cremationGradeIndex = cremationGrade.selectedIndex;
		var cremationName = cremationGrade.options[cremationGradeIndex].text;
		if (cremationName != "-请选择-") {
			var cremationBeCost = document.form3.cremationBeCost.value;
			var cremationChangeCost = document.form3.cremationRealCost.value;
			var cremationRealCost = cremationBeCost - cremationChangeCost + "";
			jsonCremation = "{'cremationName':'" + cremationName
					+ "','cremationBeCost':'" + cremationBeCost
					+ "','cremationRealCost':'" + cremationRealCost + "'}";
			// jsonCremation='{"cremationName":'+cremationName+',"cremationBeCost":'+cremationBeCost+',"cremationRealCost":'+cremationRealCost+'}';
			// jsonCremation={"cremationName":cremationName,"cremationBeCost":cremationBeCost,"cremationRealCost":cremationRealCost};
		} else {
			jsonCremation = "";
			alert("请确认已选择火化炉");
			document.form3.cremationGrade.focus();
			return false;
		}
	}

	var urn = jsonUrn + "";
	var makeBeauty = jsonMakeBeauty + "";
	var leaveRoom = jsonLeaveRoom + "";
	var cremation = jsonCremation + "";
	var funeralGoods = arrFuneralGoods + "";
	url = "deadId=" + document.form3.deadId.value + "&";
	url = url + "funeralGoods=" + funeralGoods + "&";
	url = url + "urn=" + urn + "&";
	url = url + "makeBeauty=" + makeBeauty + "&";
	url = url + "leaveRoom=" + leaveRoom + "&";
	url = url + "cremation=" + cremation;
	if (confirm("是否确定服务")) {

		http_request = createHttpRequest();
		http_request.onreadystatechange = decideYourChooseCallBack;
		http_request.open('POST', "AddServiceAction!addService", false);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		http_request.send(url);
	}
	return false;// 阻止页面刷新
}

function decideYourChooseCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			alert(http_request.responseText);
			// location.reload();
		}
	}
}

function ReadIDCard() {
	clearForm();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillForm();
		return;
	}

	alert("读卡错误,错误原因:" + ret);
}

function fillForm() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;
	var pAddress = CVR_IDCard.Address;
	var pBorn = CVR_IDCard.Born;
	var pSex = CVR_IDCard.Sex;
	var nowDate = new Date();
	var currentTime = nowDate.getFullYear();
	var bornDate = pBorn.substr(0, 4);
	var pAge = currentTime - bornDate + 1;

	document.form1.deadName.value = pName;
	document.form1.deadId.value = pCardNo;
	document.form1.deadAddress.value = pAddress;
	document.form1.deadAge.value = pAge;

	if (pSex == "1") {
		document.form1.deadSex.selectedIndex = 1;
	}
	if (pSex == "0") {
		document.form1.deadSex.selectedIndex = 2;
	}
}

function clearForm() {
	document.form1.deadName.value = "";
	document.form1.deadId.value = "";
	document.form1.deadAddress.value = "";
	document.form1.deadAge.value = "";
}

function readDealerCard() { // 读取经办人身份证信息
	clearDealerForm();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillDealerForm();
		return;
	}
	alert("读卡错误,错误原因:" + ret);
}

function clearDealerForm() {
	document.form1.dealerName.value = "";
	document.form1.dealerId.value = "";
	document.form1.directorName.value = "";
	document.form1.dealerAddress.value = "";
}

function fillDealerForm() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;
	var pAddress = CVR_IDCard.Address;
	document.form1.dealerName.value = pName;
	document.form1.dealerId.value = pCardNo;
	document.form1.dealerAddress.value = pAddress;
}

function ReadForm3DealerCard() { // 读取经办人身份证信息
	clearForm3DealerForm();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillForm3DealerForm();
		return;
	}
	alert("读卡错误,错误原因:" + ret);
}

function clearForm3DealerForm() {
	document.form3.dealerName.value = "";
	document.form3.dealerId.value = "";
	document.form3.directorName.value = "";
	document.form3.dealerAddress.value = "";
}

function fillForm3DealerForm() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;
	var pAddress = CVR_IDCard.Address;
	document.form3.dealerName.value = pName;
	document.form3.dealerId.value = pCardNo;
	document.form3.dealerAddress.value = pAddress;
}

function ReadIDCardForQRCode() {
	clearFormForQRCode();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillFormForQRCode();
		document.form2.deadId.onchange();
		return;
	}
	alert("读卡错误,错误原因:" + ret);
}

function clearFormForQRCode() {
	document.form2.deadId.value = "";
	document.form2.deadName.value = "";
}

function fillFormForQRCode() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;
	document.form2.deadName.value = pName;
	document.form2.deadId.value = pCardNo;
}

function ReadIDCardForService() {
	clearFormForService();
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0") {
		fillFormForService();
		document.form3.deadId.onchange();
		return;
	}
	alert("读卡错误,错误原因:" + ret);
}

function clearFormForService() {
	document.form3.deadId.value = "";
	document.form3.deadName.value = "";
}

function fillFormForService() {
	var pName = CVR_IDCard.Name;
	var pCardNo = CVR_IDCard.CardNo;

	document.form3.deadName.value = pName;
	document.form3.deadId.value = pCardNo;
}

// 验证身份证号码的有效性
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
// 验证电话号码有效性
function validatePhoneNumber(phoneNumber) {
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	var isMob = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	if (isMob.test(phoneNumber) || isPhone.test(phoneNumber)) {
		return true;
	} else {
		return false;
	}
}

function validateForm1(form) {
	if (form.deadId.value == "") {
		alert("逝者身份证号码不能为空！");
		form.deadId.focus();
		return false;
	}
	if (form.inTime.value == "") {
		alert("遗体进馆时间不能为空！");
		form.inTime.focus();
		return false;
	}
	var idCard = form.deadId.value;
	if (!(validateIdCard(idCard))) {
		alert("身份证号码格式错误，请检查后重新输入！");
		form.deadId.focus();
		return false;
	}
	var phoneNumber = form.memberMobile.value;
	if ((phoneNumber != "") && !(validatePhoneNumber(phoneNumber))) {
		alert("电话号码格式错误，请检查后重新输入！");
		form.memberMobile.focus();
		return false;
	} else {
		// 获取性别下拉列表中的选中项
		var sexSelect = form.deadSex;
		var sexIndex = sexSelect.selectedIndex;
		var deadSex = sexSelect.options[sexIndex].text;
		
		if(deadSex=="请选择..."){
			alert("请选择逝者性别");
			form.deadSex.focus();
			return false;
		}

		url = "deadInfo.deadId=" + form.deadId.value + "&";  //身份证号码
		url = url + "deadInfo.deadName=" + form.deadName.value + "&"; //姓名
		url = url + "deadInfo.deadSex=" + deadSex + "&";  //性别
		url = url + "deadInfo.memberMobile=" + form.memberMobile.value + "&"; //家属电话
		
		if (form.deadAge.value != "") {     
			url = url + "deadInfo.deadAge=" + form.deadAge.value + "&";    //年龄
		}     
		url = url + "deadInfo.inTime=" + form.inTime.value + "&";  //进馆时间
		url = url + "deadInfo.deadAddress=" + form.deadAddress.value + "&";  //家庭住址
		url = url + "deadInfo.deadExtraInfo=" + form.deadExtraInfo.value;
		http_request = createHttpRequest();
		http_request.onreadystatechange = validateForm1Callback;
		http_request.open('POST', "DeadInfoRegisterAction!registDeadInfo",
				false);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
	}
}

function validateForm1Callback() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var returnString = eval(http_request.responseText);
			alert(returnString);
			if (returnString == "成功加入了1行数据！") {
				document.form1.printDeadInfo.disabled = false;
			} else {
				document.form1.printDeadInfo.disabled = true;
			}
		} else {
			alert("您所请求的页面没有响应！");
		}
	}
}

// 提取遗体打印二维码需要的信息
function updateData(input) {
	url = "deadId=" + form2.deadId.value;
	http_request = createHttpRequest();
	http_request.onreadystatechange = updateDataCallback;
	http_request.open('POST', "PrintQRCodeAction!getQRCodeInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	return false;// 阻止页面刷新
}

function updateDataCallback() // 改变二维码下面用于打印的table中遗体信息显示的大小
{
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			if (json.flag == "1") {
				form2.memberMobile.value = json.memberMobile;
				form2.deadNumber.value = json.deadNumber;
				form2.inTime.value = json.inTime;
				form2.deadName.value = json.deadName;
				// <font style='font-size:20px;'>"+deadName+"</font>
				document.getElementById("deadInfoPrint").style.display = "";
				var deadNumber = json.deadNumber;
				// "<font style='font-size:15px;'>"+invoiceNo+"</font>"
				if (deadNumber.length == 1) {
					var deadNumberString = "遗体号：0" + deadNumber;
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
				form2.memberMobile.innerText = "";
				form2.deadName.innerText = "";
				form2.deadNumber.innerText = "";
				form2.inTime.innerText = "";
				form2.deadNumber.innerText = "";

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

function produceCodeCallback() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			// alert("遗体二维码生成成功");
			document.form2.deadId.onchange();
			var deadIdNumber = form2.deadId.value;
			var remainsNumber = form2.deadNumber.value;
			if (remainsNumber.length == 1) {
				remainsNumber = "" + "0" + remainsNumber;
			}
			document.getElementById("QRCode").src = "PrintQRCodeAction2?deadId="
					+ deadIdNumber + "&deadNumber=" + remainsNumber + "";
			return false;
		}
	}
}

function printCodeImage() { // 打印二维码
	produceCodeCallback();
	var deadId = document.form2.deadId.value;
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
	eprnstr = "<!--endprint-->";
	prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
	window.document.body.innerHTML = prnhtml;
	top.right.focus();
	top.right.print();
	window.document.body.innerHTML = bdhtml;
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"); // 重新绑定选项卡标签的各个触发事件
	document.getElementById("printDeadInfoPage").click();
	document.form2.deadId.value = deadId;
	document.form2.deadId.onchange();
}

function produceFamilyCodeCallback() {
	document.getElementById("deadInfoPrint").style.display = "none";
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			// alert("家属二维码生成成功");
			var familyInfo = document.form2.deadId.value;
			var familyInfoStr = familyInfo.substring(0, familyInfo.length - 1);
			var familyInfoString = "J" + familyInfoStr;
			var remainsNumber = form2.deadNumber.value;
			if (remainsNumber.length == 1) {
				remainsNumber = "0" + remainsNumber;
			}
			document.getElementById("QRCode").src = "PrintQRCodeAction2?deadId="
					+ familyInfoString + "&deadNumber=" + remainsNumber + "";
			return false;
		}
	}
}

function familyOutPut() { // 打印家属二维码 var
							
	produceFamilyCodeCallback();
	document.getElementById("deadNumberShow").innerText = "";
	document.getElementById("deadNamePrint").innerText = "";
	document.getElementById("deadSexPrint").innerText = "";
	document.getElementById("deadAgePrint").innerText = "";
	document.getElementById("deadAddressPrint").innerText = "";
	
	var deadId = document.form2.deadId.value;
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
	eprnstr = "<!--endprint-->";
	prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
	window.document.body.innerHTML = prnhtml;
	top.right.focus();
	top.right.print();
	window.document.body.innerHTML = bdhtml;
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	document.getElementById("printDeadInfoPage").click();
	document.form2.deadId.value = deadId;
	document.form2.deadId.onchange();
	document.getElementById("deadInfoPrint").style.display = "none";
}
// 第三个form显示遗体信息
function showData() {
	url="deadId="+form3.deadId.value;
	http_request=createHttpRequest();
	http_request.onreadystatechange=showDataCallback;
	http_request.open('POST',"RegisterServiceAction!showDeadInfo",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}

function getPathogenyDetail() {
	var deadReasonSelect = document.form3.deadReason;
	var deadReasonIndex = deadReasonSelect.selectedIndex;
	var deadReason = deadReasonSelect.options[deadReasonIndex].text;
	if (deadReason == "病故") {
		document.form3.pathogeny.style.display = "";
	}
	if (deadReason != "病故") {
		document.form3.pathogeny.style.display = "none";
	}
}
function showDataCallback(){
	if(http_request.readyState==4){
		
		if(http_request.status==200){
			
			var json=eval("("+http_request.responseText+")");
			
			json=eval("("+json+")");
			if(json.flag=="1"){
				form3.memberMobile.value=json.memberMobile;
				form3.deadName.value=json.deadName;
				form3.deadSex.value=json.deadSex;
				form3.deadAge.value=json.deadAge;
				form3.deadNumber.value=json.deadNumber;
				form3.inTime.value=json.inTime;
				form3.dealerId.value=json.dealerId;
				form3.dealerName.value=json.dealerName;
				form3.directorName.value=json.directorName;
				form3.dealerAddress.value=json.dealerAddress;
				
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
				
//				var deadResidenceSelect = document.form3.deadResidence;
//				var deadResidenceIndex = deadResidenceSelect.selectedIndex;				
//				deadResidenceSelect.options[deadResidenceIndex].text=json.deadResidence;
//				
//				var operatorRelationSelect = document.form3.operatorRelation;
//				var operatorRelationIndex = operatorRelationSelect.selectedIndex;				
//				operatorRelationSelect.options[operatorRelationIndex].text=json.operatorRelation;
//				
//				
//				var ashesDispositionSelect = document.form3.ashesDisposition;
//				var ashesDispositionIndex = ashesDispositionSelect.selectedIndex;				
//				ashesDispositionSelect.options[ashesDispositionIndex].text=json.ashesDisposition;
				
				if(json.deadReason!=""){
					for(var i=0;i<document.getElementById("deadReason").options.length;i++)
				    {
				        if(document.getElementById("deadReason").options[i].value == json.deadReason)
				        {
				            document.getElementById("deadReason").options[i].selected=true;
				            break;
				        }
				    }
					
					form3.deadReason.onchange();
				}
				if(json.deadType!=""){
					for(var i=0;i<document.getElementById("deadKind").options.length;i++)
				    {
				        if(document.getElementById("deadKind").options[i].value == json.deadType)
				        {
				            document.getElementById("deadKind").options[i].selected=true;
				            break;
				        }
				    }
				}
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
					document.form3.pathogeny.style.display="none";
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
				
//				var areaSelect = document.form3.area;
//				var areaIndex = areaSelect.selectedIndex;				
//				areaSelect.options[areaIndex].text=json.area;
//				var deadTime=json.deadTime;
//				deadTime=deadTime.substring(0,11);				
//				form3.deadTime.value=deadTime;
				if(json.deadTime=="0001-01-01 00:00:00"){
					form3.deadTime.value="";
				}
				else{
					
					form3.deadTime.value = json.deadTime.substring(0,11);
				}
				form3.deadAddress.value=json.address;
				form3.deadProveUnit.value = json.proofUnit;
				form3.deadExtraInfo.value = json.memo;
				form3.invoiceNo.value = json.invoiceNo;
				form3.subsidyNo.value = json.subsidyNo;
				form3.subsidyMoney.value = json.subsidyMoney;
				if(json.benefitTime=="0001-01-01 00:00:00"){
					form3.benefitTime.value="";
				}
				else{
					
					form3.benefitTime.value = json.benefitTime.substring(0,11);
				}
				

				
				form3.remainsCarryBeCost.value=json.remainsCarryBeCost;
				form3.remainsCarryRealCost.value=json.remainsCarryRealCost;
				form3.lastRemainsCarryRealCost.value=json.remainsCarryRealCost;
				
				form3.rentCrystalBeCost.value=json.rentCrystalBeCost;
				form3.rentCrystalRealCost.value=json.rentCrystalRealCost;
				form3.lastRentCrystalRealCost.value=json.rentCrystalRealCost;
				
				form3.rentCrystalCarBeCost.value=json.rentCrystalCarBeCost;
				form3.rentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
				form3.lastRentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
				
				form3.watchSpiritVillaBeCost.value=json.watchSpiritVillaBeCost;
				form3.watchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;
				form3.lastWatchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;
				
				form3.watchSpiritCoffinBeCost.value=json.watchSpiritCoffinBeCost;
				form3.watchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
				form3.lastWatchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
				//lastWholeCost存放的是除了火化之外的费用总和
				//theWholeCost存放的是所有实际收费的费用
				form3.theWholeCost.value=parseInt(form3.theWholeCost.value)-parseInt(form3.lastWholeCost.value)+parseInt(json.remainsCarryRealCost)+parseInt(json.rentCrystalRealCost)+parseInt(json.rentCrystalCarRealCost)+parseInt(json.watchSpiritVillaRealCost)+parseInt(json.watchSpiritCoffinRealCost);
				form3.lastWholeCost.value=parseInt(form3.remainsCarryRealCost.value)+parseInt(form3.rentCrystalRealCost.value)+parseInt(form3.rentCrystalCarRealCost.value)+parseInt(form3.watchSpiritVillaRealCost.value)+parseInt(form3.watchSpiritCoffinRealCost.value)+"";
				
				form3.allBeCost.value=parseInt(form3.theWholeCost.value)+parseInt(form3.remainsCarryBeCost.value)+parseInt(form3.rentCrystalBeCost.value)+parseInt(form3.rentCrystalCarBeCost.value)+parseInt(form3.watchSpiritVillaBeCost.value)+parseInt(form3.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
				form3.allRealCost.value=parseInt(form3.allRealCost.value)+parseInt(form3.remainsCarryBeCost.value)+parseInt(form3.rentCrystalBeCost.value)+parseInt(form3.rentCrystalCarBeCost.value)+parseInt(form3.watchSpiritVillaBeCost.value)+parseInt(form3.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
				
				var cg=document.getElementById("cremationGrade");//火化
				for(i=0; i<cg.options.length; i++)
				{
					if (cg.options[i].value ==json.A03) // 动态选择selected默认值
					{
						
						cg.options[i].selected = true;
					}
				}
//				getCremationBeCost();
				var mg=document.getElementById("makeBeautyGrade");//美容
				for(i=0; i<mg.options.length; i++)
				{
					if (mg.options[i].value ==json.A01) // 动态选择selected默认值
					{
						mg.options[i].selected = true;
					}
				}
//				getMakeBeautyBeCost();
				var lg=document.getElementById("leaveRoomGrade");//告别
				for(i=0; i<lg.options.length; i++)
				{
					if (lg.options[i].value ==json.A02) // 动态选择selected默认值
					{
						lg.options[i].selected = true;
					}
				}
//				getLeaveRoomBeCost();
			}
			else
	 		{
				form3.memberMobile.value="";
	 			form3.deadName.value="";
	 			form3.deadNumber.value="";
	 			form3.inTime.value="";
	 			form3.remainsCarryRealCost.value="";
				form3.rentCrystalRealCost.value="";
	 		}
		}
		else{
			alert("显示遗体信息失败！");
		}
	}
}
//function showDataCallback() { // 改变逝者身份证号码除了时价格和减免金额的联动
//	if (http_request.readyState == 4) {
//		if (http_request.status == 200) {
//			
//			var json = eval("(" + http_request.responseText + ")");
//			
//			json = eval("(" + json + ")");
//			
//			if (json.flag == "1") {
//				form3.allRealCost.value = parseInt(form3.allRealCost.value)
//						+ (parseInt(form3.remainsCarryBeCost.value)															
//								- parseInt(form3.remainsCarryRealCost.value));
//				form3.memberMobile.value = json.memberMobile;
//				form3.deadName.value = json.deadName;
//				form3.deadNumber.value = json.deadNumber;
//				form3.inTime.value = json.inTime;
//				form3.dealerId.value = json.dealerId;
//				form3.dealerName.value = json.dealerName;
//				form3.directorName.value = json.directorName;
//				form3.dealerAddress.value = json.dealerAddress;
//				
//				if(json.deadTime=="0001-01-01 00:00:00"){
//					form3.deadTime.value="";
//				}else{
//					form3.deadTime.value = json.deadTime.substring(0, 10);
//				}
//				
//				form3.deadSex.value = json.deadSex;
//				form3.deadAge.value = json.deadAge;
//				form3.deadAddress.value = json.address;
//
//				form3.remainsCarryBeCost.value = json.remainsCarryBeCost;
//				form3.remainsCarryRealCost.value = json.remainsCarryRealCost;
//				form3.lastRemainsCarryRealCost.value = json.remainsCarryRealCost;
//							
//				form3.rentCrystalBeCost.value=json.rentCrystalBeCost;
//				form3.rentCrystalRealCost.value=json.rentCrystalRealCost;
//				form3.lastRentCrystalRealCost.value=json.rentCrystalRealCost;
//				
//				form3.rentCrystalCarBeCost.value=json.rentCrystalCarBeCost;
//				form3.rentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
//				form3.lastRentCrystalCarRealCost.value=json.rentCrystalCarRealCost;
//				
//				form3.watchSpiritVillaBeCost.value=json.watchSpiritVillaBeCost;
//				form3.watchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;
//				form3.lastWatchSpiritVillaRealCost.value=json.watchSpiritVillaRealCost;				
//				form3.watchSpiritCoffinBeCost.value=json.watchSpiritCoffinBeCost;
//				form3.watchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
//				form3.lastWatchSpiritCoffinRealCost.value=json.watchSpiritCoffinRealCost;
//				//lastWholeCost存放的是除了火化之外的费用总和
//				//theWholeCost存放的是所有实际收费的费用
//				form3.theWholeCost.value=parseInt(form3.theWholeCost.value)-parseInt(form3.lastWholeCost.value)+parseInt(json.remainsCarryRealCost)+parseInt(json.rentCrystalRealCost)+parseInt(json.rentCrystalCarRealCost)+parseInt(json.watchSpiritVillaRealCost)+parseInt(json.watchSpiritCoffinRealCost);
//				form3.lastWholeCost.value=parseInt(form3.remainsCarryRealCost.value)+parseInt(form3.rentCrystalRealCost.value)+parseInt(form3.rentCrystalCarRealCost.value)+parseInt(form3.watchSpiritVillaRealCost.value)+parseInt(form3.watchSpiritCoffinRealCost.value)+"";
//				
//				form3.allBeCost.value=parseInt(form3.theWholeCost.value)+parseInt(form3.remainsCarryBeCost.value)+parseInt(form3.rentCrystalBeCost.value)+parseInt(form3.rentCrystalCarBeCost.value)+parseInt(form3.watchSpiritVillaBeCost.value)+parseInt(form3.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
//				form3.allRealCost.value=parseInt(form3.allRealCost.value)+parseInt(form3.remainsCarryBeCost.value)+parseInt(form3.rentCrystalBeCost.value)+parseInt(form3.rentCrystalCarBeCost.value)+parseInt(form3.watchSpiritVillaBeCost.value)+parseInt(form3.watchSpiritCoffinBeCost.value)-parseInt(json.remainsCarryRealCost)-parseInt(json.rentCrystalRealCost)-parseInt(json.rentCrystalCarRealCost)-parseInt(json.watchSpiritVillaRealCost)-parseInt(json.watchSpiritCoffinRealCost);
//
//				
//				var cg = document.form3.deadKind;// 火化
//				for (var i = 0; i < cg.options.length; i++) {
//					if (cg.options[i].value == json.deadKind) // 动态选择selected默认值
//					{
//						cg.options[i].selected = true;
//					}
//				}
//				var dr = document.form3.deadReason;// 火化
//				for (var j = 0; j < dr.options.length; j++) {
//					if (dr.options[j].value == json.deadReason) // 动态选择selected默认值
//					{
//						dr.options[j].selected = true;
//					}
//				}
//				
//				var area = document.form3.area;// 火化
//				for (var k = 0; k < area.options.length; k++) {
//					if (area.options[k].value == json.area) // 动态选择selected默认值
//					{
//						area.options[k].selected = true;
//					}
//				}
//				var pathogeny = document.form3.pathogeny;
//
//				for (var l = 0; l < pathogeny.options.length; l++) {
//					if (pathogeny.options[l].value == json.pathogeny) {
//						pathogeny.options[l].selected = true;
//						if (json.deadReason == "病故") {
//							pathogeny.style.display = "";
//						}
//					}
//				}
//				var operatorRelation = document.form3.operatorRelation;
//				for (var l = 0; l < operatorRelation.options.length; l++) {
//					if (operatorRelation.options[l].value == json.operatorRelation) {
//						operatorRelation.options[l].selected = true;
//					}
//				}
//				var deadResidence = document.form3.deadResidence;
//				for (var l = 0; l < deadResidence.options.length; l++) {
//					if (deadResidence.options[l].value == json.deadResidence) {
//						deadResidence.options[l].selected = true;
//					}
//				}
//				var ashesDisposition = document.form3.ashesDisposition;
//				for (var l = 0; l < ashesDisposition.options.length; l++) {
//					if (ashesDisposition.options[l].value == json.ashesDisposition) {
//						ashesDisposition.options[l].selected = true;
//					}
//				}
//				form3.deadProveUnit.value = json.proofUnit;
//				form3.deadExtraInfo.value = json.memo;
//				form3.invoiceNo.value = json.invoiceNo;
//				form3.subsidyNo.value = json.subsidyNo;
//				form3.subsidyMoney.value = json.subsidyMoney;
//				
//				form3.benefitTime.value = json.benefitTime;
//			} else {
//				alert("不存在此身份证号码对应的记录");
//				location.reload();
//			}
//		} else {
//			alert("您请求的页面没有响应！");
//		}
//	}
//}

function outPutSetServiceOrder() {
	var setServiceCheckBox = document.form3.setServiceCheckBox;
	if (setServiceCheckBox.checked == true) {
		document.getElementById("setNo").innerHTML = "";
		document.getElementById("setTaxDate").innerHTML = "";
		document.getElementById("setName").innerHTML = "";
		document.getElementById("setSex").innerHTML = "";
		document.getElementById("setAge").innerHTML = "";
		document.getElementById("setAddress").innerHTML = "";
		document.getElementById("setItem1").innerHTML = "";
		document.getElementById("setItem2").innerHTML = "";
		document.getElementById("setItem3").innerHTML = "";
		document.getElementById("setItem4").innerHTML = "";
		document.getElementById("setItem5").innerHTML = "";
		document.getElementById("setItem6").innerHTML = "";
		document.getElementById("setItem7").innerHTML = "";
		document.getElementById("itemPrice1").innerHTML = "";
		document.getElementById("itemPrice2").innerHTML = "";
		document.getElementById("itemPrice3").innerHTML = "";
		document.getElementById("itemPrice4").innerHTML = "";
		document.getElementById("itemPrice5").innerHTML = "";
		document.getElementById("itemPrice6").innerHTML = "";
		document.getElementById("itemPrice7").innerHTML = "";
		document.getElementById("setAllCost").innerHTML = "";
		document.getElementById("setAllCostUp").innerHTML = "";
		document.getElementById("setServiceDetail").innerHTML = "";
		document.getElementById("currentDateTh").innerHTML = "";
		var myDate = new Date();
		document.getElementById("setName").innerHTML = "<font style='font-weight:bold;'>"
				+ form3.deadName.value + "</font>";
		// document.getElementById("setNo").innerHTML="<font
		// style='font-weight:bold;font-size:22px'>"+form3.deadNumber.value+"</font>";
		document.getElementById("setTaxDate").innerHTML = "<font style='font-weight:bold;'>"
				+ myDate.toLocaleDateString()
				+ " "
				+ myDate.toLocaleTimeString() + "</font>";
		document.getElementById("setSex").innerHTML = "<font style='font-weight:bold;'>"
				+ document.form3.deadSex.value + "</font>";
		document.getElementById("setAge").innerHTML = "<font style='font-weight:bold;'>"
				+ document.form3.deadAge.value + "</font>";
		document.getElementById("setAddress").innerHTML = "<font style='font-weight:bold;'>"
				+ document.form3.deadAddress.value + "</font>";

		var setServiceSelect = document.form3.setService;
		var setServiceIndex = setServiceSelect.selectedIndex;
		var setService = setServiceSelect.options[setServiceIndex].text; // 选择的套餐
		var setServiceCheckBox = document.form3.setServiceCheckBox;
		if ((setServiceCheckBox.checked == true) && (setService != "-请选择-")) {
			document.getElementById("setItem1").innerHTML = "<font style='font-weight:bold;'>"
					+ setService + "</font>";
		}
		document.getElementById("currentDateTh").innerHTML = "<font style='font-weight:bold;'>"
				+ form3.deadNumber.value + "</font>";
		document.getElementById("setNo").innerHTML = "<font style='font-weight:bold;'>"
				+ document.getElementById("invoiceNo").value + "</font>";
		var leaveRoomBeCost = document.form3.leaveRoomBeCost.value;
		var cremationBeCost = document.form3.cremationBeCost.value;
		var setServiceAllCost = document.form3.setServiceAllCost.value;
		var setCost = parseInt(setServiceAllCost) + parseInt(cremationBeCost)
				+ parseInt(leaveRoomBeCost);
		document.getElementById("itemPrice1").innerHTML = "<font style='font-weight:bold;'>"
				+ setCost + "</font>"; // 套餐价格

		var number = document.getElementById("theWholeCost").value;
		document.getElementById("setAllCost").innerHTML = "<font style='font-weight:bold;'>"
				+ number + "</font>";
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
		document.getElementById("setAllCostUp").innerHTML = "<font style='font-weight:bold;'>"
				+ str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零")
						.replace(/零(万|亿|元)/g, "$1")
						.replace(/(亿)万|(拾)/g, "$1$2").replace(/^元零?|零分/g, "")
						.replace(/元$/g, "元整") + "</font>";

		// 显示套餐外服务详情
		var urnChooseBox = document.form3.urnChooseBox;
		var urnChooseSelect = document.form3.urnChoose;
		var urnChooseIndex = urnChooseSelect.selectedIndex;
		var urnChoose = urnChooseSelect.options[urnChooseIndex].text;
		var urnBeCost = document.form3.urnBeCost.value;
		var urnRealCost = document.form3.urnRealCost.value;
		var urnCost = parseInt(urnBeCost) - parseInt(urnRealCost);
		if ((urnChooseBox.checked == true) && (urnChoose != "-请选择-")) {
			document.getElementById("setItem2").innerHTML = "<font style='font-weight:bold;'>"
					+ urnChoose + "</font>";
			document.getElementById("itemPrice2").innerHTML = "<font style='font-weight:bold;'>"
					+ urnCost + "</font>";
		}

		var remainsCarryBeCost = document.form3.remainsCarryBeCost.value;
		if (remainsCarryBeCost != "0") {
			document.getElementById("setItem3").innerHTML = "<font style='font-weight:bold;'>"
					+ "代收车费" + "</font>";
			document.getElementById("itemPrice3").innerHTML = "<font style='font-weight:bold;'>"
					+ remainsCarryBeCost + "</font>";
		}

		// 将套餐中不包含的附加的丧葬品判断并且打印到套餐单据中
		var setServiceDetailContent = document.form3.setServiceDetailContent.value;
		var tableName = document.getElementById("allFuneralGoods");
		var tableRows = tableName.rows.length;
		var indexStart = 4;
		for (var i = 2; i < tableRows; i++) {
			var goodsCheckBoxInput1 = tableName.rows[i].cells[3]
					.getElementsByTagName("input");
			var goodsCheckBoxInput2 = tableName.rows[i].cells[10]
					.getElementsByTagName("input");
			if (goodsCheckBoxInput1[0].checked) {
				var goodsName = tableName.rows[i].cells[0].innerText;
				var goodsBeCost = tableName.rows[i].cells[1]
						.getElementsByTagName("input")[0].value;
				var goodsRealCost = tableName.rows[i].cells[2]
						.getElementsByTagName("input")[0].value;
				var goodsCost = goodsBeCost - goodsRealCost;
				if ((setServiceDetailContent.indexOf(goodsName) < 0)
						&& (indexStart <= 9)) {
					var setItemIndex = "setItem" + indexStart;
					document.getElementById(setItemIndex).innerHTML = "<font style='font-weight:bold;'>"
							+ goodsName + "</font>";
					var itemPriceIndex = "itemPrice" + indexStart;
					document.getElementById(itemPriceIndex).innerHTML = "<font style='font-weight:bold;'>"
							+ goodsCost + "</font>";
					indexStart = parseInt(indexStart) + parseInt(1);
				}
			}
			if ((goodsCheckBoxInput2 != null)
					&& (goodsCheckBoxInput2[0].checked)) {
				var goodsName = tableName.rows[i].cells[7].innerText;
				var goodsBeCost = tableName.rows[i].cells[8]
						.getElementsByTagName("input")[0].value;
				var goodsRealCost = tableName.rows[i].cells[9]
						.getElementsByTagName("input")[0].value;
				var goodsCost = goodsBeCost - goodsRealCost;
				if ((setServiceDetailContent.indexOf(goodsName) < 0)
						&& (indexStart <= 9)) {
					var setItemIndex = "setItem" + indexStart;
					document.getElementById(setItemIndex).innerHTML = "<font style='font-weight:bold;'>"
							+ goodsName + "</font>";
					var itemPriceIndex = "itemPrice" + indexStart;
					document.getElementById(itemPriceIndex).innerHTML = "<font style='font-weight:bold;'>"
							+ goodsCost + "</font>";
					indexStart = parseInt(indexStart) + parseInt(1);
				}
			}
		}
		if (indexStart > 9) {
			alert("选择小商品数目过多，无法选择套餐服务，请重新选取");
			location.reload();
		}
	}
	if (setServiceCheckBox.checked == false) {
		alert("套餐未选取，无法打印套餐单据");
	}
}

function chooseSetService() {
	var setServiceCheckBox = document.form3.setServiceCheckBox;
	if (setServiceCheckBox.checked == false) {
		location.reload();
	}
	if (setServiceCheckBox.checked == true) {
		var setService = document.form3.setService;
		setService.disabled = "";
		document.form3.createSetOrder.disabled = "";
		document.form3.printSetServiceList.disabled = "";
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
			+ form3.deadName.value + "</font>";
	// document.getElementById("no").innerHTML="<font
	// style='font-weight:bold;font-size:22px'>"+form3.deadNumber.value+"</font>";
	document.getElementById("currentDateTh2").innerHTML = "<font style='font-weight:bold;'>"
			+ form3.deadNumber.value + "</font>";
	document.getElementById("no").innerHTML = "<font style='font-weight:bold;'>"
			+ document.getElementById("invoiceNo").value + "</font>";
	var car1Cost = form3.remainsCarryBeCost.value;
	var car1RealCost = form3.remainsCarryRealCost.value;
	var car2Cost=form3.rentCrystalCarBeCost.value;
	var car2RealCost=form3.rentCrystalCarRealCost.value;
	var crystalCost=form3.rentCrystalBeCost.value;
	var crystalRealCost=form3.rentCrystalRealCost.value;
	var watchSpiritCost=parseInt(form3.watchSpiritVillaBeCost.value)+parseInt(form3.watchSpiritCoffinBeCost.value);
	var watchSpiritRealCost=parseInt(form3.watchSpiritVillaRealCost.value)+parseInt(form3.watchSpiritCoffinRealCost.value);


	var allServiceShouldCost = form3.allBeCost.value;

	if (form3.theWholeCost.value != "0") {
		document.getElementById("total").innerHTML = "<font style='font-weight:bold;'>"
				+ form3.theWholeCost.value + "</font>";
	}
	if (allServiceShouldCost != "0") {
		document.getElementById("beCost").innerHTML = "<font style='font-weight:bold;'>"
				+ allServiceShouldCost + "</font>";
	}
	if (form3.allRealCost.value != "0") {
		document.getElementById("charge").innerHTML = "<font style='font-weight:bold;'>"
				+ "-" + form3.allRealCost.value + "</font>";
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
			var goodsCheckBoxInput1 = tableName.rows[i].cells[3]
					.getElementsByTagName("input");
			var goodsCheckBoxInput2 = tableName.rows[i].cells[10]
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
					var goodsName = allFuneralGoods.rows[i].cells[7].innerText;

					var goodsBeCost = allFuneralGoods.rows[i].cells[8]
							.getElementsByTagName("input")[0].value;

					var goodsRealCostInput = allFuneralGoods.rows[i].cells[9]
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
			+ document.form3.deadSex.value + "</font>";
	document.getElementById("age").innerHTML = "<font style='font-weight:bold;'>"
			+ document.form3.deadAge.value + "</font>";
	document.getElementById("address").innerHTML = "<font style='font-weight:bold;font-size:14px;'>"
			+ document.form3.deadAddress.value + "</font>";
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

function outPrintServiceList() {
	var printContent = document.getElementById("goodsPartList");
	var iframe = document.createElement('IFRAME');
	var doc = null;
	iframe.setAttribute('id', 'printIframe');
	iframe.setAttribute('style',
			'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
	document.body.appendChild(iframe);
	doc = iframe.contentWindow.document;
	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();
	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}

function outPrintSetServiceList() {
	var printContent = document.getElementById("setGoodsPartList");
	var iframe = document.createElement('IFRAME');
	var doc = null;
	iframe.setAttribute('id', 'printIframe');
	iframe.setAttribute('style',
			'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
	document.body.appendChild(iframe);
	doc = iframe.contentWindow.document;
	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();
	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}

function decideInvoice() {
	var deadId = document.form3.deadId.value;
	var deadName = document.form3.deadName.value;
	var deadSex = document.form3.deadSex.value;
	var deadAge = document.form3.deadAge.value;
	var invoiceNo = document.form3.invoiceNo.value;
	var subsidyNo = document.form3.subsidyNo.value;
	var benefitTime = document.form3.benefitTime.value;
	var subsidyMoney = document.form3.subsidyMoney.value;
	var dealerId = document.form3.dealerId.value;
	var dealerName = document.form3.dealerName.value;
	var directorName = document.form3.directorName.value;
	var dealerAddress = document.form3.dealerAddress.value;
	var deadTime = document.form3.deadTime.value;
	

	var deadKindSelect = document.form3.deadKind;
	var deadKindIndex = deadKindSelect.selectedIndex;
	var deadKind = deadKindSelect.options[deadKindIndex].text;

	var deadReasonSelect = document.form3.deadReason;
	var deadReasonIndex = deadReasonSelect.selectedIndex;
	var deadReason = deadReasonSelect.options[deadReasonIndex].text;

	var deadAddress = document.form3.deadAddress.value;

	var areaSelect = document.form3.area;
	var areaIndex = areaSelect.selectedIndex;
	var area = areaSelect.options[areaIndex].text;

	var deadProveUnit = document.form3.deadProveUnit.value;
	var deadExtraInfo = document.form3.deadExtraInfo.value;
	var memberMobile = document.form3.memberMobile.value;
	if (!(validatePhoneNumber(memberMobile))) {
		alert("电话号码格式错误，请检查后重新输入！");
		form3.memberMobile.focus();
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
	if(deadTime!==""){
		url = url + "deadInfo.deadTime=" + deadTime + "&";

	}else{
		alert("请选择逝者死亡时间");
		form3.deadTime.focus();
		return false;
	}

	url = url + "deadInfo.deadProveUnit=" + deadProveUnit + "&";
	url = url + "deadInfo.deadExtraInfo=" + deadExtraInfo + "&";
	url = url + "invoiceNo=" + invoiceNo + "&";
	0001-01-01 
	if(benefitTime!=""){
		url = url + "benefitTime=" + benefitTime + "&";
	}else{
		benefitTime="0001-01-01 00:00:00";
		url = url + "benefitTime=" + benefitTime + "&";
	}
	
	url = url + "subsidyNo=" + subsidyNo + "&";

	if (deadReason != "请选择") {
		if (deadReason == "病故") {
			var pathogenySelect = document.form3.pathogeny;
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

	var operatorRelationSelect = document.form3.operatorRelation;
	var operatorRelationIndex = operatorRelationSelect.selectedIndex;
	var operatorRelation = operatorRelationSelect.options[operatorRelationIndex].text;
	if (operatorRelation != "请选择...") {
		url = url + "deadInfo.operatorRelation=" + operatorRelation + "&"; // 与经办人的关系
	} else {
		alert("请选择与经办人的关系");
		operatorRelationSelect.focus();
		return false;
	}

	var deadResidenceSelect = document.form3.deadResidence;
	var deadResidenceIndex = deadResidenceSelect.selectedIndex;
	var deadResidence = deadResidenceSelect.options[deadResidenceIndex].text;
	if (deadResidence != "请选择...") {
		url = url + "deadInfo.deadResidence=" + deadResidence + "&"; // 户籍所在地
	} else {
		alert("请选择户籍所在地");
		deadResidenceSelect.focus();
		return false;
	}

	var ashesDispositionSelect = document.form3.ashesDisposition;
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
// 税票打印
function CreatePrintPage() {
	var deadId = document.form3.deadId.value;
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
			var deadId = document.form3.deadId.value;
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

function setDetailPrint() {
	var setServiceDetailContent = document.form3.setServiceDetailContent.value;
	// var kindSelect=form.deadKind;
	// var kindIndex=kindSelect.selectedIndex;
	// var deadKind=kindSelect.options[kindIndex].text;
	var leaveRoomGradeSelect = document.form3.leaveRoomGrade;
	var leaveRoomGradeIndex = leaveRoomGradeSelect.selectedIndex;
	var leaveRoomGrade = leaveRoomGradeSelect.options[leaveRoomGradeIndex].text; // 告别厅项目
	var leaveRoomBeCost = document.form3.leaveRoomBeCost.value;
	var leaveRoomRealCost = document.form3.leaveRoomRealCost.value;
	var leaveRoomCost = parseInt(leaveRoomBeCost) - parseInt(leaveRoomRealCost); // 告别厅价格

	var cremationGradeSelect = document.form3.cremationGrade;
	var cremationGradeIndex = cremationGradeSelect.selectedIndex;
	var cremationGrade = cremationGradeSelect.options[cremationGradeIndex].text; // 火化项目
	var cremationBeCost = document.form3.cremationBeCost.value;
	var cremationRealCost = document.form3.cremationRealCost.value;
	var cremationCost = parseInt(cremationBeCost) - parseInt(cremationRealCost); // 火化价格

	LODOP.PRINT_INIT("套打EMS的模版");
	LODOP.SET_PRINT_PAGESIZE(1, '210mm', '120mm', '1');
	// LODOP.ADD_PRINT_SETUP_BKIMG("<img border='1'
	// src='http://192.168.1.102:8080/FuneralManageSystem/Images/tax.jpg'/>");
	LODOP
			.ADD_PRINT_SETUP_BKIMG("<img border='1' src='http://localhost:8080/FuneralManageSystem/Images/tax.jpg'/>");
	var setDetail = setServiceDetailContent + leaveRoomGrade + ":"
			+ leaveRoomCost + ";" + cremationGrade + ":" + cremationCost;
	// LODOP.ADD_PRINT_TEXT('27mm','55mm',500,20,setDetail);
	LODOP.ADD_PRINT_HTM('23mm', '55mm', 500, 20,
			"<font style='font-size:10px;'>" + setDetail + "</font>");
}

// 火化证打印
function CreateCremationPrint() {

	var deadId = document.form3.deadId.value;
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
	var deadIdValue = document.form3.deadId.value;
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

	var deadId = document.form3.deadId.value;
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
	var deadId = document.form3.deadId.value;

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

function goToReturnCoffin() { // 页面跳转
	window.location.reload("rentCoffin.jsp?deadId="
			+ document.form1.deadId.value + "&index=" + 2 + "&type=" + "zhenghuo");	
}

function remainsCarryChangeWholeCost() { // 遗体接运实收联动总费用变化
	var lastRemainsCarryRealCost = form3.lastRemainsCarryRealCost.value;
	var currentRemainsCarryRealCost = form3.remainsCarryRealCost.value;
	if (checkNumber(currentRemainsCarryRealCost)) {
		form3.theWholeCost.value = parseInt(form3.theWholeCost.value)
				- parseInt(lastRemainsCarryRealCost)
				+ parseInt(currentRemainsCarryRealCost);
		form3.allRealCost.value = parseInt(form3.allRealCost.value)
				- (parseInt(currentRemainsCarryRealCost) - parseInt(lastRemainsCarryRealCost));
		form3.lastRemainsCarryRealCost.value = currentRemainsCarryRealCost;
	} else {
		form3.remainsCarryRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function rentCrystalChangeWholeCost() { // 租用棺材联动总费用变化
	var lastRentCrystalRealCost = form3.lastRentCrystalRealCost.value;
	var currentRentCrystalRealCost = form3.rentCrystalRealCost.value;
	if (checkNumber(currentRentCrystalRealCost)) {
		form3.theWholeCost.value = parseInt(form3.theWholeCost.value)
				- parseInt(lastRentCrystalRealCost)
				+ parseInt(currentRentCrystalRealCost);
		form3.allRealCost.value = parseInt(form3.allRealCost.value)
				- (parseInt(currentRentCrystalRealCost) - parseInt(lastRentCrystalRealCost));
		form3.lastRentCrystalRealCost.value = currentRentCrystalRealCost;
	} else {
		form3.rentCrystalRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function rentCrystalCarChangeWholeCost() { // 运送棺材费用联动总费用变化
	var lastRentCrystalCarRealCost = form3.lastRentCrystalCarRealCost.value;
	var currentRentCrystalCarRealCost = form3.rentCrystalCarRealCost.value;
	if (checkNumber(currentRentCrystalCarRealCost)) {
		form3.theWholeCost.value = parseInt(form3.theWholeCost.value)
				- parseInt(lastRentCrystalCarRealCost)
				+ parseInt(currentRentCrystalCarRealCost);
		form3.allRealCost.value = parseInt(form3.allRealCost.value)
				- (parseInt(currentRentCrystalCarRealCost) - parseInt(lastRentCrystalCarRealCost));
		form3.lastRentCrystalCarRealCost.value = currentRentCrystalCarRealCost;
	} else {
		form3.rentCrystalCarRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function watchSpiritVillaChangeWholeCost() { // 守灵别墅费用变化联动总费用变化
	var lastWatchSpiritVillaRealCost = form3.lastWatchSpiritVillaRealCost.value;
	var currentWatchSpiritVillaRealCost = form3.watchSpiritVillaRealCost.value;
	if (checkNumber(currentWatchSpiritVillaRealCost)) {
		form3.theWholeCost.value = parseInt(form3.theWholeCost.value)
				- parseInt(lastWatchSpiritVillaRealCost)
				+ parseInt(currentWatchSpiritVillaRealCost);
		form3.allRealCost.value = parseInt(form3.allRealCost.value)
				- (parseInt(currentWatchSpiritVillaRealCost) - parseInt(lastWatchSpiritVillaRealCost));
		form3.lastWatchSpiritVillaRealCost.value = currentWatchSpiritVillaRealCost;
	} else {
		form3.watchSpiritVillaRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function watchSpiritCoffinChangeWholeCost() { // 守灵别墅用棺费用变化联动总费用变化
	var lastWatchSpiritCoffinRealCost = form3.lastWatchSpiritCoffinRealCost.value;
	var currentWatchSpiritCoffinRealCost = form3.watchSpiritCoffinRealCost.value;
	if (checkNumber(currentWatchSpiritCoffinRealCost)) {
		form3.theWholeCost.value = parseInt(form3.theWholeCost.value)
				- parseInt(lastWatchSpiritCoffinRealCost)
				+ parseInt(currentWatchSpiritCoffinRealCost);
		form3.allRealCost.value = parseInt(form3.allRealCost.value)
				- (parseInt(currentWatchSpiritCoffinRealCost) - parseInt(lastWatchSpiritCoffinRealCost));
		form3.lastWatchSpiritCoffinRealCost.value = currentWatchSpiritCoffinRealCost;
	} else {
		form3.watchSpiritCoffinRealCost.focus();
		alert("非纯数字，请检查后重新输入");
	}
}

function decideMixedServiceCost() { // 确定火化服务之外的服务费用
	var deadId = form3.deadId.value;
	var remainsCarryRealCost = form3.remainsCarryRealCost.value;
	var rentCrystalRealCost = form3.rentCrystalRealCost.value;
	var rentCrystalCarRealCost = form3.rentCrystalCarRealCost.value;
	var watchSpiritVillaRealCost = form3.watchSpiritVillaRealCost.value;
	var watchSpiritCoffinRealCost = form3.watchSpiritCoffinRealCost.value;
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

function checkNumber(number) {
	var reg = /^\d+(\.\d+)?$/;
	if (reg.test(number) == true) {
		return true;
	} else {
		return false;
	}
}
                          /*2017-08-22 */
function remainsInfo(){
	
	var pageNumTemp = 1; // 默认当前页为1

	writePage(pageNumTemp);
	return false;
}
function writePage(pageNumTemp) { // 发送查询火化信息的请求

	deleteTable(); // 清空表格
	PageNum = pageNumTemp;

	var startTime = document.form4.startTime.value; // 开始时间
	var endTime = document.form4.endTime.value; // 结束时间

	url = "&startTime=" + startTime + "&endTime=" + endTime;

	url = "pageNum=" + PageNum + url;

	http_request = createHttpRequest();

	http_request.onreadystatechange = writePageCallBack;

	http_request.open('POST', "QueryCremationInfoAction!queryCremationInfo",
			false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;
}
function writePageCallBack() { // 接收查询得到的火化信息
	var pageSize = 10; // 每页显示10条数据
	var TotalPage = 1; // 定义总页数

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;

			var json = eval("(" + result1 + ")");

			

			var jsonValue2 = eval("(" + json + ")");

			var length = jsonValue2.length;
			

			document.getElementById("asd").innerText = jsonValue2[length - 1].result;
			if(length>1){
				
				document.getElementById("remainsInfo").style.display="";
				document.getElementById("divNumber").style.display="";
				
			}
			
			for (var i = 0; i < length - 1; i++) {

				var deadId =jsonValue2[i].deadId;
				var remainsNumber = jsonValue2[i].remainsNumber;
				var deadName = jsonValue2[i].deadName;									
				var inTime = jsonValue2[i].inTime;
				inTime = inTime.substring(0, 10);																
				var remainsInfo = document.getElementById("remainsInfo");
				var row = remainsInfo.insertRow();
				var cell = row.insertCell();
				var cell0 = row.insertCell();
				var cell1 = row.insertCell();
				var cell2 = row.insertCell();
				var cell3 = row.insertCell();

				cell.innerText = inTime;
				cell0.innerText = remainsNumber;
				cell1.innerText = deadId;
				cell2.innerText = deadName;
				cell3.innerHTML = "<input align='center' type='button' value='修改' onclick='return selectDeadId(this)'>";			
			}

			var count = jsonValue2[length - 1].result;

			if (count != 0 && count % pageSize == 0) {
				TotalPage = count / pageSize;
			} else if (count != 0 && count % pageSize != 0) {
				TotalPage = parseInt(count / pageSize) + 1;
			}
			var pageList = GetPaging(PageNum, TotalPage, "writePage");

			document.getElementById("divNumber").innerHTML = pageList;

		}

	}
}
function selectDeadId(obj) {
	
	
	var tr = obj.parentNode.parentNode;
	var wrongDeadId = tr.cells[2].innerText;
	
	document.form4.wrongDeadId.value = wrongDeadId;
    
}
function updateDeadId(){
	var wrongDeadId = document.form4.wrongDeadId.value;
	var latestDeadId = document.form4.latestDeadId.value;
	
	url = "wrongDeadId=" + wrongDeadId +"&";
	url = url +"latestDeadId=" +latestDeadId;
	if (confirm("是否确定修改")) {
	http_request = createHttpRequest();

	http_request.onreadystatechange = updateDeadIdBack;

	http_request.open('POST', "DeadInfoRegisterAction!updateDeadId", false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);
	}

	return false;
}
function  updateDeadIdBack(){
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result = http_request.responseText;

			var json = eval("(" + result + ")");
			alert("逝者身份证号已修改为:"+json);
	
}
	}
}
function deleteTable() { // 删除火化信息表

	for (var i = document.getElementById("remainsInfo").rows.length - 1; i >= 2; i--) {

		document.getElementById("remainsInfo").deleteRow(i);
	}
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
		var goodsCheckBoxInput1 = tableName.rows[i].cells[3].getElementsByTagName("input");
		if (goodsCheckBoxInput1[0].checked) {
			var goodsBeCostCell = tableName.rows[i].cells[1];
			var goodsBeCostInput = goodsBeCostCell.getElementsByTagName("input");
			var goodsBeCost = goodsBeCostInput[0].value;
			var goodsRealCostInput = tableName.rows[i].cells[2].getElementsByTagName("input");
			var goodsRealCost = goodsRealCostInput[0].value;
			sumBeCost=sumBeCost+Number(goodsBeCost);
			sumRealCost=sumRealCost+Number(goodsRealCost);
		}
			var goodsCheckBoxInput2 = tableName.rows[i].cells[10].getElementsByTagName("input");
		if (goodsCheckBoxInput2[0] != null) {
			if (goodsCheckBoxInput2[0].checked) {
		// alert("文本框被选中！");				
				var goodsBeCostCell = tableName.rows[i].cells[8];
				var goodsBeCostInput = goodsBeCostCell.getElementsByTagName("input");
				var goodsBeCost = goodsBeCostInput[0].value;
				var goodsRealCostInput = tableName.rows[i].cells[9].getElementsByTagName("input");
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