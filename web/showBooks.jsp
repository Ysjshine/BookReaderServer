<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>书籍管理</title>
</head>
<body>

<table>
    <jsp:useBean id="bookBeans" scope="request" type="java.util.List<bean.BookBean>"/>
    <c:forEach var="bookBean" items="${bookBeans}">
        <tr>
            <td>${bookBean}</td>
        </tr>
    </c:forEach>
</table>
<br>
<h2>删除书籍</h2>
<form action="${pageContext.request.contextPath}/DelBook" method="post">
    <label for="bookID">BookID</label><input type="number" id="bookID" name="bid" required>
    <input type="submit">
</form>

</body>
