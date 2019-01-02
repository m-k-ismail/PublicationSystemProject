package com.atypon.lit.publication;

public class Publication {
    private String jCode;
    private String publisherName;
    private String source;

    private int year;
    private int month;
    private int volume;
    private int issueNum;
    private String issueDoi;

    private String articleDoi;
    private String articlePath;

    public Publication() {
    }

    public String getjCode() {
        return jCode;
    }

    public void setjCode(String jCode) {
        this.jCode = jCode;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    public String getIssueDoi() {
        return issueDoi;
    }

    public void setIssueDoi(String issueDoi) {
        this.issueDoi = issueDoi;
    }

    public String getArticleDoi() {
        return articleDoi;
    }

    public void setArticleDoi(String articleDoi) {
        this.articleDoi = articleDoi;
    }

    public String getArticlePath() {
        return articlePath;
    }

    public void setArticlePath(String articlePath) {
        this.articlePath = articlePath;
    }
}
