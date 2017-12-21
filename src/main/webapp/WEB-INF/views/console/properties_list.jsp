<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String resourcePath = request.getServletContext().getInitParameter("resource_path") + "/";
%>
<html>
<head>
    <jsp:include page="${path}/common/header" >
        <jsp:param name="menu" value="console"></jsp:param>
    </jsp:include>
    <jsp:include page="${path}/common/style"  />
    <jsp:include page="${path}/common/script" />
</head>
<script type="text/javascript">

    function modify(key,value){
        var url="<%=path%>/api/v1.0/properties/modify?pkey="+key+"&pvalue="+value;
        layer.open({
            type: 2,
            title: '修改属性值',
            area: ['450px', '170px'],
            shade: 0.3,
            closeBtn: 1,
            shadeClose: false,
            content: url
        });
    }

</script>
<body>
<div id="main" style="padding-bottom: 50px;padding-top: 140px">
    <div class="layoutMid bg-white border-line cw-box" style="padding:0;">
        <div class="sui-container">
            <ul class="sui-breadcrumb">
                <li><a href="<%=basePath %>index.html">首页</a></li>
            </ul>
            <form id="listForm" action="" method="post">
                <table class="sui-table sui-table-nobody">
                    <thead>
                    <tr>
                        <th width="10%">
                            <label class="checkbox">序号</label>
                        </th>
                        <th class="center" width="33%">key值</th>
                        <th class="center" width="33%">value值</th>
                        <th class="center" width="33%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty pmap}">
                                <c:forEach items="${pmap}" var="map" varStatus="vs">
                                 <tr>
                                    <td width="8%">
                                    <label class="checkbox">${vs.index+1}</label>
                                    </td>
                                    <td class="center" width="22%">
                                        ${map.key}
                                    </td>
                                    <td class="center" width="10%">
                                        ${map.value}
                                    </td>
                                    <td class="center" width="10%">
                                            <i class="sui-icon-ok"></i><a href="javascript:modify('${map.key}','${map.value}')">修改</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>

            </form>

<%--            <div class="sui-pagination pagination-right">
                <div>
                    <span>共<span class="sui-text-warning">234</span>个查询结果</span>
                </div>
                <ul>
                    <li class="prev disabled"><a href="#">«上一页</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">6</a></li>
                    <li><a href="#">7</a></li>
                    <li><a href="#">8</a></li>
                    <li class="dotted"><span>...</span></li>
                    <li class="next"><a href="#">下一页»</a></li>
                </ul>
            </div>
        </div>--%>

        <br/><br/><br/><br/><br/>



        <jsp:include page="${path}/common/script" />

</body>
</html>
