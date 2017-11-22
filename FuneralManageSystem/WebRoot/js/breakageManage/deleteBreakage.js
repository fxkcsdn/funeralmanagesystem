/**
 * 删除报损单界面对应的脚本
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
window.parent.document.getElementById("TabbedPanels1").children[0].children[3].onclick = function()
{
  var type = window.parent.document.getElementById("typeStorage").value;// 获取父页面type
  var breakageNumber = window.parent.document.getElementById("breakageNumberStorage").value;// 报损单号
  // 将父窗口的type和报损单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("breakageNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && breakageNumber == "")
  {
    alert("请先选择一个报损单！");
    // 重新加载页面
    window.parent.location.reload("breakageManage.jsp");
  }
  else
  {
	 document.getElementById("breakageNumber").value = breakageNumber;
	 // 请求报损单主信息
	 getBreakage(breakageNumber);
	 // 请求报损单明细信息
	 getBreakageDetails(breakageNumber);
  }
};

/**
 * 请求报损单主信息
 */
function getBreakage(breakageNumber)
{
	var data = "breakageNumber=" + breakageNumber;
	var url = "/FuneralManageSystem/deleteBreakage!getBreakage";
	sendRequest("post", url, data, getBreakageBack);
}

/**
 * 返回报损单主信息
 * @param result
 */
function getBreakageBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历报损单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("breakageDate").value = data[i].breakageDate.substring(0, data[i].breakageDate.length - 5);
		document.getElementById("warehouseName").value = data[i].warehouseName;
	}
}

/**
 * 请求报损单明细信息
 * @param breakageNumber 
 */
function getBreakageDetails(breakageNumber)
{
	var data = "breakageNumber=" + breakageNumber;
	var url = "/FuneralManageSystem/deleteBreakage!getBreakageDetails";
	sendRequest("post", url, data, getBreakageDetailsBack);
}

/**
 * 获取报损单明细信息
 * @param result
 */
function getBreakageDetailsBack(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	var table = document.getElementById("goodsInfo");// 表格
	// 遍历报损单明细信息
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
		td3.innerHTML = "<input type='text' style='width:80px;' value='" + data[i].unit + "' disabled='disabled'/>";
		td3.align = "center";
		td4.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].amount + "' disabled='disabled'/>";
		td4.align = "center";
	}
}

/**
 * 获取多条报损信息
 */
function getBreakages()
{
	var json = JSON.parse("{\"data\":[]}");
	var goodsInfo = document.getElementById("goodsInfo");// 表格
	var staffName = document.getElementById("staffName");// 业务员
	var breakageDate = document.getElementById("breakageDate");// 报损日期
	var warehouseName = document.getElementById("warehouseName");// 报损仓库
	// 遍历商品表格
	for (var i = 1; i < goodsInfo.rows.length; i++)
	{
		var object = new Object();
		object.staffName = staffName.value;
		object.breakageDate = breakageDate.value;
		object.warehouseName = warehouseName.value;
		object.goodsName = goodsInfo.rows[i].cells[1].getElementsByTagName("INPUT")[0].value;
		object.amount = goodsInfo.rows[i].cells[3].getElementsByTagName("INPUT")[0].value;
		json.data.push(object);
	}
	return JSON.stringify(json);
}

/**
 * 删除报损单
 */
function deleteBreakageInfo()
{
	var breakageNumber = document.getElementById("breakageNumber");// 报损单号
	var data = "";
	var url = "";
	if (confirm("确定删除吗？"))
	{
		data = "breakageNumber=" + breakageNumber.value + "&breakages=" + getBreakages();
		url = "/FuneralManageSystem/deleteBreakage!saveDeleteInfo";
		sendRequest("post", url, data, deleteBreakageInfoBack);
	}
	return false;
}

/**
 * 返回删除结果
 * @param result
 */
function deleteBreakageInfoBack(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("删除成功！");
		saveButton.disabled = "disabled";
	}
	else alert("删除失败，请检查报损信息是否正确！");
}