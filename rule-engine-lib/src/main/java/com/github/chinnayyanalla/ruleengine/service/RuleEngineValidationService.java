package com.github.chinnayyanalla.ruleengine.service;

import com.github.chinnayyanalla.ruleengine.model.Rule;

import java.util.List;

public interface RuleEngineValidationService {

	List<Object> validate(Object validationObject, Class validationObjectClass, Rule... rules);

}
