package com.atypon.lit.action;

import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.utility.*;

import javax.servlet.ServletException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.atypon.lit.constant.Path.*;

public class ArticleHtml extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String issue = request.getParameter("issue");
        String doi = request.getParameter("doi");
        String oldDTD = ARTICLE_OLD_DTD.getFilepath();
        String newDTD = ARTICLE_NEW_DTD.getFilepath();

        ArticleDAO articleDAO = new ArticleDAO();
        String xmlPath = articleDAO.getArticlePathByDoi(doi);

        File xmlFile = new File(xmlPath);
        File htmlFile = new File(request.getServletContext().getInitParameter("SharedHTML") + doi + ".html");
        File xslFile = new File(XSL_FILE_PATH.getFilepath());


        if (!((xmlFile.lastModified() > htmlFile.lastModified())
                || (xslFile.lastModified() > htmlFile.lastModified()))) {
            System.out.println("file Exist!!");
        } else {
            htmlFile.delete();
            DtdReplacer.replace(xmlPath, oldDTD, newDTD);
            Source xsl = new StreamSource(xslFile);
            Source xml = new StreamSource(xmlFile);

            String htmlPath = request.getServletContext().getInitParameter("SharedHTML") + doi + ".html";

            XslTransformation.transform(htmlPath, xsl, xml);
        }

        URI uri;
        try {
            if (OsUtils.isLinux()) {
                uri = new URI(request.getServletContext().getInitParameter("SharedHTML") + doi + ".html");
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("/usr/bin/google-chrome -new-tab " + uri);
                response.sendRedirect("/action/GetArticles?doi=" + issue);
            } else{
                uri = new URI(request.getServletContext().getInitParameter("SharedHTML") + doi + ".html");
                uri.normalize();
                Desktop.getDesktop().browse(uri);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
