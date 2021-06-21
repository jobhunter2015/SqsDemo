package com.example.SqsWriterService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;

@Configuration
@EnableJms
public class SqsConfiguration {
    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Bean
    public AmazonSQSClient amazonSQSClient() {
        return new AmazonSQSClient(getAwsCredentials());
    }

    private AWSCredentials getAwsCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    private AWSCredentialsProvider getAwsCredentialsProvider() {
        return new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return getAwsCredentials();
            }

            @Override
            public void refresh() {
            }
        };
    }
}