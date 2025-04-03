<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Create 회원가입</title>
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
   /* 회원가입 칸 만들기 */
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
   	/* 각 라벨간 간격 주기 */
    .form-group {
        margin-bottom: 15px;
        text-align: center; /* 라벨 중앙 정렬 */
    }
    
    .form-group label {
        display: block;
        margin-bottom: 5px; /* 라벨 텍스트 왼쪽 정렬 */ 
        text-align: left; /* 라벨 텍스트 왼쪽 정렬 */
        position: relative;
        left : 30px;
    }
    
    .form-group input {
        width: 80%; /* 중앙 정렬을 위해 너비 조정 */
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        display: block;
        margin: 0 auto; /* 입력창을 가운데로 배치 */
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
	/* 버튼 컬러 적용 */
    .buttons .submit {
        background-color: #4CAF50;
        color: white;
    }
    .buttons .cancel {
        background-color: #f44336;
        color: white;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        
		<!-- 에러 메시지 처리 부분 -->
        <c:if test="${errorMessage != null}">
            <script>
                alert("${errorMessage}");
            </script>
        </c:if>
        
        <form action="RegisterController" method="post">
            <div class="form-group">
                <label for="id">아이디 </label>
                <input type="text" id="id" name="id" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="passwordConfirm">비밀번호 확인</label>
                <input type="password" id="passwordConfirm" name="passwordConfirm" required>
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="nikname">닉네임</label>
                <input type="text" id="nikname" name="nikname" required>
            </div>
            <div class="buttons">
                <input type="submit" value="회원가입" class="submit">
                <input type="button" value="취소" class="cancel" onclick="location.href='login.jsp'">
            </div>
        </form>
    </div>
</body>
</html>
