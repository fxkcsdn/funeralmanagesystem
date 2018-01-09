function cremationPhoto(){							
			var deadId=document.getElementById("deadId").value;
			var Name = "D:\\逝者火化证档案照片\\"+deadId+".jpg";   //图片名称|路径//创建摄者档案照片文件夹					
			var img = plugin().Video_CreateImage(Video, 0, view().View_GetObject());
//			var base64=	plugin().Image_GetBase64(img,13, 0x0800);  //获取图片base64代码
			var bSave = plugin().Image_Save(img, Name, 0x0200);
			if (bSave)
			{
				view().View_PlayCaptureEffect();
				thumb().Thumbnail_Add(Name);
			}

			plugin().Image_Release(img);
			 
			var data = "deadId="+deadId+"&name="+Name;
			var url = "/FuneralManageSystem/FilePhotoAction!cremationPhoto";
			sendRequest("post", url, data, cremationPhotoResult);
		}
		function cremationPhotoResult(result)
		{
			var json = eval("(" + result + ")");
				alert(json);
				
		}
		
		function taxPhoto(){							
			var deadId=document.getElementById("deadId").value;
			var Name = "D:\\逝者税票档案照片\\"+deadId+".jpg";   //图片名称|路径//创建摄者档案照片文件夹					
			var img = plugin().Video_CreateImage(Video, 0, view().View_GetObject());
//			var base64=	plugin().Image_GetBase64(img,13, 0x0800);  //获取图片base64代码
			var bSave = plugin().Image_Save(img, Name, 0x0200);
			if (bSave)
			{
				view().View_PlayCaptureEffect();
				thumb().Thumbnail_Add(Name);
			}

			plugin().Image_Release(img);
			 
			var data = "deadId="+deadId+"&Name="+Name;
			var url = "/FuneralManageSystem/FilePhotoAction!TaxPhoto";
			sendRequest("post", url, data, taxPhotoResult);
		}
		function taxPhotoResult(result)
		{
			var json = eval("(" + result + ")");
			alert(json);
			
		}
		
		function benefitPhoto(){							
			var deadId=document.getElementById("deadId").value;
			var Name = "D:\\逝者惠民补助档案照片\\"+deadId+".jpg";   //图片名称|路径//创建摄者档案照片文件夹					
			var img = plugin().Video_CreateImage(Video, 0, view().View_GetObject());
//			var base64=	plugin().Image_GetBase64(img,13, 0x0800);  //获取图片base64代码
			var bSave = plugin().Image_Save(img, Name, 0x0200);
			if (bSave)
			{
				view().View_PlayCaptureEffect();
				thumb().Thumbnail_Add(Name);
			}

			plugin().Image_Release(img);
			 
			var data = "deadId="+deadId+"&Name="+Name;
			var url = "/FuneralManageSystem/FilePhotoAction!benefitPhoto";
			sendRequest("post", url, data, benefitPhotoResult);
		}
		function benefitPhotoResult(result)
		{
			var json = eval("(" + result + ")");
			alert(json);
			
		}