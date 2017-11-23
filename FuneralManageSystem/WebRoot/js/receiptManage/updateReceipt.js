/**
 * 修改收货单界面对应的脚本
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
  var receiptNumber = window.parent.document.getElementById("receiptNumberStorage").value;// 收货单号
  // 将父窗口的type和收货单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("receiptNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && receiptNumber == "")
  {
    alert("请先选择一个收货单！");
    // 重新加载页面
    window.parent.location.reload("receiptManage.jsp");
  }
  else
  {
	 document.getElementById("receiptNumber").value = receiptNumber;
	 // 请求收货单主信息
	 getReceipt(receiptNumber);
	 // 请求采购单详细信息
	 getPurchaseDetails();
	 // 请求收货单明细信息
	 getReceiptDetails(receiptNumber);
  }
};

/**
 * 请求收货单主信息
 */
var getReceipt = function(receiptNumber)
{
	var data = "receiptNumber=" + receiptNumber;
	var url = "/FuneralManageSystem/updateReceipt!getReceipt";
	sendRequest("post", url, data, getReceiptBack);
};

/**
 * 获取收货单主信息
 */
var getReceiptBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有收货单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("receiptDate").value = data[i].receiptDate;
		document.getElementById("purchaseNumber").value = data[i].purchaseNumber;
	}
	
};

/**
 * 请求采购明细信息
 */
var getPurchaseDetails = function()
{
	var purchaseNumber = document.getElementById("purchaseNumber");// 采购编号
	var receiptNumber = document.getElementById("receiptNumber");// 收货单号
	var data = "receiptNumber=" + receiptNumber.value + "&purchaseNumber=" + purchaseNumber.value;
	var url = "/FuneralManageSystem/updateReceipt!getPurchaseDetails";
	sendRequest("post", url, data, getPurchaseDetailsBack);
};

/**
 * 获取采购明细信息
 */
var getPurchaseDetailsBack = function(result)
{
	var detail = document.getElementById("receiptDetails");// 采购明细表
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 清空表格
	deleteTable(detail);
	// 遍历采购明细信息
	for (var i = 0; i < data.length; i++)
	{
		var tr = detail.insertRow();
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		var td7 = tr.insertCell();
		td1.innerHTML = data[i].goodsType;
		td2.innerHTML = data[i].goodsName;
		td3.innerHTML = data[i].unit;
		td4.innerHTML = data[i].amount;
		td5.innerHTML = "<input type='text' value='' style='width:100px;' onchange='checkNum(this);'/>";
		td5.align = "center";
		td6.innerHTML = data[i].notArrivedAmount;
		td7.innerHTML = "<input type='text' value='" + data[i].notArrivedAmount + "' style='width:100px;' onchange='check(this);'/>";
		td7.align = "center";
	}
};

/**
 * 请求收货单明细信息
 */
var getReceiptDetails = function(receiptNumber)
{
	var data = "receiptNumber=" + receiptNumber;
	var url = "/FuneralManageSystem/updateReceipt!getReceiptDetails";
	sendRequest("post", url, data, getReceiptDetailsBack);
};

/**
 * 获取收货单明细信息
 */
var getReceiptDetailsBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	var table = document.getElementById("receiptDetails");// 收货单明细表格
	// 遍历所有明细信息
	for (var i = 0; i < data.length; i++)
	{
		// 遍历表格
		for (var j = 1; j < table.rows.length; j++)
		{
			var goodsName = table.rows[j].cells[1].innerHTML;
			// 找到这个商品
			if (data[i].goodsName == goodsName)
			{
				// 将入库价和本次收货数量填进去
				table.rows[j].cells[4].getElementsByTagName("input")[0].value = data[i].buyPrice;
				table.rows[j].cells[6].getElementsByTagName("input")[0].value = data[i].amountIn;
				break;
			}
		}
	}
};

/**
 * 验证入库价是否符合格式
 */
var checkNum = function(obj)
{
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	// 如果金额的格式不对
	if (obj.value != "" && !reg.test(obj.value))
	{
		alert("入库价格式不正确，请重新输入！");
		// 清空入库价
		obj.value = "";
	}
};

