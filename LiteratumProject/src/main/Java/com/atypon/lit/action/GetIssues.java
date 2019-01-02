package com.atypon.lit.action;

import com.atypon.lit.domain.dao.IssueDAO;
import com.atypon.lit.domain.dao.JournalDAO;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class GetIssues extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String source = String.valueOf(request.getParameter("source"));
        JournalDAO journalDAO = new JournalDAO();
        IssueDAO issueDAO = new IssueDAO();

        int id = journalDAO.getJournalBySource(source);
        ArrayList<String> issueDetails = issueDAO.getIssuesByJournalId(id);
        ArrayList<String> issueModifiedDetails = new ArrayList<>();
        ArrayList<String> issuesDoiList = new ArrayList<>();

        for (String item : issueDetails) {
            issuesDoiList.add(item.substring(item.indexOf("_") + 1)
                    .replace(".", "%2E")
                    .replace("/", "12123"));
            issueModifiedDetails.add(item.substring(0, item.indexOf("_")));
        }
        request.setAttribute("issues", issueModifiedDetails);
        request.setAttribute("doi", issuesDoiList);
        forward("/JSP/allIssues.jsp");
    }
}
