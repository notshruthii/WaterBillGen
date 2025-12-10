<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<h2>View Bill</h2>
<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="view">

    Consumer Number: <input type="text" name="consumerNumber"><br><br>

    Month: <input type="text" name="billMonth"><br><br>

    Year: <input type="text" name="year"><br><br>

    <input type="submit" value="View Bill">

</form>
</body>
</html>