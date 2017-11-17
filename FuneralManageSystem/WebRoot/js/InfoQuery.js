function showGoods() {
	
	http_request = createHttpRequest();
	http_request.onreadystatechange = showAllFuneralGoodsCallBack;
	http_request.open('POST', "RegisterServiceAction!showAllFuneralGoods",
			false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(null);

	http_request = createHttpRequest();
	http_request.onreadystatechange = showUrnCallBack;
	http_request.open('POST', "RegisterServiceAction!showUrn", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(null);

	http_request = createHttpRequest();
	http_request.onreadystatechange = getLeaveRoomCallBack;
	http_request.open('POST', "RegisterServiceAction!getLeaveRoom", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(null);
	return false;

}
function showAllFuneralGoodsCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var testTbl = document.getElementById("showallFuneralGoods");
			for (var i = 0; i < jsonLength; i++) {
				var row = testTbl.insertRow();

				var funeralGoodsCell1 = row.insertCell();
				var goodsBeCost1 = row.insertCell();
				var goodsRealCost1 = row.insertCell();
				var addGoodsButton1 = row.insertCell();
				var hideInput1 = row.insertCell();

				var gap = row.insertCell();

				var hideInput2 = row.insertCell();
				var funeralGoodsCell2 = row.insertCell();
				var goodsBeCost2 = row.insertCell();
				var goodsRealCost2 = row.insertCell();
				var addGoodsButton2 = row.insertCell();

				funeralGoodsCell1.innerText = json[i].goodsName;
				goodsBeCost1.innerHTML = "<input type='text' style='width: 80px;' onchange='changeGoodBeCost1(this)' value='"
						+ json[i].goodsBeCost + "' disabled='true'>";
				goodsRealCost1.innerHTML = "<input type='text' style='width: 80px;' value='0' onchange='changeGoodsCost1(this)' disabled='true'>";
				addGoodsButton1.innerHTML = "<input align='center' type='checkbox' onchange='chooseFuneralGoods1(this)'>";
				hideInput1.innerHTML = "<input type='hidden' value='0'><input type='hidden' value='"
						+ json[i].goodsBeCost + "'>";
				gap.innerText = " ";
				i++;
				if (i < jsonLength) {
					funeralGoodsCell2.innerText = json[i].goodsName;
					goodsBeCost2.innerHTML = "<input type='text' style='width: 80px;'  onchange='changeGoodBeCost2(this)' value='"
							+ json[i].goodsBeCost + "' disabled='true'>";
					goodsRealCost2.innerHTML = "<input type='text' style='width: 80px;' value='0' onchange='changeGoodsCost2(this)' disabled='disabled'>";
					addGoodsButton2.innerHTML = "<input align='center' type='checkbox' onchange='chooseFuneralGoods2(this)'>";
					hideInput2.innerHTML = "<input type='hidden' value='0'><input type='hidden' value='"
							+ json[i].goodsBeCost + "'>";
				}
			}
			var te = document.getElementById("alladd");
			for (var i = 0; i < jsonLength; i++) {
				var row = te.insertRow();
				var funeralGoodsCell1 = row.insertCell();
				var goodsGrade1 = row.insertCell();
				var goodsBeCost1 = row.insertCell();
				var goodsRealCost1 = row.insertCell();
				var funeralGoodsCell2 = row.insertCell();
				var goodsGrade2 = row.insertCell();
				var goodsBeCost2 = row.insertCell();
				var goodsRealCost2 = row.insertCell();
				var funeralGoodsCell3 = row.insertCell();
				var goodsGrade3 = row.insertCell();
				var goodsBeCost3 = row.insertCell();
				var goodsRealCost3 = row.insertCell();

				funeralGoodsCell1.innerHTML = "<b>" + json[i].goodsName
						+ "</b>";
				gap.innerText = " ";
				i++;
				if (i < jsonLength) {
					funeralGoodsCell2.innerHTML = "<b>" + json[i].goodsName
							+ "</b>";
				}
				i++;
				if (i < jsonLength) {
					funeralGoodsCell3.innerHTML = "<b>" + json[i].goodsName
							+ "</b>";
				}
			}
		} else {
			alert("您请求的页面没有响应！");
		}
	}
}
var pageSize = 10; // 每页显示5条数据
var TotalPage = 1; // 定义总页数
var cremationInfo = document.getElementById("cremationInfo");
var benefitInfo = document.getElementById("benefitInfo");// 获取表格
// 获取表格
function getLeaveRoomCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var urnSelect = document.getElementById("leaveRoomGrade");
			for (var i = 0; i < jsonLength; i++) {
				var new_opt = new Option(json[i].itemName, json[i].itemName);
				urnSelect.options.add(new_opt);
			}
		}
	}
}
function showUrnCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var json = eval("(" + http_request.responseText + ")");
			json = eval("(" + json + ")");
			var jsonLength = json.length;
			var urnSelect = document.getElementById("urnChoose");
			for (var i = 0; i < jsonLength; i++) {
				var new_opt = new Option(json[i].urnName, json[i].urnBeCost);
				urnSelect.options.add(new_opt);
			}
		}
	}
}
function CremationInfoQuery() { // 查询火化信息

	var pageNumTemp = 1; // 默认当前页为1

	writePage(pageNumTemp);
	return false;

}

function BenefitInfoQuery() { // 查询惠民补助信息

	var pageNumTemp = 1;
	writePage1(pageNumTemp);
	return false;

}
function CremationQuery() { // 查询惠民补助信息


	var pageNumTemp = 1;
	writePage2(pageNumTemp);
	return false;

}

function writePage(pageNumTemp) { // 发送查询火化信息的请求

	deleteTable(); // 清空表格
	PageNum = pageNumTemp;
	var startTime = document.form1.startTime.value; // 开始时间
	var endTime = document.form1.endTime.value; // 结束时间
	url = "&startTime=" + startTime + "&endTime=" + endTime;
	url = "pageNum=" + PageNum + url;
	http_request = createHttpRequest();
	http_request.onreadystatechange = writePageCallBack;
	http_request.open('POST', "QueryCremationInfoAction!queryCremationInfo",
			false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	http_request.send(url);
	return false;
}

function writePage1(pageNumTemp) { // 发送查询惠民补助信息的请求
	deleteTable1(); // 清空表格
	PageNum = pageNumTemp;
	var startTime = document.form2.startTime1.value; // 开始时间
	var endTime = document.form2.endTime1.value; // 结束时间
	url = "&startTime=" + startTime + "&endTime=" + endTime;
	url = "pageNum=" + PageNum + url;
	http_request = createHttpRequest();
	http_request.onreadystatechange = writePage1CallBack;
	http_request.open('POST', "QueryBenefitInfoAction!queryBenefitInfo", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;
}
function writePage2(pageNumTemp) { // 发送查询惠民补助信息的请求

	deleteTable2(); // 清空表格
	PageNum = pageNumTemp;
	
	var startTime = document.form1.startTime.value; // 开始时间
	var endTime = document.form1.endTime.value; // 结束时间

	url = "&startTime=" + startTime + "&endTime=" + endTime;
	url = "pageNum=" + PageNum + url;
	http_request = createHttpRequest();
	http_request.onreadystatechange = writePage2CallBack;
	http_request.open('POST', "QueryCremationAction!queryCremation", false);
	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;
}
function printCremationTable() { // 打印火化信息表格
	var printContent = document.getElementById("cremationInfo");
	var iframe = document.createElement('IFRAME');
	var doc = null;

	iframe.setAttribute('id', 'printIframe');
	iframe.setAttribute('style',
			'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');

	document.body.appendChild(iframe);
	doc = iframe.contentWindow.document;
	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();

	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}

function printBenefitTable() { // 打印惠民补助信息表格
	var printContent = document.getElementById("benefitInfo");
	var iframe = document.createElement('IFRAME');
	var doc = null;

	iframe.setAttribute('id', 'printIframe');
	iframe.setAttribute('style',
			'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');

	document.body.appendChild(iframe);
	doc = iframe.contentWindow.document;
	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();

	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}

function writePageCallBack() { // 接收查询得到的火化信息

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;

			var json = eval("(" + result1 + ")");

			

			var jsonValue2 = eval("(" + json + ")");

			var length = jsonValue2.length;

			document.getElementById("asd").innerText = jsonValue2[length - 1].result;
			document.getElementById("tableExcel").style.display = "";
			for (var i = 0; i < length - 1; i++) {

				var deadId =jsonValue2[i].deadId;
				var remainsNumber = jsonValue2[i].remainsNumber;
				var deadName = jsonValue2[i].deadName;
				var deadSex = jsonValue2[i].deadSex;
				var deadAge = jsonValue2[i].deadAge;
				var address = jsonValue2[i].address;
				var inTime = jsonValue2[i].inTime;
				inTime = inTime.substring(0, 10);
				var remainsNo = jsonValue2[i].remainsNo;
				var deadTime = jsonValue2[i].deadTime + "";
				deadTime = deadTime.substring(0, 10);
				var deadReason = jsonValue2[i].deadReason;
				var memberMobile = jsonValue2[i].memberMobile;
				var invoiceNo = jsonValue2[i].invoiceNo;
				var cremationTable = document.getElementById("cremationInfo");
				var row = cremationTable.insertRow();
				var cell = row.insertCell();
				var cell0 = row.insertCell();
				var cell1 = row.insertCell();
				var cell2 = row.insertCell();
				var cell3 = row.insertCell();
				var cell4 = row.insertCell();
				var cell5 = row.insertCell();
				var cell6 = row.insertCell();
				var cell7 = row.insertCell();
				var cell8 = row.insertCell();
				var cell9 = row.insertCell();
				var cell10 = row.insertCell()
				cell.innerText = inTime;
				cell0.innerText = remainsNumber;
				cell1.innerText = deadName;
				cell2.innerText = deadSex;
				cell3.innerText = deadAge;
				cell4.innerText = "";
				cell5.innerText = address;
				cell6.innerText = deadId;
				cell7.innerText = deadTime;
				cell8.innerText = deadReason;
				cell9.innerText = memberMobile;
				cell10.innerText = invoiceNo;

			}

			var count = jsonValue2[length - 1].result;

			if (count != 0 && count % pageSize == 0) {
				TotalPage = count / pageSize;
			} else if (count != 0 && count % pageSize != 0) {
				TotalPage = parseInt(count / pageSize) + 1;
			}
			var pageList = GetPaging(PageNum, TotalPage, "writePage");

			document.getElementById("divNumber").innerHTML = pageList;

		}

	}
}

