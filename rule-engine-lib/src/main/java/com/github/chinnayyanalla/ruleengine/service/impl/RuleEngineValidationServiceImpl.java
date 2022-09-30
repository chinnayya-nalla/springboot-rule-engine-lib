package com.github.chinnayyanalla.ruleengine.service.impl;


import com.github.chinnayyanalla.ruleengine.config.SpringApplicationContext;
import com.github.chinnayyanalla.ruleengine.model.Rule;
import com.github.chinnayyanalla.ruleengine.model.RuleDataSource;
import com.github.chinnayyanalla.ruleengine.model.RuleDefinition;
import com.github.chinnayyanalla.ruleengine.service.RuleEngineValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RuleEngineValidationServiceImpl implements RuleEngineValidationService {


	private final RuleDataSource ruleDataSource;
	private final SpringApplicationContext springApplicationContext;

	@Override
	public List<Object> validate(Object validationObject, Class validationObjectClass, Rule... rules) {

		if (Objects.isNull(rules)) {
			return Collections.emptyList();
		}

		List<Object> responseObjectList = Arrays.stream(rules).map(rule -> {

			Object faultDetail = null;
			RuleDefinition ruleDefinition = ruleDataSource.getRuleDefinitionMap().getOrDefault(rule.getRuleCode(), null);

			if(Objects.isNull(ruleDefinition) || !validationObjectClass.getName().equalsIgnoreCase(ruleDefinition.getType().getName())){
				return null;
			}

			Object object = springApplicationContext.getBean(ruleDefinition.getBeanName());

			try {
				faultDetail = (Object) ruleDefinition.getRule().invoke(object, rule, validationObject);
			} catch (Exception exception) {
				log.error("Exception Occurred while trying to invoke rule: {}, ruleName: {}, /nRootCause: {}", rule.getRuleCode(),
						ruleDefinition.getRule().getName(), exception);
			}
			return faultDetail;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(responseObjectList)) {
			responseObjectList = Collections.emptyList();
		}
		return responseObjectList;
	}

}
