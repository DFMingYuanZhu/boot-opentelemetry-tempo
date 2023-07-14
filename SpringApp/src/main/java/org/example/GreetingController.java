package org.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Span.Kind;
import io.opentelemetry.extension.annotations.WithSpan;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2023/7/10 13:22
 */
@RestController
public class GreetingController {

    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting1")
    public Greeting greeting1(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {
        logger.info("greeting1!");
        greetingService.greeting1();
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting2")
    public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {
        logger.info("greeting2!");
        greetingService.greeting2();
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting3")
    public Greeting greeting3(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {
        logger.info("greeting3!");
        greetingService.greeting3();
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
