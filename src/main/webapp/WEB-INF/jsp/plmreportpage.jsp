<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PLM-Report</title>
<style type="text/css">
table {
	table-layout: fixed;
	width: auto;
}

td {
	min-wdith: 250px;
}
</style>
<script type="text/javascript" src="/sab-info/lib/angular.js"></script>
<script type="text/javascript" src="/sab-info/js/reportApp.js"></script>
<script type="text/javascript" src="/sab-info/js/reportservices.js"></script>
</head>
<body ng-App="plmreport">
	<div ng-controller="generateReport">
		<span style="color: red;">{{error}}</span>
		<div task-Dir data={{data}}></div>
	</div>
	<div ng-controller="logController">
		<p>Get Task Logs</p>
		Enter the task Id <input type="text" ng-model="serchTaskId">
		<button ng-click="getTaskLogs(serchTaskId)">Get Task Logs</button>
		{{data}}
	</div>

</body>
</html>