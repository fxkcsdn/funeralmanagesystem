/**
 * 修改销售单界面对应的脚本
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
	var url = "/FuneralManageSystem/updateSell!getSell";
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
	var url = "/FuneralManageSystem/updateSell!getSellDetails";
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
		var td7 = tr.insertCell();
		td1.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsType + "' disabled='disabled'/>";
		td1.align = "center";
		td2.innerHTML = "<input type='text' style='width:120px;' value='" + data[i].goodsName + "' disabled='disabled'/>";
		td2.align = "center";
		td3.innerHTML = "<input type='text' style='width:60px;' value='" + data[i].unit + "' disabled='disabled'/>";
		td3.align = "center";
		td4.innerHTML = "<input type='text' style='width:100px;' value='" + (parseInt(data[i].balanceNumber) + parseInt(data[i].amount)) + "' disabled='disabled'/>";
		td4.align = "center";
		td5.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].sellPrice + "' required='required' onchange='checkPrice(this);'/>";
		td5.align = "center";
		td6.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].amount + "' required='required' onchange='checkAmount(this);'/>";
		td6.align = "center";
		td7.innerHTML = "<input type='text' style='width:100px;' value='" + data[i].sum + "' required='required' onchange='checkSum(this);'/>";
		td7.align = "center";
	}
}

/**
 * 验证日期是否填写
 */
var checkDate = function()
{
	var sellDate = document.getElementById("sellDate");// 销售日期
	// 收货日期为空
	if (sellDate.value == "")
	{
		alert("销售日期不能为空！");
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
	var sellDate = document.getElementById("sellDate");// 销售日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	// 截止到日期
	sellDate.value = time.startTime;
};

/**
 * 检验数量是否正确
 */
function checkAmount(obj)
{
	var reg = /^[1-9]+\d*$/;// 大于0的自然数正则表达式
	var sellPrice = obj.parentNode.previousElementSibling.getElementsByTagName("input")[0];// 销售价
	var sum = obj.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 销售金额
	var balanceNumber = obj.parentNode.previousElementSibling.previousElementSibling.getElementsByTagName("input")[0];// 库存数量
	// 如果数量格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("数量格式不正确，请填写大于0的正整数！");
		obj.value = "";
	}
	else
	{
		// 如果销售价不为空
		if (sellPrice.value != "" && obj.value != "")
		{
			if (parseInt(obj.value) <= parseInt(balanceNumber.value)) sum.value = parseFloat(sellPrice.value) * parseInt(obj.value);
			else 
			{
				alert("销售数量不能大于库存数量！");
				obj.value = "";
				sum.value = "";
			}
		}
		else sum.value = "";
	}
}

/**
 * 检验金钱格式是否正确
 */
function checkPrice(obj)
{
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;// 金钱正则表达式
	var amount = obj.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 销售数量
	var sum = obj.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 销售金额
	// 如果金钱格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("金钱格式不正确，请重新填写！");
		obj.value = "";
	}
	else
	{
		// 如果销售数量不为空
		if (amount.value != "" && obj.value != "")
		{
			sum.value = parseInt(amount.value) * parseFloat(obj.value);
		}
		else sum.value = "";
	}
}

/**
 * 检验金额是否正确
 * @param obj
 */
function checkSum(obj)
{
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;// 金钱正则表达式
	var amount = obj.parentNode.previousElementSibling.getElementsByTagName("input")[0];// 销售数量
	var sellPrice = obj.parentNode.previousElementSibling.previousElementSibling.getElementsByTagName("input")[0];// 销售价
	
	// 如果金钱格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("金钱格式不正确，请重新填写！");
		// 如果销售数量和销售价不为空
		if (amount.value != "" && sellPrice.value != "") obj.value = parseInt(amount.value) * parseFloat(sellPrice.value);
		else obj.value = "";
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
		object.balanceNumber = goodsInfo.rows[i].cells[3].getElementsByTagName("INPUT")[0].value;
		object.amount = goodsInfo.rows[i].cells[5].getElementsByTagName("INPUT")[0].value;
		object.sellPrice = goodsInfo.rows[i].cells[4].getElementsByTagName("INPUT")[0].value;
		object.sum = goodsInfo.rows[i].cells[6].getElementsByTagName("INPUT")[0].value;
		json.data.push(object);
	}
	return JSON.stringify(json);
}

/**
 * 保存销售信息
 */
function saveSellInfo()
{
	var data = "";
	var url = "";
	var sellNumber = document.getElementById("sellNumber");// 销售单号
	if (confirm("确定提交吗？"))
	{
		data = "sells=" + getSells() + "&sellNumber=" + sellNumber.value;
		url = "/FuneralManageSystem/updateSell!saveSellInfo";
		sendRequest("post", url, data, saveSellInfoBack);
	}
	return false;
}

/**
 * 返回保存结果
 * @param result
 */
function saveSellInfoBack(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var printButton = document.getElementById("printButton");// 打印按钮
	var data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
		printButton.disabled = "";
	}
	else alert("提交失败，请检查销售信息是否正确！");
}