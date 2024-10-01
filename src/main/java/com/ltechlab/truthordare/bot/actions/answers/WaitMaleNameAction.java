package com.ltechlab.truthordare.bot.actions.answers;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionAnswers;
import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.cache.dto.CacheData;
import com.ltechlab.truthordare.cache.service.CacheService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class WaitMaleNameAction extends Action {


    protected WaitMaleNameAction(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        CacheData cacheData = cacheService.get(chatId);
        cacheData.setMaleName(text);
        cacheService.put(chatId, cacheData);

        sendMessage(staticMessageProvider.getMessage(chatId, ActionMessages.WAIT_FEMALE_NAME));
        setCommand(chatId, ActionAnswers.WAIT_FEMALE_NAME);
    }

    @Override
    public List<String> getKeys() {
        return List.of(ActionAnswers.WAIT_MALE_NAME);
    }
}