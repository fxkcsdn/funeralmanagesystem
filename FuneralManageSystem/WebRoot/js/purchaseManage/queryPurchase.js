/**
 * 查询采购单脚本
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
	// 获取厂家
	getSuppliers();
	// 获取第一页的采购单记录
	getPurchases(pageNum);
};

/**
 * 获取厂家
 */
function getSuppliers()
{
	var data = "";
	var url = "";
	data = "";
	url = "/FuneralManageSystem/queryPurchase!getSuppliersInPurchases";
	sendRequest("post", url, data, getSuppliersResult);
}

/**
 * 获取厂家数据结果
 * @param result 厂家数据
 */
function getSuppliersResult(result)
{
	var supplierName = document.getElementById("supplierName");// 厂家
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有厂家
	for (var i = 0; i < data.length; i++)
	{
		supplierName.options.add(new Option(data[i].supplierName, data[i].supplierName));
	}
}

/**
 * 获取采购单数据
 * @param pageNumTemp 当前页数
 */
function getPurchases(pageNumTemp)
{
	var supplierName = document.getElementById("supplierName");// 厂家
	var startTime = document.getElementById("startTime");// 起始时间
	var endTime = document.getElementById("endTime");// 终止时间
	var state = document.getElementById("state");// 状态
	PageNum = pageNumTemp;
	var data = "";
	var data1 = "";
	var url = "";
	var url1 = "";
	data = "supplierName=" + supplierName.value + "&startTime=" + startTime.value + "&endTime=" + endTime.value
		+ "&state=" + state.value + "&pageNum=" + PageNum + "&pageSize=" + PageSize;
	data1 = "supplierName=" + supplierName.value + "&startTime=" + startTime.value + "&endTime=" + endTime.value
	+ "&state=" + state.value + "&pageSize=" + PageSize;
	url = "/FuneralManageSystem/queryPurchase!getPurchases";
	url1 = "/FuneralManageSystem/queryPurchase!getPageCount"
	sendRequest("post", url, data, getPurchasesResult);
	sendRequest("post", url1, data1, getPageCountResult);
}

/**
 * 获取采购单数据结果
 * @param result 采购单记录
 */
function getPurchasesResult(result)
{
	// 清空表格
	deleteTable();
	var table = document.getElementById("table");// 表格
	var data = eval("(" + eval("(" + result + ")") + ")");
	if (data.length == 0)
	{
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		td1.setAttribute("colspan", "10");
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
			var td10 = tr.insertCell();
			td1.innerHTML = data[i].purchaseNumber;
			td2.innerHTML = data[i].supplierName;
			td3.innerHTML = data[i].operator;
			// 精确到分钟
			td4.innerHTML = data[i].createDate.substring(0, data[i].createDate.length - 5);
			// 如果财务为空
			if (data[i].financeAudit == "null" || data[i].financeAudit == "")
			{
				td5.innerHTML = "<a href='javascript:void(0)' color='blue' onclick='goToAuditPurchase(this);'>审核</a>";
			}
			else
			{
				td5.innerHTML = "<span>" + data[i].financeAudit + "&nbsp;&nbsp;<a href='javascript:void(0)' color='blue' title='财务取消审核' onclick='checkCancelFinanceAudit(this);'>×</a></span>";
			}
			// 如果分馆长为空
			if (data[i].viceCuratorAudit == "null" || data[i].viceCuratorAudit == "")
			{
				td6.innerHTML = "<a href='javascript:void(0)' color='blue' onclick='checkViceCuratorAudit(this);'>审核</a>";
			}
			else
			{
				td6.innerHTML = "<span>" + data[i].viceCuratorAudit + "&nbsp;&nbsp;<a href='javascript:void(0)' color='blue' title='分馆长取消审核' onclick='checkCancelViceCuratorAudit(this);'>×</a></span>";
			}
			// 如果馆长为空
			if (data[i].curatorAudit == "null" || data[i].curatorAudit == "")
			{
				td7.innerHTML = "<a href='javascript:void(0)' color='blue' onclick='checkCuratorAudit(this);'>审核</a>";
			}
			else
			{
				td7.innerHTML = "<span>" + data[i].curatorAudit + "&nbsp;&nbsp;<a href='javascript:void(0)' color='blue' title='馆长取消审核' onclick='checkCancelCuratorAudit(this);'>×</a></span>";
			}
			td8.innerHTML = "<input type='button' value='修改' onclick='updatePurchase(this);'/>";
			td9.innerHTML = "<input type='button' value='删除' onclick='deletePurchase(this);'/>";
			td10.innerHTML = "<input type='button' value='结案' onclick='closePurchase(this);'/>";
		}
	}
}

