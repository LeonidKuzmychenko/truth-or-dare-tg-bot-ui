package com.ltechlab.truthordare.bot.services.image;

import com.ltechlab.truthordare.http.model.ImageBody;

import java.io.InputStream;

public interface IRenderService {
    InputStream render(ImageBody imageBody);
}
