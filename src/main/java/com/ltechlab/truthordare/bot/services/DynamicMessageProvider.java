package com.ltechlab.truthordare.bot.services;

import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class DynamicMessageProvider {

    public final Map<String, String> messages;
    public final Map<String, String> variables;
    public final KeyboardProvider keyboardProvider;

    public DynamicMessageProvider(@Qualifier("tgMessages") Map<String, String> messages,
                                  @Qualifier("tgAnswerVariables") Map<String, String> variables,
                                  KeyboardProvider keyboardProvider) {
        this.messages = messages;
        this.variables = variables;
        this.keyboardProvider = keyboardProvider;
    }

    public SendMessage getMessage(String chatId, List<Object> params, String key) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        values(params).get(key).accept(sendMessage);
        return sendMessage;
    }

    private Map<String, Consumer<SendMessage>> values(List<Object> params) {
        Map<String, Consumer<SendMessage>> map = new HashMap<>();
        map.put(ActionMessages.STARTING_GAME, sendMessage -> {
            sendMessage.setText(String.format(messages.get(ActionMessages.STARTING_GAME), params.get(0), params.get(1)));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardNextQuestion());
        });
//        map.put(ActionMessages.NEXT_QUESTION, sendMessage -> {
//            sendMessage.setText(String.format(messages.get(ActionMessages.NEXT_QUESTION), params.get(0), params.get(1), params.get(2), params.get(3)));
//            sendMessage.setReplyMarkup(keyboardProvider.keyboardNextQuestion());
//        });
        return map;
    }

}