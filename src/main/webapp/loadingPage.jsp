<%--
  Created by IntelliJ IDEA.
  User: jeongmingyu
  Date: 2025. 4. 4.
  Time: 오후 3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding("UTF-8");  // ★ 꼭 넣어줘야 POST 데이터 깨지지 않음
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로딩 중...</title>
  <style>
    body {
      background-color: #66b3ff;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: 'Segoe UI', sans-serif;
    }

    .loader-container {
      text-align: center;
    }

    .loader-text {
      font-size: 60px;
      color: #ffffff;
      -webkit-text-stroke: 1px #328dc9;
      margin-bottom: 30px;
    }

    .loader-sub {
      font-size: 24px;
      color: white;
      opacity: 0.8;
      margin-bottom: 40px;
    }

    .dot-loader {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 10px;
    }

    .dot {
      width: 20px;
      height: 20px;
      background-color: white;
      border-radius: 50%;
      animation: bounce 1.2s infinite ease-in-out;
    }

    .dot:nth-child(2) {
      animation-delay: 0.2s;
    }

    .dot:nth-child(3) {
      animation-delay: 0.4s;
    }

    @keyframes bounce {
      0%, 80%, 100% {
        transform: scale(0);
        opacity: 0.3;
      }
      40% {
        transform: scale(1);
        opacity: 1;
      }
    }
  </style>
</head>
<body>
<div class="loader-container">
  <div class="loader-text">言霊</div>
  <div class="loader-sub">음악을 생성 중입니다...</div>
  <div class="dot-loader">
    <div class="dot"></div>
    <div class="dot"></div>
    <div class="dot"></div>
  </div>
</div>

<%
  String text = request.getParameter("Text");
  System.out.println("🔍 전달받은 Text 파라미터 = " + text);
%>

<script>
  window.onload = function () {
    const text = "<%= request.getParameter("Text") %>";
    const params = new URLSearchParams();
    params.append("Text", text);

    fetch("creatingPageCon.do", {
      method: "POST",
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'  // 👈 중요!
      },
      body: params
    })
            .then(res => {
              if (res.redirected) {
                window.location.href = res.url;
              } else {
                return res.text();
              }
            })
            .catch(error => {
              alert("에러 발생: " + error);
              console.error(error);
            });
  };
</script>
</body>
</html>
