<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>

    <script type="text/javascript">
        function doSubmit() {
            var input = document.getElementById("submit");
            input.disabled = 'disabled';
            return true;
        }
    </script>

    <style type="text/css">
        .div_register {
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
<div>
    <div class="div_register">
        <form action="${contextPath}/account/register.html" method="post" onsubmit="return doSubmit()">
            <input type="hidden" name="token" value="${token}">
            <table id="formtable">
                <caption>用户注册</caption>
                <tr>
                    <td class="formtd1">用户名：</td>
                    <td class="formtd2">
                        <input class="userinput" type="text" name="username" value="${form.username}">
                        <span class="message">${form.errors.username}</span>
                    </td>
                </tr>

                <tr>
                    <td class="formtd1">昵称：</td>
                    <td class="formtd2">
                        <input class="userinput" type="text" name="nickname" value="${form.nickname}">
                        <span class="message">${form.errors.nickname}</span>
                    </td>
                </tr>

                <tr>
                    <td class="formtd1">密码：</td>
                    <td class="formtd2">
                        <input class="userinput" type="password" name="password" value="${form.password}">
                        <span class="message">${form.errors.password}</span>
                    </td>
                </tr>

                <tr>
                    <td class="formtd1">确认密码：</td>
                    <td class="formtd2">
                        <input class="userinput" type="password" name="password2" value="${form.password2}">
                        <span class="message">${form.errors.password2}</span>
                    </td>
                </tr>

                <tr>
                    <td class="formtd1">电子邮箱：</td>
                    <td class="formtd2">
                        <input class="userinput" type="text" name="email" value="${form.email}">
                        <span class="message">${form.errors.email}</span>
                    </td>
                </tr>

                <tr>
                    <td class="formtd1">生日：</td>
                    <td class="formtd2">
                        <input class="userinput" type="date" name="birthday" value="${form.birthday}">
                        <span class="message">${form.errors.birthday}</span>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" id="submit" value="提交"/>
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>