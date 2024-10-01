package com.ltechlab.truthordare.bot.actions.commands;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionCommands;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.cache.service.CacheService;
import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class StartAction extends Action {

    private final SetMyCommands setMyCommands;

    protected StartAction(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService, SetMyCommands setMyCommands) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
        this.setMyCommands = setMyCommands;
    }


    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(staticMessageProvider.getMessage(chatId, ActionMessages.HELLO));
        sendMessage(staticMessageProvider.getMessage(chatId, ActionMessages.HELLO_INPUT));
        setCommand(chatId, "remove");
        sendMessage(this.setMyCommands);
    }

    @Override
    public List<String> getKeys() {
        return List.of(ActionCommands.START);
    }
}