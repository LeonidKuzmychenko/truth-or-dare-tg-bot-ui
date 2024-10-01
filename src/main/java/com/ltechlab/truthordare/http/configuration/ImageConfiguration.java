package com.ltechlab.truthordare.http.configuration;

import com.ltechlab.truthordare.http.services.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ImageConfiguration {


    @Bean
    public ImageService imageService() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:3000")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(ImageService.class);
    }
}
