package com.ltechlab.truthordare.bot.actions.commands;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionCommands;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.cache.service.CacheService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Component
public class DefaultAction extends Action {


    protected DefaultAction(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(staticMessageProvider.getMessage(chatId,"getChooseVariantFromOptionsMessage"));
    }

    @Override
    public List<String> getKeys() {
        return Collections.singletonList(ActionCommands.ANY);
    }
}