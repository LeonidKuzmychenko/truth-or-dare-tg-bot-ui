package com.ltechlab.truthordare.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ltechlab.truthordare.bot.constants.action.ActionCommands;
import com.ltechlab.truthordare.bot.services.KeyboardProvider;
import com.ltechlab.truthordare.bot.constants.mapkey.ActionMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
public class MessageConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("tgMessages")
    public Map<String, String> provideMessages(ObjectMapper mapper) throws IOException {
        return parse(mapper, "tg_messages_ru.json");
    }

    @Bean("tgAnswerVariables")
    public Map<String, String> provideAnswerVariables(ObjectMapper mapper) throws IOException {
        return parse(mapper, "tg_answer_variables_ru.json");
    }

    @Bean("botMenuList")
    public SetMyCommands provideMenu() {
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand(ActionCommands.INIT_GAME_2, "Начать новую игру"));
        return new SetMyCommands(botCommandList, new BotCommandScopeDefault(), "ru");
    }

//    @Bean("questionHtml")
//    public String provideQuestionHtml() throws IOException, URISyntaxException {
//        URL fileURL = this.getClass().getClassLoader().getResource("index.html");
//        URI fileURI = fileURL.toURI();
//        return Files.readString(Path.of(fileURI));
//    }

    @Bean("tgSendMessages")
    public Map<String, Consumer<SendMessage>> values(@Qualifier("tgMessages") Map<String, String> messages, KeyboardProvider keyboardProvider) {
        Map<String, Consumer<SendMessage>> map = new HashMap<>();
        map.put(ActionMessages.HELLO, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.HELLO));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
        map.put(ActionMessages.HELLO_INPUT, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.HELLO_INPUT));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardStartNewGame());
        });
        map.put(ActionMessages.INIT_GAME, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.INIT_GAME));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
        map.put(ActionMessages.WAIT_MALE_NAME, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.WAIT_MALE_NAME));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
        map.put(ActionMessages.WAIT_FEMALE_NAME, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.WAIT_FEMALE_NAME));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
        map.put("getErrorMessage", sendMessage -> {
            sendMessage.setText("Ошибочка вышла\nВозможно функционал еще не готов \uD83D\uDE2D");
        });
        map.put("getChooseVariantFromOptionsMessage", sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.CHOOSE_A_VARIANT_FROM_OPTIONS));
        });
        return map;
    }

    private Map<String, String> parse(ObjectMapper objectMapper, String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class);
        List<Map<String, String>> messages = objectMapper.readValue(inputStream, type);
        return messages.stream().collect(Collectors.toMap(map -> map.get("key"), map -> map.get("value")));
    }

}