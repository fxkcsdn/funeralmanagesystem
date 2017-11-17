/**
 * 修改采购单界面对应的脚本
 */
var goodsNameCopy = null;// 全局变量，用以保存品名

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
window.parent.document.getElementById("TabbedPanels1").children[0].children[2].onclick = function()
{
  var type = window.parent.document.getElementById("typeStorage").value;// 获取父页面type
  var purchaseNumber = window.parent.document.getElementById("purchaseNumberStorage").value;// 采购编号
  // 将父窗口的type和采购编号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("purchaseNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && purchaseNumber == "")
  {
    alert("请先选择一个采购单！");
    // 重新加载页面
    window.parent.location.reload("purchaseManage.jsp");
  }
  else
  {
	  document.getElementById("purchaseNumber").value = purchaseNumber;
	  // 获取所有厂家名称
	  getSuppliers();
	  // 获取采购单主信息
	  getPurchaseMaster(purchaseNumber);
	  // 获取采购单明细信息
	  getPurchaseDetailsByNum(purchaseNumber);
  }
};

/**
 * 获取厂家信息
 */
function getSuppliers()
{
	var data = "";       
    var url = "/FuneralManageSystem/updatePurchase!getSuppliers";
    sendRequest("post", url, data, getSuppliersResult)
}

/**
 * 获取厂家名称数据
 */
function getSuppliersResult(result)
{
	var supplierName = document.getElementById("supplierName");// 厂家下拉框
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有厂家
	for (var i = 0; i < data.length; i++)
	{
		supplierName.options.add(new Option(data[i].supplierName, data[i].supplierName));
	}
}

/**
 * 请求采购单主信息
 */
var getPurchaseMaster = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/updatePurchase!getPurchaseMaster";
	sendRequest("post", url, data, getPurchaseMasterBack);
};

/**
 * 获取采购单主信息
 */
var getPurchaseMasterBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	var supplierName = document.getElementById("supplierName");// 厂家
	// 遍历所有采购单主信息
	for (var i = 0; i < data.length; i++)
	{
		// 遍历所有厂家
		for (var j = 1; j < supplierName.options.length; j++)
		{
			if (supplierName.options[j].value == data[i].supplierName)
			{
				supplierName.options[j].selected = true;
				break;
			}
		}
		document.getElementById("operator").value = data[i].operator;
		document.getElementById("createDate").value = data[i].createDate.substring(0, data[i].createDate.length - 5);
		document.getElementById("memo").value = data[i].memo;
	}
};

/**
 * 请求采购单明细信息
 */
var getPurchaseDetailsByNum = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/updatePurchase!getPurchaseDetailsByNum";
	sendRequest("post", url, data, getPurchaseDetailsBack);
};

/**
 * 获取采购单明细信息
 */
var getPurchaseDetailsBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	// 遍历所有明细信息
	for (var i = 0; i < data.length; i++)
	{
		var table = document.getElementById("goodsInfo");// 采购单明细表格
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		var index = table.rows.length;
		var str = "<select id='goodsType" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getGoodsNames(this)'><option value='请选择'>请选择</option></select>";
		td1.align = "center";
		td1.innerHTML = str;
		var str1 = "<select id='goodsName" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getUnit(this)'><option value='请选择'>请选择</option></select>";
		td2.align = "center";
		td2.innerHTML = str1;
		var str2 = "<input id='unit" + (parseInt(index) - 1) + "' type='text' style='width:120px;' disabled='disabled'/>";
		td3.align = "center";
		td3.innerHTML = str2;
		var str3 = "<input id='amount" + (parseInt(index) - 1) + "' type='text' style='width:120px;' onchange='checkAmount(this)' required='required'/>";
		td4.align = "center";
		td4.innerHTML = str3;
		var str4 = "<input id='buyPrice" + (parseInt(index) - 1) + "' type='text' style='width:120px;' onchange='checkPrice(this)'/>";
		td5.align = "center";
		td5.innerHTML = str4;
		td6.align = "center";
		td6.innerHTML = "<a href='javascript:void(0)' onclick='deleteRow(this)' color='blue'>删除</a>";
		// 初始化新添加行的商品种类下拉框
		getGoodsTypes();
		// 初始化该行商品的信息
		initGoodsInfo(index, data[i]);
	}
};

/**
 * 点击“添加商品”时，增加一行
 */
