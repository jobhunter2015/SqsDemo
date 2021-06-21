package com.example.SqsReaderService.activity;

import com.example.SqsReaderService.model.RequestMessage;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JmsListenerService {
    @Autowired
    ObjectMapper objectMapper;

    @JmsListener(destination = "pangbin-test-sqs")
    public void jamesQueueListener(final String requestJson) throws JsonMappingException, JsonProcessingException {
        System.out.println("Got message: " + objectMapper.readValue(requestJson, RequestMessage.class));
    }
}