package com.atypon.lit.action;

import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.utility.FrontCommand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LicenseCtrl extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Gson gson = new Gson();
        JSONObject idJson = (JSONObject) request.getSession(false).getAttribute("idJson");
        ArticleDAO articleDAO = new ArticleDAO();
        JSONObject articleList = articleDAO.getArticlesByAdminId(idJson.get("userId").toString());
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> outputDoiList = gson.fromJson(articleList.get("articles").toString(), type);
        ArrayList<String> outputLicenseList = gson.fromJson(articleList.get("licenses").toString(), type);

        request.getServletContext().setAttribute("doi", outputDoiList);
        request.getServletContext().setAttribute("license", outputLicenseList);
        response.sendRedirect("/JSP/licenseControl.jsp");
    }
}
