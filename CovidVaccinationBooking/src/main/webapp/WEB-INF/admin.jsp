<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Admin page</title>
<style>
. content{
align-content: center;

}

</style>
</head>
<body>
<div class="content">
<%String user= (String)request.getAttribute("user"); %>
<h2>Welcome user: <%=user %></h2>
   <form action="Dosage_details">
   <input type="hidden" name="user" value="<%=user%>" />
     <input type="submit" value="Get Dosage details">
    </form>
    <form action="Add_vaccination_center.html">
     <input type="hidden" name="name" value="<%=user%>" />
     <input type="submit" value="Add vaccination center">
    </form>
    <form action="Remove_vaccination_Center.html">
     <input type="hidden" name="name" value="<%=user%>" />
     <input type="submit" value="Remove Vaccination Details">
    </form>
</div>
</body>
</html>