function writePage1CallBack() { // 接收查询得到的惠民补助信息

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;

			var json = eval("(" + result1 + ")");

			

			var jsonValue2 = eval("(" + json + ")");
			var length = jsonValue2.length;
			document.getElementById("qaq").innerText = jsonValue2[length - 1].result;
			document.getElementById("tableExcel2").style.display = "";
			for (var i = 0; i < length - 1; i++) {

				var deadId =jsonValue2[i].deadId;
				var subsidyMoney = jsonValue2[i].subsidyMoney;

				var deadName = jsonValue2[i].deadName;

				var deadSex = jsonValue2[i].deadSex;
				var deadAge = jsonValue2[i].deadAge;
				var address = jsonValue2[i].address;
				var inTime = jsonValue2[i].inTime;
				inTime = inTime.substring(0, 10);
				var remainsNo = jsonValue2[i].remainsNo;
				var deadTime = jsonValue2[i].deadTime + "";
					deadTime = deadTime.substring(0, 10);
				var deadReason = jsonValue2[i].deadReason;
				var memberMobile = jsonValue2[i].memberMobile;
				var invoiceNo = jsonValue2[i].invoiceNo;
				var benefitTime = jsonValue2[i].benefitTime;
					benefitTime = benefitTime.substring(0, 10);
				var subsidyNo = jsonValue2[i].subsidyNo;
				var benefitInfo = document.getElementById("benefitInfo");
				var row = benefitInfo.insertRow();
				var cell = row.insertCell();
				var cell0 = row.insertCell();
				var cell1 = row.insertCell();
				var cell2 = row.insertCell();
				var cell3 = row.insertCell();
				var cell4 = row.insertCell();
				var cell5 = row.insertCell();
				var cell6 = row.insertCell();
				var cell7 = row.insertCell();
				var cell8 = row.insertCell();
				var cell9 = row.insertCell();
				var cell10 = row.insertCell()
				var cell11 = row.insertCell();
				cell.innerText = inTime;
				cell0.innerText = deadName;
				cell1.innerText = deadSex;
				cell2.innerText = deadAge;
				cell3.innerText = address;
				cell4.innerText = deadId;
				cell5.innerText = subsidyMoney;
				cell6.innerText = deadTime;
				cell7.innerText = deadReason;
				cell8.innerText = memberMobile;				
				cell9.innerText = invoiceNo;				
				cell10.innerText = subsidyNo;
				cell11.innerText = benefitTime;

			}
			var count = jsonValue2[length - 1].result;

			if (count != 0 && count % pageSize == 0) {
				TotalPage = count / pageSize;
			} else if (count != 0 && count % pageSize != 0) {
				TotalPage = parseInt(count / pageSize) + 1;
			}
			var pageList = GetPaging(PageNum, TotalPage, "writePage1");

			document.getElementById("divNumber1").innerHTML = pageList;

		}

	}
}
function writePage2CallBack() { // 接收查询得到的火化信息

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;
			document.getElementById("infoTable").style.display="";

			var json = eval("(" + result1 + ")");

			var jsonValue2 = eval("(" + json + ")");

			var length = jsonValue2.length;

			if(length>1){
				document.getElementById("cremationTable").style.display="";
				document.getElementById("Number1").style.display="";

			}

			
			for (var i = 0; i < length - 1; i++) {

				var deadId = jsonValue2[i].deadId;
				

				var remainsNumber = jsonValue2[i].remainsNumber;

				var deadName = jsonValue2[i].deadName;

				var deadSex = jsonValue2[i].deadSex;
				var deadAge = jsonValue2[i].deadAge;
				var address = jsonValue2[i].address;
				var inTime = jsonValue2[i].inTime;
				inTime = inTime.substring(0, 10);
				var remainsNo = jsonValue2[i].remainsNo;
				var deadTime = jsonValue2[i].deadTime + "";
				deadTime = deadTime.substring(0, 10);
				var deadReason = jsonValue2[i].deadReason;
				var memberMobile = jsonValue2[i].memberMobile;
				var invoiceNo = jsonValue2[i].invoiceNo;
				var cremationTable = document.getElementById("cremationTable");
				var row = cremationTable.insertRow();
				var cell = row.insertCell();
				var cell0 = row.insertCell();
				var cell1 = row.insertCell();
				var cell2 = row.insertCell();
				var cell3 = row.insertCell();
				var cell4 = row.insertCell();
				var cell5 = row.insertCell();
				var cell6 = row.insertCell();
				var cell7 = row.insertCell();
				var cell8 = row.insertCell();
				var cell9 = row.insertCell();
				var addGoodsButton = row.insertCell()

				cell.innerText = inTime;
				cell0.innerText = remainsNumber;
				cell1.innerText = deadName;
				cell2.innerText = deadSex;
				cell3.innerText = deadAge;
				cell4.innerText = invoiceNo;
				cell5.innerText = address;
				cell6.innerText = deadId;
				cell7.innerText = deadTime;
				cell8.innerText = deadReason;
				cell9.innerText = memberMobile;
				addGoodsButton.innerHTML = "<input align='center' type='button' value='修改业务' onclick='return getCremationService(this)'>";
			}

			var count = jsonValue2[length - 1].result;

			if (count != 0 && count % pageSize == 0) {
				TotalPage = count / pageSize;
			} else if (count != 0 && count % pageSize != 0) {
				TotalPage = parseInt(count / pageSize) + 1;
			}
			var pageList = GetPaging(PageNum, TotalPage, "writePage2");

			document.getElementById("Number1").innerHTML = pageList;

		}

	}
}
function deleteTable() { // 删除火化信息表

	for (var i = cremationInfo.rows.length - 1; i >= 2; i--) {

		cremationInfo.deleteRow(i);
	}
}

function deleteTable1() { // 删除惠民补助信息

	for (var i = benefitInfo.rows.length - 1; i >= 2; i--) {

		benefitInfo.deleteRow(i);
	}
}
function deleteTable2() {
	// 删除惠民补助信息
	var cremation = document.getElementById("cremationTable");

	for (var i = cremationTable.rows.length - 1; i >= 2; i--) {

		cremation.deleteRow(i);
	}
}

function CremationToExcel() {// 将火化信息表导成Excel

	var startTime = document.form1.startTime.value; // 开始时间
	var endTime = document.form1.endTime.value;
	url = "&startTime=" + startTime + "&endTime=" + endTime;

	http_request = createHttpRequest();

	http_request.onreadystatechange = CremationToExcelCallBack;

	http_request.open('POST', "CremationToExcelAction!CremationToExcel", false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;// 结束时间

}
function CremationToExcelCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;

			var json = eval("(" + result1 + ")");

			

			var jsonValue2 = eval("(" + json + ")");
			var length = jsonValue2.length;

			var oXL = new ActiveXObject("Excel.Application"); // 创建应该对象
			var oWB = oXL.Workbooks.Add();// 新建一个Excel工作簿
			var oSheet = oWB.ActiveSheet;// 指定要写入内容的工作表为活动工作表
			oSheet.Cells(1, 1).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 2).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 3).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 4).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 5).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 6).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 7).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 8).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 9).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 10).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 11).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 12).NumberFormatLocal = "@";// 将单元格的格式定义为文本

			oSheet.Cells(1, 1).value = cremationInfo.rows(1).cells(0).innerText;

			oSheet.Cells(1, 2).value = cremationInfo.rows(1).cells(1).innerText;
			oSheet.Cells(1, 3).value = cremationInfo.rows(1).cells(2).innerText;
			oSheet.Cells(1, 4).value = cremationInfo.rows(1).cells(3).innerText;
			oSheet.Cells(1, 5).value = cremationInfo.rows(1).cells(4).innerText;
			oSheet.Cells(1, 6).value = cremationInfo.rows(1).cells(5).innerText;
			oSheet.Cells(1, 7).value = cremationInfo.rows(1).cells(6).innerText;
			oSheet.Cells(1, 8).value = cremationInfo.rows(1).cells(7).innerText;
			oSheet.Cells(1, 9).value = cremationInfo.rows(1).cells(8).innerText;
			oSheet.Cells(1, 10).value = cremationInfo.rows(1).cells(9).innerText;
			oSheet.Cells(1, 11).value = cremationInfo.rows(1).cells(10).innerText;
			oSheet.Cells(1, 12).value = cremationInfo.rows(1).cells(11).innerText;

			for (var i = 0; i < length; i++) {// 在Excel中写行

				// 定义格式
				oSheet.Cells(i + 2, 1).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 2).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 3).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 4).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 5).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 6).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 7).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 8).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 9).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 10).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 11).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 12).NumberFormatLocal = "@";// 将单元格的格式定义为文本

				var inTime = jsonValue2[i].inTime;
				inTime = inTime.substring(0, 10);

				oSheet.Cells(i + 2, 1).value = inTime;// 向单元格写入值

				oSheet.Cells(i + 2, 2).value = jsonValue2[i].remainsNumber;// 向单元格写入值
				oSheet.Cells(i + 2, 3).value = jsonValue2[i].deadName;// 向单元格写入值
				oSheet.Cells(i + 2, 4).value = jsonValue2[i].deadSex;// 向单元格写入值
				oSheet.Cells(i + 2, 5).value = jsonValue2[i].deadAge;// 向单元格写入值
				oSheet.Cells(i + 2, 6).value = "";// 向单元格写入值
				oSheet.Cells(i + 2, 7).value = jsonValue2[i].address;// 向单元格写入值
				oSheet.Cells(i + 2, 8).value = "'" + jsonValue2[i].deadId;// 向单元格写入值
				var deadTime = jsonValue2[i].deadTime + "";
				deadTime = deadTime.substring(0, 10);
				oSheet.Cells(i + 2, 9).value = deadTime;// 向单元格写入值
				oSheet.Cells(i + 2, 10).value = jsonValue2[i].deadReason;// 向单元格写入值
				oSheet.Cells(i + 2, 11).value = jsonValue2[i].memberMobile;// 向单元格写入值
				oSheet.Cells(i + 2, 12).value = jsonValue2[i].invoiceNo;// 向单元格写入值

				// //oSheet.Cells(i+1,j+1).Font.Bold = true;//加粗
				// //oSheet.Cells(i+1,j+1).Font.Size = 10;//字体大小
				// //oSheet.Cells(i+1,j+1).border = 1;

			}
			oXL.Selection.CurrentRegion.Select;
			oXL.Selection.Interior.Pattern = 0; // 设置底色为空
			oXL.Selection.Borders.LineStyle = 1; // 设置单元格边框为实线
			oXL.Selection.ColumnWidth = 13; // 设置列宽
			oXL.Selection.RowHeight = 16;
			oXL.Visible = true;
			oXL.UserControl = true;
			oXL = null

		}
	}
}

//			

