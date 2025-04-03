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
            String sql = "insert into members (userId, userName, userNikname, password) values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getNikname());
            pstmt.setString(4, user.getPassword());
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
            String sql = "select count(*) from members where userId = ?";
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
            String sql = "select count(*) from members where userNikname = ?";
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
    
    // 로그인 검증
    public boolean checkLogin(String id, String password) {
        boolean isValid = false;
        try {
            getCon();
            String sql = "select * from members where userId = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {  // 결과가 있으면 로그인 성공
                isValid = true;
            }
            conn.close();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 로그인 성공 여부
        return isValid;
    }
    
    
    
    
    
    //로그인한 회원 정보 가져오기
    public membersDTO getUserNum(int userNum) {
    	membersDTO user = null;
        try {
            getCon();
            String sql = "select * from members where userNum = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNum);
            rs = pstmt.executeQuery();
            //1행 전체 값이 나옴
            
            if (rs.next()) {
                user = new membersDTO();
                user.setUserNum(rs.getInt("userNum"));
                user.setId(rs.getString("userId"));
                user.setPassword(rs.getString("userName"));
                user.setName(rs.getString("userNikname"));
                user.setNikname(rs.getString("password"));
            }
            conn.close();
            rs.close();
            pstmt.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // 아이디로 userNum 조회하기
    public int getUserNumById(String userId) {
        int userNum = -1;
        try {
            getCon();
            String sql = "select userNum from members where userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            
            //userNum읽기
            if (rs.next()) {
                userNum = rs.getInt("userNum");
            }
            
            conn.close();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userNum;
    }

}
