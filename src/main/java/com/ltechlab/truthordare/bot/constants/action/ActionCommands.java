package com.ltechlab.truthordare.bot.constants.action;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActionCommands {

    public static final String ANY = null;
    public static final String START = "/start";
    public static final String MENU = "/menu";
    public static final String INIT_GAME = "\uD83C\uDD95";

    public static final String INIT_GAME_2 = "/newgame";

    public static final String NEXT_QUESTION = "\u27A1";

    public static final List<String> LIST = Arrays.asList(
            START, MENU, INIT_GAME, INIT_GAME_2, NEXT_QUESTION
    );
}