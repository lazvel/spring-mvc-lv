package it.engineering.spring.mvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class ViewResolverConfig {
	/* VIEW RESOLVER */
	// definisati odgovarajuci view resolver
	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("================ jspViewResoler ================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		// logicko ime = my-index
		resolver.setPrefix("/WEB-INF/pages/"); // folder
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver; 
	}
	
//	@Bean
//	public ViewResolver jspViewResoler2() {
//		System.out.println("================ jspViewResoler ================");
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		// logicko ime = my-index
//		resolver.setPrefix("/WEB-INF/pages/city/");
//		resolver.setSuffix(".jsp");
//		resolver.setOrder(3);
//		return resolver; 
//	}
	
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
}
