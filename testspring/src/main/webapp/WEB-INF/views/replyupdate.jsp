<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정댓글</title>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<section class="content">
		<div class="box-header">
			<h3>수정 댓글 쓰기</h3>
		</div>
		<form role="form" method="post">
			<div class="box-body">
				<div class="form-group">
					<label>작성자</label> <input type="text" name='rewriter' class="form-control" placeholder="${list.rewriter }">
					<textarea class="form-control" name="rememo" rows="3" placeholder="${list.rememo }"></textarea>
				</div>
	<div class="form-group">
						<label>글번호</label> <input type="hidden" name="bno" class="form-control"  
							value="${list.bno }" readonly>
					</div>
			</div>
			<div class="box-footer">
				<button type="submit" class="btn btn-primary">작성완료</button>
			</div>
		</form>
	</section>
	<%@include file="include/footer.jsp"%>
</body>
</html>
