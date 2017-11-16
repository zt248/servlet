<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NewEmployees</title>
</head>
<body>
    <form method="post" action="/employeeServlet">
        <input type="hidden" name="action" value="AddEmployee" />
        <input type="text" name="employeeCountry" value="Country"/>
        <input type="text" name="employeeCity" value="City"/>
        <input type="text" name="employeeStreet" value="Street"/>
        <input type="text" name="employeePostCode" value="PostCode"/>
        <button type="submit" name="action">AddEmployee</button>
    </form>
</body>
</html>
