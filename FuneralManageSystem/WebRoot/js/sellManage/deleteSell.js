/**
 * 删除销售单界面对应的脚本
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
  var sellNumber = window.parent.document.getElementById("sellNumberStorage").value;// 销售单号
  // 将父窗口的type和销售单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("sellNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && sellNumber == "")
  {
    alert("请先选择一个销售单！");
    // 重新加载页面
    window.parent.location.reload("sellManage.jsp");
  }
  else
  {
	 document.getElementById("sellNumber").value = sellNumber;
	 // 请求销售单主信息
	 getSell(sellNumber);
	 // 请求销售单明细信息
	 getSellDetails(sellNumber);
  }
};

/**
 * 请求销售单主信息
 */
function getSell(sellNumber)
{
	var data = "sellNumber=" + sellNumber;
	var url = "/FuneralManageSystem/deleteSell!getSell";
	sendRequest("post", url, data, getSellBack);
}

/**
 * 返回销售单主信息
 * @param result
 */
function getSellBack(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历销售单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("sellDate").value = data[i].sellDate.substring(0, data[i].sellDate.length - 5);
		document.getElementById("warehouseName").value = data[i].warehouseName;
	}
}

/**
 * 请求销售单明细信息
 * @param sellNumber 
 */
function getSellDetails(sellNumber)
{
	var data = "sellNumber=" + sellNumber;
	var url = "/FuneralManageSystem/deleteSell!getSellDetails";
	sendRequest("post", url, data, getSellDetailsBack);
}

/**
 * 返回销售明细信息
 * @param result
 */
function getSellDetailsBack(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	var table = document.getElementById("goodsInfo");// 销售单明细表格
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
		td3.innerHTML = "<input type='text' style='width:60px;' value='" + data[i].unit + "' disabled='disabled'/>";
		td3.align = "center";
		td4.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].sellPrice + "' disabled='disabled'/>";
		td4.align = "center";
		td5.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].amount + "' disabled='disabled'/>";
		td5.align = "center";
		td6.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].sum + "' disabled='disabled'/>";
		td6.align = "center";
	}
}

/**
 * 获取多条销售信息
 */
function getSells()
{
	var json = JSON.parse("{\"data\":[]}");
	var goodsInfo = document.getElementById("goodsInfo");// 表格
	var staffName = document.getElementById("staffName");// 业务员
	var sellDate = document.getElementById("sellDate");// 销售日期
	var warehouseName = document.getElementById("warehouseName");// 销售仓库
	// 遍历商品表格
	for (var i = 1; i < goodsInfo.rows.length; i++)
	{
		var object = new Object();
		object.staffName = staffName.value;
		object.sellDate = sellDate.value;
		object.warehouseName = warehouseName.value;
		object.goodsName = goodsInfo.rows[i].cells[1].getElementsByTagName("INPUT")[0].value;
		object.amount = goodsInfo.rows[i].cells[4].getElementsByTagName("INPUT")[0].value;
		object.sellPrice = goodsInfo.rows[i].cells[3].getElementsByTagName("INPUT")[0].value;
		object.sum = goodsInfo.rows[i].cells[5].getElementsByTagName("INPUT")[0].value;
		json.data.push(object);
	}
	return JSON.stringify(json);
}

/**
 * 删除销售信息
 */
function deleteSellInfo()
{
	var sellNumber = document.getElementById("sellNumber");// 销售单号
	var data = "";
	var url = "";
	if (confirm("确定删除吗？"))
	{
		data = "sellNumber=" + sellNumber.value + "&sells=" + getSells();
		url = "/FuneralManageSystem/deleteSell!saveDeleteInfo";
		sendRequest("post", url, data, deleteSellInfoBack);
	}
	return false;
}

/**
 * 返回删除结果
 * @param result
 */
function deleteSellInfoBack(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("删除成功！");
		saveButton.disabled = "disabled";
	}
	else alert("删除失败，请检查销售信息是否正确！");
}