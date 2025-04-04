<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.login_button{
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

.list_button{
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
		<h1 class="title">&#x8A00;&#x970A;</h1>
		<h1 class="smalltitle">Kotodama</h1>
			<form action="creatingPageCon.do" method="post">
    			<span class="search_wrapper"> 
        			<input type="text" class="search_box" name="Text" placeholder="검색어를 입력하세요">
        			<button type="submit" style="border: none; background: none; padding: 0; cursor: pointer;">
        			<img src="search.png" alt="검색" class="search-icon">
        			</button>
    			</span>
			</form>
  		<div class="list_button">목록</div>
	</div>
</body>
</html>