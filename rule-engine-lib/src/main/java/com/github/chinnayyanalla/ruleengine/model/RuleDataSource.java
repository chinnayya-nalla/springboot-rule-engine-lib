package com.github.chinnayyanalla.ruleengine.model;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleDataSource implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;

	private Map<String, RuleDefinition> ruleDefinitionMap = new HashMap<>();

}
