package com.neroll.controller;

import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${image-folder-absolute-path}")
    private String imageFolderPath;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping
    public Result<String> uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename.lastIndexOf(".") != -1)
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newGeneratedFilename = UUID.randomUUID() + suffix;

        File imageFolder = new File(imageFolderPath);

        try {
            file.transferTo(new File(imageFolder, newGeneratedFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String returnUrl = "http://localhost:" + serverPort + "/image/" + newGeneratedFilename;
        return Result.success("上传成功", returnUrl);

    }

}
