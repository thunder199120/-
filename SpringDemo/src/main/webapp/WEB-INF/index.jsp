<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<tr>
	<td>id</td>
	<td>name</td>
	<td>age</td>
	<td>address</td>
	</tr>
	<c:forEach items="${list }" var="student">
	<tr>
	<td>${student.id}</td>
	<td>${student.name}</td>
	<td>${student.age}</td>
	<td>${student.address}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>