package model;

public class testDAO extends SuperDAO{
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
}
