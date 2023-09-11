package com.jiayuliu.apiserver.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class AmazonS3Util {


    private AmazonS3 amazonS3;

    @Autowired
    private AmazonS3Properties amazonS3Properties;

//    @Value("${aws.endpointUrl}")
//    private String endpointUrl;
//    @Value("${aws.bucketName}")
//    private String bucketName;
//    @Value("${aws.accessKey}")
//    private String accessKey;
//    @Value("${aws.secretKey}")
//    private String secretKey;
//    @Value("${aws.region}")
//    private String region;

    @PostConstruct()
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(amazonS3Properties.getAccessKey(), amazonS3Properties.getSecretKey());
        this.amazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
//        this.amazonS3 = new AmazonS3Client(credentials);
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileURL = "";
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            File file = convertMultipartFileToFile(multipartFile);

            String suffix = originalFilename.substring(index);
            String fileName = UUID.randomUUID().toString() + suffix;

            fileURL =  amazonS3Properties.getBucketName() + ".s3." + amazonS3Properties.getRegion() + ".amazonaws.com/" + fileName;
            uploadFileToBucket(fileName, file);
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private void uploadFileToBucket(String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(amazonS3Properties.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }

    public String deleteFileFromS3Bucket(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(amazonS3Properties.getBucketName(), fileName));

        return "Successfully deleted";
    }
}
