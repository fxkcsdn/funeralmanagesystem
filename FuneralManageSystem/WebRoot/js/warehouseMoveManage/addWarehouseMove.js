/**
 * 新增调拨单界面对应的脚本
 */
var goodsNameCopy = null;// 全局变量，用以保存品名
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
 * 初始化页面
 */
window.onload = function()
{
	// 获取系统当前时间
	getCurrentTime();
};

/**
 * 请求服务器当前时间
 */	
function getCurrentTime()
{
	var data = "";
	var url = "/FuneralManageSystem/getSystemTime.action";
	sendRequest("post", url, data, getCurrentTimeResult);
}

/**
 * 获取系统当前时间
 */
function getCurrentTimeResult(result)
{
	var moveDate = document.getElementById("moveDate");// 调拨日期
	var time = eval("(" + eval("(" + result + ")") + ")");
	moveDate.value = time.startTime;
}

/**
 * 点击“添加商品”时，增加一行
 */
function addRow()
{
	var table = document.getElementById("table");// 表格
	// 插入一行
	var tr = table.insertRow();
	var td1 = tr.insertCell();
	var td2 = tr.insertCell();
	var td3 = tr.insertCell();
	var td4 = tr.insertCell();
	var td5 = tr.insertCell();
	var td6 = tr.insertCell();
	var index = table.rows.length;
	var str = "<select id='goodsType" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getGoodsNames(this);'><option value='请选择'>请选择</option></select>";
	td1.align = "center";
	td1.innerHTML = str;
	var str1 = "<select id='goodsName" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getUnitAndNum(this);'><option value='请选择'>请选择</option></select>";
	td2.align = "center";
	td2.innerHTML = str1;
	var str2 = "<input id='unit" + (parseInt(index) - 1) + "' type='text' style='width:80px;' disabled='disabled'/>";
	td3.align = "center";
	td3.innerHTML = str2;
	var str3 = "<input id='balanceNumber" + (parseInt(index) - 1) + "' type='text' style='width:100px;' disabled='disabled'/>";
	td4.align = "center";
	td4.innerHTML = str3;
	var str4 = "<input id='moveAmount" + (parseInt(index) - 1) + "' type='text' style='width:100px;' required='required' onchange='checkAmount(this);'/>";
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
	var table = document.getElementById("table");// 获取表格
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
		table.rows[i].cells[3].getElementsByTagName("input")[0].id = ("balanceNumber" + parseInt(i));
		table.rows[i].cells[4].getElementsByTagName("input")[0].id = ("moveAmount" + parseInt(i));
	}
}

/**
 * 初始化商品种类下拉框
 */
function getGoodsTypes()
{
	var data = "warehouseName=" + document.getElementById("outWarehouse").value;
	var url = "/FuneralManageSystem/addWarehouseMove!getGoodsTypesInWarehouse";
	sendRequest("post", url, data, getGoodsTypesResult);
}

/**
 * 获取商品种类
 */
function getGoodsTypesResult(result)
{
	var table = document.getElementById("table");// 获取表格
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
	var balanceNumber = goodsName.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 库存数量
	// 为全局变量赋值
	goodsNameCopy = goodsName;
	unit.value = "";
	balanceNumber.value = "";
	// 如果商品种类为“请选择”
	if (obj.value == "请选择")
	{
		goodsName.innerHTML = "<option value='请选择'>请选择</option>";
	}
	else
	{
		var data = "goodsType=" + obj.value + "&warehouseName=" + document.getElementById("outWarehouse").value;
		var url = "/FuneralManageSystem/addWarehouseMove!getGoodsNamesInWarehouse";
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
 * 获取单位和库存数量
 */
function getUnitAndNum(obj)
{
	var goodsName = obj;// 品名
	var goodsType = obj.parentNode.previousElementSibling.getElementsByTagName("select")[0];// 商品种类
	var unit = obj.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	var balanceNumber = obj.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 库存数量
	// 保存品名
	goodsNameCopy = goodsName;
	// 如果商品种类和品名有一个没有选择，则清空单位文本框
	if (goodsType.value == "请选择" || goodsName.value == "请选择")
	{
		unit.value = "";
		balanceNumber.value = "";
	}
	else
	{
		var data = "goodsType=" + goodsType.value + "&goodsName=" + obj.value + "&warehouseName=" + document.getElementById("outWarehouse").value;
		var url = "/FuneralManageSystem/addWarehouseMove!getUnitAndNum";
		sendRequest("post", url, data, getUnitAndNumResult);
	}
}

/**
 * 获取单位和库存数据
 */
function getUnitAndNumResult(result)
{
	var unit = goodsNameCopy.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	var balanceNumber = goodsNameCopy.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 库存数量
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 获取单位和库存数量
	unit.value = data[0].unit;
	balanceNumber.value = data[0].balanceNumber;
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
			// 如果报损数量大于库存数量
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
		json.data.push(obj);
	}
	return JSON.stringify(json);
}

/**
 * 检查调拨信息是否正确
 */
function checkWarehouseMove()
{
	var table = document.getElementById("table");// 表格
	// 如果没有添加商品信息
	if (table.rows.length == 1) return false;
	// 遍历商品表格
	for (var i = 1; i < table.rows.length; i++)
	{
		var goodsType = table.rows[i].cells[0].getElementsByTagName("SELECT")[0];// 商品种类
		var goodsName = table.rows[i].cells[1].getElementsByTagName("SELECT")[0];// 品名
		// 如果商品种类和品名有一个没选
		if (goodsType.value == "请选择" || goodsName.value == "请选择") return false;
	}
	return true;
}

/**
 * 保存仓库调拨信息
 */
function addWarehouseMove()
{
	var data = "";
	var url = "";
	// 如果调拨信息正确
	if (checkWarehouseMove())
	{
		if (confirm("确定提交吗？"))
		{
			data = "warehouseMoves=" + getWarehouseMoves();
			url = localAddr + "/FuneralManageSystem/addWarehouseMove!addWarehouseMove";
			sendRequest("post", url, data, addWarehouseMoveBack);
		}
	}
	else alert("请完善调拨单信息！");
	return false;
}

/**
 * 获取本地保存结果
 * @param result
 */
function addWarehouseMoveBack(result)
{
	var remoteData = "";
	var url = "";
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		remoteData = "warehouseMoves=" + getWarehouseMoves();
		url = remoteAddr + "/FuneralManageSystem/addWarehouseMove!remoteUpdateWarehouseBalance";
		sendRequest("post", url, remoteData, remoteUpdateBack);
	}
	else alert("提交失败，请检查调拨信息是否正确！");
}

/**
 * 获取远程保存结果
 * @param result
 */
function remoteUpdateBack(result)
{	
	var localData = "";
	var url = "";
	var saveButton = document.getElementById("saveButton");// 提交按钮
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
	}
	else
	{
		localData = "warehouseMoves=" + getWarehouseMoves();
		url = localAddr + "/FuneralManageSystem/addWarehouseMove!resetLocalWarehouseBalance";
		sendRequest("post", url, localData, resetWarehouseBack);
	}
}

/**
 * 获取本地重置结果
 * @param result
 */
function resetWarehouseBack(result)
{
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		alert("提交失败，请重新填写调拨单！");
	}
	else alert("提交失败！\n本地库存重置出现异常，请手动删除调拨单并恢复库存量，注意数据一致性！");
}
