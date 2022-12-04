<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${path}/resources/css/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Form</title>
</head>
<body>

<h1>수정하는 페이지</h1>
<%--@elvariable id="boardVO" type="java"--%>
<form:form modelAttribute="u"  method="post" action="../editok">
<form:hidden path="seq"/>
<table id="list">
	<tr><td>Title:</td><td><form:input path="title" /></td></tr>
	<tr><td>Writer:</td><td><form:input path="writer" /></td></tr>
	<tr><td>Content:</td><td><form:textarea cols="50" rows="5" path="content" /></td></tr>
</table>
	<input type="submit" value="수정하기" />
	<input type="button" value="취소하기" onclick="history.back()" />
</form:form>

</body>
</html>