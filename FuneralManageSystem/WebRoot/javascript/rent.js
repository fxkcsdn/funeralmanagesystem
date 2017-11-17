/**
 * 
 */
var xmlhttp;
//连接服务器请求数据
var agentInfo;
window.onload = initialization;
function initialization() {
	getCoffin();
	getSystemTime();
	getCarNumber();
	getAgent();
	getRentNumber();
}

function getCoffin() {
	var url = "/FuneralManageSystem/getCoffin.action";
	sendRequest("post", url, null, getCoffinBack);
}

//回调函数
function getCoffinBack(result) {
	var dataOfCoffin = eval("("+result+")");
	dataOfCoffin=eval("("+dataOfCoffin+")");
	showNumberOfCoffin(dataOfCoffin);
}
//获取一条龙信息
function getAgent() {
	var url = "/FuneralManageSystem/getAgentAction!searchAgent";
	sendRequest("post", url, null, getAgentBack);
}
function getAgentBack(result) {
	var c = eval("("+result+")");
	c=eval("("+c+")");
	agentInfo = c;
	var selectNode = document.getElementById("contactName");
	selectNode.options.length = 0;
	var length = c.length;
	var optNode1 = document.createElement("option");
	optNode1.innerText = "请选择";
	selectNode.appendChild(optNode1);
	for (var i = 0; i < length; i++) {
		var optNode = document.createElement("option");
		optNode.innerText = c[i].agentName;
		selectNode.appendChild(optNode);
	}
}
function linkContactPhone() {
	var d = document.getElementById("contactName").options[document
			.getElementById("contactName").selectedIndex].text;
	var contactNum;
	for (var i = 0; i < agentInfo.length; i++) {
		if (agentInfo[i].agentName == d) {
			contactNum = agentInfo[i].agentMobile;
			document.getElementById("contactPhone").value = contactNum;
		}
	}
}
function getSystemTime() {
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, null, getSystemTimeBack);
}

//回调函数
function getSystemTimeBack(result) {
	var dataOfSystemTime = eval("("+result+")");
	dataOfSystemTime = eval("("+dataOfSystemTime+")");
	showTimeOfSystem(dataOfSystemTime);
}

function getCarNumber() {
	var url = "/FuneralManageSystem/getCarNumber.action";
	sendRequest("post", url, null, getCarNumberBack);
}

//回调函数
function getCarNumberBack(result) {
	var dataOfCarNumber=eval("("+result+")");
	dataOfCarNumber=eval("("+dataOfCarNumber+")");
	showCarNumber(dataOfCarNumber);	
}

function getRentNumber() {
	var url = "/FuneralManageSystem/getRentNumber.action";
	sendRequest("post", url, null, getRentNumberBack);
}

//回调函数
function getRentNumberBack(result) {	
	var dataOfRentNumber = eval("("+result+")");
	dataOfRentNumber = eval("("+dataOfRentNumber+")");
	document.getElementById("rentNumberOfCoffin").value = dataOfRentNumber.rentNumber;
}

function saveRentCoffin(contactName, contactPhone, numberOfCoffin, address,
		startTime, rentNumber, carCost, carNumber, bInternalCar, carRealCost) { 
	var url = "/FuneralManageSystem/saveRentCoffin.action?contactName="
			+ contactName + "&&contactMobile=" + contactPhone + "&&startTime="
			+ startTime + "&&coffinNumber=" + numberOfCoffin + "&&address="
			+ address + "&&rentNumber=" + rentNumber + "&&carCost=" + carCost
			+ "&&carRealCost=" + carRealCost + "&&carNumber=" + carNumber
			+ "&&bInternalCar=" + bInternalCar;
	sendRequest("post", url, null, saveRentCoffinBack);
}

//回调函数
function saveRentCoffinBack(result) {	
	var dataOfCoffin =eval("("+result+")");
	dataOfCoffin =eval("("+dataOfCoffin+")");
	if (dataOfCoffin.result == "yes") {
		alert("保存成功!");
		document.getElementById("btnPrint").disabled = false; //释放打印按钮
		document.getElementById("save").disabled = true;
	} else {
		alert("保存失败!");
	}
}