function addRow()
{
	var table = document.getElementById("goodsInfo");// 表格
	// 插入一行
	var tr = table.insertRow();
	var td1 = tr.insertCell();
	var td2 = tr.insertCell();
	var td3 = tr.insertCell();
	var td4 = tr.insertCell();
	var td5 = tr.insertCell();
	var td6 = tr.insertCell();
	var index = table.rows.length;
	var str = "<select id='goodsType" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getGoodsNames(this)'><option value='请选择'>请选择</option></select>";
	td1.align = "center";
	td1.innerHTML = str;
	var str1 = "<select id='goodsName" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getUnit(this)'><option value='请选择'>请选择</option></select>";
	td2.align = "center";
	td2.innerHTML = str1;
	var str2 = "<input id='unit" + (parseInt(index) - 1) + "' type='text' style='width:120px;' disabled='disabled'/>";
	td3.align = "center";
	td3.innerHTML = str2;
	var str3 = "<input id='amount" + (parseInt(index) - 1) + "' type='text' style='width:120px;' onchange='checkAmount(this)' required='required'/>";
	td4.align = "center";
	td4.innerHTML = str3;
	var str4 = "<input id='buyPrice" + (parseInt(index) - 1) + "' type='text' style='width:120px;' onchange='checkPrice(this)'/>";
	td5.align = "center";
	td5.innerHTML = str4;
	td6.align = "center";
	td6.innerHTML = "<a href='javascript:void(0)' onclick='deleteRow(this)' color='blue'>删除</a>";
	// 初始化新添加行的商品种类下拉框
	getGoodsTypes();
}

/**
 * 点击“删除”时，删除当前行
 */
function deleteRow(obj)
{
	var table = document.getElementById("goodsInfo");// 获取表格
	var tr = obj.parentNode.parentNode;// 获取当前行
	// 遍历表格
	for (var i = 1; i < table.rows.length; i++)
	{
		// 找到当前行并删除
		if (table.rows[i] == tr) 
		{
			table.deleteRow(i);
			break;
		}
	}
	// 遍历表格为每一行元素重新赋id，以保证唯一性
	for (var i = 1; i < table.rows.length; i++)
	{
		table.rows[i].cells[0].getElementsByTagName("select")[0].id = ("goodsType" + parseInt(i));
		table.rows[i].cells[1].getElementsByTagName("select")[0].id = ("goodsName" + parseInt(i));
		table.rows[i].cells[2].getElementsByTagName("input")[0].id = ("unit" + parseInt(i));
		table.rows[i].cells[3].getElementsByTagName("input")[0].id = ("amount" + parseInt(i));
		table.rows[i].cells[4].getElementsByTagName("input")[0].id = ("buyPrice" + parseInt(i));
	}
}

/**
 * 初始化商品种类下拉框
 */
function getGoodsTypes()
{
	var data = "";
	var url = "/FuneralManageSystem/updatePurchase!getGoodsTypes";
	sendRequest("post", url, data, getGoodsTypesResult);
}

/**
 * 获取商品种类
 */
function getGoodsTypesResult(result)
{
	var table = document.getElementById("goodsInfo");// 获取表格
	var index = table.rows.length;// 表格行数
	var goodsType = document.getElementById("goodsType" + (parseInt(index) - 1));// 商品种类下拉框
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有的商品种类
	for (var i = 0; i < data.length; i++)
	{
		goodsType.options.add(new Option(data[i].goodsType, data[i].goodsType));
	}
}

/**
 * 获取品名
 */
function getGoodsNames(obj)
{
	var goodsName = obj.parentNode.nextElementSibling.getElementsByTagName("select")[0];// 品名下拉框
	var unit = goodsName.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	// 为全局变量赋值
	goodsNameCopy = goodsName;
	unit.value = "";
	// 如果商品种类为“请选择”
	if (obj.value == "请选择")
	{
		goodsName.innerHTML = "<option value='请选择'>请选择</option>";
	}
	else
	{
		var data = "goodsType=" + obj.value;
		var url = "/FuneralManageSystem/updatePurchase!getGoodsNames";
		sendRequest("post", url, data, getGoodsNamesResult);
	}
}

/**
 * 获取品名数据
 */
function getGoodsNamesResult(result)
{
	var goodsName = goodsNameCopy;// 获取品名
	// 先清空品名文本框
	goodsName.innerHTML = "<option value='请选择'>请选择</option>";
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有品名
	for (var i = 0; i < data.length; i++)
	{
		goodsName.options.add(new Option(data[i].goodsName, data[i].goodsName));
	}
}

/**
 * 获取单位
 */
function getUnit(obj)
{
	var goodsName = obj;// 品名
	var goodsType = obj.parentNode.previousElementSibling.getElementsByTagName("select")[0];// 商品种类
	var unit = obj.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	// 保存品名
	goodsNameCopy = goodsName;
	// 如果商品种类和品名有一个没有选择，则清空单位文本框
	if (goodsType.value == "请选择" || goodsName.value == "请选择")
	{
		unit.value = "";
	}
	else
	{
		var data = "goodsType=" + goodsType.value + "&goodsName=" + obj.value;
		var url = "/FuneralManageSystem/updatePurchase!getUnit";
		sendRequest("post", url, data, getUnitResult);
	}
}

/**
 * 获取单位数据
 */
function getUnitResult(result)
{
	var unit = goodsNameCopy.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 获取单位
	unit.value = data[0].unit;
}

/**
 * 初始化该行的商品信息
 */
