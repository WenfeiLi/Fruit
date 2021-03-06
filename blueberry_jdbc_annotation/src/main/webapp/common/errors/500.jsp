<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>服务器内部错误</title>

    <style type="text/css">
        BODY {
            font-family: Tahoma, Arial, sans-serif;
            color: black;
            background-color: white;
            font-size: 12px;
        }

        H1 {
            font-family: Tahoma, Arial, sans-serif;
            color: white;
            background-color: #525D76;
            font-size: 22px;
        }

        PRE, TT {
            border: 1px dotted #525D76
        }

        A {
            color: black;
        }

        A.name {
            color: black;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h1><span>服务器内部错误</span></h1>

    <p><span>对不起，服务器内部出现未知错误，请稍后重试或联系管理员！</span></p>

    <p><a href="${contextPath}/home.html">返回首页</a></p>
</div>
</body>
</html>
