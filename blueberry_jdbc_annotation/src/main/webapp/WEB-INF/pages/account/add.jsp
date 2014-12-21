<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp" %>
<html>
<head>
    <title>增加用户</title>
    <style type="text/css" media="all">
        .user_form{
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
    <div class="user_form">
        <%--没有action，默认为当前path--%>
        <sf:form method="post" action="${contextPath}/account/add.html"
                 modelAttribute="registerForm">
            <table>
                <caption>增加用户</caption>

                <tr>
                    <td>用户名</td>
                    <td>
                        <sf:input path="username"/>
                        <sf:errors path="username"/>
                    </td>
                </tr>

                <tr>
                    <td>昵称</td>
                    <td>
                        <sf:input path="nickname"/>
                        <sf:errors path="nickname"/>
                    </td>
                </tr>

                <tr>
                    <td>密码</td>
                    <td>
                        <sf:password path="password"/>
                        <sf:errors path="password"/>
                    </td>
                </tr>

                <tr>
                    <td>确认密码</td>
                    <td>
                        <sf:password path="password2"/>
                        <sf:errors path="password2"/>
                    </td>
                </tr>

                <tr>
                    <td>邮箱</td>
                    <td>
                        <sf:input path="email"/>
                        <sf:errors path="email"/>
                    </td>
                </tr>

                <tr>
                    <td>生日</td>
                    <td>
                        <sf:input path="birthday"/>
                        <sf:errors path="birthday"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="添加"/>&nbsp;
                        <input type="reset" value="重置"/>
                    </td>
                </tr>
            </table>
        </sf:form>
    </div>
</div>
</body>
</html>
