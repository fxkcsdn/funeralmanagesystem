/**
 * 登记遗体冷藏送运信息界面对应的脚本文件
 */

/**
 * 页面加载时触发该事件
 */
window.onload = function() {
	// 获取系统时间                   
	getSystemTime();
	// 获取车牌号下拉列表
	getCarNumber();
	//获取冰柜信息
	getReefer();
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
 * 请求车牌号
 */
var getCarNumber = function() {
	var data = "";
	var url = "/FuneralManageSystem/addReeferRemainsCarry!getCarNumbers";
	sendRequest("post", url, data, getCarNumberBack);
};

/**
 * 获取车牌号
 */
var getCarNumberBack = function(result) {
	var dataOfCarNumber = eval("(" + result + ")");
	dataOfCarNumber = eval("(" + dataOfCarNumber + ")");
	// 在车牌号下拉框只能怪显示车牌号       
	showCarNumber(dataOfCarNumber);
};


/**
 * 在页面上显示当前系统时间
 */
var showTimeOfSystem = function(dataOfSystemTime) {
	document.getElementById("sendTime").value = dataOfSystemTime.startTime;
};

/**
 * 在页面上显示车牌号，下拉列表显示
 */
var showCarNumber = function(dataOfCarNumber) {
	var dataLength = dataOfCarNumber.length;
	var sel = document.getElementById("carNumber");// 车牌号下拉框
	var js;
	sel.options.add(new Option("", ""));
	// 遍历车牌号
	for (var i = 0; i < dataLength; i++) {
		js = "";
		js = "{carNumber:\"" + dataOfCarNumber[i].carNumber
				+ "\",driverName:\"" + dataOfCarNumber[i].driverName
				+ "\",driverMoblie:\"" + dataOfCarNumber[i].driverMobile
				+ "\"}";
		// 设置添加的option的text为车牌号，value为车牌号、司机姓名和司机手机的JSON数据                     
		sel.options.add(new Option(dataOfCarNumber[i].carNumber, js));
	}
};

/**
 * 当改变车牌号时，调用该函数
 */
var driverDetailInfo = function() {
	// 获取车牌号、司机姓名和司机电话的JSON数据
	var info = document.getElementById("carNumber").value;
	if (info != "") {
		info = JSON.stringify(info);
		info = eval("(" + eval("(" + info + ")") + ")");
		// 根据车牌号获取司机姓名和电话         
		document.getElementById("driverName").value = (info.driverName == "null") ? ""
				: info.driverName;
		document.getElementById("driverPhone").value = (info.driverMoblie == "null") ? ""
				: info.driverMoblie;
	} else {
		document.getElementById("driverName").value = "";
		document.getElementById("driverPhone").value = "";
	}
};

/**
 * 保存接运信息
 */
var saveInformationOfRemainsSend = function() {
	var sendTime = document.getElementById("sendTime").value;// 接运时间
	var address = document.getElementById("address").value;// 接运地址
	var contactMobile = document.getElementById("contactMobile").value;// 联系人手机
	var contactName = document.getElementById("contactName").value;// 联系人姓名
	var driverInfo = document.getElementById("carNumber").value;// 司机信息，包括车牌号、司机姓名和司机电话
	var carBeCost = document.getElementById("carBeCost").value;// 预收费用
	var bInternalCar = document.getElementById("bInternalCar").value;// 是否本馆车辆，0为本馆车辆，1为非本馆车辆 
	var carNumber = "";
	var reg;// 正则表达式
	var reeferNo =document.getElementById("reeferNo").value;
	if(reeferNo==null||reeferNo==""){
		alert("水晶棺号不能为空！");
		return false;
	}
	// 联系人手机不符合格式
	if (!((reg = /^1[3|4|5|8][0-9]\d{4,8}$/).test(contactMobile))) {
		alert("联系人手机输入有误，请重新输入!");
		document.getElementById("contactMobile").value = "";
		return false;
	}
	// 预收费用格式不正确
	if (!((reg = /^[1-9]\d*$/).test(carBeCost))) {
		alert("预收费用输入有误，请重新输入!");
		document.getElementById("carBeCost").value = "";
		return false;
	}
	
	var r = confirm("是否确认保存?");
	if (r == false) {
		return false;
	}
	if (driverInfo != "") {
		driverInfo = JSON.stringify(driverInfo);
		driverInfo = eval("(" + eval("(" + driverInfo + ")") + ")");
		// 获取车牌号              
		carNumber = driverInfo.carNumber;
	}
	// 保存接运信息
	addRemainsCarry(reeferNo, sendTime, address, contactMobile,
			contactName, carNumber, carBeCost, bInternalCar);
	return false;
};

/**
 * 保存接运信息
 */
var addRemainsCarry = function(reeferNo, sendTime, address, contactMobile,
		contactName, carNumber, carBeCost, bInternalCar) {
	var data = "reeferNo="+reeferNo+"&reeferRemainsSend.contactName=" + contactName + "&reeferRemainsSend.contactMobile=" + contactMobile
			+ "&reeferRemainsSend.address=" + address + "&reeferRemainsSend.sendTime=" + sendTime + "&reeferRemainsSend.carNumber=" + carNumber
			+ "&reeferRemainsSend.carBeCost=" + carBeCost + "&reeferRemainsSend.bInternalCar=" + bInternalCar;
	var url = "RemainsReeferAction!addReeferRemainsSend";
	sendRequest("post", url, data, addRemainsCarryBack);
};

/**
 * 获取保存结果
 */
var addRemainsCarryBack = function(result) {
	var dataOfCoffin = eval("(" + result + ")");
	if (dataOfCoffin == "success") {
		alert("保存成功!");
		// 保存按钮不可用，打印按钮可用
		document.getElementById("save").disabled = "disabled";
		document.getElementById("btnPrint").disabled = "";
	} else {
		alert("保存失败!");
		// 清空接运编号文本框
		document.getElementById("carryNumber").value = "";
	}
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

/**
 * 打印派车单
 */
var printReeferCarryBill = function() {
	var width = window.screen.width;// 宽度
	var height = window.screen.height;// 高度
	var url = "/FuneralManageSystem/printReeferCarryBill.html";// 页面跳转地址
	// 打开子窗口
	window.open(url, "width=" + width + ", height=" + height
			+ ", left=0, top=0");
};