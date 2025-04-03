<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="sidebar">
  <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Home">
  <img src="https://cdn-icons-png.flaticon.com/512/56/56763.png" alt="List">
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
      <div class="buttons">
        <button>삭제</button>
        <text alignment-baseline="auto">${board.boardWriteName}</text>
      </div>
    </div>

    <div class="audio-player">
      <audio controls autoplay style="width: 100%;">
        <source src="https://63a0-180-80-107-4.ngrok-free.app/getMusic?uuid=${board.contentUUId}" type="audio/mpeg">
      </audio>
      <button class="like-btn" onclick="location.href='likeUpdateCon.do?boardId=${board.boardId}'">❤️ <span>${board.likeCount}</span></button>
    </div>

    <div class="comments">
      <div class="comment"><span>댓글자</span><span>내용</span><span>시간</span></div>
      <div class="comment"><span>댓글자</span><span>내용</span><span>시간</span></div>
      <div class="comment"><span>댓글자</span><span>내용</span><span>시간</span></div>
    </div>

    <div class="pagination">
      댓글수 3 &nbsp;&nbsp;
      <span>&lt;</span>
      <span>1</span><span>2</span><span>3</span><span>4</span>
      <span>&gt;</span>
    </div>
  </div>
</div>

</body>
</html>
