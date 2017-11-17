// JavaScript Document
//功能:导出多个表格到EXCEL或者ET
//调用方法：toExcel('要导出的表格ID,以|分隔多个表格','输出到excel中的工作薄名称','导出的方式，0为不带格式，1为带格式','要导出的列数')
var idTmr = ""; 
function Cleanup() { 
window.clearInterval(idTmr); 
CollectGarbage(); 
} 

function toExcel(tableId,sheetname,method,cols){
	if(!confirm("确认导出数据到EXCEL?")){return false;}
	var tables=tableId.split("|");
	for(var n=0;n<tables.length;n++){
		if(!document.getElementById(tables[n])){
			alert("表格"+tables[n]+"不存在,请检查是否有数据输出");
			return false;
		}
	}
	try{
		var oXL = new ActiveXObject("excel.Application");
	}catch(e1){
		try{
			var oXL = new ActiveXObject("et.Application");
		}catch(e2){
			alert(e2.description+"\n\n\n要使用EXCEL对象，您必须安装Excel电子表格软件\n或者,需要安装Kingsoft ET软件\n\n同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。");
			return;
		}
	}
	
    try {
		var m=1;
        oXL.Visible = true;
		oXL.ScreenUpdating=false;
		//oXL.Calculation=-4135;
		var oWB = oXL.Workbooks.Add;
		var oSheet = oWB.ActiveSheet;
		var xlsheet = oWB.Worksheets(1);
		for(var i=oWB.Worksheets.count;i>1;i--){	//删除多余工作表
			oWB.Worksheets(i).Delete();
		}
		for(var n=0;n<tables.length;n++){
			var elTable = document.getElementById(tables[n]);
			var oRangeRef = document.body.createTextRange();
			oRangeRef.moveToElementText(elTable);
			oRangeRef.execCommand("Copy");
			oSheet.cells(m,1).select;
			oSheet.Paste();	//此方式为直接粘贴，带格式
			if (method == 0) {
				oSheet.cells.ClearFormats;
				//以下删除因表头分拆后产生的空行,一般表头不会超过5行,此处检查5行数据
				for(var delrow=1;delrow<5;delrow++){
					var isBlank=true;
					for(var col=1;col<=elTable.rows[0].cells.length;col++){
						if(oSheet.cells(m+1,col).value!="" && oSheet.cells(m+1,col).value!=undefined){
							isBlank=false;
							break;
						}
					}
					if(isBlank){
						oSheet.Rows(m+1).Delete;
					}
				}
			}
			m+=elTable.rows.length;
		}
        //oSheet.Cells.NumberFormatLocal = "@";//格式化数字时使用
		n=oSheet.Shapes.count;
		for(var i=1;i<=n;i++){
			oSheet.Shapes.Item(1).Delete();		//因为每次删除都会使总数减少,所以删除n次第一个对象,也可以倒过来从大到小删除
		}
		oXL.Selection.CurrentRegion.Select;			//选择当前区域
		oXL.Selection.Interior.Pattern = 0;			//设置底色为空
		oXL.Selection.Borders.LineStyle = 1;		//设置单元格边框为实线
        oXL.Selection.ColumnWidth = 5;				//设置列宽
        oXL.Selection.RowHeight = 16;				//行高

		oXL.Selection.Columns.AutoFit;				//列宽自动适应
		//xlsheet.Columns("A:Z").AutoFit;			//列宽自动适应
		xlsheet.Rows("1:"+m).AutoFit;				//自动行高
		xlsheet.Name=sheetname;
        oSheet = null;
        oWB = null;
        appExcel = null;
		//oXL.Calculation=-4105;
		oXL.ScreenUpdating=true;
		idTmr = window.setInterval("Cleanup();",1); 	//释放Excel进程，回收内存空间，避免产生多个不会自己终止的Excel进程
   	}catch (e) {
		idTmr = window.setInterval("Cleanup();",1);
        alert(e.description);		
    }
}
