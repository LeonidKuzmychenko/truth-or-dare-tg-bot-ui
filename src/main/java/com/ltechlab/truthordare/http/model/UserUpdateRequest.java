package com.ltechlab.truthordare.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String chatId;
    private String command;
}
