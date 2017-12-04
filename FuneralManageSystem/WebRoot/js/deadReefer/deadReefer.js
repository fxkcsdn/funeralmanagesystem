/**
 * 
 */

function submitForm()
{
	var reeferNo =document.getElementById("reeferNo").value;
	if(reeferNo==null||reeferNo==""){
		alert("水晶棺号不能为空！");
		return false;
	}
	var url = "RemainsReeferAction!addReeferServiceConsume";
	var data=$("#form1").serialize();
	data+="&reeferNo="+reeferNo;
	sendRequest("post", url, data, addReeferServiceConsumeBack);
	return false;
}

function addReeferServiceConsumeBack(data){
	var b=eval("("+data+")");
	b=eval("("+b+")");
	if(b.result=="success"){
		alert("保存成功！");
	}else if(b.result=="failure"){
		alert("保存失败！");
	}
}
window.onload = function()
{
	// 获取系统时间
	getSystemTime();
	// 获取不可用的冰柜号
	getReefer();
};
/**
 * 请求服务器可用冰柜号
 */
var getReefer = function() 
{
	var data = "";
	var url = "/FuneralManageSystem/addRemainsReefer!getReefer";
	sendRequest("post", url, data, getReeferBack);
};

/**
 * 获取可用冰柜号
 */
var getReeferBack = function(result) 
{
	var dataOfReefer = eval("(" + result + ")");
	dataOfReefer = eval("(" + dataOfReefer + ")");
	// 显示冰柜，图像表示
	showNumberOfReefer(dataOfReefer);
};

/**
 * 显示冰柜信息，图像表示
 * @param dataOfReefer 冰柜信息
 */
var showNumberOfReefer = function(dataOfReefer) 
{
	var dataLength = dataOfReefer.length;
	var p = document.getElementById("reefer");// 冰柜显示区域
	// 遍历每个冰柜
	for (var i = 0; i < dataLength; i++) 
	{
		var image = document.createElement("img");
		image.setAttribute("id", dataOfReefer[i].reeferNo);

		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
		if(dataOfReefer[i].bAvailable==1){
			image.setAttribute("src", "Images/green.png");
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "alert('该冰柜还未使用!');");
		}else{
			image.setAttribute("src", "Images/red.png");
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "chooseNumberOfReefer(" + dataOfReefer[i].reeferNo + ")");
		}	
		p.appendChild(image);
		p.innerHTML += "<strong><font size='5'>"
				+ (parseInt(dataOfReefer[i].reeferNo) > 9 ? dataOfReefer[i].reeferNo
						: "0" + dataOfReefer[i].reeferNo) + "</font></strong>";
		p.innerHTML += "&nbsp;&nbsp;";
		// 每5个图像换行
		if ((i + 1) % 4 == 0) 
		{
			p.innerHTML += "<br>";
		}
	}
};
/**
 * 单击图像，获取冰柜号
 * @param reeferN0
 */
var chooseNumberOfReefer = function(reeferNo) 
{
	document.getElementById("reeferNo").value = reeferNo;
};


var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
function queryBills() {
	var queryDate = document.getElementById("queryDate").value;
		if (queryDate == "" || queryDate == null) {
			alert("请选择查询时间！");
			document.getElementById("queryDate").focus();
			return false;
	}
	var url = "RemainsReeferAction!queryRemainsCarry";
	var data="queryDate="+queryDate;
	sendRequest("post", url, data, queryBillsback);
}

function queryBillsback(data) {
	document.getElementById("detailTab").style.display = "";
	var b = data;
	var b = eval("(" + b + ")");
	var c = eval("(" + b + ")");
	var tab = document.getElementById("detailTab");
	var len = c.length;
	var i = 0;
	while (tab.rows.length > 1) {
		for (var rows = tab.rows.length - 1; rows > 0; rows--)
			tab.deleteRow(rows);
	}
	for (i = 0; i < len; i++) {
		var row = tab.insertRow(tab.rows.length);
		var cell = row.insertCell(-1);
		cell.innerText = c[i].carryNumber;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].carNumber;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].contactName;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].address;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].carryTime;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].carBeCost;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].bInternalCar;
	}
	
}

function queryReeferRemain() {
	var queryDate = document.getElementById("queryDate2").value;
		if (queryDate == "" || queryDate == null) {
			alert("请选择查询时间！");
			document.getElementById("queryDate2").focus();
			return false;
	}
	var url = "RemainsReeferAction!queryRemainsReefer";
	var data="queryDate="+queryDate;
	sendRequest("post", url, data, queryReeferRemainBack);
}

function queryReeferRemainBack(data) {
	document.getElementById("detailTab2").style.display = "";
	var b = data;
	var b = eval("(" + b + ")");
	var c = eval("(" + b + ")");
	var tab = document.getElementById("detailTab2");
	var len = c.length;
	var i = 0;
	while (tab.rows.length > 1) {
		for (var rows = tab.rows.length - 1; rows > 0; rows--)
			tab.deleteRow(rows);
	}
	for (i = 0; i < len; i++) {
		//<th>冷藏单号</th><th>接运编号</th><th>冰柜号</th><th>逝者身份证号</th><th>到馆时间</th><th>经办人姓名</th><th>经办人手机号</th>
		var row = tab.insertRow(tab.rows.length);
		var cell = row.insertCell(-1);
		cell.innerText = c[i].reeferNumber;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].carryNumber;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].reeferNo;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].deadID;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].arrivalTime;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].contactName;
		var cell = row.insertCell(-1);
		cell.innerText = c[i].contactMobile;
	}
	
}

/**
 * 请求服务器当前时间
 */
var getSystemTime = function() 
{
	var data = "";
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, data, getSystemTimeBack);
};

/**
 * 获取服务器当前时间
 */
var getSystemTimeBack = function(result) 
{
	var dataOfSystemTime = eval("(" + result + ")");
	dataOfSystemTime = eval("(" + dataOfSystemTime + ")");
	// 显示系统当前时间
	showTimeOfSystem(dataOfSystemTime);
};

/**
 * 显示系统当前时间
 * @param dataOfSystemTime 系统当前时间
 */
var showTimeOfSystem = function(dataOfSystemTime) 
{
	document.getElementById("arrivalTime").value = dataOfSystemTime.startTime;
	document.getElementById("arrivalTime2").value = dataOfSystemTime.startTime;
	document.getElementById("arrivalTime3").value = dataOfSystemTime.startTime;
};