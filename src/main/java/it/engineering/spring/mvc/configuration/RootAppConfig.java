package it.engineering.spring.mvc.configuration;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
@ComponentScan(basePackages = {
		"it.engineering.spring.mvc.service",
		"it.engineering.spring.mvc.dao",
		"it.engineering.spring.mvc.converter"
})
@Import(value = DatabaseConfig.class)
public class RootAppConfig {
	public RootAppConfig() {
		System.out.println("================= CONSTRUCTOR =================");
		System.out.println("--------------- public RootAppConfig() ---------------");
	}
	
	@Bean
	public Validator localValidatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		return bean;
	}
}