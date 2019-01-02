package com.atypon.lit.action;

import com.atypon.lit.utility.CreateAuthJson;
import com.atypon.lit.utility.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class WatAuthentication extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        CreateAuthJson.create(request);
        forward("/action/ShowUpload");
    }

}
