package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    /**
     * vm args
     * -javaagent:agent/opentelemetry-javaagent-all.jar -Dotel.trace.exporter=jaeger -Dotel.exporter.jaeger.endpoint=localhost:14250  -Dotel.resource.attributes=service.name=spring-boot-app -Dotel.javaagent.debug=false -Dotel.metrics.exporter=none
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
}
