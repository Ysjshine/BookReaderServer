<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>删除结果</title>
</head>
<body>
<p>
    <c:choose>
        <c:when test="${ans == true}">
            删除成功
        </c:when>
        <c:when test="${ans == false}">
            删除失败,书籍ID不存在或书籍仍被用户收藏
        </c:when>
    </c:choose>
</p>
<p>
    <button onclick="location.href='${pageContext.request.contextPath}/QueryBookAll'">继续查询</button>
</p>

</body>
</html>
