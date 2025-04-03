<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Create 로그인</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background: white;
	padding: 30px;
	border-radius: 0px;
	width: 400px;
}

.container h2 {
	text-align: center;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
	text-align: center;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	text-align: left;
	position: relative;
	left: 30px;
}

.form-group input {
	width: 80%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	display: block;
	margin: 0 auto;
}

.buttons {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
}

.buttons input {
	width: 48%;
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.buttons .submit {
	background-color: #4CAF50;
	color: white;
}

.buttons .register {
	background-color: #f44336;
	color: white;
}
</style>
</head>
<body>
	<div class="container">
		<h2>로그인</h2>
		
		<!-- 에러 메시지 처리 부분 -->
		<c:if test="${errorMessage != null}">
			<script>
        	alert("${errorMessage}");
    		</script>
		</c:if>
		
		<!-- 회원가입 성공 메시지 확인 -->
		<c:if test="${param.message eq 'success'}">
			<script>
    			alert("회원가입이 성공적 으로 완료되었습니다!");
    		</script>
		</c:if>
		<form action="LoginController" method="post">
			<div class="form-group">
				<label for="id">아이디</label> <input type="text" id="id" name="id"
					required>
			</div>
			<div class="form-group">
				<label for="password">패스워드</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="buttons">
				<input type="submit" value="로그인" class="submit"> <input
					type="button" value="회원가입" class="register"
					onclick="location.href='register.jsp'">
			</div>
		</form>
	</div>
</body>
</html>
