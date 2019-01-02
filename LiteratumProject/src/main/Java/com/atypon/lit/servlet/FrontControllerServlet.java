package com.atypon.lit.servlet;

import com.atypon.lit.action.UnknownCommand;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/action/*")
public class FrontControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doCommand(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doCommand(request, response);
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            String url = request.getRequestURI();
            int lastIndex = url.lastIndexOf("/");
            String command = url.substring(lastIndex+1);
            Class type = Class.forName(String.format(
                    "com.atypon.lit.action.%s", command));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    private void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }
}
