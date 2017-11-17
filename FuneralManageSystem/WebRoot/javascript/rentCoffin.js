/**
 * 
 */
	window.onload=initialization;
    function initialization()
    {
      var url = location.search; //获取url中"?"符后的字串
      
       if(url.indexOf("?")!=-1){
       		var str=url.substr(1);

       		var first = url.indexOf("deadId");
       		var second = url.indexOf("index");
       		var third = url.indexOf("type");
       		
       		var deadId=url.substring(first+7, second-1);
       		
       		
       		var index = url.substring(second+6,third-1);
       		
       		var type = url.substring(third+5);
       		
       		
       		document.cookie = "type="+type+"&index="+index;
       
       		if(index!="1"){
       		
       		document.getElementById("tab2").click();
       		document.getElementById("deadIdStorage").value=deadId;
       		
       		}
       		
		
       }
    }