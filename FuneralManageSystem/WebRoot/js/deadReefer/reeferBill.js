/**
 * 遗体冷藏结算
 */

/**
 * 页面加载时触发该事件
 */
window.onload = function() {
	//获取冰柜信息
	getReefer();
};

/**
 * 请求服务器可用冰柜号
 */
var getReefer = function() 
{
	var data = "";
	var url = "/FuneralManageSystem/addRemainsReefer!getReefer";
	sendRequest("post", url, data, getReeferBack);
};

/**
 * 获取可用冰柜号
 */
var getReeferBack = function(result) 
{
	var dataOfReefer = eval("(" + result + ")");
	dataOfReefer = eval("(" + dataOfReefer + ")");
	// 显示冰柜，图像表示
	showNumberOfReefer(dataOfReefer);
};

/**
 * 显示冰柜信息，图像表示
 * @param dataOfReefer 冰柜信息
 */
var showNumberOfReefer = function(dataOfReefer) 
{
	var dataLength = dataOfReefer.length;
	var p = document.getElementById("reefer");// 冰柜显示区域
	// 遍历每个冰柜
	for (var i = 0; i < dataLength; i++) 
	{
		var image = document.createElement("img");
		image.setAttribute("id", dataOfReefer[i].reeferNo);

		image.setAttribute("width", "35px");
		image.setAttribute("height", "20px");
		if(dataOfReefer[i].bAvailable==1){
			image.setAttribute("src", "Images/green.png");
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "alert('该冰柜还未使用!');");
		}else{
			image.setAttribute("src", "Images/red.png");
			// 为每个图像的onclick事件绑定事件处理函数
			image.setAttribute("onclick", "chooseNumberOfReefer(" + dataOfReefer[i].reeferNo + ")");
		}	
		p.appendChild(image);
		p.innerHTML += "<strong><font size='5'>"
				+ (parseInt(dataOfReefer[i].reeferNo) > 9 ? dataOfReefer[i].reeferNo
						: "0" + dataOfReefer[i].reeferNo) + "</font></strong>";
		p.innerHTML += "&nbsp;&nbsp;";
		// 每5个图像换行
		if ((i + 1) % 4 == 0) 
		{
			p.innerHTML += "<br>";
		}
	}
};

/**
 * 单击图像，获取冰柜号
 * @param reeferN0
 */
var chooseNumberOfReefer = function(reeferNo) 
{
	document.getElementById("reeferNo").value = reeferNo;
};

/**
 * 打印派车单
 */
var printReeferCarryBill = function() {
	var width = window.screen.width;// 宽度
	var height = window.screen.height;// 高度
	var url = "/FuneralManageSystem/printReeferCarryBill.html";// 页面跳转地址
	// 打开子窗口
	window.open(url, "width=" + width + ", height=" + height
			+ ", left=0, top=0");
};