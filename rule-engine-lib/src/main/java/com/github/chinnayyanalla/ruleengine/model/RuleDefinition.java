package com.github.chinnayyanalla.ruleengine.model;

import lombok.*;

import java.io.Serializable;
import java.lang.reflect.Method;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleDefinition implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;

	private String beanName;
	private Method rule;
	private Class type;
	private String name;
	private String code;

}
