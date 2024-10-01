package com.ltechlab.truthordare.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long id;
    private String player;
    private String type;
    private String text;
    private Integer level;
}