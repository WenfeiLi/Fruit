<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp" %>
<html>
<head>
    <title>${userInfo.nickname}的个人信息</title>
    <style type="text/css" media="all">
        .user_info {
            position: relative;
            top: 40px;
            left: 145px;
        }

        table {
            border: 1px solid #249bdb;
            width: 700px;
            border-collapse: collapse;
        }

        table td {
            border: 1px solid #249bdb;
            padding: 3px;
        }

        table th {
            border: 1px solid #249bdb;
            padding: 10px;
            background-color: rgb(200, 200, 200);
        }
    </style>
</head>
<body>
<div id="content">
    <div class="user_info">
        <table>
            <caption>${userInfo.nickname}的个人信息</caption>
            <tr>
                <td>用户名</td>
                <td>${userInfo.username}</td>
            </tr>

            <tr>
                <td>昵称</td>
                <td>${userInfo.nickname}</td>
            </tr>

            <tr>
                <td>邮箱</td>
                <td>${userInfo.email}</td>
            </tr>

            <tr>
                <td>生日</td>
                <td>${userInfo.birthday}</td>
            </tr>
        </table>
        <a href="${contextPath}/account/list.html">返回</a>
    </div>
</div>
</body>
</html>
