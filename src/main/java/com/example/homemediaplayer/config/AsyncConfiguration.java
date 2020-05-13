package com.example.homemediaplayer.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(
                    Throwable throwable, Method method, Object... obj) {

                System.out.println("Exception message - " + throwable.getMessage());
                System.out.println("Method name - " + method.getName());
                for (Object param : obj) {
                    System.out.println("Parameter value - " + param);
                }
            }
        };
    }
}
