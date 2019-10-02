<%--
File    : 
Created : Nov 3, 20169:45:29 PM (Happy Birthday)
Author  :Sunil
---%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
body {
	font-family: serif;
}

div {
	padding: 5px;
}

tabale tr :even {
	background-color: silver;
}

table tr td {
	padding: 3px;
}
</style>
</head>
<body>
	<%@ include file= "/WEB-INF/jsp/header.jsp"%>
	<sec:authorize access="hasRole('ADMIN')">
		<form action="save" method="post" commandName="book"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>Book No :</td>
					<td><input type="text" name="bookNo" value="${book.bookNo}" /></td>
				</tr>
				<tr>
					<td>Book Name :</td>
					<td><input type="text" name="bookName" /></td>
				</tr>
				<tr>
					<td>Description :</td>
					<td><input type="text" name="description" /></td>
				</tr>
				<tr>
					<td>Author :</td>
					<td><input type="text" name="createdBy" /></td>
				</tr>
				<tr>
					<td span="2"><input type="submit" value="Create Book" /></td>
				</tr>
			</table>
		</form>
	</sec:authorize>
	<div style="background-color: silver;">
		<c:if test="${!empty bookList}">
			<table style="width: 100%; border: solid 1px;">
				<tr>
					<th width="5%">Book No</th>
					<th width="10%">Name</th>
					<th width="15%">Description</th>
					<th width="10%">Id</th>
					<th width="10%">created</th>
					<th width="10%">Author</th>
				</tr>
				<c:forEach items="${bookList}" var="book">
					<tr>
						<td>${book.bookNo}</td>
						<td><a
							href="${pageContext.request.contextPath}/getBookDetails/${book.bookId}">${book.bookName}</a></td>
						<td>${book.description}</td>
						<td><a
							href="${pageContext.request.contextPath}/getBookDetails/${book.bookId}">${book.bookId}</a></td>
						<td>${book.createdDate}</td>
						<td>${book.createdBy}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</div>

</body>
</html>
