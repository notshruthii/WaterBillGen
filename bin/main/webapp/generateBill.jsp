<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<h2>Generate Water Bill</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="generate">

    Consumer Number: <input type="text" name="consumerNumber"><br><br>

    Month: <input type="text" name="billMonth"><br><br>

    Year: <input type="text" name="year"><br><br>

    Start Reading: <input type="text" name="startReading"><br><br>

    Current Reading: <input type="text" name="currentReading"><br><br>

    Connection Type:
    <select name="connectionType">
        <option value="Domestic">Domestic</option>
        <option value="Industrial">Industrial</option>
    </select>
    <br><br>

    <input type="submit" value="Generate Bill">

</form>

</body>
</html>