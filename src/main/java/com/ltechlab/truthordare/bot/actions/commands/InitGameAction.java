package com.ltechlab.truthordare.bot.actions.commands;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionAnswers;
import com.ltechlab.truthordare.bot.constants.action.ActionCommands;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.cache.service.CacheService;
import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class InitGameAction extends Action {


    protected InitGameAction(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(staticMessageProvider.getMessage(chatId, ActionMessages.INIT_GAME));
        sendMessage(staticMessageProvider.getMessage(chatId, ActionMessages.WAIT_MALE_NAME));
        setCommand(chatId, ActionAnswers.WAIT_MALE_NAME);
    }

    @Override
    public List<String> getKeys() {
        return List.of(ActionCommands.INIT_GAME, ActionCommands.INIT_GAME_2);
    }
}