function change() {
	document.getElementById("save").style.display = 'none';
	document.getElementById("btnPrint").style.display = 'block';
	document.getElementById("contactName").setAttribute("disabled", "true");
	document.getElementById("contactPhone").setAttribute("disabled", "true");
	document.getElementById("address").setAttribute("disabled", "true");
	document.getElementById("numberOfCoffin").setAttribute("disabled", "true");
	document.getElementById("startTime").setAttribute("disabled", "true");
	document.getElementById("coffin").style.display = 'none';
	// document.getElementById("carNumber").disabled = 'true';
	document.getElementById("carNumber").setAttribute("disabled", "true");
	document.getElementById("carCost").setAttribute("disabled", "true");
	document.getElementById("carRealCost").setAttribute("disabled", "true");
	document.getElementById("driverName").setAttribute("disabled", "true");
	document.getElementById("driverPhone").setAttribute("disabled", "true");
	//       document.getElementById("bInternalCar").setAttribute("disabled","true");

}

function chooseNumberOfCoffin(coffinNumber, bAvailable) {
	if (bAvailable == 0) {
		alert("该水晶棺不可用，请重新选择！");
	} else {
		document.getElementById("numberOfCoffin").value = coffinNumber;
	}
}

function showNumberOfCoffin(dataOfCoffin) {
	var dataLength = dataOfCoffin.length;
	var p = document.getElementById("coffin");
	for (var i = 0; i < dataLength; ++i) {
		var image = document.createElement("img");
		image.setAttribute("id", dataOfCoffin[i].coffinNumber);
		if (dataOfCoffin[i].bAvailable == 1) {
			image.setAttribute("src", "Images/green.png");
		} else {
			image.setAttribute("src", "Images/red.png");
		}
		image.setAttribute("alt", dataOfCoffin[i].bAvailable);
		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
		image.setAttribute("onclick", "chooseNumberOfCoffin("
				+ dataOfCoffin[i].coffinNumber + ','
				+ dataOfCoffin[i].bAvailable + ")");
		p.appendChild(image);
		p.innerHTML += "<strong>"
				+ (Number(dataOfCoffin[i].coffinNumber) > 9 ? dataOfCoffin[i].coffinNumber
						: "0" + dataOfCoffin[i].coffinNumber) + "</strong>";
		p.innerHTML += "&nbsp;&nbsp;";
		if ((i + 1) % 5 == 0) {
			p.innerHTML += "<br>";
		}

	}
}

function showTimeOfSystem(dataOfSystemTime) {
	document.getElementById("startTime").value = dataOfSystemTime.startTime;
}

function showCarNumber(dataOfCarNumber) {
	var dataLength = dataOfCarNumber.length;
	var sel = document.getElementById("carNumber");
	var js;
	sel.options.add(new Option("", ""));
	for (var i = 0; i < dataLength; ++i) {
		js = "";
		js = "{carNumber:\"" + dataOfCarNumber[i].carNumber
				+ "\",driverName:\"" + dataOfCarNumber[i].driverName
				+ "\",bInternal:\"" + dataOfCarNumber[i].bInternal
				+ "\",driverMoblie:\"" + dataOfCarNumber[i].driverMobile
				+ "\"}";
		//sel.options.add(new Option(dataOfCarNumber[i].carNumber,dataOfCarNumber[i].carNumber));
		sel.options.add(new Option(dataOfCarNumber[i].carNumber, js));
	}
}

function driverDetailInfo() {
	var info = document.getElementById("carNumber").value;
	if (info != "") {
		info = JSON.stringify(info);
		info = eval("(" + eval("(" + info + ")") + ")");
		document.getElementById("driverName").value = (info.driverName == "null") ? ""
				: info.driverName;
		document.getElementById("driverPhone").value = (info.driverMoblie == "null") ? ""
				: info.driverMoblie;     
	} else {
		document.getElementById("driverName").value = "";
		document.getElementById("driverPhone").value = "";
	}
}

