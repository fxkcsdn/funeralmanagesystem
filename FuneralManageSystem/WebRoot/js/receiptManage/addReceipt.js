/**
 * 新增收货单界面所对应的脚本
 */

var PageNum = 1;// 页数
var PageSize = 10;// 每页10条记录
var TotalPage = 1;// 总页数

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
	var pageNum = 1;
	// 获取厂家
	getSuppliers();
	// 获取第一页的采购单记录
	getPurchases(pageNum);
};

/**
 * 请求厂家数据
 */
var getSuppliers = function()
{
	var data = "";
	var url = "";
	data = "";
	url = "/FuneralManageSystem/addReceipt!getSuppliersInPurchases";
	sendRequest("post", url, data, getSuppliersBack);
};

/**
 * 获取厂家数据
 * @param result 厂家数据，JSON格式
 */
var getSuppliersBack = function(result)
{
	var supplierName = document.getElementById("supplierName");// 厂家
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有厂家
	for (var i = 0; i < data.length; i++)
	{
		supplierName.options.add(new Option(data[i].supplierName, data[i].supplierName));
	}
};

/**
 * 请求采购单主信息数据
 * @param pageNumTemp 当前页数
 */
var getPurchases = function(pageNumTemp)
{
	var purchaseNumber = document.getElementById("purchaseNumber");// 采购编号
	var supplierName = document.getElementById("supplierName");// 厂家
	var startTime = document.getElementById("startTime");// 起始时间
	var endTime = document.getElementById("endTime");// 终止时间
	PageNum = pageNumTemp;
	var data = "";
	var data1 = "";
	var url = "";
	var url1 = "";
	data = "purchaseNumber=" + purchaseNumber.value + "&supplierName=" + supplierName.value + "&startTime=" + startTime.value 
		+ "&endTime=" + endTime.value + "&pageNum=" + PageNum + "&pageSize=" + PageSize;
	data1 = "purchaseNumber=" + purchaseNumber.value + "&supplierName=" + supplierName.value + "&startTime=" + startTime.value 
		+ "&endTime=" + endTime.value + "&pageSize=" + PageSize;
	url = "/FuneralManageSystem/addReceipt!getPurchases";
	url1 = "/FuneralManageSystem/addReceipt!getPageCount"
	sendRequest("post", url, data, getPurchasesBack);
	sendRequest("post", url1, data1, getPageCountBack);
};

/**
 * 获取采购单主信息
 */
var getPurchasesBack = function(result)
{
	var table = document.getElementById("table");// 表格
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 清空表格
	deleteTable(table);
	if (data.length == 0)
	{
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		td1.setAttribute("colspan", "9");
		td1.style.cssText="text-align:center";
		td1.innerHTML = "0条记录！";
	}
	else
	{
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
			var td8 = tr.insertCell();
			var td9 = tr.insertCell();
			td1.innerHTML = data[i].purchaseNumber;
			td2.innerHTML = data[i].supplierName;
			td3.innerHTML = data[i].operator;
			// 精确到分钟
			td4.innerHTML = data[i].createDate.substring(0, data[i].createDate.length - 5);
			td5.innerHTML = data[i].financeAudit;
			td6.innerHTML = data[i].viceCuratorAudit;
			td7.innerHTML = data[i].curatorAudit;
			td8.innerHTML = "否";
			td9.innerHTML = "<a href='javascript:void(0)' onclick='showReceiptInfo(this);'>创建收货单</a>";
		}
	}
};

/**
 * 获取分页总页数
 */
var getPageCountBack = function(result)
{
	var page = document.getElementById("page");// 分页显示区
	TotalPage = eval("(" + eval("(" + result + ")") + ")");
	page.innerHTML = GetPaging(PageNum, TotalPage, "getPurchases");
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
 * 根据查询条件获取采购单主信息
 */
var searchPurchase = function()
{
	var pageNum = 1;
	// 查询当前页采购单主信息
	getPurchases(pageNum);
};

/**
 * 显示收货信息
 */
var showReceiptInfo = function(obj)
{
	var curPurchaseNumber = document.getElementById("curPurchaseNumber");// 当前采购单号
	var receiptInfo = document.getElementById("receiptInfo");// 收货信息区域
	var tr = obj.parentNode.parentNode;// 当前行
	// 获取采购单编号
	curPurchaseNumber.value = tr.cells[0].innerHTML;
	// 请求系统当前时间
	getSystemTime();
	// 请求采购单详细信息
	getPurchaseDetails();
	// 显示收货信息区域
	receiptInfo.style.display = "";
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
 * 请求采购明细信息
 */
var getPurchaseDetails = function()
{
	var curPurchaseNumber = document.getElementById("curPurchaseNumber");// 当前采购单号
	var data = "purchaseNumber=" + curPurchaseNumber.value;
	var url = "/FuneralManageSystem/addReceipt!getPurchaseDetails";
	sendRequest("post", url, data, getPurchaseDetailsBack);
};

/**
 * 获取采购明细信息
 */
var getPurchaseDetailsBack = function(result)
{
	var detail = document.getElementById("detail");// 采购明细表
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
		// 如果入库价为空
		if (data[i].buyPrice == "null" || data[i].buyPrice == "")
		{
			td5.innerHTML = "<input type='text' value='' style='width:100px;' onchange='checkNum(this);'/>";
		}
		else
		{
			td5.innerHTML = "<input type='text' value='" + data[i].buyPrice + "' style='width:100px;' onchange='checkNum(this);'/>";
		}
		td5.align = "center";
		td6.innerHTML = data[i].notArrivedAmount;
		td7.innerHTML = "<input type='text' value='" + data[i].notArrivedAmount + "' style='width:100px;' onchange='check(this);'/>";
		td7.align = "center";
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
 * 获取多条收货记录
 */
var getReceipts = function()
{
	var json = JSON.parse("{\"data\":[]}");
	var detail = document.getElementById("detail");// 表格
	var curPurchaseNumber = document.getElementById("curPurchaseNumber");// 当前采购单号
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
			obj.purchaseNumber = curPurchaseNumber.value;
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

/**
 * 检验收货信息是否合理
 */
var checkDetail = function()
{
	var detail = document.getElementById("detail");// 表格
	// 遍历表格
	for (var i = 1; i < detail.rows.length; i++)
	{
		// 如果本次收货数量不为空且大于0且入库价不为空
		if (detail.rows[i].cells[6].getElementsByTagName("input")[0].value != "" 
				&& parseInt(detail.rows[i].cells[6].getElementsByTagName("input")[0].value) > 0
				&& detail.rows[i].cells[4].getElementsByTagName("input")[0].value != "")
		{
			return true;
		}
	}
	return false;
};

/**
 * 保存收货单信息
 */
var saveReceiptInfo = function()
{
	var data = "";
	var url = "";
	// 如果收货信息合理
	if (checkDetail())
	{
		if (confirm("确定提交吗？"))
		{
			data = "receipts=" + getReceipts();
			url = "/FuneralManageSystem/addReceipt!addReceipts";
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