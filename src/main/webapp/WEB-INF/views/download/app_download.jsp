<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>手机</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <style>
        * {
            padding: 0;
            margin: 0;
            text-decoration: none;
            font-style: normal;
            font: normal 100% Helvetica, Arial;
            letter-spacing: 0.25em;
        }

        body {
            background: #f5f8fa;
            padding: 0 35px;
        }

        h5 {
            padding: 2rem 15px;
            font-size: 1.2rem;
            text-align: center;
            color: #5a6163;
        }

        h6 {
            padding: 0.6rem 12px;
            font-size: 1rem;
            color: #ffa55c;
        }

        h4 {
            /* padding: 2rem 15px; */
            font-size: 1.2rem;
            text-align: center;
            color: #5a6163;
            margin-bottom: 1rem;
        }

        img {
            width: 100%;
            height: auto;
            margin-bottom: 2rem;
        }

        div:nth-child(2) {
            /* top:26%; */
            /* 	background-color:green; */
            margin-bottom: 3rem;
        }

        div:nth-child(3) {
            /* top:50%; */
            /* 	background-color:green; */
            margin-bottom: 3rem;
        }

        span {
            display: block;
            width: 100%;
            color: #fff;
            line-height: 1rem;
            text-align: center;
            margin-bottom: 2rem;
            background-color: #fcedc6;
            text-align: left;
        }

        span i {
            display: inline-block;
            font-size: 0.8rem;
            text-align: left;
            line-height: 1.3rem;
            padding: 0rem 12px 1rem 20px;
            color: #555555;
        }
        /* 将button换成a标签 ，加了一个text-align属性*/
        a {
            display: block;
            width: 100%;
            /* 		background:none;
        outline:none; */
            color: #fff;
            line-height: 5rem;
            text-align: center;
            letter-spacing: 0.25em;
            /* 		border:none; */
        }

        a:nth-child(1) {
            background: #2c95e7;
            margin-bottom: 1.8rem;
        }

        a:nth-child(2) {
            background: #1abc9c;
        }
    </style>
</head>
<body>
<h5>手机APP下载</h5>
<div>
        <span>
            <h6>温馨提示</h6>
            <i>
                请点击微信右上角按钮，然后在弹出的菜单中，
                点击在浏览器中打开，即可安装
            </i>
        </span>
</div>
<div>
    <a href="${iosDownloadUrl}">IOS版(IPone)</a>
    <a href="${androidDownloadUrl}">安卓版(Android)</a>
</div>
<div>
    <h4>安卓版下载的提示操作</h4>
    <img src="<%=path%>/assets/imgs/1.png" alt="" />
    <img src="<%=path%>/assets/imgs/2.png" alt="" />
</div>

</body>
</html>
