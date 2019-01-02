package com.atypon.lit.action;

import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateLicense extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String license = request.getParameter("license");
        String doi = request.getParameter("doi");

        if(license == null){
            license = "1";
        }
        ArticleDAO articleDAO = new ArticleDAO();
        articleDAO.updateArticleLicense(license, doi);

        forward("/action/LicenseCtrl");
    }
}
