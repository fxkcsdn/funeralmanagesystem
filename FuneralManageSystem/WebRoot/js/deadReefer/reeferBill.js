/**
 * 遗体冷藏结算
 */

/**
 * 页面加载时触发该事件
 */
window.onload = function() {
	// 获取系统时间                   
	getSystemTime();
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
 * 在页面上显示当前系统时间
 */
var showTimeOfSystem = function(dataOfSystemTime) {
	document.getElementById("endTime").value = dataOfSystemTime.startTime;
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
	if(reeferNo==null||reeferNo.toString().length==0)
		return false;
	var data = "reeferNo="+reeferNo;
	var url = "RemainsReeferAction!getAllBeCostOfReeferRemains";
	sendRequest("post", url, data, getAllBeCostOfReeferRemainsBack);
};

var getAllBeCostOfReeferRemainsBack = function(result) 
{
	var allBeCostOfReeferRemains = eval("(" + result + ")");
	allBeCostOfReeferRemains = eval("(" + allBeCostOfReeferRemains + ")");
	document.getElementById("arriveTime").value=allBeCostOfReeferRemains.arriveTime;//到馆时间
	document.getElementById("sendBeCost").value = allBeCostOfReeferRemains.sendBeCost;//送运应收
	document.getElementById("sendRealCost").value = allBeCostOfReeferRemains.sendBeCost;//送运实收
	document.getElementById("carryBeCost").value = allBeCostOfReeferRemains.carryBeCost;//接运应收
	document.getElementById("carryRealCost").value = allBeCostOfReeferRemains.carryBeCost;//接运实收
	document.getElementById("serviceBeCost").value = allBeCostOfReeferRemains.serviceBeCost;//服务应收
	document.getElementById("serviceRealCost").value = allBeCostOfReeferRemains.serviceBeCost;//服务实收
	calculate();//计算冰柜应收费用,冷藏总应收费用和实际应收费用
};

//计算冰柜应收费用,冷藏总应收费用和实际应收费用
function calculate() {
	var startTime = document.getElementById("arriveTime").value;
	if(startTime==null||startTime.toString().length==0){
		alert("请先选择冰柜号！");
		return false;
	}
	var endTime = document.getElementById("endTime").value;
	var date1 = Date.parse(startTime.replace(/-/g, "/")); // begintime 为开始时间
	var date2 = Date.parse(endTime.replace(/-/g, "/")); // endtime 为结束时间
	var date3 = date2 - date1; // 时间差的毫秒数
	var days = Math.floor(date3 / (24 * 3600 * 1000));
	var leave1 = date3 % (24 * 3600 * 1000); // 计算天数后剩余的毫秒数
	var hours = Math.floor(leave1 / (3600 * 1000));
	var leave2 = leave1 % (100 * 3600);
	var minutes = Math.floor(leave2 / (1000 * 60));
	if (hours > 0 || minutes > 0) {
		days = days + 1;
	}
	var reeferBeCost=days * 120; 
	
	document.getElementById("reeferBeCost").value = reeferBeCost;//冰棺应收
	document.getElementById("reeferRealCost").value = reeferBeCost;//冰棺实收
	var sendBeCost=document.getElementById("sendBeCost").value;
	var carryBeCost=document.getElementById("carryBeCost").value;
	var serviceBeCost=document.getElementById("serviceBeCost").value;
	if(sendBeCost==null||sendBeCost.toString().length==0)
		sendBeCost=0;
	if(sendBeCost==null||sendBeCost.toString().length==0)
		sendBeCost=0;
	if(carryBeCost==null||carryBeCost.toString().length==0)
		carryBeCost=0;
	if(serviceBeCost==null||serviceBeCost.toString().length==0)
		serviceBeCost=0;
	var allMoney=parseFloat(sendBeCost)+parseFloat(carryBeCost)+parseFloat(serviceBeCost)+parseFloat(reeferBeCost);
	document.getElementById("allBeCost").value = allMoney;//冷藏总应收
	document.getElementById("allRealCost").value = allMoney;//冷藏总实收
}

/**
 * 保存冷藏结算
 * @returns {Boolean}
 */
function submitForm(){
	var reeferNo =document.getElementById("reeferNo").value;
	if(reeferNo==null||reeferNo==""){
		alert("冰棺号不能为空！");
		return false;
	}
	var deadID=document.getElementById("deadID").value;
	var reg15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    var reg18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    if(!reg15.test(deadID) && !reg18.test(deadID)){
         alert("逝者身份证号输入有误，请重新输入!");
         document.getElementById("deadID").value = "";
         return false;               
    }
	var url = "RemainsReeferAction!reeferBillOfRemains";
	var data=$("#form1").serialize();
	data+="&reeferNo="+reeferNo;
	sendRequest("post", url, data, reeferBillOfRemainsBack);
	return false;
}

//保存冷藏结算返回函数
function reeferBillOfRemainsBack(result){
	var dataOfCoffin = eval("(" + result + ")");
	if (dataOfCoffin == "success") {
		alert("保存成功!");
		// 保存按钮不可用，打印按钮可用
		document.getElementById("save").disabled = "disabled";
		document.getElementById("btnPrint").disabled = "";
	} else {
		alert("保存失败!");
	}
}

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