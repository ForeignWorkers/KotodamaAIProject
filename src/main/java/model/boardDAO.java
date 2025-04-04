package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.Vector;

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
            pstmt.setString(6, board.getContentUUId());
            pstmt.executeUpdate();

            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boardDTO selectOneBoard(int id, boolean isCountView){
        getCon();
        boardDTO board = new boardDTO();

        if(isCountView){
            try {
                String countsql = "UPDATE board set viewCount = viewCount+1 WHERE boardId = ?";
                pstmt = conn.prepareStatement(countsql);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

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
    
  //전체 포스트의 갯수를 반환하는데 쓸 메서드
    public int getAllPostCount() {
    	
    	getCon();
    	
    	int count = 0;
    	
    	try {
			
    		String sql = "select count(*) from board";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			count = rs.getInt(1);
    		}
    		conn.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return count;
    }
    
    //전체 포스트를 리스트에 출력
    public Vector<boardDTO> getAllPost(int startRow, int endRow){
    	
    	getCon();
    	Vector<boardDTO> v = new Vector<boardDTO>();
    	try {
			//현재 페이지에 최신글 10개 가져오기.
    		String sql = "select * \r\n"
    				+ "from (\r\n"
    				+ "    select A.*, row_number() over (order by boardid desc) as Rnum\r\n"
    				+ "    from board A\r\n"
    				+ ") subquery\r\n"
    				+ "where subquery.Rnum >= ? and subquery.Rnum <= ?";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, startRow);
    		pstmt.setInt(2, endRow);
    		rs = pstmt.executeQuery();
    		
			
    		while(rs.next()) { 
			  boardDTO bean = new boardDTO();
			  
			  //불러온 목록의 게시글에 들어갈 내용물들.
			  bean.setBoardId(rs.getInt(1));
			  bean.setBoardName(rs.getString(2));
			  bean.setBoardWriteName(rs.getString(4));
			  bean.setViewCount(rs.getInt(5));
			  bean.setLikeCount(rs.getInt(6));
			  
			  v.add(bean);
			  }
    		conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return v;
    }

    public void deleteBoard(int id){
        getCon();
        try {
            String sql = "delete from board where boardId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBoardIdUseUUID(String uuid){
        getCon();
        int findId = 0;
        try {
            String sql = "select * from board where contentUUId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uuid);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                findId = rs.getInt("boardId");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return findId;
    }
}
