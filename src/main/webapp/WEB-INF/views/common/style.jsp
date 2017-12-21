<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
    var basePath = "<%=basePath %>"; var resPath = "<%=path %>";
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>博晟 | 后台管理中心</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="<%=basePath %>assets/css/base.css" rel="stylesheet">
<link href="<%=basePath %>assets/css/sui.min.css" rel="stylesheet">
<link href="<%=basePath %>assets/css/sui-append.min.css" rel="stylesheet">
<style>
    .sui-container{
        width:100%!important;
    }
</style>