/**
 * 查询收货单界面对应的脚本
 */
var PageNum = 1;// 页数
var PageSize = 10;// 每页10条记录
var TotalPage = 1;// 总页数

/**
 * 创建XMLHttpRequest对象
 */
function createXMLHttpRequest()
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
}
	
/**
 * ajax提交请求
 */
function sendRequest(method, url, data, getResult)
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
}

/**
 * 页面加载时触发该事件
 */
window.onload = function()
{
	var pageNum = 1;
	// 获取第一页的收货单记录
	getReceipts(pageNum);
};

/**
 * 获取收货单数据
 * @param pageNumTemp 当前页数
 */
function getReceipts(pageNumTemp)
{
	var purchaseNumber = document.getElementById("purchaseNumber");// 采购编号
	var startTime = document.getElementById("startTime");// 起始时间
	var endTime = document.getElementById("endTime");// 终止时间
	var state = document.getElementById("state");// 状态
	PageNum = pageNumTemp;
	var data = "";
	var data1 = "";
	var url = "";
	var url1 = "";
	data = "purchaseNumber=" + purchaseNumber.value + "&startTime=" + startTime.value + "&endTime=" + endTime.value
		+ "&state=" + state.value + "&pageNum=" + PageNum + "&pageSize=" + PageSize;
	data1 = "purchaseNumber=" + purchaseNumber.value + "&startTime=" + startTime.value + "&endTime=" + endTime.value
	+ "&state=" + state.value + "&pageSize=" + PageSize;
	url = "/FuneralManageSystem/queryReceipt!getReceipts";
	url1 = "/FuneralManageSystem/queryReceipt!getPageCount";
	sendRequest("post", url, data, getReceiptsBack);
	sendRequest("post", url1, data1, getPageCountBack);
}

/**
 * 获取收货单数据结果
 * @param result 收货单记录
 */
function getReceiptsBack(result)
{
	// 清空表格
	deleteTable();
	var table = document.getElementById("table");// 表格
	var data = eval("(" + eval("(" + result + ")") + ")");
	if (data.length == 0)
	{
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		td1.setAttribute("colspan", "8");
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
			td1.innerHTML = data[i].receiptNumber;
			td2.innerHTML = data[i].purchaseNumber;
			td3.innerHTML = data[i].staffName;
			td4.innerHTML = data[i].receiptDate;
			td5.innerHTML = data[i].warehouseName;
			// 如果审核人为空
			if (data[i].audit == "")
			{
				td6.innerHTML = "<a href='javascript:void(0)' color='blue' onclick='goToAuditReceipt(this);'>审核</a>";
			}
			else
			{
				td6.innerHTML = "<span>" + data[i].audit + "&nbsp;&nbsp;<a href='javascript:void(0)' color='blue' title='取消审核' onclick='goToCancelAuditReceipt(this);'>×</a></span>";
			}
			td7.innerHTML = "<input type='button' value='修改' onclick='updateReceipt(this);'/>";
			td7.align = "center";
			td8.innerHTML = "<input type='button' value='删除' onclick='deleteReceipt(this);'/>";
			td8.align = "center";
		}
	}
}

/**
 * 获取分页结果
 * @param result
 */
function getPageCountBack(result)
{
	var page = document.getElementById("page");// 分页显示区
	TotalPage = eval("(" + eval("(" + result + ")") + ")");
	page.innerHTML = GetPaging(PageNum, TotalPage, "getReceipts");
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
 * 点击“查询”按钮触发该事件
 */
function searchReceipt()
{
	var pageNum = 1;
	// 查询当前页收货单信息
	getReceipts(pageNum);
}

/**
 * 审核收货单
 */
var goToAuditReceipt = function(obj)
{
	var receiptNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取收货单号
	// 跳转到审核界面
	window.parent.location.reload("receiptManage.jsp?type=audit&receiptNumber=" + receiptNumber);
};

/**
 * 取消收货单审核
 */
var goToCancelAuditReceipt = function(obj)
{
	var receiptNumber = obj.parentNode.parentNode.parentNode.cells[0].innerHTML;// 获取收货单号
	// 跳转到审核界面
	window.parent.location.reload("receiptManage.jsp?type=cancelAudit&receiptNumber=" + receiptNumber);
};

/**
 * 修改收货单
 */
var updateReceipt = function(obj)
{
	// 获取审核人单元格内元素
	var tag = obj.parentNode.parentNode.cells[5].children[0];
	var receiptNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取收货单号
	// 如果收货单已审核
	if (tag.tagName == "SPAN")
	{
		alert("收货单已审核，无法修改！");
	}
	// 跳转到审核界面
	else window.parent.location.reload("receiptManage.jsp?type=update&receiptNumber=" + receiptNumber);
};

/**
 * 删除收货单
 */
var deleteReceipt = function(obj)
{
	// 获取审核人单元格内元素
	var tag = obj.parentNode.parentNode.cells[5].children[0];
	var receiptNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取收货单号
	// 如果收货单已审核
	if (tag.tagName == "SPAN")
	{
		alert("收货单已审核，无法删除！");
	}
	// 跳转到审核界面
	else window.parent.location.reload("receiptManage.jsp?type=delete&receiptNumber=" + receiptNumber);
};