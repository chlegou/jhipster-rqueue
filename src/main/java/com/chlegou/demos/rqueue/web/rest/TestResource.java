package com.chlegou.demos.rqueue.web.rest;


import com.github.sonus21.rqueue.core.RqueueMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestResource {

    private RqueueMessageSender rqueueMessageSender;

    @Value("${rqueue.test.echo}")
    private String echoQueue;
    @Value("${rqueue.test.echo-timespan}")
    private long echoTimespan;


    public TestResource(RqueueMessageSender rqueueMessageSender) {
        this.rqueueMessageSender = rqueueMessageSender;
    }


    @GetMapping("/echo/{message}")
    public ResponseEntity<Map<String, String>> postMessage(@PathVariable String message) {
        log.info("echo instant message: {}", message);
        Map<String, String> result = new HashMap<>();
        result.put("message", message);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/echo/delay/{message}")
    public ResponseEntity<Void> informNextRoll(@PathVariable String message) {
        log.info("queuing delayed message: {}", message);
        rqueueMessageSender.enqueueIn(echoQueue, message, echoTimespan, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
