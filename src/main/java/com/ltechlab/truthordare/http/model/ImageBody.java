package com.ltechlab.truthordare.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageBody {

    private String type;
    private Integer level;
    private String name;
    private String question;
}
