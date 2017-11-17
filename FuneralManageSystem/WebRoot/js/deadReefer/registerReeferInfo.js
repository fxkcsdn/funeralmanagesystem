/**
 * 登记遗体冷藏信息页面所对应的脚本文件
 */


/**
 * 创建XMLHttpRequest对象
 */
var createXMLHttpRequest = function()
{
    var XMLHttpReq = false;
    // 如果浏览器有XMLHttpRequest对象，则直接创建
    if (window.XMLHttpRequest)
    {
	  XMLHttpReq = new XMLHttpRequest();
    }
	// 如果是ie浏览器
	else if (window.ActiveXObject)
	{
		try 
		{
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			XMLHttpReq = new ActiveObject("Microsoft.XMLHTTP");
		}
	}
	return XMLHttpReq;
};
	
/**
 * ajax提交请求
 */
var sendRequest = function(method, url, data, getResult)
{
	var result = "";
	// 创建XMLHttpRequest对象
	var XMLHttpReq = createXMLHttpRequest();
	if (method == "post")
	{
		// 打开连接
		XMLHttpReq.open("post", url, false);
		XMLHttpReq.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		// 回调函数
		XMLHttpReq.onreadystatechange = function()
		{
			if(XMLHttpReq.readyState == 4)
 	 		{
 	 		 	//返回成功；
 	 			if(XMLHttpReq.status == 200)
 	 		 	{
 	 		 		result = XMLHttpReq.responseText;
 	 		 		getResult(result);
 	 		 	}
 	 		}
		}
		// 发送数据
		XMLHttpReq.send(data.toString());
	}
};

/**
 * 页面加载时，触发该事件
 */
window.onload = function()
{
	// 获取系统时间
	getSystemTime();
	// 获取可用的冰柜号
	getReefer();
};

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
	    image.setAttribute("src", "Images/green.png");
		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
		// 为每个图像的onclick事件绑定事件处理函数
		image.setAttribute("onclick", "chooseNumberOfReefer(" + dataOfReefer[i].reeferNo + ")");
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
 * 显示系统当前时间
 * @param dataOfSystemTime 系统当前时间
 */
var showTimeOfSystem = function(dataOfSystemTime) 
{
	document.getElementById("arrivalTime").value = dataOfSystemTime.startTime;
};

/**
 * 读取身份证信息
 */
var readIDCare = function()
{
	var CVR_IDCard = document.getElementById("CVR_IDCard");
	// 读取身份证
	var ret = CVR_IDCard.ReadCard();
	if (ret == "0")
	{
		// 获取逝者信息
		getDeadInfo();
	    return;
	} 
	alert("读卡错误,错误原因:" + ret);
};

/**
 * 获取逝者信息
 */
var getDeadInfo = function()
{
	var pName = CVR_IDCard.Name;// 姓名
	var pCardNo = CVR_IDCard.CardNo;// 身份证号
	var pAddress = CVR_IDCard.Address;// 地址
	var pBorn = CVR_IDCard.Born;// 出生年月日
	var pSex = CVR_IDCard.Sex;// 性别
	var nowDate = new Date();
	var currentTime = nowDate.getFullYear();
	var bornDate = pBorn.substr(0,4);
	// 计算年龄
	var pAge = currentTime - bornDate + 1;
	document.getElementById("deadName").value = pName;
	document.getElementById("deadId").value = pCardNo;
	document.getElementById("familyAddress").value = pAddress;
	// 如果是男性
	if (pSex == "1")
	{
		document.getElementById("deadGender").selectedIndex = 1;
	}
	// 女性
	else if (pSex == "0")
	{
		document.getElementById("deadGender").selectedIndex = 2;
	}
};

/**
 * 提交遗体冷藏信息
 */
var saveRemainsReefer = function(carryNumber, deadId, contactName,
		contactMobile, reeferNo, arrivalTime, familyName, familyMobile,
		staffName, deposit, memo, sendRemainsUnit, accidentAddress) 
{
	var data = "carryNumber=" + carryNumber + "&deadId=" + deadId + "&contactName=" + contactName
		+ "&contactMobile=" + contactMobile + "&reeferNo=" + reeferNo + "&arrivalTime=" + arrivalTime
		+ "&familyName=" + familyName + "&familyMobile=" + familyMobile + "&staffName=" + staffName
		+ "&deposit=" + deposit + "&memo=" + memo + "&sendRemainsUnit=" + sendRemainsUnit + "&accidentAddress=" + accidentAddress;
	var url = "/FuneralManageSystem/addRemainsReefer!addRemainsReefer";
	sendRequest("post", url, data, saveRemainsReeferBack);
};

/**
 * 获取服务器返回的保存结果
 */
var saveRemainsReeferBack = function(result) 
{
	var dataOfRemainsReefer = eval("(" + result + ")");
	if (dataOfRemainsReefer == "true") 
	{
		alert("保存成功!");
		// 设置保存按钮不可用
		document.getElementById("save").disabled = "disabled";
	} 
	else 
	{
		alert("保存失败!");
	}
};

/**
 * 表单提交时，触发该事件，提交遗体冷藏信息
 */
var saveInformationOfRefrigerationInfo = function() 
{
	var carryNumber = document.getElementById("carryNumber").value;// 接运编号
	var deadId = document.getElementById("deadId").value;// 逝者身份证号
	var contactName = document.getElementById("contactName").value;// 经办人姓名
	var contactMobile = document.getElementById("contactMobile").value;// 经办人手机
	var reeferNo = document.getElementById("reeferNo").value;// 冰柜号
	var arrivalTime = document.getElementById("arrivalTime").value;// 到馆时间
	var staffName = document.getElementById("staffName").value;// 业务人员姓名
	var familyName = document.getElementById("familyName").value;// 家属姓名
	var familyMobile = document.getElementById("familyMobile").value;// 家属电话
	var deposit = document.getElementById("deposit").value;// 押金
	var memo = document.getElementById("memo").value;// 备注
	var sendRemainsUnit = document.getElementById("sendRemainsUnit").value;// 送尸单位
	var accidentAddress = document.getElementById("accidentAddress").value;// 事故地址
	var reg;// 正则表达式

	// 如果输入身份证号，应符合身份证号约束
	if (!(deadId == null || deadId == "")) 
	{
		var reg15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		var reg18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
		if (!reg15.test(deadId) && !reg18.test(deadId)) 
		{
			alert("逝者身份证号输入有误，请重新输入!");
			// 清空身份证号文本框
			document.getElementById("deadId").value = "";
			return false;
		}
	}
	// 经办人手机号不满足约束
	if (!((reg = /^1[3|4|5|8][0-9]\d{4,8}$/).test(contactMobile))) 
	{
		alert("经办人手机输入有误，请重新输入!");
		document.getElementById("contactMobile").value = "";
		return false;
	}
	// 冰柜号不能为空
	if (reeferNo == null || reeferNo == "") {
		alert("请输入冰柜号！");
		return false;
	}
	var r = confirm("是否确认保存?如果是本馆接运，必须填写接运编号，请认真核查接运编号是否正确，若正确，请点击“确定”按钮，否则点击“取消”按钮");
	if (r == false) 
	{
		return false;
	}
	// 提交遗体冷藏信息
	saveRemainsReefer(carryNumber, deadId, contactName,
			contactMobile, reeferNo, arrivalTime, familyName, familyMobile,
			staffName, deposit, memo, sendRemainsUnit, accidentAddress);
	return false;
};