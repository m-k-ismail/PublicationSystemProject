package com.atypon.lit.domain.dao;


import com.atypon.lit.constant.Constants;
import com.atypon.lit.utility.DBConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {
    private DBConnectionManager dbConnectionManager;

    public UserDAO() {
    }

    @Override
    public int getUserByUserAndPass(String username, String password) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement ps;
        ResultSet rs;
        int id= 0;
        try {
            String sql = "select id from user where username = ? and password = ?";
            ps = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return id;
    }

    @Override
    public int getUserLicense(String userId) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        int id= 0;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT license_id FROM user WHERE id = ?");
            preparedStatement.setString(1, userId);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("license_id");
            }
            dbConnectionManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return id;
    }

}
