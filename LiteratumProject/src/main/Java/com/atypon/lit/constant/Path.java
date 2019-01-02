package com.atypon.lit.constant;

public enum Path {
    ARTICLE_OLD_DTD("\"JATS-archivearticle1.dtd\">"),
    ARTICLE_NEW_DTD("\"/home/mismail/IdeaProjects/LitProject/jats.dtd\">"),
    ISSUE_OLD_DTD("\"JATS-1.0/Atypon-Issue-Xml.dtd\">"),
    ISSUE_NEW_DTD("\"/home/mismail/IdeaProjects/LitProject/jats-extract.dtd\">"),
    XSL_FILE_PATH("/home/mismail/IdeaProjects/LiteratumProject/src/main/webapp/XSLT/article.xsl"),
    UPLOADED_FILES_DIR("/home/mismail/Desktop/atypon/"),
    OUTPUT_FOLDER("/home/mismail/Desktop/UNZIPED files");

    private String filepath;

    Path(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }
}