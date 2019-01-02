<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">


    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Article</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
                      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
                      crossorigin="anonymous"/>
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
                      integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
                      crossorigin="anonymous"/>
            </head>
            <body>
                <div class="container mt-3">
                    <xsl:apply-templates/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="contrib-group/contrib">
        <xsl:if test="@contrib-type = 'author'">
            <span>
                <xsl:apply-templates/>
            </span>
        </xsl:if>
    </xsl:template>

    <xsl:template match="//article-id"/>
    <xsl:template match="//article-categories"/>

    <xsl:template match="//history"/>

    <xsl:template match="//publisher"/>
    <xsl:template match="//aff"/>
    <xsl:template match="//author-notes"/>
    <xsl:template match="//journal-meta"/>
    <xsl:template match="//volume"/>
    <xsl:template match="//issue"/>
    <xsl:template match="//fpage"/>
    <xsl:template match="//lpage"/>

    <xsl:template match="//kwd-group"/>
    <xsl:template match="//pub-date"/>
    <xsl:template match="//permissions"/>

    <xsl:template match="//custom-meta-group"/>

    <xsl:template match="title-group/article-title">
        <h1>
            <xsl:apply-templates/>
        </h1>
    </xsl:template>

    <xsl:template match="//p">
        <p>
            <xsl:apply-templates/>
        </p>
    </xsl:template>

    <xsl:template match="//abstract">
        <div class="mt-2">
            <h3>
                Abstract
            </h3>
            <p>
                <xsl:apply-templates/>
            </p>
            <hr/>
        </div>
    </xsl:template>

    <xsl:template match="//sec">
        <section>
            <xsl:apply-templates/>
        </section>
    </xsl:template>

    <xsl:template match="//title">
        <h3>
            <xsl:apply-templates/>
        </h3>
    </xsl:template>


    <xsl:template match="//italic">
        <i>
            <xsl:apply-templates/>
        </i>
    </xsl:template>

    <xsl:template match="//bold">
        <b>
            <xsl:apply-templates/>
        </b>
    </xsl:template>

    <xsl:template match="//xref">
        <a href="#">
            <xsl:apply-templates/>
        </a>
    </xsl:template>

    <xsl:template match="ref-list">
        <section>
            <xsl:apply-templates select="title"/>
            <ul>
                <xsl:apply-templates select="ref|@*"/>
                <br/>
            </ul>
        </section>
    </xsl:template>

</xsl:stylesheet>