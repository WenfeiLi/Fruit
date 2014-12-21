<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp"%>

<html>
<head>
    <title>提示信息</title>
</head>
<body>
<div style="text-align: center;">
    <div id="prompt">
        <c:if test="${not empty errorMsg}">
            <div class="error" id="errorMsg">
                <c:forEach var="error" items="${errorMsg}">
                    <img src="<c:url value="/images/warning.gif"/>" alt="warning" class="icon"/>
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
            <c:remove var="errorMsg"/>
        </c:if>
    </div>
    <div>
        <c:if test="${not empty successMsg}">
            <div class="message" id="successMsg">
                <c:forEach var="msg" items="${successMsg}">
                    <img src="<c:url value="/images/info.gif"/>" alt="info" class="icon" />
                    <c:out value="${msg}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
            <c:remove var="successMsg"/>
        </c:if>
    </div>
</div>
</body>
</html>
