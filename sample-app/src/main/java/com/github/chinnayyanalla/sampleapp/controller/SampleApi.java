package com.github.chinnayyanalla.sampleapp.controller;

import com.github.chinnayyanalla.sampleapp.model.Payload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface SampleApi {

    @GetMapping(value = "/rule-engine", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Payload> invokeRuleEngine(@RequestParam(value = "mandatoryProperty", required = false) String mandatoryProperty);

}
