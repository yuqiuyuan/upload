package com.czb.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName UploadFileController
 * @Description TODO
 * http://10.0.21.258:5566/upload
 * @Author drebander
 * @Date 2019-10-13 3:43 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/")
public class UploadFileController {
    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request) {

        System.out.println("接受到请求。。。");

        ServletInputStream sis = null;
        FileOutputStream fos = null;
        try {
            // 文件名
            String filename = request.getHeader("fileName");
            // 文件类型，例如：jpg、png、pdf...
            String fileType = request.getHeader("fileType");
            // 存储路径
            String filePath = "/czb/upload/";

            File file = new File(filePath + filename + "." + fileType);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            sis = request.getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while ((len = sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();
            System.out.println("接受成功～！");

            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();

            return "fail";
        } finally {
            try {
                if (sis != null) {
                    sis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/load")
    public String load(HttpServletRequest request) {
        return null;
    }
}
