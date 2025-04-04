package model;

import javax.xml.stream.events.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class commentDAO extends SuperDAO{
    public void insertComment(String comment, int userId, String nickname, int boardId) {
        getCon();
        try {
            String sql = "INSERT INTO comments (userId, userName, refBoardId, comment) " +
                    "VALUES (?, ?, ?, ?)";  // commentDate 생략!

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, nickname);
            pstmt.setInt(3, boardId);
            pstmt.setString(4, comment);

            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector<commentDTO> getComments(int refBoardId){
        Vector<commentDTO> comments = new Vector<>();
        getCon();

        try{
            String sql = "select * from comments where refboardid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, refBoardId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                commentDTO dto = new commentDTO();
                dto.setCommentId(rs.getInt("commentId"));
                dto.setUserId(rs.getInt("userId"));
                dto.setUserName(rs.getString("userName"));
                dto.setRefBoardId(rs.getInt("refBoardId"));
                dto.setComment(rs.getString("comment"));
                dto.setCommentDate(rs.getDate("commentDate"));
                comments.add(dto);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public int getCommentCount(int refBoardId){
        getCon();
        try{
            String sql = "select count(*) from comments where refboardid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, refBoardId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            conn.close();
            return rs.getInt(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //#3. 전체 게시글 출력
    public Vector<commentDTO> getCommentRange(int refBoardId, int startRow, int endRow){
        getCon();
        Vector<commentDTO> v = new Vector<>();
        String sql = "SELECT * FROM comments WHERE refBoardId = ? ORDER BY commentId ASC LIMIT ? OFFSET ?";


        try {
            //댓글까지 포함하여 최신글 10개 가져옴
            pstmt = conn.prepareStatement(sql);

            int offset = startRow - 1;
            int limit = endRow - startRow + 1;

            pstmt.setInt(1, refBoardId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                commentDTO dto = new commentDTO();
                dto.setCommentId(rs.getInt("commentId"));
                dto.setUserId(rs.getInt("userId"));
                dto.setUserName(rs.getString("userName"));
                dto.setRefBoardId(rs.getInt("refBoardId"));
                dto.setComment(rs.getString("comment"));
                dto.setCommentDate(rs.getDate("commentDate"));

                v.add(dto);
            }
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}
