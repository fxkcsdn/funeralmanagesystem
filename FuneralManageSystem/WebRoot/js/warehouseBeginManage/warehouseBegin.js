/**
 * 期初入库界面对应的脚本文件
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
	var str1 = "<select id='goodsName" + (parseInt(index) - 1) + "' style='width:120px;' onchange='getUnitAndPrice(this)'><option value='请选择'>请选择</option></select>";
	td2.align = "center";
	td2.innerHTML = str1;
	var str2 = "<input id='unit" + (parseInt(index) - 1) + "' type='text' style='width:80px;' disabled='disabled'/>";
	td3.align = "center";
	td3.innerHTML = str2;
	var str3 = "<input id='sellPrice" + (parseInt(index) - 1) + "' type='text' style='width:120px;' disabled='disabled'/>";
	td4.align = "center";
	td4.innerHTML = str3;
	var str4 = "<input id='balanceNumber" + (parseInt(index) - 1) + "' type='text' style='width:120px;' required='required' onchange='checkAmount(this);'/>";
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
		table.rows[i].cells[3].getElementsByTagName("input")[0].id = ("sellPrice" + parseInt(i));
		table.rows[i].cells[4].getElementsByTagName("input")[0].id = ("balanceNumber" + parseInt(i));
	}
}

/**
 * 初始化商品种类下拉框
 */
function getGoodsTypes()
{
	var data = "";
	var url = "/FuneralManageSystem/warehouseBegin!getGoodsTypes";
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
	var sellPrice = goodsName.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 销售价
	// 为全局变量赋值
	goodsNameCopy = goodsName;
	unit.value = "";
	sellPrice.value = "";
	// 如果商品种类为“请选择”
	if (obj.value == "请选择")
	{
		goodsName.innerHTML = "<option value='请选择'>请选择</option>";
	}
	else
	{
		var data = "goodsType=" + obj.value;
		var url = "/FuneralManageSystem/warehouseBegin!getGoodsNames";
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
 * 获取单位和销售价
 */
function getUnitAndPrice(obj)
{
	var goodsName = obj;// 品名
	var goodsType = obj.parentNode.previousElementSibling.getElementsByTagName("select")[0];// 商品种类
	var unit = obj.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	var sellPrice = obj.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 销售价
	// 保存品名
	goodsNameCopy = goodsName;
	// 如果商品种类和品名有一个没有选择，则清空单位文本框
	if (goodsType.value == "请选择" || goodsName.value == "请选择")
	{
		unit.value = "";
		sellPrice.value = "";
	}
	else
	{
		var data = "goodsType=" + goodsType.value + "&goodsName=" + obj.value;
		var url = "/FuneralManageSystem/warehouseBegin!getUnitAndPrice";
		sendRequest("post", url, data, getUnitAndPriceResult);
	}
}

/**
 * 获取单位和销售价数据
 */
function getUnitAndPriceResult(result)
{
	var unit = goodsNameCopy.parentNode.nextElementSibling.getElementsByTagName("input")[0];// 单位
	var sellPrice = goodsNameCopy.parentNode.nextElementSibling.nextElementSibling.getElementsByTagName("input")[0];// 销售价
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 获取单位和销售价
	unit.value = data[0].unit;
	sellPrice.value = data[0].sellPrice;
}

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
 * 检验期初入库信息是否正确
 */
function checkWarehouseBeginInfo()
{
	var table = document.getElementById("goodsInfo");// 表格
	if (table.rows.length == 1) return false;
	// 遍历表格
	for (var i = 1; i < table.rows.length; i++)
	{
		var tr = table.rows[i];
		var goodsType = tr.cells[0].getElementsByTagName("select")[0];// 商品种类
		var goodsName = tr.cells[1].getElementsByTagName("select")[0];// 品名
		if (goodsType.value == "请选择" || goodsName.value == "请选择") return false;
	}
	return true;
}

/**
 * 获取添加的商品信息
 */
function getGoods()
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
		obj.sellPrice = table.rows[i].cells[3].getElementsByTagName("input")[0].value;
		obj.balanceNumber = table.rows[i].cells[4].getElementsByTagName("input")[0].value;
		// 将这个对象装入到json中
		json.data.push(obj);
	}
	return JSON.stringify(json);
}

/**
 * 保存期初入库信息
 */
function saveWarehouseBeginInfo()
{
	var warehouseName = document.getElementById("warehouseName");// 仓库
	// 如果期初入库信息正确
	if (checkWarehouseBeginInfo())
	{
		if (confirm("确定提交吗？\n提交后，不可修改！请仔细检查期初入库信息是否正确！"))
		{
			var data = "warehouseName=" + warehouseName.value + "&goods=" + getGoods();
			var url = "/FuneralManageSystem/warehouseBegin!saveWarehouseBeginInfo";
			sendRequest("post", url, data, saveWarehouseBeginInfoBack);
		}
	}
	else
	{
		if (table.rows.length == 1) alert("请添加商品信息！");
		else alert("请完善期初入库信息！");
	}
	return false;
}

/**
 * 获取保存信息
 * @param result
 */
function saveWarehouseBeginInfoBack(result)
{
	var saveButton = document.getElementById("saveButton");// 提交按钮
	data = eval("(" + result + ")");
	if (data == "true") 
	{
		alert("提交成功！");
		saveButton.disabled = "disabled";
	}
	else alert("提交失败，请检查期初入库单是否正确！");
}