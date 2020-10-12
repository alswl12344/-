<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<html>
<head>
<title>Home</title></head><body>

<%@include file="include/header.jsp"%>
<section class="content">
	<Form method="post">
	아이디 : <input type="text" name="id" /><br>
	패스워드 : <input type="text" name="password" /><br>
	<input type="submit" value="로그인" />
	
	</Form>
	<hr>
	admin/12345<br>
	system/12345<br>
	test/12345<br>
	user/????
</section>
<%@include file="include/footer.jsp"%>

</body>
</html>
