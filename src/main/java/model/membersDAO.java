package model;

import java.util.Vector;

public class membersDAO extends SuperDAO{
    public void insertTable() {
        getCon();
        String sql = "INSERT INTO testmembers VALUES(?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 2);
            pstmt.setString(2, "호랑이");
            pstmt.setString(3, "test3@naver.com");
            pstmt.executeUpdate();

            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectTable() {
        getCon();
        String sql = "SELECT * FROM testmembers";
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("email"));
            }

            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //회원 추가
    public void insertUser(membersDTO user) {
        try {
            getCon();
            String sql = "insert into members (id, name, password, nikname) values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getNikname());
            pstmt.executeUpdate();
            conn.close();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //id중복 확인
    public boolean isIdExist(String id) {
        try {
            getCon();
            String sql = "select count(*) from members where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // 이메일이 이미 존재하면 true 반환
            }
            
            conn.close();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //닉네임 중복확인
    public boolean isNiknameExist(String nikname) {
        try {
            getCon();
            String sql = "select count(*) from members where nikname = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nikname);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // 닉네임이 이미 존재하면 true 반환
            }
            conn.close();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
    public Vector<membersDTO> getAllPost(int startRow, int endRow){
    	
    	getCon();
    	Vector<membersDTO> v = new Vector<membersDTO>();
    	try {
			//현재 페이지에 최신글 10개 가져오기.
    		String sql = "select * from (select A.*, Rownum Rnum from (select * from board order by board_id desc) A) where Rnum>=? and Rnum<=?";
    		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, startRow);
    		pstmt.setInt(2, endRow);
    		rs = pstmt.executeQuery();
    		
			
			  while(rs.next()) { membersDTO bean = new membersDTO();
			  
			  //불러온 목록의 게시글에 들어갈 내용물들.
			  bean.setPostID(rs.getInt(1));
			  bean.setPostSubject(rs.getString(2));
			  bean.setNikname(rs.getString(3));
			  bean.setViewcount(rs.getInt(4));
			  bean.setLikecount(rs.getInt(5));
			  
			  v.add(bean);
			  }
    		conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return v;
    }
    
}
