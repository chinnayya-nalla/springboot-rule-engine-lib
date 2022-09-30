package com.github.chinnayyanalla.ruleengine.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RuleMapping {

	/**
	 * Mandatory parameter class to describe the payload of the validation Object.
	 */
	Class<?> type();

	/**
	 * Human-readable description to accompany the rule name.
	 */
	String name();

	/**
	 * Human-readable description to accompany the rule code.
	 */
	String code();

}
