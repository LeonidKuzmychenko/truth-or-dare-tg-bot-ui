package com.ltechlab.truthordare.bot.services.image;

import com.ltechlab.truthordare.http.model.ImageBody;
import com.ltechlab.truthordare.http.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class RenderServiceImpl implements IRenderService {

    private final ImageService imageService;

    public RenderServiceImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    public InputStream render(ImageBody imageBody) {
        try {
            ResponseEntity<byte[]> responseEntity = imageService.generate(imageBody);
            byte[] byteArray = responseEntity.getBody();
            return new ByteArrayInputStream(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
