package org.example;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2023/7/10 13:19
 */
@Service
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);


    public void greeting1() throws InterruptedException {
        logger.info("greeting1");
        TimeUnit.SECONDS.sleep(1);
    }

    @WithSpan("custom-span")
    public void greeting2() throws InterruptedException {
        logger.info("greeting2");
        TimeUnit.SECONDS.sleep(2);
    }


    public void greeting3() throws InterruptedException {
        logger.info("greeting3");
        Span span = Span.current();
        span.setAttribute("a1", "greeting3-inner-block");
        span.addEvent("greeting3-inner-block-start");
        TimeUnit.SECONDS.sleep(3);
        span.addEvent("greeting3-inner-block-end");
    }

}
