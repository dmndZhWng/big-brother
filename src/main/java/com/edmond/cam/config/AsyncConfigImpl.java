package com.edmond.cam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfigImpl implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        Executor executor = new SimpleAsyncTaskExecutor();
        return new SimpleAsyncTaskExecutor();
    }
}
