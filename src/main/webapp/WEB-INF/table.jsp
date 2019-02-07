<%--
  Created by IntelliJ IDEA.
  User: glazk
  Date: 01.02.2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parsing Result</title>
</head>
<body>
<table>
    <tr>
        <th align=center>ID</th>
        <th align=center>Name</th>
        <th align=center>Operator Name</th>
        <th align=center>Payroll</th>
        <th align=center>Within network</th>
        <th align=center>Other networks</th>
        <th align=center>Landline Phones</th>
        <th align=center>SMS Price</th>
        <th align=center>Favorite Numbers</th>
        <th align=center>Tariffing</th>
        <th align=center>Connection Fee</th>
    </tr>
    <c:forEach items="${tariffs}" var="tariff">
        <tr>
            <td align=center>${tariff.id}</td>
            <td align=center>${tariff.name}</td>
            <td align=center>${tariff.operatorName}</td>
            <td align=center>${tariff.payroll}</td>
            <td align=center>${tariff.callPrices.withinNetwork}</td>
            <td align=center>${tariff.callPrices.otherNetworks}</td>
            <td align=center>${tariff.callPrices.landlinePhones}</td>
            <td align=center>${tariff.smsPrice}</td>
            <td align=center>${tariff.parameters.favoriteNumbers}</td>
            <td align=center>${tariff.parameters.tariffingType}</td>
            <td align=center>${tariff.parameters.connectionFee}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