/**
 * 获取分页结果
 * @param result
 */
function getPageCountResult(result)
{
	var page = document.getElementById("page");// 分页显示区
	TotalPage = eval("(" + eval("(" + result + ")") + ")");
	page.innerHTML = GetPaging(PageNum, TotalPage, "getPurchases");
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
function searchPurchase()
{
	var pageNum = 1;
	// 查询当前页采购单主信息
	getPurchases(pageNum);
}

/**
 * 跳转到审核采购单界面
 */
function goToAuditPurchase(obj)
{
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 跳转到审核界面
	window.parent.location.reload("purchaseManage.jsp?type=financeAudit&purchaseNumber=" + purchaseNumber);
}

/**
 * 检验分馆长是否可以审核
 * @param obj
 */
function checkViceCuratorAudit(obj)
{
	// 获取财务审批单元格内元素
	var tag = obj.parentNode.parentNode.cells[4].children[0];
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过财务审核
	if (tag.tagName == "A")
	{
		alert("请先财务审核！");
	}
	else
	{
		// 跳转到审核界面
		window.parent.location.reload("purchaseManage.jsp?type=viceCuratorAudit&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 检验馆长是否可以审核
 */
function checkCuratorAudit(obj)
{
	// 获取分馆长审批单元格内元素
	var tag = obj.parentNode.parentNode.cells[5].children[0];
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过分馆长审核
	if (tag.tagName == "A")
	{
		alert("请先分馆长审核！");
	}
	else
	{
		// 跳转到审核界面
		window.parent.location.reload("purchaseManage.jsp?type=curatorAudit&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 修改采购单
 * @param obj
 */
function updatePurchase(obj)
{
	// 获取财务审批单元格内元素
	var tag = obj.parentNode.parentNode.cells[4].children[0];
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过财务审核
	if (tag.tagName == "SPAN")
	{
		alert("该采购单已有人审核，无法修改！");
	}
	else
	{
		// 跳转到审核界面
		window.parent.location.reload("purchaseManage.jsp?type=update&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 删除采购单
 * @param obj
 */
function deletePurchase(obj)
{
	// 获取财务审批单元格内元素
	var tag = obj.parentNode.parentNode.cells[4].children[0];
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过财务审核
	if (tag.tagName == "SPAN")
	{
		alert("该采购单已有人审核，无法删除！");
	}
	else
	{
		// 跳转到审核界面
		window.parent.location.reload("purchaseManage.jsp?type=delete&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 结案采购单
 * @param obj
 */
function closePurchase(obj)
{
	// 获取财务审批单元格内元素
	var tag = obj.parentNode.parentNode.cells[6].children[0];
	var purchaseNumber = obj.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过财务审核
	if (tag.tagName == "A")
	{
		alert("该采购单还未审核完，无法结案！");
	}
	else
	{
		// 跳转到审核界面
		window.parent.location.reload("purchaseManage.jsp?type=close&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 财务取消审核
 * @param obj
 */
function checkCancelFinanceAudit(obj)
{
	// 获取分馆长审批单元格内元素
	var tag = obj.parentNode.parentNode.parentNode.cells[5].children[0];
	var purchaseNumber = obj.parentNode.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过分馆长审核
	if (tag.tagName == "SPAN")
	{
		alert("请先取消分馆长审核！");
	}
	else
	{
		// 跳转到取消审核界面
		window.parent.location.reload("purchaseManage.jsp?type=cancelFinanceAudit&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 分馆长取消审核
 * @param obj
 */
function checkCancelViceCuratorAudit(obj)
{
	// 获取馆长审批单元格内元素
	var tag = obj.parentNode.parentNode.parentNode.cells[6].children[0];
	var purchaseNumber = obj.parentNode.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 如果单元格内是a元素，说明该采购单还没有经过分馆长审核
	if (tag.tagName == "SPAN")
	{
		alert("请先取消馆长审核！");
	}
	else
	{
		// 跳转到取消审核界面
		window.parent.location.reload("purchaseManage.jsp?type=cancelViceCuratorAudit&purchaseNumber=" + purchaseNumber);
	}
}

/**
 * 馆长取消审核
 * @param obj
 */
function checkCancelCuratorAudit(obj)
{
	var purchaseNumber = obj.parentNode.parentNode.parentNode.cells[0].innerHTML;// 获取采购编号
	// 跳转到取消审核界面
	window.parent.location.reload("purchaseManage.jsp?type=cancelCuratorAudit&purchaseNumber=" + purchaseNumber);
}