package model;

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
}
