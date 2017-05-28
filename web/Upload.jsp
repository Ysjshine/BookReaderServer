<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传</title>
</head>
<body>
<h2>上传</h2>
<form action="${pageContext.request.contextPath}/UploadBook" method="post" enctype="multipart/form-data">
    <label for="Title">Title</label><input id="Title" name="Title" type="text" required><br/>
    <label for="Author">Author</label><input id="Author" name="Author" type="text" required><br/>
    <label for="Price">Price</label><input id="Price" name="Price" type="number" min="0" step="0.1" required><br/>
    <label for="File">File</label><input id="File" name="File" type="file" required><br/>
    <label for="Image">Image</label><input id="Image" name="Image" type="file" required><br/>
    <label for="Type">Type</label><input id="Type" name="Type" type="number" required><br/>
    <input type="submit">
</form>
</body>
</html>