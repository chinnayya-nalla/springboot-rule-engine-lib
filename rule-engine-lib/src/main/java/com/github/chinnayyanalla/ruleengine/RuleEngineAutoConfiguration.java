package com.github.chinnayyanalla.ruleengine;

import com.github.chinnayyanalla.ruleengine.annotation.RuleMapping;
import com.github.chinnayyanalla.ruleengine.annotation.RuleRepository;
import com.github.chinnayyanalla.ruleengine.config.SpringApplicationContext;
import com.github.chinnayyanalla.ruleengine.model.RuleDataSource;
import com.github.chinnayyanalla.ruleengine.model.RuleDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ComponentScan
@Configuration
@AutoConfigurationPackage
public class RuleEngineAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnClass(SpringApplicationContext.class)
	public SpringApplicationContext springApplicationContext() {
		log.info("Creating Spring Application Context Bean");
		return new SpringApplicationContext();
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnClass(RuleDataSource.class)
	public RuleDataSource ruleDataSource(@Autowired SpringApplicationContext springApplicationContext) {
		Map<String, Object> beansWithAnnotation = springApplicationContext.getBeansWithAnnotation(RuleRepository.class);

		RuleDataSource ruleDataSource = new RuleDataSource();

		if (CollectionUtils.isEmpty(beansWithAnnotation)) {
			return ruleDataSource;
		}

		Map<String, RuleDefinition> ruleDefinitionMap = new HashMap<>();

		beansWithAnnotation.entrySet().stream().forEach(singleBean -> {
			Method[] methods = AopUtils.getTargetClass(springApplicationContext.getBean(singleBean.getKey()))
					.getDeclaredMethods();

			log.info("Created Rule Bean : {}", singleBean.getKey());

			for (Method method : methods) {
				if (method.isAnnotationPresent(RuleMapping.class)) {

					RuleMapping ruleMapping = method.getAnnotation(RuleMapping.class);
					if (ruleDefinitionMap.containsKey(ruleMapping.code())) {
						String errorMessage = String.format(
								"%n%nDescription: %nThe rule %s, defined in bean [%s] [%s], could not be registered.%nA rule with that code has already been defined and overriding is disabled.%n%nAction:%nConsider renaming one of the rule%n%n",
								ruleMapping.code(), ruleMapping.name(), singleBean.getKey());
						throw new IllegalArgumentException(errorMessage);
					}

					log.info("Registered Rule {} - {}", ruleMapping.code(), ruleMapping.name());

					RuleDefinition ruleDefinition = new RuleDefinition();
					ruleDefinition.setCode(ruleMapping.code());
					ruleDefinition.setName(ruleMapping.name());
					ruleDefinition.setType(ruleMapping.type());
					ruleDefinition.setBeanName(singleBean.getKey());
					ruleDefinition.setRule(method);
					ruleDefinitionMap.put(ruleMapping.code(), ruleDefinition);

				}

			}
		});

		ruleDataSource.setRuleDefinitionMap(ruleDefinitionMap);

		return ruleDataSource;
	}

}
