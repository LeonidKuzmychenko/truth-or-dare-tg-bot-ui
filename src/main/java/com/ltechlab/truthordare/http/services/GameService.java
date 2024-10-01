package com.ltechlab.truthordare.http.services;

import com.ltechlab.truthordare.http.model.CheckSessionDto;
import com.ltechlab.truthordare.http.model.QuestionDto;
import com.ltechlab.truthordare.http.model.SessionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GameService {

    @GetExchange("/game/session")
    ResponseEntity<CheckSessionDto> checkSession(@RequestParam("session") String session);

    @GetExchange("/game/question")
    ResponseEntity<QuestionDto> nextQuestion(@RequestParam("session") String session);

    @GetExchange("/game/start")
    ResponseEntity<SessionDto> initNewGame(@RequestParam("male") String male, @RequestParam("female") String female);
}
