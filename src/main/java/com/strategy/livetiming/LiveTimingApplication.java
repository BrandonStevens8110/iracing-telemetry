package com.strategy.livetiming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.strategy.livetiming",
        "com.joffrey.iracing.irsdkjava"  // 👈 This tells Spring to scan `irsdk_java`
})
public class LiveTimingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiveTimingApplication.class, args);
    }
}
