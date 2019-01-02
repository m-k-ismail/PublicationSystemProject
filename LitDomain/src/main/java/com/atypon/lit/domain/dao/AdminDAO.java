package com.atypon.lit.domain.dao;

import com.atypon.lit.constant.Constants;
import com.atypon.lit.utility.DBConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO implements IAdminDAO{
    private DBConnectionManager dbConnectionManager;

    public AdminDAO() {
    }

    @Override
    public int getAdminByUserAndPass(String username, String password) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select * from admin where username = ? and password = ?";
            ps = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS).prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return 0;
    }
}
