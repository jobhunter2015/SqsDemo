package com.example.SqsWriterService.controller;

import com.example.SqsWriterService.activity.SendMessageService;
import com.example.SqsWriterService.model.RequestMessage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SqsController {
    @Autowired
    private SendMessageService sendMessageService;

    @GetMapping()
    public String getAll(@RequestParam final String name, @RequestParam final String value) throws IOException {
        final RequestMessage message = RequestMessage.builder().name(name).value(value).build();
        sendMessageService.send(message);
        return "Message sent.";
    }
}
