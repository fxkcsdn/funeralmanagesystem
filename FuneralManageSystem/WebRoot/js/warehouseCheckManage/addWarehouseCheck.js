/**
 * 新增盘点单界面对应的脚本
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
 * 初始化页面
 */
window.onload = function()
{
	// 获取系统当前时间
	getCurrentTime();
};

/**
 * 请求服务器当前时间
 */	
function getCurrentTime()
{
	var data = "";
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, data, getCurrentTimeResult);
}

/**
 * 获取系统当前时间
 */
function getCurrentTimeResult(result)
{
	var checkDate = document.getElementById("checkDate");// 盘点日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	checkDate.value = time.startTime;
}

/**
 * 盘点商品
 */
function checkGoods()
{
	var warehouseName = document.getElementById("warehouseName");// 盘点仓库
	// 如果仓库名为空
	if (warehouseName.value == "")
	{
		alert("请先填写盘点仓库！");
	}
	else
	{
		var data = "warehouseName=" + warehouseName.value;
		var url = "/FuneralManageSystem/addWarehouseCheck!getGoodsInWarehouse";
		sendRequest("post", url, data, checkGoodsBack);
	}
}

/**
 * 获取商品信息
 * @param result
 */
function checkGoodsBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	var table = document.getElementById("table");// 表格
	// 清空表格
	deleteTable();
	// 如果没有商品信息，说明该仓库没有商品
	if (data.length == 0) alert("该仓库没有商品！");
	else
	{
		// 遍历商品信息
		for (var i = 0; i < data.length; i++)
		{
			var tr = table.insertRow();
			var td1 = tr.insertCell();
			var td2 = tr.insertCell();
			var td3 = tr.insertCell();
			var td4 = tr.insertCell();
			var td5 = tr.insertCell();
			var td6 = tr.insertCell();
			var index = table.rows.length;
			td1.innerHTML = data[i].goodsType;
			td2.innerHTML = data[i].goodsName;
			td3.innerHTML = data[i].unit;
			td4.innerHTML = data[i].balanceNumber;
			td5.innerHTML = "<input type='text' id='realAmount" + (parseInt(index) - 1) + "' style='width:100px;' value='" 
				+ data[i].balanceNumber + "' required='required' onchange='checkAmount(this);'/>";
			td5.align = "center";
			td6.innerHTML = "0";
		}
	}
}

/**
 * 清空表格
 */
function deleteTable()
{
	var table = document.getElementById("table");// 表格
	// 除第一行其它行都删除
	for (var i = table.rows.length - 1; i>=1; i--)
	{
		table.deleteRow(i);
	}
}

/**
 * 验证日期是否填写
 */
var checkCheckDate = function()
{
	var date = document.getElementById("checkDate");// 盘点日期
	// 盘点日期为空
	if (date.value == "")
	{
		alert("盘点日期不能为空！");
		// 获取系统时间
		getSystemTime();
	}
};

/**
 * 请求系统当前时间
 */
var getSystemTime = function()
{
	var data = "";
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, data, getSystemTimeBack);
};

/**
 * 获取系统当前时间
 */
var getSystemTimeBack = function(result)
{
	var checkDate = document.getElementById("checkDate");// 盘点日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	// 截止到日期
	checkDate.value = time.startTime;
};

/**
 * 检验数量是否正确
 * @param obj
 */
function checkAmount(obj)
{
	var normalAmount = obj.parentNode.parentNode.cells[3];// 理论数量
	var amountDifference = obj.parentNode.parentNode.cells[5];// 盈亏数量
	var reg = /^[1-9]+\d*$/;// 大于0的自然数正则表达式
	// 如果数量格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("数量格式不正确，请填写大于0的正整数！");
		obj.value = "";
		amountDifference.innerHTML = "";
	}
	else
	{
		// 如果实际数量为空
		if (obj.value == "") amountDifference.innerHTML = "";
		// 否则，盈亏数量为实际数量减去理论数量
		else amountDifference.innerHTML = parseInt(obj.value) - parseInt(normalAmount.innerHTML);
	}
}

/**
 * 获取商品信息
 */
function getGoods()
{
	var json = JSON.parse("{\"data\":[]}");
	var table = document.getElementById("table");// 表格
	var staffName = document.getElementById("staffName");// 业务员
	var checkDate = document.getElementById("checkDate");// 盘点日期
	var warehouseName = document.getElementById("warehouseName");// 盘点仓库
	// 遍历表格
	for (var i = 1; i < table.rows.length; i++)
	{
		// 如果实际数量和理论数量不相等
		if (table.rows[i].cells[5].innerHTML != "0")
		{
			var object = new Object();
			object.staffName = staffName.value;
			object.checkDate = checkDate.value;
			object.warehouseName = warehouseName.value;
			object.goodsName = table.rows[i].cells[1].innerHTML;
			object.normalAmount = table.rows[i].cells[3].innerHTML;
			object.realAmount = table.rows[i].cells[4].getElementsByTagName("input")[0].value;
			object.amountDifference = table.rows[i].cells[5].innerHTML;
			json.data.push(object);
		}
	}
	return JSON.stringify(json);
}

/**
 * 检验盘点信息是否正确
 */
function checkWarehouseCheckInfo()
{
	var table = document.getElementById("table");// 表格
	// 如果没有添加商品信息
	if (table.rows.length == 1) return false;
	return true;
}

/**
 * 保存盘点信息
 */
function saveWarehouseCheckInfo()
{
	var data = "";
	var url = "";
	// 检验盘点信息是否正确
	if (checkWarehouseCheckInfo())
	{
		if (confirm("确定提交吗？"))
		{
			data = "goods=" + getGoods();
			url = "/FuneralManageSystem/addWarehouseCheck!addWarehouseCheck";
			sendRequest("post", url, data, saveWarehouseCheckInfoBack);
		}
	}
	else alert("请完善盘点信息！");
	return false;
}

/**
 * 获取保存结果
 * @param result
 */
function saveWarehouseCheckInfoBack(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
	}
	else alert("提交失败，请检查盘点信息是否正确！");
}