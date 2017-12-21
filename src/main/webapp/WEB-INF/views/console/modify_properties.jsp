<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>修改properties属性值</title>
    <jsp:include page="${path}/common/style" />
    <style>
        .cl{overflow: hidden;}
        .r{float: right;}
        .l{float: left;}
        .cl > *{ padding:5px 10px;line-height: 25px; }
        label{text-align:right;padding-right:20px;width:66px;font-weight: bold;}
        .cl > .r{width:300px;}
        .r input[type="text"]{width:98%;padding:3px;}
        .btn-info{padding:0 6px;}
    </style>
</head>
<body style="min-width:100px;">
<div>
    <div class="area" style="width:auto;">

        <div class="box pd_b_10" style="margin-bottom:0;">
            <div>

                <div class="cl">
                    <label class="l">key值：</label>
                    <div class="r">
                        <input type="hidden" id="pkey" name="pvalue" value="${pkey}" />${pkey}<font color="red">(不可修改)</font>
                    </div>
                </div>
                <div class="cl">
                    <label class="l">value值：</label>
                    <div class="r">
                        <input type="text" id="pvalue" name="pvalue" value="${pvalue}" >
                    </div>
                </div>

                <div class="cl">
                    <label class="l"  style="visibility: hidden;">按钮</label>
                    <div class="r">
                        <a href="javascript:save();" class="btn btn-info pull-right" >保存</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>assets/js/jquery.min.js"></script>
<script src="<%=basePath %>assets/js/layer/layer.js"></script>
<script type="text/javascript">

      function save(){
          if($("#pvalue").val()!=""){
              $.ajax({
                  url:"<%=path %>/api/v1.0/properties/update",
                  async:false,
                  type: "POST",
                  data:{
                      pkey:$("#pkey").val(),
                      pvalue:$("#pvalue").val()

                  },
                  success:function(data){
                      var result=data.data;
                      if(result=="000000"){
                          layer.close(0);
                          layer.alert('修改成功', {icon: 1,  skin: 'layer-ext-moon'});
                          parent.location.href="<%=path%>/api/v1.0/properties/list";
                      }else{
                          layer.alert('修改失败', {icon: 2,  skin: 'layer-ext-moon'});
                      }
                  }
              });
          }

      }

</script>
</body>
</html>
