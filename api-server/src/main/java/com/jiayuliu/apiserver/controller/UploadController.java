package com.jiayuliu.apiserver.controller;

import com.jiayuliu.apiserver.pojo.Result;
import com.jiayuliu.apiserver.utils.AmazonS3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private AmazonS3Util amazonS3Util;

//    AWS s3 upload and delete
    @PostMapping("/uploadS3")
    public Result uploadFile(@RequestPart(value = "file") MultipartFile file) {
        log.info("file:{}", file);
        String url = amazonS3Util.uploadFile(file);
        System.out.println("url: " + url);
        return Result.success(url);
    }

    @PostMapping("/deleteS3")
    public String deleteFile(@RequestBody String fileURL) {
        log.info("fileName:{}", fileURL);
        return amazonS3Util.deleteFileFromS3Bucket(fileURL);

    }


//    local upload
//    @PostMapping("/upload")
//    public Result upload(MultipartFile image) throws Exception{
//        log.info("image:{}", image);
//
//        String originalFilename = image.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + suffix;
//        log.info("newFileName:{}", newFileName);
//        System.out.println("newFileName: " + newFileName);
//        image.transferTo(new File("/Users/jiayuliu/Projects/idea/EmployeeMgmtSystem/api-server/src/main/resources/images/" + newFileName));
//        return Result.success();
//    }

}
