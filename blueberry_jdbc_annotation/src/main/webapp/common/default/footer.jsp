<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp"%>

<div id="footer">
    <hr/>
    <br/>

    <div>
        <span>版权所有 &copy; 2014-2015 国际水果联盟</span>
    </div>
    <div>
        <c:if test="${(userLastAccessTime != null) and (not empty userLastAccessTime)}">
            <span>上次访问时间：${userLastAccessTime}</span>
        </c:if>
        <a href="${contextPath}/account/list.html">用户管理</a>
    </div>
</div>