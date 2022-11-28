package com.java.api.exception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class CustomFeignConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
}