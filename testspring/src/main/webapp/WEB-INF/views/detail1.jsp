<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
<div class="table-responsive">
  <table class="table">

	<form>
	<div class="form-group1">
<table>
<c:forEach var="boardreply" items="${list}">
<tr>
<td>작성자 : ${boardreply.rewriter}    작성일자 : ${boardreply.redate} </td>
</tr>
<tr>
<td><textarea name="rememo" rows="5" cols="40"
		readonly="readonly" class="form-control1">
${boardreply.rememo}</textarea></td>
<td><a href = "replyupdate?reno=${boardreply.reno}">댓글수정</a><br><br>
<a href = "replydelete?reno=${boardreply.reno}">댓글삭제</a></td>
</tr>
</c:forEach>
</table>
</div>
			<script>
	$(function(){
		//댓글수정 버튼을 눌렀을 때 처리
		$(".btn-default").click(function(){
			location.href="replyupdate?brdno?reno=" + ${boardreply.brdno} + ${boardreply.reno} 
		});
	})
	</script>

</form>	
<%@ include file="include/footer.jsp"%>
</section>		

</body>
</html>