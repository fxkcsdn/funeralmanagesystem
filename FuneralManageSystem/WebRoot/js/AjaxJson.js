/**
 * Ajax过程函数；
 */

/*
 * 创建xmlHttpRequest;
 */
function createXMLHttpRequest() {
	var XMLHttpReq = false;
	if (window.XMLHttpRequest) {
		//Mozilla浏览器
		XMLHttpReq = new XMLHttpRequest();
		if (XMLHttpReq.overriderMimeType) {
			XMLHttpReq.overrideMimeType('text/xml');
		}
	} else {
		//ie浏览器
		if (window.ActiveXObject) {
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert(e.message);
				}
			}
		}
	}
	return XMLHttpReq;
}
/**
 * 发送数据，并获取服务器返回来的数据；
 * @param url   发送请求的地址ַ
 * @param method 请求的方式"post" or "get"
 * @param data  发送的数据 例如"userName="+userName.value+"&password="+password.value+"&loginType="+loginTypeValue;
 * @param getResult函数参数，获取返回的数据；
 */
function sendRequest(method, url, data, getResult) {
	//alert("11111");
	var result = "";
	var XMLHttpReq = createXMLHttpRequest();
	if (method == "post") {
		//alert("2222");
		XMLHttpReq.open("post", url, true);
		XMLHttpReq.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		XMLHttpReq.onreadystatechange = function() {
			if (XMLHttpReq.readyState == 4) {
				//返回成功；
				if (XMLHttpReq.status == 200) {
					//alert("444");
					result = XMLHttpReq.responseText;
					getResult(result);
				}else if (XMLHttpReq.status == 500) {
					result = XMLHttpReq.responseText;
					alert("程序错误！错误信息：\r\n"+result);
				}
			}
		}
		if (data != null)
			XMLHttpReq.send(data.toString());
		else
			XMLHttpReq.send(null);
	} else if (method == "get") {
		XMLHttpReq.open("get", url + "?" + data, true);
		XMLHttpReq.onreadystatechange = function() {
			if (XMLHttpReq.readyState == 4) {
				//返回成功；
				if (XMLHttpReq.status == 200) {
					result = XMLHttpReq.responseText;
					getResult(result);
				}else if (XMLHttpReq.status == 500) {
					result = XMLHttpReq.responseText;
					alert("程序错误！错误信息：\r\n"+result);
				}
			}
		}
		XMLHttpReq.send();
	}

}
