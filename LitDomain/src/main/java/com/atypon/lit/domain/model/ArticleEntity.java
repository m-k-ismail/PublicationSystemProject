package com.atypon.lit.domain.model;

import java.util.Objects;

public class ArticleEntity {
    private int id;
    private int journalId;
    private int issueId;
    private int licenseId;
    private String doi;
    private String articlePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getArticlePath() {
        return articlePath;
    }

    public void setArticlePath(String articlePath) {
        this.articlePath = articlePath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return id == that.id &&
                Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doi);
    }
}
