package com.atypon.lit.domain.dao;

import com.atypon.lit.domain.model.ArticleEntity;
import org.json.JSONObject;

import java.util.ArrayList;

public interface IArticleDAO {
    void insert(ArticleEntity articleEntity);
    ArrayList<String> getDoiByIssueId(int id);
    boolean contains(String doi);
    JSONObject getArticlesByAdminId(String id);
    void updateArticleLicense(String license, String doi);
    int getLicenseByDoi(String doi);
}
