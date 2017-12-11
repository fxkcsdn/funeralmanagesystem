/**
 * 
 */
window.onload = function() {
	getSystemTime();
	getVillaNumber();
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
	document.getElementById("arrivalTime").value = dataOfSystemTime.startTime;
	document.getElementById("arrivalTime2").value = dataOfSystemTime.startTime;
	document.getElementById("arrivalTime3").value = dataOfSystemTime.startTime;
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
		if ((i + 1) % 4 == 0) {
			p.innerHTML += "<br>";
		}
	}
}

function chooseNumberOfVilla(villaNumber, bAvailable) {
	if (bAvailable == 1) {
		alert("该别墅还未使用，请重新选择！");
	} else {
		document.getElementById("villaName").value = villaNumber;
	}
}

function submitForm()
{
	var villaName =document.getElementById("villaName").value;
	if(villaName==null||villaName==""){
		alert("别墅号不能为空！");
		return false;
	}
	var url = "WatchSpiritAction!addWatchSpiritServiceConsume";
	var data=$("#form1").serialize();
	data+="&villaName="+villaName;
	sendRequest("post", url, data, addWatchServiceConsumeBack);
	return false;
}

function addWatchServiceConsumeBack(data){
	var b=eval("("+data+")");
	if(b=="success"){
		alert("保存成功！");
	}else if(b=="failure"){
		alert("保存失败！");
	}
}