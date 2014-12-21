<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp"%>

<html>
<head>
    <title>用户列表</title>

    <style type="text/css">
        .div_users{
            position: relative;
            top: 40px;
            left: 145px;
        }

        table{
            border:1px solid #249bdb;
            width:700px;
            border-collapse:collapse;
        }

        table td{
            border: 1px solid #249bdb;
            padding:3px;
        }

        table th{
            border:1px solid #249bdb;
            padding:10px;
            background-color: rgb(200,200,200);
        }
    </style>
</head>

<body>
<div id="content">
    <div class="div_users">
        <table id="table_users">
            <caption>用户列表</caption>
            <tr>
                <th>用户名</th>
                <th>昵称</th>
                <th>生日</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><a href="${user.username}/info.html">${user.username}</a></td>
                    <td>${user.nickname}</td>
                    <td>${user.birthday}</td>
                    <td>${user.email}</td>
                    <td><a href="${user.username}/update.html">修改</a>|<a href="${user.username}/delete.html">删除</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="add.html">添加用户</a>
    </div>
</div>
</body>
</html>
