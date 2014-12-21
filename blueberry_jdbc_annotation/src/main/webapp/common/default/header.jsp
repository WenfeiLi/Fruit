<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp" %>

<style type="text/css" media="all">
    #account_menu {
        /*float: right;*/
        text-align: right;
        margin-top: 10px;
        margin-right: 10px;
    }

    #web_title h1 {
        height: 20px;
        text-align: center;
    }

    #web_title h1 span {
        position: relative;
        top: 1px;
    }
</style>

<div id="header">
    <div id="account_menu">
        <a href="${contextPath}/home.html">Home</a> |
        <c:choose>
            <c:when test="${user!=null}">
                <a href="${contextPath}/account/${sessionScope.user.username}/info.html">${sessionScope.user.nickname}</a> |
                <a href="${contextPath}/account/logout.html">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="${contextPath}/account/login.html">Login</a> |
                <a href="${contextPath}/account/register.html">Register</a>
            </c:otherwise>
        </c:choose>
    </div>

    <div id="web_title">
        <h1><span>水果联萌</span></h1>
    </div>
</div>