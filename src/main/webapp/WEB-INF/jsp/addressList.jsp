<%@ page import="java.util.List" %>
<%@ page import="entity.Address" %>
<%@ page contentType="text/html;charset=utf8" language="java"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% List<Address> listAddres = (List<Address>) request.getAttribute("getAll");%>
<html>
<body>

<table border="1">


    <th>id</th>
    <th>country</th>
    <th>city</th>
    <th>street</th>
    <th>postCode</th>
<%--<c:forEach var="address" items="${listAddres}">--%>
    <%
        for (Address address : listAddres) {
    %>

    <tr>
        <td>
    <%--${address.getId()}--%>
            <% out.print(address.getId()); %>
        </td>
        <td>
            <% out.print(address.getCountry()); %>
        </td>
        <td>
            <% out.print(address.getCity()); %>
        </td>
        <td>
            <% out.print(address.getStreet()); %>
        </td>
        <td>
            <% out.print(address.getPostCode()); %>
        </td>
    </tr>

    <%
        }
    %>
</table>


</body>
</html>
<%--<%=  new java.util.Date() %>--%>