package org.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span.Kind;
import io.opentelemetry.extension.annotations.WithSpan;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
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

    Counter visitCounter;

    public GreetingController(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Number of visits to the site")
                .register(registry);
    }

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {
        visitCounter.increment();
        logger.info("Before Service Method Call");
        this.greetingService.doSomeWorkNewSpan();
        logger.info("After Service Method Call");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting2")
    @WithSpan(value = "my-span", kind = Kind.INTERNAL)
    public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting3")
    public Greeting greeting3(@RequestParam(value = "name", defaultValue = "World") String name)
            throws InterruptedException {

        TimeUnit.SECONDS.sleep(3);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
