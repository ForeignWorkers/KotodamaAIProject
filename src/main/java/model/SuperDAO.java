package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuperDAO {
    //DB 접속
    String url = "jdbc:postgresql://kotodama-sql.postgres.database.azure.com:5432/postgres?sslmode=require";
    String user = "kotodamaAdmin";
    //절대로 GIT 에 올리지마시고, 로컬에서 직접 값을 넣으세요!
    String password = "";

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public void getCon(){
        try{
            Class.forName("org.postgresql.Driver");
            //데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
