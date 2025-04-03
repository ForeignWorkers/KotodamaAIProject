package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class boardDAO extends SuperDAO{
    public void insertBoard(boardDTO board){
        getCon();

        try {
            String query = "INSERT INTO board (boardname, boardwriteid, boardwritename, viewcount, likecount, contentuuid)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, board.getBoardName());
            pstmt.setInt(2, board.getBoardWriteId());
            pstmt.setString(3, board.getBoardWriteName());
            pstmt.setInt(4, board.getViewCount());
            pstmt.setInt(5, board.getLikeCount());
            pstmt.setObject(6, UUID.fromString(board.getContentUUId()));  // UUID 처리
            pstmt.executeUpdate();

            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boardDTO selectOneBoard(int id){
        getCon();
        boardDTO board = new boardDTO();

        try {
            String countsql = "UPDATE board set viewCount = viewCount+1 WHERE boardId = ?";
            pstmt = conn.prepareStatement(countsql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try{
            String sql = "select * from board where boardId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                board.setBoardId(rs.getInt("boardId"));
                board.setBoardName(rs.getString("boardName"));
                board.setBoardWriteId(rs.getInt("boardWriteId"));
                board.setBoardWriteName(rs.getString("boardWriteName"));
                board.setViewCount(rs.getInt("viewCount"));
                board.setLikeCount(rs.getInt("likeCount"));
                board.setContentUUId(rs.getString("contentUUId"));

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public void increaseLikeCount(int id){
        getCon();
        try {
            String countsql = "UPDATE board set likeCount = likeCount+1 WHERE boardId = ?";
            pstmt = conn.prepareStatement(countsql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
