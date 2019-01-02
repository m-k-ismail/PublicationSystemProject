package com.atypon.lit.domain.dao;

import com.atypon.lit.domain.model.JournalEntity;

import java.util.ArrayList;

public interface IJournalDAO {
    void insert(JournalEntity journalEntity);
    boolean contains(String source);
    ArrayList<String> getAllJournals();
    int getJournalBySource(String source);

    ArrayList<Integer> getJournalByAdminId(String adminId);

    int findJournalById(String string);
}
