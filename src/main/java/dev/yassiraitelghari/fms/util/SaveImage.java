package dev.yassiraitelghari.fms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveImage {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    public static String save(MultipartFile file, String newFileName) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = UPLOAD_DIR + newFileName;

        file.transferTo(new File(filePath));

        return filePath;
    }

}
