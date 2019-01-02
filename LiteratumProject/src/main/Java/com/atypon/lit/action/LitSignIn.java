package com.atypon.lit.action;

import com.atypon.lit.domain.dao.UserDAO;
import com.atypon.lit.utility.FrontCommand;
import org.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;

public class LitSignIn extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        JSONObject jsonObject = new JSONObject();
        try {
            int userId = userDAO.getUserByUserAndPass(username, password);
            if (userId != 0) {
                jsonObject.put("status", "ok");
                jsonObject.put("userId", userId);
                request.setAttribute("jsonStatus", jsonObject);
                forward("/action/LitAuthentication");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status","false");
        jsonObject.put("id", "");
        request.setAttribute("jsonStatus", jsonObject);

        forward("/HTML/litLogin.html");
    }
}
