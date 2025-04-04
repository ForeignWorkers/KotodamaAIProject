<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<style>
body {
	background-color: #66b3ff;
	display: flex;
	justify-content: center;
	height: 100vh;
	margin: 0;
	position: relative;
}

.container {
	display: flex;
	flex-direction: column; /* 세로 정렬 */
	align-items: center; /* 내부 요소 가로 중앙 정렬 */
	margin-top: 300px;
}

.login_button {
	width: 280px;
	height: 49px;
	background: white;
	border-radius: 20px;
	text-align: center;
	font-size: 33px; /* 글자 크기 */
	color: #66b3ff;
	border: 2px solid #ccc;
	position: absolute;
	top: 20px; /* 화면 위쪽에서 20px 아래 */
	right: 50px;
}

.title {
	font-size: 70px;
	margin: 0;
	color: #66b3ff;
	-webkit-text-stroke: 2px white;
}

.smalltitle {
	font-size: 40px;
	margin-top: 3px;
	color: #66b3ff;
	-webkit-text-stroke: 1px white;
}

.search_box {
	width: 650px; /* 가로 크기 */
	height: 30px; /* 세로 크기 */
	padding: 10px;
	font-size: 30px;
	border: 2px solid #ccc; /* 테두리 */
	border-radius: 20px; /* 끝을 둥글게 */
	outline: none; /* 클릭했을 때 테두리 제거 */
	margin-top: 3px;
	color: #66b3ff;
	padding-right: 50px;
}

.search_box::placeholder {
	color: #66b3ff; /
	opacity: 1;
}

.search_wrapper {
	display: flex; /* 검색창과 아이콘을 가로 정렬 */
	align-items: center; /* 세로 중앙 정렬 */
	position: relative;
}

.search-icon {
	position: relative;
	right: 50px; /* 오른쪽 정렬 */
	width: 40px; /* 이미지 크기 조절 */
	height: 40px;
}

.list_button {
	width: 280px;
	height: 49px;
	background: white;
	border-radius: 20px;
	text-align: center;
	font-size: 33px; /* 글자 크기 */
	color: #66b3ff;
	margin-top: 200px;
	border: 2px solid #ccc;
}
</style>
<body>
	<span class="login_button">로그인</span>
	<div class="container">
		<!-- <span class="login_button">로그인</span> -->
		<!-- 세션 확인 및 로그인 상태 표시 -->
		<c:choose>
			<%-- 로그인 되었을 때 --%>
			<c:when test="${sessionScope.loginStatus eq 'login'}">
				<h1>${sessionScope.userNikname}님환영합니다!</h1>
				<a href="LogoutController" class="login_button">로그아웃</a>
			</c:when>

			<%-- 로그인 안 되었을 때 --%>
			<c:otherwise>
				<a href="login.jsp" class="login_button">로그인</a>
			</c:otherwise>
		</c:choose>
		<h1 class="title">&#x8A00;&#x970A;</h1>
		<h1 class="smalltitle">Kotodama</h1>

		<c:choose>
			<c:when test="${sessionScope.loginStatus eq 'login'}">
				<form action="loadingPage.jsp" method="post">
					<span class="search_wrapper"> <input type="text"
						class="search_box" name="Text" placeholder="검색어를 입력하세요">
						<button type="submit"
							style="border: none; background: none; padding: 0; cursor: pointer;">
							<img src="search.png" alt="검색" class="search-icon">
						</button>
					</span>
				</form>
			</c:when>
			<%-- 분기점 --%>
			<c:otherwise>
				<form>
					<span class="search_wrapper"> <input type="text"
						class="search_box" name="Text" placeholder="로그인 후 검색 가능합니다."
						onclick="alert('로그인 후 이용할 수 있습니다.')" readonly>
						<button type="button" onclick="alert('로그인 후 이용할 수 있습니다.')"
							style="border: none; background: none; padding: 0; cursor: pointer;">
							<img src="search.png" alt="검색" class="search-icon">
						</button>
					</span>
				</form>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${sessionScope.loginStatus eq 'login'}">
				<button class="list_button"
					onclick="location.href='BoardListCon.do'">목록</button>
			</c:when>
			<%-- 분기점 --%>
			<c:otherwise>
				<button class="list_button" onclick="alert('로그인 후 이용할 수 있습니다.')">목록</button>
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>