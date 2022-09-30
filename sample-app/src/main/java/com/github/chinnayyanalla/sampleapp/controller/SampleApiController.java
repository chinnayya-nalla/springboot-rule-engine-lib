package com.github.chinnayyanalla.sampleapp.controller;

import com.github.chinnayyanalla.ruleengine.model.Rule;
import com.github.chinnayyanalla.ruleengine.service.RuleEngineValidationService;
import com.github.chinnayyanalla.sampleapp.exception.CustomException;
import com.github.chinnayyanalla.sampleapp.model.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleApiController implements SampleApi {

    private final RuleEngineValidationService ruleEngineValidationService;

    @Override
    public ResponseEntity<Payload> invokeRuleEngine(String mandatoryProperty) {

        log.info("Started Executing invokeRuleEngine Api");

        Payload payload = new Payload();
        payload.setMandatoryProperty(mandatoryProperty);

        Rule rule = new Rule();
        rule.setRuleCode("RL_1001");

        List response = ruleEngineValidationService.validate(payload, Payload.class, rule);
        if(!CollectionUtils.isEmpty(response)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, response);
        }

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
