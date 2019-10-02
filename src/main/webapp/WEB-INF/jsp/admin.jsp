<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		Hello this is admin page
		<sec:authorize access="hasRole('ROLE_ADMIN')">
	<table>
  		<tr>
  	  	  	<td>xxx</td>
  	  	  	<td>Some administrator data here</td>
  	  	</tr>
  	</table>
</sec:authorize>

		
</body>
</html>