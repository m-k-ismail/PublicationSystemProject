package com.atypon.lit.utility;

import java.sql.*;

public class DBConnectionManager {
    private Connection con;

    public DBConnectionManager(){

    }

    public Connection getConnection (String url, String u, String p){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(url+"?user="+u+"&password="+ p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeConnection(){
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}