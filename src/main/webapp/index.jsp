<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="text">
<head><title>
    <fmt:message key = "title.main" />
</title></head>
<body>
<button type="button" name="en">ENG</button>
<button type="button" name="ru">RUS</button>
<form action="mainWindow" method="post" enctype="multipart/form-data">
    <h2><fmt:message key = "message.upload_xml" /></h2>
    <input type="file" name="xml">
    <h2><fmt:message key = "message.upload_xsd" /></h2>
    <input type="file" name="xsd">
    <h2><fmt:message key = "message.choose_parser" /></h2>
    <label>
        <select name="parser-type">
            <option value="DOM">DOM</option>
            <option value="SAX">SAX</option>
            <option value="StAX">StAX</option>
        </select>
    </label>
    <input type="submit">
</form>
</body>
</fmt:bundle>
</html>
