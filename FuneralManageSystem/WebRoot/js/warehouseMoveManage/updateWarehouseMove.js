/**
 * 修改调拨单界面对应的脚本
 */
var localAddr = "http://localhost:8080";// 本地ip地址
var remoteAddr = "http://172.18.23.92:8080";// 远程ip地址

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
  var warehouseMoveNumber = window.parent.document.getElementById("warehouseMoveNumberStorage").value;// 调拨单号
  // 将父窗口的type和调拨单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("warehouseMoveNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && warehouseMoveNumber == "")
  {
    alert("请先选择一个调拨单！");
    // 重新加载页面
    window.parent.location.reload("warehouseMoveManage.jsp");
  }
  else
  {
	 document.getElementById("warehouseMoveNumber").value = warehouseMoveNumber;
	 // 请求调拨单主信息
	 getWarehouseMove(warehouseMoveNumber);
	 // 请求调拨单明细信息
	 getWarehouseMoveDetails(warehouseMoveNumber);
  }
};

/**
 * 请求调拨单主信息
 */
function getWarehouseMove(warehouseMoveNumber)
{
	var data = "warehouseMoveNumber=" + warehouseMoveNumber;
	var url = "/FuneralManageSystem/updateWarehouseMove!getWarehouseMove";
	sendRequest("post", url, data, getWarehouseMoveBack);
}

/**
 * 返回调拨单主信息
 * @param result
 */
function getWarehouseMoveBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历报损单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("moveDate").value = data[i].moveDate.substring(0, data[i].moveDate.length - 5);
		document.getElementById("outWarehouse").value = data[i].outWarehouse;
		document.getElementById("inWarehouse").value = data[i].inWarehouse;
	}
}

/**
 * 请求调拨单明细信息
 */
function getWarehouseMoveDetails(warehouseMoveNumber)
{
	var data = "warehouseMoveNumber=" + warehouseMoveNumber;
	var url = "/FuneralManageSystem/updateWarehouseMove!getWarehouseMoveDetails";
	sendRequest("post", url, data, getWarehouseMoveDetailsBack);
}

/**
 * 返回调拨单明细信息
 * @param result
 */
function getWarehouseMoveDetailsBack(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	var table = document.getElementById("table");// 表格
	// 遍历调拨明细信息
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
		td4.innerHTML = "<input type='text' style='width:100px;' value='" + (parseInt(data[i].balanceNumber) + parseInt(data[i].moveAmount)) + "' disabled='disabled'/>";
		td4.align = "center";
		td5.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].moveAmount + "' required='required' onchange='checkAmount(this);'/>";
		td5.align = "center";
		td6.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].moveAmount + "' disabled='disabled'/>";
		td6.align = "center";
		td6.style.display = "none";
	}
}

/**
 * 验证日期是否填写
 */
var checkDate = function()
{
	var moveDate = document.getElementById("moveDate");// 调拨日期
	// 调拨日期为空
	if (moveDate.value == "")
	{
		alert("调拨日期不能为空！");
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
	var moveDate = document.getElementById("moveDate");// 调拨日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	// 截止到日期
	moveDate.value = time.startTime;
};

/**
 * 检验数量是否正确
 */
function checkAmount(obj)
{
	var reg = /^[1-9]+\d*$/;// 大于0的自然数正则表达式
	var balanceNumber = obj.parentNode.previousElementSibling.getElementsByTagName("input")[0];// 库存数量
	// 如果数量格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("数量格式不正确，请填写大于0的正整数！");
		obj.value = "";
	}
	else 
	{
		if (obj.value != "")
		{
			// 如果调拨数量大于库存数量
			if (parseInt(obj.value) > parseInt(balanceNumber.value))
			{
				alert("调拨数量不能大于库存数量！");
				obj.value = "";
			}
		}
	}
}

/**
 * 获取多条调拨信息
 */
function getWarehouseMoves()
{
	var json = JSON.parse("{\"data\":[]}");
	var table = document.getElementById("table");// 表格
	var staffName = document.getElementById("staffName");// 业务员
	var moveDate = document.getElementById("moveDate");// 调拨日期
	var outWarehouse = document.getElementById("outWarehouse");// 调出仓库
	var inWarehouse = document.getElementById("inWarehouse");// 调入仓库
	// 遍历调拨信息
	for (var i = 1; i < table.rows.length; i++)
	{
		var obj = new Object();
		obj.staffName = staffName.value;
		obj.moveDate = moveDate.value;
		obj.outWarehouse = outWarehouse.value;
		obj.inWarehouse = inWarehouse.value;
		obj.goodsType = table.rows[i].cells[0].getElementsByTagName("select")[0].value;
		obj.goodsName = table.rows[i].cells[1].getElementsByTagName("select")[0].value;
		obj.unit = table.rows[i].cells[2].getElementsByTagName("input")[0].value;
		obj.moveAmount = table.rows[i].cells[4].getElementsByTagName("input")[0].value;
		obj.firstMoveAmount = table.rows[i].cells[5].getElementsByTagName("input")[0].value;
		json.data.push(obj);
	}
	return JSON.stringify(json);
}