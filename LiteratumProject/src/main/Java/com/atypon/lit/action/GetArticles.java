package com.atypon.lit.action;


import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.domain.dao.IssueDAO;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class GetArticles extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String IssueDoi = request.getParameter("doi");
        IssueDoi = IssueDoi.replace("%2E", ".");
        IssueDoi = IssueDoi.replace("12123", "/");

        IssueDAO issueDAO = new IssueDAO();
        int id = issueDAO.getIssueByDoi(IssueDoi);

        ArticleDAO articleDAO = new ArticleDAO();
        ArrayList<String> articlesList = articleDAO.getDoiByIssueId(id);

        request.setAttribute("doiList", articlesList);
        request.setAttribute("issue", IssueDoi);

        forward("/JSP/allArticles.jsp");
    }
}
