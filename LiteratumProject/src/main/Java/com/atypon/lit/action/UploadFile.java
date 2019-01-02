package com.atypon.lit.action;

import com.atypon.lit.utility.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import static com.atypon.lit.constant.Path.OUTPUT_FOLDER;
import static com.atypon.lit.constant.Path.UPLOADED_FILES_DIR;

public class UploadFile extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        JSONObject idJson = (JSONObject) session.getAttribute("idJson");

        try {
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = sf.parseRequest(request);

            for (FileItem item : multiFiles) {
                String path = UPLOADED_FILES_DIR.getFilepath()+ item.getName();
                item.write(new File(path));

                String itemPath = item.getName().substring(0, item.getName().length() - 4);

                String unzipPath = OUTPUT_FOLDER.getFilepath() + File.separator + itemPath;
                UnzipFile.unZipIt(path, unzipPath);

                File folder = new File(unzipPath);
                String mainFile = ListDirectories.list(folder);

                File file = new File(unzipPath + File.separator + mainFile);
                String[] directories = file.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File current, String name) {
                        return new File(current, name).isDirectory();
                    }
                });

                String issuePath = "";
                String articlePath = "";
                String articleNum = "";
                if (!(mainFile.equals("")) && directories != null) {
                    issuePath = unzipPath + File.separator + mainFile + File.separator + "issue-files" + File.separator + mainFile + ".xml";
                    for (String directory : directories) {
                        if (!directory.equals("issue-files")) {
                            articlePath = unzipPath + File.separator + mainFile + File.separator + directory + File.separator + directory + ".xml";
                            articleNum = directory;
                        }
                    }
                } else {
                    System.out.println("No files found");
                }

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("status", "extract");
                jsonObject.put("id", idJson.get("userId"));
                jsonObject.put("issuePath", issuePath);
                jsonObject.put("articlePath", articlePath);
                jsonObject.put("articleNum", articleNum);

                if (!(request.getSession(false) == null)) {
                    session.setAttribute("json", jsonObject);
                } else {
                    System.out.println("Null session");
                }
                session.removeAttribute("json");
                System.out.println("file uploaded");
                response.sendRedirect("/action/ShowUpload");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}