function BenefitToExcel() { // 将惠民补助信息表导成Excel
	var startTime = document.form2.startTime1.value; // 开始时间
	var endTime = document.form2.endTime1.value;
	url = "&startTime=" + startTime + "&endTime=" + endTime;

	http_request = createHttpRequest();

	http_request.onreadystatechange = BenefitToExcelCallBack;

	http_request.open('POST', "ToExcelAction!toExcel", false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;// 结束时间
}
function BenefitToExcelCallBack() {

	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result1 = http_request.responseText;

			var json = eval("(" + result1 + ")");
			
			var jsonValue2 = eval("(" + json + ")");
			var length = jsonValue2.length;

			var oXL = new ActiveXObject("Excel.Application"); // 创建应该对象
			var oWB = oXL.Workbooks.Add();// 新建一个Excel工作簿
			var oSheet = oWB.ActiveSheet;// 指定要写入内容的工作表为活动工作表
			oSheet.Cells(1, 1).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 2).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 3).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 4).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 5).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 6).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 7).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 8).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 9).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 10).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 11).NumberFormatLocal = "@";// 将单元格的格式定义为文本
			oSheet.Cells(1, 12).NumberFormatLocal = "@";// 将单元格的格式定义为文本

			oSheet.Cells(1, 1).value = benefitInfo.rows(1).cells(0).innerText;
			oSheet.Cells(1, 2).value = benefitInfo.rows(1).cells(1).innerText;
			oSheet.Cells(1, 3).value = benefitInfo.rows(1).cells(2).innerText;
			oSheet.Cells(1, 4).value = benefitInfo.rows(1).cells(3).innerText;
			oSheet.Cells(1, 5).value = benefitInfo.rows(1).cells(4).innerText;
			oSheet.Cells(1, 6).value = benefitInfo.rows(1).cells(5).innerText;
			oSheet.Cells(1, 7).value = benefitInfo.rows(1).cells(6).innerText;
			oSheet.Cells(1, 8).value = benefitInfo.rows(1).cells(7).innerText;
			oSheet.Cells(1, 9).value = benefitInfo.rows(1).cells(8).innerText;
			oSheet.Cells(1, 10).value = benefitInfo.rows(1).cells(9).innerText;
			oSheet.Cells(1, 11).value = benefitInfo.rows(1).cells(10).innerText;
			oSheet.Cells(1, 12).value = benefitInfo.rows(1).cells(11).innerText;

			for (var i = 0; i < length; i++) {// 在Excel中写行

				// 定义格式
				oSheet.Cells(i + 2, 1).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 2).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 3).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 4).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 5).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 6).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 7).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 8).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 9).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 10).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 11).NumberFormatLocal = "@";// 将单元格的格式定义为文本
				oSheet.Cells(i + 2, 12).NumberFormatLocal = "@";// 将单元格的格式定义为文本

				var inTime1 = jsonValue2[i].inTime;
				inTime1 = inTime1.substring(0, 10);

				oSheet.Cells(i + 2, 1).value = inTime1;// 向单元格写入值

				oSheet.Cells(i + 2, 2).value = jsonValue2[i].deadName;// 向单元格写入值
				oSheet.Cells(i + 2, 3).value = jsonValue2[i].deadSex;// 向单元格写入值
				oSheet.Cells(i + 2, 4).value = jsonValue2[i].deadAge;// 向单元格写入值

				oSheet.Cells(i + 2, 5).value = "";// 向单元格写入值
				oSheet.Cells(i + 2, 6).value = jsonValue2[i].address;// 向单元格写入值
				oSheet.Cells(i + 2, 7).value = jsonValue2[i].deadId;// 向单元格写入值
				oSheet.Cells(i + 2, 8).value = jsonValue2[i].subsidyMoney;// 向单元格写入值
				oSheet.Cells(i + 2, 9).value = jsonValue2[i].deadTime
						.substring(0, 10);// 向单元格写入值
				oSheet.Cells(i + 2, 10).value = jsonValue2[i].deadReason;// 向单元格写入值
				oSheet.Cells(i + 2, 11).value = jsonValue2[i].memberMobile;// 向单元格写入值
				oSheet.Cells(i + 2, 12).value = jsonValue2[i].invoiceNo;// 向单元格写入值

				// //oSheet.Cells(i+1,j+1).Font.Bold = true;//加粗
				// //oSheet.Cells(i+1,j+1).Font.Size = 10;//字体大小
				// //oSheet.Cells(i+1,j+1).border = 1;

			}
			oXL.Selection.CurrentRegion.Select;
			oXL.Selection.Interior.Pattern = 0; // 设置底色为空
			oXL.Selection.Borders.LineStyle = 1; // 设置单元格边框为实线
			oXL.Selection.ColumnWidth = 13; // 设置列宽
			oXL.Selection.RowHeight = 16;
			oXL.Visible = true;
			oXL.UserControl = true;
			oXL = null

		}
	}
}
function getCremationService(obj) {

	

	document.getElementById("name").innerHTML="";
	document.getElementById("no").innerHTML="";
	document.getElementById("taxDate").innerHTML="";
	document.getElementById("sex").innerHTML="";
	document.getElementById("age").innerHTML="";
	document.getElementById("address").innerHTML="";	
	document.getElementById("urn").innerHTML="";
	document.getElementById("urn1").innerHTML="";
	document.getElementById("urn2").innerHTML="";
	document.getElementById("face").innerHTML="";
	document.getElementById("face1").innerHTML="";
	document.getElementById("face2").innerHTML="";
	document.getElementById("farewell").innerHTML="";
	document.getElementById("farewell1").innerHTML="";
	document.getElementById("farewell2").innerHTML="";
	document.getElementById("cremation").innerHTML="";
	document.getElementById("cremation1").innerHTML="";
	document.getElementById("cremation2").innerHTML="";

	document.getElementById("car").innerHTML="";
	document.getElementById("car1").innerHTML="";
	document.getElementById("car2").innerHTML="";

	document.getElementById("currentDateTh2").innerHTML="";
//	document.getElementById("no").innerHTML="";
	var allGoodsTbody=document.getElementById("alladd");
	var allFuneralGoodsRows=allGoodsTbody.rows.length;
	for(var n=0;n<allFuneralGoodsRows;n++){
//		var goodsName1=allGoods.rows[n].cells[0].innerText;
		allGoodsTbody.rows[n].cells[1].innerHTML="";
		allGoodsTbody.rows[n].cells[2].innerHTML="";
		allGoodsTbody.rows[n].cells[3].innerHTML="";
		var goods2=allGoodsTbody.rows[n].cells[4].innerText;
		if(goods2!=""){
			allGoodsTbody.rows[n].cells[5].innerHTML="";
			allGoodsTbody.rows[n].cells[6].innerHTML="";
			allGoodsTbody.rows[n].cells[7].innerHTML="";
		}
		var goods3=allGoodsTbody.rows[n].cells[8].innerText;
		if(goods3!=""){
			allGoodsTbody.rows[n].cells[9].innerHTML="";
			allGoodsTbody.rows[n].cells[10].innerHTML="";
			allGoodsTbody.rows[n].cells[11].innerHTML="";
		}
		
	}
	
	document.getElementById("total").innerHTML="";
	document.getElementById("beCost").innerHTML="";
	document.getElementById("charge").innerHTML="";
	document.getElementById("capitalMoney").innerHTML="";

	
	document.getElementById("tableExcel1").style.display="none";
	
	var tr = obj.parentNode.parentNode;
	var deadId = tr.cells[7].innerText;

	
	getFee(deadId);

	url = "deadId=" + deadId;

		
	http_request = createHttpRequest();

	http_request.onreadystatechange = getCremationServiceCallBack;

	http_request.open('POST', "getCremationServiceAction!getCremationService",
			false);

	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	http_request.send(url);

	return false;// 结束时间

}

function getCremationServiceCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result = http_request.responseText;

			var json = eval("(" + result + ")");			

						

			var jsonValue2 = eval("(" + json + ")");
			var length =jsonValue2.length;

											
			document.getElementById("servicedeadName").value="";
			document.getElementById("deadId").value="";
			document.getElementById("urnName").value="";
			document.getElementById("beauty").value="";
			document.getElementById("farewell").value="";
			document.getElementById("farewellStatus").value="";
			document.getElementById("cremationstove").value="";
			document.getElementById("cremationStatus").value="";
		
			var urnChooseBox=document.getElementById("urnChooseBox");
			var makeBeautyBox=document.getElementById("makeBeautyBox");
			var leaveRoomBox=document.getElementById("leaveRoomBox");
			var cremationBox=document.getElementById("cremationBox");
          
			for(var i=0;i<length;i++){
				var CremationTypeNo = jsonValue2[i].CremationTypeNo;
				
				var status = jsonValue2[i].status;
				var deadId1=jsonValue2[i].deadId;
				
				if(CremationTypeNo=="03"&&status=="2"){
					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value = jsonValue2[i].itemRealCost;
									
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);
					
					document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;
					document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
					
					document.getElementById("cremationstove").disabled = "true";
									
					document.getElementById("cremationStatus").value = "火化结束";
					cremationBox.disabled="true";
					document.getElementById("cremationStatus").disabled = "true";
					document.getElementById("cremationGrade").disabled = "true";
					document.getElementById("cremationBeCost").disabled = "true";
					document.getElementById("cremationRealCost").disabled = "true";						
				} 
				if(CremationTypeNo=="03"&&status=="1"){

					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("cremationRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);			
					document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
					
					
					document.getElementById("cremationstove").disabled = "true";
					document.getElementById("cremationStatus").value = "火化正在进行";
					document.getElementById("cremationStatus").disabled = "true";
					document.getElementById("cremationGrade").disabled = "true";
					document.getElementById("cremationBeCost").disabled = "true";
					document.getElementById("cremationRealCost").disabled = "true";
						
					}
				if(CremationTypeNo=="03"&&status=="0")
					{
					document.getElementById("cremationstove").value=jsonValue2[i].itemName;
					document.getElementById("cremationBeCost").value = Number(jsonValue2[i].itemBeCost);
					
					document.getElementById("cremationRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("cremationRealCost").value=document.getElementById("cremationBeCost").value -document.getElementById("cremationRealCost").value;					
					document.getElementById("preCremationBeCost").value = Number(jsonValue2[i].itemBeCost);				
					document.getElementById("preCremationRealCost").value = document.getElementById("cremationRealCost").value;
					
					
					document.getElementById("cremationstove").disabled = "true";
					document.getElementById("cremationStatus").value = "火化尚未开始";
					document.getElementById("cremationStatus").disabled = "true";
					document.getElementById("cremationGrade").disabled = "";
					document.getElementById("cremationBeCost").disabled = "";
					document.getElementById("cremationRealCost").disabled = "";
						
					}
				if(CremationTypeNo=="02"&&status=="2"){
					
					document.getElementById("farewell").value=jsonValue2[i].itemName;
					document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
					document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
					
					document.getElementById("farewell").disabled = "true";
					document.getElementById("farewellStatus").value = "告别结束";
					
					leaveRoomBox.disabled="true";
					document.getElementById("farewellStatus").disabled = "true";
					document.getElementById("leaveRoomBeCost").disabled = "true";
					document.getElementById("leaveRoomRealCost").disabled = "true";
					document.getElementById("leaveRoomGrade").disabled = "true";
					}
					if(CremationTypeNo=="02"&&status=="1"){
						document.getElementById("farewell").value=jsonValue2[i].itemName;
						document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
						document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
						document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
					document.getElementById("farewell").disabled = "true";
					document.getElementById("farewellStatus").value = "告别正在进行";
					document.getElementById("farewellStatus").disabled = "true";
					document.getElementById("leaveRoomBeCost").disabled = "";
					document.getElementById("leaveRoomRealCost").disabled = "";
					document.getElementById("leaveRoomGrade").disabled = "";
						
					}
					if(CremationTypeNo=="02"&&status=="0"){
						document.getElementById("farewell").value=jsonValue2[i].itemName;
						document.getElementById("leaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						
						document.getElementById("leaveRoomRealCost").value = Number(jsonValue2[i].itemRealCost);
						document.getElementById("leaveRoomRealCost").value=document.getElementById("leaveRoomBeCost").value-document.getElementById("leaveRoomRealCost").value
						document.getElementById("preLeaveRoomBeCost").value = Number(jsonValue2[i].itemBeCost);
						document.getElementById("preLeaveRoomRealCost").value = document.getElementById("leaveRoomRealCost").value;
					
					document.getElementById("farewell").disabled = "true";
					document.getElementById("farewellStatus").value = "告别尚未开始";
					document.getElementById("farewellStatus").disabled = "true";
					document.getElementById("leaveRoomBeCost").disabled = "";
					document.getElementById("leaveRoomRealCost").disabled = "";
					document.getElementById("leaveRoomGrade").disabled = "";
						
					}
				
					
				if(CremationTypeNo=="01"&&status=="2"){				
					
					document.getElementById("beauty").value=jsonValue2[i].itemName;					
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
					document.getElementById("beauty").disabled = "true";
					document.getElementById("beautyStatus").value = "美容结束";
					makeBeautyBox.disabled="true";
					document.getElementById("beautyStatus").disabled = "true";
					document.getElementById("makeBeautyBeCost").disabled = "true";
					document.getElementById("makeBeautyRealCost").disabled = "true";
					document.getElementById("makeBeautyGrade").disabled = "true";
				}
				if(CremationTypeNo=="01"&&status=="1"){
					document.getElementById("beauty").value=jsonValue2[i].itemName;					
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyRealCost").value-document.getElementById("makeBeautyBeCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
					
					document.getElementById("beauty").disabled = "true";
					document.getElementById("beautyStatus").value = "美容正在进行";
					document.getElementById("beautyStatus").disabled = "true";
					document.getElementById("makeBeautyBeCost").disabled = "";
					document.getElementById("makeBeautyRealCost").disabled = "";
					document.getElementById("makeBeautyGrade").disabled = "";
					
					}
				if(CremationTypeNo=="01"&&status=="0"){
					
					document.getElementById("beauty").value=jsonValue2[i].itemName;	
										
					document.getElementById("makeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);					
					document.getElementById("makeBeautyRealCost").value = Number(jsonValue2[i].itemRealCost);
					document.getElementById("makeBeautyRealCost").value=document.getElementById("makeBeautyBeCost").value-document.getElementById("makeBeautyRealCost").value;					
					document.getElementById("preMakeBeautyBeCost").value = Number(jsonValue2[i].itemBeCost);
					document.getElementById("preMakeBeautyRealCost").value = document.getElementById("makeBeautyRealCost").value;
				   				   
                   document.getElementById("beauty").disabled = "true";
                   document.getElementById("beautyStatus").value = "美容尚未开始";
                   document.getElementById("beautyStatus").disabled = "true";
                   document.getElementById("makeBeautyBeCost").disabled = "";
                   document.getElementById("makeBeautyRealCost").disabled = "";
                   document.getElementById("makeBeautyGrade").disabled = "";
					
				}
				if(CremationTypeNo=="00"){
					
					   document.getElementById("GatecremationType").value=jsonValue2[i].itemName;					
					   document.getElementById("GatecremationType").value = status;
						
					}
				document.getElementById("servicedeadName").value = jsonValue2[i].deadName;
				document.getElementById("deadId").value = jsonValue2[i].deadId;	
				if(jsonValue2[i].urnName!=null){
				document.getElementById("urnBeCost").value = Number(jsonValue2[i].urnBeCost);
				document.getElementById("urnRealCost").value = Number(jsonValue2[i].urnRealCost);

				document.getElementById("urnName").value = jsonValue2[i].urnName;
				
				document.getElementById("urnRealCost").value=Number(document.getElementById("urnBeCost").value)-Number(document.getElementById("urnRealCost").value);
				document.getElementById("preUrnRealCost").value = Number(document.getElementById("urnRealCost").value);
				document.getElementById("preUrnBeCost").value = Number(jsonValue2[i].urnBeCost);
				}
	
			}
			
            var allBeCost=document.getElementById("allBeCost").value;
            
            var allRealCost=document.getElementById("allRealCost").value;

			var cremationBeCost=document.getElementById("cremationBeCost").value;
			var cremationRealCost=document.getElementById("cremationRealCost").value;
			var makeBeautyBeCost=document.getElementById("makeBeautyBeCost").value;
			var makeBeautyRealCost=document.getElementById("makeBeautyRealCost").value;
			var leaveRoomBeCost=document.getElementById("leaveRoomBeCost").value;
			var leaveRoomRealCost=document.getElementById("leaveRoomRealCost").value;
			var urnBeCost=document.getElementById("urnBeCost").value;
			var urnRealCost=document.getElementById("urnRealCost").value;

			document.getElementById("allBeCost").value=Number(document.getElementById("allBeCost").value)+Number(cremationBeCost)+Number(makeBeautyBeCost)+Number(leaveRoomBeCost)+Number(urnBeCost);
			document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(cremationRealCost)+Number(makeBeautyRealCost)+Number(leaveRoomRealCost)+Number(urnRealCost);

			
			changeGood(deadId1);
			var table2=document.getElementById("showallFuneralGoods");
			var rows=table2.rows.length;
			
			for(var k=2;k<rows;k++){
				var reduceMoney=table2.rows[k].cells[2].getElementsByTagName("input");
				var reduceMoney1=table2.rows[k].cells[9].getElementsByTagName("input");
				
				var hideMoney=table2.rows[k].cells[4];
				var hideValue=hideMoney.getElementsByTagName("input");
				    hideValue[0].value=reduceMoney[0].value;
				    
				var hideMoney1=table2.rows[k].cells[6];
				var hideValue1=hideMoney1.getElementsByTagName("input");
					hideValue1[0].value=reduceMoney1[0].value;
				
				
				if(reduceMoney[0].value!=0){
					document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(reduceMoney[0].value);
				}
				if(reduceMoney1[0].value!=0){
					document.getElementById("allRealCost").value=Number(document.getElementById("allRealCost").value)+Number(reduceMoney1[0].value);
					
				}
				
			}
			document.getElementById("theWholeCost").value=Number(document.getElementById("allBeCost").value)-Number(document.getElementById("allRealCost").value);
			
			if(document.getElementById("urnName").value!=null){
				urnChooseBox.checked=true;			
				var urnChoose=document.getElementById("urnChoose");				
				setSelected(urnChoose,document.getElementById("urnName").value);
				
			}
			if(document.getElementById("urnName").value==""){
				urnChooseBox.checked=false;

				var urnChoose=document.getElementById("urnChoose");
					urnChoose.disabled="true";
					document.getElementById("urnBeCost").disabled="true";
					document.getElementById("urnRealCost").disabled="true";
				setSelected(urnChoose,document.getElementById("urnName").value);				
			}
			
			if(document.getElementById("beauty").value!=null){
							
				makeBeautyBox.checked=true;
				
				var makeBeautyGrade=document.getElementById("makeBeautyGrade");
										
				setSelected(makeBeautyGrade,document.getElementById("beauty").value)
				
			}
			if(document.getElementById("beauty").value==""){
//				alert("美容为空");
				makeBeautyBox.checked=false;
				
				var urnChoose=document.getElementById("makeBeautyGrade");
				makeBeautyGrade.disabled="true";
					document.getElementById("makeBeautyBeCost").disabled="true";
					document.getElementById("makeBeautyRealCost").disabled="true";
				setSelected(makeBeautyGrade,document.getElementById("beauty").value);
			}
			if(document.getElementById("farewell").value!=null){
				
				leaveRoomBox.checked=true;
				
				var leaveRoomGrade=document.getElementById("leaveRoomGrade");
				
				setSelected(leaveRoomGrade,document.getElementById("farewell").value)			
				
			}
			if(document.getElementById("farewell").value==""){
//				alert("告别为空");
				leaveRoomBox.checked=false;
				
				var leaveRoomGrade=document.getElementById("leaveRoomGrade");
					leaveRoomGrade.disabled="true";
					document.getElementById("leaveRoomBeCost").disabled="true";
					document.getElementById("leaveRoomRealCost").disabled="true";
				setSelected(leaveRoomGrade,document.getElementById("farewell").value);
				
			}
			if(document.getElementById("cremationstove").value!=null){
//				alert("火化炉不为空");
								
				cremationBox.checked=true;
				
				var cremationGrade=document.getElementById("cremationGrade");
				
				setSelected(cremationGrade,document.getElementById("cremationstove").value)
				
			}
			if(document.getElementById("cremationstove").value==""){
//				alert("火化炉为空");
				cremationBox.checked=false;
				
				var cremationGrade=document.getElementById("cremationGrade");
					cremationGrade.disabled="true";
					document.getElementById("cremationBeCost").disabled="true";
					document.getElementById("cremationRealCost").disabled="true";
				setSelected(cremationGrade,document.getElementById("cremationstove").value);
				
			}
				
		}
	}
	
}
function changeGood(deadId) {
	
	url = "deadId=" + deadId;

	http_request = createHttpRequest();

	http_request.onreadystatechange = changeGoodsCallBack;

	http_request.open('POST', "QueryChosenGoodsAction!QueryChosenGoods", false);

	http_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	http_request.send(url);

	return false;// 结束时间

}
function changeGoodsCallBack() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {

			var result = http_request.responseText;
			
			
			var json=eval("(" + result + ")");
			
		    															
			var jsonValue2 = eval("("+ json +")");
					
			var length = jsonValue2.length;
			var tb=document.getElementById("showallFuneralGoods");
			var rows=tb.rows.length;
			var cells=tb.rows[1].cells.length;

			for(var i = 0; i < length; i++){
				var goodsName=jsonValue2[i].goodsName;
			
				var goodsRealCost=jsonValue2[i].goodsRealCost;
				var goodsBeCost=jsonValue2[i].goodsBeCost;
				

				for(var j=2;j<rows;j++){
					var goodName1=tb.rows[j].cells[0].innerText;
					var goodsName2=tb.rows[j].cells[7].innerText;
					
					if(goodsName==goodName1){
											
						var goodsCheckBoxInput1=tb.rows[j].cells[3].getElementsByTagName("input");
						goodsCheckBoxInput1[0].checked=true;
						var goosBeCost1 =tb.rows[j].cells[1].getElementsByTagName("input");
						goosBeCost1[0].value=goodsBeCost;
						chooseFuneralGoods1(goodsCheckBoxInput1[0]);
//						changeGoodBeCost1(goosBeCost1[0]);
						var reduce=tb.rows[j].cells[2].getElementsByTagName("input");
						reduce[0].value=Number(goodsBeCost)-Number(goodsRealCost);
												
					}
					if(goodsName==goodsName2){
//						(goodsBeCost);
						var goosBeCost2 =tb.rows[j].cells[8].getElementsByTagName("input");
						goosBeCost2[0].value=goodsBeCost;
						
						var goodsCheckBoxInput2=tb.rows[j].cells[10].getElementsByTagName("input");
						goodsCheckBoxInput2[0].checked=true;
						chooseFuneralGoods2(goodsCheckBoxInput2[0]);
						
						var reduce1=tb.rows[j].cells[9].getElementsByTagName("input");
						reduce1[0].value=Number(goodsBeCost)-Number(goodsRealCost);
						
					}
										
				}							
			}			
		}

	}
}
function chooseFuneralGoods1(obj){
	
	var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]
