package com.ltechlab.truthordare.http.services;

import com.ltechlab.truthordare.http.model.ImageBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ImageService {

    @PostExchange("/generate")
    ResponseEntity<byte[]> generate(@RequestBody ImageBody imageBody);

}
