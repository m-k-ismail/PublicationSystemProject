package com.atypon.lit.utility;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.StringWriter;

public class XslTransformation {
    public static void transform(String htmlPath, Source xsl, Source xml){
        StringWriter sw = new StringWriter();
        try {
            FileWriter fw = new FileWriter(htmlPath);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transform = tFactory.newTransformer(xsl);
            transform.transform(xml, new StreamResult(sw));

            fw.write(sw.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out
                .println("Html generated successfully");
    }
}