//	var goodsBeCost1=tr.cells[1].innerText;
	var goodsBeCost=tr.cells[1];
	var goodsBeCostInput=goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1=goodsBeCostInput[0].value;
	
	var goodsRealCost=tr.cells[2];
	var goodsRealCostInput=goodsRealCost.getElementsByTagName("input");
	var goodsRealCost1=goodsRealCostInput[0].value;
	
	if(obj.checked==true){
		goodsBeCostInput[0].disabled="";
		goodsRealCostInput[0].disabled="";
		var preAllBeCost=document.form1.allBeCost.value;
		var preAllRealCost=document.form1.allRealCost.value;

		var curAllBeCost=parseInt(goodsBeCost1)+parseInt(preAllBeCost);
		var curAllRealCost=parseInt(goodsRealCost1)+parseInt(preAllRealCost);
		document.form1.allBeCost.value=curAllBeCost;
		document.form1.allRealCost.value=curAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(goodsBeCost1)-parseInt(goodsRealCost1);
	}
	if(obj.checked==false){
		goodsBeCostInput[0].disabled="true";
		goodsRealCostInput[0].disabled="true";
		var preAllBeCost=document.form1.allBeCost.value;
		var preAllRealCost=document.form1.allRealCost.value;
		var curAllBeCost=preAllBeCost-goodsBeCost1;
		var curAllRealCost=preAllRealCost-goodsRealCost1;
		document.form1.allBeCost.value=curAllBeCost;
		document.form1.allRealCost.value=curAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(goodsBeCost1)+parseInt(goodsRealCost1);
	}
}
function changeGoodBeCost1(obj){
	var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]

	var goodsBeCost=tr.cells[1];
	var goodsBeCostInput=goodsBeCost.getElementsByTagName("input");
	var goodsBeCost1=goodsBeCostInput[0].value;
	
	if(checkNumber(goodsBeCost1)){
		var goodsBeCostHidden=tr.cells[4];
		var goodsBeCostHiddenInput=goodsBeCostHidden.getElementsByTagName("input");
		var goodsBeCostHidden1=goodsBeCostHiddenInput[1].value;
		
		var allServiceBeCost=document.form1.allBeCost;
		
		
		allServiceBeCost.value=parseInt(allServiceBeCost.value)+parseInt(goodsBeCost1)-parseInt(goodsBeCostHidden1);
		var theWholeCost=document.form1.theWholeCost;
		theWholeCost.value=parseInt(theWholeCost.value)+parseInt(goodsBeCost1)-parseInt(goodsBeCostHidden1);
		document.form1.lastWholeCost.value=theWholeCost.value;
//		alert(document.form1.lastWholeCost.value);
		goodsBeCostHiddenInput[1].value=goodsBeCost.getElementsByTagName("input")[0].value;
//		alert(goodsBeCostHiddenInput[1].value);
	}
	else{
		alert("输入金额错误，请检查后重新输入");
		goodsBeCostInput[0].focus();
	}
}
function changeGoodsCost1(obj){
	
	var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsBeCost1=tr.cells[1].innerText;
	var goodsRealCost=tr.cells[2];
	var hideTd1=tr.cells[4];
	var goodsRealCostInput=goodsRealCost.getElementsByTagName("input");
	var goodsRealCost1=goodsRealCostInput[0].value;
	
	if(checkNumber(goodsRealCost1)){
		
		var hideInput1=hideTd1.getElementsByTagName("input");
		var hideValue1=hideInput1[0].value;
		
		var changeGoodsRealCost1=goodsRealCost1-hideValue1;
		var allRealCost=document.form1.allRealCost.value;
		document.form1.allRealCost.value=parseInt(allRealCost)-parseInt(hideValue1)+parseInt(goodsRealCost1);
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(hideValue1)-parseInt(goodsRealCost1);
		hideInput1[0].value=goodsRealCost1;
	}
	else{
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}
function changeGoodsCost2(obj){
	var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]
	var goodsRealCost=tr.cells[9];
	var hideTd2=tr.cells[6];
	var goodsRealCostInput=goodsRealCost.getElementsByTagName("input");
	var goodsRealCost2=goodsRealCostInput[0].value;
	if(checkNumber(goodsRealCost2)){
		var hideInput2=hideTd2.getElementsByTagName("input");
		var hideValue2=hideInput2[0].value;
		var changeGoodsRealCost2=goodsRealCost2-hideValue2;
		var allRealCost=document.form1.allRealCost.value;
		document.form1.allRealCost.value=parseInt(allRealCost)-parseInt(hideValue2)+parseInt(goodsRealCost2);
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(hideValue2)-parseInt(goodsRealCost2);
		hideInput2[0].value=goodsRealCost2;
	}
	else{
		alert("输入金额错误，请检查后重新输入");
		goodsRealCostInput[0].focus();
	}
}

function chooseFuneralGoods2(obj){
	var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]
