package com.jiayuliu.apiserver.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws")
public class AmazonS3Properties {

    private String endpointUrl;
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String region;
}
