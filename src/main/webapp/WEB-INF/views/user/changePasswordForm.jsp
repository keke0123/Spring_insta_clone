<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container" style="margin-top:50px;">
	<form class="form" action="changepassword.do" method="post">
		<label for="password">변경할 비밀번호 입력</label>
		<input type="password" name="password"/>
		<button>전송</button>
	</form>
</div>
</body>
</html>