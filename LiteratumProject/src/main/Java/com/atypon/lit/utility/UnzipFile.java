package com.atypon.lit.utility;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {

    public static void unZipIt(String zipFile, String outPath){
        byte[] buffer = new byte[1024];

        try{
            File folder = new File(outPath);
            if(!folder.exists()){
                folder.mkdir();
            }

            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                File newFile = new File(outPath + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}