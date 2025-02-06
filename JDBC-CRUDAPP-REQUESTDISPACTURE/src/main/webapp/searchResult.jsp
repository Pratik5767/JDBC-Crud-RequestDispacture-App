<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="lightblue">
	<br />
	<br />
	<br />
	<c:choose>
		<c:when test="${student ne null || !empty student}">
			<center>
				<table border=1>
					<tr>
						<th>SID</th>
						<th>SNAME</th>
						<th>SAGE</th>
						<th>SADDRESS</th>
					</tr>
					<tr>
						<td>${student.sid}</td>
						<td>${student.sname}</td>
						<td>${student.sage}</td>
						<td>${student.saddress}</td>
					</tr>
				</table>
			</center>
		</c:when>
		<c:otherwise>
			<h1 style='color: red; text-align: center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>