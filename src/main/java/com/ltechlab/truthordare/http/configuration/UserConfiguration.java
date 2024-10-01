package com.ltechlab.truthordare.http.configuration;

import com.ltechlab.truthordare.http.services.UserService;
import com.ltechlab.truthordare.http.services.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class UserConfiguration {


    @Bean
    public UserService userService(){
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:1010")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(UserService.class);
    }

    @Bean
    public GameService gameService(){
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:1212")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(GameService.class);
    }

}
