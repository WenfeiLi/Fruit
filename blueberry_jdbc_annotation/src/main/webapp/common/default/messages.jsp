<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/public/taglibs.jsp"%>

<style type="text/css" media="all">
    #prompt{
        background-color: red;
        color: white;
    }
</style>

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