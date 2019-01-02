package com.atypon.lit.backstage;

import com.atypon.lit.publication.Publication;
import com.atypon.lit.utility.UploadJIA;
import org.json.JSONObject;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class BSListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("name:" + event.getName());

        System.out.println("-- HttpSessionAttributeListener#attributeAdded() --");
        System.out.printf("added attribute name: %s, value:%s %n", event.getName(),
                event.getValue());

        JSONObject jsonObject = (JSONObject) event.getValue();

        String status = jsonObject.get("status").toString();
        if (status.equals("extract")) {
            String id = jsonObject.get("id").toString();
            String issuePath = jsonObject.get("issuePath").toString();
            String articlePath = jsonObject.get("articlePath").toString();
            String articleNum = jsonObject.get("articleNum").toString();

            Publication publication = XmlMetaExtract.extract(issuePath, articlePath, articleNum);
            UploadJIA.uploadJournal(id, publication);
            UploadJIA.uploadIssue(publication);
            UploadJIA.uploadArticle(publication);

            System.out.println("Completed");
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("-- HttpSessionAttributeListener#attributeRemoved() --");
        System.out.printf("removed attribute name: %s, value:%s %n", event.getName(),
                event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("-- HttpSessionAttributeListener#attributeReplaced() --");
        System.out.printf("replaced attribute name: %s, value:%s %n", event.getName(),
                event.getValue());
    }
}