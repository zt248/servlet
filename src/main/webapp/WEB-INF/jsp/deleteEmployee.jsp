<%@ page import="entity.Address" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<Address> listAddres = (List<Address>) request.getAttribute("getAll");%>

<html>
<head>
    <title>DeleteEmployee</title>
</head>
<body>
<form method="post" action="/employeeServlet">
    <input type="hidden" name="action" value="RemoveEmployee"/>
    <input type="text" name="employeeId"/>
    <button type="submit" name="action">Remove</button>
</form>
</body>
</html>
