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
* { margin: 0;
	padding: 0;
}
body {
 background-color:#e0f0ff;
 }
 /* 사이드바 */
        .sidebar {
        width: 60px;
      	background: #66b3ff;
      	height: 100vh;
      	float: left;
      	display: flex;
      	flex-direction: column;
      	align-items: center;
      	padding-top: 20px;
        }

        .sidebar img { width: 40px; height: 40px; }
/* 컨텐츠 영역 */
        .content {
        	margin-left: 60px;
      		padding: 20px;
        }
        .review-box {
        	
            background: #80cfff;
            padding: 10px 0;
            border-radius: 50px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
            border: 3px solid black;
            
        }

        .review-item *{
        	
        	color: #000000;
        	text-align: center;
        }
        
        .review-list {
   
    display: flex;
    flex-direction: column; /* 테이블이 세로 정렬될 수 있도록 설정 */
    overflow-y: auto;
    background-color: #ffffff;
    border-radius: 50px;
    margin: 0 20px; /* 가운데 정렬 */
}


.review-item {
    width: 97%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #BAE2FA;
    margin: 5px 0;
    margin-left : 15px;
    flex-wrap: wrap;
    padding: 10px;
    border-radius: 50px;
    border : 2px black;
}
span{
	
	text-align: center;
	margin : 5px;
	
}

span .boardName{
	
	display : inline-block;
	text-align: center;
	vertical-align: middle;
 	min-width: 150px;
}

/* 페이지 네비게이션 */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 10px;
            margin-bottom : 10px;
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
			<div class = "review-list">
					<c:set var="boardId" value = "${boardId }" />
					<c:forEach var="bean" items="${v }">
						<div class="review-item">
							<span id="boardid">${bean.boardId }</span><!-- 클릭시 넘어갈 보드 인포 -->
							<span class="boardName">
								<a href="detailPageCon.do?boardId=${bean.boardId}&isCountView=${true}">
								${bean.boardName }
							</a>
							</span>
							<span id="boardwrite">${bean.boardWriteName }</td> <!-- 작성자 -->
							<span id="boardlike">좋아요${bean.likeCount }</td> <!-- 좋아요 수 -->
							<span id="boardviewcount">조회수 : ${bean.viewCount }</td>
						</div>
						<c:set var="number" value="${boardId-1 }"/>
					</c:forEach>
		</table>
		<!-- 페이징 -->
		<div class = "pagination">
		<center>
			<c:if test="${postCount >0 }"></c:if>
			<c:set var="pageCount" value="${postCount/pageSize + (postCount%pageSize==0? 0:1) }"/>
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
			[${i }]
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
	</div>
</body>
</html>