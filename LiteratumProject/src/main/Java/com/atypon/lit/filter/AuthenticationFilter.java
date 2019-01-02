package com.atypon.lit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/action/*")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getRequestURI().equals("/action/WatAuthentication")
                || request.getRequestURI().equals("/action/LitAuthentication")
                || request.getRequestURI().equals("/action/ShowWatLogin")
                || request.getRequestURI().equals("/")
                || request.getRequestURI().equals("/action/ShowLitLogin")
                ||request.getRequestURI().equals("/action/WatSignIn")
                || request.getRequestURI().equals("/action/LitSignIn")
        ) {
            chain.doFilter(req, resp);
        }else {
            if (request.getSession(false) == null) {   //checking whether the session exists
                this.context.log("Unauthorized access request");
                System.out.println("invalid");
                response.sendRedirect(request.getContextPath() + "/action/ShowWatLogin");
            } else {
                System.out.println("Authorized");
                // pass the request along the filter chain
                chain.doFilter(req, resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

}
