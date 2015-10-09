<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>上传聊天文件</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=basePath %>static/css/bootstrap.css" rel="stylesheet">
  </head>
<body>

  <div class="navbar navbar-fixed-top">
	    <div class="navbar-inner">
	        <div class="container">
	            <a class="brand">此处提交聊天文件!</a>
	        </div>
	    </div>
	 
	    
		<form class="well" action="<%=basePath %>uploadchatfile"  enctype="multipart/form-data" method="post">
		   上传文件： <input type="file" id="chatFile" name="chatFile"><br /> 
		        <input type="submit" value="提交" />
		</form>
		
		
		<div class="navbar-inner">
	        <div class="container">
	            <a class="brand">此处开始对话分词（现在主要是 dmwy 51721 和 zdr 15510）</a>
	        </div>
	    </div>
	 
	    
		<form class="well" action="<%=basePath %>fenci" method="post">
		   后缀名称：  <input type="text" name="surfix" value="dmwy"><br>
		  		 <input type="text" name="lines" value="51721"><br>
		        <input type="submit" value="提交" />
		</form>

   </div>
   

</body>

</html>