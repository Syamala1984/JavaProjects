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
	<table>
		<tr>
			<td><h2>Search</h2> <form:form method="POST"
					action="doSearch.do">
					<table>
						<tr>
							<td><form:input path="searchString" /></td>
							<td colspan="2"><input type="submit" value="Search" /></td>
							<td></td>
						</tr>
					</table>
				</form:form></td>
		</tr>

		<tr>
			<td>
				<table align="left" border="1">
					<tr>
						<th>S#</th>
						<th>Name</th>
						<th>Content</th>
						<th>Type</th>
						<th>Status</th>
						<th>Created On</th>
					</tr>
					<c:if test="${!empty allNotes}">

						<c:forEach items="${allNotes}" var="notes" varStatus="loop">
							<tr>
								<td><c:out value="${loop.index+1}" /></td>
								<td><c:out value="${notes.name}" /></td>
								<td><c:out value="${notes.content}" /></td>
								<td><c:out value="${notes.type}" /></td>
								<td><c:out value="${notes.status}" /></td>
								<td align="center"><a href="update.do?id=${notes.id}">Edit</a>
									| <a href="delete.do?id=${notes.id}">Delete</a></td>
							</tr>
						</c:forEach>
				</c:if>
				</table> 

			</td>
		</tr>

		<tr>
			<td><table align="center">
					<tr style="border: thin; color: gray;">
						<td align="center"><a href="home.do">Home</a></td>
					</tr>
				</table></td>
		</tr>

	</table>



</body>
</html>