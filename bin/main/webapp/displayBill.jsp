<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.reva.water.bean.WaterBillBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Water bill details</title>
</head>
<body>
<%
    WaterBillBean bill = (WaterBillBean) request.getAttribute("bill");
%>

<h2>Water Bill Details</h2>

<%
if (bill == null) {
%>

<p>No matching records exists! Please try again!</p>

<%
} else {
%>

<p>Consumer Number: <%= bill.getConsumerNumber() %></p>
<p>Month: <%= bill.getBillMonth() %></p>
<p>Year: <%= bill.getYear() %></p>
<p>Start Reading: <%= bill.getStartReading() %></p>
<p>Current Reading: <%= bill.getCurrentReading() %></p>
<p>Total Consumption: <%= bill.getTotalConsumption() %></p>
<p>Connection Type: <%= bill.getConnectionType() %></p>
<p>Fixed Charges: <%= bill.getFixedCharges() %></p>
<p>Variable Charges: <%= bill.getVariableCharges() %></p>
<p>Total Charges: <%= bill.getTotalCharges() %></p>

<%
}
%>

</body>
</html>