//	var goodsBeCost2=tr.cells[8].innerText;
	var goodsBeCost=tr.cells[8];
	var goodsBeCostInput=goodsBeCost.getElementsByTagName("input");
	var goodsBeCost2=goodsBeCostInput[0].value;
	
	var goodsRealCost=tr.cells[9];
	var goodsRealCostInput=goodsRealCost.getElementsByTagName("input");
	var goodsRealCost2=goodsRealCostInput[0].value;
	if(obj.checked==true){
		goodsBeCostInput[0].disabled="";
		goodsRealCostInput[0].disabled="";
		var preAllBeCost=document.form1.allBeCost.value;
		var preAllRealCost=document.form1.allRealCost.value;
		var curAllBeCost=parseInt(goodsBeCost2)+parseInt(preAllBeCost);
		var curAllRealCost=parseInt(goodsRealCost2)+parseInt(preAllRealCost);
		document.form1.allBeCost.value=curAllBeCost;
		document.form1.allRealCost.value=curAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(goodsBeCost2)-parseInt(goodsRealCost2);
	}
	if(obj.checked==false){
		goodsBeCostInput[0].disabled="true";
		goodsRealCostInput[0].disabled="true";
		var preAllBeCost=document.form1.allBeCost.value;
		var preAllRealCost=document.form1.allRealCost.value;
		var curAllBeCost=preAllBeCost-goodsBeCost2;
		var curAllRealCost=preAllRealCost-goodsRealCost2;
		document.form1.allBeCost.value=curAllBeCost;
		document.form1.allRealCost.value=curAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(goodsBeCost2)+parseInt(goodsRealCost2);
	}
}
function checkNumber(number){
	var reg = /^\d+(\.\d+)?$/;
	if(reg.test(number)==true){
	    return true;
	}else{
	    return false;
	}
}
function getFee(deadId) {// 将火化信息表导成Excel
		
	url = "deadId=" + deadId;
		
	http_request = createHttpRequest();

	http_request.onreadystatechange = getFeeCallBack;

	http_request.open('POST', "GetFeeAction!Money", false);

	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	http_request.send(url);


	return false;// 结束时间

}
function getFeeCallBack(){
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			           
			var json=eval("("+http_request.responseText+")");
			
												
			
			json=eval("("+json+")")
			
			var remainsCarBeCost=Number(json[0].remainsCarBeCost);
			var remainsCarRealCost=Number(json[0].remainsCarRealCost);			
			var coffinRentBeCost=Number(json[0].coffinRentBeCost);
			var coffinRentRealcost=Number(json[0].coffinRentRealcost);
			var coffinCarBeCost=Number(json[0].coffinCarBeCost);
			var coffinCarRealCost=Number(json[0].coffinCarRealCost);
			var villaBeCost=Number(json[0].villaBeCost);
			var villaRealCost=Number(json[0].villaRealCost);
			var villaCoffinBeCost=Number(json[0].villaCoffinBeCost);
			var villaCoffinrealCost=Number(json[0].villaCoffinrealCost);

			document.getElementById("allBeCost").value=remainsCarBeCost+coffinRentBeCost+coffinCarBeCost+villaBeCost+villaCoffinBeCost;
//			alert(document.getElementById("allBeCost").value);
			document.getElementById("allRealCost").value=remainsCarRealCost+coffinRentRealcost+coffinCarRealCost+villaRealCost+villaCoffinrealCost;
			document.getElementById("allRealCost").value=document.getElementById("allBeCost").value-document.getElementById("allRealCost").value;
//			alert(document.getElementById("allRealCost").value);
			//租用冰棺费用
			document.getElementById("crystal1").innerHTML="<font style='font-weight:bold;'>"+(Number(coffinRentBeCost)+Number(villaCoffinBeCost))+"</font>";
			if(document.getElementById("crystal1").innerText=="-0"){
				document.getElementById("crystal1").innerHTML="";
			}
			document.getElementById("crystal2").innerHTML="-"+"<font style='font-weight:bold;'>"+(Number(coffinRentBeCost)+Number(villaCoffinBeCost)-Number(coffinRentRealcost)-Number(villaCoffinrealCost))+"</font>";
			if(document.getElementById("crystal2").innerText=="-0"){
				document.getElementById("crystal2").innerHTML="";
			}
			//守灵费用
			document.getElementById("watch1").innerHTML="<font style='font-weight:bold;'>"+villaBeCost+"</font>";
//			alert(document.getElementById("watch1").innerHTML);
			if(document.getElementById("watch1").innerText=="0"){
				document.getElementById("watch1").innerHTML="";
			}
			
			document.getElementById("watch2").innerHTML="-"+"<font style='font-weight:bold;'>"+(villaBeCost-villaRealCost)+"</font>";
//			alert(document.getElementById("watch2").innerText);
			if(document.getElementById("watch2").innerText=="-0"){
							document.getElementById("watch2").innerHTML="";
			}

			//遗体接运费用
			document.getElementById("car1").innerHTML="<font style='font-weight:bold;'>"+remainsCarBeCost+"</font>";
			if(document.getElementById("car1").innerText=="0"){
				document.getElementById("car1").innerHTML="";
			}
			document.getElementById("car2").innerHTML="-"+"<font style='font-weight:bold;'>"+(remainsCarBeCost-remainsCarRealCost)+"</font>";
			if(document.getElementById("car2").innerText=="-0"){
				document.getElementById("car2").innerHTML="";
			}
			//用棺捷运费用
			document.getElementById("car4").innerHTML="<font style='font-weight:bold;'>"+coffinCarBeCost+"</font>";
//			alert(document.getElementById("car4").innerHTML);
			if(document.getElementById("car4").innerText=="0"){
				document.getElementById("car4").innerHTML="";
			}
			document.getElementById("car5").innerHTML="-"+"<font style='font-weight:bold;'>"+(coffinCarBeCost-coffinCarRealCost)+"</font>";
			if(document.getElementById("car5").innerText=="-0"){
				document.getElementById("car5").innerHTML="";
			}
								
		}

	}
		
}
function outPutServiceList(){
	var myDate = new Date();
	
	//"<font style='font-weight:bold;'>"+urn.options[urn1].text+"</font>";
	//生成新的表单前先清空表单中之前的数据
	document.getElementById("name").innerHTML="";
	document.getElementById("no").innerHTML="";
	document.getElementById("currentDateTh2").innerHTML="";
	document.getElementById("taxDate").innerHTML="";
	document.getElementById("sex").innerHTML="";
	document.getElementById("age").innerHTML="";
	document.getElementById("address").innerHTML="";
	
	document.getElementById("urn").innerHTML="";
	document.getElementById("urn1").innerHTML="";
	document.getElementById("urn2").innerHTML="";
	document.getElementById("face").innerHTML="";
	document.getElementById("face1").innerHTML="";
	document.getElementById("face2").innerHTML="";
	document.getElementById("farewell").innerHTML="";
	document.getElementById("farewell1").innerHTML="";
	document.getElementById("farewell2").innerHTML="";
	document.getElementById("cremation").innerHTML="";
	document.getElementById("cremation1").innerHTML="";
	document.getElementById("cremation2").innerHTML="";


	document.getElementById("car").innerHTML="";
//	document.getElementById("car1").innerHTML="";
//	document.getElementById("car2").innerHTML="";

	
	var allGoodsTbody=document.getElementById("alladd");
	var allFuneralGoodsRows=allGoodsTbody.rows.length;
	for(var n=0;n<allFuneralGoodsRows;n++){
//		var goodsName1=allGoods.rows[n].cells[0].innerText;
		allGoodsTbody.rows[n].cells[1].innerHTML="";
		allGoodsTbody.rows[n].cells[2].innerHTML="";
		allGoodsTbody.rows[n].cells[3].innerHTML="";
		var goods2=allGoodsTbody.rows[n].cells[4].innerText;
		if(goods2!=""){
			allGoodsTbody.rows[n].cells[5].innerHTML="";
			allGoodsTbody.rows[n].cells[6].innerHTML="";
			allGoodsTbody.rows[n].cells[7].innerHTML="";
		}
		var goods3=allGoodsTbody.rows[n].cells[8].innerText;
		if(goods3!=""){
			allGoodsTbody.rows[n].cells[9].innerHTML="";
			allGoodsTbody.rows[n].cells[10].innerHTML="";
			allGoodsTbody.rows[n].cells[11].innerHTML="";
		}
		
	}
	
	document.getElementById("total").innerHTML="";
	document.getElementById("beCost").innerHTML="";
	document.getElementById("charge").innerHTML="";
	document.getElementById("capitalMoney").innerHTML="";
	
	//清空先前生成的表单后生成新的表单
	var cremationTable = document.getElementById("cremationTable");
	
	
	var rows=cremationTable.rows.length;
	var cells=cremationTable.rows[1].cells.length;
	
	var name=document.getElementById("servicedeadName").value;
	for(var i=2;i<rows;i++){
		
		
		var deadName=cremationTable.rows[i].cells[2].innerText;
		
		
		if(name==deadName){
			
			document.getElementById("name").innerHTML="<font style='font-weight:bold;font-size:12px'>"+name+"</font>";
			document.getElementById("no").innerHTML="<font style='font-weight:bold;font-size:12px'>"+cremationTable.rows[i].cells[5].innerText+"</font>";
			document.getElementById("currentDateTh2").innerHTML="<font style='font-weight:bold;font-size:12px'>"+cremationTable.rows[i].cells[1].innerText+"</font>";
			document.getElementById("sex").innerHTML="<font style='font-weight:bold;font-size:12px'>"+cremationTable.rows[i].cells[3].innerText+"</font>";
			document.getElementById("age").innerHTML="<font style='font-weight:bold;font-size:12px'>"+cremationTable.rows[i].cells[4].innerText+"</font>";
			document.getElementById("taxDate").innerHTML="<font style='font-weight:bold;'>"+myDate.toLocaleDateString()+" "+myDate.toLocaleTimeString()+"</font>";
			document.getElementById("address").innerHTML="<font style='font-weight:bold;font-size:12px'>"+cremationTable.rows[i].cells[6].innerText+"</font>";
		}
	}
	
	var allServiceShouldCost=form1.allBeCost.value;
//	
	if(form1.theWholeCost.value!="0"){
		document.getElementById("total").innerHTML="<font style='font-weight:bold;'>"+form1.theWholeCost.value+"</font>";
	}
	if(allServiceShouldCost!="0"){
		document.getElementById("beCost").innerHTML="<font style='font-weight:bold;'>"+allServiceShouldCost+"</font>";
	}
	if(form1.allRealCost.value!="0"){
		document.getElementById("charge").innerHTML="<font style='font-weight:bold;'>"+"-"+form1.allRealCost.value+"</font>";
	}
	

	var urn = document.getElementById("urnChoose");
    var urn1 = urn.selectedIndex;
	if((document.getElementById("urnChooseBox").checked)&&(urn.options[urn1].text!="-请选择-")){
		document.getElementById("urn").innerHTML="<font style='font-weight:bold;'>"+urn.options[urn1].text+"</font>";
	    document.getElementById("urn1").innerHTML="<font style='font-weight:bold;'>"+document.getElementById("urnBeCost").value+"</font>";
	    if(document.getElementById("urnRealCost").value!="0"){
	    	document.getElementById("urn2").innerHTML="<font style='font-weight:bold;'>"+"-"+document.getElementById("urnRealCost").value+"</font>";
	    }
	    else{
	    	document.getElementById("urn2").innerHTML="";
	    }
	}
	
	var beauty = document.getElementById("makeBeautyGrade");
    var beauty1 = beauty.selectedIndex;
	if((document.getElementById("makeBeautyBox").checked)&&(beauty.options[beauty1].text)!="-请选择-"){
		document.getElementById("face").innerHTML="<font style='font-weight:bold;'>"+beauty.options[beauty1].text+"</font>";
	    document.getElementById("face1").innerHTML="<font style='font-weight:bold;'>"+document.getElementById("makeBeautyBeCost").value+"</font>";
	    if(document.getElementById("makeBeautyRealCost").value!="0"){
	    	document.getElementById("face2").innerHTML="<font style='font-weight:bold;'>"+"-"+document.getElementById("makeBeautyRealCost").value+"</font>";
	    }
	    else{
	    	document.getElementById("face2").innerHTML="";
	    }
	    
	}
	
	var leave = document.getElementById("leaveRoomGrade");
    var leave1 = leave.selectedIndex;
	if((document.getElementById("leaveRoomBox").checked)&&(leave.options[leave1].text!="-请选择-")){

		document.getElementById("farewelll").innerHTML="<font style='font-weight:bold;'>"+leave.options[leave1].text+"</font>";
	    document.getElementById("farewell1").innerHTML="<font style='font-weight:bold;'>"+document.getElementById("leaveRoomBeCost").value+"</font>";
	    if(document.getElementById("leaveRoomRealCost").value!="0"){
	    	document.getElementById("farewell2").innerHTML="<font style='font-weight:bold;'>"+"-"+document.getElementById("leaveRoomRealCost").value+"</font>";
	    }
	    else{
	    	document.getElementById("farewell2").innerHTML="";
	    }
	}
	
	var cremation = document.getElementById("cremationGrade");
    var cremation1 = cremation.selectedIndex;
	if((document.getElementById("cremationBox").checked)&&(cremation.options[cremation1].text!="-请选择-")){
		document.getElementById("cremation").innerHTML="<font style='font-weight:bold;'>"+cremation.options[cremation1].text+"</font>";
	    document.getElementById("cremation1").innerHTML="<font style='font-weight:bold;'>"+document.getElementById("cremationBeCost").value+"</font>";
	    if(document.getElementById("cremationRealCost").value!="0"){
	    	document.getElementById("cremation2").innerHTML="<font style='font-weight:bold;'>"+"-"+document.getElementById("cremationRealCost").value+"</font>";
	    }
	    else{
	    	document.getElementById("cremation2").innerHTML="";
	    }
	    
	}
	var rows=showallFuneralGoods.rows.length;
	var rows1=allGoods.rows.length;
	var tableName=document.getElementById("showallFuneralGoods");
	
	if(rows>2){
		
		for(var i=2;i<rows;i++)
		{
			var goodsCheckBoxInput1=tableName.rows[i].cells[3].getElementsByTagName("input");
			var goodsCheckBoxInput2=tableName.rows[i].cells[10].getElementsByTagName("input");
			if(goodsCheckBoxInput1[0].checked){
				var goodsName=showallFuneralGoods.rows[i].cells[0].innerText;
				
				var goodsBeCost=showallFuneralGoods.rows[i].cells[1].getElementsByTagName("input")[0].value;
				
				var goodsRealCostInput=showallFuneralGoods.rows[i].cells[2].getElementsByTagName("input");
				var goodsRealCost=goodsRealCostInput[0].value;
				for(var n=4;n<rows1;n++)			
				{	
					var goodsName1=allGoods.rows[n].cells[0].innerText;
					var goodsName2=allGoods.rows[n].cells[4].innerText;
					var goodsName3=allGoods.rows[n].cells[8].innerText;
					if(goodsName==goodsName1)
					{					
						allGoods.rows[n].cells[2].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
						if(goodsRealCost!="0"){
							allGoods.rows[n].cells[3].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
						}
						else{
							allGoods.rows[n].cells[3].innerHTML = "";
						}
						break;
					}
					else if(goodsName==goodsName2)
					{
						allGoods.rows[n].cells[6].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
						if(goodsRealCost!="0"){
							allGoods.rows[n].cells[7].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
						}
						else{
							allGoods.rows[n].cells[7].innerHTML = "";
						}
						break;
					}
					
					else if(goodsName==goodsName3)
					{					
						allGoods.rows[n].cells[10].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
						if(goodsRealCost!="0"){
							allGoods.rows[n].cells[11].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
						}
						else{
							allGoods.rows[n].cells[11].innerHTML = "";
						}
						break;
					}
				}
			}
			if(goodsCheckBoxInput2[0]!=null){
				if(goodsCheckBoxInput2[0].checked){
					var goodsName=showallFuneralGoods.rows[i].cells[7].innerText;
					
					var goodsBeCost=showallFuneralGoods.rows[i].cells[8].getElementsByTagName("input")[0].value;
					
					var goodsRealCostInput=showallFuneralGoods.rows[i].cells[9].getElementsByTagName("input");
					var goodsRealCost=goodsRealCostInput[0].value;
					for(var n=4;n<rows1;n++)			
					{	
						var goodsName1=allGoods.rows[n].cells[0].innerText;
						var goodsName2=allGoods.rows[n].cells[4].innerText;
						var goodsName3=allGoods.rows[n].cells[8].innerText;
						if(goodsName==goodsName1)
						{					
							allGoods.rows[n].cells[2].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
							if(goodsRealCost!="0"){
								allGoods.rows[n].cells[3].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
							}
							else{
								allGoods.rows[n].cells[3].innerHTML = "";
							}
							break;
						}
						else if(goodsName==goodsName2)
						{
							allGoods.rows[n].cells[6].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
							if(goodsRealCost!="0"){
								allGoods.rows[n].cells[7].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
							}
							else{
								allGoods.rows[n].cells[7].innerHTML = "";
							}
							break;
						}
						
						else if(goodsName==goodsName3)
						{					
							allGoods.rows[n].cells[10].innerHTML= "<font style='font-weight:bold;'>"+goodsBeCost+"</font>";
							if(goodsRealCost!="0"){
								allGoods.rows[n].cells[11].innerHTML= "<font style='font-weight:bold;'>"+"-"+goodsRealCost+"</font>";
							}
							else{
								allGoods.rows[n].cells[11].innerHTML = "";
							}
							break;
						}
					}
				}
			}
		}
	}

	var number = document.getElementById("total").innerText;
    if (!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test(number)) {
        return false;
    }
    var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
    number += "00";
    var point = number.indexOf('.');
    if (point >= 0) {
        number = number.substring(0, point) + number.substr(point + 1, 2);
    }
    unit = unit.substr(unit.length - number.length);
    for (var i = 0; i < number.length; i++) {
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(number.charAt(i)) + unit.charAt(i);
    }
    //document.getElementById(idCHN).value = str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
    document.getElementById("capitalMoney").innerHTML = "<font style='font-weight:bold;'>"+str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整")+"</font>";
}
function cancelUrnCost(){
		
	if(document.getElementById("urnChooseBox").checked==false){
		document.getElementById("urnChoose").disabled="true";
		document.getElementById("urnBeCost").disabled="true";
		document.getElementById("urnRealCost").disabled="true";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curUrnBeCost=document.getElementById("urnBeCost").value;
		var curUrnRealCost=document.getElementById("urnRealCost").value;
		var lastAllBeCost=curAllBeCost-curUrnBeCost;
		var lastAllRealCost=curAllRealCost-curUrnRealCost;
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=document.form1.theWholeCost.value-parseInt(curUrnBeCost)+parseInt(curUrnRealCost);
	}
	if(document.getElementById("urnChooseBox").checked==true){
		document.getElementById("urnChoose").disabled="";
		document.getElementById("urnBeCost").disabled="";
		document.getElementById("urnRealCost").disabled="";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curUrnBeCost=document.getElementById("urnBeCost").value;
		var curUrnRealCost=document.getElementById("urnRealCost").value;
		var lastAllBeCost=parseInt(curAllBeCost)+parseInt(curUrnBeCost);
		var lastAllRealCost=parseInt(curAllRealCost)+parseInt(curUrnRealCost);
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(curUrnBeCost)-parseInt(curUrnRealCost);
	}
}
function cancelBeautyCost(){
	if(document.getElementById("makeBeautyBox").checked==false){
		document.getElementById("makeBeautyGrade").disabled="true";
		document.getElementById("makeBeautyBeCost").disabled="true";
		document.getElementById("makeBeautyRealCost").disabled="true";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curBeautyBeCost=document.getElementById("makeBeautyBeCost").value;
		var curBeautyRealCost=document.getElementById("makeBeautyRealCost").value;
		var lastAllBeCost=curAllBeCost-curBeautyBeCost;
		var lastAllRealCost=curAllRealCost-curBeautyRealCost;
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(curBeautyBeCost)+parseInt(curBeautyRealCost);
	}
	if(document.getElementById("makeBeautyBox").checked==true){
		document.getElementById("makeBeautyGrade").disabled="";
		document.getElementById("makeBeautyBeCost").disabled="";
		document.getElementById("makeBeautyRealCost").disabled="";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curBeautyBeCost=document.getElementById("makeBeautyBeCost").value;
		var curBeautyRealCost=document.getElementById("makeBeautyRealCost").value;
		var lastAllBeCost=parseInt(curAllBeCost)+parseInt(curBeautyBeCost);
		var lastAllRealCost=parseInt(curAllRealCost)+parseInt(curBeautyRealCost);
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(curBeautyBeCost)-parseInt(curBeautyRealCost);
	}
}
function cancelLeaveRoomCost(){
	if(document.getElementById("leaveRoomBox").checked==false){
		document.getElementById("leaveRoomGrade").disabled="true";
		document.getElementById("leaveRoomBeCost").disabled="true";
		document.getElementById("leaveRoomRealCost").disabled="true";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curLeaveRoomBeCost=document.getElementById("leaveRoomBeCost").value;
		var curLeaveRoomRealCost=document.getElementById("leaveRoomRealCost").value;
		var lastAllBeCost=curAllBeCost-curLeaveRoomBeCost;
		var lastAllRealCost=curAllRealCost-curLeaveRoomRealCost;
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(curLeaveRoomBeCost)+parseInt(curLeaveRoomRealCost);
	}
	if(document.getElementById("leaveRoomBox").checked==true){
		document.getElementById("leaveRoomGrade").disabled="";
		document.getElementById("leaveRoomBeCost").disabled="";
		document.getElementById("leaveRoomRealCost").disabled="";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curLeaveRoomBeCost=document.getElementById("leaveRoomBeCost").value;
		var curLeaveRoomRealCost=document.getElementById("leaveRoomRealCost").value;
		var lastAllBeCost=parseInt(curAllBeCost)+parseInt(curLeaveRoomBeCost);
		var lastAllRealCost=parseInt(curAllRealCost)+parseInt(curLeaveRoomRealCost);
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(curLeaveRoomBeCost)-parseInt(curLeaveRoomRealCost);
	}
}
function cancelCremationCost(){
	if(document.getElementById("cremationBox").checked==false){
		document.getElementById("cremationGrade").disabled="true";
		document.getElementById("cremationBeCost").disabled="true";
		document.getElementById("cremationRealCost").disabled="true";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curCremationBeCost=document.getElementById("cremationBeCost").value;
		var curCremationRealCost=document.getElementById("cremationRealCost").value;
		var lastAllBeCost=curAllBeCost-curCremationBeCost;
		var lastAllRealCost=curAllRealCost-curCremationRealCost;
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(curCremationBeCost)+parseInt(curCremationRealCost);
	}
	if(document.getElementById("cremationBox").checked==true){
		document.getElementById("cremationGrade").disabled="";
		document.getElementById("cremationBeCost").disabled="";
		document.getElementById("cremationRealCost").disabled="";
		var curAllBeCost=document.getElementById("allBeCost").value;
		var curAllRealCost=document.getElementById("allRealCost").value;
		var curCremationBeCost=document.getElementById("cremationBeCost").value;
		var curCremationRealCost=document.getElementById("cremationRealCost").value;
		var lastAllBeCost=parseInt(curAllBeCost)+parseInt(curCremationBeCost);
		var lastAllRealCost=parseInt(curAllRealCost)+parseInt(curCremationRealCost);
		document.getElementById("allBeCost").value=lastAllBeCost;
		document.getElementById("allRealCost").value=lastAllRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(curCremationBeCost)-parseInt(curCremationRealCost);
	}
}
function setSelected(selectObj,optionsText){  //设置下拉框默认值
    for(var i=0;i<selectObj.options.length;i++){
        if(selectObj.options[i].text==optionsText){
            selectObj.options[i].selected=true;
            return i;
        }
    }
}
function getLeaveRoomBeCost(){
	var leaveRoom=document.getElementById("leaveRoomGrade");
	var leaveRoomIndex=leaveRoom.selectedIndex;
	var leaveRoomName=leaveRoom.options[leaveRoomIndex].text;
	url="itemName="+leaveRoomName;
	http_request=createHttpRequest();
	http_request.onreadystatechange=getLeaveRoomBeCostCallBack;
	http_request.open('POST',"GetServiceBeCostAction!getServiceBeCost",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function getLeaveRoomBeCostCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			var leaveRoomBeCost=document.getElementById("leaveRoomBeCost");
			leaveRoomBeCost.value=json.price;
			document.getElementById("leaveRoomRealCost").value=0;
			document.getElementById("leaveRoomRealCost").onchange();
			var leaveRoomRealCost=document.getElementById("leaveRoomRealCost");
			
			var curLeaveRoomBeCost=leaveRoomBeCost.value;
			var curLeaveRoomRealCost=leaveRoomRealCost.value;
			var preLeaveRoomBeCost=document.getElementById("preLeaveRoomBeCost").value;
			var preLeaveRoomRealCost=document.getElementById("preLeaveRoomRealCost").value;
			var changeLeaveRoomBeCost=curLeaveRoomBeCost-preLeaveRoomBeCost;
			var changeLeaveRoomRealCost=curLeaveRoomRealCost-preLeaveRoomRealCost;
			var preAllBeCost=document.getElementById("allBeCost").value;
			var preAllRealCost=document.getElementById("allRealCost").value;
			var allBeCost=parseInt(preAllBeCost)+parseInt(changeLeaveRoomBeCost);
			var allRealCost=parseInt(preAllRealCost)+parseInt(changeLeaveRoomRealCost);
			document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(changeLeaveRoomRealCost)+parseInt(changeLeaveRoomBeCost);
			document.getElementById("allBeCost").value=allBeCost;
			document.getElementById("allRealCost").value=allRealCost;
			document.getElementById("preLeaveRoomBeCost").value=curLeaveRoomBeCost;
			document.getElementById("preLeaveRoomRealCost").value=curLeaveRoomRealCost;
		}
	}
}

