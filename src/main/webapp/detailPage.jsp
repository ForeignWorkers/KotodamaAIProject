<%@ page import="model.commentDTO" %>
<%@ page import="model.membersDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시글</title>
  <style>
    body {
      background-color: #e0f0ff;
      margin: 0;
      padding: 0;
    }

    .comment-form {
      background: #ffffff;
      border-radius: 10px;
      padding: 10px;
      margin: 20px 0;
    }

    .comment-form textarea {
      width: 98%;
      height: 60px;
      resize: none;
      padding: 8px;
      border-radius: 8px;
      border: 1px solid #ccc;
      font-family: inherit;
    }
    .comment-form button {
      margin-top: 6px;
      padding: 6px 14px;
      background-color: #66b3ff;
      color: white;
      font-weight: bold;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .comment-form button:hover {
      background-color: #4da3f7;
    }

    /* 기존 스타일 유지 + 좋아요 버튼 스타일 추가 */
    .like-btn {
      background-color: transparent;
      border: none;
      color: #ff4d6d;
      font-size: 14px;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 4px;
    }

    .like-btn:hover {
      text-decoration: underline;
    }

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

    .sidebar img {
      width: 30px;
      margin: 20px 0;
    }

    .container {
      margin-left: 60px;
      padding: 20px;
    }

    .post-box {
      background: #80cfff;
      padding: 20px;
      border-radius: 15px;
      box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
    }

    .post-header {
      display: flex;
      justify-content: space-between;
    }

    .post-title input {
      font-size: 20px;
      width: 300px;
    }

    .buttons {
      display: flex;
      gap: 10px;
    }

    .buttons button {
      padding: 8px 12px;
      font-weight: bold;
    }

    .audio-player {
      margin: 20px 0;
    }

    .comments {
      background: white;
      border-radius: 10px;
      padding: 10px;
    }

    .comment {
      background: #d8ecff;
      margin: 6px 0;
      padding: 8px;
      border-radius: 10px;
      display: flex;
      justify-content: space-between;
    }

    .pagination {
      margin-top: 10px;
      text-align: center;
    }

    .pagination span {
      margin: 0 5px;
      cursor: pointer;
    }

    .like-btn {
      display: flex;
      justify-content: flex-end;
      margin-top: 10px;
    }
  </style>
</head>
<body>

<%
  membersDTO member = (membersDTO) session.getAttribute("userbean");

  String username =  null;
  int userNum = 0;

  if (member != null) {
    username = member.getNikname();
    userNum = member.getUserNum();
  }

  request.setAttribute("userNum", userNum);  // ⭐️ JSTL에서도 사용 가능하게 설정
  request.setAttribute("username", username);

  System.out.println("userNUM" + userNum);
%>

<div class="sidebar">
  <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" onclick="location.href='MainPage.jsp'" alt="Home">
  <img src="https://cdn-icons-png.flaticon.com/512/56/56763.png" onclick="location.href='BoardListCon.do'" alt="List">
</div>

<div class="container">
  <div class="post-box">
    <div class="post-header">
      <div class="post-title">
        <div>
          <label>제 목 :</label>
          <text>${board.boardName}</text>
        </div>
        <div style="margin-top: 10px;">
          <label>조 회 수 :</label>
          <text>${board.viewCount}</text>
        </div>

      </div>
      <div class="buttons" style="display: flex; align-items: center; gap: 10px;">
        <text>${board.boardWriteName}</text>
        <c:if test="${board.boardWriteId == userNum}">
          <button onclick="location.href='deleteBoardCon.do?boardId=${board.boardId}'">삭제</button>
        </c:if>
      </div>
    </div>

    <div class="audio-player">
      <audio controls autoplay style="width: 100%;">
        <source src="https://63a0-180-80-107-4.ngrok-free.app/getMusic?uuid=${board.contentUUId}" type="audio/mpeg">
      </audio>
      <button class="like-btn" onclick="location.href='likeUpdateCon.do?boardId=${board.boardId}'">❤️ <span>${board.likeCount}</span></button>
    </div>

    <!-- 댓글 작성 폼 -->
    <div class="comment-form">
      <form action="commentInsertCon.do" method="post">
        <input type="hidden" name="boardId" value="${board.boardId}" />
        <input type="hidden" name="userNum" value="<%=userNum%>" />
        <input type="hidden" name="userName" value="<%=username%>" />

        <textarea name="comment" placeholder="댓글을 입력하세요..." required></textarea>
        <div style="text-align: right;">
          <button type="submit">등록</button>
        </div>
      </form>
    </div>

    <div class="comments">
      <c:forEach var="comment" items="${comments}">
        <div class="comment">
          <span>${comment.userName}</span>
          <span>${comment.comment}</span>
          <span><fmt:formatDate value="${comment.commentDate}" pattern="yyyy-MM-dd HH:mm" /></span>
        </div>
      </c:forEach>
    </div>

    <div class="pagination">
      댓글수 ${commentCount} &nbsp;&nbsp;

      <!-- 페이징처리 구현 -->
      <center>
        <c:if test="${commentCount>0 }">

          <c:set var = "pageCount" value="${commentCount/pageSize + (commentCount%pageSize==0? 0:1) }" />

          <!-- 시작 페이지 숫자 지정 -->
          <c:set var="startPage" value="1" />

          <c:if test="${currentPage%10 != 0 }">
            <fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
            <c:set var="startPage" value="${result*10+1 }" />

          </c:if>

          <!-- 한 번에 보여줄 페이지 블록 10개의 게시글 -->
          <c:set var="pageBlock" value="10" />
          <c:set var="endPage" value="${startPage+pageBlock-1 }" />

          <c:if test="${endPage>pageCount }">
            <c:set var="endPage" value="${pageCount}" />
          </c:if>

          <!-- 이전페이지로 이동-->
          <c:if test="${startPage>10 }">
            <a href="detailPageCon.do?pageNum=${startPage-10}&boardId=${board.boardId}" style="text-decoration:none">
              [이전]
            </a>
          </c:if>


          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <a href="detailPageCon.do?pageNum=${i}&boardId=${board.boardId}" style="text-decoration: none">
              [${i }]
            </a>
          </c:forEach>

          <c:if test="${endPage<pageCount }">

            <!-- 다음페이지로 이동 -->
            <a href="detailPageCon.do?pageNum=${startPage+10}&boardId=${board.boardId}" style="text-decoration:none">
              [다음]
            </a>
          </c:if>

        </c:if>
      </center>
    </div>
  </div>
</div>
</body>
</html>
