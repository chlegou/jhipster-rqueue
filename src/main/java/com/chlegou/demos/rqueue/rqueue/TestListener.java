package com.chlegou.demos.rqueue.rqueue;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestListener {


    @RqueueListener(value = "${rqueue.test.echo}")
    @Async
    public void echoQueue(String message) {
        log.info("echo delayed message: {}", message);
    }


}
