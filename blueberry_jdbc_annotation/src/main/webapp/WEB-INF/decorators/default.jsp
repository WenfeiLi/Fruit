<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
    <title><decorator:title/> | Blueberry</title>
    <%@ include file="/common/public/meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/global.css"/>
    <decorator:head/>
</head>

<body<decorator:getProperty property="body.id" writeEntireProperty="true"/>
        <decorator:getProperty property="body.class" writeEntireProperty="true"/>>

<div id="wrap">
    <jsp:include page="/common/default/header.jsp"/>
    <%@ include file="/common/default/messages.jsp" %>
    <decorator:body/>
    <jsp:include page="/common/default/footer.jsp"/>
</div>

</body>
</html>