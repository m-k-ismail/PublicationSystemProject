package com.atypon.lit.filter;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebFilter(filterName = "AccessFilter", urlPatterns = "/action/CheckAccess")
public class AccessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Gson gson = new Gson();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        JSONObject userIdJson = (JSONObject) request.getSession(false).getAttribute("idJson");


        String userId = userIdJson.get("userId").toString();
        String doi = request.getParameter("doi");
        String issue = request.getParameter("issue");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost requestUrl = new HttpPost("http://localhost:8083/rs/litWS/getAccess/"+userId +"/"+ doi);
        requestUrl.addHeader("content-type", "application/json");
        try {
            HttpResponse urlResponse = httpClient.execute(requestUrl);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (urlResponse.getEntity().getContent())));

            String output;
            JSONObject jsonObject = null;
            while ((output = br.readLine()) != null) {
                jsonObject = gson.fromJson(output, JSONObject.class);
            }
            System.out.println(jsonObject);
            if (!jsonObject.getString("status").equals("ACCESS_ALLOWED")) {
                response.sendRedirect("/HTML/unauthorized.html");
            } else {
                response.sendRedirect("/action/ArticleHtml?doi="+doi+"&issue="+issue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
