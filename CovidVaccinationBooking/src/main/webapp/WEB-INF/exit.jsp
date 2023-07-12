<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style1.css">
<title>Exit</title>
</head>
<body>
    <div class="con">
<%String user= (String)request.getAttribute("user"); %>
<h2>Your slot has booked have a nice day</h2>
<h2><%out.print(user); %></h2>
</div>
</body>
</html>