package com.atypon.lit.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DtdReplacer {
    public static void replace(String path, String oldDTD, String newDTD){
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }

            reader.close();
            // replace a word in a file
            //String newtext = oldtext.replaceAll("drink", "Love");

            //To replace a line in a file
            String newtext = oldtext.replaceAll(oldDTD, newDTD);
            FileWriter writer = new FileWriter(path);
            writer.write(newtext);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
