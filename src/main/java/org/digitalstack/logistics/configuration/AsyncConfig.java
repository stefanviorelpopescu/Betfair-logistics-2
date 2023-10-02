package org.digitalstack.logistics.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AsyncConfig {

    @Bean(name = "shippingExecutor")
    public ThreadPoolExecutor shippingExecutor() {
        return new ThreadPoolExecutor(4,4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.AbortPolicy());
    }

}
