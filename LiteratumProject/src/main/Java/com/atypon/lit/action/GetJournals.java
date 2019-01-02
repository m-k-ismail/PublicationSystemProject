package com.atypon.lit.action;

import com.atypon.lit.domain.dao.JournalDAO;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class GetJournals extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        JournalDAO journalDAO = new JournalDAO();
        ArrayList<String> journalsSourceArray = journalDAO.getAllJournals();

        request.setAttribute("sources", journalsSourceArray);
        forward("/JSP/allJournals.jsp");
    }
}
