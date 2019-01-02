package com.atypon.lit.action;

import com.atypon.lit.utility.FrontCommand;
import com.atypon.lit.utility.OsUtils;


import javax.servlet.ServletException;
import java.io.IOException;

public class ShowWatLogin extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("/HTML/watLogin.html");
    }
}
