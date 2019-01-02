package com.atypon.lit.utility;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateAuthJson {
    public static void create(HttpServletRequest request){
        JSONObject jsonObject = (JSONObject) request.getAttribute("jsonStatus");
        HttpSession oldSession = request.getSession(false);
        if (request.getSession(false) != null) {
            oldSession.invalidate();
        }
        //generate a new session
        HttpSession newSession = request.getSession(true);
        //setting session to expiry in 5 mins
        //newSession.setMaxInactiveInterval(10*60);
        String id = String.valueOf(jsonObject.getInt("userId"));
        JSONObject idJson = new JSONObject();
        idJson.put("status", "sessionUserId");
        idJson.put("userId", id);
        // session.setAttribute("id",id);
        // jspContext.setAttribute("id", id);
        newSession.setAttribute("idJson", idJson);
    }
}
