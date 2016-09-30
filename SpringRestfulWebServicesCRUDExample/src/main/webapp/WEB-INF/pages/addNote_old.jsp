<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Form Handling</title>
</head>
<body>
	<h2>Add Note</h2>
	<form:form method="POST" action="save.do">
		<table>
			<%-- <tr>
			        <td><form:label path="id">Note ID:</form:label></td>
			        <td><form:input path="id" value="${employee.id}" readonly="true"/></td>
			    </tr> --%>
			<tr>
				<td><form:label path="name">Note Name:</form:label></td>
				<td><form:input path="name" value="${notes.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="content">Notes</form:label></td>
				<td><form:textarea cols="80" rows="10" path="content" value="${notes.content}" /></td>
			</tr>
			<tr>
				<td><form:hidden path="id" value="${notes.id}" /> <form:hidden
						path="folder.id" value="1" /> <form:hidden path="user.id"
						value="1" /> <form:hidden path="type" value=".txt" /> <form:hidden
						path="status" value="Active" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save Note" /></td>
			</tr>

			<tr style="border: thin; color: gray;">
				<td colspan="2" align="center"><a href="home.do">Home</a></td>
			</tr>
		</table>
	</form:form>

</body>
</html>