function saveInformationOfCoffin() {
	var contactName = document.getElementById("contactName").value;
	var contactPhone = document.getElementById("contactPhone").value;
	var numberOfCoffin = document.getElementById("numberOfCoffin").value;
	var address = document.getElementById("address").value;
	var startTime = document.getElementById("startTime").value;
	var carCost = document.getElementById("carCost").value;
	var carRealCost = document.getElementById("carRealCost").value;
	var bInternalCar = document.getElementById("bInternalCar").options[document
			.getElementById("bInternalCar").selectedIndex].text;
	var carNumber = "";

	if (contactName == "请选择") {
		alert("请输入联系人姓名！");
		document.getElementById("contactName").focus();
		return;
	}
	if (contactPhone == null || contactPhone == "") {
		alert("请输入联系人手机！");
		return;
	} else {
		var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
		if (!reg.test(contactPhone)) {
			alert("联系人手机输入有误，请重新输入!");
			document.getElementById("contactPhone").value = "";
			return;
		}
	}
	if (numberOfCoffin == null || numberOfCoffin == "") {
		alert("请输入水晶棺编号！");
		return;
	}
	if (address == null || address == "") {
		alert("请输入送棺地址！");
		return;
	}
	if (startTime == null || startTime == "") {
		alert("请输入送棺时间！");
		return;
	}
	if (carCost == null || carCost == "") {
		alert("请输入用车应收费用！");
		return;
	} else {
		var reg = /^[1-9]\d*$/;
		if (!reg.test(carCost)) {
			alert("用车应收费用输入有误，请重新输入!");
			document.getElementById("carCost").value = "";
			return;
		}
	}
	if (carRealCost == null || carRealCost == "") {
		alert("请输入用车实收费用！");
		return;
	} else {
		var reg = /^[1-9]\d*$/;
		if (!reg.test(carRealCost)) {
			alert("用车实收费用输入有误，请重新输入!");
			document.getElementById("carRealCost").value = carCost;
			return;
		} else if (Number(carRealCost) > Number(carCost)) {
			alert("用车实收费用超出用车应收费用，请重新输入!");
			document.getElementById("carRealCost").value = carCost;
			return;
		}
	}
	if (bInternalCar == "请选择") {
		alert("请选择是否为本馆车辆！");
		document.getElementById("bInternalCar").focus();
		return false;
	}
	var r = confirm("是否确认保存?");
	if (r == false) {
		return;
	}

	
	var rentNumber = document.getElementById("rentNumberOfCoffin").value;
	document.getElementById("saveContactName").value = document
			.getElementById("contactName").value;
	document.getElementById("saveContactPhone").value = document
			.getElementById("contactPhone").value;
	document.getElementById("saveNumberOfCoffin").value = document
			.getElementById("numberOfCoffin").value;
	document.getElementById("saveAddress").value = document
			.getElementById("address").value;
	document.getElementById("saveStartTime").value = document
			.getElementById("startTime").value;
	document.getElementById("saveDriverName").value = document
			.getElementById("driverName").value;
	document.getElementById("saveDriverPhone").value = document
			.getElementById("driverPhone").value;
	document.getElementById("saveCarCost").value = document
			.getElementById("carCost").value;
	document.getElementById("saveCarRealCost").value = document
			.getElementById("carRealCost").value;
	document.getElementById("saveBInternalCar").value = document
			.getElementById("bInternalCar").value;

	var info = document.getElementById("carNumber");
	document.getElementById("saveCarNumber").value = info.selectedIndex;
	info = document.getElementById("carNumber").value;
	if (info != "") {
		info = JSON.stringify(info);
		info = eval("(" + eval("(" + info + ")") + ")");
		carNumber = info.carNumber;
	}

	//document.getElementById("saveCarNumber").value = document.getElementById("carNumber").value;        

	saveRentCoffin(contactName, contactPhone, numberOfCoffin, address,
			startTime, rentNumber, carCost, carNumber, bInternalCar,
			carRealCost);
	
}

function updateCarRealCost() {
	document.getElementById("carRealCost").value = document
			.getElementById("carCost").value;
}
function printRentBill() //打印
{
	var carNum = "";
	var driverName = document.getElementById("driverName").value; //司机姓名
	var startTime = document.getElementById("startTime").value; //送棺时间
	var address = document.getElementById("address").value; //地址
	var coffinNum = document.getElementById("numberOfCoffin").value; //水晶棺编号
	var contactName = document.getElementById("contactName").value; //联系人姓名
	var contactNum = document.getElementById("contactPhone").value; //联系人号码
	var info = document.getElementById("carNumber");
	document.getElementById("saveCarNumber").value = info.selectedIndex;
	info = document.getElementById("carNumber").value;
	if (info != "") {
		info = JSON.stringify(info);
		info = eval("(" + eval("(" + info + ")") + ")");
		carNum = info.carNumber;
	}
	var url = 'printRentBill.html?driverName=' + driverName + '&startTime='
			+ startTime + '&address=' + address + '&coffinNum=' + coffinNum
			+ '&contactName=' + contactName + '&contactNum=' + contactNum
			+ '&carNum=' + carNum;
	var URL = encodeURI(url);
	window.open(URL);
	location.reload(); //刷新界面
}