package com.ltechlab.truthordare.bot.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;
import java.util.function.Consumer;

@Service
public class StaticMessageProvider {

    private final Map<String, Consumer<SendMessage>> messages;

    public StaticMessageProvider(@Qualifier("tgSendMessages") Map<String, Consumer<SendMessage>> messages) {
        this.messages = messages;
    }

    public SendMessage getMessage(String chatId, String key) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        this.messages.get(key).accept(sendMessage);
        return sendMessage;
    }

}