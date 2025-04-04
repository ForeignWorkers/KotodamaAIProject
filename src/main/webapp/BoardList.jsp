<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board List</title>
<link rel="stylesheet" href="./bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<style>
* { box-sizing: border-box; margin: 0; padding: 0;}
body { display: flex; height: 100vh; background-color: #ffffff; }
 /* 사이드바 */
        .sidebar {
            width: 80px;
            background-color: #39f;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px;
        }
        .sidebar button {
            background: none;
            border: none;
            cursor: pointer;
            margin: 15px 0;
        }
        .sidebar img { width: 40px; height: 40px; }
/* 컨텐츠 영역 */
        .content {
        	width : 100%;
        	height : 100%;
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color : #ffffff;
        }
        .review-box {
        	
            width: 90%;
            height : 90%;
            background: #4EBBFF;
            padding: 20px;
            border-radius: 50px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
            border: 3px solid black;
        }

        .review-item *{
        	
        	color : #4EBBFF;
        	text-align: center;
        }
        
        .review-list {
    width: 95%;  /* 부모 요소(review-box)의 95% 크기로 설정 */
    height: 100%;
    display: flex;
    flex-direction: column; /* 테이블이 세로 정렬될 수 있도록 설정 */
    overflow-y: auto;
    background-color: #ffffff;
    border-radius: 50px;
    margin: 0 auto; /* 가운데 정렬 */
}

table {
    width: 90%;  /* review-list의 100%를 사용 */
    table-layout: fixed;
    border-collapse: collapse;
}

td {
    white-space: nowrap;
    overflow: hidden;
    padding: 10px;
}

.review-item {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #BAE2FA;
    margin: 5px 0;
    flex-wrap: wrap;
    align-content: center;
    padding: 10px;
    border-radius: 50px;
    border : 2px black;
}
/* 페이지 네비게이션 */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }
        .pagination button {
            background: #39f;
            color: white;
            border: none;
            padding: 5px 15px;
            margin: 0 5px;
            cursor: pointer;
            border-radius: 5px;
        }

button {
    padding: 10px 20px;
    margin-left: 10px;
    border: none;
    border-radius: 4px;
    background-color: #007bff;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}
button:hover {
    background-color: #0056b3;
}
</style>

</head>
<body>

 <!-- 사이드바 -->
    <div class="sidebar">
		<img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" onclick="location.href='MainPage.jsp'" alt="Home">
		<img src="https://cdn-icons-png.flaticon.com/512/56/56763.png" onclick="location.href='BoardListCon.do'" alt="List">
    </div>
    
	<div class="content">
		<div class="review-box">
			<table class = "review-list">
					<c:set var="boardId" value = "${boardId }" />
					<c:forEach var="bean" items="${v }">
						<tr height="50" class="review-item">
							<td width="50" align="center">${bean.boardId }</td><!-- 클릭시 넘어갈 보드 인포 -->
							<td width = "500" align = "left">
								<a href="detailPageCon.do?boardId=${bean.boardId}&isCountView=${true}">
								${bean.boardName }
							</a>
							</td>
							<td width = "100" align = "center">${bean.boardWriteName }</td> <!-- 작성자 -->
							<td width = "100" align = "center">좋아요${bean.likeCount }</td> <!-- 좋아요 수 -->
							<td width = "100" align = "center">조회수 : ${bean.viewCount }</td>
						</tr>
						<c:set var="number" value="${boardId-1 }"/>
					</c:forEach>
		</table>
		<p></p>
		<!-- 페이징 -->
		<center>
			<c:if test="${count >0 }"></c:if>
			<c:set var="pageCount" value="${count/pageSize + (count%pageSize==0? 0:1) }"/>
			<!-- 시작 페이지 숫자 지정 -->
			<c:set var="startPage" value="1"/>
			
			<c:if test="${currentPage%10 !=0 }">
				<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
				<c:set var="startPage" value="${result*10+1 }"/>
			</c:if>
			
			<!-- 한 번에 보여줄 페이지 블록 10개의 게시글 -->
            <c:set var="pageBlock" value="10" />
            <c:set var="endPage" value="${startPage+pageBlock-1 }" />
            
            <!-- 마지막 페이지 설정 -->
            <c:if test="${endPage>pageCount }">
            	<c:set var="endPage" value="${pageCount }"/>
            </c:if>   
            
            <!-- 이전 페이지로(10페이지 앞으로) 버튼-->
            <c:if test="${startPage>10 }">
            	<a href="BoardListCon.do?pageNum=${startPage-10 }" style= "text-decoration:none" class = "pagination">
            	[◀]
            	</a>
            </c:if>
			
			<!-- 10페이지 사이에 있는 항목으로 이동 -->
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="BoardListCon.do?pageNum=${i }" style="text-decoration:none">
			${i }
			</a>
			</c:forEach>
			
			<c:if test="${endPage<pageCount }">
				<a href="BoardListCon.do?pageNum=${startPage+10 }" class = "pagination">
				[▶]
				</a>
			</c:if>
		</center>
		</div>
	</div>
</body>
</html>