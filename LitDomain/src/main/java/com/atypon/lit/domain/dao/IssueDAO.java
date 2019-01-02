package com.atypon.lit.domain.dao;

import com.atypon.lit.constant.Constants;
import com.atypon.lit.domain.model.IssueEntity;
import com.atypon.lit.utility.DBConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IssueDAO implements IIssueDAO{

    private DBConnectionManager dbConnectionManager;

    public IssueDAO() {
    }

    @Override
    public void insert(IssueEntity issueEntity) {
        this.dbConnectionManager = new DBConnectionManager();
        try {
            Statement stmt = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS).createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();

            if(contains(issueEntity.getDoi())) {
                PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                        .prepareStatement("INSERT INTO issue (journal_id, year ,month, volume, issueNum, doi) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, issueEntity.getJournalId());
                preparedStatement.setInt(2, issueEntity.getYear());
                preparedStatement.setInt(3, issueEntity.getMonth());
                preparedStatement.setInt(4, issueEntity.getVolume());
                preparedStatement.setInt(5, issueEntity.getIssueNum());
                preparedStatement.setString(6, issueEntity.getDoi());

                preparedStatement.executeUpdate();
                preparedStatement.close();

                stmt = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS).createStatement();
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");
                stmt.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
    }

    @Override
    public ArrayList<String> getIssuesByJournalId(int id) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        ArrayList<String> issueList = null;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT year, volume, issueNum, doi FROM issue WHERE journal_id = ?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            issueList = new ArrayList<>();

            while (rs.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rs.getInt("year"))
                        .append("-").append(rs.getInt("volume"))
                        .append("-").append(rs.getInt("issueNum"))
                        .append("_").append(rs.getString("doi"));

                if (!issueList.contains(stringBuilder.toString())) {
                    issueList.add(stringBuilder.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return issueList;
    }

    @Override
    public int getIssueByDoi(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        int id = 0;
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT id FROM issue WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id= rs.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return id;
    }

    @Override
    public boolean contains(String doi){
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT * FROM issue WHERE doi = ?");
            preparedStatement.setString(1, doi);
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

    @Override
    public int findIdByDoi(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT id FROM issue WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
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
    public int findJournalIdByDoi(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT journal_id FROM issue WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("journal_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return 0;
    }
}
