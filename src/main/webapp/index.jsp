<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="text">
<head>
    <title><fmt:message key = "title.main" /></title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<%--<button type="button" name="en">ENG</button>--%>
<%--<button type="button" name="ru">RUS</button>--%>
<form action="mainWindow" method="post" enctype="multipart/form-data">
    <div class="left-column">
        <fmt:message key = "message.upload_xml" /><br><br>
        <input type="file" name="xml">
    </div>
    <div class="right-column">
        <fmt:message key = "message.upload_xsd" /><br><br>
        <input type="file" name="xsd">
    </div>
    <div class="footer">
        <fmt:message key = "message.choose_parser" /><br><br>
        <label>
            <select name="parser-type">
                <option value="DOM">DOM</option>
                <option value="SAX">SAX</option>
                <option value="StAX">StAX</option>
            </select>
        </label>
        <input type="submit">
    </div>
</form>
</body>
</fmt:bundle>
</html>
