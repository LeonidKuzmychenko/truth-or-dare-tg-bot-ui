package com.ltechlab.truthordare.bot.services;


import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.http.model.UserUpdateRequest;
import com.ltechlab.truthordare.http.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class BotUserService {

    private final UserService userService;

    public BotUserService(UserService userService) {
        this.userService = userService;
    }

    public BotUser getUser(String chatId) {
        try {
            ResponseEntity<BotUser> responseEntity = userService.getUser(chatId).get();
            if (responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
//                log.info("Получение юзера произошло успешно");
                return responseEntity.getBody();
            } else {
                log.error("Получение юзера произошло с ошибкой");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("User do not exist");
    }

    public void setCommand(String chatId, String command) {
        BotUser user = getUser(chatId);
        user.setCommand(command);
        try {
            ResponseEntity<BotUser> responseEntity = userService.updateUser(new UserUpdateRequest(chatId, command)).get();
            if (responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
//                log.info("Юзер успешно обновился");
                return;
            } else {
                log.error("Юзер обновился с ошибкой");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("User update Error");
    }

}