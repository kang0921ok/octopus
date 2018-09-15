<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Welcome to Index

	<div id="result"></div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script>
		$.get("${cp}/userList", function(data) {
			console.log(data);
			var text;
			$.each(data, function(key, value){
				alert( key + ": " + value.userId );
			})
			$("#result").html(text);
		}).done(function() {
			alert("Load was performed.");
		}).fail(function() {
			alert("error");
		}).always(function() {
			alert("finished");
		});
	</script>
</body>
</html>