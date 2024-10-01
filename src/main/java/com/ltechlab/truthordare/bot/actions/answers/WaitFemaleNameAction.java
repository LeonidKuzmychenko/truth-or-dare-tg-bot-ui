package com.ltechlab.truthordare.bot.actions.answers;

import com.ltechlab.truthordare.bot.Action;
import com.ltechlab.truthordare.bot.constants.action.ActionAnswers;
import com.ltechlab.truthordare.bot.model.BotUser;
import com.ltechlab.truthordare.bot.services.*;
import com.ltechlab.truthordare.cache.dto.CacheData;
import com.ltechlab.truthordare.cache.service.CacheService;
import com.ltechlab.truthordare.http.model.QuestionDto;
import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import com.ltechlab.truthordare.bot.services.image.PhotoService;
import com.ltechlab.truthordare.http.model.SessionDto;
import com.ltechlab.truthordare.http.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class WaitFemaleNameAction extends Action {

    private final GameService gameService;
    private final PhotoService photoService;

    protected WaitFemaleNameAction(MessageSender messageSender, StaticMessageProvider staticMessageProvider, DynamicMessageProvider dynamicMessageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, CacheService cacheService, GameService gameService, PhotoService photoService) {
        super(messageSender, staticMessageProvider, dynamicMessageProvider, keyboardProvider, botUserService, cacheService);
        this.gameService = gameService;
        this.photoService = photoService;
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        CacheData cacheData = cacheService.get(chatId);
        cacheData.setFemaleName(text);
        cacheService.put(chatId, cacheData);

        setCommand(chatId, null);

        String male = cacheData.getMaleName();
        String female = cacheData.getFemaleName();

        sendMessage(dynamicMessageProvider.getMessage(chatId, List.of(male, female), ActionMessages.STARTING_GAME));

        SessionDto session = startGame(male, female);
        cacheData.setSession(session.getSession());
        cacheService.put(chatId, cacheData);
        QuestionDto question = nextQuestion(session.getSession());

        InputFile inputFile = photoService.renderPhoto(question);
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);
        sendMessage(sendPhoto);

//        List<Object> vars = List.of(question.getType(), question.getPlayer(), question.getLevel(), question.getText());
//        sendMessage(dynamicMessageProvider.getMessage(chatId, vars, ActionMessages.NEXT_QUESTION));
    }

    private QuestionDto nextQuestion(String session) {
        ResponseEntity<QuestionDto> response = gameService.nextQuestion(session);
        return response.getBody();
    }

    private SessionDto startGame(String male, String female) {
        ResponseEntity<SessionDto> response = gameService.initNewGame(male, female);
        return response.getBody();
    }

    @Override
    public List<String> getKeys() {
        return List.of(ActionAnswers.WAIT_FEMALE_NAME);
    }
}