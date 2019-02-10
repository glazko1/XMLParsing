<%--
  Created by IntelliJ IDEA.
  User: glazk
  Date: 01.02.2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<c:set var="locale" value="${locale}" />
<fmt:bundle basename="${locale}">
<head>
    <title>Parsing Result</title>
</head>
<body>
<table border="1" cellpadding="4" cellspacing="0">
    <tr>
        <th align=center rowspan="2">ID</th>
        <th align=center rowspan="2"><fmt:message key = "table.name" /></th>
        <th align=center rowspan="2"><fmt:message key = "table.operator_name" /></th>
        <th align=center rowspan="2"><fmt:message key = "table.payroll" /></th>
        <th align=center colspan="3"><fmt:message key = "table.call_prices" /></th>
        <th align=center rowspan="2"><fmt:message key = "table.sms_price" /></th>
        <th align=center colspan="4"><fmt:message key = "table.parameters" /></th>
    </tr>
    <tr>
        <th align=center><fmt:message key = "table.within_network" /></th>
        <th align=center><fmt:message key = "table.other_networks" /></th>
        <th align=center><fmt:message key = "table.landline_phones" /></th>
        <th align=center><fmt:message key = "table.favorite_numbers" /></th>
        <th align=center><fmt:message key = "table.tariffing" /></th>
        <th align=center><fmt:message key = "table.connection_fee" /></th>
        <th align=center><fmt:message key = "table.launch_date" /></th>
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
            <td align=center>${tariff.parameters.tariffingType}<fmt:message key = "table.seconds" /></td>
            <td align=center>${tariff.parameters.connectionFee}</td>
            <td align=center>${tariff.parameters.launchDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</fmt:bundle>
</html>
