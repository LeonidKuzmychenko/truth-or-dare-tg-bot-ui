package com.ltechlab.truthordare.bot.actions.commands;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionCommands;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.bot.services.image.PhotoService;
import com.ltechlab.truthordare.cache.dto.CacheData;
import com.ltechlab.truthordare.cache.service.CacheService;
import com.ltechlab.truthordare.http.model.QuestionDto;
import com.ltechlab.truthordare.http.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;








import java.util.List;

@Component
public class NextQuestionAction extends Action {
    private final GameService gameService;
    private final PhotoService photoService;

    protected NextQuestionAction(
            MessageSender messageSender,
            StaticMessageProvider staticMessageProvider,
            DynamicMessageProvider dynamicMessageProvider,
            KeyboardProvider keyboardProvider,
            BotUserService botUserService,
            CacheService cacheService,
            GameService gameService,
            PhotoService photoService) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
        this.gameService = gameService;
        this.photoService = photoService;
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        CacheData cacheData = cacheService.get(chatId);
        QuestionDto question = nextQuestion(cacheData.getSession());

        InputFile inputFile = photoService.renderPhoto(question);
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);
        sendMessage(sendPhoto);
    }

    private QuestionDto nextQuestion(String session) {
        ResponseEntity<QuestionDto> response = gameService.nextQuestion(session);
        return response.getBody();
    }

    @Override
    public List<String> getKeys() {
        return List.of(ActionCommands.NEXT_QUESTION);
    }
}