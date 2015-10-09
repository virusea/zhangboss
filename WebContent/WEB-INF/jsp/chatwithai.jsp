<%@page import="java.util.List"%>
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
    <title>updatePage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=basePath %>static/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand">和机器人聊天！</a>
        </div>
    </div>
    
<form class="well" action="<%=basePath %>chatwithai" method="post">
  
           <label>    发送方：</label><input type="text" class="span3" name="fromuser" value="dmwy"><br>
 	<label> 对话：</label><input type="text" class="span3" name="message" value='<%=request.getParameter("message")%>' placeholder='<%=request.getParameter("message")%>'><br>
  <button type="submit" class="btn">聊天</button>
</form>

<table class="table table-striped table-condensed">
    <thead>
    <tr>
        <th style="width: 40px">id</th>
        <th style="width: 40px">content</th>  
    </tr>
    </thead>
    <tbody>   
    <!-- 遍历打印从数据库中拿出的数据 -->
   	 
   	 	<%
   		 List<String> aiResps = (List<String>)request.getAttribute("aiResps");
   	 	 if(aiResps != null){
   	 		int i = 1;
   	 	 	for(String str : aiResps){
   	 	%>
   	 	<tr>
   	 	   <td ><%=i %></td>
   	 		<td ><%=str %></td>
   	     </tr>
   	 	<% 
   	 		i++;
   	 		}
   	 	 }
   	 	
   	 	%>

   </tbody>
    </table>

 </div>
</body>
</html>