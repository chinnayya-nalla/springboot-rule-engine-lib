package com.github.chinnayyanalla.sampleapp.rule;

import com.github.chinnayyanalla.ruleengine.annotation.RuleMapping;
import com.github.chinnayyanalla.ruleengine.annotation.RuleRepository;
import com.github.chinnayyanalla.ruleengine.model.Rule;
import com.github.chinnayyanalla.sampleapp.model.FaultDetail;
import com.github.chinnayyanalla.sampleapp.model.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@RuleRepository
@RequiredArgsConstructor
public class BusinessValidationRule {

    @RuleMapping(name = "Business Validation Rule", code = "RL_1001", type = Payload.class)
    public FaultDetail businessValidationRule(Rule rule, Payload payload) {

        if(Objects.isNull(payload)) {
            return null;
        }

        if(!StringUtils.hasText(payload.getMandatoryProperty())) {
            FaultDetail faultDetail = new FaultDetail();
            faultDetail.setCode(rule.getRuleCode());
            faultDetail.setReason("Mandatory Property Not Provided");
            faultDetail.setDescription("Mandatory Property cannot be null or empty");
            return faultDetail;
        }

        return null;

    }


}
