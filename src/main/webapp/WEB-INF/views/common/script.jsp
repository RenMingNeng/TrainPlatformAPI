<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String resourcePath = request.getServletContext().getInitParameter("resource_path") + "/";
%>

<script src="<%=basePath %>assets/js/jquery.min.js"></script>
<script src="<%=basePath %>assets/js/sui.min.js"></script>
<script src="<%=basePath %>assets/js/layer/layer.js"></script>