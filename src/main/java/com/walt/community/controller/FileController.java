package com.walt.community.controller;

import com.qingstor.sdk.exception.QSException;
import com.walt.community.dto.FileDTO;
import com.walt.community.exception.CustomizeException;
import com.walt.community.provider.QingCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: walt1012
 * @date: 2020/8/18
 */
@Controller
public class FileController {

    @Autowired
    private QingCloudProvider qingCloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("editormd-image-file");
        try {
            if (file != null) {
                String url = qingCloudProvider.upload(file.getOriginalFilename(), file.getInputStream());
                FileDTO fileDTO = new FileDTO();
                fileDTO.setSuccess(1);
                fileDTO.setUrl(url);
                return fileDTO;
            }
        } catch (IOException | CustomizeException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/WechatIMG88.jpeg");
        return fileDTO;
    }
}
