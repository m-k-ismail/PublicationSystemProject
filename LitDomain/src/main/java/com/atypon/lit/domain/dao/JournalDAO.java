package com.atypon.lit.domain.dao;

import com.atypon.lit.constant.Constants;
import com.atypon.lit.domain.model.JournalEntity;
import com.atypon.lit.utility.DBConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JournalDAO implements IJournalDAO {
    private DBConnectionManager dbConnectionManager;

    public JournalDAO() {
    }

    @Override
    public void insert(JournalEntity journalEntity) {
        this.dbConnectionManager = new DBConnectionManager();
        try {
            if(contains(journalEntity.getSource())) {
                PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                        .prepareStatement("INSERT INTO journal (admin_id, jcode ,publisher_name, source) VALUES (?,?, ?, ?)");

                preparedStatement.setInt(1, journalEntity.getAdminId());
                preparedStatement.setString(2, journalEntity.getJcode());
                preparedStatement.setString(3, journalEntity.getPublisherName());
                preparedStatement.setString(4, journalEntity.getSource());

                preparedStatement.executeUpdate();
                preparedStatement.close();
                dbConnectionManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
    }

    @Override
    public ArrayList<String> getAllJournals() {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;

        ArrayList<String> sourceList = new ArrayList<>();
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT source FROM journal");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String source = rs.getString("source");
                sourceList.add(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return sourceList;
    }

    @Override
    public int getJournalBySource(String source) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        int id = 0;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT id FROM journal WHERE source = ?");
            preparedStatement.setString(1, source);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
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
    public ArrayList<Integer> getJournalByAdminId(String adminId) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        ArrayList<Integer> id = new ArrayList<>();
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT id FROM journal WHERE admin_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(adminId));
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id.add(rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return id;
    }

    @Override
    public int findJournalById(String issueDoi) {
        this.dbConnectionManager = new DBConnectionManager();
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT id FROM journal WHERE jcode = ?");
            preparedStatement.setString(1, issueDoi.substring(8, issueDoi.indexOf("_")));
            rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return 0;
    }


    @Override
    public boolean contains(String source){
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT * FROM journal WHERE source = ?");
            preparedStatement.setString(1, source);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return true;
    }
}
