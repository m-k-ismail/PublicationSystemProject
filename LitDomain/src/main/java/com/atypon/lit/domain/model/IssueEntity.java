package com.atypon.lit.domain.model;

import java.util.Objects;


public class IssueEntity {
    private int id;
    private int journalId;
    private int year;
    private int month;
    private int volume;
    private int issueNum;
    private String doi;
    private JournalEntity journalByJournalId;

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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueEntity that = (IssueEntity) o;
        return id == that.id &&
                year == that.year &&
                month == that.month &&
                volume == that.volume &&
                issueNum == that.issueNum &&
                Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, volume, issueNum, doi);
    }


    public JournalEntity getJournalByJournalId() {
        return journalByJournalId;
    }

    public void setJournalByJournalId(JournalEntity journalByJournalId) {
        this.journalByJournalId = journalByJournalId;
    }
}