/**
 * 验证本次收货数量是否正确
 */
var check = function(obj)
{
	var amount = obj.value;
	var notArrivedAmount = obj.parentNode.parentNode.cells[5].innerHTML;
	var reg = /^\+?[1-9]\d*$/;
	// 收货数量不符合格式
	if (amount != "" && (!reg.test(amount) || (parseInt(amount) > parseInt(notArrivedAmount))))
	{
		alert("本次收货数量错误，请重新输入！");
		obj.value = "";
	}
};

/**
 * 验证日期是否填写
 */
var checkDate = function()
{
	var receiptDate = document.getElementById("receiptDate");// 收货日期
	// 收货日期为空
	if (receiptDate.value == "")
	{
		alert("收货日期不能为空！");
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
	var receiptDate = document.getElementById("receiptDate");// 收货日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	// 截止到日期
	receiptDate.value = time.startTime.substring(0, time.startTime.length - 6);
};

/**
 * 清空表格
 */
var deleteTable = function(table)
{
	// 除第一行其它行都删除
	for (var i = table.rows.length - 1; i>=1; i--)
	{
		table.deleteRow(i);
	}
};

/**
 * 保存收货单信息
 */
var saveReceiptInfo = function()
{
	var data = "";
	var url = "";
	var receiptNumber = document.getElementById("receiptNumber");// 当前收货单号
	// 如果收货信息合理
	if (checkDetail())
	{
		if (confirm("确定提交吗？"))
		{
			data = "receiptNumber=" + receiptNumber.value + "&receipts=" + getReceipts();
			url = "/FuneralManageSystem/updateReceipt!updateReceipts";
			sendRequest("post", url, data, saveReceiptInfoBack);
		}
	}
	else alert("请完善收货单信息！");
	// 阻止表单刷新
	return false;
};

/**
 * 获取保存结果
 */
var saveReceiptInfoBack = function(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
	}
	else alert("提交失败，请检查收货信息是否正确！");
};

/**
 * 检验收货信息是否合理
 */
var checkDetail = function()
{
	var detail = document.getElementById("receiptDetails");// 表格
	// 遍历表格
	for (var i = 1; i < detail.rows.length; i++)
	{
		// 如果本次收货数量不为空且大于0且入库价不为空
		if (detail.rows[i].cells[4].getElementsByTagName("INPUT")[0].value == "" 
				&& detail.rows[i].cells[6].getElementsByTagName("INPUT")[0].value != "")
		{
			return false;
		}
	}
	return true;
};

/**
 * 获取多条收货记录
 */
var getReceipts = function()
{
	var json = JSON.parse("{\"data\":[]}");
	var detail = document.getElementById("receiptDetails");// 表格
	var purchaseNumber = document.getElementById("purchaseNumber");// 当前采购单号
	var staffName = document.getElementById("staffName");// 收货人
	var receiptDate = document.getElementById("receiptDate");// 收货日期
	var warehouse = document.getElementById("warehouse");// 仓库
	// 遍历明细表
	for (var i = 1; i < detail.rows.length; i++)
	{
		// 如果本次收货数量不为空且大于0，则保存该记录
		if (detail.rows[i].cells[6].getElementsByTagName("input")[0].value != "" 
				&& parseInt(detail.rows[i].cells[6].getElementsByTagName("input")[0].value) > 0)
		{
			var obj = new Object();// 创建一个对象
			obj.purchaseNumber = purchaseNumber.value;
			obj.staffName = staffName.value;
			obj.receiptDate = receiptDate.value;
			obj.warehouse = warehouse.value;
			obj.goodsName = detail.rows[i].cells[1].innerHTML;
			obj.buyPrice = detail.rows[i].cells[4].getElementsByTagName("input")[0].value;
			obj.amountIn = detail.rows[i].cells[6].getElementsByTagName("input")[0].value;
			// 将对象装入data关联的数组中
			json.data.push(obj);
		}
	}
	return JSON.stringify(json);
};