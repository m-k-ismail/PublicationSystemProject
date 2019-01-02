package com.atypon.lit.utility;

import java.io.File;

public class ListDirectories {

    public static String  list(File folder) {
        File[] files = folder.listFiles();
        if (files != null){
            for (File fileEntry : files) {
                System.out.println("fileentry: "+fileEntry);
                if (fileEntry.isDirectory()) {
                    return fileEntry.getName();
                } else {
                    list(fileEntry);
                }
            }
        }
        return "";
    }
}
