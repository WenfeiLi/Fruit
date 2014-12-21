<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="${contextPath}/styles/login.css"/>
</head>
<body>
<div id="content">
    <div id="login">
        <div id="form">
            <form action="${contextPath}/account/login.html" method="post">
                <div id="input">
                    <div>
                        <span>Username</span>
                        <input type="text" name="username" value="${username}"/>
                        <br/>
                    </div>
                    <div>
                        <span>Password</span>
                        <input type="password" name="password"/><br/>
                    </div>
                </div>
                <div id="btn">
                    <input type="submit" value="Login"/>
                    <input type="button" value="Register"
                           onclick="window.location.href='${contextPath}/account/register.html'"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>