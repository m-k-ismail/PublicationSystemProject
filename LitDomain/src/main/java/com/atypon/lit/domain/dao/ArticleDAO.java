package com.atypon.lit.domain.dao;

import com.atypon.lit.constant.Constants;
import com.atypon.lit.domain.model.ArticleEntity;
import com.atypon.lit.utility.DBConnectionManager;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleDAO implements IArticleDAO {
    private DBConnectionManager dbConnectionManager;

    public ArticleDAO() {
    }

    @Override
    public void insert(ArticleEntity articleEntity) {
        this.dbConnectionManager = new DBConnectionManager();

        try {
            if (contains(articleEntity.getDoi())) {
                Statement stmt = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS).createStatement();
                stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                stmt.close();

                PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                        .prepareStatement("INSERT INTO article (journal_id ,issue_id, doi, article_path) "
                                + "VALUES (?, ?, ?, ?)");
                preparedStatement.setInt(1, articleEntity.getJournalId());
                preparedStatement.setInt(2, articleEntity.getIssueId());
                preparedStatement.setString(3, articleEntity.getDoi());
                preparedStatement.setString(4, articleEntity.getArticlePath());

                preparedStatement.executeUpdate();
                preparedStatement.close();

                stmt = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS).createStatement();
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.dbConnectionManager.closeConnection();
        }
    }

    @Override
    public ArrayList<String> getDoiByIssueId(int id) {
        this.dbConnectionManager = new DBConnectionManager();
        ResultSet rs;
        ArrayList<String> issueList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT doi FROM article WHERE issue_id = ?");
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                issueList.add(rs.getString("doi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.dbConnectionManager.closeConnection();
        }
        return issueList;
    }

    @Override
    public boolean contains(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT * FROM article WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
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
    public JSONObject getArticlesByAdminId(String adminId) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        Map<String, Integer> articleMap = new HashMap<String, Integer>();

        JournalDAO journalDAO = new JournalDAO();
        ArrayList<Integer> jId = journalDAO.getJournalByAdminId(adminId);
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        ArrayList<String> articleList = new ArrayList<>();
        ArrayList<String> licenseList = new ArrayList<>();

        try {
            for (Integer id : jId) {
                System.out.println("id: " + id);
                preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                        .prepareStatement("SELECT * FROM article WHERE journal_id = ?");
                preparedStatement.setInt(1, id);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    articleList.add(String.valueOf(rs.getString("doi")));
                    licenseList.add(String.valueOf(rs.getInt("license_id")));
                }
            }

            jsonObject.put("articles", gson.toJson(articleList));
            jsonObject.put("licenses", gson.toJson(licenseList));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return jsonObject;
    }

    @Override
    public void updateArticleLicense(String license, String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("UPDATE article SET license_id = ? WHERE doi = ?");
            preparedStatement.execute("SET FOREIGN_KEY_CHECKS=0");

            if (license.equals("true")) {
                preparedStatement.setInt(1, 2);
            } else {
                preparedStatement.setInt(1, 1);
            }
            preparedStatement.setString(2, doi);
            preparedStatement.executeUpdate();
            preparedStatement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
    }

    @Override
    public int getLicenseByDoi(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        int id = 0;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT license_id FROM article WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("license_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return id;
    }

    public String getArticlePathByDoi(String doi) {
        this.dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = dbConnectionManager.getConnection(Constants.JDBC_DB_URL, Constants.JDBC_USER, Constants.JDBC_PASS)
                    .prepareStatement("SELECT article_path FROM article WHERE doi = ?");
            preparedStatement.setString(1, doi);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("article_path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.dbConnectionManager.closeConnection();
        }
        return "";
    }
}
