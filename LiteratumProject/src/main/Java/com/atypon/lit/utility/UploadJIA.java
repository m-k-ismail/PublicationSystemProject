package com.atypon.lit.utility;

import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.domain.dao.IssueDAO;
import com.atypon.lit.domain.dao.JournalDAO;
import com.atypon.lit.domain.model.ArticleEntity;
import com.atypon.lit.domain.model.IssueEntity;
import com.atypon.lit.domain.model.JournalEntity;
import com.atypon.lit.publication.Publication;


public class UploadJIA {
    public static void uploadJournal(String userId, Publication publication) {
        JournalEntity journalEntity = new JournalEntity();
        JournalDAO journalDAO = new JournalDAO();

        journalEntity.setAdminId(Integer.parseInt(userId));
        journalEntity.setJcode(publication.getjCode());
        journalEntity.setPublisherName(publication.getPublisherName());
        journalEntity.setSource(publication.getSource());

        journalDAO.insert(journalEntity);

    }

    public static void uploadIssue(Publication publication) {
        IssueEntity issueEntity = new IssueEntity();
        IssueDAO issueDAO = new IssueDAO();
        JournalDAO journalDAO = new JournalDAO();

        issueEntity.setJournalId(journalDAO.findJournalById(publication.getIssueDoi()));
        issueEntity.setYear(publication.getYear());
        issueEntity.setMonth(publication.getMonth());
        issueEntity.setVolume(publication.getVolume());
        issueEntity.setIssueNum(publication.getIssueNum());
        issueEntity.setDoi(publication.getIssueDoi());

        issueDAO.insert(issueEntity);
    }

    public static void uploadArticle(Publication publication) {
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleDAO articleDAO = new ArticleDAO();
        IssueDAO issueDAO= new IssueDAO();

        articleEntity.setJournalId(issueDAO.findJournalIdByDoi(publication.getIssueDoi()));
        articleEntity.setIssueId(issueDAO.findIdByDoi(publication.getIssueDoi()));
        articleEntity.setDoi(publication.getArticleDoi());
        articleEntity.setArticlePath(publication.getArticlePath());

        articleDAO.insert(articleEntity);
    }


}
