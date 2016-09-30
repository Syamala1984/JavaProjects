<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<spring:url value="/save.do" var="noteActionUrl" />

<form:form class="form-horizontal" method="post" modelAttribute="notes" action="${noteActionUrl}">

<form:hidden path="id" value="${notes.id}"/>
<form:hidden path="folder.id" value="1" />
<form:hidden path="user.id"	value="1" />
<form:hidden path="type" value=".txt" />
<form:hidden path="status" value="Active" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="name" size="25" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="content">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Content</label>
				<div class="col-sm-10">
					<form:textarea path="content" rows="5" cols="80" class="form-control" id="content" placeholder="content" />
					<form:errors path="content" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn-lg btn-primary pull-right">Save</button>
			</div>
		</div>

</form:form>