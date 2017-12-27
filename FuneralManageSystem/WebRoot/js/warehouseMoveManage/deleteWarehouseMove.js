/**
 * 删除调拨单界面对应的脚本
 */
var localAddr = "http://localhost:8080";// 本地ip地址
var remoteAddr = "http://172.18.22.7:8080";// 远程ip地址

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
window.parent.document.getElementById("TabbedPanels1").children[0].children[3].onclick = function()
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
	var url = "/FuneralManageSystem/deleteWarehouseMove!getWarehouseMove";
	sendRequest("post", url, data, getWarehouseMoveBack);
}

/**
 * 返回调拨单主信息
 * @param result
 */
function getWarehouseMoveBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历调拨单主信息
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
	var url = "/FuneralManageSystem/deleteWarehouseMove!getWarehouseMoveDetails";
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
		td1.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsType + "' disabled='disabled'/>";
		td1.align = "center";
		td2.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsName + "' disabled='disabled'/>";
		td2.align = "center";
		td3.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].unit + "' disabled='disabled'/>";
		td3.align = "center";
		td4.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].moveAmount + "' disabled='disabled'/>";
		td4.align = "center";
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
		obj.goodsType = table.rows[i].cells[0].getElementsByTagName("input")[0].value;
		obj.goodsName = table.rows[i].cells[1].getElementsByTagName("input")[0].value;
		obj.unit = table.rows[i].cells[2].getElementsByTagName("input")[0].value;
		obj.moveAmount = table.rows[i].cells[3].getElementsByTagName("input")[0].value;
		json.data.push(obj);
	}
	return JSON.stringify(json);
}

/**
 * 删除调拨单信息
 */
function deleteWarehouseMove()
{
	var data = "";
	var url = "";
	var warehouseMoveNumber = document.getElementById("warehouseMoveNumber");
	if (confirm("确定提交吗？"))
	{
		data = "warehouseMoves=" + getWarehouseMoves();
		url = localAddr + "/FuneralManageSystem/deleteWarehouseMove!resetLocalNumber";
		sendRequest("post", url, data, resetLocalBack);
	}
	return false;
}

/**
 * 返回本地还原结果
 * @param result
 */
function resetLocalBack(result)
{
	var remoteData = "";
	var url = "";
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		remoteData = "warehouseMoves=" + getWarehouseMoves();
		url = remoteAddr + "/FuneralManageSystem/deleteWarehouseMove!resetRemoteNumber";
		sendRequest("post", url, remoteData, resetRemoteBack);
	}
	else alert("删除失败！\n若删除，请手动删除，注意保持数据一致性！");
}

/**
 * 返回远程还原结果
 * @param result
 */
function resetRemoteBack(result)
{
	var localData = "";
	var url = "";
	var warehouseMoveNumber = document.getElementById("warehouseMoveNumber");// 调拨单号
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		localData = "warehouseMoveNumber=" + warehouseMoveNumber.value;
		url = localAddr + "/FuneralManageSystem/deleteWarehouseMove!deleteWarehouseMove";
		sendRequest("post", url, localData, deleteWarehouseMoveBack);
	}
	else
	{
		localData = "warehouseMoves=" + getWarehouseMoves();
		url = localAddr + "/FuneralManageSystem/addWarehouseMove!resetLocalNumberSec";
		sendRequest("post", url, localData, resetLocalNumberSecBack);
	}
}

/**
 * 返回调拨单删除结果
 * @param result
 */
function deleteWarehouseMoveBack(result)
{
	var data = eval("(" + result + ")");
	var saveButton = document.getElementById("saveButton");// 提交按钮
	if (data == "true")
	{
		alert("删除成功！");
		saveButton.disabled = "disabled";
	}
	else
	{
		alert("删除失败！\n请手动删除调拨单！");
	}
}

/**
 * 返回第二次本地重置结果
 * @param result
 */
function resetLocalNumberSecBack(result)
{
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		alert("删除失败！\n若删除，请手动删除，注意保持数据一致性！");
	}
	else alert("删除失败！\n本地库存重置出现异常，请手动恢复库存量！\n恢复方法为：根据调拨单寻找相应仓库的调拨商品，在库存量" +
			"的基础上减去调拨数量。");
}