var initGoodsInfo = function(index, data)
{
	var goodsType = document.getElementById("goodsType" + (parseInt(index) - 1));// 商品种类
	var goodsName = document.getElementById("goodsName" + (parseInt(index) - 1));// 商品名称
	var unit = document.getElementById("unit" + (parseInt(index) - 1));// 单位
	var amount = document.getElementById("amount" + (parseInt(index) - 1));// 数量
	var buyPrice = document.getElementById("buyPrice" + (parseInt(index) - 1));// 入库价
	
	// 遍历商品种类
	for (var i = 0; i < goodsType.options.length; i++)
	{
		if (goodsType.options[i].value == data.goodsType)
		{
			goodsType.options[i].selected = true;
			break;
		}
	}
	// 根据商品种类获取品名
	getGoodsNames(goodsType);
	// 遍历商品名称
	for (var j = 0; j < goodsName.options.length; j++)
	{
		if (goodsName.options[j].value == data.goodsName)
		{
			goodsName.options[j].selected = true;
		}
	}
	unit.value = data.unit;
	amount.value = data.amount;
	if (data.buyPrice != "" && data.buyPrice != "null") buyPrice.value = data.buyPrice;
	else buyPrice.value = "";
};

/**
 * 检验数量是否正确
 */
function checkAmount(obj)
{
	var reg = /^[1-9]+\d*$/;// 大于0的自然数正则表达式
	// 如果数量格式不正确
	if (!reg.test(obj.value))
	{
		alert("数量格式不正确，请填写大于0的正整数！");
		obj.value = "";
	}
}

/**
 * 检验金钱格式是否正确
 */
function checkPrice(obj)
{
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;// 金钱正则表达式
	// 如果金钱格式不正确
	if (!reg.test(obj.value) && obj.value != "")
	{
		alert("金钱格式不正确，请重新填写！");
		obj.value = "";
	}
}

/**
 * 保存采购主信息和明细信息
 */
function savePurchaseInfo()
{
	var purchaseNumber = document.getElementById("purchaseNumber");// 采购编号
	var supplierName = document.getElementById("supplierName");// 厂家
	var operator = document.getElementById("operator");// 申请人
	var createDate = document.getElementById("createDate");// 创建日期
	var memo = document.getElementById("memo");// 备注
	var table = document.getElementById("goodsInfo");// 表格
	// 如果采购单信息正确
	if (checkPurchaseInfo())
	{
		if (confirm("确定提交吗？"))
		{
			var data = "purchaseNumber=" + purchaseNumber.value + "&supplierName=" + supplierName.value + "&operator=" + operator.value + "&createDate=" + createDate.value + "&memo=" + memo.value
				+ "&purchaseDetails=" + getPurchaseDetails();
			var url = "/FuneralManageSystem/updatePurchase!savePurchaseInfo";
			sendRequest("post", url, data, getSavePurchaseInfoResult);
		}
	}
	else
	{
		if (table.rows.length == 1) alert("请添加商品信息！");
		else alert("请完善采购单信息，入库价可以不填！");
	}
	return false;
}

/**
 * 检验采购单是否正确
 */
function checkPurchaseInfo()
{
	var supplierName = document.getElementById("supplierName");// 厂家
	var operator = document.getElementById("operator");// 申请人
	var createDate = document.getElementById("createDate");// 创建日期
	var table = document.getElementById("goodsInfo");// 表格
	if (supplierName.value == "请选择" || table.rows.length == 1) return false;
	// 遍历表格
	for (var i = 1; i < table.rows.length; i++)
	{
		var tr = table.rows[i];
		var goodsType = tr.cells[0].getElementsByTagName("select")[0];// 商品种类
		var goodsName = tr.cells[1].getElementsByTagName("select")[0];// 品名
		var amount = tr.cells[3].getElementsByTagName("input")[0];// 数量
		if (goodsType.value == "请选择" || goodsName.value == "请选择") return false;
	}
	return true;
}

/**
 * 获取采购商品信息
 */
function getPurchaseDetails()
{
	var json = JSON.parse("{\"data\":[]}");
	var table = document.getElementById("goodsInfo");// 表格
	// 遍历每条商品信息
	for (var i = 1; i < table.rows.length; i++)
	{
		// 创建一个对象
		var obj = new Object();
		obj.goodsType = table.rows[i].cells[0].getElementsByTagName("select")[0].value;
		obj.goodsName = table.rows[i].cells[1].getElementsByTagName("select")[0].value;
		obj.unit = table.rows[i].cells[2].getElementsByTagName("input")[0].value;
		obj.amount = table.rows[i].cells[3].getElementsByTagName("input")[0].value;
		obj.buyPrice = table.rows[i].cells[4].getElementsByTagName("input")[0].value;
		// 将这个对象装入到json中
		json.data.push(obj);
	}
	return JSON.stringify(json);
}

/**
 * 获取提交反馈结果
 */
function getSavePurchaseInfoResult(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var createTableButton = document.getElementById("createTableButton");// 生成采购单按钮
	data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
		createTableButton.disabled = "";
	}
	else alert("提交失败，请检查采购单是否正确！");
}