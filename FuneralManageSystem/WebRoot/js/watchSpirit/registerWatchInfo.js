
window.onload = function() {
	getSystemTime();
	getVillaNumber();
	getCoffin();
};

/**
 * 请求系统时间数据
 */
var getSystemTime = function() {
	var data = "";
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, data, getSystemTimeBack);
};

/**
 * 获取系统时间数据
 */
var getSystemTimeBack = function(result) {
	var dataOfSystemTime = eval("(" + result + ")");
	dataOfSystemTime = eval("(" + dataOfSystemTime + ")");
	// 在页面上显示获得的系统时间
	showTimeOfSystem(dataOfSystemTime);
};

/**
 * 在页面上显示当前系统时间
 */
var showTimeOfSystem = function(dataOfSystemTime) {
	document.getElementById("startTime").value = dataOfSystemTime.startTime;
};

function getVillaNumber() {	
	var data = "";
	var url = "/FuneralManageSystem/getVillaNumber.action";
	sendRequest("post", url, data, getVillaNumberBack);
}

//回调函数
function getVillaNumberBack(result) {	
	var dataOfVillaNumber = eval("(" + result + ")");
	dataOfVillaNumber = eval("(" + dataOfVillaNumber + ")");
	showVillaNumber(dataOfVillaNumber);
}

function showVillaNumber(dataOfVillaNumber) {
	
	var dataLength = dataOfVillaNumber.length;
	var p = document.getElementById("villa");
	for (var i = 0; i < dataLength; ++i) {
		var image = document.createElement("img");
		image.setAttribute("id", dataOfVillaNumber[i].villaNumber);
		if (dataOfVillaNumber[i].bAvailable == 1) {
			image.setAttribute("src", "Images/green.png");
		} else {
			image.setAttribute("src", "Images/red.png");
		}
		image.setAttribute("alt", dataOfVillaNumber[i].bAvailable);
		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
		image.setAttribute("onclick", "chooseNumberOfVilla("
				+ dataOfVillaNumber[i].villaNumber + ','
				+ dataOfVillaNumber[i].bAvailable + ")");
		p.appendChild(image);
		p.innerHTML += "<strong>"
				+ (Number(dataOfVillaNumber[i].villaNumber) > 9 ? dataOfVillaNumber[i].villaNumber
						: "0" + dataOfVillaNumber[i].villaNumber) + "</strong>";
		p.innerHTML += "&nbsp;&nbsp;";
		if ((i + 1) % 2 == 0) {
			p.innerHTML += "<br>";
		}
	}
}

function chooseNumberOfVilla(villaNumber, bAvailable) {
	if (bAvailable == 0) {
		alert("该别墅不可用，请重新选择！");
	} else {
		document.getElementById("villaNumber").value = villaNumber;
	}
}

function getCoffin() {
	
	var data = "";
	var url = "/FuneralManageSystem/getCoffin.action";
	sendRequest("post", url, data, getCoffinBack);
}

//回调函数
function getCoffinBack(result) {
	var dataOfCoffin = eval("(" + result + ")");
	dataOfCoffin = eval("(" + dataOfCoffin + ")");
	showNumberOfCoffin(dataOfCoffin);
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
		if ((i + 1) % 4 == 0) {
			p.innerHTML += "<br>";
		}
	}
}

function chooseNumberOfCoffin(coffinNumber, bAvailable) {
	if (bAvailable == 0) {
		alert("该水晶棺不可用，请重新选择！");
	} else {
		document.getElementById("coffinNumber").value = coffinNumber;
	}
}

function showTimeOfSystem(dataOfSystemTime) {
	document.getElementById("startTime").value = dataOfSystemTime.startTime;
}


function saveWatchSpirit( carryNumber, deadId, memberName,
		memberMobile, villaNumber, coffinNumber, startTime) {
	
	var data = "watchSpirit.carryNumber=" + carryNumber + "&&watchSpirit.deadID="
		+ deadId + "&&watchSpirit.memberName=" + memberName + "&&watchSpirit.memberMobile="
		+ memberMobile + "&&watchSpirit.villaName=" + villaNumber + "&&watchSpirit.coffinNumber="
		+ coffinNumber + "&&watchSpirit.startTime=" + startTime;
	var url = "WatchSpiritAction!saveWatchSpirit";
	sendRequest("post", url, data, saveWatchSpiritBack);
}

//回调函数
function saveWatchSpiritBack(result) {
	var dataOfWatchSpirit = eval("(" +result + ")");
	if (dataOfWatchSpirit == "success") {
		alert("保存成功!");
	} else {
		alert("保存失败!");
	}
}


function saveInformationOfWakeInfo() {
	var carryNumber = document.getElementById("carryNumber").value;
	var deadId = document.getElementById("deadId").value;
	var memberName = document.getElementById("memberName").value;
	var memberMobile = document.getElementById("memberMobile").value;
	var villaNumber = document.getElementById("villaNumber").value;
	var coffinNumber = document.getElementById("coffinNumber").value;
	var startTime = document.getElementById("startTime").value;

	if (villaNumber == null || villaNumber == "") {
		alert("请输入别墅号！");
		return;
	}
	if (coffinNumber == null || coffinNumber == "") {
		alert("请输入水晶棺号！");
		return;
	}
	if (deadId == null || deadId == "") {
		//alert("请输入逝者身份证号！");
		//return;
	} else {
		var reg15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		var reg18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
		if (!reg15.test(deadId) && !reg18.test(deadId)) {
			alert("逝者身份证号输入有误，请重新输入!");
			document.getElementById("deadId").value = "";
			return;
		}
	}
	if (memberName == null || memberName == "") {
		alert("请输入家属姓名！");
		return;
	}
	if (memberMobile == null || memberMobile == "") {
		alert("请输入家属手机！");
		return;
	} else {
		var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
		if (!reg.test(memberMobile)) {
			alert("家属手机输入有误，请重新输入!");
			document.getElementById("memberMobile").value = "";
			return;
		}
	}
	
	
	if (startTime == null || startTime == "") {
		alert("请输入开始时间！");
		return;
	}

	var r = confirm("是否确认保存?");
	if (r == false) {
		return;
	}
	saveWatchSpirit( carryNumber, deadId, memberName, memberMobile,
			villaNumber, coffinNumber, startTime);
}