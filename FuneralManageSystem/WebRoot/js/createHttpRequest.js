//此函数创建一个httpRequest对象
function createHttpRequest()
	{
		http_request=false;
		if(window.XMLHttpRequest)
			{
				http_request=new XMLHttpRequest();
			}
		else if(window.ActiveXObject)
			{
				try
				{
					http_request=new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch(e)
				{
					try
					{
						http_request=new ActiveXObject("Microsoft.XMLHTTP");
					}
					catch(e)
					{
						;
					}
				}
			}
		if(!http_request)
			{
				alert("不能创建XMLHttpRequest对象实例!");
				return false;
			}
		return http_request;
	}