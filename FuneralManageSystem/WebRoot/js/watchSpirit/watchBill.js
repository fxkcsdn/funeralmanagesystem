/**
 * 
 */

window.onload = function() {
	// 获取系统时间                   
	getSystemTime();
	//获取冰柜信息
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
	document.getElementById("endTime").value = dataOfSystemTime.startTime;
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
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "alert('该别墅还未使用!');");
		} else {
			image.setAttribute("src", "Images/red.png");
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "chooseNumberOfVilla(" + dataOfVillaNumber[i].villaNumber + ")");
		}
		image.setAttribute("alt", dataOfVillaNumber[i].bAvailable);
		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
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


/**
 * 单击图像，获取冰柜号
 * @param reeferN0
 */
var chooseNumberOfVilla = function(villaName) 
{
	document.getElementById("villaName").value = villaName;
	if(villaName==null||villaName.toString().length==0)
		return false;
	var data = "villaName="+villaName;
	var url = "WatchSpiritAction!getAllBeCostOfWatchSpirit";
	sendRequest("post", url, data, getAllBeCostOfWatchSpiritBack);
};

var getAllBeCostOfWatchSpiritBack = function(result) 
{
	var allBeCostOfWatchSpirit = eval("(" + result + ")");
	allBeCostOfWatchSpirit = eval("(" + allBeCostOfWatchSpirit + ")");
	document.getElementById("startTime").value=allBeCostOfWatchSpirit.startTime;//到馆时间
	document.getElementById("carryBeCost").value = allBeCostOfWatchSpirit.carryBeCost;//接运应收
	document.getElementById("carryRealCost").value = allBeCostOfWatchSpirit.carryBeCost;//接运实收
	document.getElementById("serviceBeCost").value = allBeCostOfWatchSpirit.serviceBeCost;//服务应收
	document.getElementById("serviceRealCost").value = allBeCostOfWatchSpirit.serviceBeCost;//服务实收
	calculate();//计算冰柜应收费用,冷藏总应收费用和实际应收费用
};

function calculate()
{       
   var startTime =  document.getElementById("startTime").value;
   var endTime =  document.getElementById("endTime").value;
   var date1 = Date.parse(startTime.replace(/-/g, "/")); //begintime 为开始时间
   var date2 = Date.parse(endTime.replace(/-/g, "/"));   // endtime 为结束时间      
 
   var date3=date2-date1;  //时间差的毫秒数
   var days=Math.floor(date3/(24*3600*1000));
   var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
   var hours=Math.floor(leave1/(3600*1000));
   var leave2=leave1%(100*3600);
   var minutes1 = Math.floor(leave2/(1000*60));
   
   var days2 = days;
   var hours2 = hours;
   var minutes2 = minutes1;
   
   hours = hours + days * 24;
   var money1 = hours * 8;
   
   if (hours2 > 0 || minutes2 > 0)
   {
         days2 = days2 + 1;
   }
   var money2 = days2 * 1500;   
   
    
   document.getElementById("coffinBeCost").value = money1;
   document.getElementById("coffinRealCost").value = document.getElementById("coffinBeCost").value;
   
   document.getElementById("villaBeCost").value = money2;
   document.getElementById("villaRealCost").value = document.getElementById("villaBeCost").value;
   //计算守灵应实收
	var carryBeCost=document.getElementById("carryBeCost").value;
	var serviceBeCost=document.getElementById("serviceBeCost").value;
	if(carryBeCost==null||carryBeCost.toString().length==0)
		carryBeCost=0;
	if(serviceBeCost==null||serviceBeCost.toString().length==0)
		serviceBeCost=0;
	var allMoney=parseFloat(money1)+parseFloat(carryBeCost)+parseFloat(serviceBeCost)+parseFloat(money2);
	//计算守灵总实收
	var carryRealCost=document.getElementById("carryRealCost").value;
	var serviceRealCost=document.getElementById("serviceRealCost").value;
	if(carryRealCost==null||carryRealCost.toString().length==0)
		carryRealCost=0;
	if(serviceRealCost==null||serviceRealCost.toString().length==0)
		serviceRealCost=0;
	var allMoney2=parseFloat(money1)+parseFloat(carryRealCost)+parseFloat(serviceRealCost)+parseFloat(money2);
	
	document.getElementById("allBeCost").value = allMoney;//守灵总应收
	document.getElementById("allRealCost").value = allMoney2;//守灵总实收
       
}

function reCalculate(){
	var carryRealCost=document.getElementById("carryRealCost").value;
	var serviceRealCost=document.getElementById("serviceRealCost").value;
	var coffinRealCost=document.getElementById("coffinRealCost").value;
	var villaRealCost=document.getElementById("villaRealCost").value;
	if(carryRealCost==null||carryRealCost.toString().length==0)
		carryRealCost=0;
	if(serviceRealCost==null||serviceRealCost.toString().length==0)
		serviceRealCost=0;
	if(coffinRealCost==null||coffinRealCost.toString().length==0)
		coffinRealCost=0;
	if(villaRealCost==null||villaRealCost.toString().length==0)
		villaRealCost=0;
	var allMoney=parseFloat(coffinRealCost)+parseFloat(carryRealCost)+parseFloat(serviceRealCost)+parseFloat(villaRealCost);
	
	document.getElementById("allRealCost").value = allMoney;//守灵总实收
}

function submitForm(){
	var villaName =document.getElementById("villaName").value;
	if(villaName==null||villaName==""){
		alert("别墅号不能为空！");
		return false;
	}
	var deadID=document.getElementById("deadID").value;
	if(deadID==null||deadID==""){
		alert("身份证号不能为空！");
		return false;
	}
	var reg15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    var reg18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    if(!reg15.test(deadID) && !reg18.test(deadID)){
         alert("逝者身份证号输入有误，请重新输入!");
         document.getElementById("deadID").value = "";
         return false;               
    }
	var url = "WatchSpiritAction!watchSpiritBill";
	var data=$("#form1").serialize();
	data+="&villaName="+villaName;
	sendRequest("post", url, data, watchSpiritBillBack);
	return false;
}

//保存冷藏结算返回函数
function watchSpiritBillBack(result){
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