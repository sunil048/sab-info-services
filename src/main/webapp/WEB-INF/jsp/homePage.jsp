<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Page details</title>

<spring:url value="/css/info.css" var="info" />
<spring:url value="/css/main.css" var="mainCss" />
<spring:url value="/css/widgEditor.css" var="widgEditorCss" />
<spring:url value="/css/widgContent.css" var="widgContentCss" />
<spring:url value="/lib/widgEditor.js" var="widgEditor" />

<link type="text/css" rel="stylesheet" href="${info}" />
<link type="text/css" rel="stylesheet" href="${mainCss}" />
<link type="text/css" rel="stylesheet" href="${widgEditorCss}" />
<link type="text/css" rel="stylesheet" href="${widgContentCss}" />



<style type="text/css">
table tr td {
	padding: 3px;
	font-size: medium;
	font-style: normal;
}
</style>

<script type="text/javascript" src="${widgEditor}"></script>


</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div>
		<form action="${pageContext.request.contextPath}/${url}"
			commandName="Page" method="post">
			<div style="background-color: silver; vertical-align: top;">
				<table>
					<tr>
						<td><input type="hidden" value="${Page.bookName}"
							name="bookName" />Book :<u>${Page.bookName} </u></td>
						<td>Page title : <input type="text" name="title"
							value="${Page.title}" min="50" maxlength="${Page.title.length()}" /></td>
						<td>Page No</td>
						<td><input type="text" name="pageNo" value="${Page.pageNo}"
							style="display: none;" />${Page.pageNo}</td>
						<td>Created Date</td>
						<td><input type="text" name="createdDate"
							value="${Page.createdDate}" /></td>
						<td>Author</td>
						<td><input type="text" name="createdBy"
							value="${Page.createdBy}" /></td>
						<td><input type="hidden" value="${Page.bookId}" name="bookId"
							readonly="readonly" /> <%-- ${Page.bookId} --%></td>
						<td><input type="hidden" value="${Page.pageId}" name="pageId" />${Page.pageId}</td>
						<td><a
							href="${pageContext.request.contextPath}/getBookDetails/${Page.bookId}">Page
								List</a></td>
					</tr>
				</table>
			</div>
			<div style="vertical-align: top;" style="background-color: silver;">
				<table width="100%">
					<tr>
						<td style="vertical-align: top; overflow: scroll;" width="10%">
							<div
								style="background-color: silver; vertical-align: top; font-size: 0.5em;">
								<c:if test="${! empty pageList }">
									<table>
										<tr>
											<td>Pages</td>
										</tr>
										<c:forEach items="${pageList}" var="Page" varStatus="status">
											<tr>
												<td style="font-size: 0.5em;">${status.index+1}<a
													href="${pageContext.request.contextPath}/editPage/${Page.pageId}">${Page.title}</a></td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
							</div>
						</td>
						<td width="90%" style="vertical-align: top;">
							<div width="100%"
								style="background-color: #FFFFFF; height: 100%;">
								<sec:authorize access="hasRole('ROLE_ADMIN')">

									<textarea name="content" id="ORIGINAL_IDWidgTextarea"
										class="widgEditor">${Page.content}</textarea>

								</sec:authorize>

								<sec:authorize access="hasRole('ROLE_USER')">

									<div>${Page.content}</div>
								</sec:authorize>
							</div>
						</td>
					</tr>
				</table>

			</div>
			<div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<input type="submit" value="${submitButton}" />
				</sec:authorize>
			</div>
		</form>
	</div>
	<div>
		<c:if test="${! empty documentList}">
			<table style="border: solid 1px; background-color: silver;">
				<colgroup>
					<col width="5%" />
					<col width="20%" />
					<col width="8%" />
					<col width="8%" />
					<col width="15%" />
					<col width="8%" />

				</colgroup>
				<tr>
					<th>Attachment No</th>
					<th>Title</th>
					<th>Attachment Id</th>
					<th>Page Id</th>
					<th>Created Date</th>
					<th>Created By</th>
				</tr>
				<c:forEach items="${documentList}" var="mydoc">
					<tr>
						<td>${mydoc.attachementNo}</td>
						<td><a
							href="${pageContext.request.contextPath}/downloadAttachment/${mydoc.attachementId}">${mydoc.title}</a></td>
						<td>${mydoc.attachementId}</td>
						<td>${mydoc.pageId}</td>
						<td>${mydoc.createdDate}</td>
						<td>${mydoc.createdBy}</td>
						<%-- <td><br> <img alt="${mydoc.title}"
						src="/sab-edu/downloadAttachment/${mydoc.attachementId}"
						height="200px" width="200px" /></td> --%>
					</tr>
				</c:forEach>
			</table>

		</c:if>

	</div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div title="attachement">

			<form action="${pageContext.request.contextPath}/addDocumet"
				method="post" commandName="Attachement"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>Page Id</td>
						<td><input type="hidden" name="pageId" value="${Page.pageId}"
							readonly="readonly" />${Page.pageId}</td>
					</tr>
					<tr>
						<td>Document Id</td>
						<td><input type="hidden" name="attachementId"
							value="${attachementId}">${attachementId}</td>
					</tr>
					<tr>
						<td>Document No</td>
						<td><input type="text" name="attachementNo"
							value="${attachementNo}" style=""></td>
					</tr>
					<tr>
						<td>Title</td>
						<td><input type="text" name="title" style=""></td>
					</tr>
					<tr>
						<td>createdDate</td>
						<td><input type="text" name="createdDate" style=""></td>
					</tr>
					<tr>
						<td>Author</td>
						<td><input type="text" name="createdBy" style=""></td>
					</tr>
					<tr>
						<td>Attachment</td>
						<td><input type="file" id="attachedFile" name="attachedFile"
							style=""></td>
					</tr>
					<tr>
						<td span="2"><input type="submit" value="Add document" /></td>
					</tr>
				</table>
			</form>
		</div>
	</sec:authorize>

</body>
</html>

