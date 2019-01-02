package com.atypon.lit.domain.dao;

import com.atypon.lit.domain.model.IssueEntity;

import java.util.ArrayList;

public interface IIssueDAO {
    void insert(IssueEntity issueEntity);
    ArrayList<String> getIssuesByJournalId(int id);
    int getIssueByDoi(String doi);
    boolean contains(String doi);

    int findIdByDoi(String doi);

    int findJournalIdByDoi(String doi);
}
