package com.atypon.lit.backstage;

import com.atypon.lit.publication.Publication;
import com.atypon.lit.utility.DtdReplacer;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.atypon.lit.constant.Path.ISSUE_NEW_DTD;
import static com.atypon.lit.constant.Path.ISSUE_OLD_DTD;

class XmlMetaExtract {
     static Publication extract(String issuePath, String articlePath, String articleNum) {

        String oldDTD = ISSUE_OLD_DTD.getFilepath();
        String newDTD = ISSUE_NEW_DTD.getFilepath();
        Publication publication = null;
        try {
            DtdReplacer.replace(issuePath, oldDTD, newDTD);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(issuePath);

            publication = new Publication();

            publication.setjCode(document.getElementsByTagName("journal-id").item(0).getTextContent());
            publication.setPublisherName(document.getElementsByTagName("publisher").item(0).getTextContent());
            publication.setSource(document.getElementsByTagName("journal-title").item(0).getTextContent());
            publication.setYear(Integer.parseInt(document.getElementsByTagName("year").item(0).getTextContent()));
            publication.setMonth(Integer.parseInt(document.getElementsByTagName("month").item(0).getTextContent()));
            publication.setVolume(Integer.parseInt(document.getElementsByTagName("volume").item(0).getTextContent()));
            publication.setIssueNum(Integer.parseInt(document.getElementsByTagName("issue").item(0).getTextContent()));
            publication.setIssueDoi(document.getElementsByTagName("issue-id").item(0).getTextContent());
            publication.setArticleDoi(articleNum);
            publication.setArticlePath(articlePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return publication;
    }
}
