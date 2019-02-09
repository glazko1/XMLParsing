<%--
  Created by IntelliJ IDEA.
  User: glazk
  Date: 01.02.2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parsing Result</title>
</head>
<body>
<table border="1" cellpadding="4" cellspacing="0">
    <tr>
        <th align=center rowspan="2">ID</th>
        <th align=center rowspan="2">Name</th>
        <th align=center rowspan="2">Operator name</th>
        <th align=center rowspan="2">Payroll</th>
        <th align="center" colspan="3">Call prices</th>
        <th align=center rowspan="2">SMS price</th>
        <th align="center" colspan="3">Parameters</th>
    </tr>
    <tr>
        <th align=center>Within network</th>
        <th align=center>Other networks</th>
        <th align=center>Landline phones</th>
        <th align=center>Favorite numbers</th>
        <th align=center>Tariffing</th>
        <th align=center>Connection fee</th>
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