function getCremationBeCost(){
	var cremation=document.getElementById("cremationGrade");
	var cremationIndex=cremation.selectedIndex;
	var cremationName=cremation.options[cremationIndex].text;
	url="itemName="+cremationName;
	http_request=createHttpRequest();
	http_request.onreadystatechange=getCremationBeCostCallBack;
	http_request.open('POST',"GetServiceBeCostAction!getServiceBeCost",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function getCremationBeCostCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			var cremationBeCost=document.getElementById("cremationBeCost");
			cremationBeCost.value=json.price;
			document.getElementById("cremationRealCost").value=0;
			document.getElementById("cremationRealCost").onchange();
			var cremationRealCost=document.getElementById("cremationRealCost");
			
			var curCremationBeCost=cremationBeCost.value;
			var curCremationRealCost=cremationRealCost.value;
			var preCremationBeCost=document.getElementById("preCremationBeCost").value;
			var preCremationRealCost=document.getElementById("preCremationRealCost").value;
			var changeCremationBeCost=curCremationBeCost-preCremationBeCost;
			var changeCremationRealCost=curCremationRealCost-preCremationRealCost;
			var preAllBeCost=document.getElementById("allBeCost").value;
			var preAllRealCost=document.getElementById("allRealCost").value;
			var allBeCost=parseInt(preAllBeCost)+parseInt(changeCremationBeCost);
			var allRealCost=parseInt(preAllRealCost)+parseInt(changeCremationRealCost);
			document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(changeCremationRealCost)+parseInt(changeCremationBeCost);
			document.getElementById("allBeCost").value=allBeCost;
			document.getElementById("allRealCost").value=allRealCost;
			document.getElementById("preCremationBeCost").value=curCremationBeCost;
			document.getElementById("preCremationRealCost").value=curCremationRealCost;
		}
	}
}
function getMakeBeautyBeCost(){
	var makeBeauty=document.getElementById("makeBeautyGrade");
	var makeBeautyIndex=makeBeauty.selectedIndex;
	var makeBeautyName=makeBeauty.options[makeBeautyIndex].text;
	url="itemName="+makeBeautyName;
	http_request=createHttpRequest();
	http_request.onreadystatechange=getMakeBeautyBeCostCallBack;
	http_request.open('POST',"GetServiceBeCostAction!getServiceBeCost",false);
	http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	http_request.send(url);
	return false;//阻止页面刷新
}
function getMakeBeautyBeCostCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			var makeBeautyBeCost=document.getElementById("makeBeautyBeCost");
			makeBeautyBeCost.value=json.price;
			document.getElementById("makeBeautyRealCost").value=0;
			document.getElementById("makeBeautyRealCost").onchange();
			var makeBeautyRealCost=document.getElementById("makeBeautyRealCost");
			
			var curBeautyBeCost=makeBeautyBeCost.value;
			var curBeautyRealCost=makeBeautyRealCost.value;
			var preMakeBeautyBeCost=document.getElementById("preMakeBeautyBeCost").value;
			var preMakeBeautyRealCost=document.getElementById("preMakeBeautyRealCost").value;
			var changeBeautyBeCost=curBeautyBeCost-preMakeBeautyBeCost;
			var changeBeautyRealCost=curBeautyRealCost-preMakeBeautyRealCost;
			var preAllBeCost=document.getElementById("allBeCost").value;
			var preAllRealCost=document.getElementById("allRealCost").value;
			var allBeCost=parseInt(preAllBeCost)+parseInt(changeBeautyBeCost);
			var allRealCost=parseInt(preAllRealCost)+parseInt(changeBeautyRealCost);
			document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(changeBeautyBeCost)+parseInt(changeBeautyRealCost);
			document.getElementById("allBeCost").value=allBeCost;
			document.getElementById("allRealCost").value=allRealCost;
			document.getElementById("preMakeBeautyBeCost").value=curBeautyBeCost;
			document.getElementById("preMakeBeautyRealCost").value=curBeautyRealCost;
		}
	}
}
function getUrnBeCost(){
	
	var urnChooseBox=document.getElementById("urnChooseBox");
	if(urnChooseBox.checked==true){
		var urnChoose=document.getElementById("urnChoose");
		var urnChooseIndex=urnChoose.selectedIndex;
		var urnName=urnChoose.options[urnChooseIndex].text;
		url="urnName="+urnName;
		http_request=createHttpRequest();
		http_request.onreadystatechange=getUrnBeCostCallBack;
		http_request.open('POST',"GetUrnBeCostAction!getUrnBeCost",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;//阻止页面刷新
	}
}

function getUrnBeCostCallBack(){
	if(http_request.readyState==4){
		if(http_request.status==200){
			var json=eval("("+http_request.responseText+")");
			json=eval("("+json+")");
			
			var urnBeCost=document.getElementById("urnBeCost");
			urnBeCost.value=json.urnBeCost;
			
			document.getElementById("urnRealCost").value=0;
			document.getElementById("urnRealCost").onchange();
			var urnRealCost=document.getElementById("urnRealCost");
			
			var curUrnBeCost=urnBeCost.value;
			
			var curUrnRealCost=urnRealCost.value;
			var preUrnBeCost=document.getElementById("preUrnBeCost").value;
			
			var preUrnRealCost=document.getElementById("preUrnRealCost").value;
			var changeUrnBeCost=curUrnBeCost-preUrnBeCost;
			var changeUrnRealCost=curUrnRealCost-preUrnRealCost;
			var preAllBeCost=document.getElementById("allBeCost").value;
			var preAllRealCost=document.getElementById("allRealCost").value;
			
			var allBeCost=parseInt(preAllBeCost)+parseInt(changeUrnBeCost);
			var allRealCost=parseInt(preAllRealCost)+parseInt(changeUrnRealCost);
			document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)+parseInt(changeUrnBeCost)+parseInt(changeUrnRealCost);
			
			document.getElementById("allBeCost").value=allBeCost;
			document.getElementById("allRealCost").value=allRealCost;
			document.getElementById("preUrnBeCost").value=curUrnBeCost;
			document.getElementById("preUrnRealCost").value=curUrnRealCost;
		}
	}
}
function outPrintServiceList(){
	var printContent = document.getElementById("goodsPartListPrint");
	var iframe = document.createElement('IFRAME');
 	var doc = null;
 	
 	iframe.setAttribute('id', 'printIframe');
 	iframe.setAttribute('style', 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
 	
 	document.body.appendChild(iframe);
 	doc = iframe.contentWindow.document;
 	doc.write('<table border="1">' + printContent.innerHTML + '</table>');
	doc.close();
	
	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	document.getElementById("printIframe").parentNode.removeChild(iframe);
}

function changeUrnRealCost(){
	var urnRealCost=document.getElementById("urnRealCost");
	var curUrnRealCost=urnRealCost.value;
	if(checkNumber(curUrnRealCost)){
		var preUrnRealCost=document.getElementById("preUrnRealCost").value;
		var preAllRealCost=document.getElementById("allRealCost").value;
		var changeUrnRealCost=curUrnRealCost-preUrnRealCost;
		var allRealCost=parseInt(preAllRealCost)+parseInt(changeUrnRealCost);
		document.getElementById("allRealCost").value=allRealCost;
		document.getElementById("preUrnRealCost").value=curUrnRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(changeUrnRealCost);
	}
	else{
		urnRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}
function changeBeautyRealCost(){
	var BeautyRealCost=document.getElementById("makeBeautyRealCost");
	var curBeautyRealCost=BeautyRealCost.value;
	if(checkNumber(curBeautyRealCost)){
		var preBeautyRealCost=document.getElementById("preMakeBeautyRealCost").value;
		var preAllRealCost=document.getElementById("allRealCost").value;
		var changeBeautyRealCost=curBeautyRealCost-preBeautyRealCost;
		var allRealCost=parseInt(preAllRealCost)+parseInt(changeBeautyRealCost);
		document.getElementById("allRealCost").value=allRealCost;
		document.getElementById("preMakeBeautyRealCost").value=curBeautyRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(changeBeautyRealCost);
	}
	else{
		BeautyRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}function changeLeaveRoomRealCost(){
	var leaveRoomRealCost=document.getElementById("leaveRoomRealCost");
	var curLeaveRoomRealCost=leaveRoomRealCost.value;
	if(checkNumber(curLeaveRoomRealCost)){
		var preLeaveRoomRealCost=document.getElementById("preLeaveRoomRealCost").value;
		var preAllRealCost=document.getElementById("allRealCost").value;
		var changeLeaveRoomRealCost=curLeaveRoomRealCost-preLeaveRoomRealCost;
		var allRealCost=parseInt(preAllRealCost)+parseInt(changeLeaveRoomRealCost);
		document.getElementById("allRealCost").value=allRealCost;
		document.getElementById("preLeaveRoomRealCost").value=curLeaveRoomRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(changeLeaveRoomRealCost);
	}
	else{
		leaveRoomRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}

function changeCremationRealCost(){
	var cremationRealCost=document.getElementById("cremationRealCost");
	var curCremationRealCost=cremationRealCost.value;
	if(checkNumber(curCremationRealCost)){
		var preCremationRealCost=document.getElementById("preCremationRealCost").value;
		var preAllRealCost=document.getElementById("allRealCost").value;
		var changeCremationRealCost=curCremationRealCost-preCremationRealCost;
		var allRealCost=parseInt(preAllRealCost)+parseInt(changeCremationRealCost);
		document.getElementById("allRealCost").value=allRealCost;
		document.getElementById("preCremationRealCost").value=curCremationRealCost;
		document.form1.theWholeCost.value=parseInt(document.form1.theWholeCost.value)-parseInt(changeCremationRealCost);
	}
	else{
		cremationRealCost.focus();
		alert("输入金额错误，请检查后重新输入");
	}
}
function deleteGoodsTable(){
	
	var showallFuneralGoods=document.getElementById("showallFuneralGoods");
	var rows=showallFuneralGoods.rows.length;
	
	for(var i=2;i<rows;i++){
		showallFuneralGoods.rows[i].cells[2].innerText="";
		var goodsInput=showallFuneralGoods.rows[i].cells[3].getElementsByTagName("input");
		goodsInput[0].checked=false;
		showallFuneralGoods.rows[i].cells[9].innerText="";
		var goodsInput1=showallFuneralGoods.rows[i].cells[10].getElementsByTagName("input");
		goodsInput1[0].checked=false;
	}
}
