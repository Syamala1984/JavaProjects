<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<body>

	<div class="container">

		<%-- <c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if> --%>

		<h1>All Notes</h1>

		<table class="table table-striped" border="1">
			<thead>
				<tr>
					<th>S#</th>
					<th>Name</th>
					<th>Content</th>
					<th>Type</th>
					<th>Status</th>
					<th>Created On</th>
					<th>Action</th>

				</tr>
			</thead>

			<c:forEach var="notes" items="${allNotes}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${notes.name}</td>
					<td>${notes.content}</td>
					<td>${notes.type}</td>
					<td>${notes.status}</td>
					<td>${notes.createdDate}</td>

					<td><spring:url value="update.do?id=${notes.id}"
							var="updateUrl" /> <spring:url value="delete.do?id=${notes.id}"
							var="deleteUrl" />

						<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger"
							onclick="location.href='${deleteUrl}'">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>