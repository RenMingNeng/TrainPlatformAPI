<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="header">
	<div class="top_tip">
		<div class="layoutMid">
			<a class="help_center" href="#">帮助中心</a>
		</div>
	</div>
	<div class="top_main layoutMid clear">
		<h1 class="logo fl"><a href="#"><img src="<%=basePath %>assets/imgs/logo.png"></a></h1>
		<div class="nav fl">
			<a href="<%=basePath %>api/v1.0/properties/list" class="<c:if test="${param.menu=='console'}">active</c:if>">控制台</a>
		</div>
		<div class="fr clear">
		</div>
	</div>
</div>



