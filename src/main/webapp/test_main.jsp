<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <style>
        .login-btn {
            padding: 10px 20px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="login-section">
            <!-- 로그인 확인 -->
            <c:choose>
                <c:when test="${loginStatus eq 'logged_in'}">
                    <h1>${userNikname}님 환영합니다!</h1>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp" class="login-btn">로그인</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
