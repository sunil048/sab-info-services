<%--
File    : 
Created : Nov 4, 20166:24:24 PM
Author  :Sunil
---%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Book</title>
		<style type="text/css">
		div{
		padding : 5px;
		}
		table tr td {
		padding:3px;
		}
		
		
		</style>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<p>Books Details.</p>
		<div>
			<table style="width:60%;">
				<tr>
					<th widt="5%">Name :</th>
					<td widt="10%">${MyBooks.bookName}</td>
					<th widt="8%">Description :</th>
					<td widt="15%">${MyBooks.description}</td>
				</tr>
				<tr>
					<th widt="8%">Book No :</th>
					<td widt="5%">${MyBooks.bookNo}</td>
					<th widt="5%">Author :</th>
					<td widt="10%">${MyBooks.createdBy}</td>
				</tr>
			</table>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/addPage/${MyBooks.bookId}/${MyBooks.bookName}" method="get">Add page</a>
			<a href="${pageContext.request.contextPath}/wiki" method="get">Book List</a>
		</div>
		<div style="background-color: silver;">
		   <table style="width: 100%;border:1px solid">
				<tr>
				    <td>Page no</td>
				    <td>Title</td>
					<td>Page Id</td>
					<td>Created Date</td>
					<td>Author</td>
				</tr>
				<c:forEach items="${book_pages}" var="Page">
					<tr>
						<td width="5%">${Page.pageNo}</td>
						<td width="20%"><a href="${pageContext.request.contextPath}/editPage/${Page.pageId}" method="get">${Page.title}</a></td>
						<td width="15%"><a href="${pageContext.request.contextPath}/editPage/${Page.pageId}" method="get">${Page.pageId}</a></td>
						<td width="8%">${Page.createdDate}</td>
						<td width="8%">${Page.createdBy}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>

</html>
