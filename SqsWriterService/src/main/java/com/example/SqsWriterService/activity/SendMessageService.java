package com.example.SqsWriterService.activity;

import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.example.SqsWriterService.model.RequestMessage;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SendMessageService {
    @Autowired
    AmazonSQSClient amazonSQSClient;

    @Autowired
    protected ObjectMapper objectMapper;

    @Value("${sqs.url}")
    private String sqsUrl;

    public void send(final RequestMessage simpleMessage) throws IOException {
        simpleMessage.setId(UUID.randomUUID().toString());
        amazonSQSClient.sendMessage(new SendMessageRequest(sqsUrl, objectMapper.writeValueAsString(simpleMessage)));
    }
}