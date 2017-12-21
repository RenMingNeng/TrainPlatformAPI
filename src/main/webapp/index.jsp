<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String resourcePath = request.getServletContext().getInitParameter("resource_path") + "/";
%>
<script>
    var basePath = "<%=basePath %>";
    //console.log("basePath="+basePath);
    window.location.href = basePath + "index.html";
</script>
