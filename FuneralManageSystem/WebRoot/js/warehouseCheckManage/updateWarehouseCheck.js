/**
 * 修改盘点单界面对应的脚本
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
 * 页面加载时触发该事件
 */
window.parent.document.getElementById("TabbedPanels1").children[0].children[2].onclick = function()
{
  var type = window.parent.document.getElementById("typeStorage").value;// 获取父页面type
  var warehouseCheckNumber = window.parent.document.getElementById("warehouseCheckNumberStorage").value;// 盘点单号
  // 将父窗口的type和盘点单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("warehouseCheckNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && warehouseCheckNumber == "")
  {
    alert("请先选择一个盘点单！");
    // 重新加载页面
    window.parent.location.reload("warehouseCheckManage.jsp");
  }
  else
  {
	 document.getElementById("warehouseCheckNumber").value = warehouseCheckNumber;
	 // 请求盘点单主信息
	 getWarehouseCheck(warehouseCheckNumber);
	 // 请求盘点单明细信息
	 getWarehouseCheckDetails(warehouseCheckNumber);
  }
};

/**
 * 请求盘点单主信息
 */
function getWarehouseCheck(warehouseCheckNumber)
{
	var data = "warehouseCheckNumber=" + warehouseCheckNumber;
	var url = "/FuneralManageSystem/updateWarehouseCheck!getWarehouseCheck";
	sendRequest("post", url, data, getWarehouseCheckBack);
}

/**
 * 获取盘点单主信息
 * @param result 盘点单主信息
 */
function getWarehouseCheckBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历销售单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("checkDate").value = data[i].checkDate.substring(0, data[i].checkDate.length - 5);
		document.getElementById("warehouseName").value = data[i].warehouseName;
	}
}

/**
 * 请求盘点单明细信息
 * @param warehouseCheckNumber 
 */
function getWarehouseCheckDetails(warehouseCheckNumber)
{
	var data = "warehouseCheckNumber=" + warehouseCheckNumber;
	var url = "/FuneralManageSystem/updateWarehouseCheck!getWarehouseCheckDetails";
	sendRequest("post", url, data, getWarehouseCheckDetailsBack);
}

/**
 * 获取盘点明细信息
 * @param result
 */
function getWarehouseCheckDetailsBack(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	var table = document.getElementById("goodsInfo");// 盘点单明细表格
	// 遍历明细信息
	for (var i = 0; i < data.length; i++)
	{
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		td1.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsType + "' disabled='disabled'/>";
		td1.align = "center";
		td2.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsName + "' disabled='disabled'/>";
		td2.align = "center";
		td3.innerHTML = "<input type='text' style='width:80px;' value='" + data[i].unit + "' disabled='disabled'/>";
		td3.align = "center";
		td4.innerHTML = "<input type='text' style='width:100px;' value='" + (parseInt(data[i].balanceNumber) - parseInt(data[i].amountDifference)) + "' disabled='disabled'/>";
		td4.align = "center";
		td5.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].realAmount + "' required='required' onchange='checkAmount(this);'/>";
		td5.align = "center";
		td6.innerHTML = "<input type='text' style='width:100px;' value='" + (parseInt(data[i].realAmount) - parseInt(data[i].balanceNumber) + parseInt(data[i].amountDifference)) + "' disabled='disabled'/>";
		td6.align = "center";
	}
}

/**
 * 检验日期是否正确
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
		amountDifference.getElementsByTagName("input")[0].value = "";
	}
	else
	{
		// 如果实际数量为空
		if (obj.value == "") amountDifference.getElementsByTagName("input")[0].value = "";
		// 否则，盈亏数量为实际数量减去理论数量
		else amountDifference.getElementsByTagName("input")[0].value = parseInt(obj.value) - parseInt(normalAmount.getElementsByTagName("input")[0].value);
	}
}

/**
 * 获取商品信息
 */
function getGoods()
{
	var json = JSON.parse("{\"data\":[]}");
	var table = document.getElementById("goodsInfo");// 表格
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
			object.goodsName = table.rows[i].cells[1].getElementsByTagName("input")[0].value;
			object.normalAmount = table.rows[i].cells[3].getElementsByTagName("input")[0].value;
			object.realAmount = table.rows[i].cells[4].getElementsByTagName("input")[0].value;
			object.amountDifference = table.rows[i].cells[5].getElementsByTagName("input")[0].value;
			json.data.push(object);
		}
	}
	return JSON.stringify(json);
}

/**
 * 保存盘点信息
 */
function saveWarehouseCheckInfo()
{
	var data = "";
	var url = "";
	var warehouseCheckNumber = document.getElementById("warehouseCheckNumber");// 盘点单号
	if (confirm("确定提交吗？"))
	{
		data = "goods=" + getGoods() + "&warehouseCheckNumber=" + warehouseCheckNumber.value;
		url = "/FuneralManageSystem/updateWarehouseCheck!updateWarehouseCheck";
		sendRequest("post", url, data, saveWarehouseCheckInfoBack);
	}
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