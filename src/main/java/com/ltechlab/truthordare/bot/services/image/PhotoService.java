package com.ltechlab.truthordare.bot.services.image;

import com.ltechlab.truthordare.http.model.ImageBody;
import com.ltechlab.truthordare.http.model.QuestionDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.InputStream;

@Service
public class PhotoService {

    private final IRenderService IRenderService;

    public PhotoService(IRenderService IRenderService) {
        this.IRenderService = IRenderService;
    }

    public InputFile renderPhoto(QuestionDto question){
        String questionText = question.getText();
        ImageBody imageBody = new ImageBody(question.getType(), question.getLevel(), question.getPlayer(), questionText);
        InputStream inputStream = IRenderService.render(imageBody);
        return new InputFile(inputStream, "render.jpeg");
    }
}
