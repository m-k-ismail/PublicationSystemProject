package com.atypon.lit.ws;

import com.atypon.lit.domain.dao.ArticleDAO;
import com.atypon.lit.domain.dao.UserDAO;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("litWS")
public class AccessControl {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @POST
    @Path("/getAccess/{userId}/{doi}")
    public Response getAccess(@PathParam("userId") String userId, @PathParam("doi") String doi) {
        Gson gson = new Gson();
        ArticleDAO articleDAO = new ArticleDAO();
        UserDAO userDAO = new UserDAO();
        int articleLicense = articleDAO.getLicenseByDoi(doi);
        int userLicense = userDAO.getUserLicense(userId);
        JSONObject jsonObject = new JSONObject();
        if (userLicense < articleLicense) {
            jsonObject.put("status", "ACCESS_DENIED");
        } else{
            jsonObject.put("status", "ACCESS_ALLOWED");
        }
        return Response.ok(gson.toJson(jsonObject)).build();
    }
}