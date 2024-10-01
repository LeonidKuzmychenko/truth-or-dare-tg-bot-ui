package com.ltechlab.truthordare.bot;

import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import lombok.extern.slf4j.Slf4j;
import com.ltechlab.truthordare.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class Action {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;

    public final StaticMessageProvider staticMessageProvider;
    public final DynamicMessageProvider dynamicMessageProvider;
    public final KeyboardProvider keyboardProvider;

    public final CacheService cacheService;

    private final MessageSender messageSender;
    private final BotUserService botUserService;

    protected Action(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService) {
        this.messageSender = messageSender;
        this.staticMessageProvider = staticMessageProvider;
        this.dynamicMessageProvider = dynamicMessageProvider;
        this.keyboardProvider = keyboardProvider;
        this.botUserService = botUserService;
        this.cacheService = cacheService;
    }

    public void run(Update update, BotUser botUser, String chatId, String text) {
        log.info("__________________________________________");
        log.info("Start action '{}'", getClass().getSimpleName());
        log.info("With command = '{}'", botUser.getCommand());
        log.info("With message = '{}'", text);
        action(update, botUser, chatId, text);
        log.info("End action '{}'", getClass().getSimpleName());
    }

    public void sendMessage(SendMessage sendMessage) {
        messageSender.send(sendMessage);
    }

    public void sendMessage(SendPhoto sendPhoto) {
        messageSender.send(sendPhoto);
    }

    public void sendMessage(SetMyCommands commands) {
        messageSender.send(commands);
    }

    public void setCommand(String chatId, String command) {
        botUserService.setCommand(chatId, command);
    }

    protected abstract void action(Update update, BotUser botUser, String chatId, String text);

    public abstract List<String> getKeys();

    @Autowired
    void add(ActionRouter actionRouter) {
        getKeys().forEach(key -> actionRouter.put(key, Action.this));
    }
}