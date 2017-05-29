<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>更新</title>
</head>
<body>
<h2>更新</h2>
<form action="${pageContext.request.contextPath}/UploadNewsImage" method="post" enctype="multipart/form-data">
    <label for="a">a</label> <input id="a" name="a1" type="file" required>
    <label for="b">b</label> <input id="b" name="b1" type="file" required>
    <label for="a">c</label> <input id="c" name="c1" type="file" required>
    <input type="submit">
</form>
</body>
</html>
