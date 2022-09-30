package com.github.chinnayyanalla.ruleengine.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RuleRepository {

	/**
	 * Human-readable description to accompany the rule name.
	 */
	String name() default "";

}
