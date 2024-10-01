package com.ltechlab.truthordare.http.services;

import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.http.model.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.concurrent.CompletableFuture;

public interface UserService {

    @GetExchange("/v1/users")
    CompletableFuture<ResponseEntity<BotUser>> getUser(@RequestParam("chatId") String chatId);

    @PutExchange("/v1/users")
    CompletableFuture<ResponseEntity<BotUser>> updateUser(@RequestBody UserUpdateRequest request);
}