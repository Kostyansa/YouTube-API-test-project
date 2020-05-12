package com.example.homemediaplayer.config;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
public class YoutubeAPIConfig {

    @Bean
    public JsonFactory jsonFactory(){
        return JacksonFactory.getDefaultInstance();
    }

    @Bean
    public HttpTransport httpTransport(){
        return new NetHttpTransport();
    }

    @Bean
    public HttpRequestInitializer httpRequestInitializer(){
        return request -> { };
    }

    @Bean
    public YouTube youTube(HttpTransport httpTransport,
                           JsonFactory jsonFactory,
                           HttpRequestInitializer httpRequestInitializer){
        return new YouTube.Builder(
                httpTransport,
                jsonFactory,
                httpRequestInitializer)
                .setApplicationName("Home MediaPlayer")
                .build();
    }
}
