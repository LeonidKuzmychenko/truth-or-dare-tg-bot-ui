package com.ltechlab.truthordare.http.model;

import com.ltechlab.truthordare.bot.model.BotUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckSessionDto {
    private BotUser isExist;
}