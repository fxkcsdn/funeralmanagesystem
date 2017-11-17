/**
 * 
 */
var baseurl="http://localhost:8080/FuneralManageSystem_v2";
var PageSize=10;//分页--每页的记录数

function parseTimeToLocal(datetime)//2016-07-10 00:00:00.0
{
	var date=datetime.substring(0,10);
	var time=datetime.substring(11);
	return ""+date+"T"+time;
}
function parseTimeFromLocal(datetime)//2016-07-10T00:00:00.0
{
	var date=datetime.substring(0,10);
	var time=datetime.substring(11);
	return  ""+date+" "+time;
}
function getYearMonthFromLocal(datetime)//2016-07-10T00:00:00.0
{
	var year=datetime.substring(0,4);
	var month=datetime.substring(5,7);
	return  ""+year+""+month;
}
function convertDateTimeToDate(datetime)//2016-07-10 00:00:00.0
{
	var date=datetime.substring(0,10);
	return  ""+date;
}
//获取系统当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
//保留两位小数 
//功能：将浮点数四舍五入，取小数点后2位 
function toDecimal(x) { 
	var f = parseFloat(x); 
	if (isNaN(f)) { 
	return; 
	} 
	f = Math.round(x*100)/100; 
	return f; 
} 
//保留六位小数 
//功能：将浮点数四舍五入，取小数点后2位 
function toDecimalSix(x) { 
	var f = parseFloat(x); 
	if (isNaN(f)) { 
	return; 
	} 
	f = Math.round(x*1000000)/1000000; 
	return f; 
} 
//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try{t1=arg1.toString().split(".")[1].length}catch(e){}
	try{t2=arg2.toString().split(".")[1].length}catch(e){}
	with(Math){
		r1=Number(arg1.toString().replace(".",""))
		r2=Number(arg2.toString().replace(".",""))
		return (r1/r2)*pow(10,t2-t1);
	}
}

//给Number类型增加一个div方法，调用起来更加 方便。
Number.prototype.div = function (arg){
return accDiv(this, arg);
}

//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以 arg2的精确结果
function accMul(arg1,arg2)
{
var m=0,s1=arg1.toString(),s2=arg2.toString();
try{m+=s1.split(".")[1].length}catch(e){}
try{m+=s2.split(".")[1].length}catch(e){}
return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg){
return accMul(arg, this);
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2){
var r1,r2,m;
try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
m=Math.pow(10,Math.max(r1,r2))
return (arg1*m+arg2*m)/m
}

//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
return accAdd(arg,this);
}
